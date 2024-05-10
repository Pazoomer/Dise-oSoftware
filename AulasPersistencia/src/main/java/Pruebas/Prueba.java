/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package Pruebas;

import entidades.Conexion;
import entidades.CrudCampus;
import entidades.CrudEvento;
import entidades.CrudMaestro;
import entidades.EntidadCampus;
import entidades.EntidadEvento;
import entidades.EntidadMaestro;
import entidades.EntidadTipoEventoEnum;
import entidades.EntidadUbicacion;
import excepcioness.PersistenciaExceptionn;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 *
 * @author luiis
 */
public class Prueba {

    public static void main(String[] args) {
//        Calendar inicioDia = Calendar.getInstance();
//        inicioDia.setTimeZone(TimeZone.getTimeZone("America/Arizona"));
//        inicioDia.set(Calendar.HOUR_OF_DAY, 10);
//        inicioDia.set(Calendar.MINUTE, 20);
//        String diaSemana=inicioDia.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.LONG,Locale.US);
//        System.out.println(diaSemana);
//        Double horasDuracionEvento=2.5;
//        Calendar horaInicio=Calendar.getInstance();
//        horaInicio.set(Calendar.MONTH, 2);
//        horaInicio.set(Calendar.DAY_OF_MONTH, 20);
//        horaInicio.set(Calendar.HOUR_OF_DAY, 13);
//        horaInicio.set(Calendar.MINUTE, 30);
//        System.out.println("mes : "+horaInicio.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US));
//        System.out.println("hora inicio: "+horaInicio.get(Calendar.HOUR_OF_DAY)+":"+horaInicio.get(Calendar.MINUTE));
//        
//        
//        Calendar fechaFinCalculada=Calendar.getInstance();
//        fechaFinCalculada.setTime(horaInicio.getTime());
//        fechaFinCalculada.add(Calendar.HOUR, horasDuracionEvento.intValue());
//        System.out.println("fecha fin calculada: "+fechaFinCalculada.get(Calendar.DAY_OF_MONTH)+"/"+fechaFinCalculada.get(Calendar.MONTH)
//        +"/"+fechaFinCalculada.get(Calendar.YEAR)+" "+fechaFinCalculada.get(Calendar.HOUR_OF_DAY)+":"+fechaFinCalculada.get(Calendar.MINUTE));
//        
//        CrudCampus crudCampus=new CrudCampus(); 
//        EntidadUbicacion ubi=null;
//        try{
//            //ubi=crudCampus.agregarUbicacionACampus(new EntidadUbicacion("Tutorias", "Obregon Nainari", "Edificio de tutorias"));
//            ubi=crudCampus.obtenerUbi(new EntidadUbicacion("Tutorias"));
//            System.out.println("ubicacion: "+ubi);
//        }catch(PersistenciaExceptionn e){
//            System.out.println(e);
        //}
        
//        CrudMaestro crudMaestro=new CrudMaestro();
//        EntidadMaestro entidadMaestro=new EntidadMaestro();
//        entidadMaestro.setIdMaestro("1");
//        try{
//            System.out.println(crudMaestro.obtenerMaestro(entidadMaestro).getNombre());
//        }catch(PersistenciaExceptionn e){
//            System.out.println(e);
//        }
//--------------------------------------
//        CrudEvento crud=new CrudEvento();
//        try{
//            EntidadEvento ev=crud.obtenerEventos().get(0);
//            ev.setColor("naranja");
//            List<String> camposModificados=new ArrayList<>();
//            camposModificados.add("color");
//            ev=crud.editarEvento(ev, camposModificados);
//            System.out.println(ev.getColor());
//        }catch(PersistenciaExceptionn e){
//            System.out.println(e);
//        }
//-------------------------------------------
        CrudEvento crud=new CrudEvento();
//        Calendar fechaInicio=Calendar.getInstance();
//        fechaInicio.set(2024, Calendar.MAY, 5, 8, 30,0);
//        System.out.println(fechaInicio.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US));
        //Calendar fechaFin=Calendar.getInstance();
        //fechaFin.set(2024, 5, 15, 19, 0,0);
//        EntidadUbicacion ubicacion=new EntidadUbicacion("AV-1200");
//        CrudCampus c=new CrudCampus();
//        EntidadEvento evento=new EntidadEvento("Dia de software", "Conferencias y talleres del dia de software 2024", null, "blanco", fechaInicio.getTime(), 4.0);
//        EntidadEvento evento=new EntidadEvento();
//        evento.setNombre("Galeria de arte");
        try{
//            ubicacion=c.obtenerUbi(ubicacion);
//            System.out.println("ubicacion: "+ubicacion);
//            evento.setUbicacion(ubicacion);
//            evento=crud.agregarEvento(evento);
//            if(evento!=null)System.out.println(evento);
//            ubicacion.agregarEvento(evento);
//            ubicacion=c.obtenerUbi(ubicacion);
//            System.out.println("eventos de ubi: "+ubicacion.eventosToString());
//            if(c.agregarEventoAUbicacion(ubicacion, evento))System.out.println("se agrego");
//            else System.out.println("no se agrego");
//--------------------------------------------            
//            evento=crud.obtenerEvento(evento);
//            System.out.println("evento a agregar: "+evento);
//            ubicacion=c.obtenerUbi(ubicacion);
//            System.out.println("ubicacion: "+ubicacion);
//            ubicacion.agregarEvento(evento);
//            System.out.println("eventos de ubi: "+ubicacion.eventosToString());
//            if(c.agregarEventoAUbicacion(ubicacion, evento))System.out.println("se agrego");
//            else System.out.println("no se agrego");
//-----------------------------------------
//            evento.setUbicacion(ubicacion);
//            evento=crud.obtenerEvento(evento);
//            System.out.println(evento);
            
//-----------------------------------------
//            evento.setColor("morado");
//            System.out.println("evento con color morado: "+evento);
//            List<String> camposModificados=new ArrayList<>();
//            camposModificados.add("color");
//            evento=crud.editarEvento(evento, camposModificados);
//            if(evento!=null)System.out.println("evento editado; "+evento);
//-----------------------------------------
            List<EntidadEvento> eventos=crud.obtenerEventos("unico");
            if(eventos!=null){
                for (EntidadEvento evento1 : eventos) {
                    System.out.println("nombre: "+evento1.getNombre());
                    System.out.println("tipo: "+evento1.getTipo().toString());
                }
            }else System.out.println("sin eventos");
//-----------------------------------------
        }catch(PersistenciaExceptionn e){
            System.out.println(e);
        }
        
////       
//        System.out.println("eeeee");
//        
//        if(Conexion.getDatabasee().getCollection("Eventos", EntidadEvento.class).insertOne(evento).getInsertedId()!=null){
//            System.out.println("se inserto");
//        }else
//            System.out.println("no se pudo");
        //CrudEvento crudEvento=new CrudEvento();
        
//        
//        try{
//            evento=crudEvento.agregarEvento(evento);
//            if(evento!=null)System.out.println(evento.getNombre());
//            else System.out.println("evento null");
//        }catch(PersistenciaExceptionn e){
//            System.out.println(e);
//        }
//       
////        EntidadUbicacion ubicacion=new EntidadUbicacion("AV-1100");
//        
//        try{
//            
////            List<EntidadCampus> campuses=crudCampus.obtenerTodosLosCampus();
////            
////            if(!campuses.isEmpty()) System.out.println(campuses.get(0));
////            else System.out.println("No hay campuses");
//            
//            entidadMaestro=crudMaestro.obtenerMaestro(entidadMaestro);
//            System.out.println(entidadMaestro.toString());
//            
////            ubicacion=crudCampus.obtenerUbi(ubicacion);
////            System.out.println(ubicacion.toString());
//            
//        }catch(PersistenciaExceptionn e){
//            System.out.println(e);
//        }
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
