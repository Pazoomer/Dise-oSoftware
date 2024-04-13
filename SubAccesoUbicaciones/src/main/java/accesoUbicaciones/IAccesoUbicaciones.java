
package accesoUbicaciones;

import java.util.List;

/**
 *
 * @author t1pas
 */
public interface IAccesoUbicaciones {
    /**
     * Recupera los campus
     * @return 
     */
    public List<String> recuperarEdificios();
    
    /**
     * Recupera los edificios de un campus
     * @param campus
     * @return 
     */
    public List<String> recuperarEdificiosPorCampus(String campus);
}
