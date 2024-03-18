
package DTOS.evento;

import java.awt.Color;
import java.util.Calendar;

/**
 *
 * @author t1pas
 */
public class EventoConsultableDTO {
    private final String tipo;
    private final String nombre;
    private final String descripcion;
    private final Color color;
    private final boolean[] diasSemana;
    private final String ubicacion;
    private final Calendar fechaInicio;
    private final Calendar fechaFin;
    
    public EventoConsultableDTO(String tipo, String nombre, String descripcion, Color color, boolean[] diasSemana, String ubicacion, Calendar fechaInicio) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaInicio;
        if (tipo.equalsIgnoreCase("unico")) {

            this.fechaFin.add(Calendar.HOUR, 1);
        } else if (tipo.equalsIgnoreCase("semanal")) {

            this.fechaFin.add(Calendar.MONTH, 6);
        }
        this.tipo = tipo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.color = color;
        this.ubicacion = ubicacion;
        this.diasSemana = diasSemana;
    }

    public boolean[] getDiasSemana() {
        return diasSemana;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Color getColor() {
        return color;
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

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EventoNuevoDTO{");
        sb.append("tipo=").append(tipo);
        sb.append(", nombre=").append(nombre);
        sb.append(", descripcion=").append(descripcion);
        sb.append(", color=").append(color);
        sb.append(", ubicacion=").append(ubicacion);
        sb.append(", fechaInicio=").append(fechaInicio);
        sb.append(", fechaFin=").append(fechaFin);
        sb.append('}');
        return sb.toString();
    }
}
