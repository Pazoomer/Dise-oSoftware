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
        CrudCampus crudCampus=new CrudCampus(); 
        CrudMaestro crudMaestro=new CrudMaestro();
        EntidadMaestro entidadMaestro=new EntidadMaestro();
        entidadMaestro.setIdMaestro("1");
       
        EntidadUbicacion ubicacion=new EntidadUbicacion("AV-1100");
        
        try{
            
            List<EntidadCampus> campuses=crudCampus.obtenerTodosLosCampus();
            
            if(!campuses.isEmpty()) System.out.println(campuses.get(0));
            else System.out.println("No hay campuses");
            
            entidadMaestro=crudMaestro.obtenerMaestro(entidadMaestro);
            System.out.println(entidadMaestro.toString());
            
            ubicacion=crudCampus.obtenerUbi(ubicacion);
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
