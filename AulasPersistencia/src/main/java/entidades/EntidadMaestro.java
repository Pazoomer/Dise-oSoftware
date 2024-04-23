package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

/**
 *
 * @author luiis
 */
public class EntidadMaestro implements Serializable {

    @BsonId
    private ObjectId id;

    private Long idMaestro; //Representa el identificador fuera de la base de datos

    private String nombre;
    
    private EntidadUbicacion cubiculo;

    private String descripcion;

    private String foto;

    private List<EntidadEvento> calendario;

    public EntidadMaestro() {
        this.calendario = new ArrayList<>();
    }

    public EntidadMaestro(ObjectId id) {
        this.id = id;
    }

    public EntidadMaestro(Long idMaestro, String nombre, EntidadUbicacion cubiculo, String descripcion, String foto, List<EntidadEvento> calendario) {
        this.idMaestro = idMaestro;
        this.nombre = nombre;
        this.cubiculo = cubiculo;
        this.descripcion = descripcion;
        this.foto = foto;
        this.calendario = calendario;
    }

    public EntidadMaestro(Long idMaestro, String nombre, EntidadUbicacion cubiculo, String descripcion, String foto) {
        this.idMaestro = idMaestro;
        this.nombre = nombre;
        this.cubiculo = cubiculo;
        this.descripcion = descripcion;
        this.foto = foto;
        this.calendario = new ArrayList<>();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Long getIdMaestro() {
        return idMaestro;
    }

    public void setIdMaestro(Long idMaestro) {
        this.idMaestro = idMaestro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EntidadUbicacion getCubiculo() {
        return cubiculo;
    }

    public void setCubiculo(EntidadUbicacion cubiculo) {
        this.cubiculo = cubiculo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public List<EntidadEvento> getCalendario() {
        return calendario;
    }

    public void setCalendario(List<EntidadEvento> calendario) {
        this.calendario = calendario;
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
        sb.append("EntidadMaestro{");
        sb.append("id=").append(id);
        sb.append(", idMaestro=").append(idMaestro);
        sb.append(", nombre=").append(nombre);
        sb.append(", cubiculo[");
        sb.append("edificio=").append(cubiculo.getIdentificador());
        sb.append(", campus=").append(cubiculo.getCampus().getNombre()).append(']');
        sb.append(", descripcion=").append(descripcion);
        sb.append(", foto=").append(foto);
        if (!calendario.isEmpty()) {
            sb.append(", calendario=").append(calendarioToString());
        }
        sb.append('}');
        return sb.toString();
    }

    public String calendarioToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Eventos[");
        for (EntidadEvento ev : calendario) {
            sb.append("{nombre=").append(ev.getNombre());
            sb.append("ubicacion=").append(ev.getUbicacion());
            sb.append("fecha inicio=").append(ev.getFechaInicio());
            sb.append("hora inicio=").append(ev.getHoraInicio()).append("}");
        }
        sb.append("]");
        return sb.toString();
    }
}
