
package DTOS.campus;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author t1pas
 */
public class CampusConsultableDTO {
    private String id;
    private String nombre;
    private List<UbicacionDTO> ubicaciones;

    public CampusConsultableDTO(String nombre) {
        this.nombre = nombre;
        this.ubicaciones=new ArrayList<>();
    }

    public CampusConsultableDTO(String nombre, List<UbicacionDTO> ubicaciones) {
        this.nombre = nombre;
        this.ubicaciones = ubicaciones;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<UbicacionDTO> getUbicaciones() {
        return ubicaciones;
    }

    public void setUbicaciones(List<UbicacionDTO> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CampusConsultableDTO{");
        sb.append("id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", ubicaciones=").append(ubicaciones);
        sb.append('}');
        return sb.toString();
    }
    
    public String edificiosToString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Edificios[");
        for(UbicacionDTO u: ubicaciones){
            sb.append("id_edificio=").append(u.getIdentificador());
            if(ubicaciones.indexOf(u)>(ubicaciones.size()-1))
                sb.append(", ");
        }
        sb.append(']');
        return sb.toString();
    }
}
