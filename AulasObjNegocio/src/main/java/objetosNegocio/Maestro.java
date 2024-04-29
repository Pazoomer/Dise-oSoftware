
package objetosNegocio;

import DTOS.evento.EventoConsultableDTO;
import DTOS.maestro.MaestroEditableDTO;
import entidades.CrudMaestro;
import entidades.EntidadEvento;
import entidades.EntidadMaestro;
import excepciones.NegocioException;
import excepcioness.PersistenciaExceptionn;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luiis
 */
public class Maestro {
    private final Conversiones convertidor;
    private final CrudMaestro crudMaestro;

    public Maestro() {
        this.convertidor=new Conversiones();
        this.crudMaestro=new CrudMaestro();
    }

    public MaestroEditableDTO editarMaestro(MaestroEditableDTO maestro)throws NegocioException{
        EntidadMaestro maestroEditado= convertidor.toMaestroBO(maestro);
        try{
            return convertidor.toMaestroDTO(crudMaestro.editarMaestro(maestroEditado));
        }catch(PersistenciaExceptionn e){
            throw new NegocioException(e.getMessage());
        }
    }
    
    public MaestroEditableDTO obtenerMaestro(MaestroEditableDTO maestro)throws NegocioException{
        EntidadMaestro maestroBuscado=convertidor.toMaestroBO(maestro);
        try {
            EntidadMaestro maestroEncontrado=crudMaestro.obtenerMaestro(maestroBuscado);
            if (maestroEncontrado!=null) {
                return convertidor.toMaestroDTO(maestroEncontrado);
            }
            throw new PersistenciaExceptionn("No se encontro el maestro");
        } catch (PersistenciaExceptionn ex) {
            Logger.getLogger(Maestro.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ex.getMessage());
        }
    }
    
    public boolean agregarEventoCalendario(MaestroEditableDTO maestro, EventoConsultableDTO evento)throws NegocioException{
        EntidadMaestro maestroBO=convertidor.toMaestroBO(maestro);
        EntidadEvento eventoBO=convertidor.toEventoBO(evento);
        try{
            return crudMaestro.agregarEventoCalendario(maestroBO, eventoBO);
        }catch(PersistenciaExceptionn e){
            Logger.getLogger(Maestro.class.getName()).log(Level.SEVERE, null, e);
            throw new NegocioException(e.getMessage());
        }
    }
    
}
