
package subsistemas.conexionGoogleMaps;

import conexion.IConexionDAO;
import excepciones.NegocioException;
import java.util.List;
import javax.persistence.EntityManager;
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

        // Consulta JPQL para obtener los nombres de todos los campus
        String jpql = "SELECT c.nombre FROM Campus c";

        // Crear una TypedQuery con la consulta JPQL
        TypedQuery<String> query = entityManager.createQuery(jpql, String.class);

        // Ejecutar la consulta y obtener los resultados
        nombresCampus = query.getResultList();
        entityManager.close();

        return nombresCampus;
    }

    List<String> accesoEdificiosPorCampusGoogleMaps(String campus) throws NegocioException {
        EntityManager entityManager = conexion.crearConexion();
        List<String> edificios;

        // Consulta JPQL para obtener los nombres de los edificios por campus
        String jpql = "SELECT u.nombre FROM Ubicacion u WHERE u.campus.nombre = :nombreCampus";

        // Crear una TypedQuery con la consulta JPQL
        TypedQuery<String> query = entityManager.createQuery(jpql, String.class);
        query.setParameter("nombreCampus", campus);

        // Ejecutar la consulta y obtener los resultados
        edificios = query.getResultList();
        
        entityManager.close();

        return edificios;
    }
    
}
