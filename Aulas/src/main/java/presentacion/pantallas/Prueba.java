
package presentacion.pantallas;

import DTOS.maestro.MaestroEditableDTO;
import excepciones.PersistenciaException;
import subsistemas.recuperarMaestro.IRecuperarMaestro;
import subsistemas.recuperarMaestro.FachadaRecuperarMaestro;



/**
 *
 * @author luiis
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        IRecuperarMaestro rMaestro = new FachadaRecuperarMaestro();
        MaestroEditableDTO maestroE = null;
        try {
            maestroE = rMaestro.recuperarMaestro();
        } catch (PersistenciaException e) {
            System.out.println("error al cargar el maestro");
        }
        if (maestroE != null) {
            new PrincipalMaestro(maestroE).setVisible(true);
        }

    }

}
