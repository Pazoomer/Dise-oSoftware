package Pruebas;

import entidades.CrudCampus;
import entidades.CrudMaestro;
import entidades.EntidadCampus;
import entidades.EntidadEvento;
import entidades.EntidadMaestro;
import entidades.EntidadUbicacion;
import excepcioness.PersistenciaExceptionn;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author t1pas
 */
public class PruebaInsertar {

    

    public static void main(String[] args) throws PersistenciaExceptionn {

        CrudMaestro CRUDmaestro=new CrudMaestro();
        CrudCampus CRUDcampus=new CrudCampus();
//
//        // Crear instancias de las entidades
        EntidadMaestro maestro = new EntidadMaestro("1", "Juan Pérez", new EntidadUbicacion("Cubículo 101"), "Profesor de Matemáticas", "foto_maestro.jpg");
//        EntidadEvento evento = new EntidadEvento(SEMANAL, "Clase de Cálculo", "Clase de repaso de cálculo", "Lu", "Aula 201", "Azul", null, null, null, 2.5, maestro.getIdConversion());

        EntidadCampus campus = new EntidadCampus("Campus Principal");
        EntidadUbicacion ubicacion = new EntidadUbicacion("AV-1100", "Campus Principal", "Aula para clases regulares");
        
        List<EntidadUbicacion> ubicaciones=new ArrayList<>();
        ubicaciones.add(ubicacion);
        campus.setUbicaciones(ubicaciones);
        
        if(CRUDcampus.agregarCampus(campus)!=null) System.out.println("Se agrego el campus");
        else System.out.println("No se agrego el campus");
        CRUDcampus.cerrarConexion();

        Calendar fechaInicio=Calendar.getInstance();
        fechaInicio.set(Calendar.DAY_OF_MONTH, 2);
        fechaInicio.set(Calendar.MONTH, 4);
        fechaInicio.set(Calendar.HOUR_OF_DAY, 10);
        fechaInicio.set(Calendar.MINUTE, 0);
        EntidadEvento evento2 = new EntidadEvento("Asesoria de Algebra", "asesoria para examen de algebra", "Aula 210", "Rosa", fechaInicio.getTime(), fechaInicio.getTime(), 2.5, maestro.getIdConversion());
        
        if(CRUDmaestro.agregarMaestro(maestro)!=null) System.out.println("Se agrego el maestro");
        else System.out.println("No se agrego el maestro");
        
        if(CRUDmaestro.agregarEventoCalendario(maestro, evento2)) System.out.println("Se agrego el evento");
        else System.out.println("No se agrego el evento");
        
        if(CRUDmaestro.cerrarConexion()) System.out.println("Se cerro la conexion");
        else System.out.println("No se cerro la conexion");
        
       
    }

}
