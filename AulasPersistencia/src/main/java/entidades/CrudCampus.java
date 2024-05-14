
package entidades;

import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Aggregates.lookup;
import static com.mongodb.client.model.Filters.eq;
import excepcioness.PersistenciaExceptionn;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.conversions.Bson;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import java.util.Iterator;
import org.bson.types.ObjectId;

/**
 *
 * @author t1pas
 */
public class CrudCampus {
    private static MongoCollection<EntidadCampus> coleccion;
    private final static Logger LOG = Logger.getLogger(CrudCampus.class.getName());

    public CrudCampus() {
        coleccion = Conexion.getDatabasee().getCollection("Campus", EntidadCampus.class);
    }

    public EntidadCampus agregarCampus(EntidadCampus campus) throws PersistenciaExceptionn {
        try {
            coleccion.insertOne(campus);
            return campus;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al agregar el campus.");
        }
    }

    public EntidadCampus editarCampus(EntidadCampus campus) throws PersistenciaExceptionn {
        try {

            if (campus.getUbicaciones() != null && !campus.getUbicaciones().isEmpty()) {

                for (EntidadUbicacion ubicacion : campus.getUbicaciones()) {
                    if (ubicacion.getId() == null) {
                        ubicacion.setId(new ObjectId()); // Generar un nuevo ID
                    }
                }
            }
            coleccion.replaceOne(eq("_id", campus.getId()), campus);

            return campus;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al actualizar el campus.");
        }
}

    public boolean eliminarCampus(EntidadCampus campusParametro) throws PersistenciaExceptionn {
        try {
            coleccion.deleteOne(eq("_id", campusParametro.getId()));
            return true;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al eliminar el campus.");
        }
    }

    public EntidadCampus obtenerCampus(EntidadCampus campusParametro) throws PersistenciaExceptionn {
        try {
            EntidadCampus campusEncontrado = coleccion.find(eq("_id", campusParametro.getId())).first();
            return campusEncontrado;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al obtener el campus.");
        }
    }
    
    public EntidadCampus obtenerCampusPorNombre(String nombre) throws PersistenciaExceptionn {
        try {
            EntidadCampus campusEncontrado = coleccion.find(eq("nombre", nombre)).first();
            return campusEncontrado;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al obtener el campus.");
        }
    }

    public List<EntidadUbicacion> obtenerUbicacionesPorCampus(EntidadCampus campus) throws PersistenciaExceptionn {

        try {
            EntidadCampus campusEncontrado = obtenerCampus(campus);
            if (campusEncontrado != null) {
                return campusEncontrado.getUbicaciones();
            } else {
                throw new PersistenciaExceptionn("No se encontro al campus " + campus.getNombre());
            }

        } catch (MongoException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al obtener las ubicaciones del campus " + campus.getNombre());
        }
    }
    
    public List<EntidadUbicacion> obtenerUbicacionesPorNombreCampus(String nombre) throws PersistenciaExceptionn {

        try {
            EntidadCampus campusEncontrado = obtenerCampusPorNombre(nombre);
            if (campusEncontrado != null) {
                return campusEncontrado.getUbicaciones();
            } else {
                throw new PersistenciaExceptionn("No se encontro al campus " +nombre);
            }

        } catch (MongoException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al obtener las ubicaciones del campus " + nombre);
        }
    }

    public List<EntidadCampus> obtenerTodosLosCampus() throws PersistenciaExceptionn {
        List<EntidadCampus> campus = new ArrayList<>();
        try {
            FindIterable<EntidadCampus> resultados = coleccion.find();
            if (campus != null) {
                for (EntidadCampus entidad : resultados) {
                    campus.add(entidad);
                }
            } else {
                throw new PersistenciaExceptionn("No se encontro ningun campus");
            }
            return campus;
        } catch (MongoException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al obtener todos los campus");
        }
    }

    public EntidadUbicacion obtenerUbicacion(EntidadUbicacion entidadUbicacion) throws PersistenciaExceptionn {
        try {

            EntidadCampus campusEncontrado = obtenerCampus(new EntidadCampus(entidadUbicacion.getCampus()));

            if (campusEncontrado != null) {

                if (campusEncontrado.getUbicaciones() != null) {
                    for (EntidadUbicacion ubicacion : campusEncontrado.getUbicaciones()) {
                        if (ubicacion.equals(entidadUbicacion)) {
                            return ubicacion;
                        }
                    }
                }
            }

        } catch (PersistenciaExceptionn e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al obtener el campus.");
        }
        return null;
    }
    

    public EntidadUbicacion obtenerUbi(EntidadUbicacion ubicacion) throws PersistenciaExceptionn {
        try {
            Bson filter = Filters.eq("ubicaciones.identificador", ubicacion.getIdentificador());
            EntidadCampus registro = coleccion.find(filter).first();
            List<EntidadUbicacion> ubi;
            if (registro != null) {
                ubi = registro.getUbicaciones();
                return ubi.get(0);
            }
            return null;
        } catch (MongoException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al obtener la ubicacion");
        }
    }

    public EntidadUbicacion agregarUbicacion(EntidadUbicacion ubicacion) throws PersistenciaExceptionn {
        try {
            EntidadCampus campus = new EntidadCampus();
            campus.setId(ubicacion.getCampus());
            
            //Obtiene el campus de la ubicacion
            campus = obtenerCampus(campus);
            
            //Añade la ubicacion a el campus
            campus.getUbicaciones().add(ubicacion);
            editarCampus(campus);
            
            return ubicacion;
        } catch (PersistenciaExceptionn e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al agregar la ubicacion.");
        }
    }
    
    public EntidadUbicacion editarUbicacion(EntidadUbicacion ubicacion) throws PersistenciaExceptionn {
        try {
            EntidadCampus campus = new EntidadCampus();
            campus.setId(ubicacion.getCampus());

            //Obtiene el campus de la ubicacion
            campus = obtenerCampus(campus);

            ///Edita la ubicacion a el campus
            List<EntidadUbicacion> ubicaciones = campus.getUbicaciones();
            Iterator<EntidadUbicacion> iterator = ubicaciones.iterator();
            
            while (iterator.hasNext()) {
                EntidadUbicacion ubicacionIterable = iterator.next();
                
                if (ubicacionIterable.equals(ubicacion)) {
                    iterator.remove();
                    ubicaciones.add(ubicacion);
                    break; // Termina el bucle una vez que se ha encontrado y editado la ubicación
                }
                
            }
            editarCampus(campus);
            
            return ubicacion;
        } catch (PersistenciaExceptionn e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        throw new PersistenciaExceptionn("Hubo un error al actualizar la ubicacion.");
    }
}

    public Boolean eliminarUbicacion(EntidadUbicacion ubicacionParametro) throws PersistenciaExceptionn {
        try {

            EntidadCampus campus = new EntidadCampus();
            campus.setId(ubicacionParametro.getCampus());

            //Obtiene el campus de la ubicacion
            campus = obtenerCampus(campus);

            //Elimina la ubicacion a el campus
            campus.getUbicaciones().remove(ubicacionParametro);
            editarCampus(campus);

            return true;
        } catch (PersistenciaExceptionn e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al eliminar la ubicacion.");
        }
    }
    
    public boolean agregarEventoAUbicacion(EntidadUbicacion ubicacion, EntidadEvento evento)throws PersistenciaExceptionn{
        try{
            Bson filters=Filters.and(Filters.eq("_id", ubicacion.getCampus()),
                    Filters.elemMatch("ubicaciones", Filters.eq("identificador", ubicacion.getIdentificador())));
//            EntidadEvento eventoReducido=new EntidadEvento();
//            eventoReducido.setNombre(evento.getNombre());
//            eventoReducido.setTipo(evento.getTipo());
            UpdateResult updateRes=coleccion.updateOne(filters, Updates.push("ubicaciones.$.eventos",evento));
            return updateRes.getModifiedCount()>0;
        }catch(MongoException e){
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al agregar el evento a la ubicacion");
        }
    }

    
    public boolean cerrarConexion(){
        Conexion.cerrarConexion();
        return true;
    }
    
    
}
