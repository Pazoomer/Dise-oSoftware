
package accesoUbicaciones;

import DTOS.campus.CampusConsultableDTO;
import DTOS.campus.UbicacionDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author t1pas
 */
public interface IAccesoUbicaciones {
    /**
     * Recupera el campus del parametro
     * @param campus
     * @return 
     * @throws excepciones.NegocioException 
     */
    public CampusConsultableDTO recuperarCampus(CampusConsultableDTO campus)throws NegocioException;
    
    /**
     * Recupera todos los campus
     * @return 
     * @throws excepciones.NegocioException 
     */
    public  List<CampusConsultableDTO> recuperarTodosLosCampus()throws NegocioException;
    
    /**
     * Recupera los edificios de un campus
     * @param campus
     * @return 
     * @throws excepciones.NegocioException 
     */
    public List<UbicacionDTO> recuperarEdificiosPorCampus(CampusConsultableDTO campus)throws NegocioException;
}
