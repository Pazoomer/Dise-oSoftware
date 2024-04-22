package entidades;

import java.io.Serializable;
import java.util.List;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EntidadCampus{");
        sb.append("id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append("edificios=").append(edificiosToString());
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
}
