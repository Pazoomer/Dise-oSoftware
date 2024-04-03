
package subsistemas.accesoMaestro;

import DTOS.maestro.MaestroEditableDTO;
import excepciones.NegocioException;

/**
 *
 * @author t1pas
 */
public class FachadaEditarMaestro implements IAccesoMaestro {

    private final ControlMaestro maestros;

    public FachadaEditarMaestro() {
        this.maestros = new ControlMaestro();
    }
   
    @Override
    public MaestroEditableDTO agregarEventoCalendario(MaestroEditableDTO maestro) throws NegocioException {
        //TODO
        //Solo soy una fachada
         try{
        return maestros.agregarEventoCalendario(maestro);
        }catch(NegocioException e){
                    throw new NegocioException(e.getMessage());
        }
    }
     public MaestroEditableDTO editarMaestro(MaestroEditableDTO maestro) throws NegocioException {
        
      try{
        return maestros.editarMaestro(maestro);
        }catch(NegocioException e){
                    throw new NegocioException(e.getMessage());
        }
     }
}
