
package subsistemas.accesoMaestro;

import DTOS.evento.EventoConsultableDTO;
import DTOS.evento.TipoEventoEnumDTO;
import DTOS.maestro.MaestroEditableDTO;
import conexion.IConexionDAO;
import excepciones.NegocioException;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import objetosNegocio.Evento;
import objetosNegocio.Maestro;
import objetosNegocio.TipoEventoEnum;
import subsistemas.recuperarMaestro.FachadaRecuperarMaestro;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import objetosNegocio.Maestro;
import subsistemas.recuperarMaestro.IRecuperarMaestro;

/**
 *
 * @author pauli
 */
public class ControlMaestro {
    
    private final IConexionDAO conexion;

    public ControlMaestro(IConexionDAO conexion) {
        this.conexion = conexion;
    }
    /*
     public MaestroEditableDTO agregarEventoCalendario(MaestroEditableDTO maestro) throws NegocioException{
        MaestroA maestroEditable=convertMaestro(maestro);
        for(EventoA evento: maestroEditable.getCalendario()){ 
            maestroEditable.agregarEventoCalendario(evento);
        }
        return maestro;
    }*/

    public boolean editarMaestro(MaestroEditableDTO maestro) throws NegocioException {
        EntityManager entityManager=conexion.crearConexion();
        try {
            
            entityManager.getTransaction().begin();

            String jpql = "SELECT m FROM Maestro m WHERE m.idMaestro = :idMaestro";
            TypedQuery<Maestro> query = entityManager.createQuery(jpql, Maestro.class);
            query.setParameter("idMaestro", maestro.getId());
            Maestro maestroPersistido = query.getSingleResult();

            // Actualizar los atributos del maestro con los valores proporcionados en el DTO
            maestroPersistido.setNombre(maestro.getNombre());
            maestroPersistido.setCubiculo(maestro.getCubiculo());
            maestroPersistido.setFoto(maestro.getFoto());
            maestroPersistido.setDescripcion(maestro.getDescripcion());

            // Completar la transacción
            entityManager.getTransaction().commit();
        } catch (NoResultException e) {
            // Manejar el caso en que no se encuentre ningún Maestro con el valor proporcionado
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new NegocioException("Maestro no encontrado para el id_maestro: " + maestro.getIdBD(), e);
        } catch (Exception e) {
            // Manejar cualquier otra excepción y hacer rollback en caso de error
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            throw new NegocioException("Error al editar el maestro", e);
        } finally {
            entityManager.close();
        }
        return true;

        /*
        recuperaM=new FachadaRecuperarMaestro();
        MaestroEditableDTO maestroRecuperado=recuperaM.recuperarMaestro();
        MaestroA maestroEditado=convertMaestro(maestroRecuperado);
        return maestroRecuperado;
         */
    }
    /*
    private MaestroA convertMaestro(MaestroEditableDTO maestro){
        MaestroA maestroCon=new MaestroA();
        maestroCon.setId(maestro.getId());
        maestroCon.setNombre(maestro.getNombre());
        maestroCon.setCubiculo(maestro.getCubiculo());
        List<Evento> listaEventos = new ArrayList<>();
        for (EventoConsultableDTO dtoEvento: maestro.getCalendario()){
            Evento even=toEventoBO(dtoEvento);
            listaEventos.add(even);
        }
        maestroCon.setCalendario(listaEventos);
        return maestroCon;
    }
    /*
    private EventoA toBO(EventoConsultableDTO evento){
        EventoA eventoConvertido = null;
        switch (evento.getTipo()) {
            case UNICO_UN_DIA ->
                eventoConvertido = new EventoConsultableDTO(
                        evento.getNombre(),
                        evento.getDescripcion(),
                        color,
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
                        color,
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
                        color,
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
    
    private MaestroEditableDTO toMaestroDTO(Maestro maestro, List<Color> colores){
        MaestroEditableDTO maestroCon=new MaestroEditableDTO();
        maestroCon.setId(maestro.getIdMaestro());
        maestroCon.setNombre(maestro.getNombre());
        maestroCon.setCubiculo(maestro.getCubiculo());
        List<EventoConsultableDTO> listaEventos = new ArrayList<>();
        List<Evento> eventosBO=maestro.getCalendario();
        
        for (Evento evento: eventosBO){
            listaEventos.add(toEventoDTO(evento, 
                    colores.get(eventosBO.indexOf(evento)))
            );
        }
        maestroCon.setCalendario(listaEventos);
        return maestroCon;
    }
    
    private Evento toEventoBO(EventoConsultableDTO evento){
        Evento eventoConvertido = null;
        switch (evento.getTipo()) {
            case UNICO_UN_DIA ->
                eventoConvertido = new Evento(
                        evento.getNombre(),
                        evento.getDescripcion(),
                        evento.getFechaInicio(),
                        evento.getUbicacion(),
                        evento.getHoraInicio(),
                        evento.getHorasDuracionEvento()
                );
            case UNICO_VARIOS_DIAS ->
                eventoConvertido = new Evento(
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
    }*/
}
