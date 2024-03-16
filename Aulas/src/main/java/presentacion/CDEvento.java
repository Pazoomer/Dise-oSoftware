
package presentacion;

import DTOS.evento.EventoConsultableDTO;
import java.awt.Color;
import java.util.Calendar;
import java.util.Date;
import presentacion.pantallas.MapaCalendario;
import presentacion.pantallas.PrincipalCalendario;

/**
 *
 * @author t1pas
 */
public class CDEvento extends javax.swing.JDialog {

    public final PrincipalCalendario calendario;
    private final java.awt.Frame parent;
    /**
     * Creates new form CDEvento
     * @param parent
     * @param calendario
     * @param modal
     */
    public CDEvento(java.awt.Frame parent,PrincipalCalendario calendario, boolean modal) {
        super(parent, modal);
        initComponents();
        this.calendario=calendario;
        this.parent=parent;
    }

    private void habilitarCampos(){
        txtDescripcion.setEnabled(false);
        txtNombre.setEnabled(false);
        txtUbicacion.setEnabled(false);
        cmbTipo.setEnabled(false);
        spdFecha.setEnabled(false);
        btnColor.setEnabled(false);
    }
    
    //TODO
    //Al hacer clic sobre el boton deberia intentar añadir el evento al calendario.
    //primero se asegura que no haya eventos a la misma hora
    private void añadirEvento(){
        String tipo=(String) this.cmbTipo.getSelectedItem();
        String descripcion = this.txtDescripcion.getText();
        String nombre = this.txtNombre.getText();
        String ubicacion = this.txtUbicacion.getText();
        Date fecha = this.spdFecha.getDate();
        Color color = this.lblEjemploEstatico.getForeground();
        Calendar calendar=null;
        try {
            calendar = Calendar.getInstance();
            calendar.setTime(fecha);
        } catch (Exception e) {
            //TODO
            //Mostrar mensaje de que la fecha no es valida
        }
        if (calendar==null) {
            //TODO
            //Mostrar mensaje de que la fecha no es valida
        }
 
        EventoConsultableDTO evento = new EventoConsultableDTO(tipo, nombre, descripcion, color, ubicacion, calendar);
        System.out.println(evento);
        calendario.añadirEvento(evento);
        
    }
    
    public void guardarUbicacion(String ubicacion){
        this.txtUbicacion.setText(ubicacion);
    }
    
    /**
     * Abre el frame MapaCalendario
     */
    private void abrirMapa(){
        new MapaCalendario(this).setVisible(true);
        calendario.setVisible(false);
        this.setVisible(false);
    }
    
    //TODO
    //Abre un seleccionador de color y se le asigna al evento
    private void abrirSeleccionColor() {
        CuadroDialogoColor color = new CuadroDialogoColor(parent);
        color.setVisible(true);
        this.lblEjemploEstatico.setForeground(color.getColor());
    }
    
    //TODO
    //Abre un seleccionador de fecha y se le asigna al evento
    private void abrirSeleccionFecha(){
        CuadroDialogoCalendario calendarioColor = new CuadroDialogoCalendario(parent);
        calendarioColor.setVisible(true);
        if (calendarioColor.getDate()!=null) {
            this.spdFecha.setDate(calendarioColor.getDate());  
        }
        
    }
    
    private void limpiar(){
       txtDescripcion.setText("");
        txtNombre.setText("");
        txtUbicacion.setText("");
        spdFecha.setDate(Calendar.getInstance().getTime()); 
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlEvento = new javax.swing.JPanel();
        lblInfoEventoEstatico = new javax.swing.JLabel();
        lblUbicacionEstatico = new javax.swing.JLabel();
        txtUbicacion = new javax.swing.JTextField();
        btnMapa = new javax.swing.JButton();
        lblNombreEstatico = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblDescripcionEstatico = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        btnAñadir = new javax.swing.JButton();
        lblAñadir = new javax.swing.JLabel();
        btnCalendario = new javax.swing.JButton();
        cmbTipo = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        btnColor = new javax.swing.JButton();
        spdFecha = new com.toedter.calendar.JSpinnerDateEditor();
        lblEjemploEstatico = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        limpiar = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblInfoEventoEstatico.setText("Datos del evento");

        lblUbicacionEstatico.setText("Ubicacion");

        btnMapa.setText("Imagen de mapa");
        btnMapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMapaActionPerformed(evt);
            }
        });

        lblNombreEstatico.setText("Nombre del evento");

        lblDescripcionEstatico.setText("Descripcion del evento");

        btnAñadir.setText("Imagen de añadir");
        btnAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirActionPerformed(evt);
            }
        });

        lblAñadir.setText("Añadir evento");

        btnCalendario.setText("Asignar Fecha");
        btnCalendario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalendarioActionPerformed(evt);
            }
        });

        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Unico", "Semanal" }));

        jLabel1.setText("Tipo de evento");

        btnColor.setText("Asignar color");
        btnColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColorActionPerformed(evt);
            }
        });

        lblEjemploEstatico.setText("EJEMPLO COLOR");

        jLabel2.setText("Limpiar");

        limpiar.setText("imagen de limpiar");
        limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlEventoLayout = new javax.swing.GroupLayout(pnlEvento);
        pnlEvento.setLayout(pnlEventoLayout);
        pnlEventoLayout.setHorizontalGroup(
            pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEventoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEventoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spdFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnColor)
                            .addGroup(pnlEventoLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblAñadir)
                                    .addComponent(lblEjemploEstatico))))
                        .addGap(18, 18, 18)
                        .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEventoLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(110, 110, 110))
                            .addGroup(pnlEventoLayout.createSequentialGroup()
                                .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDescripcionEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(68, 68, 68))))
                    .addGroup(pnlEventoLayout.createSequentialGroup()
                        .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUbicacionEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlEventoLayout.createSequentialGroup()
                                .addComponent(txtUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnCalendario))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlEventoLayout.createSequentialGroup()
                        .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlEventoLayout.createSequentialGroup()
                                .addComponent(btnAñadir)
                                .addGap(78, 78, 78)
                                .addComponent(limpiar))
                            .addGroup(pnlEventoLayout.createSequentialGroup()
                                .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNombreEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblInfoEventoEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pnlEventoLayout.setVerticalGroup(
            pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEventoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblInfoEventoEstatico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblNombreEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUbicacionEstatico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMapa, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCalendario)
                    .addComponent(lblDescripcionEstatico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEventoLayout.createSequentialGroup()
                        .addComponent(spdFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnColor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblEjemploEstatico))
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAñadir)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
        );

        btnAtras.setText("Imagen de volver");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAtras)
                .addContainerGap(240, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(421, Short.MAX_VALUE)
                .addComponent(btnAtras)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(pnlEvento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(53, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMapaActionPerformed
        abrirMapa();
    }//GEN-LAST:event_btnMapaActionPerformed

    private void btnAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirActionPerformed
        añadirEvento();
    }//GEN-LAST:event_btnAñadirActionPerformed

    private void btnCalendarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalendarioActionPerformed
        abrirSeleccionFecha();
    }//GEN-LAST:event_btnCalendarioActionPerformed

    private void btnColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColorActionPerformed
        abrirSeleccionColor();
    }//GEN-LAST:event_btnColorActionPerformed

    private void limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_limpiarActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnAtrasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnAñadir;
    private javax.swing.JButton btnCalendario;
    private javax.swing.JButton btnColor;
    private javax.swing.JButton btnMapa;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblAñadir;
    private javax.swing.JLabel lblDescripcionEstatico;
    private javax.swing.JLabel lblEjemploEstatico;
    private javax.swing.JLabel lblInfoEventoEstatico;
    private javax.swing.JLabel lblNombreEstatico;
    private javax.swing.JLabel lblUbicacionEstatico;
    private javax.swing.JButton limpiar;
    private javax.swing.JPanel pnlEvento;
    private com.toedter.calendar.JSpinnerDateEditor spdFecha;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtUbicacion;
    // End of variables declaration//GEN-END:variables
}
