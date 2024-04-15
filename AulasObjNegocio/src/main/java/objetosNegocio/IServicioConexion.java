/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package objetosNegocio;

import excepciones.NegocioException;

/**
 *
 * @author luiis
 */
public interface IServicioConexion {
    public void cerrarConexion()throws NegocioException;
}
