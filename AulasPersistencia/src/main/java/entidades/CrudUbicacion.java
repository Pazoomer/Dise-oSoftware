package entidades;

import com.mongodb.MongoException;
import excepcioness.PersistenciaExceptionn;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;
import java.util.ArrayList;
import org.bson.types.ObjectId;

/**
 * Clase para realizar operaciones CRUD sobre la colección de ubicaciones en MongoDB.
 */
public class CrudUbicacion {
    private final MongoCollection<Document> coleccion;
    private final static Logger LOG = Logger.getLogger(CrudUbicacion.class.getName());

    public CrudUbicacion() {
        this.coleccion = ClaseConexion.getColeccion("Ubicaciones");
    }

    /**
     * Crea una nueva ubicación en la base de datos.
     * @param ubicacion La ubicación a crear.
     * @throws PersistenciaExceptionn Si ocurre un error durante la operación.
     */
    public void crearUbicacion(EntidadUbicacion ubicacion) throws PersistenciaExceptionn {
        try {
            Document doc = new Document("identificador", ubicacion.getIdentificador())
                .append("descripcion", ubicacion.getDescripcion());
            coleccion.insertOne(doc);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al crear la ubicación");
        }
    }

    /**
     * Obtiene una ubicación basada en su identificador.
     * @param identificador El identificador de la ubicación.
     * @return La ubicación encontrada o null si no se encuentra.
     * @throws PersistenciaExceptionn Si ocurre un error durante la operación.
     */
    public EntidadUbicacion obtenerUbicacion(String identificador) throws PersistenciaExceptionn {
        try {
            Document doc = coleccion.find(eq("identificador", identificador)).first();
            if (doc != null) {  
                return documentToEntidadUbicacion(doc);
            } else {
                throw new PersistenciaExceptionn("No se encontró la ubicación con el identificador especificado");
            }
        } catch (PersistenciaExceptionn e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al obtener la ubicación");
        }
    }

    /**
     * Actualiza una ubicación existente en la base de datos.
     * @param ubicacion La ubicación actualizada.
     * @throws PersistenciaExceptionn Si ocurre un error durante la operación.
     */
    public void actualizarUbicacion(EntidadUbicacion ubicacion) throws PersistenciaExceptionn {
        try {
            coleccion.updateOne(eq("_id", ubicacion.getId()),
                    set("descripcion", ubicacion.getDescripcion()));
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al actualizar la ubicación");
        }
    }

    /**
     * Elimina una ubicación de la base de datos.
     *
     * @param id El ID de la ubicación a eliminar.
     * @throws PersistenciaExceptionn Si ocurre un error durante la operación.
     */
    public void eliminarUbicacion(ObjectId id) throws PersistenciaExceptionn {
        try {
            coleccion.deleteOne(eq("_id", id));
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al eliminar la ubicación");
        }
    }

    public List<EntidadUbicacion> obtenerUbicacionesPorCampus(EntidadCampus campus) throws PersistenciaExceptionn {
        List<EntidadUbicacion> ubicacionesEncontradas = new ArrayList<>();
        Document query = new Document("campus_id", new ObjectId(campus.getId().toString()));
        try (MongoCursor<Document> cursor = coleccion.find(query).iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                EntidadUbicacion ubicacion = documentToEntidadUbicacion(doc);
                ubicacionesEncontradas.add(ubicacion);
            }
        } catch (MongoException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al obtener las ubicaciones del campus " + campus.getNombre());
        }
        return ubicacionesEncontradas;
    }

    public List<EntidadUbicacion> obtenerUbicaciones() throws PersistenciaExceptionn {
        List<EntidadUbicacion> ubicacionesEncontradas = new ArrayList<>();
        try (MongoCursor<Document> cursor = coleccion.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                EntidadUbicacion ubicacion = documentToEntidadUbicacion(doc);
                ubicacionesEncontradas.add(ubicacion);
            }
        } catch (MongoException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al obtener las ubicaciones");
        }
        return ubicacionesEncontradas;
    }

    // Método para convertir un Document a una instancia de EntidadUbicacion
    private EntidadUbicacion documentToEntidadUbicacion(Document doc) {
        EntidadUbicacion ubicacion = new EntidadUbicacion();
        ubicacion.setId(doc.getObjectId("_id"));
        ubicacion.setIdentificador(doc.getString("identificador"));
        ubicacion.setDescripcion(doc.getString("descripcion"));
        ubicacion.setCampus((EntidadCampus) doc.get("campus"));
        ubicacion.setEventos((List<EntidadEvento>) doc.get("calendario"));
        return ubicacion;
    }


}
