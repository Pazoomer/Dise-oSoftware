/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebas;

import DTOS.maestro.MaestroEditableDTO;
import accesoMaestro.FachadaAccesoMaestro;
import accesoMaestro.IAccesoMaestro;
import excepciones.NegocioException;

/**
 *
 * @author luiis
 */
public class pruebaMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        IAccesoMaestro accesoMaestro=new FachadaAccesoMaestro();
        try{
            MaestroEditableDTO maestro=accesoMaestro.recuperarMaestro(new MaestroEditableDTO(1L));
            System.out.println(maestro.toString());
        }catch(NegocioException e){
            System.out.println(e.getMessage());
        }
    }
    
}
