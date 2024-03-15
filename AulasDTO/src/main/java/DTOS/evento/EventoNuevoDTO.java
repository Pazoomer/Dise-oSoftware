
package DTOS.evento;

import java.awt.Color;
import java.util.Calendar;

/**
 *
 * @author t1pas
 */
public class EventoNuevoDTO {
    private final String tipo;
    private final String nombre;
    private final String descripcion;
    private final Color color;
    private final String ubicacion;
    private Calendar fechaUnica;
    private Calendar fechaSemanal;

    public EventoNuevoDTO(String tipo, String nombre, String descripcion, Color color, String ubicacion, Calendar fecha) {
        if (tipo.equalsIgnoreCase("semanal")) {
            this.fechaSemanal=fecha;
        }else if (tipo.equalsIgnoreCase("unico")) {
            this.fechaUnica = fecha;
        }
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.color = color;
        this.ubicacion = ubicacion; 
        this.nombre=nombre;
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

    public Calendar getFechaUnica() {
        return fechaUnica;
    }

    public Calendar getFechaSemanal() {
        return fechaSemanal;
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
        sb.append(", fechaUnica=").append(fechaUnica);
        sb.append(", fechaSemanal=").append(fechaSemanal);
        sb.append('}');
        return sb.toString();
    }

}
