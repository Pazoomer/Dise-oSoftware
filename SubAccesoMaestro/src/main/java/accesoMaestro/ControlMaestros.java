
package accesoMaestro;

import DTOS.evento.EventoConsultableDTO;
import DTOS.maestro.MaestroEditableDTO;
//import conexion.IConexionDAO;
import excepciones.NegocioException;
import java.util.List;
import objetosNegocio.Maestro;
//import static objetosNegocio.TipoEventoEnum.SEMANAL;
//import static objetosNegocio.TipoEventoEnum.UNICO_UN_DIA;
//import static objetosNegocio.TipoEventoEnum.UNICO_VARIOS_DIAS;
/**
 *
 * @author t1pas
 */
public class ControlMaestros {
    private final Maestro maestroBO;
    //private IConexionDAO conexion;
//
//    public ControlMaestros(IConexionDAO conexion) {
//        this.conexion = conexion;
//    }

    public ControlMaestros() {
        maestroBO=new Maestro();
    }
    
    protected boolean editarMaestro(MaestroEditableDTO maestro) throws NegocioException {
        MaestroEditableDTO maestroEditado;
        try{
            maestroEditado=maestroBO.editarMaestro(maestro);
            return maestroEditado!=null;
        }catch(NegocioException e){
            throw e;
        }
    }

    protected MaestroEditableDTO recuperarMaestro(MaestroEditableDTO maestro)
            throws NegocioException {
        try {
            return maestroBO.obtenerMaestro(maestro);
        } catch (NegocioException e) {
            throw e;
        }
    }
    
    protected boolean agregarEventoCalendario(MaestroEditableDTO maestro, EventoConsultableDTO evento)
            throws NegocioException{
        try{
            return maestroBO.agregarEventoCalendario(maestro, evento);
        }catch(NegocioException e){
            throw e;
        }
    }
    
}
