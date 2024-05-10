
package objetosNegocio;

import DTOS.campus.CampusConsultableDTO;
import DTOS.campus.UbicacionDTO;
import DTOS.evento.EventoConsultableDTO;
import DTOS.maestro.MaestroEditableDTO;
import entidades.CrudCampus;
import entidades.CrudMaestro;
import entidades.EntidadCampus;
import entidades.EntidadEvento;
import entidades.EntidadMaestro;
import entidades.EntidadTipoEventoEnum;
import entidades.EntidadUbicacion;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import objetosNegocio.Conversiones;
import org.bson.types.ObjectId;

/**
 *
 * @author t1pas
 */
public class PruebaConversiones {

    public static void main(String[] args) {
/*
        Conversiones conversiones = new Conversiones();

        EntidadUbicacion ubicacion = new EntidadUbicacion("AV-1100", "Campus Principal", "Aula para clases regulares");
        ubicacion.setId(new ObjectId("662e839aecc0c87a0fbe5e90"));

        EntidadMaestro maestro = new EntidadMaestro(true,"1", "Juan Pérez", ubicacion, "Profesor de Matemáticas", "fotoMaestroG.jpg");
        maestro.setId(new ObjectId("662e839aecc0c87a0fbe5e90"));

        EntidadCampus campus = new EntidadCampus("Campus Principal");
        campus.setId(new ObjectId("662e839aecc0c87a0fbe5e90"));

        List<EntidadUbicacion> ubicaciones = new ArrayList<>();
        ubicaciones.add(ubicacion);
        campus.setUbicaciones(ubicaciones);

        Calendar fechaInicio = Calendar.getInstance();
        fechaInicio.set(Calendar.DAY_OF_MONTH, 2);
        fechaInicio.set(Calendar.MONTH, 4);
        fechaInicio.set(Calendar.HOUR_OF_DAY, 10);
        fechaInicio.set(Calendar.MINUTE, 0);
        EntidadUbicacion ubi=new EntidadUbicacion("AV-1100");
        EntidadEvento evento = new EntidadEvento("Asesoria de Algebra", "asesoria para examen de algebra", ubi, "Rosa", fechaInicio.getTime(), fechaInicio.getTime(), 2.5, maestro.getIdConversion());
        evento.setTipo(EntidadTipoEventoEnum.SEMANAL);
        evento.setDiasSemana("0011100");
        evento.setMaestro(maestro.getNombre());
        evento.setId(new ObjectId("662e839aecc0c87a0fbe5e90"));

        List<EntidadEvento> calendario = new ArrayList<>();
        calendario.add(evento);
        maestro.setCalendario(calendario);

        CampusConsultableDTO campusDTO = conversiones.toCampusDTO(campus);
        System.out.println("CampusDTO: " + campusDTO);

        UbicacionDTO ubicacionDTO = conversiones.toUbicacionDTO(ubicacion);
        System.out.println("UbicacionDTO: " + ubicacionDTO);

        MaestroEditableDTO maestroDTO = conversiones.toMaestroDTO(maestro);
        System.out.println("MaestroDTO: " + maestroDTO);

        EventoConsultableDTO eventoDTO = conversiones.toEventoDTO(evento);
        eventoDTO.setMaestro(maestroDTO);
        System.out.println("EventoDTO: " + eventoDTO);

        EntidadCampus entidadCampus = conversiones.toCampusBO(campusDTO);
        System.out.println("CampusBO: " + entidadCampus);
        System.out.println("CampusAntiguo: " + campus);

        EntidadUbicacion entidadUbicacion = conversiones.toUbicacionBO(ubicacionDTO);
        System.out.println("ubicacionBO: " + entidadUbicacion);
        System.out.println("UbicacionAntigua: " + ubicacion);

        EntidadMaestro entidadMaestro = conversiones.toMaestroBO(maestroDTO);
        System.out.println("MaestroBO: " + entidadMaestro);
        System.out.println("MaestroAntiguo: " + maestro);

        EntidadEvento entidadEvento = conversiones.toEventoBO(eventoDTO);
        System.out.println("EventoBO: " + entidadEvento);
        System.out.println("EventoAntiguo: " + evento);

        if (campus.equals(entidadCampus)) {
            System.out.println("Campus es igual");
        } else {
            System.out.println("Campus no es igual");
        }

        if (ubicacion.equals(entidadUbicacion)) {
            System.out.println("Ubicacion es igual");
        } else {
            System.out.println("Ubicacion no es igual");
        }

        if (maestro.equals(entidadMaestro)) {
            System.out.println("Maestro es igual");
        } else {
            System.out.println("Maestro no es igual");
        }

        if (evento.equals(entidadEvento)) {
            System.out.println("Evento es igual");
        } else {
            System.out.println("Evento no es igual");
        }
*/
    }
    
}
