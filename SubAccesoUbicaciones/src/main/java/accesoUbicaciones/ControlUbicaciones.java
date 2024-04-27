
package accesoUbicaciones;

import DTOS.campus.CampusConsultableDTO;
import DTOS.campus.UbicacionDTO;
import excepciones.NegocioException;
import java.util.List;
import objetosNegocio.Campus;

/**
 *
 * @author t1pas
 */
public class ControlUbicaciones {
    
    private final Campus campusBO;
    
    public ControlUbicaciones() {
        this.campusBO=new Campus();
    }

    protected List<CampusConsultableDTO> recuperarTodosLosCampus()throws NegocioException{
        try{
            return campusBO.obtenerTodosLosCampus();
        }catch(NegocioException e){
            throw e;
        }
    }
    
    protected CampusConsultableDTO recuperarCampus(CampusConsultableDTO campusParametro)throws NegocioException{
        try{
            return campusBO.obtenerCampus(campusParametro);
        }catch(NegocioException e){
            throw e;
        }
    }

    protected List<UbicacionDTO> recuperarEdificiosPorCampus(CampusConsultableDTO campus)throws NegocioException {
        try{
            return campusBO.obtenerUbicacionesPorCampus(campus);
        }catch(NegocioException e){
            throw e;
        }

    }
    
}
