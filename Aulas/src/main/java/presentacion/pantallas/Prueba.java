
package presentacion.pantallas;

import DTOS.maestro.MaestroEditableDTO;
import conexion.ConexionDAO;
import conexion.IConexionDAO;
import excepciones.NegocioException;
import subsistemas.recuperarMaestro.IRecuperarMaestro;
import subsistemas.recuperarMaestro.RecuperarMaestro;



/**
 *
 * @author luiis
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        IConexionDAO conexion=new ConexionDAO();

        IRecuperarMaestro rMaestro = new RecuperarMaestro(conexion);
        MaestroEditableDTO maestroE = null;
        try {
            maestroE = rMaestro.recuperarMaestro();
        } catch (NegocioException e) {
            System.out.println("error al cargar el maestro");
        }
        if (maestroE != null) {
            new PrincipalMaestro(maestroE,conexion).setVisible(true);
        }

    }

}
