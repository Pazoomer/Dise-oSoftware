/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOS.campus;

/**
 *
 * @author luiis
 */
public class UbicacionDTO {
    private Long id;
    private String identificador;
    private String descripcion;
    private CampusConsultableDTO campus;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        sb.append("identificador=").append(identificador);
        sb.append(", descripcion=").append(descripcion);
        sb.append(", campus=").append(campus.getNombre());
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
