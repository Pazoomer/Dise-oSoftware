/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package lum.subadmineventos;

import DTOS.campus.UbicacionDTO;
import DTOS.evento.EventoConsultableDTO;
import DTOS.evento.TipoEventoEnumDTO;
import adminEventos.FachadaAdminEventos;
import excepciones.NegocioException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 *
 * @author luiis
 */
public class SubAdminEventos {

    public static void main(String[] args) {
//        Calendar horaInicio=Calendar.getInstance();
//        horaInicio.set(Calendar.HOUR_OF_DAY, 15);
//        horaInicio.set(Calendar.MINUTE, 0);
//        EventoConsultableDTO eventoN=new EventoConsultableDTO(
//                TipoEventoEnumDTO.SEMANAL, 
//                "Modelado de procesos",
//                "Clase de modelado de procesos",
//                "gris",
//                "Lu,Mi,Vi", 
//                new UbicacionDTO("AV-1100"),
//                Calendar.getInstance(), Calendar.getInstance(), horaInicio, 1.0);
//        System.out.println(eventoN);
//        EventoConsultableDTO en=new EventoConsultableDTO("Modelado de procesos");
//        
        //System.out.println(eventoN.getFechaInicio().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US));
        adminEventos.IAdminEventos admin=new FachadaAdminEventos();
//        Calendar horaInicio=Calendar.getInstance();
//        horaInicio.set(Calendar.HOUR_OF_DAY, 15);
//        horaInicio.set(Calendar.MINUTE, 0);
//        EventoConsultableDTO eventoN=new EventoConsultableDTO(
//                TipoEventoEnumDTO.SEMANAL, 
//                "Modelado de procesos",
//                "Clase de modelado de procesos",
//                "gris",
//                "Lu,Mi,Vi", 
//                new UbicacionDTO("AV-1100"),
//                Calendar.getInstance(), Calendar.getInstance(), horaInicio, 1.0);
        try {
//            en=admin.obtenerEvento(en);
//            if(en!=null)System.out.println(en);
//            else System.out.println("es null");
//----------------------------------------------------            
//            eventoN=admin.agregarEvento(eventoN);
//            if(eventoN!=null){
//                System.out.println("evento nuevo:");
//                System.out.println(eventoN);
//            }else System.out.println("evento nuevo null");
//--------------------------------------------------------            
            Calendar fecha=Calendar.getInstance();
            //fecha.setTimeZone(TimeZone.getTimeZone("America/Arizona"));
            fecha.set(2024, 5, 17);
            List<EventoConsultableDTO> eventos=admin.obtenerEventos("unico",fecha);
            if(eventos!=null){
                for (EventoConsultableDTO ev : eventos) {
                    System.out.println("??:"+ev);
                }
            }else System.out.println("eee");
        } catch (NegocioException e) {
            System.out.println(e);
        }
    }
}
