package Insercion;

import entidades.CrudCampus;
import entidades.CrudEvento;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.types.ObjectId;

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
            
            //CREAR LOS CRUDS
            CrudMaestro CRUDmaestro = new CrudMaestro();
            CrudCampus CRUDcampus = new CrudCampus();
            CrudUsuario CRUDusuario=new CrudUsuario();
            CrudEvento CRUDevento=new CrudEvento();

            //CREAR LOS CAMPUS
            EntidadCampus campusNainari = new EntidadCampus("Obregon Nainari");
            campusNainari.setUrl("https://itson.mx/eventos/olimpianeic/PublishingImages/Paginas/mapa/croquis-nainari.jpg");
            EntidadCampus campusCentro = new EntidadCampus("Centro");
            campusCentro.setUrl("https://itson.mx/universidad/PublishingImages/mapas-campus/campus-centro.jpg");
            //AGREGAR LOS CAMPUS
            if ((campusNainari = CRUDcampus.agregarCampus(campusNainari)) != null) {
            } else {
                System.out.println("No se agrego el campus");
            }
            if ((campusCentro = CRUDcampus.agregarCampus(campusCentro)) != null) {
            } else {
                System.out.println("No se agrego el campus");
            }
              
            //CREAR LAS UBICACIONES
            EntidadUbicacion ubicacionNainari1 = new EntidadUbicacion("AV-1100", campusNainari.getId(), "Aula para clases regulares");
            ubicacionNainari1.setPosicionX(200D);
            ubicacionNainari1.setPosicionY(200D);
            ObjectId id=new ObjectId();
            ubicacionNainari1.setId(id);
            
            EntidadUbicacion ubicacionNainari2 = new EntidadUbicacion("AV-1200", campusNainari.getId(), "Aula para clases regulares");
            ubicacionNainari2.setPosicionX(300D);
            ubicacionNainari2.setPosicionY(300D);
            
            EntidadUbicacion ubicacionObregon1 = new EntidadUbicacion("LV-100", campusCentro.getId(), "Aula para clases regulares");
            ubicacionObregon1.setPosicionX(100D);
            ubicacionObregon1.setPosicionY(100D);
            
            EntidadUbicacion ubicacionObregon2 = new EntidadUbicacion("LV-200", campusCentro.getId(), "Aula para clases regulares");
            ubicacionObregon2.setPosicionX(400D);
            ubicacionObregon2.setPosicionY(400D);
            
            //CREAR EL MAESTRO
            EntidadMaestro maestro = new EntidadMaestro("1", "Juan Pérez", ubicacionNainari1, "Profesor de Matemáticas", "fotoMaestro.png");
            
            //AÑADIR LAS UBICACIONES AL CAMPUS
            List<EntidadUbicacion> ubicacionesNainari = new ArrayList<>();
            ubicacionesNainari.add(ubicacionNainari1);
            ubicacionesNainari.add(ubicacionNainari2);
            campusNainari.setUbicaciones(ubicacionesNainari);
            
            List<EntidadUbicacion> ubicacionesCentro = new ArrayList<>();
            ubicacionesCentro.add(ubicacionObregon1);
            ubicacionesCentro.add(ubicacionObregon2);
            campusCentro.setUbicaciones(ubicacionesCentro);
            
            //CREAR LAS FECHAS
            Calendar fechaInicio1 = Calendar.getInstance();
            fechaInicio1.set(Calendar.DAY_OF_MONTH, 2);
            fechaInicio1.set(Calendar.MONTH, 4);
            fechaInicio1.set(Calendar.HOUR_OF_DAY, 10);
            fechaInicio1.set(Calendar.MINUTE, 30);
            
            //CREAR EL EVENTO
            EntidadEvento evento1 = new EntidadEvento("Asesoria de Algebra", "asesoria para examen de algebra", null, "Rosa", fechaInicio1.getTime(), fechaInicio1.getTime(), 2.5, maestro.ggetIdConversion());
            Calendar f=Calendar.getInstance();
            f.setTime(evento1.getFechaInicio());
            
            evento1.setTipo(EntidadTipoEventoEnum.SEMANAL);
            evento1.setDiasSemana("Lu,Mi,Vi");
            evento1.setMaestro(maestro.getNombre());
            evento1.setUbicacion(id);
            
            //AGREGAR EL EVENTO AL MAESTRO
            List<EntidadEvento> calendario=new ArrayList<>();
            calendario.add(evento1);
            maestro.setCalendario(calendario);
            
            //CREA LISTA DE EVENTOS PARA UBICACIONES
            EntidadEvento eventoUbicacion1 = new EntidadEvento();
            eventoUbicacion1.setColor("Red");
            eventoUbicacion1.setDescripcion("Descripcion");
            eventoUbicacion1.setDiasSemana("Lu,Ma");
            eventoUbicacion1.setFechaFin(fechaInicio1.getTime());
            eventoUbicacion1.setFechaInicio(fechaInicio1.getTime());
            eventoUbicacion1.setHoraInicio(fechaInicio1.getTime());
            eventoUbicacion1.setHorasDuracionEvento(2.5);
            eventoUbicacion1.setNombre("Nombre");
            eventoUbicacion1.setTipo(EntidadTipoEventoEnum.SEMANAL);
            eventoUbicacion1.setUbicacion(id);
            eventoUbicacion1.setIdCampus(campusNainari.getId());
            
            EntidadEvento eventoUbicacion2 = new EntidadEvento();
            eventoUbicacion2.setColor("Red");
            eventoUbicacion2.setDescripcion("Descripcion");
            eventoUbicacion2.setDiasSemana("Lu,Ma");
            eventoUbicacion2.setFechaFin(fechaInicio1.getTime());
            eventoUbicacion2.setFechaInicio(fechaInicio1.getTime());
            eventoUbicacion2.setHoraInicio(fechaInicio1.getTime());
            eventoUbicacion2.setHorasDuracionEvento(2.5);
            eventoUbicacion2.setNombre("Nombre");
            eventoUbicacion2.setTipo(EntidadTipoEventoEnum.SEMANAL);
            eventoUbicacion2.setUbicacion(id);
            eventoUbicacion2.setIdCampus(campusNainari.getId());

            //AÑADIR LOS EVENTOS A LA UBICACION
//            List<EntidadEvento> eventosUbicaciones = new ArrayList<>();
//            eventosUbicaciones.add(eventoUbicacion1);
//            eventosUbicaciones.add(eventoUbicacion2);
//            ubicacionNainari1.setEventos(eventosUbicaciones);

            //AÑADIR LOS EVENTOS A LA BASE DE DATOS
            if (CRUDevento.agregarEvento(eventoUbicacion1) != null) {
                System.out.println("Se agrego el evento");
            } else {
                System.out.println("No se agrego el evento");
            }
            if (CRUDevento.agregarEvento(eventoUbicacion2) != null) {
                System.out.println("Se agrego el evento");
            } else {
                System.out.println("No se agrego el evento");
            }
            
            //EDITAR LOS CAMPUS CON LAS UBICACIONES
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
    
            //AGREGAR EL MAESTRO
            if (CRUDmaestro.agregarMaestro(maestro) != null) {
                System.out.println("Se agrego el maestro");
            } else {
                System.out.println("No se agrego el maestro");
            }

            //CREAR EL USUARIO MAESTRO
            EntidadUsuario usuario = new EntidadUsuario();
            usuario.setIdUsuario("1");
            usuario.setContraseña("pass123");
            usuario.setAdministrador(false);

            //AGREGAR EL USUARIO MAESTRO
            if (CRUDusuario.agregarUsuario(usuario) != null) {
                System.out.println("Se agrego el usuario");
            }

            //CREAR EL USUARIO ADMIN
            EntidadUsuario usuarioAdmin = new EntidadUsuario();
            usuarioAdmin.setIdUsuario("23");
            usuarioAdmin.setContraseña("pass123");
            usuarioAdmin.setAdministrador(true);

            //AGREGAR EL USUARIO ADMIN
            if (CRUDusuario.agregarUsuario(usuarioAdmin) != null) {
                System.out.println("Se agrego el usuario administrador");
            }

            //CERRAR CONEXION USUARIO
            if (CRUDusuario.cerrarConexion()) {
                System.out.println("Se cerro la conexion");
            } else {
                System.out.println("No se cerro la conexion");
            }

        } catch (PersistenciaExceptionn ex) {
            Logger.getLogger(Insercion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
