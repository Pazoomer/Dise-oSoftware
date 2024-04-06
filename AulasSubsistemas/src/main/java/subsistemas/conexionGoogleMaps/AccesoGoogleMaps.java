
package subsistemas.conexionGoogleMaps;

import conexion.IConexionDAO;
import excepciones.NegocioException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author t1pas
 */
public class AccesoGoogleMaps {
    
     private final IConexionDAO conexion;
     
     public AccesoGoogleMaps(IConexionDAO conexion) {
        this.conexion = conexion;
    }

    List<String> accesoEdificiosGoogleMaps() throws NegocioException {
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

    List<String> accesoEdificiosPorCampusGoogleMaps(String campus) throws NegocioException {

        System.out.println(campus);
        EntityManager entityManager = conexion.crearConexion();
        List<String> nombresEdificios;

        try {
            // Consulta SQL nativa para obtener los nombres de los edificios por campus
            String sql = "SELECT nombre FROM ubicacion WHERE campus_id = (SELECT id FROM campus WHERE nombre = :nombreCampus)";

            // Crear una consulta nativa
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter("nombreCampus", campus);

            System.out.println(sql);
            // Ejecutar la consulta y obtener los nombres de los edificios
            nombresEdificios = query.getResultList();
        } finally {
            // Siempre cierra el EntityManager
            entityManager.close();
        }

        return nombresEdificios;

    }
}