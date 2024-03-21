
package subsistemas.recuperarUbicaciones;

import java.util.List;

/**
 *
 * @author luiis
 */
public class FachadaRecuperarUbicaciones implements IRecuperarUbicaciones {

    private Ubicaciones ubicaciones;

    public FachadaRecuperarUbicaciones(Ubicaciones ubicaciones) {
        this.ubicaciones = ubicaciones;
    }

    @Override
    public List<String> recuperarCampus() {
        return ubicaciones.recuperarCampus();
    }

    @Override
    public List<String> recuperarEdificios(String campus) {
        return ubicaciones.recuperarEdificios(campus);
    }
    
}
