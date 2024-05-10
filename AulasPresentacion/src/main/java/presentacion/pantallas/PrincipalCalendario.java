
package presentacion.pantallas;

import DTOS.evento.EventoConsultableDTO;
import DTOS.evento.TipoEventoEnumDTO;
import DTOS.maestro.MaestroEditableDTO;
import accesoMaestro.FachadaAccesoMaestro;
import accesoMaestro.IAccesoMaestro;
import excepciones.NegocioException;
import java.awt.Color;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import presentacion.CDEvento;
import presentacion.ModeloTablaHorario;

/**
 *
 * @author t1pas, luisa morales
 */
public class PrincipalCalendario extends javax.swing.JFrame {

    /**
     * Es el maestro al que le pertenece el calendario
     */
    private MaestroEditableDTO maestro;
   // PrincipalMaestro prinMaestro;
    IAccesoMaestro accesoMaestro;
    JFrame parent;
    static List<EventoConsultableDTO> calendarioMes;
    public static List<EventoConsultableDTO> calendarioMaestroTemporal;
    static ModeloTablaHorario modelo;
    private CDEvento cdEvento;
    public static boolean isDisplayed=false;
    public static EventoConsultableDTO eventoSeleccionado;

    public PrincipalCalendario(){
    }
    
    
    /**
     * Creates new form PrincipalCalendario
     * @param parent
     * @param maestro
     */
    public PrincipalCalendario(JFrame parent, MaestroEditableDTO maestro) {
        setUndecorated(true);
        this.setResizable(false);
        initComponents();
        this.parent=parent;
        this.maestro=maestro.clone();
        //this.prinMaestro=prinMaestro;
        accesoMaestro=new FachadaAccesoMaestro();
        modelo=new ModeloTablaHorario();
        calendarioMaestroTemporal=maestro.getCalendario();
        cargarCalendario();
        cargarEventos();
        //this.setVisible(true);
        this.setSize(800, 630);
        this.setLocationRelativeTo(null);
        cargarIconos();
    }
    
    private void cargarIconos() {
        // Carga el icono de retorno en el botón btnAtras
        ImageIcon iconoReturn = new ImageIcon(getClass().getResource("/imagenes/icons8-return-50.png"));
        btnAtras.setIcon(iconoReturn);
        // Carga el icono de guardar en el botón btnGuardar
        ImageIcon iconoGuardar = new ImageIcon(getClass().getResource("/imagenes/icons8-save-50.png"));
        btnGuardar.setIcon(iconoGuardar);
    }
    
    private List<EventoConsultableDTO> cargarEventosSemana(int semana){
        List<EventoConsultableDTO> eventosDeSemana=new ArrayList<>();
        cargarCalendario();
        for(EventoConsultableDTO evento:calendarioMes){
             if (evento.getFechaInicio().get(Calendar.WEEK_OF_MONTH) == semana
                     || evento.getTipo().equals(TipoEventoEnumDTO.SEMANAL)) {
                eventosDeSemana.add(evento);
                //System.out.println("metodo eventos de la semana: "+evento.getNombre());
            }//else
                //System.out.println("no hay");
        }
        return eventosDeSemana;
    }
    
    private void agregarEventoASemana(EventoConsultableDTO evento, int semana){
        List<EventoConsultableDTO> eventos=cargarEventosSemana(semana);
        eventos.add(evento);
    }
    
    private void cargarEventos(){
        //System.out.println("inicia el cargar eventos");
        modelo=new ModeloTablaHorario();
        Calendar fecha=calEsquinaSuperior.getCalendar();
        int semana=fecha.get(Calendar.WEEK_OF_MONTH);
        List<EventoConsultableDTO> eventos=cargarEventosSemana(semana);
//        if(eventos.isEmpty()){
//            JOptionPane.showMessageDialog(this, "No hay eventos de la semana");
//        }
        eventos.forEach(e->{
            String diasEvento=e.getDiasSemana();
//            System.out.println("evento de la semana dentro de foreach: "+e);
            Calendar horaInicio = e.getHoraInicio();
            int hora = horaInicio.get(Calendar.HOUR);
            int minutos = horaInicio.get(Calendar.MINUTE);
//            System.out.println("hora evenot: "+hora+":"+minutos);   
            Double duracion = e.getHorasDuracionEvento();
//            System.out.println("duracion evento:"+duracion);
            if (e.getTipo().equals(TipoEventoEnumDTO.SEMANAL)) {
                String[] arrDias=diasEvento.split(",");
                String[] diasSemana={"Do","Lu","Ma","Mi","Ju","Vi","Sa"};
                for (String dia : arrDias) {
//                    System.out.println("diaaa: " + dia);
                    for (int i = 0; i < 7; i++) {
                        if (dia.equals(diasSemana[i])) {
//                            System.out.println("true");
                            setEvento(hora, minutos, duracion, i + 1, e);
                        }
                    }
                }
            } else {
                int diaEv=e.getFechaInicio().get(Calendar.DAY_OF_WEEK);
//                System.out.println("dia ev dentro d foreachh:"+diaEv);
                setEvento(hora, minutos, duracion,
                         diaEv, e);
            }
        });
        tablaEventos.setModel(modelo);
        getEventoSeleccionado();
        //System.out.println("evento seleccionado actual: "+eventoSeleccionado);
    }
    
    private void setEvento(int horaEvento,int minutoEvento, 
            Double duracionEvento,int diaEvento,EventoConsultableDTO evento){
        int rows=modelo.getRowCount();
//        System.out.println("rows: "+rows);
        
        int index=0;
        boolean encontrada=false;
        
        for (int i = 0; i < rows; i++) {
           String horaCelda=(String)modelo.getValueAt(i, 0);
           horaCelda=horaCelda.substring(0, 5);
////           System.out.println("hora celda: "+horaCelda);
//           System.out.println("hora evento: "+horaEvento);
           int semana=calEsquinaSuperior.getCalendar().get(Calendar.WEEK_OF_MONTH);
           if(!encontrada){
               if (minutoEvento > 0) {
                   //System.out.println("hora evento: " + horaEvento + ":" + minutoEvento);
                   //System.out.println("columna dia evento: " + diaEvento);
                   if (horaCelda.equals(horaEvento + ":" + minutoEvento) ||
                           horaCelda.equals("0"+horaEvento + ":" + minutoEvento)) {
//                       System.out.println("se encontro la celda: " + horaCelda);
                       modelo.setValueAt(evento.getNombre(), i, diaEvento);
                       agregarEventoASemana(evento, semana);
                       encontrada = true;
                       index=i;
                       break;
                   }
               } else if (horaCelda.equals(horaEvento + ":" + "00") || 
                       horaCelda.equals("0"+horaEvento+":"+"00")) {
//                   System.out.println("se encontro la celda: " + horaCelda);
                   modelo.setValueAt(evento.getNombre(), i, diaEvento);
                   agregarEventoASemana(evento, semana);
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
    
    private void llenarRestoCeldasEvento(int index,int columnIndex,
            Double duracionEvento, EventoConsultableDTO evento){
        int horas=duracionEvento.intValue();
        double copiaHoras=duracionEvento;
        double decimal=copiaHoras-horas;
        int minutos=(int)(decimal*100);
        int totalCeldasEvento=(horas*2)-1;
        if(minutos>0)
            totalCeldasEvento=totalCeldasEvento+1;
        
        for (int i = 1; i <=totalCeldasEvento; i++) {
            modelo.setValueAt(evento.getNombre(), index+i, columnIndex);
        }
    }
        
    private void getEventoSeleccionado(){
        tablaEventos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                //System.out.println("cambio el valor: "+event.toString());
                // Verificar si la selección está cambiando
                if (!event.getValueIsAdjusting()) {
                    // Obtener la fila y columna seleccionadas
                    int selectedRow = tablaEventos.getSelectedRow();
                    int selectedColumn = tablaEventos.getSelectedColumn();
                    if(selectedRow>0 && selectedColumn>0){
//                        System.out.println("celda seelccionada: "+selectedRow+", "+selectedColumn);
                        String nombreEvento = (String) modelo.getValueAt(selectedRow, selectedColumn);
////                        System.out.println(nombreEvento);
                        if (nombreEvento != null) {
                            if(!isDisplayed){
                                isDisplayed=true;
////                                System.out.println("nombre evento: " + nombreEvento);
                                List<EventoConsultableDTO> evSem = cargarEventosSemana(
                                        calEsquinaSuperior.getCalendar().get(Calendar.WEEK_OF_MONTH));
                                for (EventoConsultableDTO e : evSem) {
                                    if (e.getNombre().equals(nombreEvento)) {
                                        eventoSeleccionado=e;
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
    
    //inicializa el calendario con los eventos del mes seleccionado
    private void cargarCalendario(){
       Calendar fecha=calEsquinaSuperior.getCalendar();
       int mes=fecha.get(Calendar.MONTH);
       calendarioMes=new ArrayList<>();
        
        for (EventoConsultableDTO evento : calendarioMaestroTemporal) {
            //System.out.println("evento desde metodo cargar Calendario: "+ evento);
            if (evento.getFechaInicio().get(Calendar.MONTH) == mes || evento.getTipo().equals(TipoEventoEnumDTO.SEMANAL)) {
                calendarioMes.add(evento);
                //System.out.println("evento del maestro: "+evento.getNombre());
            }
        }
    }
    
    /**
     * Al hacer clic en guardar calendario debe actualizar el calendario del
     * maestro y guardar al maestro en la base de datos
     */
    private void guardarCalendario() {
        maestro.setCalendario(calendarioMaestroTemporal);
        try {
            accesoMaestro.editarMaestro(maestro);

        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        this.parent.setVisible(true);
        this.dispose();
    }

    /**
     * Cierra este frame y abre el frame PrincipalMaestro
     */
    private void cerrar() {
        this.dispose();
        this.parent.setVisible(true);
    }

    public void añadirEvento(EventoConsultableDTO evento) {
        calendarioMaestroTemporal.add(evento);
        maestro.setCalendario(calendarioMaestroTemporal);
        try {
            if (accesoMaestro.agregarEventoCalendario(maestro, evento)) {
//                System.out.println("yesss");
                eventoSeleccionado = evento;
                cargarEventos();
            }
        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public void editarEvento(EventoConsultableDTO eventoEditado) {

        int index = calendarioMaestroTemporal.indexOf(eventoSeleccionado);
        calendarioMaestroTemporal.set(index, eventoEditado);
        maestro.setCalendario(calendarioMaestroTemporal);

        JOptionPane.showMessageDialog(this, "Evento editado correctamente",
                "Status de la operacion", JOptionPane.INFORMATION_MESSAGE);
        cargarCalendario();
        cargarEventos();
    }

    private void desplegarCDEvento(String operacion) {
        CDEvento cdEvento;
        if (operacion.equals("agregar")) {
            cdEvento = new CDEvento(this, true, "agregar");
        } else if (eventoSeleccionado != null) {
            if (operacion.equals("editar")) {
                cdEvento = new CDEvento(this, eventoSeleccionado, true, "editar");
                cdEvento.desplegarEventoEditable();
            } else {
                cdEvento = new CDEvento(this, eventoSeleccionado, true, "desplegar");
                cdEvento.desplegarEvento();
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se selecciono algun evento");
            return;
        }
        cdEvento.setVisible(true);
    }
    
    private void eliminarEvento() {
        if (eventoSeleccionado != null && calendarioMaestroTemporal != null) {

            if (calendarioMaestroTemporal.contains(eventoSeleccionado)) {

                calendarioMaestroTemporal.remove(eventoSeleccionado);
                cargarCalendario();
                cargarEventos();
            }
        }
    }

    public boolean editarEventso(EventoConsultableDTO eventoEditado){
        //List<EventoConsultableDTO> calendarioEditado;
        IAccesoMaestro accesoCalendario = new FachadaAccesoMaestro();
        //IAccesoCalendarioBO accesoCalendarioBO = new AccesoCalendarioBO(conexion);
        try {
            maestro.setCalendario(calendarioMaestroTemporal);
            return accesoCalendario.editarMaestro(maestro);
        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            return false;
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnEditarEvento = new javax.swing.JButton();
        btnAñadirEvento = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();
        pnlCalendario = new javax.swing.JPanel();
        javax.swing.JScrollPane scpCalendario = new javax.swing.JScrollPane();
        tablaEventos = new javax.swing.JTable();
        calEsquinaSuperior = new com.toedter.calendar.JCalendar();
        btnDetalleEvento = new javax.swing.JButton();
        btnEliminarEvento = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        javax.swing.JLabel lblTituloCalendario = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(800, 600));

        btnEditarEvento.setBackground(new java.awt.Color(22, 81, 198));
        btnEditarEvento.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        btnEditarEvento.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarEvento.setText("Editar Evento");
        btnEditarEvento.setBorder(null);
        btnEditarEvento.setBorderPainted(false);
        btnEditarEvento.setPreferredSize(new java.awt.Dimension(69, 15));
        btnEditarEvento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEditarEventoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEditarEventoMouseExited(evt);
            }
        });
        btnEditarEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarEventoActionPerformed(evt);
            }
        });

        btnAñadirEvento.setBackground(new java.awt.Color(22, 81, 198));
        btnAñadirEvento.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        btnAñadirEvento.setForeground(new java.awt.Color(255, 255, 255));
        btnAñadirEvento.setText("Añadir evento");
        btnAñadirEvento.setBorder(null);
        btnAñadirEvento.setBorderPainted(false);
        btnAñadirEvento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAñadirEventoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAñadirEventoMouseExited(evt);
            }
        });
        btnAñadirEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirEventoActionPerformed(evt);
            }
        });

        btnGuardar.setBorder(null);
        btnGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardarMouseExited(evt);
            }
        });
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnAtras.setBorder(null);
        btnAtras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAtrasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAtrasMouseExited(evt);
            }
        });
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        pnlCalendario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tablaEventos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scpCalendario.setViewportView(tablaEventos);

        javax.swing.GroupLayout pnlCalendarioLayout = new javax.swing.GroupLayout(pnlCalendario);
        pnlCalendario.setLayout(pnlCalendarioLayout);
        pnlCalendarioLayout.setHorizontalGroup(
            pnlCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCalendarioLayout.createSequentialGroup()
                .addComponent(scpCalendario, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlCalendarioLayout.setVerticalGroup(
            pnlCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCalendarioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scpCalendario, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        calEsquinaSuperior.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                calEsquinaSuperiorPropertyChange(evt);
            }
        });

        btnDetalleEvento.setBackground(new java.awt.Color(22, 81, 198));
        btnDetalleEvento.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        btnDetalleEvento.setForeground(new java.awt.Color(255, 255, 255));
        btnDetalleEvento.setText("Ver detalle Evento");
        btnDetalleEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetalleEventoActionPerformed(evt);
            }
        });

        btnEliminarEvento.setBackground(new java.awt.Color(22, 81, 198));
        btnEliminarEvento.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        btnEliminarEvento.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarEvento.setText("Eliminar Evento");
        btnEliminarEvento.setBorder(null);
        btnEliminarEvento.setBorderPainted(false);
        btnEliminarEvento.setPreferredSize(new java.awt.Dimension(69, 15));
        btnEliminarEvento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEliminarEventoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEliminarEventoMouseExited(evt);
            }
        });
        btnEliminarEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarEventoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(calEsquinaSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(61, 61, 61)
                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(btnAñadirEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEditarEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEliminarEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnDetalleEvento)
                        .addGap(82, 82, 82)))
                .addComponent(pnlCalendario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlCalendario, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(calEsquinaSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEditarEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAñadirEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminarEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addComponent(btnDetalleEvento)
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                            .addComponent(btnAtras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(104, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 80, 800, 520);

        jPanel2.setBackground(new java.awt.Color(22, 81, 198));

        lblTituloCalendario.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblTituloCalendario.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloCalendario.setText("Calendario");
        ImageIcon iconoCalendario = new ImageIcon(getClass().getResource("/imagenes/icons8-calendar-50white.png"));
        lblTituloCalendario.setIcon(iconoCalendario);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(lblTituloCalendario)
                .addContainerGap(606, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lblTituloCalendario, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 0, 743, 80);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardarCalendario();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        cerrar();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnAñadirEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirEventoActionPerformed
        desplegarCDEvento("agregar");
    }//GEN-LAST:event_btnAñadirEventoActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        //cerrar();
    }//GEN-LAST:event_formWindowClosed

    private void calEsquinaSuperiorPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_calEsquinaSuperiorPropertyChange
        if(calendarioMes!=null)
            cargarEventos();
    }//GEN-LAST:event_calEsquinaSuperiorPropertyChange

    private void btnEditarEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarEventoActionPerformed
        EventoConsultableDTO ev=eventoSeleccionado;
        desplegarCDEvento("editar");
        //cargarEventos();
    }//GEN-LAST:event_btnEditarEventoActionPerformed

    private void btnAñadirEventoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAñadirEventoMouseEntered
        // TODO add your handling code here:
        btnAñadirEvento.setBackground(Color.lightGray);
        btnAñadirEvento.setForeground(Color.DARK_GRAY);
    }//GEN-LAST:event_btnAñadirEventoMouseEntered

    private void btnEditarEventoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarEventoMouseEntered
        // TODO add your handling code here:
        btnEditarEvento.setBackground(Color.lightGray);
        btnEditarEvento.setForeground(Color.DARK_GRAY);
    }//GEN-LAST:event_btnEditarEventoMouseEntered

    private void btnGuardarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseEntered
        // TODO add your handling code here:
        btnGuardar.setBackground(Color.lightGray);
    }//GEN-LAST:event_btnGuardarMouseEntered

    private void btnAtrasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAtrasMouseEntered
        // TODO add your handling code here:
        btnAtras.setBackground(Color.lightGray);
    }//GEN-LAST:event_btnAtrasMouseEntered

    private void btnAñadirEventoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAñadirEventoMouseExited
        // TODO add your handling code here:
        btnAñadirEvento.setBackground(new Color(0x16, 0x51, 0xC6));
        btnAñadirEvento.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnAñadirEventoMouseExited

    private void btnEditarEventoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarEventoMouseExited
        // TODO add your handling code here:
        btnEditarEvento.setBackground(new Color(0x16, 0x51, 0xC6));
        btnEditarEvento.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnEditarEventoMouseExited

    private void btnAtrasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAtrasMouseExited
        // TODO add your handling code here:
        btnAtras.setBackground(Color.WHITE);
    }//GEN-LAST:event_btnAtrasMouseExited

    private void btnGuardarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseExited
        // TODO add your handling code here:
        btnGuardar.setBackground(Color.WHITE);
    }//GEN-LAST:event_btnGuardarMouseExited

    private void btnDetalleEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetalleEventoActionPerformed
        // TODO add your handling code here:
        desplegarCDEvento("desplegar");
    }//GEN-LAST:event_btnDetalleEventoActionPerformed

    private void btnEliminarEventoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarEventoMouseEntered
        // TODO add your handling code here:
        btnEliminarEvento.setBackground(Color.lightGray);
        btnEliminarEvento.setForeground(Color.DARK_GRAY);
    }//GEN-LAST:event_btnEliminarEventoMouseEntered

    private void btnEliminarEventoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarEventoMouseExited
        // TODO add your handling code here:
        btnEliminarEvento.setBackground(new Color(0x16, 0x51, 0xC6));
        btnEliminarEvento.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnEliminarEventoMouseExited

    private void btnEliminarEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarEventoActionPerformed
        // TODO add your handling code here:
        int opcion=JOptionPane.showConfirmDialog(this, "¿Esta seguro que desea eliminar el evento seleccionado?", 
                "Confirmacion de operacion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if(opcion==JOptionPane.YES_OPTION){
            eliminarEvento();
        }else System.out.println("se cancelo la operacion de eliminar");
    }//GEN-LAST:event_btnEliminarEventoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnAñadirEvento;
    private javax.swing.JButton btnDetalleEvento;
    private javax.swing.JButton btnEditarEvento;
    private javax.swing.JButton btnEliminarEvento;
    private javax.swing.JButton btnGuardar;
    public static com.toedter.calendar.JCalendar calEsquinaSuperior;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel pnlCalendario;
    public static javax.swing.JTable tablaEventos;
    // End of variables declaration//GEN-END:variables
}
