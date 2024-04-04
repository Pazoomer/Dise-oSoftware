
package objetosNegocio;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author luiis
 */
public class EventoA {
    private TipoEventoEnum tipo;
    private String nombre;
    private String descripcion;
    private List<Integer> diasSemana;
    private String ubicacion;
    private Calendar fechaInicio;
    private Calendar fechaFin;
    private LocalTime horaInicio;
    private float horasDuracionEvento;

    public EventoA() {
        this.diasSemana=new ArrayList<>();
    }

    /**
     * Constructor para eventos que van a durar mas de un dia. Aplica para eventos de 
     * tipo semanal o unico de varios dias
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
    public EventoA(TipoEventoEnum tipo, String nombre, String descripcion, List<Integer> diasSemana, 
            String ubicacion, Calendar fechaInicio, Calendar fechaFin, LocalTime horaInicio, float horasDuracionEvento) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.diasSemana = diasSemana;
        this.ubicacion = ubicacion;
        this.fechaInicio = fechaInicio;
        this.fechaInicio.set(Calendar.HOUR_OF_DAY, horaInicio.getHour());
        this.fechaInicio.set(Calendar.MINUTE, horaInicio.getMinute());
        this.fechaFin = fechaFin;
        this.horaInicio = horaInicio;
        this.horasDuracionEvento = horasDuracionEvento;
    }

    /**
     * Constructor para eventos de un solo dia.Calcula la fecha fin del evento tomando la fecha inicio
 como base y a esta se le suman las horas que dura el evento
     * @param nombre
     * @param descripcion
     * @param fechaInicio
     * @param ubicacion
     * @param horaInicio
     * @param horasDuracionEvento 
     */
    public EventoA(String nombre, String descripcion, Calendar fechaInicio, String ubicacion,
            LocalTime horaInicio, float horasDuracionEvento) {
        this.tipo = TipoEventoEnum.UNICO_UN_DIA;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.ubicacion = ubicacion;
        this.fechaInicio.set(Calendar.HOUR_OF_DAY, horaInicio.getHour());
        this.fechaInicio.set(Calendar.MINUTE, horaInicio.getMinute());
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

    public TipoEventoEnum getTipo() {
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
