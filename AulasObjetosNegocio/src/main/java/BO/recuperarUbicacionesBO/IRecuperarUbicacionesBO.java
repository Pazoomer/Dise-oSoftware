
package BO.recuperarUbicacionesBO;

import java.util.List;

/**
 *
 * @author t1pas
 */
public interface IRecuperarUbicacionesBO {
    public List<String> recuperarCampus();
    
    public List<String> recuperarEdificiosPorCampus(String campus);
}
