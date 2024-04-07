
package subsistemas.conexionGoogleMaps;

import conexion.IConexionDAO;
import excepciones.NegocioException;
import java.sql.PreparedStatement;
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
