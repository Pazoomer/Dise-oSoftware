/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subsistemas.accesoMaestro;

import DTOS.evento.EventoConsultableDTO;
import DTOS.maestro.MaestroEditableDTO;
import excepciones.NegocioException;
import java.util.ArrayList;
import java.util.List;
import objetosNegocio.EventoA;
import objetosNegocio.MaestroA;
import objetosNegocio.TipoEventoEnum;
import subsistemas.recuperarMaestro.FachadaRecuperarMaestro;
import subsistemas.recuperarMaestro.IRecuperarMaestro;

/**
 *
 * @author pauli
 */
public class ControlMaestro {
    private IRecuperarMaestro recuperaM;
    
    
     public MaestroEditableDTO agregarEventoCalendario(MaestroEditableDTO maestro) throws NegocioException{
        MaestroA maestroEditable=convertMaestro(maestro);
        for(EventoA evento: maestroEditable.getCalendario()){ 
            maestroEditable.agregarEventoCalendario(evento);   
        }
       return maestro;   
    }
     public MaestroEditableDTO editarMaestro(MaestroEditableDTO maestro) throws NegocioException{
          
        recuperaM=new FachadaRecuperarMaestro();
        MaestroEditableDTO maestroRecuperado=recuperaM.recuperarMaestro();
        MaestroA maestroEditado=convertMaestro(maestroRecuperado);
        return maestroRecuperado;
     }
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
    }
}
