package Insercion;

import entidades.CrudCampus;
import entidades.CrudMaestro;
import entidades.CrudUsuario;
import entidades.EntidadCampus;
import entidades.EntidadEvento;
import entidades.EntidadMaestro;
import entidades.EntidadTipoEventoEnum;
import entidades.EntidadUbicacion;
import entidades.EntidadUsuario;
import excepcioness.PersistenciaExceptionn;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
            CrudUsuario CRUDusuario=new CrudUsuario();

            EntidadCampus campusNainari = new EntidadCampus("Obregon Nainari");
            EntidadCampus campusCentro = new EntidadCampus("Centro");

            if ((campusNainari = CRUDcampus.agregarCampus(campusNainari)) != null) {
                //System.out.println("Se agrego el campus");
                //System.out.println("Id Campus nainari: " + campusNainari.getId());
            } else {
                System.out.println("No se agrego el campus");
            }

            if ((campusCentro = CRUDcampus.agregarCampus(campusCentro)) != null) {
                //System.out.println("Se agrego el campus");
                //System.out.println("Id Campus centro: " + campusCentro.getId());
            } else {
                System.out.println("No se agrego el campus");
            }
            
            EntidadUbicacion ubicacionNainari1 = new EntidadUbicacion("AV-1100", campusNainari.getId(), "Aula para clases regulares");
            ubicacionNainari1.setPosicionX(200D);
            ubicacionNainari1.setPosicionY(200D);
            
            EntidadUbicacion ubicacionNainari2 = new EntidadUbicacion("AV-1200", campusNainari.getId(), "Aula para clases regulares");
            ubicacionNainari2.setPosicionX(300D);
            ubicacionNainari2.setPosicionY(300D);
            
            EntidadUbicacion ubicacionObregon1 = new EntidadUbicacion("LV-100", campusCentro.getId(), "Aula para clases regulares");
            ubicacionObregon1.setPosicionX(100D);
            ubicacionObregon1.setPosicionY(100D);
            
            EntidadUbicacion ubicacionObregon2 = new EntidadUbicacion("LV-200", campusCentro.getId(), "Aula para clases regulares");
            ubicacionObregon2.setPosicionX(400D);
            ubicacionObregon2.setPosicionY(400D);
            
            EntidadMaestro maestro = new EntidadMaestro("1", "Juan Pérez", ubicacionNainari1, "Profesor de Matemáticas", "fotoMaestro.png");
            
            List<EntidadUbicacion> ubicacionesNainari = new ArrayList<>();
            ubicacionesNainari.add(ubicacionNainari1);
            ubicacionesNainari.add(ubicacionNainari2);
            campusNainari.setUbicaciones(ubicacionesNainari);
            
            List<EntidadUbicacion> ubicacionesCentro = new ArrayList<>();
            ubicacionesCentro.add(ubicacionObregon1);
            ubicacionesCentro.add(ubicacionObregon2);
            campusCentro.setUbicaciones(ubicacionesCentro);
            
            Calendar fechaInicio1 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            fechaInicio1.set(Calendar.DAY_OF_MONTH, 2);
            fechaInicio1.set(Calendar.MONTH, 4);
            fechaInicio1.set(Calendar.HOUR_OF_DAY, 10);
            fechaInicio1.set(Calendar.MINUTE, 30);
            
            EntidadEvento evento1 = new EntidadEvento("Asesoria de Algebra", "asesoria para examen de algebra", "AV-1100", "Rosa", fechaInicio1.getTime(), fechaInicio1.getTime(), 2.5, maestro.getIdConversion());
            Calendar f=Calendar.getInstance();
            f.setTime(evento1.getFechaInicio());
            
            //System.out.println(f.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US));
            evento1.setTipo(EntidadTipoEventoEnum.SEMANAL);
            evento1.setDiasSemana("0011100");
            evento1.setMaestro(maestro.getNombre());
            
            List<EntidadEvento> calendario=new ArrayList<>();
            calendario.add(evento1);
            maestro.setCalendario(calendario);
            
            //System.out.println(maestro);
            //System.out.println(evento1);
            //System.out.println(campusNainari);
            //System.out.println(ubicacionNainari1);
            
            System.out.println(campusNainari.getUbicaciones().get(0));
            
            if (CRUDcampus.editarCampus(campusNainari) != null) {
                System.out.println("Se agrego el campus");
            } else {
                System.out.println("No se agrego el campus");
            }

            if (CRUDcampus.editarCampus(campusCentro) != null) {
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

            EntidadUsuario usuario = new EntidadUsuario();

            usuario.setIdUsuario("1");
            usuario.setContraseña("pass123");
            usuario.setAdministrador(false);

            if (CRUDusuario.agregarUsuario(usuario) != null) {
                System.out.println("Se agrego el usuario");
            }

            EntidadUsuario usuarioAdmin = new EntidadUsuario();
            usuarioAdmin.setIdUsuario("23");
            usuarioAdmin.setContraseña("pass123");
            usuarioAdmin.setAdministrador(true);

            if (CRUDusuario.agregarUsuario(usuarioAdmin) != null) {
                System.out.println("Se agrego el usuario administrador");
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
