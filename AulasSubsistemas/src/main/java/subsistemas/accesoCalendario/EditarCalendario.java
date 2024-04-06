
package subsistemas.accesoCalendario;

import DTOS.evento.EventoConsultableDTO;
import conexion.IConexionDAO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author t1pas
 */
public class EditarCalendario implements IAccesoCalendario{

    private final ControlCalendarios calendarios;
    private final IConexionDAO conexion;
    
    public EditarCalendario(IConexionDAO conexion) {
        this.conexion = conexion;
        this.calendarios = new ControlCalendarios(conexion);
    }
    
//    @Override
//    public List<EventoConsultableDTO> editarCalendario(List<EventoConsultableDTO> calendario, EventoConsultableDTO evento,
//            String tipoOperacion) throws NegocioException {
//        List<EventoConsultableDTO> calendarioEditado=null;
//        switch(tipoOperacion){
//            case "agregar":
//                try{
//                    calendarioEditado=calendarios.agregarEvento(calendario, evento);
//                }catch(NegocioException e){
//                    throw new NegocioException(e.getMessage());
//                }
//                break;
//            case "editar":
//                try{
//                    calendarioEditado=calendarios.editarEvento(calendario, evento);
//                }catch(NegocioException e){
//                    throw new NegocioException(e.getMessage());
//                }
//                break;
//            case "eliminar":
//                try{
//                    calendarioEditado=calendarios.eliminarEvento(calendario, evento);
//                }catch(NegocioException e){
//                    throw new NegocioException(e.getMessage());
//                }
//                break;
//        }
//        return calendarioEditado;
//    }
//
//    @Override
//    public EventoConsultableDTO obtenerEvento(EventoConsultableDTO evento) throws NegocioException {
//        EventoConsultableDTO eventoObtenido;
//        try{
//            eventoObtenido=calendarios.obtenerEvento(evento);
//            return eventoObtenido;
//        }catch(NegocioException e){
//            throw new NegocioException(e.getMessage());
//        }
//    }

    @Override
    public boolean editarCalendario(List<EventoConsultableDTO> calendario) {
       return calendarios.editarCalendario(calendario);
    }
}
