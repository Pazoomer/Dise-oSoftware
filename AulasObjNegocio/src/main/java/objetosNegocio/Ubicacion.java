
package objetosNegocio;

import entidades.CrudCampus;

/**
 *
 * @author luiis
 */
public class Ubicacion {
    private String identificador;
    private String descripcion;
    private String campus;
    private CrudCampus crudCampus;
    private Conversiones conversiones;
    
    public Ubicacion() {
        this.crudCampus=new CrudCampus();
        this.conversiones=new Conversiones();
    }

    public Ubicacion(String identificador, String descripcion, String campus) {
        this.identificador = identificador;
        this.descripcion = descripcion;
        this.campus = campus;
        this.crudCampus=new CrudCampus();
        this.conversiones=new Conversiones();
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

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

//    public UbicacionDTO obtenerUbicacion(UbicacionDTO ubicacion)throws NegocioException{
//        EntidadUbicacion ubi=conversiones.toUbicacionBO(ubicacion);
//        try {
//            return conversiones.toUbicacionDTO(crudUbicacion.obtenerUbicacion(ubi.getIdentificador()));
//        } catch (PersistenciaExceptionn ex) {
//            Logger.getLogger(Ubicacion.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
//    
//    public List<UbicacionDTO> obtenerUbicacionesPorCampus(CampusConsultableDTO campus)throws NegocioException{
//        List<UbicacionDTO> ubicacionesDTO=new ArrayList<>();
//        EntidadCampus campusBO=new EntidadCampus(campus.getNombre());
//        try{
//            List<EntidadUbicacion> ubicaciones=crudUbicacion.obtenerUbicacionesPorCampus(campusBO);
//            if(ubicaciones!=null && !ubicaciones.isEmpty()){
//                for(EntidadUbicacion u:ubicaciones){
//                    ubicacionesDTO.add(conversiones.toUbicacionDTO(u));
//                }
//                return ubicacionesDTO;
//            }
//            return null;
//        }catch(PersistenciaExceptionn e){
//            throw new NegocioException(e.getMessage());
//        }
//    }
//    public List<UbicacionDTO> obtenerUbicaciones()throws NegocioException{
//        List<UbicacionDTO> ubicacionesDTO=new ArrayList<>();
//        try{
//            List<EntidadUbicacion> ubicaciones=crudUbicacion.obtenerUbicaciones();
//            if(ubicaciones!=null && !ubicaciones.isEmpty()){
//                for(EntidadUbicacion u:ubicaciones){
//                    ubicacionesDTO.add(conversiones.toUbicacionDTO(u));
//                }
//                return ubicacionesDTO;
//            }
//            return null;
//        }catch(PersistenciaExceptionn e){
//            throw new NegocioException(e.getMessage());
//        }
//    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ubicacion{");
        sb.append("identificador=").append(identificador);
        sb.append(", descripcion=").append(descripcion);
        sb.append(", campus=").append(campus);
        sb.append('}');
        return sb.toString();
    }
    
    public String toStringReducido(){
        StringBuilder sb = new StringBuilder();
        sb.append("id_edificio=").append(identificador);
        sb.append(", campus=").append(campus);
        return sb.toString();
    }
}
