

package bda.aulasobjnegocio;

import DTOS.campus.UbicacionDTO;
import DTOS.maestro.MaestroEditableDTO;
import excepciones.NegocioException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import objetosNegocio.Evento;
import objetosNegocio.Maestro;
import objetosNegocio.Ubicacion;

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
        Maestro m=new Maestro();
        MaestroEditableDTO maestroDTO=new MaestroEditableDTO(1L);
        try{
            MaestroEditableDTO maestro=m.obtenerMaestro(maestroDTO);
            System.out.println(maestro);
            System.out.println(maestro.getCalendario().getFirst().getUbicacion().getCampus().getNombre());
        }catch(NegocioException e){
            System.out.println(e);
        }
//      
    }
}
