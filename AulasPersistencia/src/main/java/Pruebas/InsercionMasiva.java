package Pruebas;

import entidades.CrudEvento;
import entidades.CrudMaestro;
import entidades.CrudUbicacion;
import entidades.EntidadCampus;
import entidades.EntidadEvento;
import entidades.EntidadMaestro;
import static entidades.EntidadTipoEventoEnum.SEMANAL;
import entidades.EntidadUbicacion;
import excepcioness.PersistenciaExceptionn;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author t1pas
 */
public class InsercionMasiva {

    

    public static void main(String[] args) throws PersistenciaExceptionn {

        CrudMaestro CRUDmaestro=new CrudMaestro();
        CrudUbicacion CRUDubicacion=new CrudUbicacion();
        CrudEvento CRUDevento=new CrudEvento();

        // Crear instancias de las entidades
        EntidadMaestro maestro = new EntidadMaestro(1L, "Juan Pérez", new EntidadUbicacion("Cubículo 101"), "Profesor de Matemáticas", "foto_maestro.jpg");
        EntidadEvento evento = new EntidadEvento(SEMANAL, "Clase de Cálculo", "Clase de repaso de cálculo", "Lunes", new EntidadUbicacion("Aula 201"), "Azul", null, null, null, 2.5, maestro);
        EntidadUbicacion ubicacion = new EntidadUbicacion("Aula 201", new EntidadCampus("Campus Principal"), "Aula para clases regulares");
        EntidadCampus campus = new EntidadCampus("Campus Principal");

        List<EntidadUbicacion> ubicaciones=new ArrayList<>();
        ubicaciones.add(ubicacion);
        
        // Agregar la ubicación al campus
        campus.setUbicaciones(ubicaciones);
        
        List<EntidadEvento> calendario=new ArrayList<>();
        calendario.add(evento);

        // Agregar el evento al calendario del maestro
        maestro.setCalendario(calendario);
        
        CRUDmaestro.agregarMaestro(maestro);
        
        CRUDubicacion.crearUbicacion(ubicacion);
    }

}
