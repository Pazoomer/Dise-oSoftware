
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

/**
 *
 * @author t1pas
 */
public class CrudCampus {
    private static MongoCollection<EntidadCampus> coleccion;
    private final static Logger LOG = Logger.getLogger(CrudCampus.class.getName());
    IConexion conexion;

    public CrudCampus() {
        conexion=new Conexion();
        coleccion = conexion.ConversionDocumentCampus();
    }

    public EntidadCampus agregarCampus(EntidadCampus campus) throws PersistenciaExceptionn {
        try {
            coleccion.insertOne(campus);
            conexion.cerrarConexion();
            return campus;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al agregar el campus.");
        }
    }
    
    public EntidadCampus editarCampus(EntidadCampus campus) throws PersistenciaExceptionn {
    try {
        coleccion.replaceOne(eq("nombre", campus.getNombre()), campus);
        conexion.cerrarConexion();
        return campus;
    } catch (Exception e) {
        LOG.log(Level.SEVERE, e.getMessage(), e);
        throw new PersistenciaExceptionn("Hubo un error al actualizar el campus.");
    }
}

    public boolean eliminarCampus(EntidadCampus campusParametro) throws PersistenciaExceptionn {
        try {
            coleccion.deleteOne(eq("nombre", campusParametro.getNombre()));
            conexion.cerrarConexion();
            return true;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al eliminar el campus.");
        }
    }

    public EntidadCampus obtenerCampus(EntidadCampus campusParametro) throws PersistenciaExceptionn {
        try {
            EntidadCampus campusEncontrado = coleccion.find(eq("nombre", campusParametro.getNombre())).first();
            conexion.cerrarConexion();
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
            EntidadCampus campusEncontrado=obtenerCampus(new EntidadCampus(entidadUbicacion.getCampus()));
            conexion.cerrarConexion();
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

}
