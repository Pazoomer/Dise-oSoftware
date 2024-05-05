package objetosNegocio;

import DTOS.campus.CampusConsultableDTO;
import DTOS.campus.UbicacionDTO;
import entidades.CrudCampus;
import entidades.EntidadCampus;
import entidades.EntidadUbicacion;
import excepciones.NegocioException;
import excepcioness.PersistenciaExceptionn;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author t1pas
 */
public class Campus {
    private final CrudCampus crudCampus;
    private final Conversiones conversiones;
    
    public Campus() {
        this.crudCampus=new CrudCampus();
        this.conversiones=new Conversiones();
    }

    public CampusConsultableDTO obtenerCampus(CampusConsultableDTO campusParametro) throws NegocioException {

        try {
            EntidadCampus entidadCampus=crudCampus.obtenerCampus(conversiones.toCampusBO(campusParametro));
            if (entidadCampus!=null) {
                 return conversiones.toCampusDTO(entidadCampus);
            }
            throw new PersistenciaExceptionn("No se encontro el campus");

        } catch (PersistenciaExceptionn ex) {
            Logger.getLogger(Campus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<CampusConsultableDTO> obtenerTodosLosCampus() throws NegocioException {

        try {
            List<CampusConsultableDTO> campus = new ArrayList<>();
            List<EntidadCampus> entidadesCampus = crudCampus.obtenerTodosLosCampus();
            for (EntidadCampus entidadCampus : entidadesCampus) {
                campus.add(conversiones.toCampusDTO(entidadCampus));
            }
            return campus;
        } catch (PersistenciaExceptionn ex) {
            Logger.getLogger(Campus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<UbicacionDTO> obtenerUbicacionesPorCampus(CampusConsultableDTO campus)throws NegocioException{
        List<UbicacionDTO> ubicacionesDTO=new ArrayList<>();
        EntidadCampus campusBO=new EntidadCampus(campus.getNombre());
        
        try{
            List<EntidadUbicacion> ubicacionesAux=crudCampus.obtenerUbicacionesPorCampus(campusBO);
            
            if(ubicacionesAux!=null && !ubicacionesAux.isEmpty()){
                for(EntidadUbicacion u:ubicacionesAux){
                    ubicacionesDTO.add(conversiones.toUbicacionDTO(u));
                }
                return ubicacionesDTO;
            }
            return null;
        } catch (PersistenciaExceptionn e) {
            throw new NegocioException(e.getMessage());
        }
    }

    public UbicacionDTO obtenerUbicacion(UbicacionDTO ubicacion) throws NegocioException {
        try {
            EntidadUbicacion entidadUbicacion=crudCampus.obtenerUbi(conversiones.toUbicacionBO(ubicacion));
            if (entidadUbicacion!=null) {
                return conversiones.toUbicacionDTO(entidadUbicacion);
            }
            return null;
        } catch (PersistenciaExceptionn ex) {
            throw new NegocioException(ex.getMessage());
            //Logger.getLogger(Campus.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return null;
    }

}
