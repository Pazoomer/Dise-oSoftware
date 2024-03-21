
package DTOS.campus;

import java.util.List;

/**
 *
 * @author t1pas
 */
public class CampusConsultableDTO {
    private String nombre;
    private List<String> ubicaciones;

    public CampusConsultableDTO(String nombre, List<String> ubicaciones) {
        this.nombre = nombre;
        this.ubicaciones = ubicaciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getUbicaciones() {
        return ubicaciones;
    }

    public void setUbicaciones(List<String> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MaestroConsultableDTO{");
        sb.append("nombre=").append(nombre);
        sb.append(", ubicaciones=").append(ubicaciones);
        sb.append('}');
        return sb.toString();
    }
    
}
