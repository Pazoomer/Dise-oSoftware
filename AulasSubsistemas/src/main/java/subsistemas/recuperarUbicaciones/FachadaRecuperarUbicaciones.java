
package subsistemas.recuperarUbicaciones;

import DTOS.campus.CampusConsultableDTO;
import java.util.List;

/**
 *
 * @author luiis
 */
public class FachadaRecuperarUbicaciones implements IRecuperarUbicaciones {

    private Ubicaciones ubicaciones;

    public FachadaRecuperarUbicaciones() {
        this.ubicaciones = new Ubicaciones();
    }

    @Override
    public List<String> recuperarEdificios() {
        return ubicaciones.recuperarEdificios();
    }

    @Override
    public List<String> recuperarEdificiosPorCampus(String campus) {
        return ubicaciones.recuperarEdificiosPorCampus(campus);
    }
    
}
