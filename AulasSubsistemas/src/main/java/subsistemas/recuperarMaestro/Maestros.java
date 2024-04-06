
package subsistemas.recuperarMaestro;

import DTOS.evento.EventoConsultableDTO;
import static DTOS.evento.TipoEventoEnumDTO.SEMANAL;
import static DTOS.evento.TipoEventoEnumDTO.UNICO_UN_DIA;
import static DTOS.evento.TipoEventoEnumDTO.UNICO_VARIOS_DIAS;
import DTOS.maestro.MaestroEditableDTO;
import conexion.IConexionDAO;
import excepciones.NegocioException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import objetosNegocio.Evento;
import objetosNegocio.Maestro;

/**
 *
 * @author t1pas
 */
public class Maestros {
    
    private final IConexionDAO conexion;

    public Maestros(IConexionDAO conexion) {
        this.conexion = conexion;
    }
    //TODO
    //Solo soy una fachada
    protected MaestroEditableDTO recuperarMaestro() throws NegocioException{
        EntityManager entityManager = null;
        try {
            entityManager = conexion.crearConexion();
            // Consulta JPQL para obtener el primer maestro de la base de datos
            Maestro maestro = entityManager.createQuery("SELECT m FROM Maestro m", Maestro.class)
                                          .setMaxResults(1)
                                          .getSingleResult();
            if (maestro==null) {
                return null;
            }
            return convertirAMaestroEditableDTO(maestro);
        } catch (Exception e) {
            throw new NegocioException("Error al recuperar el primer maestro", e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
    
    // Método auxiliar para convertir un Maestro a un MaestroEditableDTO
    private MaestroEditableDTO convertirAMaestroEditableDTO(Maestro maestro) {
        MaestroEditableDTO maestroDTO = new MaestroEditableDTO(
                maestro.getIdMaestro(),
                maestro.getNombre(),
                maestro.getCubiculo(),
                maestro.getDescripcion(),
                maestro.getFoto(),
                convertirAEventoEditableDTO(maestro.getCalendario())
        );
        return maestroDTO;
    }

    private List<EventoConsultableDTO> convertirAEventoEditableDTO(List<Evento> eventos) {
        List<EventoConsultableDTO> eventosConsultables=new ArrayList<>();
        for (Evento evento : eventos) {
            EventoConsultableDTO eventoAux = null;
            switch (evento.getTipo()) {
                case SEMANAL -> {

                    eventoAux = new EventoConsultableDTO(
                            evento.getTipo(),
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
                case UNICO_UN_DIA -> {
                    eventoAux = new EventoConsultableDTO(
                            evento.getNombre(),
                            evento.getDescripcion(),
                            evento.getColor(),
                            evento.getUbicacion(),
                            evento.getFechaInicio(),
                            evento.getHoraInicio(),
                            evento.getHorasDuracionEvento()
                    );
                }
                case UNICO_VARIOS_DIAS -> {

                    eventoAux = new EventoConsultableDTO(
                            evento.getTipo(),
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
                
            }
            eventosConsultables.add(eventoAux);
        }

        return eventosConsultables;
    }
}
