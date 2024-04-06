
package DTOS.maestro;

import DTOS.evento.EventoConsultableDTO;
import java.util.List;
import javax.swing.Icon;

/**
 *
 * @author t1pas
 */
public class MaestroEditableDTO {
    private Long id;
    private String nombre;
    private String cubiculo;
    private String descripcion;
    private Icon foto;
    private List<EventoConsultableDTO> calendario;   

    public MaestroEditableDTO() {
    }

    
    /**
     * Constructor con todos los atributos
     * @param id
     * @param nombre
     * @param cubiculo
     * @param descripcion
     * @param foto
     * @param calendario 
     */
    public MaestroEditableDTO(Long id, String nombre, String cubiculo, String descripcion, Icon foto, List<EventoConsultableDTO> calendario) {
        this.id = id;
        this.nombre = nombre;
        this.cubiculo = cubiculo;
        this.descripcion = descripcion;
        this.foto = foto;
        this.calendario = calendario;
    }
    
    /**
     * Constructor sin ID
     * @param nombre
     * @param cubiculo
     * @param descripcion
     * @param foto
     * @param calendario 
     */
    public MaestroEditableDTO(String nombre, String cubiculo, String descripcion, Icon foto, List<EventoConsultableDTO> calendario) {
        this.nombre = nombre;
        this.cubiculo = cubiculo;
        this.descripcion = descripcion;
        this.foto = foto;
        this.calendario = calendario;
        this.id=null;
        
    }

    /**
     * Constructor sin calendario
     * @param id
     * @param nombre
     * @param cubiculo
     * @param descripcion
     * @param foto 
     */
    public MaestroEditableDTO(Long id, String nombre, String cubiculo, String descripcion, Icon foto) {
        this.id = id;
        this.nombre = nombre;
        this.cubiculo = cubiculo;
        this.descripcion = descripcion;
        this.foto = foto;
        this.calendario=null;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCubiculo(String cubiculo) {
        this.cubiculo = cubiculo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFoto(Icon foto) {
        this.foto = foto;
    }
    
    
    
    public Long getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getCubiculo() {
        return cubiculo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Icon getFoto() {
        return foto;
    }

    public List<EventoConsultableDTO> getCalendario() {
        return calendario;
    }

    public void setCalendario(List<EventoConsultableDTO> calendario) {
        this.calendario = calendario;
    }

}
