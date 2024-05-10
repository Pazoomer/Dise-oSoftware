
package entidades;

import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
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

/**
 *
 * @author t1pas
 */
public class CrudCampus {
    private static MongoCollection<EntidadCampus> coleccion;
    private final static Logger LOG = Logger.getLogger(CrudCampus.class.getName());
    IConexion conexion;

    public CrudCampus() {
        coleccion = Conexion.getDatabasee().getCollection("Campus", EntidadCampus.class);
    }

    public EntidadUbicacion agregarUbicacionACampus(EntidadUbicacion ubicacion)throws PersistenciaExceptionn{
        try{
            EntidadCampus campus=new EntidadCampus(ubicacion.getCampus());
            campus=obtenerCampus(campus);
            UpdateResult result=coleccion.updateOne(Filters.eq("nombre", campus.getNombre()),Updates.push("ubicaciones", ubicacion));
            if(result.getModifiedCount()>0)return ubicacion;
            return null;
        }catch(MongoException e){
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al agregar la ubicacion al campus.");
        }
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
            coleccion.replaceOne(eq("nombre", campus.getNombre()), campus);
            return campus;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al actualizar el campus.");
        }
    }

    public boolean eliminarCampus(EntidadCampus campusParametro) throws PersistenciaExceptionn {
        try {
            coleccion.deleteOne(eq("nombre", campusParametro.getNombre()));
            return true;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al eliminar el campus.");
        }
    }

    public EntidadCampus obtenerCampus(EntidadCampus campusParametro) throws PersistenciaExceptionn {
        try {
            EntidadCampus campusEncontrado = coleccion.find(eq("nombre", campusParametro.getNombre())).first();
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
            System.out.println(entidadUbicacion);
            EntidadCampus campusEncontrado=obtenerCampus(new EntidadCampus(entidadUbicacion.getCampus()));
            System.out.println(campusEncontrado);
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

    public EntidadUbicacion obtenerUbi(EntidadUbicacion ubicacion)throws PersistenciaExceptionn {
        try{
            Bson filter=Filters.elemMatch("ubicaciones",Filters.eq("identificador", ubicacion.getIdentificador()));
            EntidadCampus registro=coleccion.find(filter).first();
            List<EntidadUbicacion> ubi;
            if(registro!=null){
                ubi=registro.getUbicaciones();
                for (EntidadUbicacion entidadUbicacion : ubi){
                    if(entidadUbicacion.getIdentificador().equals(ubicacion.getIdentificador())) return entidadUbicacion;
                }
            }
            return null;
        }catch(MongoException e){
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al obtener la ubicacion");
        }
    }
    
    public boolean agregarEventoAUbicacion(EntidadUbicacion ubicacion, EntidadEvento evento)throws PersistenciaExceptionn{
        try{
            Bson filters=Filters.and(Filters.eq("nombre", ubicacion.getCampus()),
                    Filters.elemMatch("ubicaciones", Filters.eq("identificador", ubicacion.getIdentificador())));
            EntidadEvento eventoReducido=new EntidadEvento();
            eventoReducido.setNombre(evento.getNombre());
            eventoReducido.setTipo(evento.getTipo());
            UpdateResult updateRes=coleccion.updateOne(filters, Updates.push("ubicaciones.$.eventos",eventoReducido));
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
