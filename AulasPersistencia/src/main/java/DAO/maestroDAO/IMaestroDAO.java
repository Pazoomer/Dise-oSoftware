package DAO.maestroDAO;

import java.util.List;
import entidades.EntidadMaestro;

/**
 *
 * @author t1pas
 */
public interface IMaestroDAO {

    EntidadMaestro guardarMaestro(EntidadMaestro maestro);

    EntidadMaestro actualizarMaestro(EntidadMaestro maestro);

    boolean eliminarMaestro(long maestroId);

    EntidadMaestro obtenerMaestroPorId(long maestroId);

    List<EntidadMaestro> obtenerTodosLosMaestros();
}
