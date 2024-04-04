
package conexion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author t1pas
 */
public class ConexionDAO implements IConexionDAO{

    /**
     * Contraseña de la base de datos 1234a
     *
     * @return
     */
    @Override
    public EntityManager crearConexion() {

        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("PUAula");

        EntityManager entityManager = emFactory.createEntityManager();

        return entityManager;
    }
}
