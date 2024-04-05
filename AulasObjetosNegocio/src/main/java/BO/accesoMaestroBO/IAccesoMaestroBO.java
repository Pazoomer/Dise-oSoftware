
package BO.accesoMaestroBO;

import DAO.exceptions.PersistenciaException;
import DAO.exceptions.ValidacionException;
import DTOS.maestro.MaestroEditableDTO;

/**
 *
 * @author t1pas
 */
public interface IAccesoMaestroBO {
    MaestroEditableDTO recuperarMaestro(MaestroEditableDTO maestroEditableDTO) throws PersistenciaException,ValidacionException;
}
