
package subsistemas.accesoCalendario;

import DTOS.evento.EventoConsultableDTO;
import conexion.IConexionDAO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import objetosNegocio.Evento;
import objetosNegocio.Maestro;

/**
 *
 * @author t1pas
 */
class ControlCalendarios {
    
    private final IConexionDAO conexion;

    public ControlCalendarios(IConexionDAO conexion) {
        this.conexion = conexion;
    }
    
    public boolean editarCalendario(List<EventoConsultableDTO> calendario) {
        EntityManager entityManager=conexion.crearConexion();
        try {
            entityManager.getTransaction().begin();

            // Obtener el primer maestro de la base de datos
            Maestro maestro = entityManager.createQuery("SELECT m FROM Maestro m", Maestro.class)
                    .setMaxResults(1)
                    .getSingleResult();

            // Asociar la lista de eventos al maestro
            List<Evento> eventos = new ArrayList<>();
            for (EventoConsultableDTO eventoDTO : calendario) {
                Evento evento = new Evento(eventoDTO.getTipo(), eventoDTO.getNombre(), eventoDTO.getDescripcion(), eventoDTO.getDiasSemana(), eventoDTO.getUbicacion(), eventoDTO.getFechaInicio(), eventoDTO.getFechaFin(), eventoDTO.getHoraInicio(), eventoDTO.getHorasDuracionEvento(),maestro);

                // Configurar el evento con los datos del DTO (o como sea necesario)
                eventos.add(evento);
            }
            maestro.setCalendario(eventos);

            // Completar la transacción
            entityManager.getTransaction().commit();
            
            return true;
        } catch (Exception e) {
            // Manejar cualquier excepción y hacer rollback en caso de error
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
    
    
//        //TODO
//        //Solo soy una fachada
//    protected List<EventoConsultableDTO> editarCalendario(List<EventoConsultableDTO> calendario, EventoConsultableDTO evento,
//            String tipoOperacion) throws NegocioException {
//        List<EventoConsultableDTO> calendarioEditado=null;
//        switch(tipoOperacion){
//            case "agregar" -> {
//                try{
//                    calendarioEditado=agregarEvento(calendario, evento);
//                }catch(NegocioException e){
//                    throw new NegocioException(e.getMessage());
//                }
//            }
//            case "editar" -> {
//                try{
//                    calendarioEditado=editarEvento(calendario, evento);
//                }catch(NegocioException e){
//                    throw new NegocioException(e.getMessage());
//                }
//            }
//            case "eliminar" -> {
//                try{
//                    calendarioEditado=eliminarEvento(calendario, evento);
//                }catch(NegocioException e){
//                    throw new NegocioException(e.getMessage());
//                }
//            }
//        }
//        return calendarioEditado;
//    }
//    /**
//     * crea un eventoDTO a partir de los atributos del evento y el color dados por los parametros
//     * @param evento
//     * @param color
//     * @return 
//     */
//    private EventoConsultableDTO toDTO(EventoA evento,Color color){
//        EventoConsultableDTO eventoConvertido = null;
//        switch (evento.getTipo()) {
//            case UNICO_UN_DIA ->
//                eventoConvertido = new EventoConsultableDTO(
//                        evento.getNombre(),
//                        evento.getDescripcion(),
//                        color,
//                        evento.getUbicacion(),
//                        evento.getFechaInicio(),
//                        evento.getHoraInicio(),
//                        evento.getHorasDuracionEvento()
//                );
//            case UNICO_VARIOS_DIAS ->
//                eventoConvertido = new EventoConsultableDTO(
//                        TipoEventoEnumDTO.UNICO_VARIOS_DIAS,
//                        evento.getNombre(),
//                        evento.getDescripcion(),
//                        color,
//                        evento.getDiasSemana(),
//                        evento.getUbicacion(),
//                        evento.getFechaInicio(),
//                        evento.getFechaFin(),
//                        evento.getHoraInicio(),
//                        evento.getHorasDuracionEvento()
//                );
//            case SEMANAL ->
//                eventoConvertido = new EventoConsultableDTO(
//                        TipoEventoEnumDTO.SEMANAL,
//                        evento.getNombre(),
//                        evento.getDescripcion(),
//                        color,
//                        evento.getDiasSemana(),
//                        evento.getUbicacion(),
//                        evento.getFechaInicio(),
//                        evento.getFechaFin(),
//                        evento.getHoraInicio(),
//                        evento.getHorasDuracionEvento()
//                );
//        }
//        return eventoConvertido;
//    }
//    /**
//     * crea un evento BO a partir de los atributos del eventoDTO dado en el parametro
//     * @param evento
//     * @return 
//     */
//    private EventoA toBO(EventoConsultableDTO evento){
//        EventoA eventoConvertido = null;
//        switch (evento.getTipo()) {
//            case UNICO_UN_DIA ->
//                eventoConvertido = new EventoA(
//                        evento.getNombre(),
//                        evento.getDescripcion(),
//                        evento.getFechaInicio(),
//                        evento.getUbicacion(),
//                        evento.getHoraInicio(),
//                        evento.getHorasDuracionEvento()
//                );
//            case UNICO_VARIOS_DIAS ->
//                eventoConvertido = new EventoA(
//                        TipoEventoEnum.UNICO_VARIOS_DIAS,
//                        evento.getNombre(),
//                        evento.getDescripcion(),
//                        evento.getDiasSemana(),
//                        evento.getUbicacion(),
//                        evento.getFechaInicio(),
//                        evento.getFechaFin(),
//                        evento.getHoraInicio(),
//                        evento.getHorasDuracionEvento()
//                );
//            case SEMANAL ->
//                eventoConvertido = new EventoA(
//                        TipoEventoEnum.SEMANAL,
//                        evento.getNombre(),
//                        evento.getDescripcion(),
//                        evento.getDiasSemana(),
//                        evento.getUbicacion(),
//                        evento.getFechaInicio(),
//                        evento.getFechaFin(),
//                        evento.getHoraInicio(),
//                        evento.getHorasDuracionEvento()
//                );
//        }
//        return eventoConvertido;
//    }
//    
//    public List<EventoConsultableDTO> editarEvento(List<EventoConsultableDTO> calendario, 
//            EventoConsultableDTO evento) throws NegocioException{
//        EventoA eventoEditable=toBO(evento);
//        eventoEditable=eventoEditable.editarEvento(eventoEditable);
//        EventoConsultableDTO eventoEditado=toDTO(eventoEditable, evento.getColor());
//        if(calendario.add(eventoEditado))
//            return calendario;
//        else throw new NegocioException("ERROR: no se pudo editar el evento");
//    }
//    
//    public List<EventoConsultableDTO> agregarEvento(List<EventoConsultableDTO> calendario,
//            EventoConsultableDTO evento) throws NegocioException{
//        EventoA eventoEditable=toBO(evento);
//        if(eventoEditable.agregarEvento(eventoEditable)){
//            calendario.add(evento);
//            return calendario;
//        }else throw new NegocioException("ERROR: no se agrego el evento al calendario");
//    }
//    
//    public EventoConsultableDTO obtenerEvento(EventoConsultableDTO evento)throws NegocioException{
//        EventoA eventoEditable=toBO(evento);
//        eventoEditable=eventoEditable.obtenerEvento(eventoEditable);
//        if(eventoEditable!=null){
//            EventoConsultableDTO eventoEditado=toDTO(eventoEditable, evento.getColor());
//            return eventoEditado;
//        }
//        else throw new NegocioException("ERROR: no se pudo obtener la informacion del evento");
//    }
//    
//    public List<EventoConsultableDTO> eliminarEvento(List<EventoConsultableDTO> calendario, 
//            EventoConsultableDTO evento)throws NegocioException{
//        EventoA eventoEditable=toBO(evento);
//        if(eventoEditable.eliminarEvento(eventoEditable)){
//            if(calendario.remove(evento))
//                return calendario;
//        }
//        throw new NegocioException("ERROR: no se pudo eliminar el evento del calendario");
//    }
}
