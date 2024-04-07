
package subsistemas.accesoCalendario;

import DTOS.evento.EventoConsultableDTO;
import DTOS.evento.TipoEventoEnumDTO;
import com.toedter.calendar.JCalendar;
import excepciones.NegocioException;
import java.awt.Color;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import objetosNegocio.Evento;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import objetosNegocio.TipoEventoEnum;
import conexion.IConexionDAO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import objetosNegocio.Evento;
import objetosNegocio.Maestro;

/**
 *
 * @author t1pas, luisa morales
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
        //return calendarioEditado;
    }

    /**
     * crea un eventoDTO a partir de los atributos del evento y el color dados por los parametros
     * @param evento
     * @param color
     * @return 
     */

     /*
      *  private EventoConsultableDTO toDTO(Evento evento,Color color){
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
      */
   
    /**
     * crea un evento BO a partir de los atributos del eventoDTO dado en el parametro
     * @param evento
     * @return 
     */
    /*
     * 
     
    private Evento toBO(EventoConsultableDTO evento){
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
    
    public List<EventoConsultableDTO> editarEvento(List<EventoConsultableDTO> calendario, 
            EventoConsultableDTO evento) throws NegocioException{
        Evento eventoEditable=toBO(evento);
        eventoEditable=eventoEditable.editarEvento(eventoEditable);
        EventoConsultableDTO eventoEditado=toDTO(eventoEditable, evento.getColor());
        if(calendario.add(eventoEditado))
            return calendario;
        else throw new NegocioException("ERROR: no se pudo editar el evento");
    }
    
    public List<EventoConsultableDTO> agregarEvento(List<EventoConsultableDTO> calendario,
            EventoConsultableDTO evento) throws NegocioException{
        Evento eventoEditable=toBO(evento);
        if(eventoEditable.agregarEvento(eventoEditable)){
            calendario.add(evento);
            return calendario;
        }else throw new NegocioException("ERROR: no se agrego el evento al calendario");
    }
    
    public EventoConsultableDTO obtenerEvento(EventoConsultableDTO evento)throws NegocioException{
        Evento eventoEditable=toBO(evento);
        eventoEditable=eventoEditable.obtenerEvento(eventoEditable);
        if(eventoEditable!=null){
            EventoConsultableDTO eventoEditado=toDTO(eventoEditable, evento.getColor());
            return eventoEditado;
        }
        else throw new NegocioException("ERROR: no se pudo obtener la informacion del evento");
    }
    
    public List<EventoConsultableDTO> eliminarEvento(List<EventoConsultableDTO> calendario, 
            EventoConsultableDTO evento)throws NegocioException{
        Evento eventoEditable=toBO(evento);
        if(eventoEditable.eliminarEvento(eventoEditable)){
            if(calendario.remove(evento))
                return calendario;
        }
        throw new NegocioException("ERROR: no se pudo eliminar el evento del calendario");
    }
    
    public EventoConsultableDTO inicializarCalendario(JCalendar jcalendar, List<EventoConsultableDTO> calendarioMtro,
            JTable tabla){
        System.out.println("inicio de initCal");
        desplegarCal=new ControlDesplegarCalendario(jcalendar, calendarioMtro, tabla);
        desplegarCal.cargarCalendario();
        System.out.println("se ejecuto cargarCal");
        desplegarCal.cargarEventos();
        System.out.println("se ejecuto cargar");
        return ControlDesplegarCalendario.eventoSeleccionado;
    }
    
    public EventoConsultableDTO refrescarCalendario(JCalendar jcalendar, List<EventoConsultableDTO> calendarioMtro,
            JTable table){
        desplegarCal.cargarEventos();
        return ControlDesplegarCalendario.eventoSeleccionado;
    }
    private class ControlDesplegarCalendario{
        List<EventoConsultableDTO> calendarioMes;
        List<EventoConsultableDTO> calendarioMaestro;
        ModeloTablaHorario modeloTabla;
        JCalendar jCalendar;
        JTable tablaCalendario;
        boolean isDisplayed=false;
        static EventoConsultableDTO eventoSeleccionado;
        
        private ControlDesplegarCalendario(JCalendar jcalendar,
                List<EventoConsultableDTO> calendarioMtro, JTable tabla){
            calendarioMaestro=calendarioMtro;
            jCalendar=jcalendar;
            tablaCalendario=tabla;
        }
        
        //inicializa el calendario con los eventos del mes seleccionado
        private void cargarCalendario() {
            Calendar fecha = jCalendar.getCalendar();
            int mes = fecha.get(Calendar.MONTH);
            calendarioMes = new ArrayList<>();

            for (EventoConsultableDTO evento : calendarioMaestro) {
                //System.out.println("evento desde metodo cargar Calendario: "+ evento);
                if (evento.getFechaInicio().get(Calendar.MONTH) == mes || evento.getTipo().equals(TipoEventoEnumDTO.SEMANAL)) {
                    calendarioMes.add(evento);
                    //System.out.println("evento del maestro: "+evento.getNombre());
                }
            }
        }
        
        private void cargarEventos() {
            System.out.println("inicia el cargar eventos");
            modeloTabla = new ModeloTablaHorario();
            Calendar fecha = jCalendar.getCalendar();
            int semana = fecha.get(Calendar.WEEK_OF_MONTH);
            List<EventoConsultableDTO> eventos = cargarEventosSemana(semana);
            if (eventos.isEmpty()) {
                System.out.println("No hay eventos de la semana");
            }
            eventos.forEach(e -> {
                String diasEvento = e.getDiasSemana();
                System.out.println("evento de la semana dentro de foreach: "+e);
                Calendar horaInicio = e.getHoraInicio();
                int hora = horaInicio.get(Calendar.HOUR_OF_DAY);
                int minutos = horaInicio.get(Calendar.MINUTE);
                float duracion = e.getHorasDuracionEvento();
                System.out.println("duracion evento:"+duracion);
                if (e.getTipo().equals(TipoEventoEnumDTO.SEMANAL)) {
                    for (Integer i : diasSemana(diasEvento)) {
                        System.out.println("dia: " + i);
                        setEvento(hora, minutos, duracion, i, e);
                    }
                } else {
                    int diaEv = e.getFechaInicio().get(Calendar.DAY_OF_WEEK);
                    System.out.println("dia ev dentro d foreachh:"+diaEv);
                    setEvento(hora, minutos, duracion,
                            diaEv, e);
                }
            });
            tablaCalendario.setModel(modeloTabla);
            getEventoSeleccionado();
            System.out.println("evento seleccionado actual: "+eventoSeleccionado);
        }
        
        private List<Integer> diasSemana(String dias){
            String[] diasArr=dias.split(",",7);
            List<Integer> diasNum=new ArrayList<>();
            for(String s:diasArr){
                switch(s){
                    case "Do" -> diasNum.add(1);
                    case "Lu" -> diasNum.add(2);
                    case "Ma" -> diasNum.add(3);
                    case "Mi" -> diasNum.add(4);
                    case "Ju" -> diasNum.add(5);
                    case "Vi" -> diasNum.add(6);
                    case "Sa" -> diasNum.add(7);
                }
            }
            return diasNum;
        }
        
        protected void getEventoSeleccionado() {
            tablaCalendario.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent event) {
                    //System.out.println("cambio el valor: "+event.toString());
                    // Verificar si la selección está cambiando
                    if (!event.getValueIsAdjusting()) {
                        // Obtener la fila y columna seleccionadas
                        int selectedRow = tablaCalendario.getSelectedRow();
                        int selectedColumn = tablaCalendario.getSelectedColumn();
                        if (selectedRow > 0 && selectedColumn > 0) {
                            System.out.println("celda seelccionada: " + selectedRow + ", " + selectedColumn);
                            String nombreEvento = (String) modeloTabla.getValueAt(selectedRow, selectedColumn);
                            if (nombreEvento != null) {
                                if (!isDisplayed) {
                                    isDisplayed = true;
                                    System.out.println("nombre evento: " + nombreEvento);
                                    List<EventoConsultableDTO> evSem = cargarEventosSemana(
                                            jCalendar.getCalendar().get(Calendar.WEEK_OF_MONTH));
                                    for (EventoConsultableDTO e : evSem) {
                                        if (e.getNombre().equals(nombreEvento)) {
                                            eventoSeleccionado = e;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }
        
        private void setEvento(int horaEvento, int minutoEvento,
                float duracionEvento, int diaEvento, EventoConsultableDTO evento) {
            int rows = modeloTabla.getRowCount();
            System.out.println("rows: "+rows);

            int index = 0;
            boolean encontrada = false;

            for (int i = 0; i < rows; i++) {
                String horaCelda = (String) modeloTabla.getValueAt(i, 0);
                horaCelda = horaCelda.substring(0, 5);
                System.out.println("hora celda: "+horaCelda);
                System.out.println("hora evento: "+horaEvento);
                int semana = jCalendar.getCalendar().get(Calendar.WEEK_OF_MONTH);
                if (!encontrada) {
                    if (minutoEvento > 0) {
                        System.out.println("hora evento: " + horaEvento + ":" + minutoEvento);
                        System.out.println("columna dia evento: " + diaEvento);
                        if (horaCelda.equals(horaEvento + ":" + minutoEvento)
                                || horaCelda.equals("0" + horaEvento + ":" + minutoEvento)) {
                            //System.out.println("se encontro la celda: " + horaCelda);
                            modeloTabla.setValueAt(evento.getNombre(), i, diaEvento);
                            agregarEventoSemana(evento, semana);
                            encontrada = true;
                            index = i;
                            break;
                        }
                    } else if (horaCelda.equals(horaEvento + ":" + "00")
                            || horaCelda.equals("0" + horaEvento + ":" + "00")) {
                        System.out.println("se encontro la celda: " + horaCelda);
                        modeloTabla.setValueAt(evento.getNombre(), i, diaEvento);
                        agregarEventoSemana(evento, semana);
                        encontrada = true;
                        index = i;
                        break;
                    }
                }
            }
            if (encontrada) {
                llenarRestoCeldasEvento(index, diaEvento, duracionEvento, evento);
            }
        }

        private void llenarRestoCeldasEvento(int index, int columnIndex,
                float duracionEvento, EventoConsultableDTO evento) {
            System.out.println("inicio llenarRestoCeldas");
            int horas = (int) duracionEvento;
            double copiaHoras = duracionEvento;
            double decimal = copiaHoras - horas;
            int minutos = (int) (decimal * 100);
            int totalCeldasEvento = (horas * 2) - 1;
            if (minutos > 0) {
                totalCeldasEvento = totalCeldasEvento + 1;
            }

            for (int i = 1; i <= totalCeldasEvento; i++) {
                modeloTabla.setValueAt(evento.getNombre(), index + i, columnIndex);
            }
        }

        
        private List<EventoConsultableDTO> cargarEventosSemana(int semana) {
            List<EventoConsultableDTO> eventosDeSemana = new ArrayList<>();
            cargarCalendario();
            for (EventoConsultableDTO evento : calendarioMes) {
                if (evento.getFechaInicio().get(Calendar.WEEK_OF_MONTH) == semana
                        || evento.getTipo().equals(TipoEventoEnumDTO.SEMANAL)) {
                    eventosDeSemana.add(evento);
                    //System.out.println("metodo eventos de la semana: "+evento.getNombre());
                }//else
                //System.out.println("no hay");
            }
            return eventosDeSemana;
        }

        private void agregarEventoSemana(EventoConsultableDTO evento, int semana) {
            List<EventoConsultableDTO> eventos = cargarEventosSemana(semana);
            eventos.add(evento);
        }
    }
    
    private class ModeloTablaHorario extends AbstractTableModel{
        // Definir los días de la semana y las horas del día
        private String[] diasSemanales = {"Horas", "Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"};
        private String[] horas = {"07:00 AM", "07:30 AM", "08:00 AM", "08:30 AM", "09:00 AM", "09:30 AM", "10:00 AM", "10:30 AM",
            "11:00 AM", "11:30 AM", "12:00 PM", "12:30 PM", "01:00 PM", "01:30 PM", "02:00 PM", "02:30 PM", "03:00 PM", "03:30 PM",
            "04:00 PM", "04:30 PM", "05:00 PM", "05:30 PM", "06:00 PM", "06:30 PM", "07:00 PM", "07:30 PM", "08:00 PM"};

        private String[][] horario = new String[horas.length][diasSemanales.length];

        public ModeloTablaHorario() {
            for (int i = 0; i < horas.length; i++) {
                for (int j = 0; j < diasSemanales.length; j++) {
                    if (j == 0) {
                        horario[i][j] = horas[i];
                    } else {
                        horario[i][j] = "";
                    }

                }
            }

//        //prueba
//        horario[0][1] = "Ingles";
//        horario[1][2] = "diseño";
//        horario[2][3] = "bda";
        }

        @Override
        public int getRowCount() {
            return horas.length;
        }

        @Override
        public int getColumnCount() {
            return diasSemanales.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return horario[rowIndex][columnIndex];
        }

        @Override
        public void setValueAt(Object value, int rowIndex, int columnIndex) {
            horario[rowIndex][columnIndex] = (String) value;
            fireTableCellUpdated(rowIndex, columnIndex);
        }

        @Override
        public String getColumnName(int column) {
            return diasSemanales[column];
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return true; // Permitir edición de celdas
        }
    }
    */
}
