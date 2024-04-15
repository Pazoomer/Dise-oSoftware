/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import entidades.ClaseConexion;
import excepciones.NegocioException;
import excepcioness.PersistenciaExceptionn;

/**
 *
 * @author luiis
 */
public class ServicioConexion implements IServicioConexion{

    @Override
    public void cerrarConexion()throws NegocioException {
        try{
            ClaseConexion.cerrarConexion();
        }catch(PersistenciaExceptionn e){
            throw new NegocioException(e.getMessage());
        }
    }
    
}
