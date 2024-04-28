
package DTOS.maestro;

import DTOS.campus.UbicacionDTO;
import DTOS.evento.EventoConsultableDTO;
import DTOS.evento.TipoEventoEnumDTO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author t1pas
 */
public class MaestroEditableDTO {
    private String idBD;
    private String id;
    private String nombre;
    private UbicacionDTO cubiculo;
    private String descripcion;
    private String foto;
    private List<EventoConsultableDTO> calendario;   

    public MaestroEditableDTO(String id) {
        this.id=id;
        this.calendario=new ArrayList<>();
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
    public MaestroEditableDTO(String id, String nombre, UbicacionDTO cubiculo, String descripcion, String foto, List<EventoConsultableDTO> calendario) {
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
    public MaestroEditableDTO(String nombre, UbicacionDTO cubiculo, String descripcion, String foto, List<EventoConsultableDTO> calendario) {
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
    public MaestroEditableDTO(String id, String nombre, UbicacionDTO cubiculo, String descripcion, String foto) {
        this.id = id;
        this.nombre = nombre;
        this.cubiculo = cubiculo;
        this.descripcion = descripcion;
        this.foto = foto;
        this.calendario=new ArrayList<>();
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCubiculo(UbicacionDTO cubiculo) {
        this.cubiculo = cubiculo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    
    public String getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public UbicacionDTO getCubiculo() {
        return cubiculo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public List<EventoConsultableDTO> getCalendario() {
        return calendario;
    }

    public void setCalendario(List<EventoConsultableDTO> calendario) {
        this.calendario = calendario;
    }

    public String getIdBD() {
        return idBD;
    }

    public void setIdBD(String idBD) {
        this.idBD = idBD;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MaestroEditableDTO{");
        sb.append("idBD=").append(idBD);
        sb.append(", id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", cubiculo=").append(cubiculo.getIdentificador());
        sb.append(", descripcion=").append(descripcion);
        sb.append(", foto=").append(foto);
        if(!calendario.isEmpty())
            sb.append(", calendario=").append(calendarioToString());
        sb.append('}');
        return sb.toString();
    }
    
    public String calendarioToString(){
        StringBuilder sb=new StringBuilder();
        sb.append("Eventos[");
        for(EventoConsultableDTO ev:calendario){
            sb.append("{nombre=").append(ev.getNombre());
            sb.append("ubicacion=").append(ev.getUbicacion());
            sb.append("fecha inicio=").append(fechaToString(ev.getFechaInicio()));
            sb.append("hora inicio=").append(fechaToString(ev.getHoraInicio()));
            if(ev.getTipo().equals(TipoEventoEnumDTO.SEMANAL)){
                sb.append(ev.getDiasSemana()).append("}");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    public String fechaToString(Calendar fecha){
        SimpleDateFormat formatoFecha=new SimpleDateFormat("yyyy-MM-dd");
        return formatoFecha.format(fecha.getTime());
    }
}
