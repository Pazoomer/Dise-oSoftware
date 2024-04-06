
package BO.recuperarMaestroBO;

import DTOS.maestro.MaestroEditableDTO;
import conexion.IConexionDAO;
import excepciones.NegocioException;
import java.util.logging.Level;
import java.util.logging.Logger;
import subsistemas.conexionCia.FachadaAccesoCia;
import subsistemas.conexionCia.IAccesoCia;
import subsistemas.recuperarMaestro.IRecuperarMaestro;
import subsistemas.recuperarMaestro.RecuperarMaestro;

/**
 *
 * @author t1pas
 */
public class RecuperarMaestroBO implements IRecuperarMaestroBO{
    
    private final IConexionDAO conexion;

    public RecuperarMaestroBO(IConexionDAO conexion) {
        this.conexion = conexion;
    }

    @Override
    public MaestroEditableDTO recuperarMaestro(MaestroEditableDTO maestroEditableDTO) {
        if (maestroEditableDTO == null) {
            IAccesoCia dao = new FachadaAccesoCia(conexion);
            try {
                return dao.accesoCia();
            } catch (NegocioException ex) {
                Logger.getLogger(RecuperarMaestroBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            IRecuperarMaestro dao=new RecuperarMaestro(conexion);
            try {
                return dao.recuperarMaestro();
            } catch (NegocioException ex) {
                Logger.getLogger(RecuperarMaestroBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
        //Long maestroId = maestroEditableDTO.getId();
        //if (maestroId != null && maestroId != 0) { // Verificar que el ID del maestro no sea null ni cero
            //MaestroDAO mDao = new MaestroDAO(conexion);
            //Maestro maestro = mDao.obtenerMaestroPorId(maestroId);
            //IAccesoCia accesoCia=new FachadaAccesoCia();
            //MaestroEditableDTO maestro=accesoCia.accesoCia();
            //return maestro;
            
            /*
            List<EventoConsultableDTO> eventosConsultables = new ArrayList<>();
            for (Evento evento : maestro.getCalendario()) {

                EventoConsultableDTO eventoAux = null;
                switch (evento.getTipo()) {
                    case SEMANAL -> {

                        List<DiasSemanaDTO> diasSemanasDTO = new ArrayList<>();
                        for (DiasSemana diasSemana : evento.getDiasSemana()) {
                            DiasSemanaDTO diasSemanaAux = new DiasSemanaDTO(
                                    diasSemana.getValorBinario()
                            );
                            diasSemanasDTO.add(diasSemanaAux);
                        }

                        eventoAux = new EventoConsultableDTO(
                                evento.getTipo(),
                                evento.getNombre(),
                                evento.getDescripcion(),
                                evento.getColor(),
                                diasSemanasDTO,
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
                        List<DiasSemanaDTO> diasSemanasDTO = new ArrayList<>();
                        for (DiasSemana diasSemana : evento.getDiasSemana()) {
                            DiasSemanaDTO diasSemanaAux = new DiasSemanaDTO(
                                    diasSemana.getValorBinario()
                            );
                            diasSemanasDTO.add(diasSemanaAux);
                        }

                        eventoAux = new EventoConsultableDTO(
                                evento.getTipo(),
                                evento.getNombre(),
                                evento.getDescripcion(),
                                evento.getColor(),
                                diasSemanasDTO,
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

            MaestroEditableDTO maestroEncontrado = new MaestroEditableDTO(
                    maestro.getIdMaestro(),
                    maestro.getNombre(),
                    maestro.getCubiculo(),
                    maestro.getDescripcion(),
                    maestro.getFoto(),
                    eventosConsultables
            );
            return maestroEncontrado; // Devolver el maestro obtenido*/
        //} else {
            // Si el ID del maestro es null o cero, imprimir un mensaje de error
            //throw new ValidacionException("El Maestro no existe");

       // }
    }
    
}
