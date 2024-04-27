package entidades;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

public class EntidadEvento implements Serializable {
    @BsonId
    private ObjectId id;

    private EntidadTipoEventoEnum tipo;

    private String nombre;

    private String descripcion;

    private String diasSemana;

    private String ubicacion;

    private String color;

    private Calendar fechaInicio;

    private Calendar fechaFin;

    private Calendar horaInicio;

    private Double horasDuracionEvento;

    private String maestro;

    public EntidadEvento() {
    }


    public EntidadEvento(EntidadTipoEventoEnum tipo, String nombre, String descripcion, String diasSemana, String ubicacion, String color, Calendar fechaInicio, Calendar fechaFin, Calendar horaInicio, Double horasDuracionEvento, String maestro) {
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
        //this.maestro = maestro;
    }

    public EntidadEvento(String nombre, String descripcion, String ubicacion, String color, Calendar fechaInicio, Calendar horaInicio, Double horasDuracionEvento, String maestro) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.color = color;
        this.fechaInicio = fechaInicio;
        this.horaInicio = horaInicio;
        this.horasDuracionEvento = horasDuracionEvento;
        //this.maestro = maestro;
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
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
    public String getIdConversion() {
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
    public void setIdConversion(String id) {
        this.id = new ObjectId(id);
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
        sb.append(", fechaInicio=").append(fechaInicio);
        sb.append(", fechaFin=").append(fechaFin);
        sb.append(", horaInicio=").append(horaInicio);
        sb.append(", horasDuracionEvento=").append(horasDuracionEvento);
        //sb.append(", maestro=").append(maestro);
        sb.append('}');
        return sb.toString();
    }

 
    
    public String fechaToString(Calendar fecha){
        SimpleDateFormat formatoFecha=new SimpleDateFormat("yyyy-MM-dd");
        return formatoFecha.format(fecha.getTime());
    }
}
