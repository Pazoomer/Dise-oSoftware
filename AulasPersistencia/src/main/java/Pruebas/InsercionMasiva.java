package Pruebas;

import entidades.Conexion;
import entidades.CrudCampus;
import entidades.CrudMaestro;
import entidades.EntidadCampus;
import entidades.EntidadEvento;
import entidades.EntidadMaestro;
import entidades.EntidadTipoEventoEnum;
import static entidades.EntidadTipoEventoEnum.SEMANAL;
import entidades.EntidadUbicacion;
import entidades.IConexion;
import excepcioness.PersistenciaExceptionn;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author t1pas
 */
public class InsercionMasiva {

    

    public static void main(String[] args) throws PersistenciaExceptionn {

        CrudMaestro CRUDmaestro=new CrudMaestro();
//        CrudCampus CRUDcampus=new CrudCampus();
//
//        // Crear instancias de las entidades
        EntidadMaestro maestro = new EntidadMaestro(1L, "Juan Pérez", new EntidadUbicacion("Cubículo 101"), "Profesor de Matemáticas", "foto_maestro.jpg");
//        EntidadEvento evento = new EntidadEvento(SEMANAL, "Clase de Cálculo", "Clase de repaso de cálculo", "Lu", "Aula 201", "Azul", null, null, null, 2.5, maestro.getIdConversion());
//        EntidadUbicacion ubicacion = new EntidadUbicacion("Aula 201", "Campus Principal", "Aula para clases regulares");
//        EntidadCampus campus = new EntidadCampus("Campus Principal");
//
//        List<EntidadUbicacion> ubicaciones=new ArrayList<>();
//        ubicaciones.add(ubicacion);
//        
//        // Agregar la ubicación al campus
//        campus.setUbicaciones(ubicaciones);
//        
//        List<EntidadEvento> calendario=new ArrayList<>();
//        calendario.add(evento);
//
//        // Agregar el evento al calendario del maestro
//        maestro.setCalendario(calendario);
//        
//        CRUDmaestro.agregarMaestro(maestro);
//        CRUDcampus.agregarCampus(campus);
//        
        Calendar fechaInicio=Calendar.getInstance();
        fechaInicio.set(Calendar.DAY_OF_MONTH, 2);
        fechaInicio.set(Calendar.MONTH, 4);
        fechaInicio.set(Calendar.HOUR_OF_DAY, 10);
        fechaInicio.set(Calendar.MINUTE, 0);
        EntidadEvento evento2 = new EntidadEvento("Asesoria de Algebra", "asesoria para examen de algebra", "Aula 210", "Rosa", fechaInicio.getTime(), fechaInicio.getTime(), 2.5, maestro.getIdConversion());
        
        if(CRUDmaestro.agregarEventoCalendario(maestro, evento2)) System.out.println("se agrego");
        else System.out.println("no se agrego");
    }

}
