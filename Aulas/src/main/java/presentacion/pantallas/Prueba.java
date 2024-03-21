/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package presentacion.pantallas;

import DTOS.evento.EventoConsultableDTO;
import DTOS.maestro.MaestroEditableDTO;
import excepciones.PersistenciaException;
import java.awt.Color;
import java.awt.Image;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import subsistemas.recuperarMaestro.IRecuperarMaestro;
import subsistemas.recuperarMaestro.FachadaRecuperarMaestro;



/**
 *
 * @author luiis
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        IRecuperarMaestro rMaestro = new FachadaRecuperarMaestro();
        MaestroEditableDTO maestroE = null;
        try {
            maestroE = rMaestro.recuperarMaestro();
        } catch (PersistenciaException e) {
            System.out.println("error al cargar el maestro");
        }
        if(maestroE!=null)
            new PrincipalMaestro(maestroE).setVisible(true);
        

    }
    
}
