
package subsistemas.recuperarUbicaciones;

import java.util.List;

/**
 *
 * @author luiis
 */
public interface IRecuperarUbicaciones {
    public List<String> recuperarEdificios();
    
    public List<String> recuperarEdificiosPorCampus(String campus);
}
