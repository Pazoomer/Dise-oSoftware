

package bda.aulasobjnegocio;

import DTOS.campus.UbicacionDTO;
import DTOS.maestro.MaestroEditableDTO;
import excepciones.NegocioException;
import java.util.List;
import objetosNegocio.Evento;
import objetosNegocio.Maestro;
import objetosNegocio.Ubicacion;

/**
 *
 * @author luiis
 */
public class AulasObjNegocio {

    public static void main(String[] args) {
        Maestro m=new Maestro();
        MaestroEditableDTO maestroDTO=new MaestroEditableDTO(1L);
        try{
            MaestroEditableDTO maestro=m.obtenerMaestro(maestroDTO);
            System.out.println(maestro);
            System.out.println(maestro.getCalendario().getFirst().getUbicacion().getCampus().getNombre());
        }catch(NegocioException e){
            System.out.println(e);
        }
//      
    }
}
