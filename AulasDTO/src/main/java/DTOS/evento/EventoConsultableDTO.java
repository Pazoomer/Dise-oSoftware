
package DTOS.evento;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;

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
    private final List<Integer> diasSemana2;
    private final String ubicacion;
    private Calendar fechaInicio;
    private Calendar fechaFin;
    private final LocalTime horaInicio;
    private final float horasDuracionEvento;

    
    public EventoConsultableDTO(String tipo, String nombre, String descripcion, Color color, List<Integer> diasSemanaa, 
            String ubicacion, Calendar fechaInicio, LocalTime horaInicio, float horasDuracionEvento) {
        this.fechaInicio = fechaInicio;
        this.fechaFin=Calendar.getInstance();
        this.horaInicio=horaInicio;
        //las horas se almacenan en un float para que permita guardar eventos
        //de hora y media por ejemplo
        this.horasDuracionEvento=horasDuracionEvento;
        //esto separa la parte entera y la decimal del float, almacenandolas
        //en variables representando las horas y minutos. 
        //Por ejemplo si horasDuracionEvento guarda 2.5 (2 horas y media),
        //horas guarda el 2 y minutos guarda 30, para agregarlos por separado a la fecha
        int horas=(int)horasDuracionEvento;
        double copiaHoras=horasDuracionEvento;
        double decimal=copiaHoras-horas;
        int minutos=(int)(decimal*100);
        //
        if (tipo.equalsIgnoreCase("unico")) {
            this.fechaFin=fechaInicio;
            this.fechaFin.add(Calendar.HOUR, horas);
            this.fechaFin.add(Calendar.MINUTE, minutos);
        } else if (tipo.equalsIgnoreCase("semanal")) {
            this.fechaFin.set(2024, Calendar.MAY, 11);
        }
        this.tipo = tipo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.color = color;
        this.ubicacion = ubicacion;
        this.diasSemana2 = diasSemanaa;
        this.diasSemana=null;
    }

    public EventoConsultableDTO(String tipo, String nombre, String descripcion, Color color, boolean[] diasSemanaaa, 
            String ubicacion, Calendar fechaInicio, Calendar fechaFin) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.color = color;
        this.diasSemana = diasSemanaaa;
        this.diasSemana2=null;
        this.ubicacion = ubicacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.horaInicio=null;
        this.horasDuracionEvento=0.0f;
    }
    
    
    
    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public float getHorasDuracionEvento() {
        return horasDuracionEvento;
    }
    
    public boolean[] getDiasSemana() {
        return diasSemana;
    }

    public List<Integer> getDiasSemana2() {
        return diasSemana2;
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
        sb.append(", fechaInicio=").append(fechaToString(fechaInicio));
        sb.append(", fechaFin=").append(fechaToString(fechaFin));
        sb.append('}');
        return sb.toString();
    }
    
     /**
     * metodo para convertir la fecha de tipo GregorianCalendar a 
     * un string con formato yyyy-mm-dd
     * @param fecha
     * @return la fecha de la instancia con el formato
     */
    public String fechaToString(Calendar fecha){
        SimpleDateFormat formatoFecha=new SimpleDateFormat("yyyy-MM-dd");
        return formatoFecha.format(fecha.getTime());
    }
}
