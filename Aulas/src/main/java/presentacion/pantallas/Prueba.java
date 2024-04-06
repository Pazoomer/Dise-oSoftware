
package presentacion.pantallas;

import BO.recuperarMaestroBO.IRecuperarMaestroBO;
import BO.recuperarMaestroBO.RecuperarMaestroBO;
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

        IConexionDAO conexion = new ConexionDAO();

        IRecuperarMaestroBO recuperarMaestroBo = new RecuperarMaestroBO(conexion);
        MaestroEditableDTO maestroEditable = recuperarMaestroBo.recuperarMaestro();

        if (maestroEditable != null) {
            new PrincipalMaestro(maestroEditable, conexion).setVisible(true);
        }

    }

}
