/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package bda.aulaspersistencia2;

import entidades.CrudMaestro;
import entidades.EntidadEvento;
import entidades.EntidadMaestro;
import excepcioness.PersistenciaExceptionn;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author luiis
 */
public class AulasPersistencia2 {

    public static void main(String[] args) {
        CrudMaestro maestro=new CrudMaestro();
        
        try{
            EntidadMaestro maestroOb=new EntidadMaestro();
            maestroOb.setIdMaestro(1L);
            EntidadMaestro m=maestro.obtenerMaestro(maestroOb);
            Calendar fecha=Calendar.getInstance();
            fecha.set(Calendar.MONTH, 4);
            fecha.set(Calendar.DAY_OF_MONTH, 10);
            List<EntidadEvento> eventos=maestro.obtenerEventosMes(m, fecha,"semana");
            if(eventos!=null && !eventos.isEmpty()){
                for(EntidadEvento e:eventos){
                    System.out.println(e.getNombre());
                }
            }else System.out.println("error");
            System.out.println(m.getNombre());
        }catch(PersistenciaExceptionn e){
            System.out.println(e);
        }
    }
}
