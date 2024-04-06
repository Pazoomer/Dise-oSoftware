/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subsistemas.accesoMaestro;

import DTOS.evento.EventoConsultableDTO;
import DTOS.evento.TipoEventoEnumDTO;
import DTOS.maestro.MaestroEditableDTO;
import excepciones.NegocioException;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import objetosNegocio.Evento;
import objetosNegocio.Maestro;
import objetosNegocio.TipoEventoEnum;
import subsistemas.recuperarMaestro.FachadaRecuperarMaestro;
import subsistemas.recuperarMaestro.IRecuperarMaestro;

/**
 *
 * @author pauli
 */
public class ControlMaestro {
    
    public MaestroEditableDTO agregarEventoCalendario(MaestroEditableDTO maestro) throws NegocioException {
        Maestro maestroEditable = convertMaestro(maestro);
        for (Evento evento : maestroEditable.getCalendario()) {
            maestroEditable.agregarEventoCalendario(evento);
        }
        return maestro;
    }
    
    public MaestroEditableDTO editarMaestro(MaestroEditableDTO maestro) throws NegocioException {
        //recuperaM = new FachadaRecuperarMaestro();
        List<Color> colores=new ArrayList<>();
        for(EventoConsultableDTO ec: maestro.getCalendario()){
            colores.add(ec.getColor());
        }
        Maestro maestroBO=convertMaestro(maestro);
        maestroBO=maestroBO.editarMaestro(maestroBO);
        MaestroEditableDTO maestroEditado=toMaestroDTO(maestroBO, colores);
        return maestroEditado;
    }
    
    private Maestro convertMaestro(MaestroEditableDTO maestro){
        Maestro maestroCon=new Maestro();
        maestroCon.setIdMaestro(maestro.getId());
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
    private EventoConsultableDTO toEventoDTO(Evento evento,Color color){
        EventoConsultableDTO eventoConvertido = null;
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
    }
}
