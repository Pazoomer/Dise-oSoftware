
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
     * Recupera los campus
     * @param ubicacion
     * @return 
     * @throws excepciones.NegocioException 
     */
    public UbicacionDTO recuperarCampus(UbicacionDTO ubicacion)throws NegocioException;
    
    /**
     * Recupera los edificios de un campus
     * @param campus
     * @return 
     * @throws excepciones.NegocioException 
     */
    public List<UbicacionDTO> recuperarEdificiosPorCampus(CampusConsultableDTO campus)throws NegocioException;
}
