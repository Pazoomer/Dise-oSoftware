package DTOS.evento;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author t1pas, luisa
 */
public class EventoConsultableDTO {

    private TipoEventoEnumDTO tipo;
    private final String nombre;
    private final String descripcion;
    private final String color;
    private String diasSemana;
    private final String ubicacion;
    private Calendar fechaInicio;
    private Calendar fechaFin;
    private final Calendar horaInicio;
    private final float horasDuracionEvento;

    /**
     * Constructor para eventos de un solo dia. Calcula la fecha fin del evento
     * tomando la fecha inicio como base y a esta se le suman las horas que dura
     * el evento
     *
     * @param nombre
     * @param descripcion
     * @param color
     * @param ubicacion
     * @param fechaInicio
     * @param horaInicio
     * @param horasDuracionEvento
     */
    public EventoConsultableDTO(String nombre, String descripcion, String color,
            String ubicacion, Calendar fechaInicio, Calendar horaInicio, float horasDuracionEvento) {
        this.tipo = TipoEventoEnumDTO.UNICO_UN_DIA;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.color = color;
        this.ubicacion = ubicacion;
        this.fechaInicio = fechaInicio;
        this.fechaInicio.set(Calendar.HOUR_OF_DAY, horaInicio.get(Calendar.HOUR));
        this.fechaInicio.set(Calendar.MINUTE, horaInicio.get(Calendar.MINUTE));
        this.horaInicio = horaInicio;
        //las horas se almacenan en un float para que permita guardar eventos
        //de hora y media por ejemplo
        this.horasDuracionEvento = horasDuracionEvento;
        //esto separa la parte entera y la decimal del float, almacenandolas
        //en variables representando las horas y minutos. 
        //Por ejemplo si horasDuracionEvento guarda 2.5 (2 horas y media),
        //horas guarda el 2 y minutos guarda 30, para agregarlos por separado a la fecha
        Calendar fechaCopia = fechaInicio;
        int horas = (int) horasDuracionEvento;
        double copiaHoras = horasDuracionEvento;
        double decimal = copiaHoras - horas;
        int minutos = (int) (decimal * 100);
        fechaCopia.add(Calendar.HOUR_OF_DAY, horas);
        fechaCopia.add(Calendar.MINUTE, minutos);
        this.fechaFin = fechaCopia;
    }

    /**
     * Constructor para eventos que van a durar mas de un dia.Aplica para
     * eventos de tipo semanal o unico de varios dias
     *
     * @param tipo
     * @param nombre
     * @param descripcion
     * @param color
     * @param diasSemanaa
     * @param ubicacion
     * @param fechaInicio
     * @param fechaFin
     * @param horaInicio
     * @param horasDuracionEvento
     */
    public EventoConsultableDTO(TipoEventoEnumDTO tipo, String nombre, String descripcion, String color,String diasSemanaa,
            String ubicacion, Calendar fechaInicio, Calendar fechaFin, Calendar horaInicio, float horasDuracionEvento) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = Calendar.getInstance();
        this.horaInicio = horaInicio;
        this.horasDuracionEvento = horasDuracionEvento;
        if (tipo.equals(TipoEventoEnumDTO.UNICO_VARIOS_DIAS)) {
            this.fechaFin = fechaFin;
        } else if (tipo.equals(TipoEventoEnumDTO.SEMANAL)) {
            this.fechaFin.set(2024, Calendar.MAY, 11);
        }
        this.tipo = tipo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.color = color;
        this.ubicacion = ubicacion;
        this.diasSemana = diasSemanaa;
    }
//    public EventoConsultableDTO(TipoEventoEnumDTO tipo, String nombre, String descripcion, Color color, boolean[] diasSemanaaa, 
//            String ubicacion, Calendar fechaInicio, Calendar fechaFin) {
//        this.tipo = tipo;
//        this.nombre = nombre;
//        this.descripcion = descripcion;
//        this.color = color;
//        this.diasSemana2=null;
//        this.ubicacion = ubicacion;
//        this.fechaInicio = fechaInicio;
//        this.fechaFin = fechaFin;
//        this.horaInicio=null;
//        this.horasDuracionEvento=0.0f;
//    }

    public void setDiasSemana(String diasSemana) {
        this.diasSemana = diasSemana;
    }

    public void setTipo(TipoEventoEnumDTO tipo) {
        this.tipo = tipo;
    }

    public void setFechaFin(Calendar fechaFin) {
        this.fechaFin = fechaFin;
    }

    
    public Calendar getHoraInicio() {
        return horaInicio;
    }

    public float getHorasDuracionEvento() {
        return horasDuracionEvento;
    }

    public String getDiasSemana() {
        return diasSemana;
    }

    public TipoEventoEnumDTO getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getColor() {
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
     * metodo para convertir la fecha de tipo GregorianCalendar a un string con
     * formato yyyy-mm-dd
     *
     * @param fecha
     * @return la fecha de la instancia con el formato
     */
    public String fechaToString(Calendar fecha) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        return formatoFecha.format(fecha.getTime());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.nombre);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EventoConsultableDTO other = (EventoConsultableDTO) obj;
        return Objects.equals(this.nombre, other.nombre);
    }
    
    
}
