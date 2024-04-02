
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
import javax.swing.ImageIcon;

/**
 *
 * @author t1pas
 */
public class Maestros {
    //TODO
    //Solo soy una fachada
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
        
        List<Integer> diasSemana = new ArrayList<>();
        diasSemana.add(2);
        diasSemana.add(4);
        diasSemana.add(6);
        LocalTime horaInicio = LocalTime.of(10, 0);
        EventoConsultableDTO ev1 = new EventoConsultableDTO(TipoEventoEnumDTO.SEMANAL, "diseño de software", "clase de diseño", Color.yellow,
                diasSemana, "1826", fecha2,fecha2, horaInicio, 2.5f);
        calendario.add(ev1);
        
        String rutaRealtiva = "fotoMaestro.png";

        ImageIcon icon = new ImageIcon(rutaRealtiva);

        ImageIcon scaledIcon = new ImageIcon(icon.getImage().getScaledInstance(120, 100, Image.SCALE_SMOOTH));

        MaestroEditableDTO maestro = new MaestroEditableDTO(1L, "Gibran Duran", "AV0900", "Doy asesorias de 9 a 11 de bases de datos los sabados y domingos", scaledIcon, calendario);

        return maestro;
    }
}
