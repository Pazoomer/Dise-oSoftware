/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import excepcioness.PersistenciaExceptionn;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author luiis
 */
public class CrudMaestro {
    private EntityManager em;
    private CriteriaBuilder cb;
    private final static Logger LOG = Logger.getLogger(CrudMaestro.class.getName());

    public CrudMaestro() {
        em = ClaseConexion.getEntityManager();
        cb = em.getCriteriaBuilder();
    }

    public EntidadMaestro obtenerMaestro(EntidadMaestro maestro) throws PersistenciaExceptionn {
        CriteriaQuery<EntidadMaestro> criteria = cb.createQuery(EntidadMaestro.class);
        Root<EntidadMaestro> root = criteria.from(EntidadMaestro.class);

        criteria.select(root).
                where(cb.equal(root.get("idMaestro"), maestro.getIdMaestro()));

        TypedQuery<EntidadMaestro> query = em.createQuery(criteria);
        EntidadMaestro maestroEncontrado;
        try {
            maestroEncontrado = query.getSingleResult();
            return maestroEncontrado;
        } catch (NoResultException nre) {
            LOG.log(Level.SEVERE, nre.getMessage(), nre);
            throw new PersistenciaExceptionn("no se encontro el maestro con el id especificado");
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("hubo un error al obtener el maestro");
        }
    }

    public EntidadMaestro editarMaestro(EntidadMaestro maestro) throws PersistenciaExceptionn {
        EntidadMaestro maestroEditado;
        try {
            maestroEditado = em.find(EntidadMaestro.class, maestro.getIdBd());
            if (maestroEditado != null) {
                em.getTransaction().begin();
                maestroEditado = em.merge(maestro);
                em.getTransaction().commit();
                return maestroEditado;
            }
        }catch(IllegalArgumentException iae){
            LOG.log(Level.SEVERE, iae.getMessage(), iae);
            throw new PersistenciaExceptionn("hubo un error al editar el maestro");
        } 
        catch (Exception e) {
            em.getTransaction().rollback();
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("hubo un error al editar el maestro");
        }
        return null;
    }
    
    public List<EntidadEvento> obtenerEventosMes(EntidadMaestro maestro, Calendar fecha, String filtro)throws PersistenciaExceptionn{
        CriteriaQuery<EntidadEvento> criteria=cb.createQuery(EntidadEvento.class);
        Root<EntidadEvento> root=criteria.from(EntidadEvento.class);
        Join<EntidadEvento, EntidadMaestro> join=root.join("maestro");
        
        Calendar fechaInicio=fecha;
        Calendar fechaFin=fechaInicio;
        if(filtro.equals("mes")){
            fechaInicio.set(Calendar.DAY_OF_MONTH, 1);
            fechaFin.add(Calendar.MONTH, 1);
        }
        else{
            int semana=fecha.get(Calendar.WEEK_OF_MONTH);
            fechaInicio.set(Calendar.WEEK_OF_MONTH, semana);
            fechaInicio.set(Calendar.DAY_OF_WEEK, 2);
            fechaFin=fechaInicio;
            fechaFin.add(Calendar.DATE,6);
            System.out.println("fecha incio");
            System.out.println(fechaToString(fechaInicio));
            System.out.println("fecha fin");
            System.out.println(fechaToString(fechaFin));
        }
        
        Predicate equal=cb.equal(join.get("idMaestro"), maestro.getIdMaestro());
        Predicate or=cb.or(cb.between(root.<Calendar>get("fechaInicio"), fechaInicio,fechaFin),
                cb.equal(root.<EntidadTipoEventoEnum>get("tipo"), EntidadTipoEventoEnum.SEMANAL));
        Predicate predicate=cb.and(equal,or);
        criteria.select(root).where(predicate);
        
        TypedQuery<EntidadEvento> query=em.createQuery(criteria);
        List<EntidadEvento> eventos;
        try{
            eventos=query.getResultList();
            return eventos;
        }catch(Exception e){
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al obtener los eventos del mes");
        }
    }
    
     public String fechaToString(Calendar fecha){
        SimpleDateFormat formatoFecha=new SimpleDateFormat("yyyy-MM-dd");
        return formatoFecha.format(fecha.getTime());
    }
}
