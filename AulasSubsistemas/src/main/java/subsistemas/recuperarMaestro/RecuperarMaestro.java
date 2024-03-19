
package subsistemas.recuperarMaestro;

import DTOS.evento.EventoConsultableDTO;
import DTOS.maestro.MaestroEditableDTO;
import excepciones.PersistenciaException;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.ImageIcon;

/**
 *
 * @author t1pas
 */
public class RecuperarMaestro implements IRecuperarMaestro {

    //TODO
    //Solo soy una fachada
    @Override
    public MaestroEditableDTO recuperarMaestro() throws PersistenciaException {
        List<EventoConsultableDTO> calendario = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);

        String rutaRealtiva = "fotoMaestro.png";

        ImageIcon icon = new ImageIcon(rutaRealtiva);

        ImageIcon scaledIcon = new ImageIcon(icon.getImage().getScaledInstance(120, 100, Image.SCALE_SMOOTH));

        //EventoConsultableDTO evento = new EventoConsultableDTO("semanal", "Bases de datos", "...", Color.BLUE,null, null, calendar);

        //calendario.add(evento);

        MaestroEditableDTO maestro = new MaestroEditableDTO(1L, "Gibran Duran", "AV0900", "Doy asesorias de 9 a 11 de bases de datos los sabados y domingos", scaledIcon, calendario);

        return maestro;
    }

}
