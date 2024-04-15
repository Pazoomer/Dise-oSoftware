/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import excepcioness.PersistenciaExceptionn;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author luiis
 */
public class ClaseConexion {
    private static final EntityManagerFactory emf=Persistence.createEntityManagerFactory("AulasPU");
    private static EntityManager em;
    
    private ClaseConexion(){}
    
    public static synchronized EntityManager getEntityManager(){
        if(em==null){
            em=emf.createEntityManager();
        }
        return em;
    }
    
    public static void cerrarConexion()throws PersistenciaExceptionn{
        try{
            if (em.isOpen()) {
                em.close();
                System.out.println("entity manager cerrado");
            }
            if (emf.isOpen()) {
                emf.close();
                System.out.println("entity manager factory cerrado");
            }
        }catch(Exception e){
            throw new PersistenciaExceptionn("hubo un error al cerrar la conexion con la base de datos");
        }
        
    }
}
