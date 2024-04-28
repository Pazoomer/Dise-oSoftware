package Insercion;

import entidades.CrudCampus;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author t1pas
 */
public class Insercion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            
            //PRIMERO BORRAR TODOS LOS DOCUMENTOS DE LA BASE DE DATOS
            
            CrudMaestro CRUDmaestro = new CrudMaestro();
            CrudCampus CRUDcampus = new CrudCampus();

            EntidadUbicacion ubicacion = new EntidadUbicacion("AV-1100", "Campus Principal", "Aula para clases regulares");
            
            EntidadMaestro maestro = new EntidadMaestro("1", "Juan Pérez", ubicacion, "Profesor de Matemáticas", "fotoMaestroG.jpg");

            EntidadCampus campusNainari = new EntidadCampus("Obregon Nainari");
            EntidadCampus campusCentro = new EntidadCampus("Centro");

            List<EntidadUbicacion> ubicacionesNainari = new ArrayList<>();
            ubicacionesNainari.add(ubicacion);
            campusNainari.setUbicaciones(ubicacionesNainari);
            
            List<EntidadUbicacion> ubicacionesCentro = new ArrayList<>();
            ubicacionesCentro.add(ubicacion);
            campusCentro.setUbicaciones(ubicacionesCentro);
            
            Calendar fechaInicio1 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            fechaInicio1.set(Calendar.DAY_OF_MONTH, 2);
            fechaInicio1.set(Calendar.MONTH, 4);
            fechaInicio1.set(Calendar.HOUR_OF_DAY, 10);
            fechaInicio1.set(Calendar.MINUTE, 30);
            EntidadEvento evento1 = new EntidadEvento("Asesoria de Algebra", "asesoria para examen de algebra", "AV-1100", "Rosa", fechaInicio1.getTime(), fechaInicio1.getTime(), 2.5, maestro.getIdConversion());
            Calendar f=Calendar.getInstance();
            f.setTime(evento1.getFechaInicio());
            
            System.out.println(f.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US));
            evento1.setTipo(EntidadTipoEventoEnum.SEMANAL);
            evento1.setDiasSemana("0011100");
            evento1.setMaestro(maestro.getNombre());
            
            List<EntidadEvento> calendario=new ArrayList<>();
            calendario.add(evento1);
            maestro.setCalendario(calendario);
            
            System.out.println(maestro);
            System.out.println(evento1);
            System.out.println(campusNainari);
            System.out.println(ubicacion);
            
            if (CRUDcampus.agregarCampus(campusNainari) != null) {
                System.out.println("Se agrego el campus");
            } else {
                System.out.println("No se agrego el campus");
            }
            
            if (CRUDcampus.agregarCampus(campusCentro) != null) {
                System.out.println("Se agrego el campus");
            } else {
                System.out.println("No se agrego el campus");
            }
            
            if (CRUDmaestro.agregarMaestro(maestro) != null) {
                System.out.println("Se agrego el maestro");
            } else {
                System.out.println("No se agrego el maestro");
            }

            if (CRUDmaestro.cerrarConexion()) {
                System.out.println("Se cerro la conexion");
            } else {
                System.out.println("No se cerro la conexion");
            }
            
            if (CRUDcampus.cerrarConexion()) {
                System.out.println("Se cerro la conexion");
            } else {
                System.out.println("No se cerro la conexion");
            }

        } catch (PersistenciaExceptionn ex) {
            Logger.getLogger(Insercion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
