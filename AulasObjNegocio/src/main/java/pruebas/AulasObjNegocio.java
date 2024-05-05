

package pruebas;

import DTOS.campus.UbicacionDTO;
import DTOS.evento.EventoConsultableDTO;
import DTOS.evento.TipoEventoEnumDTO;
import DTOS.maestro.MaestroEditableDTO;
import excepciones.NegocioException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import objetosNegocio.Campus;
import objetosNegocio.Evento;
import objetosNegocio.Maestro;

/**
 *
 * @author luiis
 */
public class AulasObjNegocio {

    public static void main(String[] args) {
        
//        Calendar fechaBase1=Calendar.getInstance();
//        fechaBase1.set(Calendar.MONTH, 3);
//        fechaBase1.set(Calendar.DAY_OF_MONTH, 10);
//        fechaBase1.set(Calendar.YEAR, 2023);
//        Calendar fechaBase2=Calendar.getInstance();
//        fechaBase2.set(Calendar.MONTH, 4);
//        fechaBase2.set(Calendar.DAY_OF_MONTH, 20);
//        fechaBase2.set(Calendar.YEAR, 2023);
//        Calendar fechaBase3=Calendar.getInstance();
//        fechaBase3.set(Calendar.HOUR_OF_DAY,10);
//        fechaBase3.set(Calendar.MINUTE,0);
//        
//        Calendar fechaInicio =Calendar.getInstance();
//        Date date=fechaBase1.getTime();
//        fechaInicio.setTime(date);
//        Calendar fechaFin=Calendar.getInstance();
//        date=fechaBase2.getTime();
//        fechaFin.setTime(date);
//        Calendar horaInicio=Calendar.getInstance();
//        date=fechaBase3.getTime();
//        horaInicio.setTime(date);
//        
//        System.out.println("fecha inicio: "+fechaInicio.get(Calendar.DAY_OF_MONTH)+"/"+
//                fechaInicio.get(Calendar.MONTH)+"/"+fechaInicio.get(Calendar.YEAR));
//        System.out.println("fecha fin: "+fechaFin.get(Calendar.DAY_OF_MONTH)+"/"+
//                fechaFin.get(Calendar.MONTH)+"/"+fechaFin.get(Calendar.YEAR));
//        System.out.println("hora inicio: "+horaInicio.get(Calendar.HOUR_OF_DAY)+":"+
//                horaInicio.get(Calendar.MINUTE));
//        Maestro m=new Maestro();
//        MaestroEditableDTO maestroDTO=new MaestroEditableDTO("1");
//        try{
//            MaestroEditableDTO maestro=m.obtenerMaestro(maestroDTO);
//            System.out.println("Maestro: "+maestro);
//            System.out.println("Nombre del campus de la ubicacion del primer evento del maestro: "+maestro.getCalendario().get(0).getUbicacion().getCampus().getNombre());
//        }catch(NegocioException e){
//            System.out.println(e);
//        }
//      
        Evento ev=new Evento();
        Campus c=new Campus();
//        Calendar fechaInicio=Calendar.getInstance();
//        Calendar fechaFin=Calendar.getInstance();
//        fechaInicio.set(2024, 0, 15, 8, 0,0);
//        fechaFin.set(2024, 5, 10);
//        UbicacionDTO ubicacion=new UbicacionDTO("AV-1100");
        
        EventoConsultableDTO eventoN=new EventoConsultableDTO("Galeria de arte");
//        EventoConsultableDTO eventoN=new EventoConsultableDTO(TipoEventoEnumDTO.SEMANAL, "Algebra lineal", "Clase de algebra lineal", "rojo", "LuMi",ubicacion, fechaInicio, fechaFin, fechaInicio, 1.5);
        try{
            //eventoN=ev.agregarEvento(eventoN);
            eventoN=ev.obtenerEvento(eventoN);
            if(eventoN!=null)System.out.println(eventoN);
            else System.out.println("evento null");
            eventoN.setColor("purpura");
            List<String> camposModificados=new ArrayList<>();
            camposModificados.add("color");
            eventoN=ev.editarEvento(eventoN, camposModificados);
            if(eventoN!=null)System.out.println("evento editado: "+eventoN);
            else System.out.println("evento editado null");
//            ubicacion=c.obtenerUbicacion(ubicacion);
//            if(ubicacion!=null)System.out.println(ubicacion);
//            else System.out.println("ubi null");
//            List<EventoConsultableDTO> eventos=ev.obtenerEventos();
//            if(eventos!=null){
//                for (EventoConsultableDTO evento : eventos) {
//                    System.out.println("nombre evento; "+evento.getNombre());
//                }
//            }else System.out.println("eventosss null");
        }catch(NegocioException e){
            System.out.println(e);
        }
    }
}
