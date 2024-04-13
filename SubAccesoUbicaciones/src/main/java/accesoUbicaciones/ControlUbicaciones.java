
package accesoUbicaciones;

import conexion.IConexionDAO;
import excepciones.NegocioException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author t1pas
 */
public class ControlUbicaciones {
    
    
    private final IConexionDAO conexion;
    
     public ControlUbicaciones(IConexionDAO conexion) {
        this.conexion = conexion;
    }

    protected List<String> recuperarEdificios() {
        //IAccesoGoogleMaps dao = new FachadaAccesoGoogleMaps(conexion);

        try {
            return this.accesoEdificiosGoogleMaps();
        } catch (NegocioException ex) {
            Logger.getLogger(ControlUbicaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    protected List<String> recuperarEdificiosPorCampus(String campus) {
        //IAccesoGoogleMaps dao=new FachadaAccesoGoogleMaps(conexion);
        
        try {
            return this.accesoEdificiosPorCampusGoogleMaps(campus);
        } catch (NegocioException ex) {
            Logger.getLogger(ControlUbicaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private List<String> accesoEdificiosGoogleMaps() throws NegocioException {
        EntityManager entityManager = conexion.crearConexion();
        List<String> nombresCampus;

        // Consulta SQL nativa para obtener solo los nombres de todos los campus
        String sql = "SELECT nombre FROM Campus";

        // Crear una consulta nativa
        Query query = entityManager.createNativeQuery(sql);

        // Ejecutar la consulta y obtener los nombres de los campus
        nombresCampus = query.getResultList();
        
        entityManager.close();

        return nombresCampus;
    }

    private List<String> accesoEdificiosPorCampusGoogleMaps(String campus) throws NegocioException {

        EntityManager entityManager = conexion.crearConexion();
        List<String> nombresEdificios;

        try {

            if (campus.equalsIgnoreCase("Obregon Nainari")) {
                String sql1 = "SELECT nombre FROM ubicacion WHERE campus_id = 1";

                Query query1 = entityManager.createNativeQuery(sql1);

                // Ejecutar la consulta
                nombresEdificios = query1.getResultList();
            } else {
                String sql1 = "SELECT nombre FROM ubicacion WHERE campus_id = 2";

                Query query1 = entityManager.createNativeQuery(sql1);

                // Ejecutar la consulta
                nombresEdificios = query1.getResultList();
            }


        } finally {
            // Siempre cierra el EntityManager
            entityManager.close();
        }

        return nombresEdificios;

    }
}
