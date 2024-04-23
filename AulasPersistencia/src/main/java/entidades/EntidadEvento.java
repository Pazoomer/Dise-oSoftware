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

    private EntidadUbicacion ubicacion;

    private String color;

    private Calendar fechaInicio;

    private Calendar fechaFin;

    private Calendar horaInicio;

    private Double horasDuracionEvento;

    private EntidadMaestro maestro;

    public EntidadEvento() {
    }


    public EntidadEvento(EntidadTipoEventoEnum tipo, String nombre, String descripcion, String diasSemana, EntidadUbicacion ubicacion, String color, Calendar fechaInicio, Calendar fechaFin, Calendar horaInicio, Double horasDuracionEvento, EntidadMaestro maestro) {
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
        this.maestro = maestro;
    }

    public EntidadEvento(String nombre, String descripcion, EntidadUbicacion ubicacion, String color, Calendar fechaInicio, Calendar horaInicio, Double horasDuracionEvento, EntidadMaestro maestro) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.color = color;
        this.fechaInicio = fechaInicio;
        this.horaInicio = horaInicio;
        this.horasDuracionEvento = horasDuracionEvento;
        this.maestro = maestro;
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

    public EntidadUbicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(EntidadUbicacion ubicacion) {
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

    public EntidadMaestro getMaestro() {
        return maestro;
    }

    public void setMaestro(EntidadMaestro maestro) {
        this.maestro = maestro;
    }
    
    /**
     * Obtienes el valor de ObjectId como long
     * @return 
     */
    public Long getIdLong(){
        // Obtener el valor hexadecimal del ObjectId
        String valorHex = this.id.toHexString();
        
        // Convertir el valor hexadecimal a un número
        return Long.valueOf(valorHex);
    }

    /**
     * Recibe un long que convierta a ObjectId para colocarselo como atributo
     * @param id 
     */
    public void setIdLong(Long id) {

        // Convertir el valor long a su representación hexadecimal
        String hexValue = Long.toHexString(id);

        // Rellenar con ceros a la izquierda para asegurar que tenga 24 dígitos
        while (hexValue.length() < 24) {
            hexValue = "0" + hexValue;
        }

        this.id = new ObjectId(hexValue);
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
        sb.append(", maestro=").append(maestro);
        sb.append('}');
        return sb.toString();
    }

 
    
    public String fechaToString(Calendar fecha){
        SimpleDateFormat formatoFecha=new SimpleDateFormat("yyyy-MM-dd");
        return formatoFecha.format(fecha.getTime());
    }
}
