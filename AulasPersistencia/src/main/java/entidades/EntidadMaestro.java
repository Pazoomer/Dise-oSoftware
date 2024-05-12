package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

/**
 *
 * @author luiis
 */
public class EntidadMaestro implements Serializable {

    @BsonId
    private ObjectId id;

    private String idMaestro; //Representa el identificador fuera de la base de datos

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

    public EntidadMaestro(String idMaestro, String nombre, EntidadUbicacion cubiculo, String descripcion, String foto, List<EntidadEvento> calendario) {
        this.idMaestro = idMaestro;
        this.nombre = nombre;
        this.cubiculo = cubiculo;
        this.descripcion = descripcion;
        this.foto = foto;
        this.calendario = calendario;
    }

    public EntidadMaestro(String idMaestro, String nombre, EntidadUbicacion cubiculo, String descripcion, String foto) {
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

    public String getIdMaestro() {
        return idMaestro;
    }

    public void setIdMaestro(String idMaestro) {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EntidadMaestro{");
        sb.append("id=").append(id);
        sb.append(", idMaestro=").append(idMaestro);
        sb.append(", nombre=").append(nombre);
        sb.append(", cubiculo=").append(cubiculo);
        sb.append(", descripcion=").append(descripcion);
        sb.append(", foto=").append(foto);
        sb.append(", calendario=").append(calendario);
        sb.append('}');
        return sb.toString();
    }

    public String calendarioToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Eventos[");
        for (EntidadEvento ev : calendario) {
            sb.append("{nombre=").append(ev.getNombre());
            sb.append("ubicacion=").append(ev.getUbicacion());
            sb.append("fecha inicio=").append(ev.fechaToString(ev.getFechaInicio()));
            sb.append("hora inicio=").append(ev.fechaToString(ev.getHoraInicio())).append("}");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
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
        final EntidadMaestro other = (EntidadMaestro) obj;
        return Objects.equals(this.id, other.id);
    }
    
    
}
