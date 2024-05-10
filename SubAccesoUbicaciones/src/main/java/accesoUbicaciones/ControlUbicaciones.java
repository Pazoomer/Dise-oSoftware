
package accesoUbicaciones;

import DTOS.campus.CampusConsultableDTO;
import DTOS.campus.UbicacionDTO;
import DTOS.evento.EventoConsultableDTO;
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

    protected CampusConsultableDTO recuperarCampus(CampusConsultableDTO campusParametro) throws NegocioException {
        try {
            return campusBO.obtenerCampus(campusParametro);
        } catch (NegocioException e) {
            throw e;
        }
    }

    protected CampusConsultableDTO agregarCampus(CampusConsultableDTO campusParametro) throws NegocioException {
        try {
            return campusBO.agregarCampus(campusParametro);
        } catch (NegocioException e) {
            throw e;
        }
    }

    protected Boolean eliminarCampus(CampusConsultableDTO campusParametro) throws NegocioException {
        try {
            return campusBO.eliminarCampus(campusParametro);
        } catch (NegocioException e) {
            throw e;
        }
    }

    protected CampusConsultableDTO editarCampus(CampusConsultableDTO campusParametro) throws NegocioException {
        try {
            return campusBO.editarCampus(campusParametro);
        } catch (NegocioException e) {
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
    
     protected UbicacionDTO recuperarUbicacion(UbicacionDTO ubicacion)throws NegocioException {
        try{
            return campusBO.obtenerUbicacion(ubicacion);
        }catch(NegocioException e){
            throw e;
        }
    }
     
     protected UbicacionDTO agregarUbicacion(UbicacionDTO ubicacion)throws NegocioException {
        try{
            return campusBO.agregarUbicacion(ubicacion);
        }catch(NegocioException e){
            throw e;
        }

    }
     
     protected UbicacionDTO editarUbicacion(UbicacionDTO ubicacion)throws NegocioException {
        try{
            return campusBO.editarUbicacion(ubicacion);
        }catch(NegocioException e){
            throw e;
        }

    }
     
     protected Boolean eliminarUbicacion(UbicacionDTO ubicacion)throws NegocioException {
        try{
            return campusBO.eliminarUbicacion(ubicacion);
        }catch(NegocioException e){
            throw e;
        }

    }
    
    protected boolean agregarEventoAUbicacion(UbicacionDTO ubicacion, EventoConsultableDTO evento)throws NegocioException{
        try{
            return campusBO.agregarEventoAUbicacion(ubicacion, evento);
        }catch(NegocioException e){
            throw e;
        }
    }
}
