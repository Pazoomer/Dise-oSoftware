
package pruebas;

import conexion.ConexionDAO;
import conexion.IConexionDAO;

/**
 *
 * @author t1pas
 */
public class PruebaBaseDeDatos {

    public static void main(String[] args) {
        IConexionDAO conexion = new ConexionDAO();
        conexion.crearConexion();
    }

}
