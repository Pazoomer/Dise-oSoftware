package DAO.maestroDAO;

import java.util.List;
import objetosNegocio.Maestro;

/**
 *
 * @author t1pas
 */
public interface IMaestroDAO {

    Maestro guardarMaestro(Maestro maestro);

    Maestro actualizarMaestro(Maestro maestro);

    boolean eliminarMaestro(long maestroId);

    Maestro obtenerMaestroPorId(long maestroId);

    List<Maestro> obtenerTodosLosMaestros();
}
