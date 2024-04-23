package entidades;

import com.mongodb.client.MongoCollection;
import excepcioness.PersistenciaExceptionn;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.Calendar;

public class CrudEvento {
    private final static Logger LOG = Logger.getLogger(CrudEvento.class.getName());
    private final MongoCollection<Document> coleccion;

    public CrudEvento() {
        coleccion = ClaseConexion.getColeccion("Eventos");
    }

    public boolean agregarEvento(EntidadEvento evento) throws PersistenciaExceptionn {
        try {
            // Validar si el maestro asignado al evento está registrado (no se proporciona la implementación)
            // Aquí iría la lógica para verificar si el maestro está registrado en MongoDB
            // Si el maestro está registrado, se procede a agregar el evento
            Document doc = new Document("tipo", evento.getTipo().toString())
                    .append("nombre", evento.getNombre())
                    .append("descripcion", evento.getDescripcion())
                    .append("diasSemana", evento.getDiasSemana())
                    .append("ubicacion", evento.getUbicacion().getId().toString())
                    .append("color", evento.getColor())
                    .append("fechaInicio", evento.getFechaInicio().getTime())
                    .append("fechaFin", evento.getFechaFin().getTime())
                    .append("horaInicio", evento.getHoraInicio().getTime())
                    .append("horasDuracionEvento", evento.getHorasDuracionEvento())
                    .append("maestro", evento.getMaestro().getId().toString());
            coleccion.insertOne(doc);
            return true;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al agregar el evento");
        }
    }

    public EntidadEvento editarEvento(EntidadEvento evento) throws PersistenciaExceptionn {
        try {
            // Suponiendo que se puede buscar por el ID del evento en MongoDB
            Document filter = new Document("_id", new ObjectId(evento.getId().toString()));
            Document update = new Document("$set", new Document("tipo", evento.getTipo().toString())
                    .append("nombre", evento.getNombre())
                    .append("descripcion", evento.getDescripcion())
                    .append("diasSemana", evento.getDiasSemana())
                    .append("ubicacion", evento.getUbicacion().getId().toString())
                    .append("color", evento.getColor())
                    .append("fechaInicio", evento.getFechaInicio().getTime())
                    .append("fechaFin", evento.getFechaFin().getTime())
                    .append("horaInicio", evento.getHoraInicio().getTime())
                    .append("horasDuracionEvento", evento.getHorasDuracionEvento())
                    .append("maestro", evento.getMaestro().getId().toString()));
            // Se actualiza el documento en MongoDB
            coleccion.updateOne(filter, update);
            return evento;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al editar el evento");
        }
    }

    public boolean eliminarEvento(EntidadEvento evento) throws PersistenciaExceptionn {
        try {
            // Suponiendo que se puede buscar por el ID del evento en MongoDB
            Document filter = new Document("_id", new ObjectId(evento.getId().toString()));
            // Se elimina el documento en MongoDB
            coleccion.deleteOne(filter);
            return true;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al eliminar el evento");
        }
    }

    public EntidadEvento obtenerEvento(EntidadEvento evento) throws PersistenciaExceptionn {
        try {
            // Suponiendo que se puede buscar por el nombre del evento en MongoDB
            Document query = new Document("nombre", evento.getNombre());
            Document doc = coleccion.find(query).first();
            if (doc != null) {
                // Convertir el documento a una instancia de EntidadEvento
                return documentToEntidadEvento(doc);
            } else {
                return null;
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al obtener el evento");
        }
    }

    private EntidadEvento documentToEntidadEvento(Document doc) {
        ObjectId id = doc.getObjectId("_id");
        EntidadTipoEventoEnum tipo = EntidadTipoEventoEnum.valueOf(doc.getString("tipo"));
        String nombre = doc.getString("nombre");
        String descripcion = doc.getString("descripcion");
        String diasSemana = doc.getString("diasSemana");
        ObjectId ubicacionId = doc.getObjectId("ubicacion");
        String color = doc.getString("color");
        Calendar fechaInicio = Calendar.getInstance();
        fechaInicio.setTime(doc.getDate("fechaInicio"));
        Calendar fechaFin = Calendar.getInstance();
        fechaFin.setTime(doc.getDate("fechaFin"));
        Calendar horaInicio = Calendar.getInstance();
        horaInicio.setTime(doc.getDate("horaInicio"));
        Double horasDuracionEvento = doc.getDouble("horasDuracionEvento");
        ObjectId maestroId = doc.getObjectId("maestro");

        // Se construye la instancia de EntidadEvento
        EntidadEvento evento = new EntidadEvento(tipo, nombre, descripcion, diasSemana, new EntidadUbicacion(ubicacionId), color, fechaInicio, fechaFin, horaInicio, horasDuracionEvento, new EntidadMaestro(maestroId));
        evento.setId(id);
        return evento;
    }
}
