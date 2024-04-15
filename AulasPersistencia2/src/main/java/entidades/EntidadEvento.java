/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
 * @author luisa
 */
@Entity
@Table (name = "eventos")
public class EntidadEvento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evento_id")
    private Long id;

    @Column(name = "tipo", nullable = true)
    @Enumerated(EnumType.STRING)
    private EntidadTipoEventoEnum tipo;

    @Column(name = "nombre", nullable = true, length = 100)
    private String nombre;

    @Column(name = "descripcion", nullable = true, length = 200)
    private String descripcion;

    @Column(name = "diasSemana", nullable = true, length = 8)
    private String diasSemana;

    @ManyToOne
    @JoinColumn(name = "id_ubicacion")
    private EntidadUbicacion ubicacion;

    @Column(name = "color", nullable = true, length = 100)
    private String color;

    @Temporal(TemporalType.DATE)
    @Column(name = "fechaInicio", nullable = true)
    private Calendar fechaInicio;

    @Temporal(TemporalType.DATE)
    @Column(name = "fechaFin", nullable = true)
    private Calendar fechaFin;

    @Column(name = "horaInicio", nullable = true)
    @Temporal(TemporalType.TIME)
    private Calendar horaInicio;

    @Column(name = "horasDuracionEvento", nullable = true)
    private float horasDuracionEvento;

    @ManyToOne
    @JoinColumn(name = "id_maestro_bd", nullable = false)
    private EntidadMaestro maestro;

    public EntidadEvento() {
    }

    public EntidadEvento( EntidadTipoEventoEnum tipo, String nombre, String descripcion, String diasSemana, EntidadUbicacion ubicacion, String color, Calendar fechaInicio, Calendar fechaFin, Calendar horaInicio, float horasDuracionEvento, EntidadMaestro maestro) {
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

    public EntidadEvento(String nombre, String descripcion, EntidadUbicacion ubicacion, String color, Calendar fechaInicio, Calendar horaInicio, float horasDuracionEvento, EntidadMaestro maestro) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.color = color;
        this.fechaInicio = fechaInicio;
        this.horaInicio = horaInicio;
        this.horasDuracionEvento = horasDuracionEvento;
        this.maestro = maestro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EntidadTipoEventoEnum getTipo() {
        return tipo;
    }

    public void setTipo(EntidadTipoEventoEnum tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDiasSemana() {
        return diasSemana;
    }

    public void setDiasSemana(String diasSemana) {
        this.diasSemana = diasSemana;
    }

    public EntidadUbicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(EntidadUbicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Calendar getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Calendar fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Calendar getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Calendar fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Calendar getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Calendar horaInicio) {
        this.horaInicio = horaInicio;
    }

    public float getHorasDuracionEvento() {
        return horasDuracionEvento;
    }

    public void setHorasDuracionEvento(float horasDuracionEvento) {
        this.horasDuracionEvento = horasDuracionEvento;
    }

    public EntidadMaestro getMaestro() {
        return maestro;
    }

    public void setMaestro(EntidadMaestro maestro) {
        this.maestro = maestro;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EntidadEvento{");
        sb.append("id=").append(id);
        sb.append(", tipo=").append(tipo);
        sb.append(", nombre=").append(nombre);
        sb.append(", descripcion=").append(descripcion);
        sb.append(", diasSemana=").append(diasSemana);
        sb.append(", ubicacion[");
        sb.append("edificio=").append(ubicacion.getIdentificador());
        sb.append(", campus=").append(ubicacion.getCampus().getNombre()).append(']');
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
    
}
