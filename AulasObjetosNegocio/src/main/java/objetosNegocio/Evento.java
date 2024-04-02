/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author luiis
 */
public class Evento {
    private String tipo;
    private String nombre;
    private String descripcion;
    private List<Integer> diasSemana;
    private String ubicacion;
    private Calendar fechaInicio;
    private Calendar fechaFin;
    private LocalTime horaInicio;
    private float horasDuracionEvento;

    public Evento() {
        this.diasSemana=new ArrayList<>();
    }

    public Evento(String tipo, String nombre, String descripcion, List<Integer> diasSemana, String ubicacion, Calendar fechaInicio, Calendar fechaFin, LocalTime horaInicio, float horasDuracionEvento) {
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

    public Evento(String tipo, String nombre, String descripcion, Calendar fechaInicio, LocalTime horaInicio, float horasDuracionEvento) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.horaInicio = horaInicio;
        this.horasDuracionEvento = horasDuracionEvento;
        Calendar fechaCopia=fechaInicio;
        fechaCopia.set(Calendar.HOUR_OF_DAY, horaInicio.getHour());
        fechaCopia.add(Calendar.HOUR_OF_DAY, (int)horasDuracionEvento);
        this.fechaFin=fechaCopia;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public List<Integer> getDiasSemana() {
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

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public float getHorasDuracionEvento() {
        return horasDuracionEvento;
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
