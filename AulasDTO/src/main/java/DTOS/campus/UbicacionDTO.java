
package DTOS.campus;

import DTOS.evento.EventoConsultableDTO;
import java.util.List;

/**
 *
 * @author luiis
 */
public class UbicacionDTO {

    private String id;
    private String identificador;
    private String descripcion;
    private CampusConsultableDTO campus;
    private List<EventoConsultableDTO> eventos;
    private Double posicionX;
    private Double posicionY;

    public UbicacionDTO() {
    }

    public UbicacionDTO(String identificador) {
        this.identificador = identificador;
    }

    public UbicacionDTO(String identificador, String descripcion, CampusConsultableDTO campus) {
        this.identificador = identificador;
        this.descripcion = descripcion;
        this.campus = campus;
    }

    public List<EventoConsultableDTO> getEventos() {
        return eventos;
    }

    public void setEventos(List<EventoConsultableDTO> eventos) {
        this.eventos = eventos;
    }

    public Double getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(Double posicionX) {
        this.posicionX = posicionX;
    }

    public Double getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(Double posicionY) {
        this.posicionY = posicionY;
    }
    
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CampusConsultableDTO getCampus() {
        return campus;
    }

    public void setCampus(CampusConsultableDTO campus) {
        this.campus = campus;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UbicacionDTO{");
        sb.append("id=").append(id);
        sb.append(", identificador=").append(identificador);
        sb.append(", descripcion=").append(descripcion);
        if (campus != null) {
            sb.append(", campus=").append(campus.getId());
        }

        sb.append(", eventos=").append(eventos);
        sb.append(", posicionX=").append(posicionX);
        sb.append(", posicionY=").append(posicionY);
        sb.append('}');
        return sb.toString();
    }

   
    
    
    public String toStringReducido(){
        StringBuilder sb = new StringBuilder();
        sb.append("id_edificio=").append(identificador);
        sb.append(", campus=").append(campus.getNombre());
        return sb.toString();
    }
    
}
