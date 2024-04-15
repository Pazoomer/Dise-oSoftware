/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import DTOS.campus.CampusConsultableDTO;
import DTOS.campus.UbicacionDTO;
import entidades.CrudEvento;
import entidades.CrudUbicacion;
import entidades.EntidadCampus;
import entidades.EntidadUbicacion;
import excepciones.NegocioException;
import excepcioness.PersistenciaExceptionn;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luiis
 */
public class Ubicacion {
    private String identificador;
    private String descripcion;
    private String campus;
    private CrudUbicacion crudUbicacion;
    private Conversiones conversiones;
    
    public Ubicacion() {
        this.crudUbicacion=new CrudUbicacion();
        this.conversiones=new Conversiones();
    }

    public Ubicacion(String identificador, String descripcion, String campus) {
        this.identificador = identificador;
        this.descripcion = descripcion;
        this.campus = campus;
        this.crudUbicacion=new CrudUbicacion();
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

    public UbicacionDTO obtenerUbicacion(UbicacionDTO ubicacion)throws NegocioException{
        EntidadUbicacion ubi=conversiones.toUbicacionBO(ubicacion);
        try{
            return conversiones.toUbicacionDTO(crudUbicacion.obtenerUbicacion(ubi));
        }catch(PersistenciaExceptionn e){
            throw new NegocioException(e.getMessage());
        }
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
