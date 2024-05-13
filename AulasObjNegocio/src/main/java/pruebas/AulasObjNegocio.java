

package pruebas;

import DTOS.campus.CampusConsultableDTO;
import DTOS.campus.UbicacionDTO;
import DTOS.maestro.MaestroEditableDTO;
import excepciones.NegocioException;
import objetosNegocio.Campus;
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
        Maestro m=new Maestro();
        Campus c=new Campus();
        CampusConsultableDTO campus=new CampusConsultableDTO();
        campus.setId("663fe5915d17b662b3d49bef");
        UbicacionDTO cubiculo= new UbicacionDTO();
        
        try {
            campus=c.obtenerCampus(campus);
            if (campus != null) {
                System.out.println("campus: "+campus);
                cubiculo.setCampus(campus);
                cubiculo=c.obtenerUbicacion(cubiculo);
                if(cubiculo!=null){
                    System.out.println("cubiculo: "+cubiculo);
                }
            }
        } catch (NegocioException e) {
            System.out.println(e);
        }
        MaestroEditableDTO maestroDTO = new MaestroEditableDTO("1");
        try {
            MaestroEditableDTO maestro = m.obtenerMaestro(maestroDTO);
            System.out.println("Maestro: " + maestro);
            //System.out.println("Nombre del campus de la ubicacion del primer evento del maestro: " + maestro.getCalendario().get(0).getUbicacion().getCampus().getNombre());
        } catch (NegocioException e) {
            System.out.println(e);
        }

        //MaestroEditableDTO maestroDTO=new MaestroEditableDTO("1");
        
//      
    }
}
