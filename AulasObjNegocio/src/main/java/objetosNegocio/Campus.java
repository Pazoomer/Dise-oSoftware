package objetosNegocio;

import DTOS.campus.CampusConsultableDTO;
import entidades.CrudCampus;
import entidades.EntidadCampus;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author t1pas
 */
public class Campus {
    private String id;
    private String nombre;
    private List<Ubicacion> ubicaciones;
    private CrudCampus crudCampus;
    private Conversiones conversiones;
    
    public Campus() {
        this.crudCampus=new CrudCampus();
        this.conversiones=new Conversiones();
    }

    public Campus(String id, String nombre, List<Ubicacion> ubicaciones) {
        this.id = id;
        this.nombre = nombre;
        this.ubicaciones = ubicaciones;
        this.crudCampus=new CrudCampus();
        this.conversiones=new Conversiones();
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

    public List<Ubicacion> getUbicaciones() {
        return ubicaciones;
    }

    public void setUbicaciones(List<Ubicacion> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }

    public CrudCampus getCrudCampus() {
        return crudCampus;
    }

    public void setCrudCampus(CrudCampus crudCampus) {
        this.crudCampus = crudCampus;
    }

    public CampusConsultableDTO obtenerCampus(CampusConsultableDTO campus)throws NegocioException{
        EntidadCampus ubi=conversiones.toCampusBO(campus);
        try {
            return conversiones.toUbicacionDTO(crudUbicacion.obtenerUbicacion(ubi.getIdentificador()));
        } catch (PersistenciaExceptionn ex) {
            Logger.getLogger(Ubicacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<UbicacionDTO> obtenerUbicacionesPorCampus(CampusConsultableDTO campus)throws NegocioException{
        List<UbicacionDTO> ubicacionesDTO=new ArrayList<>();
        EntidadCampus campusBO=new EntidadCampus(campus.getNombre());
        try{
            List<EntidadUbicacion> ubicaciones=crudUbicacion.obtenerUbicacionesPorCampus(campusBO);
            if(ubicaciones!=null && !ubicaciones.isEmpty()){
                for(EntidadUbicacion u:ubicaciones){
                    ubicacionesDTO.add(conversiones.toUbicacionDTO(u));
                }
                return ubicacionesDTO;
            }
            return null;
        }catch(PersistenciaExceptionn e){
            throw new NegocioException(e.getMessage());
        }
    }
    public List<UbicacionDTO> obtenerUbicaciones()throws NegocioException{
        List<UbicacionDTO> ubicacionesDTO=new ArrayList<>();
        try{
            List<EntidadUbicacion> ubicaciones=crudUbicacion.obtenerUbicaciones();
            if(ubicaciones!=null && !ubicaciones.isEmpty()){
                for(EntidadUbicacion u:ubicaciones){
                    ubicacionesDTO.add(conversiones.toUbicacionDTO(u));
                }
                return ubicacionesDTO;
            }
            return null;
        }catch(PersistenciaExceptionn e){
            throw new NegocioException(e.getMessage());
        }
    } 
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Campus{");
        sb.append("id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", ubicaciones=").append(ubicaciones);
        sb.append(", crudCampus=").append(crudCampus);
        sb.append(", conversiones=").append(conversiones);
        sb.append('}');
        return sb.toString();
    }

    public String toStringReducido() {
        StringBuilder sb = new StringBuilder();
        sb.append("id_campus=").append(id);
        sb.append("nombre=").append(nombre);
        return sb.toString();
    }
}
