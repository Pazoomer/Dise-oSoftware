
package entidades;

import conexion.ClaseConexion;
import excepciones.PersistenciaException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NoResultException;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author t1pas
 */
@Entity
@Table(name = "maestro")
public class EntidadMaestro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_maestro_bd")
    private Long idBd;
    
    @Column(name="id_maestro",nullable=false)
    private Long idMaestro; //Representa el identificador fuera de la base de datos
    
    @Column(name="nombre",nullable=true,length=150)
    private String nombre;
    
    @Column(name="cubiculo", nullable = true, length = 50)
    private String cubiculo;

    @Column(name = "descripcion", nullable = true, length = 200)
    private String descripcion;
    
    @Column(name = "foto", nullable = true, length = 300)
    private String foto;
    
    @OneToMany(mappedBy = "maestro", cascade = CascadeType.ALL)
    private List<EntidadEvento> calendario;
    
    private final OperacionesPersistencia operacionesPers;
    
    public EntidadMaestro() {
        this.operacionesPers = new OperacionesPersistencia();
        this.calendario=new ArrayList<>();
    }
    
    public EntidadMaestro(Long idMaestro, String nombre, String cubiculo, String descripcion, String foto, List<EntidadEvento> calendario) {
        this.operacionesPers = new OperacionesPersistencia();
        this.idMaestro = idMaestro;
        this.nombre = nombre;
        this.cubiculo = cubiculo;
        this.descripcion = descripcion;
        this.foto = foto;
        this.calendario = calendario;
    }

    public EntidadMaestro(Long idMaestro, String nombre, String cubiculo, String descripcion, String foto) {
        this.operacionesPers = new OperacionesPersistencia();
        this.idMaestro = idMaestro;
        this.nombre = nombre;
        this.cubiculo = cubiculo;
        this.descripcion = descripcion;
        this.foto = foto;
    }

    public Long getIdBd() {
        return idBd;
    }

    public void setIdBd(Long idBd) {
        this.idBd = idBd;
    }

    public Long getIdMaestro() {
        return idMaestro;
    }

    public void setIdMaestro(Long idMaestro) {
        this.idMaestro = idMaestro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCubiculo() {
        return cubiculo;
    }

    public void setCubiculo(String cubiculo) {
        this.cubiculo = cubiculo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<EntidadEvento> getCalendario() {
        return calendario;
    }

    public EntidadMaestro obtenerMaestro(EntidadMaestro maestro)throws PersistenciaException{
        try{
            return operacionesPers.obtenerMaestro(maestro);
        }catch(PersistenciaException e){
            throw e;
        }
    }
    
    public EntidadMaestro editarMaestro(EntidadMaestro maestroEditado)throws PersistenciaException{
        try{
            return operacionesPers.editarMaestro(maestroEditado);
        }catch(PersistenciaException e){
            throw e;
        }
    }
    
    public void setCalendario(List<EntidadEvento> calendario) {
        this.calendario = calendario;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Maestro{");
        sb.append("idBd=").append(idBd);
        sb.append(", idMaestro=").append(idMaestro);
        sb.append(", nombre=").append(nombre);
        sb.append(", cubiculo=").append(cubiculo);
        sb.append(", descripcion=").append(descripcion);
        sb.append(", foto=").append(foto);
        sb.append(", calendario=").append(calendario);
        sb.append('}');
        return sb.toString();
    }
 
    static class OperacionesPersistencia implements Serializable{
        private EntityManager em;
        private CriteriaBuilder cb;
        private final static Logger LOG = Logger.getLogger(OperacionesPersistencia.class.getName());
        
        OperacionesPersistencia(){
            em=ClaseConexion.getEntityManager();
            cb=em.getCriteriaBuilder();
        }
        
        EntidadMaestro obtenerMaestro(EntidadMaestro maestro)throws PersistenciaException{
            CriteriaQuery<EntidadMaestro> criteria=cb.createQuery(EntidadMaestro.class);
            Root<EntidadMaestro> root=criteria.from(EntidadMaestro.class);
            
            criteria.select(root).
                    where(cb.equal(root.get("idMaestro"),maestro.getIdMaestro()));
            
            TypedQuery<EntidadMaestro> query=em.createQuery(criteria);
            EntidadMaestro maestroEncontrado;
            try{
                maestroEncontrado=query.getSingleResult();
                return maestroEncontrado;
            }catch(NoResultException nre){
                LOG.log(Level.SEVERE, nre.getMessage(), nre);
                throw new PersistenciaException("no se encontro el maestro con el id especificado");
            }
            catch(Exception e){
                LOG.log(Level.SEVERE, e.getMessage(), e);
                throw new PersistenciaException("hubo un error al obtener el maestro");
            }
        }
        
        EntidadMaestro editarMaestro(EntidadMaestro maestro)throws PersistenciaException{
            EntidadMaestro maestroEditado;
            if(em.find(EntidadMaestro.class, maestro.getIdBd())!=null){
                try{
                    em.getTransaction().begin();
                    maestroEditado=em.merge(maestro);
                    em.getTransaction().commit();
                    return maestroEditado;
                }catch(Exception e){
                    em.getTransaction().rollback();
                    LOG.log(Level.SEVERE, e.getMessage(), e);
                    throw new PersistenciaException("hubo un error al editar el maestro");
                }
            }
            return null;
        }
        
    }
}
