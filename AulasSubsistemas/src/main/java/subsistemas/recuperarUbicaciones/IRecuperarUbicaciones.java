/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package subsistemas.recuperarUbicaciones;

import java.util.List;

/**
 *
 * @author luiis
 */
public interface IRecuperarUbicaciones {
    public List<String> recuperarCampus();
    public List<String> recuperarEdificios(String campus);
}
