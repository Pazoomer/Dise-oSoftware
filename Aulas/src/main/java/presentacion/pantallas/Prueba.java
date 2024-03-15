/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package presentacion.pantallas;

import DTOS.evento.EventoConsultableDTO;
import DTOS.maestro.MaestroEditableDTO;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;



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
        List<EventoConsultableDTO> eventos=new ArrayList<>();
        Calendar fecha=Calendar.getInstance();
        fecha.set(2024, 2, 17);
        Calendar fecha2=Calendar.getInstance();
        fecha2.set(2024, 2, 20);
        EventoConsultableDTO ev1=new EventoConsultableDTO("semanal", "evento 1", "bla bla", Color.yellow, "milo", Calendar.getInstance());
        EventoConsultableDTO ev2=new EventoConsultableDTO("semanal", "evento 2", "bladas bla", Color.red, "milo", Calendar.getInstance());
        EventoConsultableDTO ev3=new EventoConsultableDTO("semanal", "evento 3", "blacasc bla", Color.green, "milo", Calendar.getInstance());
        EventoConsultableDTO ev4=new EventoConsultableDTO("semanal", "evento 4", "blafwg bla", Color.pink, "milo", Calendar.getInstance());
        EventoConsultableDTO ev5=new EventoConsultableDTO("semanal", "evento 5", "blaaaabla", Color.blue, "milo", fecha);
        EventoConsultableDTO ev6=new EventoConsultableDTO("unico", "evento 6", "fuaaa", Color.gray, "1826", fecha);
        EventoConsultableDTO ev7=new EventoConsultableDTO("unico", "evento 7", "que sueño", Color.ORANGE, "1828", fecha2);
        EventoConsultableDTO ev8=new EventoConsultableDTO("unico", "evento 8", "zzzz", Color.CYAN, "1829", fecha2);
        eventos.add(ev1);
        eventos.add(ev2);
        eventos.add(ev3);
        eventos.add(ev4);
        eventos.add(ev5);
        eventos.add(ev6);
        eventos.add(ev7);
        eventos.add(ev8);
        
        Icon imagen=new ImageIcon("C:/Users/luiis/Dropbox/PC/Documents/GitHub/Dise-oSoftware/Aulas/fotoMaestro.png");
        MaestroEditableDTO maestro=new MaestroEditableDTO(1L, "Gibran", "123", 
                "no se", imagen,eventos);
        PrincipalMaestro maes=new PrincipalMaestro(maestro);
        //PrincipalCalendario cal=new PrincipalCalendario(maes,maestro);
        
    }
    
}
