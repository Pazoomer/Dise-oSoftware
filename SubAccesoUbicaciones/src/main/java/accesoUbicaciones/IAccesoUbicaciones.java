
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
     * Agrega el campus del parametro
     * @param campus
     * @return 
     * @throws excepciones.NegocioException 
     */
    public CampusConsultableDTO agregarCampus(CampusConsultableDTO campus)throws NegocioException;
    
    /**
     * Elimina el campus del parametro
     * @param campus
     * @return 
     * @throws excepciones.NegocioException 
     */
    public Boolean eliminarCampus(CampusConsultableDTO campus)throws NegocioException;
    
    /**
     * Edita el campus del parametro
     * @param campus
     * @return 
     * @throws excepciones.NegocioException 
     */
    public CampusConsultableDTO editarCampus(CampusConsultableDTO campus)throws NegocioException;
    
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
    
    /**
     * Recupera los edificios de un campus
     * @param ubicacion
     * @return 
     * @throws excepciones.NegocioException 
     */
    public UbicacionDTO recuperarUbicacion(UbicacionDTO ubicacion)throws NegocioException;
    
    /**
     * Agrega los edificios de un campus
     * @param ubicacion
     * @return 
     * @throws excepciones.NegocioException 
     */
    public UbicacionDTO agregarUbicacion(UbicacionDTO ubicacion)throws NegocioException;
    
    /**
     * Edita los edificios de un campus
     * @param ubicacion
     * @return 
     * @throws excepciones.NegocioException 
     */
    public UbicacionDTO editarUbicacion(UbicacionDTO ubicacion)throws NegocioException;
    
    /**
     * Elimina los edificios de un campus
     * @param ubicacion
     * @return 
     * @throws excepciones.NegocioException 
     */
    public Boolean eliminarUbicacion(UbicacionDTO ubicacion)throws NegocioException;
}
