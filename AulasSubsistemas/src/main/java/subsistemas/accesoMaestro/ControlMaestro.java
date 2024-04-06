
package subsistemas.accesoMaestro;

import DTOS.maestro.MaestroEditableDTO;
import conexion.IConexionDAO;
import excepciones.NegocioException;
import javax.persistence.EntityManager;
import objetosNegocio.Maestro;
import subsistemas.recuperarMaestro.IRecuperarMaestro;

/**
 *
 * @author pauli
 */
public class ControlMaestro {
    private IRecuperarMaestro recuperaM;
    
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

            // Obtener el maestro a actualizar desde la base de datos
            Maestro maestroPersistido = entityManager.find(Maestro.class, maestro.getId());

            // Actualizar los atributos del maestro con los valores proporcionados en el DTO
            maestroPersistido.setNombre(maestro.getNombre());
            maestroPersistido.setCubiculo(maestro.getCubiculo());
            maestroPersistido.setFoto(maestro.getFoto());
            maestroPersistido.setDescripcion(maestro.getDescripcion());

            // Completar la transacción
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            // Manejar cualquier excepción y hacer rollback en caso de error
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new NegocioException("Error al editar el maestro", e);
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
        List<EventoA> listaEventos = new ArrayList<>();
        for (EventoConsultableDTO dtoEvento: maestro.getCalendario()){
            EventoA even=toBO(dtoEvento);
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
                eventoConvertido = new EventoA(
                        evento.getNombre(),
                        evento.getDescripcion(),
                        evento.getFechaInicio(),
                        evento.getUbicacion(),
                        evento.getHoraInicio(),
                        evento.getHorasDuracionEvento()
                );
            case UNICO_VARIOS_DIAS ->
                eventoConvertido = new EventoA(
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
                eventoConvertido = new EventoA(
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
