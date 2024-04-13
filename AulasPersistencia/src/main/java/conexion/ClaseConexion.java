
package conexion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author t1pas
 */
public class ClaseConexion implements IConexionDAO{

    private static final EntityManagerFactory emf=Persistence.createEntityManagerFactory("conexionPU");
    private static EntityManager em;
    private static ClaseConexion conexion;
    
    private ClaseConexion(){
        em=emf.createEntityManager();
    }
    
    public static synchronized EntityManager getEntityManager(){
        if(em==null){
            conexion=new ClaseConexion();
        }
        return em;
    }
    
    public static void cerrarConexion(){
        if(em.isOpen()){
            em.close();
            System.out.println("entity manager cerrado");
        }
        if(emf.isOpen()){
            emf.close();
            System.out.println("entity manager factory cerrado");
        }
    }
    
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
