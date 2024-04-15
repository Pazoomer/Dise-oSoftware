
package conexion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author t1pas
 */
public class ClaseConexion{

    private static final EntityManagerFactory emf=Persistence.createEntityManagerFactory("PUAula");
    private static EntityManager em;
    
    private ClaseConexion(){
    }
    
    public static synchronized EntityManager getEntityManager(){
        if(em==null){
            em=emf.createEntityManager();
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
    
//    /**
//     * Contraseña de la base de datos 1234a
//     *
//     * @return
//     */
//    @Override
//    public EntityManager crearConexion() {
//
//        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("PUAula");
//
//        EntityManager entityManager = emFactory.createEntityManager();
//
//        return entityManager;
//    }
}
