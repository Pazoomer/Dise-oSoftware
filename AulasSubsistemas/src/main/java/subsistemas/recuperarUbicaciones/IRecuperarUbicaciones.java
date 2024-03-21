
package subsistemas.recuperarUbicaciones;

import java.util.List;

/**
 *
 * @author luiis
 */
public interface IRecuperarUbicaciones {
    public List<String> recuperarCampus();
    public List<String> recuperarEdificios(String campus);
}
