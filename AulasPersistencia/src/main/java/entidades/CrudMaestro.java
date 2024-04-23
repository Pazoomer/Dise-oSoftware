package entidades;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import excepcioness.PersistenciaExceptionn;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import org.bson.types.ObjectId;

public class CrudMaestro {

    private static MongoCollection<Document> coleccion;
    private final static Logger LOG = Logger.getLogger(CrudMaestro.class.getName());
    IConexion conexion;

    public CrudMaestro() {
        conexion=new Conexion();
        coleccion = conexion.getColeccion("Maestros");
    }

    public EntidadMaestro agregarMaestro(EntidadMaestro maestro) throws PersistenciaExceptionn {
        try {
            Document doc = new Document();
            doc.append("idMaestro", maestro.getIdMaestro())
               .append("nombre", maestro.getNombre())
               .append("cubiculo", maestro.getCubiculo().getIdentificador())
               .append("descripcion", maestro.getDescripcion())
               .append("foto", maestro.getFoto())
               .append("calendario", maestro.getCalendario());

            coleccion.insertOne(doc);
            conexion.cerrarConexion();
            return maestro;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al agregar el maestro.");
        }
    }

    public EntidadMaestro editarMaestro(EntidadMaestro maestro) throws PersistenciaExceptionn {
        try {
            Document filtro = new Document("_id", maestro.getId());
            Document actualizacion = new Document("$set", new Document("nombre", maestro.getNombre())
                                                          .append("cubiculo", maestro.getCubiculo().getIdentificador())
                                                          .append("descripcion", maestro.getDescripcion())
                                                          .append("foto", maestro.getFoto())
                                                          .append("calendario", maestro.getCalendario()));
            coleccion.updateOne(filtro, actualizacion);
            conexion.cerrarConexion();
            return maestro;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al editar el maestro.");
        }
    }

    public boolean eliminarMaestro(EntidadMaestro maestroParametro) throws PersistenciaExceptionn {
        try {
            coleccion.deleteOne(Filters.eq("idMaestro", maestroParametro.getIdMaestro()));
            conexion.cerrarConexion();
            return true;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al eliminar el maestro.");
        }
    }

    public EntidadMaestro obtenerMaestro(EntidadMaestro maestroParametro) throws PersistenciaExceptionn {
        
        try {
            Document doc = coleccion.find(Filters.eq("idMaestro", maestroParametro.getIdMaestro())).first();
            if (doc != null) {
                EntidadMaestro maestro = new EntidadMaestro();
                maestro.setId((ObjectId) doc.get("_id"));
                maestro.setIdMaestro(doc.getLong("idMaestro"));
                maestro.setNombre(doc.getString("nombre"));
                maestro.setCubiculo(new EntidadUbicacion(doc.getString("cubiculo")));
                maestro.setDescripcion(doc.getString("descripcion"));
                maestro.setFoto(doc.getString("foto"));
                maestro.setCalendario((List<EntidadEvento>) doc.get("calendario"));
                conexion.cerrarConexion();
                return maestro;
            }
            return null;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al obtener el maestro.");
        }
    }

    public List<EntidadEvento> obtenerEventosMes(EntidadMaestro maestro, Calendar fecha, String filtro) throws PersistenciaExceptionn {
        try {
            List<EntidadEvento> eventos = new ArrayList<>();
            Calendar fechaInicio = (Calendar) fecha.clone();
            Calendar fechaFin = (Calendar) fechaInicio.clone();

            if (filtro.equals("mes")) {
                fechaInicio.set(Calendar.DAY_OF_MONTH, 1);
                fechaFin.add(Calendar.MONTH, 1);
            } else {
                int semana = fecha.get(Calendar.WEEK_OF_MONTH);
                fechaInicio.set(Calendar.WEEK_OF_MONTH, semana);
                fechaInicio.set(Calendar.DAY_OF_WEEK, 2);
                fechaFin.setTimeInMillis(fechaInicio.getTimeInMillis());
                fechaFin.add(Calendar.DATE, 6);
            }

            Document docFiltro = new Document("maestro", maestro.getId())
                    .append("$or",
                            List.of(
                                    new Document("fechaInicio", new Document("$gte", fechaInicio).append("$lte", fechaFin)),
                                    new Document("tipo", "SEMANAL")
                            ));

            coleccion.find(docFiltro).forEach(doc -> {
                // Mapear el documento a la entidad EntidadEvento
                EntidadEvento evento = new EntidadEvento();
                evento.setNombre(doc.getString("nombre"));
                evento.setDescripcion(doc.getString("descripcion"));
                evento.setUbicacion((EntidadUbicacion) doc.get("ubicacion"));
                evento.setFechaInicio((Calendar) doc.get("fechaInicio"));
                evento.setHoraInicio((Calendar) doc.get("horaInicio"));
                evento.setHorasDuracionEvento(doc.getDouble("horasDuracionEvento"));
                // Agregar evento a la lista
                eventos.add(evento);
            });
            return eventos;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al obtener los eventos del mes");
        }
    }

    public String fechaToString(Calendar fecha) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        return formatoFecha.format(fecha.getTime());
    }
}
