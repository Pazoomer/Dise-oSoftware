
package presentacion;

import DTOS.campus.UbicacionDTO;
import DTOS.evento.*;
import accesoMaestro.FachadaAccesoMaestro;
import java.awt.Color;
import java.util.Calendar;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import presentacion.pantallas.MapaCalendario;
import presentacion.pantallas.PrincipalCalendario;

/**
 *
 * @author t1pas
 */
public class CDEvento extends javax.swing.JDialog {

    public final PrincipalCalendario calendario;
    private EventoConsultableDTO eventoEditable;
    private UbicacionDTO ubicacionDTO;
    private String tipoOperacion;
    
    /**
     * Creates new form CDEvento
     * @param parent
     * @param calendario
     * @param modal
     * @param tipoOperacion
     */
    public CDEvento(java.awt.Frame parent, PrincipalCalendario calendario, boolean modal, String tipoOperacion) {
        super(parent, modal);
        initComponents();
        this.calendario = calendario;
        this.tipoOperacion = tipoOperacion;
        new FachadaAccesoMaestro();
        this.setSize(500, 620);
        actualizarPermisos();
        cargarIconos();
    }

    public CDEvento(java.awt.Frame parent, PrincipalCalendario calendario, EventoConsultableDTO eventoEditable, boolean modal, String tipoOperacion) {
        super(parent, modal);
        initComponents();
        this.calendario = calendario;
        this.eventoEditable = eventoEditable;
        this.tipoOperacion = tipoOperacion;
        new FachadaAccesoMaestro();
        cargarIconos();
        this.setSize(500, 620);
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
    
    private void añadirEvento2(){
        EventoConsultableDTO eventoN=crearEvento();
        calendario.añadirEvento(eventoN);
        JOptionPane.showMessageDialog(null, "Evento añadido con exito", "Mensaje de confirmación", JOptionPane.INFORMATION_MESSAGE);
        
        calendario.setVisible(true);
    }
    
    private EventoConsultableDTO crearEvento() {
        String tipoStr = (String) this.cmbTipo.getSelectedItem();
        TipoEventoEnumDTO tipo;
        if (tipoStr.equals("Unico")) {
            tipo = TipoEventoEnumDTO.UNICO_UN_DIA;
        } else {
            tipo = TipoEventoEnumDTO.SEMANAL;
        }

        String descripcion = this.txtDescripcion.getText();
        String nombre = this.txtNombre.getText();
        //String ubicacion = this.txtUbicacion.getText();
        Calendar fecha = Calendar.getInstance();
        if (tipo.equals(TipoEventoEnumDTO.UNICO_UN_DIA)) {
            fecha = this.dtcFecha.getCalendar();
        }
        String color = this.lblEjemploEstatico.getForeground().toString();
        String duracionStr = (String) cmbDuracionEvento.getSelectedItem();
        double horasDuracion = Double.parseDouble(String.valueOf(duracionStr.charAt(0)));
        if (duracionStr.length() > 1) {
            horasDuracion = horasDuracion + 0.5f;
        }

        JCheckBox[] arrChkBx = {chbDomingo, chbLunes, chbMartes, chbMiercoles, chbJueves, chbViernes, chbSabado};
        StringBuilder stringBuilder = new StringBuilder();
        for (JCheckBox checkBox : arrChkBx) {
            // Verificar si el checkbox está seleccionado o no y agregar '1' o '0' al string
            if (checkBox.isSelected()) {
                stringBuilder.append('1');
            } else {
                stringBuilder.append('0');
            }
        }
        String diasSemana = stringBuilder.toString();

        String horaSeleccionada = (String) cmbHora.getSelectedItem();
        int hora = Integer.parseInt(horaSeleccionada.substring(0, 2));
        int minutos = Integer.parseInt(horaSeleccionada.substring(3,5));
        Calendar horaInicio = Calendar.getInstance();
        horaInicio.set(Calendar.HOUR_OF_DAY, hora);
        horaInicio.set(Calendar.MINUTE, minutos);
        //System.out.println("hora inicio evento desde cdEvento: "+hora+":"+minutos);

        EventoConsultableDTO eventoN;
        
        if (tipo.equals(TipoEventoEnumDTO.UNICO_UN_DIA)) {
            eventoN = new EventoConsultableDTO(nombre, descripcion, color, ubicacionDTO, fecha, horaInicio, horasDuracion);
        } else {
            eventoN = new EventoConsultableDTO(tipo, nombre, descripcion, color,
                    diasSemana, ubicacionDTO, fecha, fecha, horaInicio, horasDuracion);
        }

        JOptionPane.showMessageDialog(null, "Evento añadido con exito", "Mensaje de confirmación", JOptionPane.INFORMATION_MESSAGE);

//calendario.añadirEvento(eventoN);
        return eventoN;
    }

    public void guardarUbicacion(String ubicacion, UbicacionDTO ubicacionDTO) {
        this.txtUbicacion.setText(ubicacion);
        this.ubicacionDTO=ubicacionDTO;
    }
    
    /**
     * Abre el frame MapaCalendario
     */
    private void abrirMapa(){
        new MapaCalendario(this).setVisible(true);
        calendario.setVisible(false);
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
    public void editarEvento(){
        //accesoCalendario=new AccesoCalendarioBO(conexion);
        EventoConsultableDTO eventoEditado=crearEvento();
        calendario.editarEvento(eventoEditado);
    }
    
    public void desplegarEventoEditable(){
        desplegarInfo();
        actualizarPermisos();
        btnLimpiar.setVisible(false);
    }
    
    private void desplegarInfo() {

        if (eventoEditable != null) {

            txtNombre.setText(this.eventoEditable.getNombre());
            txtDescripcion.setText(this.eventoEditable.getDescripcion());

            if (this.eventoEditable.getUbicacion() != null) {
                txtUbicacion.setText(this.eventoEditable.getUbicacion().getIdentificador());
            }

            String duracion = String.valueOf(eventoEditable.getHorasDuracionEvento());
//        if(duracion.charAt(2)!=0)
            cmbDuracionEvento.setSelectedItem(duracion);
            int horaIn = eventoEditable.getHoraInicio().get(Calendar.HOUR);
            int minutoIn = eventoEditable.getHoraInicio().get(Calendar.MINUTE);
            String horaInicio = horaIn + ":" + minutoIn;
            if (horaIn >= 12)
            horaInicio = horaInicio + " PM";
            else
                horaInicio = horaInicio + " AM";
            System.out.println("metodo desplegar info");
            System.out.println("hora inicio: " + horaInicio);
            for (int i = 0; i < cmbHora.getModel().getSize(); i++) {
                if (horaInicio.equals(cmbHora.getModel().getElementAt(i))) {
                    cmbHora.setSelectedIndex(i);
                    break;
                }
            }

            if (eventoEditable.getTipo().equals(TipoEventoEnumDTO.UNICO_UN_DIA)) {
                dtcFecha.setDate(this.eventoEditable.getFechaInicio().getTime());
                cmbTipo.setSelectedIndex(1);
            } else if (eventoEditable.getTipo().equals(TipoEventoEnumDTO.SEMANAL)) {
                cmbTipo.setSelectedIndex(0);
                String diasSemana = eventoEditable.getDiasSemana();
            for (int i = 0; i < diasSemana.length(); i++) {
                    char dia = diasSemana.charAt(i);
                switch (i + 1) { // Sumamos 1 para que coincida con el índice de los días de la semana
                        case 1 ->
                        chbDomingo.setSelected(dia == '1');
                    case 2 ->
                        chbLunes.setSelected(dia == '1');
                    case 3 ->
                        chbMartes.setSelected(dia == '1');
                    case 4 ->
                        chbMiercoles.setSelected(dia == '1');
                    case 5 ->
                        chbJueves.setSelected(dia == '1');
                    case 6 ->
                        chbViernes.setSelected(dia == '1');
                    case 7 ->
                        chbSabado.setSelected(dia == '1');
                        default -> {
                        }
                    }
                }
 {
                    
                    // Sumamos 1 para que coincida con el índice de los días de la semana
                    // Manejar cualquier otro caso si es necesario
                }

            }
        }
    }

    public void desplegarEvento(){
        desplegarInfo();
        txtNombre.setEnabled(false);
        txtDescripcion.setEnabled(false);
        txtUbicacion.setEnabled(false);
        cmbHora.setEnabled(false);
        cmbTipo.setEnabled(false);
        cmbDuracionEvento.setEnabled(false);
        dtcFecha.setEnabled(false);
        chbDomingo.setEnabled(false);
        chbLunes.setEnabled(false);
        chbMartes.setEnabled(false);
        chbMiercoles.setEnabled(false);
        chbJueves.setEnabled(false);
        chbViernes.setEnabled(false);
        chbSabado.setEnabled(false);
        btnLimpiar.setVisible(false);
        btnAñadir.setVisible(false);
    }
    
    /**
     * Establece vacio los campos de texto y la fecha
     */
    private void limpiar(){
       txtDescripcion.setText("");
        txtNombre.setText("");
        txtUbicacion.setText("");
        this.dtcFecha.setDate(null);
        this.chbLunes.setSelected(false);
        this.chbMartes.setSelected(false);
        this.chbMiercoles.setSelected(false);
        this.chbJueves.setSelected(false);
        this.chbViernes.setSelected(false);
        this.chbSabado.setSelected(false);
        this.chbDomingo.setSelected(false);
        actualizarPermisos();
    }
    
     private void actualizarPermisos(){
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
        }else if (this.cmbTipo.getSelectedItem().equals("Semanal")) {
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
        lblInfoEventoEstatico = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
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
        lblUbicacionEstatico.setBounds(220, 90, 200, 20);
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
        lblDescripcionEstatico.setBounds(220, 150, 138, 14);
        pnlEvento.add(txtDescripcion);
        txtDescripcion.setBounds(220, 170, 250, 100);

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
        cmbTipo.setBounds(30, 50, 160, 21);

        lblTipoEstatico.setBackground(new java.awt.Color(255, 255, 255));
        lblTipoEstatico.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblTipoEstatico.setForeground(new java.awt.Color(22, 81, 198));
        lblTipoEstatico.setText(" Tipo de evento:");
        lblTipoEstatico.setOpaque(true);
        pnlEvento.add(lblTipoEstatico);
        lblTipoEstatico.setBounds(30, 30, 160, 20);

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
        chbLunes.setBounds(30, 80, 85, 17);

        chbMartes.setBackground(new java.awt.Color(255, 255, 255));
        chbMartes.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        chbMartes.setText("Martes");
        chbMartes.setBorder(null);
        pnlEvento.add(chbMartes);
        chbMartes.setBounds(30, 100, 85, 17);

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
        chbMiercoles.setBounds(30, 120, 85, 17);

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
        chbJueves.setBounds(30, 140, 85, 17);

        chbViernes.setBackground(new java.awt.Color(255, 255, 255));
        chbViernes.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        chbViernes.setText("Viernes");
        chbViernes.setBorder(null);
        pnlEvento.add(chbViernes);
        chbViernes.setBounds(120, 80, 85, 17);

        chbSabado.setBackground(new java.awt.Color(255, 255, 255));
        chbSabado.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        chbSabado.setText("Sabado");
        chbSabado.setBorder(null);
        pnlEvento.add(chbSabado);
        chbSabado.setBounds(120, 100, 85, 17);

        chbDomingo.setBackground(new java.awt.Color(255, 255, 255));
        chbDomingo.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        chbDomingo.setText("Domingo");
        chbDomingo.setBorder(null);
        pnlEvento.add(chbDomingo);
        chbDomingo.setBounds(120, 120, 85, 17);
        pnlEvento.add(dtcFecha);
        dtcFecha.setBounds(30, 200, 160, 30);

        lblFecha.setBackground(new java.awt.Color(255, 255, 255));
        lblFecha.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(22, 81, 198));
        lblFecha.setText(" Fecha:");
        lblFecha.setOpaque(true);
        pnlEvento.add(lblFecha);
        lblFecha.setBounds(30, 176, 160, 20);

        lblHora.setBackground(new java.awt.Color(255, 255, 255));
        lblHora.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblHora.setForeground(new java.awt.Color(22, 81, 198));
        lblHora.setText(" Hora:");
        lblHora.setOpaque(true);
        pnlEvento.add(lblHora);
        lblHora.setBounds(30, 240, 160, 20);

        cmbHora.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "07:00 AM", "07:30 AM", "08:00 AM", "08:30 AM", "09:00 AM", "09:30 AM", "10:00 AM", "10:30 AM", "11:00 AM", "11:30 AM", "12:00 PM", "12:30 PM", "13:00 PM", "13:30 PM", "14:00 PM", "14:30 PM", "15:00 PM", "15:30 PM", "16:00 PM", "16:30 PM", "17:00 PM", "17:30 PM", "18:00 PM", "18:30 PM", "19:00 PM", "19:30 PM", "20:00 PM", "20:30" }));
        pnlEvento.add(cmbHora);
        cmbHora.setBounds(30, 260, 110, 22);

        lblNombreEstatico.setBackground(new java.awt.Color(255, 255, 255));
        lblNombreEstatico.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblNombreEstatico.setForeground(new java.awt.Color(22, 81, 198));
        lblNombreEstatico.setText(" Nombre del evento:");
        lblNombreEstatico.setOpaque(true);
        pnlEvento.add(lblNombreEstatico);
        lblNombreEstatico.setBounds(220, 30, 250, 20);
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
        lblDuracion.setBounds(220, 280, 120, 16);

        cmbDuracionEvento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "1.5", "2", "2.5", "3", "3.5", "4", "4.5", "5" }));
        pnlEvento.add(cmbDuracionEvento);
        cmbDuracionEvento.setBounds(270, 310, 72, 22);

        lblHorasDuracion.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblHorasDuracion.setForeground(new java.awt.Color(22, 81, 198));
        lblHorasDuracion.setText("horas:");
        pnlEvento.add(lblHorasDuracion);
        lblHorasDuracion.setBounds(220, 310, 40, 16);

        getContentPane().add(pnlEvento);
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
        if(tipoOperacion.equals("editar")){
            editarEvento();
        }else
            añadirEvento2();
        this.dispose();
    }//GEN-LAST:event_btnAñadirActionPerformed

    private void btnColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColorActionPerformed
        abrirSeleccionColor();
    }//GEN-LAST:event_btnColorActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        this.dispose();
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnAñadir;
    private javax.swing.JButton btnColor;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnMapa;
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
