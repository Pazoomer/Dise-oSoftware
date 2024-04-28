package entidades;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

public class EntidadCampus implements Serializable {

    @BsonId
    private ObjectId id;
    
    private String nombre;

    private List<EntidadUbicacion> ubicaciones;

    public EntidadCampus() {
    }

    public EntidadCampus(String nombre, List<EntidadUbicacion> ubicaciones) {
        this.nombre = nombre;
        this.ubicaciones = ubicaciones;
    }

    public EntidadCampus(String nombre) {
        this.nombre = nombre;
    }
    
    public List<EntidadUbicacion> getUbicaciones() {
        return ubicaciones;
    }

    public void setUbicaciones(List<EntidadUbicacion> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
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
       if (id != null) {
            this.id = new ObjectId(id);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EntidadCampus{");
        sb.append("id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", ubicaciones=").append(ubicaciones);
        sb.append('}');
        return sb.toString();
    }

    

    

    private String edificiosToString(){
        StringBuilder sb=new StringBuilder();
        sb.append("Ubicaciones[");
        for(EntidadUbicacion ubi:ubicaciones){
            sb.append("{edificio=").append(ubi.getIdentificador()).append('}');
        }
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
        final EntidadCampus other = (EntidadCampus) obj;
        return Objects.equals(this.id, other.id);
    }
    
}
