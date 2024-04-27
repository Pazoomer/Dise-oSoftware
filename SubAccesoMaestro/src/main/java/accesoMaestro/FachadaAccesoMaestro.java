
package accesoMaestro;

import DTOS.evento.EventoConsultableDTO;
import DTOS.maestro.MaestroEditableDTO;
//import conexion.IConexionDAO;
import excepciones.NegocioException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author t1pas
 */
public class FachadaAccesoMaestro implements IAccesoMaestro{
    private ControlMaestros maestros;
//    public FachadaAccesoMaestro(IConexionDAO conexion) {
//        this.maestros = new ControlMaestros(conexion);
//    }

    public FachadaAccesoMaestro() {
        this.maestros =new ControlMaestros();
    }
    
    @Override
    public boolean editarMaestro(MaestroEditableDTO maestro)throws NegocioException{
        try {
            return maestros.editarMaestro(maestro);
        } catch (NegocioException e) {
            throw e;
        }
    }

    @Override
    public MaestroEditableDTO recuperarMaestro(MaestroEditableDTO maestro)throws NegocioException{
        try {
            return maestros.recuperarMaestro(maestro);
        } catch (NegocioException ex) {
            Logger.getLogger(FachadaAccesoMaestro.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
}
