
package objetosNegocio;

import DTOS.evento.TipoEventoEnumDTO;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
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

/**
 *
 * @author t1pas
 */
@Entity
@Table(name = "evento")
public class Evento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="evento_id")
    private Long id;
    
    @Column(name="tipo",nullable=true)
    @Enumerated(EnumType.STRING)
    private TipoEventoEnumDTO tipo;
    
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
    private Maestro maestro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
     public Evento() {

    }

    public Evento(TipoEventoEnumDTO tipo, String nombre, String descripcion, String diasSemana, String ubicacion, String color, Calendar fechaInicio, Calendar fechaFin, Calendar horaInicio, float horasDuracionEvento, Maestro maestro) {
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
     
     

    public Evento(TipoEventoEnumDTO tipo, String nombre, String descripcion, String diasSemana, String ubicacion, String color, Calendar fechaInicio, Calendar fechaFin, Calendar horaInicio, float horasDuracionEvento) {
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
     
     

    public Evento(TipoEventoEnumDTO tipo, String nombre, String descripcion, String diasSemana, String ubicacion, Calendar fechaInicio, Calendar fechaFin, Calendar horaInicio, float horasDuracionEvento, Maestro maestro) {
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
    public Evento(String color, TipoEventoEnumDTO tipo, String nombre, String descripcion,String diasSemana, 
            String ubicacion, Calendar fechaInicio, Calendar fechaFin, Calendar horaInicio, float horasDuracionEvento) {
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
    public Evento(String color, String nombre, String descripcion, Calendar fechaInicio, String ubicacion,
            Calendar horaInicio, float horasDuracionEvento) {
        this.color=color;
        this.tipo = TipoEventoEnumDTO.UNICO_UN_DIA;
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

    public TipoEventoEnumDTO getTipo() {
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

    public EventoA editarEvento(EventoA evento){
        return evento;
    }
    
    public boolean agregarEvento(EventoA evento){
        return true;
    }
    
    public EventoA obtenerEvento(EventoA evento){
        return evento;
    }
    
    public boolean eliminarEvento(EventoA evento){
        return true;
    }

    public Maestro getMaestro() {
        return maestro;
    }

    public void setMaestro(Maestro maestro) {
        this.maestro = maestro;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
        sb.append(", fechaInicio=").append(fechaInicio);
        sb.append(", fechaFin=").append(fechaFin);
        sb.append(", horaInicio=").append(horaInicio);
        sb.append(", horasDuracionEvento=").append(horasDuracionEvento);
        sb.append(", maestro=").append(maestro);
        sb.append('}');
        return sb.toString();
    }
    
    

    
    

    
}
