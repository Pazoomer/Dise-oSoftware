
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
    public CampusConsultableDTO agregarCampus(CampusConsultableDTO campus) throws NegocioException {
        return ubicaciones.agregarCampus(campus);
    }

    @Override
    public Boolean eliminarCampus(CampusConsultableDTO campus) throws NegocioException {
      return ubicaciones.eliminarCampus(campus);
    }

    @Override
    public CampusConsultableDTO editarCampus(CampusConsultableDTO campus) throws NegocioException {
        return ubicaciones.editarCampus(campus);
    }

    @Override
    public UbicacionDTO agregarUbicacion(UbicacionDTO ubicacion) throws NegocioException {
        return ubicaciones.agregarUbicacion(ubicacion);
    }

    @Override
    public UbicacionDTO editarUbicacion(UbicacionDTO ubicacion) throws NegocioException {
        return ubicaciones.editarUbicacion(ubicacion);
    }

    @Override
    public Boolean eliminarUbicacion(UbicacionDTO ubicacion) throws NegocioException {
       return ubicaciones.eliminarUbicacion(ubicacion);
    }
    
    @Override
    public boolean agregarEventoAUbicacion(UbicacionDTO ubicacion, EventoConsultableDTO evento) throws NegocioException {
        return ubicaciones.agregarEventoAUbicacion(ubicacion, evento);
    }

    @Override
    public List<UbicacionDTO> recuperarEdificiosPorNombre(String nombre)throws NegocioException {
      return ubicaciones.recuperarEdificiosPorNombre(nombre);
    }

    @Override
    public CampusConsultableDTO recuperarCampusPorNombre(String nombre) throws NegocioException {
       return ubicaciones.recuperarCampusPorNombre(nombre);
    }

}
