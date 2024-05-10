
package accesoUbicaciones;

import DTOS.campus.*;
import DTOS.evento.EventoConsultableDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author t1pas
 */
public class FachadaAccesoUbicaciones implements IAccesoUbicaciones {

    private final ControlUbicaciones ubicaciones;

    public FachadaAccesoUbicaciones() {
        this.ubicaciones = new ControlUbicaciones();
    }

    @Override
    public CampusConsultableDTO recuperarCampus(CampusConsultableDTO campus)throws NegocioException {
        return ubicaciones.recuperarCampus(campus);
    }

    @Override
    public List<UbicacionDTO> recuperarEdificiosPorCampus(CampusConsultableDTO campus) throws NegocioException{
        return ubicaciones.recuperarEdificiosPorCampus(campus);
    }

    @Override
    public List<CampusConsultableDTO> recuperarTodosLosCampus() throws NegocioException {
        return ubicaciones.recuperarTodosLosCampus();
    }

    @Override
    public UbicacionDTO recuperarUbicacion(UbicacionDTO ubicacion) throws NegocioException {
       return ubicaciones.recuperarUbicacion(ubicacion);
    }

    @Override
    public boolean agregarEventoAUbicacion(UbicacionDTO ubicacion, EventoConsultableDTO evento) throws NegocioException {
        return ubicaciones.agregarEventoAUbicacion(ubicacion, evento);
    }

}
