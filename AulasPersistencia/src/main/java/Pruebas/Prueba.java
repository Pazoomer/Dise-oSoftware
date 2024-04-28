/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package Pruebas;

import entidades.CrudCampus;
import entidades.CrudMaestro;
import entidades.EntidadCampus;
import entidades.EntidadEvento;
import entidades.EntidadMaestro;
import entidades.EntidadUbicacion;
import excepcioness.PersistenciaExceptionn;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author luiis
 */
public class Prueba {

    public static void main(String[] args) {
        CrudCampus campus=new CrudCampus(); 
        CrudMaestro maestro=new CrudMaestro();
        EntidadMaestro m=new EntidadMaestro();
        m.setIdMaestro(1L);
       
        EntidadUbicacion ubicacion=new EntidadUbicacion("AV-1100");
        try{
            List<EntidadCampus> ubicaciones=campus.obtenerTodosLosCampus();
            if(!ubicaciones.isEmpty()) System.out.println(ubicaciones.getFirst());
            else System.out.println("no hay campus");
            m=maestro.obtenerMaestro(m);
            System.out.println(m.toString());
            ubicacion=campus.obtenerUbi(ubicacion);
            System.out.println(ubicacion.toString());
        }catch(PersistenciaExceptionn e){
            System.out.println(e);
        }
//       
//        
//        try{
//            EntidadMaestro maestroOb=new EntidadMaestro();
//            maestroOb.setIdMaestro(1L);
//            EntidadMaestro m=maestro.obtenerMaestro(maestroOb);
//            Calendar fecha=Calendar.getInstance();
//            fecha.set(Calendar.MONTH, 4);
//            fecha.set(Calendar.DAY_OF_MONTH, 10);
//            List<EntidadEvento> eventos=maestro.obtenerEventosMes(m, fecha,"semana");
//            if(eventos!=null && !eventos.isEmpty()){
//                for(EntidadEvento e:eventos){
//                    System.out.println(e.getNombre());
//                }
//            }else System.out.println("error");
//            System.out.println(m.getNombre());
//        }catch(PersistenciaExceptionn e){
//            System.out.println(e);
//        }
    }
}
