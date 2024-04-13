/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import DTOS.evento.EventoConsultableDTO;
import java.util.Calendar;
import entidades.EntidadEvento;

/**
 *
 * @author luiis
 */
public class Evento {
    private TipoEventoEnum tipo;
    private String nombre;
    private String descripcion;
    private String diasSemana;
    private String ubicacion;
    private Calendar fechaInicio;
    private Calendar fechaFin;
    private Calendar horaInicio;
    private float horasDuracionEvento;
    private EntidadEvento eventoPersistencia;
    private Conversiones convertidor;

    public Evento() {
    }

    public Evento(TipoEventoEnum tipo, String nombre, String descripcion, String diasSemana, 
            String ubicacion, Calendar fechaInicio, Calendar fechaFin, Calendar horaInicio, float horasDuracionEvento) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.diasSemana = diasSemana;
        this.ubicacion = ubicacion;
        this.fechaInicio = fechaInicio;
        this.fechaInicio.set(Calendar.HOUR_OF_DAY, horaInicio.get(Calendar.HOUR_OF_DAY));
        this.fechaInicio.set(Calendar.MINUTE, horaInicio.get(Calendar.MINUTE));
        this.fechaFin = fechaFin;
        this.horaInicio = horaInicio;
        this.horasDuracionEvento = horasDuracionEvento;
    }

    public Evento(String nombre, String descripcion, String ubicacion, Calendar fechaInicio,
            Calendar horaInicio, float horasDuracionEvento) {
        this.tipo=TipoEventoEnum.UNICO_UN_DIA;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.fechaInicio = fechaInicio;
        this.fechaInicio.set(Calendar.HOUR_OF_DAY, horaInicio.get(Calendar.HOUR_OF_DAY));
        this.fechaInicio.set(Calendar.MINUTE, horaInicio.get(Calendar.MINUTE));
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

    public boolean agregarEvento(EventoConsultableDTO evento){
        EntidadEvento eventoNuevo=convertidor.toEventoBO(evento);
        return eventoPersistencia.agregarEvento(eventoNuevo);
    }
    
    public EventoConsultableDTO editarEvento(EventoConsultableDTO evento){
        EntidadEvento eventoAEditar=convertidor.toEventoBO(evento);
        return convertidor.toEventoDTO(eventoPersistencia.editarEvento(eventoAEditar));
    }
    
    public boolean eliminarEvento(EventoConsultableDTO evento){
        EntidadEvento eventoEliminado=convertidor.toEventoBO(evento);
        return eventoPersistencia.eliminarEvento(eventoEliminado);
    }
    
    public EventoConsultableDTO obtenerEvento(EventoConsultableDTO evento){
        EntidadEvento eventoBuscado=convertidor.toEventoBO(evento);
        return convertidor.toEventoDTO(eventoPersistencia.obtenerEvento(eventoBuscado));
    }
    
    
    public TipoEventoEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoEventoEnum tipo) {
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Evento{");
        sb.append("tipo=").append(tipo);
        sb.append(", nombre=").append(nombre);
        sb.append(", descripcion=").append(descripcion);
        sb.append(", diasSemana=").append(diasSemana);
        sb.append(", ubicacion=").append(ubicacion);
        sb.append(", fechaInicio=").append(fechaInicio);
        sb.append(", fechaFin=").append(fechaFin);
        sb.append(", horaInicio=").append(horaInicio);
        sb.append(", horasDuracionEvento=").append(horasDuracionEvento);
        sb.append('}');
        return sb.toString();
    }
    
    
}
