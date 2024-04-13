
package entidades;

import conexion.ClaseConexion;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author t1pas
 */
@Entity
@Table(name = "evento")
public class EntidadEvento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="evento_id")
    private Long id;
    
    @Column(name="tipo",nullable=true)
    @Enumerated(EnumType.STRING)
    private EntidadTipoEventoEnum tipo;
    
    @Column(name="nombre",nullable=true,length=100)
    private String nombre;
    
    @Column(name="descripcion",nullable=true,length=200)
    private String descripcion;
    
    @Column(name="diasSemana",nullable=true,length=8)
    private String diasSemana;
    
    @Column(name="ubicacion",nullable=true,length=100)
    private String ubicacion;
    
    @Column(name="color",nullable=true,length=100)
    private String color;
    
    @Temporal(TemporalType.DATE)
    @Column(name="fechaInicio",nullable=true)
    private Calendar fechaInicio;
    
    @Temporal(TemporalType.DATE)
    @Column(name="fechaFin",nullable=true)
    private Calendar fechaFin;
    
    @Column(name="horaInicio",nullable=true)
    @Temporal(TemporalType.TIME)
    private Calendar horaInicio;
    
    @Column(name="horasDuracionEvento",nullable=true)
    private float horasDuracionEvento;
    
    @ManyToOne
    @JoinColumn(name = "id_maestro_bd", nullable = false)
    private EntidadMaestro maestro;
    
    private final OperacionesPersistencia operacionesPers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
     public EntidadEvento() {
        operacionesPers = new OperacionesPersistencia();
    }

    public EntidadEvento(EntidadTipoEventoEnum tipo, String nombre, String descripcion,
            String diasSemana, String ubicacion, String color, Calendar fechaInicio, 
            Calendar fechaFin, Calendar horaInicio, float horasDuracionEvento, EntidadMaestro maestro) {
        this.operacionesPers = new OperacionesPersistencia();
        this.tipo = tipo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.diasSemana = diasSemana;
        this.ubicacion = ubicacion;
        this.color = color;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.horaInicio = horaInicio;
        this.horasDuracionEvento = horasDuracionEvento;
        this.maestro = maestro;
    }
     
     

    public EntidadEvento(EntidadTipoEventoEnum tipo, String nombre, String descripcion, 
            String diasSemana, String ubicacion, String color, Calendar fechaInicio, 
            Calendar fechaFin, Calendar horaInicio, float horasDuracionEvento) {
        this.operacionesPers = new OperacionesPersistencia();
        this.tipo = tipo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.diasSemana = diasSemana;
        this.ubicacion = ubicacion;
        this.color = color;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.horaInicio = horaInicio;
        this.horasDuracionEvento = horasDuracionEvento;
    }
     
     

    public EntidadEvento(EntidadTipoEventoEnum tipo, String nombre, String descripcion, 
            String diasSemana, String ubicacion, Calendar fechaInicio, 
            Calendar fechaFin, Calendar horaInicio, float horasDuracionEvento, EntidadMaestro maestro) {
        this.operacionesPers = new OperacionesPersistencia();
        this.tipo = tipo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.diasSemana = diasSemana;
        this.ubicacion = ubicacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.horaInicio = horaInicio;
        this.horasDuracionEvento = horasDuracionEvento;
        this.maestro = maestro;
    }
     
     

    /**
     * Constructor para eventos que van a durar mas de un dia. Aplica para eventos de 
     * tipo semanal o unico de varios dias
     * @param color
     * @param tipo
     * @param nombre
     * @param descripcion
     * @param diasSemana
     * @param ubicacion
     * @param fechaInicio
     * @param fechaFin
     * @param horaInicio
     * @param horasDuracionEvento 
     */
    public EntidadEvento(String color, EntidadTipoEventoEnum tipo, String nombre, String descripcion,String diasSemana, 
            String ubicacion, Calendar fechaInicio, Calendar fechaFin, Calendar horaInicio, float horasDuracionEvento) {
        this.operacionesPers = new OperacionesPersistencia();
        this.color=color;
        this.tipo = tipo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.diasSemana = diasSemana;
        this.ubicacion = ubicacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.horaInicio = horaInicio;
        this.horasDuracionEvento = horasDuracionEvento;
    }

    /**
     * Constructor para eventos de un solo dia.Calcula la fecha fin del evento tomando la fecha inicio
 como base y a esta se le suman las horas que dura el evento
     * @param color
     * @param nombre
     * @param descripcion
     * @param fechaInicio
     * @param ubicacion
     * @param horaInicio
     * @param horasDuracionEvento 
     */
    public EntidadEvento(String color, String nombre, String descripcion, Calendar fechaInicio, String ubicacion,
            Calendar horaInicio, float horasDuracionEvento) {
        this.operacionesPers = new OperacionesPersistencia();
        this.color=color;
        this.tipo = EntidadTipoEventoEnum.UNICO_UN_DIA;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.ubicacion = ubicacion;
        this.horaInicio = horaInicio;
        this.horasDuracionEvento = horasDuracionEvento;
        Calendar fechaCopia=fechaInicio;
        int horas=(int)horasDuracionEvento;
        double copiaHoras=horasDuracionEvento;
        double decimal=copiaHoras-horas;
        int minutos=(int)(decimal*100);
        fechaCopia.add(Calendar.HOUR_OF_DAY, horas);
        fechaCopia.add(Calendar.MINUTE, minutos);
        this.fechaFin=fechaCopia;
    }

    public EntidadTipoEventoEnum getTipo() {
        return tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDiasSemana() {
        return diasSemana;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public Calendar getFechaInicio() {
        return fechaInicio;
    }

    public Calendar getFechaFin() {
        return fechaFin;
    }

    public Calendar getHoraInicio() {
        return horaInicio;
    }

    public float getHorasDuracionEvento() {
        return horasDuracionEvento;
    }

    public EntidadEvento editarEvento(EntidadEvento evento){
        return operacionesPers.editarEvento(evento);
    }
    
    public boolean agregarEvento(EntidadEvento evento){
        return operacionesPers.agregarEvento(evento);
    }
    
    public EntidadEvento obtenerEvento(EntidadEvento evento){
        return operacionesPers.obtenerEvento(evento);
    }
    
    public boolean eliminarEvento(EntidadEvento evento){
        return operacionesPers.eliminarEvento(evento);
    }

    public EntidadMaestro getMaestro() {
        return maestro;
    }

    public void setMaestro(EntidadMaestro maestro) {
        this.maestro = maestro;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    public void setTipoEvento(EntidadTipoEventoEnum tipo){
        this.tipo=tipo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Evento{");
        sb.append("id=").append(id);
        sb.append(", tipo=").append(tipo);
        sb.append(", nombre=").append(nombre);
        sb.append(", descripcion=").append(descripcion);
        sb.append(", diasSemana=").append(diasSemana);
        sb.append(", ubicacion=").append(ubicacion);
        sb.append(", color=").append(color);
        sb.append(", fechaInicio=").append(fechaToString(fechaInicio));
        sb.append(", fechaFin=").append(fechaToString(fechaFin));
        sb.append(", horaInicio=").append(fechaToString(horaInicio));
        sb.append(", horasDuracionEvento=").append(horasDuracionEvento);
        sb.append(", maestro=").append(maestro.getNombre());
        sb.append('}');
        return sb.toString();
    }

    public String fechaToString(Calendar fecha){
        SimpleDateFormat formatoFecha=new SimpleDateFormat("yyyy-MM-dd");
        return formatoFecha.format(fecha.getTime());
    }
    
    private class OperacionesPersistencia{
        private EntityManager em;
        private CriteriaBuilder cb;
        private final static Logger LOG = Logger.getLogger(OperacionesPersistencia.class.getName());
        
        OperacionesPersistencia(){
            em=ClaseConexion.getEntityManager();
            cb=em.getCriteriaBuilder();
        }
        boolean agregarEvento(EntidadEvento evento){
            EntidadMaestro maestro=em.find(EntidadMaestro.class, evento.getMaestro().getIdBd());
            if(maestro!=null){
                try{
                    em.getTransaction().begin();
                    em.persist(evento);
                    em.getTransaction().commit();
                    return true;
                }catch(Exception e){
                    em.getTransaction().rollback();
                    LOG.log(Level.SEVERE, e.getMessage(), e);
                }
            }
            return false;
        }
        
        EntidadEvento editarEvento(EntidadEvento evento){
            EntidadEvento eventoEditado;
            if(em.find(EntidadEvento.class, evento.getId())!=null){
                try{
                    em.getTransaction().begin();
                    eventoEditado=em.merge(evento);
                    em.getTransaction().commit();
                    return eventoEditado;
                }catch(Exception e){
                    em.getTransaction().rollback();
                    LOG.log(Level.SEVERE, e.getMessage(), e);
                }
            }
            return null;
        }
        
        boolean eliminarEvento(EntidadEvento evento){
            EntidadEvento eventoEliminado=em.find(EntidadEvento.class, evento.getId());
            if(eventoEliminado!=null){
                try{
                    em.getTransaction().begin();
                    em.remove(eventoEliminado);
                    em.getTransaction().commit();
                    return true;
                }catch(Exception e){
                    em.getTransaction().rollback();
                    LOG.log(Level.SEVERE, e.getMessage(), e);
                }
            }
            return false;
        }
        
        EntidadEvento obtenerEvento(EntidadEvento evento){
            CriteriaQuery<EntidadEvento> criteria=cb.createQuery(EntidadEvento.class);
            Root<EntidadEvento> root=criteria.from(EntidadEvento.class);
            
            criteria.select(root).
                    where(cb.equal(root.get("nombre"),evento.getNombre()));
            
            TypedQuery<EntidadEvento> query=em.createQuery(criteria);
            EntidadEvento eventoEncontrado;
            try{
                eventoEncontrado=query.getSingleResult();
                return eventoEncontrado;
            }catch(Exception e){
                LOG.log(Level.SEVERE, e.getMessage(), e);
                return null;
            }
        }
    }
}
