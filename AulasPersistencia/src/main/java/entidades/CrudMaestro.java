package entidades;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import excepcioness.PersistenciaExceptionn;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.conversions.Bson;

public class CrudMaestro {

    private static MongoCollection<EntidadMaestro> coleccion;
    private final static Logger LOG = Logger.getLogger(CrudMaestro.class.getName());
    IConexion conexion;

    public CrudMaestro() {
        conexion=new Conexion();
        coleccion = conexion.ConversionDocumentMaestro();
    }

    public EntidadMaestro agregarMaestro(EntidadMaestro maestro) throws PersistenciaExceptionn {
        try {
            coleccion.insertOne(maestro);
            return maestro;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al agregar el maestro.");
        }
    }
    
    public EntidadMaestro editarMaestro(EntidadMaestro maestro) throws PersistenciaExceptionn {
        System.out.println(maestro);
    try {
        coleccion.replaceOne(eq("idMaestro", maestro.getIdMaestro()), maestro);  
        return maestro;
    } catch (Exception e) {
        LOG.log(Level.SEVERE, e.getMessage(), e);
        throw new PersistenciaExceptionn("Hubo un error al actualizar el maestro.");
    }
}

    public boolean eliminarMaestro(EntidadMaestro maestroParametro) throws PersistenciaExceptionn {
        try {
            coleccion.deleteOne(eq("idMaestro", maestroParametro.getIdMaestro()));
            return true;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al eliminar el maestro.");
        }
    }

    public EntidadMaestro obtenerMaestro(EntidadMaestro maestroParametro) throws PersistenciaExceptionn {
        try {
            EntidadMaestro maestroEncontrado = coleccion.find(eq("idMaestro", maestroParametro.getIdMaestro())).first();
            return maestroEncontrado;
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

            EntidadMaestro maestroEntidad = obtenerMaestro(maestro);

            if (maestroEntidad != null) {

                if (maestroEntidad.getCalendario() != null) {
                    for (EntidadEvento evento : maestroEntidad.getCalendario()) {
                        if (evento.getFechaInicio().before(fechaInicio.getTime()) && evento.getFechaFin().after(fechaFin.getTime())) {
                            eventos.add(evento);
                        }

                    }
                }
            } else {
                throw new PersistenciaExceptionn("No se encontro al maestro");
            }
            return eventos;
        } catch (PersistenciaExceptionn e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al obtener los eventos del mes");
        }
    }

    public String fechaToString(Calendar fecha) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        return formatoFecha.format(fecha.getTime());
    }
    
    public boolean agregarEventoCalendario(EntidadMaestro maestro,EntidadEvento evento)throws PersistenciaExceptionn{
        Bson filtro=Filters.eq("idMaestro", maestro.getIdMaestro());
        Bson updates=Updates.push("calendario", evento);
        
        UpdateResult result = coleccion.updateOne(filtro, updates);
        return (result.getModifiedCount() > 0) ;
    }
    
    public boolean cerrarConexion(){
        conexion.cerrarConexion();
        return true;
    }
}
