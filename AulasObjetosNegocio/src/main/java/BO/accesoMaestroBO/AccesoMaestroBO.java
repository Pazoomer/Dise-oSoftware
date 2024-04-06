package BO.accesoMaestroBO;

import DAO.exceptions.PersistenciaException;
import DAO.exceptions.ValidacionException;
import DTOS.maestro.MaestroEditableDTO;
import conexion.IConexionDAO;
import excepciones.NegocioException;
import java.util.logging.Level;
import java.util.logging.Logger;
import subsistemas.accesoMaestro.IAccesoMaestro;

/**
 *
 * @author t1pas
 */
public class AccesoMaestroBO implements IAccesoMaestroBO {

    private final IConexionDAO conexion;

    public AccesoMaestroBO(IConexionDAO conexion) {
        this.conexion = conexion;
    }

    @Override
    public boolean editarMaestro(MaestroEditableDTO maestroEditableDTO) {

        IAccesoMaestro dao=new EditarMaestro(conexion);
        try {
            return dao.editarMaestro(maestroEditableDTO);
        } catch (NegocioException ex) {
            Logger.getLogger(AccesoMaestroBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
