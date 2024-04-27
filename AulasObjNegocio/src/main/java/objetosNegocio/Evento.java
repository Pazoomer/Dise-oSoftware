
package objetosNegocio;

import java.util.Calendar;
import java.text.SimpleDateFormat;

/**
 *
 * @author luiis
 */
public class Evento {
    private TipoEventoEnum tipo;
    private String nombre;
    private String descripcion;
    private String diasSemana;
    private Ubicacion ubicacion;
    private Calendar fechaInicio;
    private Calendar fechaFin;
    private Calendar horaInicio;
    private float horasDuracionEvento;

    public Evento() {
    }

    public Evento(TipoEventoEnum tipo, String nombre, String descripcion, String diasSemana, 
            Ubicacion ubicacion, Calendar fechaInicio, Calendar fechaFin, Calendar horaInicio, float horasDuracionEvento) {
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

    public Evento(String nombre, String descripcion, Ubicacion ubicacion, Calendar fechaInicio,
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

//    public boolean agregarEvento(EventoConsultableDTO evento)throws NegocioException{
//        EntidadEvento eventoNuevo=convertidor.toEventoBO(evento);
//        try{
//            return crudEvento.agregarEvento(eventoNuevo);
//        }catch(PersistenciaExceptionn e){
//            throw new NegocioException(e.getMessage());
//        }
//    }
//    
//    public EventoConsultableDTO editarEvento(EventoConsultableDTO evento)throws NegocioException{
//        EntidadEvento eventoAEditar=convertidor.toEventoBO(evento);
//        try{
//            return convertidor.toEventoDTO(crudEvento.editarEvento(eventoAEditar),null);
//        }catch(PersistenciaExceptionn e){
//            throw new NegocioException(e.getMessage());
//        }
//    }
//    
//    public boolean eliminarEvento(EventoConsultableDTO evento)throws NegocioException{
//        EntidadEvento eventoEliminado=convertidor.toEventoBO(evento);
//        try{
//            return crudEvento.eliminarEvento(eventoEliminado);
//        }catch(PersistenciaExceptionn e){
//            throw new NegocioException(e.getMessage());
//        }
//    }
//    
//    public EventoConsultableDTO obtenerEvento(EventoConsultableDTO evento)throws NegocioException{
//        EntidadEvento eventoBuscado=convertidor.toEventoBO(evento);
//        try{
//            return convertidor.toEventoDTO(crudEvento.obtenerEvento(eventoBuscado),null);
//        }catch(PersistenciaExceptionn e){
//            throw new NegocioException(e.getMessage());
//        }
//    }
    
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

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
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
        sb.append("Eventos{");
        sb.append("tipo=").append(tipo.toString());
        sb.append(", nombre=").append(nombre);
        sb.append(", ubicacion[").append(ubicacion.toStringReducido()).append(']');
        switch(tipo){
            case UNICO_UN_DIA -> sb.append(", fecha inicio=").append(fechaToString(fechaInicio));
            case UNICO_VARIOS_DIAS -> {
                sb.append(", fecha inicio=").append(fechaToString(fechaInicio));
                sb.append(", fecha fin=").append(fechaToString(fechaFin));
            }
            case SEMANAL -> sb.append(", dias semana=").append(diasSemana);
        }
        sb.append(", hora inicio=").append(fechaToString(horaInicio));
        sb.append(", hrs duracion=").append(horasDuracionEvento);
        sb.append('}');
        return sb.toString();
    }
    
    public String toStringReducido(){
        StringBuilder sb = new StringBuilder();
        sb.append("nombre=").append(nombre);
        switch(tipo){
            case UNICO_UN_DIA -> sb.append(", fecha inicio=").append(fechaToString(fechaInicio));
            case UNICO_VARIOS_DIAS -> {
                sb.append(", fecha inicio=").append(fechaToString(fechaInicio));
                sb.append(", fecha fin=").append(fechaToString(fechaFin));
            }
            case SEMANAL -> sb.append(", dias semana=").append(diasSemana);
        }
        sb.append(", hora inicio=").append(fechaToString(horaInicio));
        return sb.toString();
    }
    
    public String fechaToString(Calendar fecha){
        SimpleDateFormat formatoFecha=new SimpleDateFormat("yyyy-MM-dd");
        return formatoFecha.format(fecha.getTime());
    }
}
