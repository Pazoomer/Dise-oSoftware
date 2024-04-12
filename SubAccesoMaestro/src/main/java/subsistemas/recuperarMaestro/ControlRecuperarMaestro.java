
package subsistemas.recuperarMaestro;

import DTOS.evento.EventoConsultableDTO;
import DTOS.evento.TipoEventoEnumDTO;
import DTOS.maestro.MaestroEditableDTO;
import excepciones.NegocioException;
import java.awt.Color;
import java.awt.Image;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import objetosNegocio.Evento;
import objetosNegocio.Maestro;
import objetosNegocio.TipoEventoEnum;

/**
 *
 * @author t1pas
 */
public class ControlRecuperarMaestro {
    //TODO
    //Solo soy una fachada
    
    private Maestro toMaestroBO(MaestroEditableDTO maestro){
        List<EventoConsultableDTO> eventos=maestro.getCalendario();
        List<Evento> eventosBO=new ArrayList<>();
        if(!eventos.isEmpty()){
            for(EventoConsultableDTO ec:eventos){
                eventosBO.add(toEventoBO(ec));
            }
        }
        Maestro maestroConvertido=new Maestro(
                1L, 
                maestro.getNombre(),
                maestro.getCubiculo(), 
                maestro.getDescripcion(), 
                maestro.getFoto(),
                eventosBO
        );
        return maestroConvertido;
    }
    
    private MaestroEditableDTO toMaestroDTO(Maestro maestro){
        List<Evento> eventos=maestro.getCalendario();
        List<EventoConsultableDTO> eventosDTO=new ArrayList<>();
        if(!eventos.isEmpty()){
            for(Evento ec:eventos){
                eventosDTO.add(toEventoDTO(ec)
                );
            }
        }
        MaestroEditableDTO maestroDTO=new MaestroEditableDTO(
                maestro.getNombre(),
                maestro.getCubiculo(),
                maestro.getDescripcion(), 
                maestro.getFoto(), 
                eventosDTO
        );
        return maestroDTO;
    }
    
    private Evento toEventoBO(EventoConsultableDTO evento){
        Evento eventoConvertido = null;
        switch (evento.getTipo()) {
            case UNICO_UN_DIA ->
                eventoConvertido = new Evento(evento.getColor(),
                        evento.getNombre(),
                        evento.getDescripcion(),
                        evento.getFechaInicio(),
                        evento.getUbicacion(),
                        evento.getHoraInicio(),
                        evento.getHorasDuracionEvento()
                );
            case UNICO_VARIOS_DIAS ->
                eventoConvertido = new Evento(
                        evento.getColor(),
                        TipoEventoEnum.UNICO_VARIOS_DIAS,
                        evento.getNombre(),
                        evento.getDescripcion(),
                        evento.getDiasSemana(),
                        evento.getUbicacion(),
                        evento.getFechaInicio(),
                        evento.getFechaFin(),
                        evento.getHoraInicio(),
                        evento.getHorasDuracionEvento()
                );
            case SEMANAL ->
                eventoConvertido = new Evento(
                        evento.getColor(),
                        TipoEventoEnum.SEMANAL,
                        evento.getNombre(),
                        evento.getDescripcion(),
                        evento.getDiasSemana(),
                        evento.getUbicacion(),
                        evento.getFechaInicio(),
                        evento.getFechaFin(),
                        evento.getHoraInicio(),
                        evento.getHorasDuracionEvento()
                );
        }
        return eventoConvertido;
    }
    
    private EventoConsultableDTO toEventoDTO(Evento evento){
        EventoConsultableDTO eventoConvertido = null;
        switch (evento.getTipo()) {
            case UNICO_UN_DIA ->
                eventoConvertido = new EventoConsultableDTO(
                        evento.getNombre(),
                        evento.getDescripcion(),
                        evento.getColor(),
                        evento.getUbicacion(),
                        evento.getFechaInicio(),
                        evento.getHoraInicio(),
                        evento.getHorasDuracionEvento()
                );
            case UNICO_VARIOS_DIAS ->
                eventoConvertido = new EventoConsultableDTO(
                        TipoEventoEnumDTO.UNICO_VARIOS_DIAS,
                        evento.getNombre(),
                        evento.getDescripcion(),
                        evento.getColor(),
                        evento.getDiasSemana(),
                        evento.getUbicacion(),
                        evento.getFechaInicio(),
                        evento.getFechaFin(),
                        evento.getHoraInicio(),
                        evento.getHorasDuracionEvento()
                );
            case SEMANAL ->
                eventoConvertido = new EventoConsultableDTO(
                        TipoEventoEnumDTO.SEMANAL,
                        evento.getNombre(),
                        evento.getDescripcion(),
                        evento.getColor(),
                        evento.getDiasSemana(),
                        evento.getUbicacion(),
                        evento.getFechaInicio(),
                        evento.getFechaFin(),
                        evento.getHoraInicio(),
                        evento.getHorasDuracionEvento()
                );
        }
        return eventoConvertido;
    }
    
   
    protected MaestroEditableDTO recuperarMaestro2(MaestroEditableDTO maestro)
            throws NegocioException{
        List<String> colores=new ArrayList<>();
        for(EventoConsultableDTO ec: maestro.getCalendario()){
            colores.add(ec.getColor());
        }
        Maestro maestroBO=toMaestroBO(maestro);
        maestroBO=maestroBO.obtenerMaestro(maestroBO);
        MaestroEditableDTO maestroDTO=toMaestroDTO(maestroBO);
        return maestroDTO;
    }
    
    protected MaestroEditableDTO recuperarMaestro() throws NegocioException{
        List<EventoConsultableDTO> calendario = new ArrayList<>();
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY, 10);
//        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
//        calendar.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);

        Calendar fecha=Calendar.getInstance();
        fecha.set(2024, 2, 17);
        Calendar fecha2=Calendar.getInstance();
        fecha2.set(2024, 2, 20);
        
        String diasSemana = "0101010";
        Calendar horaInicio = Calendar.getInstance();
        horaInicio.set(Calendar.HOUR_OF_DAY, 10);
        horaInicio.set(Calendar.MINUTE, 30);
        EventoConsultableDTO ev1 = new EventoConsultableDTO(TipoEventoEnumDTO.SEMANAL, "diseño de software", "clase de diseño","amarillo",
                diasSemana, "1826", fecha2,fecha2, horaInicio, 2.5f);
        calendario.add(ev1);
        
        String rutaRelativa = "fotoMaestro.png";

        ImageIcon icon = new ImageIcon(rutaRelativa);

        ImageIcon scaledIcon = new ImageIcon(icon.getImage().getScaledInstance(120, 100, Image.SCALE_SMOOTH));

        MaestroEditableDTO maestro = new MaestroEditableDTO(1L, "Gibran Duran", "AV0900", "Doy asesorias de 9 a 11 de bases de datos los sabados y domingos", rutaRelativa, calendario);

        return maestro;
    }
}
