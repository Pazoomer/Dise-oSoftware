package entidades;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

public class EntidadEvento implements Serializable {
    @BsonId
    private ObjectId id;

    private EntidadTipoEventoEnum tipo;

    private String nombre;

    private String descripcion;

    private String diasSemana;

    private ObjectId ubicacion;
    
    private ObjectId idCampus;

    private String color;

    private Date fechaInicio;

    private Date fechaFin;

    private Date horaInicio;

    private Double horasDuracionEvento;

    private String maestro;

    public EntidadEvento() {
    }
    
    public EntidadEvento(EntidadTipoEventoEnum tipo, String nombre, String descripcion, String diasSemana, String color, Date fechaInicio, Date fechaFin, Date horaInicio, Double horasDuracionEvento, String maestro) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.diasSemana = diasSemana;
        this.color = color;
        Calendar fechaInicioCalculada=Calendar.getInstance();
       // fechaInicioCalculada.setTimeZone(TimeZone.getTimeZone("UTC"));
        fechaInicioCalculada.setTime(fechaInicio);
        fechaInicioCalculada.add(Calendar.MONTH, -1);
        this.fechaInicio = fechaInicioCalculada.getTime();
        Calendar fechaFinCalculada=Calendar.getInstance();
       // fechaFinCalculada.setTimeZone(TimeZone.getTimeZone("UTC"));
        fechaFinCalculada.setTime(fechaFin);
        fechaFinCalculada.add(Calendar.MONTH, -1);
        this.fechaFin = fechaFinCalculada.getTime();
        this.horaInicio = horaInicio;
        this.horasDuracionEvento = horasDuracionEvento;
        //this.maestro = maestro;
    }


    public EntidadEvento(EntidadTipoEventoEnum tipo, String nombre, String descripcion, String diasSemana, ObjectId ubicacion, String color, Date fechaInicio, Date fechaFin, Date horaInicio, Double horasDuracionEvento, String maestro) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.diasSemana = diasSemana;
        this.ubicacion = ubicacion;
        this.color = color;
        Calendar fechaInicioCalculada=Calendar.getInstance();
       // fechaInicioCalculada.setTimeZone(TimeZone.getTimeZone("UTC"));
        fechaInicioCalculada.setTime(fechaInicio);
        fechaInicioCalculada.add(Calendar.MONTH, -1);
        this.fechaInicio = fechaInicioCalculada.getTime();
        Calendar fechaFinCalculada=Calendar.getInstance();
       // fechaFinCalculada.setTimeZone(TimeZone.getTimeZone("UTC"));
        fechaFinCalculada.setTime(fechaFin);
        fechaFinCalculada.add(Calendar.MONTH, -1);
        this.fechaFin = fechaFinCalculada.getTime();
        this.horaInicio = horaInicio;
        this.horasDuracionEvento = horasDuracionEvento;
        //this.maestro = maestro;
    }

    public EntidadEvento(String nombre, String descripcion, ObjectId ubicacion, String color, Date fechaInicio, Date horaInicio, Double horasDuracionEvento, String maestro) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.color = color;
        Calendar fechaInicioCalculada=Calendar.getInstance();
        //fechaInicioCalculada.setTimeZone(TimeZone.getTimeZone("UTC"));
        fechaInicioCalculada.setTime(fechaInicio);
        fechaInicioCalculada.add(Calendar.MONTH, -1);
        this.fechaInicio = fechaInicioCalculada.getTime();
        this.horaInicio = fechaInicioCalculada.getTime();
        this.fechaFin=fechaInicioCalculada.getTime();
        Calendar fechaFinCalculada=Calendar.getInstance();
        fechaFinCalculada.setTime(this.horaInicio);
        fechaFinCalculada.add(Calendar.HOUR, horasDuracionEvento.intValue());
        this.horasDuracionEvento = horasDuracionEvento;
        //this.maestro = maestro;
    }
    
    public EntidadEvento(String nombre, String descripcion, String color, Date fechaInicio, Date horaInicio, Double horasDuracionEvento, String maestro) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.color = color;
        Calendar fechaInicioCalculada=Calendar.getInstance();
        //fechaInicioCalculada.setTimeZone(TimeZone.getTimeZone("UTC"));
        fechaInicioCalculada.setTime(fechaInicio);
        fechaInicioCalculada.add(Calendar.MONTH, -1);
        this.fechaInicio = fechaInicioCalculada.getTime();
        this.horaInicio = fechaInicioCalculada.getTime();
        this.fechaFin=fechaInicioCalculada.getTime();
        Calendar fechaFinCalculada=Calendar.getInstance();
        fechaFinCalculada.setTime(this.horaInicio);
        fechaFinCalculada.add(Calendar.HOUR, horasDuracionEvento.intValue());
        this.horasDuracionEvento = horasDuracionEvento;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
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

    public ObjectId getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(ObjectId ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        Calendar fechaInicioCalculada=Calendar.getInstance();
        //fechaInicioCalculada.setTimeZone(TimeZone.getTimeZone("UTC"));
        fechaInicioCalculada.setTime(fechaInicio);
        fechaInicioCalculada.add(Calendar.MONTH, -1);
        this.fechaInicio = fechaInicioCalculada.getTime();
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        Calendar fechaFinCalculada=Calendar.getInstance();
        //fechaFinCalculada.setTimeZone(TimeZone.getTimeZone("UTC"));
        fechaFinCalculada.setTime(fechaFin);
        fechaFinCalculada.add(Calendar.MONTH, -1);
        this.fechaFin = fechaFinCalculada.getTime();
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        Calendar horaInicioCalculada=Calendar.getInstance();
        //horaInicioCalculada.setTimeZone(TimeZone.getTimeZone("UTC"));
        horaInicioCalculada.setTime(horaInicio);
        horaInicioCalculada.add(Calendar.MONTH, -1);
        this.horaInicio = horaInicioCalculada.getTime();
    }

    public Double getHorasDuracionEvento() {
        return horasDuracionEvento;
    }

    public void setHorasDuracionEvento(Double horasDuracionEvento) {
        this.horasDuracionEvento = horasDuracionEvento;
    }

    public String getMaestro() {
        return maestro;
    }

    public void setMaestro(String maestro) {
        this.maestro = maestro;
    }
    
    /**
     * Obtienes el valor de ObjectId como string
     *
     * @return
     */
    public String ggetIdConversion() {
        // Obtener el valor hexadecimal del ObjectId
        if (this.id == null) {
            return null;
        }
        
        return this.id.toString();
    }

    /**
     * Recibe un String que convierta a ObjectId para colocarselo como atributo
     * @param id 
     */
    public void ssetIdConversion(String id) {
       if (id != null) {
            this.id = new ObjectId(id);
        }
    }
    
    /**
     * Obtienes el valor de la ubicacion como string
     *
     * @return
     */
    public String ggetUbicacionConversion() {
        // Obtener el valor hexadecimal del ObjectId
        if (this.ubicacion == null) {
            return null;
        }
        
        return this.ubicacion.toString();
    }

    /**
     * Recibe una ubicacion que convierta a ObjectId para colocarselo como atributo
     * @param ubicacion 
     */
    public void ssetUbicacionConversion(String ubicacion) {
       if (ubicacion != null) {
            this.ubicacion = new ObjectId(ubicacion);
        }
    }
    
    public String ggetCampusConversion() {
        // Obtener el valor hexadecimal del ObjectId
        if (this.idCampus == null) {
            return null;
        }
        
        return this.idCampus.toString();
    }

    /**
     * Recibe una ubicacion que convierta a ObjectId para colocarselo como atributo 
     * @param idCampus
     */
    public void ssetCampusConversion(String idCampus) {
       if (idCampus != null) {
            this.idCampus = new ObjectId(idCampus);
        }
    }

    public ObjectId getIdCampus() {
        return idCampus;
    }

    public void setIdCampus(ObjectId idCampus) {
        this.idCampus = idCampus;
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
        sb.append(", ubicacion=").append(ubicacion);
        sb.append(", color=").append(color);
        sb.append(", fechaInicio=").append(fechaToString(fechaInicio));
        sb.append(", fechaFin=").append(fechaToString(fechaFin));
        Calendar hora=Calendar.getInstance();
        hora.setTime(horaInicio);
        hora.setTimeZone(TimeZone.getTimeZone("America/Arizona"));
        sb.append(", horaInicio=").append(hora.get(Calendar.HOUR_OF_DAY)+":"+hora.get(Calendar.MINUTE));
        sb.append(", horasDuracionEvento=").append(horasDuracionEvento);
        sb.append(", maestro=").append(maestro);
        sb.append('}');
        return sb.toString();
    }

    

    public String fechaToString(Date fecha){
        SimpleDateFormat formatoFecha=new SimpleDateFormat("yyyy-MM-dd");
        formatoFecha.setTimeZone(TimeZone.getTimeZone("America/Arizona"));
        return formatoFecha.format(fecha);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final EntidadEvento other = (EntidadEvento) obj;
        return Objects.equals(this.id, other.id);
    }
    
    
}
