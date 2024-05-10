package presentacion;

import DTOS.campus.UbicacionDTO;
import DTOS.evento.*;
import accesoMaestro.FachadaAccesoMaestro;
import accesoMaestro.IAccesoMaestro;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import presentacion.pantallas.FrmAdminEventos;
import presentacion.pantallas.MapaCalendario;
import presentacion.pantallas.PrincipalCalendario;

/**
 *
 * @author t1pas
 */
public class CDEvento extends javax.swing.JDialog {

    private PrincipalCalendario calendario;
    private FrmAdminEventos frmAdminEventos;
    private EventoConsultableDTO eventoEditable;
    private UbicacionDTO ubicacionDTO;
    private String tipoOperacion;
    private JFrame parent;
    private static IAccesoMaestro accesoMaestro;
    /**
     * Creates new form CDEvento
     *
     * @param parent
     * @param calendario
     * @param modal
     * @param tipoOperacion
     */
    public CDEvento(JFrame parent, boolean modal, String tipoOperacion) {
        super(parent, modal);
        initComponents();
        iniciar(parent);
        //this.calendario=new PrincipalCalendario();
        this.frmAdminEventos=new FrmAdminEventos();
        this.tipoOperacion = tipoOperacion;
        this.cmbTipo.setSelectedIndex(0);
        actualizarPermisos();
    }

    public CDEvento(JFrame parent, EventoConsultableDTO eventoEditable, boolean modal, String tipoOperacion) {
        super(parent, modal);
        initComponents();
        this.eventoEditable = eventoEditable;
        //this.calendario = new PrincipalCalendario();
        this.frmAdminEventos=new FrmAdminEventos();
        this.tipoOperacion = tipoOperacion;
        iniciar(parent);
    }

    private void iniciar(JFrame parent){
        this.setSize(500,620);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        accesoMaestro=new FachadaAccesoMaestro();
        this.parent=parent;
        cargarIconos();
        mostrarCheckBox(false);
    }
    
    /**
     * Carga los iconos en los botones de la interfaz.
     */
    private void cargarIconos() {
        // Carga el icono de retorno en el botón btnAtras
        ImageIcon iconoReturn = new ImageIcon(getClass().getResource("/imagenes/icons8-return-50.png"));
        btnAtras.setIcon(iconoReturn);
        // Carga el icono de mapa en el botón btnMapa
        ImageIcon iconoMapa = new ImageIcon(getClass().getResource("/imagenes/icons8-map-30 blue.png"));
        btnMapa.setIcon(iconoMapa);
        // Carga el icono de guardar en el botón btnGuardar
        ImageIcon iconoGuardar = new ImageIcon(getClass().getResource("/imagenes/icons8-save-50.png"));
        btnAñadir.setIcon(iconoGuardar);
        // Carga el icono de limpiar en el botón btnLimpiar
        ImageIcon iconoLimpiar = new ImageIcon(getClass().getResource("/imagenes/icons8-remove-48.png"));
        btnLimpiar.setIcon(iconoLimpiar);
        // Carga el icono de datos en el label lblInfoEventoEstatico
        ImageIcon iconoDatos = new ImageIcon(getClass().getResource("/imagenes/icons8-note-50.png"));
        lblInfoEventoEstatico.setIcon(iconoDatos);
    }
//"C:\Users\luiis\Dropbox\PC\Documents\GitHub\Dise-oSoftware\AulasPresentacion\src\main\java\imagenes\reiniciar-img.png"
    private void cambiarIconoBoton(){
        // Carga el icono de reiniciar en el botón btnLimpiar
        ImageIcon iconoReiniciar = new ImageIcon("restart-48_46609.png");
        btnLimpiar.setIcon(iconoReiniciar);
    }
    
    private void añadirEvento2() {
        EventoConsultableDTO eventoN = crearEvento();
        System.out.println("parent class: "+this.parent.getClass());
        System.out.println("frm class: "+FrmAdminEventos.class);
        if(this.parent.getClass()==PrincipalCalendario.class)
            ((PrincipalCalendario)parent).añadirEvento(eventoN);
        else if(this.parent.getClass()==FrmAdminEventos.class)
            ((FrmAdminEventos)parent).agregarEvento(eventoN);
        //JOptionPane.showMessageDialog(null, "Evento añadido con exito", "Mensaje de confirmación", JOptionPane.INFORMATION_MESSAGE);

        //calendario.setVisible(true);
    }

    private EventoConsultableDTO setDatosBasicos(){
        EventoConsultableDTO eventoN=new EventoConsultableDTO();
        eventoN.setDescripcion(this.txtDescripcion.getText());
        eventoN.setNombre(this.txtNombre.getText());
        eventoN.setColor(this.lblEjemploEstatico.getForeground().toString());
        
        String duracionStr = (String) cmbDuracionEvento.getSelectedItem();
        double horasDuracion = Double.parseDouble(String.valueOf(duracionStr.charAt(0)));
        if (duracionStr.length() > 1) {
            horasDuracion=horasDuracion + 0.5;
        }
        eventoN.setHorasDuracionEvento(horasDuracion);
        
        String tipo=this.cmbTipo.getSelectedItem().toString();
        if(tipo.equals("Semanal"))
            eventoN.setTipo(TipoEventoEnumDTO.SEMANAL);
        else
            eventoN.setTipo(TipoEventoEnumDTO.UNICO_UN_DIA);
        return eventoN;
    }
    
    private EventoConsultableDTO setDatosFechasHoras(EventoConsultableDTO evento){
        EventoConsultableDTO eventoN=evento;
        if(eventoN.getTipo().equals(TipoEventoEnumDTO.SEMANAL)){
            eventoN.setFechaInicio(Calendar.getInstance());
            eventoN.setFechaFin(Calendar.getInstance());
            JCheckBox[] arrChkBx = {chbDomingo, chbLunes, chbMartes, chbMiercoles, chbJueves, chbViernes, chbSabado};
            String[] dias = {"Do", "Lu", "Ma", "Mi", "Ju", "Vi", "Sa"};
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < 7; i++) {
                if (arrChkBx[i].isSelected()) {
                    if (!stringBuilder.isEmpty() && i < 6) {
                        stringBuilder.append(",");
                    }
                    stringBuilder.append(dias[i]);
                }
            }
            if(!stringBuilder.isEmpty())
                eventoN.setDiasSemana(stringBuilder.toString());
            else System.out.println("setDatosFechasHoras: no se selecciono ningun dia");
        }else{
            Calendar fecha=dtcFecha.getCalendar();
            if(fecha!=null){
                //System.out.println("dia: "+fecha.get(Calendar.DAY_OF_MONTH));
                eventoN.setFechaInicio(fecha);
            }System.out.println("fecha null");
        }
        
        String horaSeleccionada = (String) cmbHora.getSelectedItem();
        int hora = Integer.parseInt(horaSeleccionada.substring(0, 2));
        int minutos = Integer.parseInt(horaSeleccionada.substring(3, 5));
        Calendar horaInicio = Calendar.getInstance();
        horaInicio.set(Calendar.HOUR_OF_DAY, hora);
        horaInicio.set(Calendar.MINUTE, minutos);
        
        eventoN.setHoraInicio(horaInicio);
        return eventoN;
    }
    
    private EventoConsultableDTO crearEvento() {
        EventoConsultableDTO eventoN=setDatosBasicos();
        eventoN=setDatosFechasHoras(eventoN);
        
        if(ubicacionDTO==null){
            ubicacionDTO=new UbicacionDTO(txtUbicacion.getText());
        }
        //eventoN.setUbicacion(ubicacionDTO);
        if(eventoN.getTipo().equals(TipoEventoEnumDTO.UNICO_UN_DIA))
            return new EventoConsultableDTO(eventoN.getNombre(), eventoN.getDescripcion(), 
                    eventoN.getColor(), ubicacionDTO, eventoN.getFechaInicio(), 
                    eventoN.getHoraInicio(), eventoN.getHorasDuracionEvento());
        else
            return new EventoConsultableDTO(TipoEventoEnumDTO.SEMANAL, eventoN.getNombre(),
                    eventoN.getDescripcion(), 
                    eventoN.getColor(),
                    eventoN.getDiasSemana(), ubicacionDTO, 
                    eventoN.getFechaInicio(), eventoN.getFechaFin(), 
                    eventoN.getHoraInicio(), eventoN.getHorasDuracionEvento());
    }

    public void guardarUbicacion(String ubicacion, UbicacionDTO ubicacionDTO) {
        this.txtUbicacion.setText(ubicacion);
        this.ubicacionDTO = ubicacionDTO;
    }

    /**
     * Abre el frame MapaCalendario
     */
    private void abrirMapa() {
        this.setAlwaysOnTop(false);
        new MapaCalendario(this).setVisible(true);
        //this.parent.setVisible(false);
        this.setVisible(false);
    }

    /**
     * Abre un seleccionador de color y se le asigna al evento
     */
    private void abrirSeleccionColor() {
        CuadroDialogoColor color = new CuadroDialogoColor(calendario);
        color.setVisible(true);
        this.lblEjemploEstatico.setForeground(color.getColor());
    }

    /**
     * Abre un seleccionador de fecha y se le asigna al evento
     */
    public void editarEvento() {
        //accesoCalendario=new AccesoCalendarioBO(conexion);
        EventoConsultableDTO eventoEditado = crearEvento();
        if(this.parent.getClass()==PrincipalCalendario.class)
            ((PrincipalCalendario)parent).editarEvento(eventoEditado);
        else if(this.parent.getClass()==FrmAdminEventos.class){
            eventoEditado.setId(eventoEditable.getId());
            ((FrmAdminEventos)parent).editarEvento(eventoEditado,camposModificados());
        }
            
       // calendario.editarEvento(eventoEditado);
    }

    private List<String> camposModificados(){
        JCheckBox[] chkBoxs={cbDias,cbFecha,cbHora,cbNombre,cbDescripcion,cbUbicacion,cbDuracion,cbColor};
        String[] modificaciones={"diasSemana","fechaInicio","horaIncio","nombre","descripcion","ubicacion","duracion","color"};
        List<String> campos=new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            if(chkBoxs[i].isSelected())
                campos.add(modificaciones[i]);
        }
        return campos;
    }
    
    public void desplegarEventoEditable() {
        desplegarInfo();
        //actualizarPermisos();
        btnAñadir.setEnabled(false);
        btnLimpiar.setEnabled(false);
        deshabilitarCampos();
        cambiarIconoBoton();
        mostrarCheckBox(true);
        if (eventoEditable.getTipo().equals(TipoEventoEnumDTO.SEMANAL)) {
            this.cbFecha.setVisible(false);
        }
        else {
            this.cbDias.setVisible(false);
        }
    }

    private void deshabilitarCampos(){
        this.chbLunes.setEnabled(false);
        this.chbMartes.setEnabled(false);
        this.chbMiercoles.setEnabled(false);
        this.chbJueves.setEnabled(false);
        this.chbViernes.setEnabled(false);
        this.chbSabado.setEnabled(false);
        this.chbDomingo.setEnabled(false);
        dtcFecha.setEnabled(false);
        txtNombre.setEnabled(false);
        txtDescripcion.setEnabled(false);
        txtUbicacion.setEnabled(false);
        cmbHora.setEnabled(false);
        cmbTipo.setEnabled(false);
        cmbDuracionEvento.setEnabled(false);
        btnColor.setEnabled(false);
        btnMapa.setEnabled(false);
    }
    
    private void desplegarInfo() {
        if (eventoEditable != null) {
            txtNombre.setText(this.eventoEditable.getNombre());
            txtDescripcion.setText(this.eventoEditable.getDescripcion());
            lblEjemploEstatico.setForeground(Color.getColor(this.eventoEditable.getColor()));

            if (this.eventoEditable.getUbicacion() != null) {
                txtUbicacion.setText(this.eventoEditable.getUbicacion().getIdentificador());
            }

            String duracion = String.valueOf(eventoEditable.getHorasDuracionEvento());
            cmbDuracionEvento.setSelectedItem(duracion);
            setCampoHoraInicio();
            desplegarInfoFechas();
        }
    }

    private void setCmbBoxDias(){
        String diasSemana = eventoEditable.getDiasSemana();
        //String[] dias={"Do","Lu","Ma","Mi","Ju","Vi","Sa"};
        String[] arrDiasS = diasSemana.split(",");
        for (String arrDiasS1 : arrDiasS) {
            // char dia = diasSemana.charAt(i);
            switch (arrDiasS1) {
                // Sumamos 1 para que coincida con el índice de los días de la semana
                case "Do" ->
                    chbDomingo.setSelected(true);
                case "Lu" ->
                    chbLunes.setSelected(true);
                case "Ma" ->
                    chbMartes.setSelected(true);
                case "Mi" ->
                    chbMiercoles.setSelected(true);
                case "Ju" ->
                    chbJueves.setSelected(true);
                case "Vi" ->
                    chbViernes.setSelected(true);
                case "Sa" ->
                    chbSabado.setSelected(true);
                default -> {
                }
            }
        }
    }
    private void desplegarInfoFechas(){
        if (eventoEditable.getTipo().equals(TipoEventoEnumDTO.UNICO_UN_DIA)) {
            dtcFecha.setDate(this.eventoEditable.getFechaInicio().getTime());
            cmbTipo.setSelectedIndex(1);
        } else if (eventoEditable.getTipo().equals(TipoEventoEnumDTO.SEMANAL)) {
            cmbTipo.setSelectedIndex(0);
            setCmbBoxDias();
        }
    }
    
    public void desplegarEvento() {
        desplegarInfo();
        deshabilitarCampos();
        btnAñadir.setVisible(false);
        btnLimpiar.setVisible(false);
    }

    private void mostrarCheckBox(boolean flag){
        if(flag){
            lblTipoEstatico.setText("Dias evento:");
        }else lblTipoEstatico.setText("Tipo evento:");
        this.cbDescripcion.setVisible(flag);
        this.cbNombre.setVisible(flag);
        this.cbDuracion.setVisible(flag);
        this.cbHora.setVisible(flag);
        this.cbColor.setVisible(flag);
        this.cbDias.setVisible(flag);
        this.cbFecha.setVisible(flag);
        this.cbUbicacion.setVisible(flag);
    }
    
    
    /**
     * Establece vacio los campos de texto y la fecha
     */
    private void limpiar() {
        btnLimpiar.setEnabled(false);
        btnAñadir.setEnabled(false);
        txtDescripcion.setText("");
        txtNombre.setText("");
        txtUbicacion.setText("");
        this.cmbTipo.setSelectedIndex(1);
        this.dtcFecha.setDate(Calendar.getInstance().getTime());
        this.chbLunes.setSelected(false);
        this.chbMartes.setSelected(false);
        this.chbMiercoles.setSelected(false);
        this.chbJueves.setSelected(false);
        this.chbViernes.setSelected(false);
        this.chbSabado.setSelected(false);
        this.chbDomingo.setSelected(false);
        actualizarPermisos();
    }

    private void actualizarPermisos() {
        if (this.cmbTipo.getSelectedItem().equals("Unico")) {
            this.chbLunes.setSelected(false);
            this.chbMartes.setSelected(false);
            this.chbMiercoles.setSelected(false);
            this.chbJueves.setSelected(false);
            this.chbViernes.setSelected(false);
            this.chbSabado.setSelected(false);
            this.chbDomingo.setSelected(false);

            this.chbLunes.setEnabled(false);
            this.chbMartes.setEnabled(false);
            this.chbMiercoles.setEnabled(false);
            this.chbJueves.setEnabled(false);
            this.chbViernes.setEnabled(false);
            this.chbSabado.setEnabled(false);
            this.chbDomingo.setEnabled(false);

            this.dtcFecha.setEnabled(true);
        } else if (this.cmbTipo.getSelectedItem().equals("Semanal")) {
            this.chbLunes.setEnabled(true);
            this.chbMartes.setEnabled(true);
            this.chbMiercoles.setEnabled(true);
            this.chbJueves.setEnabled(true);
            this.chbViernes.setEnabled(true);
            this.chbSabado.setEnabled(true);
            this.chbDomingo.setEnabled(true);

            this.dtcFecha.setEnabled(false);
            this.dtcFecha.setDate(null);
        }
    }
    
    private void verificarCambiosEnTxt(javax.swing.JTextField txtField, String datoAnterior){
        //if(tipoOperacion!=null && tipoOperacion.equals("editar")){
            boolean flag=!txtField.getText().equals(datoAnterior);
            btnAñadir.setEnabled(flag);
            btnLimpiar.setEnabled(flag);
        //}
    }
    private void verificarCambiosEnCmbBox(javax.swing.JComboBox<String> cmbBox, String datoAnterior){
        //if(tipoOperacion!=null && tipoOperacion.equals("editar")){
            boolean flag=!cmbBox.getSelectedItem().toString().equals(datoAnterior);
            btnAñadir.setEnabled(flag);
            btnLimpiar.setEnabled(flag);
        //}
    }
    
    private void setCampoHoraInicio(){
        int horaIn = eventoEditable.getHoraInicio().get(Calendar.HOUR_OF_DAY);
        int minutoIn = eventoEditable.getHoraInicio().get(Calendar.MINUTE);
        String horaInicio = horaIn + ":" + minutoIn;
        if (minutoIn == 0) {
            horaInicio = horaInicio + "0";
        }
        if (horaIn >= 12) {
            horaInicio = horaInicio + " PM";
        } else {
            horaInicio = horaInicio + " AM";
        }
        for (int i = 0; i < cmbHora.getModel().getSize(); i++) {
            if (horaInicio.equals(cmbHora.getModel().getElementAt(i))) {
                cmbHora.setSelectedIndex(i);
                break;
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlEvento = new javax.swing.JPanel();
        lblUbicacionEstatico = new javax.swing.JLabel();
        txtUbicacion = new javax.swing.JTextField();
        btnMapa = new javax.swing.JButton();
        lblDescripcionEstatico = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        btnAñadir = new javax.swing.JButton();
        cmbTipo = new javax.swing.JComboBox<>();
        lblTipoEstatico = new javax.swing.JLabel();
        btnColor = new javax.swing.JButton();
        lblEjemploEstatico = new javax.swing.JLabel();
        btnLimpiar = new javax.swing.JButton();
        chbLunes = new javax.swing.JCheckBox();
        chbMartes = new javax.swing.JCheckBox();
        chbMiercoles = new javax.swing.JCheckBox();
        chbJueves = new javax.swing.JCheckBox();
        chbViernes = new javax.swing.JCheckBox();
        chbSabado = new javax.swing.JCheckBox();
        chbDomingo = new javax.swing.JCheckBox();
        dtcFecha = new com.toedter.calendar.JDateChooser();
        lblFecha = new javax.swing.JLabel();
        lblHora = new javax.swing.JLabel();
        cmbHora = new javax.swing.JComboBox<>();
        lblNombreEstatico = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        btnAtras = new javax.swing.JButton();
        lblDuracion = new javax.swing.JLabel();
        cmbDuracionEvento = new javax.swing.JComboBox<>();
        lblHorasDuracion = new javax.swing.JLabel();
        cbDias = new javax.swing.JCheckBox();
        cbFecha = new javax.swing.JCheckBox();
        cbHora = new javax.swing.JCheckBox();
        cbNombre = new javax.swing.JCheckBox();
        cbUbicacion = new javax.swing.JCheckBox();
        cbDescripcion = new javax.swing.JCheckBox();
        cbDuracion = new javax.swing.JCheckBox();
        cbColor = new javax.swing.JCheckBox();
        lblInfoEventoEstatico = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        pnlEvento.setBackground(new java.awt.Color(255, 255, 255));
        pnlEvento.setLayout(null);

        lblUbicacionEstatico.setBackground(new java.awt.Color(255, 255, 255));
        lblUbicacionEstatico.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblUbicacionEstatico.setForeground(new java.awt.Color(22, 81, 198));
        lblUbicacionEstatico.setText(" Ubicación:");
        lblUbicacionEstatico.setOpaque(true);
        pnlEvento.add(lblUbicacionEstatico);
        lblUbicacionEstatico.setBounds(220, 80, 70, 20);

        txtUbicacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUbicacionKeyTyped(evt);
            }
        });
        pnlEvento.add(txtUbicacion);
        txtUbicacion.setBounds(220, 110, 200, 30);

        btnMapa.setBorder(null);
        btnMapa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMapaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMapaMouseExited(evt);
            }
        });
        btnMapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMapaActionPerformed(evt);
            }
        });
        pnlEvento.add(btnMapa);
        btnMapa.setBounds(420, 100, 50, 50);

        lblDescripcionEstatico.setBackground(new java.awt.Color(255, 255, 255));
        lblDescripcionEstatico.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblDescripcionEstatico.setForeground(new java.awt.Color(22, 81, 198));
        lblDescripcionEstatico.setText(" Descripcion del evento:");
        lblDescripcionEstatico.setOpaque(true);
        pnlEvento.add(lblDescripcionEstatico);
        lblDescripcionEstatico.setBounds(220, 150, 138, 20);

        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyTyped(evt);
            }
        });
        pnlEvento.add(txtDescripcion);
        txtDescripcion.setBounds(220, 180, 250, 100);

        btnAñadir.setBorder(null);
        btnAñadir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAñadirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAñadirMouseExited(evt);
            }
        });
        btnAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirActionPerformed(evt);
            }
        });
        pnlEvento.add(btnAñadir);
        btnAñadir.setBounds(100, 400, 70, 50);

        cmbTipo.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semanal", "Unico" }));
        cmbTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoActionPerformed(evt);
            }
        });
        pnlEvento.add(cmbTipo);
        cmbTipo.setBounds(30, 50, 160, 25);

        lblTipoEstatico.setBackground(new java.awt.Color(255, 255, 255));
        lblTipoEstatico.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblTipoEstatico.setForeground(new java.awt.Color(22, 81, 198));
        lblTipoEstatico.setText(" Tipo de evento:");
        lblTipoEstatico.setOpaque(true);
        pnlEvento.add(lblTipoEstatico);
        lblTipoEstatico.setBounds(30, 20, 90, 20);

        btnColor.setBackground(new java.awt.Color(22, 81, 198));
        btnColor.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        btnColor.setForeground(new java.awt.Color(255, 255, 255));
        btnColor.setText("Asignar color");
        btnColor.setBorder(null);
        btnColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColorActionPerformed(evt);
            }
        });
        pnlEvento.add(btnColor);
        btnColor.setBounds(30, 305, 140, 20);

        lblEjemploEstatico.setText("EJEMPLO COLOR");
        lblEjemploEstatico.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                lblEjemploEstaticoPropertyChange(evt);
            }
        });
        pnlEvento.add(lblEjemploEstatico);
        lblEjemploEstatico.setBounds(30, 340, 140, 16);

        btnLimpiar.setBorder(null);
        btnLimpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLimpiarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLimpiarMouseExited(evt);
            }
        });
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        pnlEvento.add(btnLimpiar);
        btnLimpiar.setBounds(310, 400, 60, 50);

        chbLunes.setBackground(new java.awt.Color(255, 255, 255));
        chbLunes.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        chbLunes.setText("Lunes");
        chbLunes.setBorder(null);
        pnlEvento.add(chbLunes);
        chbLunes.setBounds(30, 80, 85, 20);

        chbMartes.setBackground(new java.awt.Color(255, 255, 255));
        chbMartes.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        chbMartes.setText("Martes");
        chbMartes.setBorder(null);
        pnlEvento.add(chbMartes);
        chbMartes.setBounds(30, 100, 85, 20);

        chbMiercoles.setBackground(new java.awt.Color(255, 255, 255));
        chbMiercoles.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        chbMiercoles.setText("Miercoles");
        chbMiercoles.setBorder(null);
        chbMiercoles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbMiercolesActionPerformed(evt);
            }
        });
        pnlEvento.add(chbMiercoles);
        chbMiercoles.setBounds(30, 120, 85, 20);

        chbJueves.setBackground(new java.awt.Color(255, 255, 255));
        chbJueves.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        chbJueves.setText("Jueves");
        chbJueves.setBorder(null);
        chbJueves.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbJuevesActionPerformed(evt);
            }
        });
        pnlEvento.add(chbJueves);
        chbJueves.setBounds(30, 140, 85, 20);

        chbViernes.setBackground(new java.awt.Color(255, 255, 255));
        chbViernes.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        chbViernes.setText("Viernes");
        chbViernes.setBorder(null);
        pnlEvento.add(chbViernes);
        chbViernes.setBounds(120, 80, 85, 20);

        chbSabado.setBackground(new java.awt.Color(255, 255, 255));
        chbSabado.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        chbSabado.setText("Sabado");
        chbSabado.setBorder(null);
        pnlEvento.add(chbSabado);
        chbSabado.setBounds(120, 100, 85, 20);

        chbDomingo.setBackground(new java.awt.Color(255, 255, 255));
        chbDomingo.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        chbDomingo.setText("Domingo");
        chbDomingo.setBorder(null);
        pnlEvento.add(chbDomingo);
        chbDomingo.setBounds(120, 120, 85, 20);

        dtcFecha.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtcFechaPropertyChange(evt);
            }
        });
        pnlEvento.add(dtcFecha);
        dtcFecha.setBounds(30, 200, 160, 30);

        lblFecha.setBackground(new java.awt.Color(255, 255, 255));
        lblFecha.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(22, 81, 198));
        lblFecha.setText(" Fecha:");
        lblFecha.setOpaque(true);
        pnlEvento.add(lblFecha);
        lblFecha.setBounds(30, 176, 50, 20);

        lblHora.setBackground(new java.awt.Color(255, 255, 255));
        lblHora.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblHora.setForeground(new java.awt.Color(22, 81, 198));
        lblHora.setText(" Hora:");
        lblHora.setOpaque(true);
        pnlEvento.add(lblHora);
        lblHora.setBounds(30, 240, 40, 20);

        cmbHora.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "07:00 AM", "07:30 AM", "08:00 AM", "08:30 AM", "09:00 AM", "09:30 AM", "10:00 AM", "10:30 AM", "11:00 AM", "11:30 AM", "12:00 PM", "12:30 PM", "13:00 PM", "13:30 PM", "14:00 PM", "14:30 PM", "15:00 PM", "15:30 PM", "16:00 PM", "16:30 PM", "17:00 PM", "17:30 PM", "18:00 PM", "18:30 PM", "19:00 PM", "19:30 PM", "20:00 PM", "20:30 PM" }));
        pnlEvento.add(cmbHora);
        cmbHora.setBounds(30, 270, 110, 26);

        lblNombreEstatico.setBackground(new java.awt.Color(255, 255, 255));
        lblNombreEstatico.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblNombreEstatico.setForeground(new java.awt.Color(22, 81, 198));
        lblNombreEstatico.setText(" Nombre del evento:");
        lblNombreEstatico.setOpaque(true);
        pnlEvento.add(lblNombreEstatico);
        lblNombreEstatico.setBounds(220, 20, 120, 20);

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        pnlEvento.add(txtNombre);
        txtNombre.setBounds(220, 50, 250, 28);

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
        pnlEvento.add(btnAtras);
        btnAtras.setBounds(10, 470, 50, 40);

        lblDuracion.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblDuracion.setForeground(new java.awt.Color(22, 81, 198));
        lblDuracion.setText("Duracion del evento:");
        pnlEvento.add(lblDuracion);
        lblDuracion.setBounds(220, 290, 120, 16);

        cmbDuracionEvento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "1.5", "2", "2.5", "3", "3.5", "4", "4.5", "5" }));
        cmbDuracionEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDuracionEventoActionPerformed(evt);
            }
        });
        pnlEvento.add(cmbDuracionEvento);
        cmbDuracionEvento.setBounds(270, 310, 47, 26);

        lblHorasDuracion.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblHorasDuracion.setForeground(new java.awt.Color(22, 81, 198));
        lblHorasDuracion.setText("horas:");
        pnlEvento.add(lblHorasDuracion);
        lblHorasDuracion.setBounds(220, 310, 40, 16);

        cbDias.setBackground(new java.awt.Color(255, 255, 255));
        cbDias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDiasActionPerformed(evt);
            }
        });
        pnlEvento.add(cbDias);
        cbDias.setBounds(130, 20, 24, 24);

        cbFecha.setBackground(new java.awt.Color(255, 255, 255));
        cbFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFechaActionPerformed(evt);
            }
        });
        pnlEvento.add(cbFecha);
        cbFecha.setBounds(80, 170, 24, 24);

        cbHora.setBackground(new java.awt.Color(255, 255, 255));
        cbHora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbHoraActionPerformed(evt);
            }
        });
        pnlEvento.add(cbHora);
        cbHora.setBounds(70, 240, 24, 24);

        cbNombre.setBackground(new java.awt.Color(255, 255, 255));
        cbNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNombreActionPerformed(evt);
            }
        });
        pnlEvento.add(cbNombre);
        cbNombre.setBounds(340, 20, 24, 24);

        cbUbicacion.setBackground(new java.awt.Color(255, 255, 255));
        cbUbicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbUbicacionActionPerformed(evt);
            }
        });
        pnlEvento.add(cbUbicacion);
        cbUbicacion.setBounds(290, 80, 24, 24);

        cbDescripcion.setBackground(new java.awt.Color(255, 255, 255));
        cbDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDescripcionActionPerformed(evt);
            }
        });
        pnlEvento.add(cbDescripcion);
        cbDescripcion.setBounds(360, 150, 24, 24);

        cbDuracion.setBackground(new java.awt.Color(255, 255, 255));
        cbDuracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDuracionActionPerformed(evt);
            }
        });
        pnlEvento.add(cbDuracion);
        cbDuracion.setBounds(340, 290, 24, 24);

        cbColor.setBackground(new java.awt.Color(255, 255, 255));
        cbColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbColorActionPerformed(evt);
            }
        });
        pnlEvento.add(cbColor);

        cbColor.setBounds(170, 330, 19, 19);

        cbColor.setBounds(170, 330, 24, 24);
        pnlEvento.setBounds(0, 60, 500, 520);
        lblInfoEventoEstatico.setBackground(new java.awt.Color(22, 81, 198));
        lblInfoEventoEstatico.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblInfoEventoEstatico.setForeground(new java.awt.Color(255, 255, 255));
        lblInfoEventoEstatico.setText("Datos del evento");
        lblInfoEventoEstatico.setOpaque(true);
        getContentPane().add(lblInfoEventoEstatico);
        lblInfoEventoEstatico.setBounds(0, 0, 500, 60);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMapaActionPerformed
        abrirMapa();
    }//GEN-LAST:event_btnMapaActionPerformed

    private void btnAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirActionPerformed
        this.dispose();
        if (tipoOperacion.equals("editar")) {
            editarEvento();
        } else {
            añadirEvento2();
        }
        //this.dispose();
    }//GEN-LAST:event_btnAñadirActionPerformed

    private void btnColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColorActionPerformed
        abrirSeleccionColor();
    }//GEN-LAST:event_btnColorActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        if(tipoOperacion!=null && tipoOperacion.equals("editar")){
            desplegarEventoEditable();
        }else limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        this.dispose();
        this.parent.setVisible(true);
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void cmbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoActionPerformed
        actualizarPermisos();
    }//GEN-LAST:event_cmbTipoActionPerformed

    private void btnAtrasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAtrasMouseEntered
        // TODO add your handling code here:
        btnAtras.setBackground(Color.lightGray);
    }//GEN-LAST:event_btnAtrasMouseEntered

    private void btnLimpiarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarMouseEntered
        // TODO add your handling code here:
        btnLimpiar.setBackground(Color.lightGray);
    }//GEN-LAST:event_btnLimpiarMouseEntered

    private void btnAñadirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAñadirMouseEntered
        // TODO add your handling code here:
        btnAñadir.setBackground(Color.lightGray);
    }//GEN-LAST:event_btnAñadirMouseEntered

    private void btnMapaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMapaMouseEntered
        // TODO add your handling code here:
        btnMapa.setBackground(Color.lightGray);
    }//GEN-LAST:event_btnMapaMouseEntered

    private void chbJuevesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbJuevesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbJuevesActionPerformed

    private void chbMiercolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbMiercolesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbMiercolesActionPerformed

    private void btnAtrasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAtrasMouseExited
        // TODO add your handling code here:
        btnAtras.setBackground(Color.WHITE);
    }//GEN-LAST:event_btnAtrasMouseExited

    private void btnLimpiarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarMouseExited
        // TODO add your handling code here:
        btnLimpiar.setBackground(Color.WHITE);
    }//GEN-LAST:event_btnLimpiarMouseExited

    private void btnAñadirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAñadirMouseExited
        // TODO add your handling code here:
        btnAñadir.setBackground(Color.WHITE);
    }//GEN-LAST:event_btnAñadirMouseExited

    private void btnMapaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMapaMouseExited
        // TODO add your handling code here:
        btnMapa.setBackground(Color.WHITE);
    }//GEN-LAST:event_btnMapaMouseExited

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        //PrincipalCalendario.isDisplayed=false;
    }//GEN-LAST:event_formWindowClosing

    private void cbDiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDiasActionPerformed
        // TODO add your handling code here:
        boolean flag = cbDias.isSelected();
        if(flag)
            setCmbBoxDias();
        chbDomingo.setEnabled(flag);
        chbLunes.setEnabled(flag);
        chbMartes.setEnabled(flag);
        chbMiercoles.setEnabled(flag);
        chbJueves.setEnabled(flag);
        chbViernes.setEnabled(flag);
        chbSabado.setEnabled(flag);
    }//GEN-LAST:event_cbDiasActionPerformed

    private void cbDuracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDuracionActionPerformed
        // TODO add your handling code here:
        boolean flag=cbDuracion.isSelected();
        if(flag){
            String duracion = String.valueOf(eventoEditable.getHorasDuracionEvento());
            cmbDuracionEvento.setSelectedItem(duracion);
        }
        cmbDuracionEvento.setEnabled(flag);
    }//GEN-LAST:event_cbDuracionActionPerformed

    private void cbFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFechaActionPerformed
        // TODO add your handling code here:
        boolean flag=cbFecha.isSelected();
        if(flag)
            dtcFecha.setCalendar(eventoEditable.getFechaInicio());
        dtcFecha.setEnabled(flag);
    }//GEN-LAST:event_cbFechaActionPerformed

    private void cbHoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbHoraActionPerformed
        // TODO add your handling code here:
        boolean flag=cbHora.isSelected();
        if(flag){
            setCampoHoraInicio();
        }
        cmbHora.setEnabled(flag);
    }//GEN-LAST:event_cbHoraActionPerformed

    private void cbNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNombreActionPerformed
        // TODO add your handling code here:
        boolean flag=cbNombre.isSelected();
        if(flag)
            txtNombre.setText(eventoEditable.getNombre());
        txtNombre.setEnabled(flag);
    }//GEN-LAST:event_cbNombreActionPerformed

    private void cbUbicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbUbicacionActionPerformed
        // TODO add your handling code here:
        boolean flag=cbUbicacion.isSelected();
        if(flag)
            txtUbicacion.setText(eventoEditable.getUbicacion().getIdentificador());
        txtUbicacion.setEnabled(flag);
        btnMapa.setEnabled(flag);
    }//GEN-LAST:event_cbUbicacionActionPerformed

    private void cbDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDescripcionActionPerformed
        // TODO add your handling code here:
        boolean flag=cbDescripcion.isSelected();
        if(flag)
            txtDescripcion.setText(eventoEditable.getDescripcion());
        txtDescripcion.setEnabled(flag);
    }//GEN-LAST:event_cbDescripcionActionPerformed

    private void cbColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbColorActionPerformed
        // TODO add your handling code here:
        boolean flag=cbColor.isSelected();
        if(flag)
            lblEjemploEstatico.setForeground(Color.getColor(eventoEditable.getColor()));
        btnColor.setEnabled(flag);
    }//GEN-LAST:event_cbColorActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:
        if(tipoOperacion!=null && tipoOperacion.equals("editar"))
            verificarCambiosEnTxt(txtNombre, eventoEditable.getNombre());
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtUbicacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUbicacionKeyTyped
        // TODO add your handling code here:
        if(tipoOperacion!=null && tipoOperacion.equals("editar"))
            verificarCambiosEnTxt(txtUbicacion, eventoEditable.getUbicacion().getIdentificador());
    }//GEN-LAST:event_txtUbicacionKeyTyped

    private void txtDescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyTyped
        // TODO add your handling code here:
        if(tipoOperacion!=null && tipoOperacion.equals("editar"))
            verificarCambiosEnTxt(txtDescripcion, eventoEditable.getDescripcion());
        
    }//GEN-LAST:event_txtDescripcionKeyTyped

    private void cmbDuracionEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDuracionEventoActionPerformed
        // TODO add your handling code here:
        if(tipoOperacion!=null && tipoOperacion.equals("editar"))
            verificarCambiosEnCmbBox(cmbDuracionEvento, String.valueOf(eventoEditable.getHorasDuracionEvento()));
    }//GEN-LAST:event_cmbDuracionEventoActionPerformed

    private void lblEjemploEstaticoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_lblEjemploEstaticoPropertyChange
        // TODO add your handling code here:
        if(cbColor.isSelected() && tipoOperacion!=null && tipoOperacion.equals("editar")){
            boolean flag=!lblEjemploEstatico.getForeground().toString().equals(eventoEditable.getColor());
            btnAñadir.setEnabled(flag);
            btnLimpiar.setEnabled(flag);
        }
    }//GEN-LAST:event_lblEjemploEstaticoPropertyChange

    private void dtcFechaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtcFechaPropertyChange
        // TODO add your handling code here:
        if (cbFecha.isSelected() && tipoOperacion != null && tipoOperacion.equals("editar")) {
            boolean flag = !dtcFecha.getCalendar().equals(eventoEditable.getFechaInicio());
            btnAñadir.setEnabled(flag);
            btnLimpiar.setEnabled(flag);
        }
    }//GEN-LAST:event_dtcFechaPropertyChange

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnAñadir;
    private javax.swing.JButton btnColor;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnMapa;
    private javax.swing.JCheckBox cbColor;
    private javax.swing.JCheckBox cbDescripcion;
    private javax.swing.JCheckBox cbDias;
    private javax.swing.JCheckBox cbDuracion;
    private javax.swing.JCheckBox cbFecha;
    private javax.swing.JCheckBox cbHora;
    private javax.swing.JCheckBox cbNombre;
    private javax.swing.JCheckBox cbUbicacion;
    private javax.swing.JCheckBox chbDomingo;
    private javax.swing.JCheckBox chbJueves;
    private javax.swing.JCheckBox chbLunes;
    private javax.swing.JCheckBox chbMartes;
    private javax.swing.JCheckBox chbMiercoles;
    private javax.swing.JCheckBox chbSabado;
    private javax.swing.JCheckBox chbViernes;
    private javax.swing.JComboBox<String> cmbDuracionEvento;
    private javax.swing.JComboBox<String> cmbHora;
    private javax.swing.JComboBox<String> cmbTipo;
    private com.toedter.calendar.JDateChooser dtcFecha;
    private javax.swing.JLabel lblDescripcionEstatico;
    private javax.swing.JLabel lblDuracion;
    private javax.swing.JLabel lblEjemploEstatico;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblHorasDuracion;
    private javax.swing.JLabel lblInfoEventoEstatico;
    private javax.swing.JLabel lblNombreEstatico;
    private javax.swing.JLabel lblTipoEstatico;
    private javax.swing.JLabel lblUbicacionEstatico;
    private javax.swing.JPanel pnlEvento;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtUbicacion;
    // End of variables declaration//GEN-END:variables
}
