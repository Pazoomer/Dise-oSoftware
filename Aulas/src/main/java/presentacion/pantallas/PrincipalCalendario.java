
package presentacion.pantallas;

import DTOS.evento.EventoConsultableDTO;
import DTOS.maestro.MaestroEditableDTO;
import com.toedter.calendar.JCalendar;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import presentacion.CuadroDialogoColor;

/**
 *
 * @author t1pas
 */
public class PrincipalCalendario extends javax.swing.JFrame {

    /**
     * Es el maestro al que le pertenece el calendario
     */
    private MaestroEditableDTO maestro;
    
    /**
     * Creates new form PrincipalCalendario
     * @param maestro
     */
    public PrincipalCalendario(MaestroEditableDTO maestro) {
        initComponents();
        this.maestro=maestro;
        cargarEvento();
    }
    
    //TODO
    //Deberia cargar los datos del evento seleccionado en los campos de texto
    private void cargarEvento() {
        
        
    }
    
    //TODO
    //Al hacer clic sobre el boton deberia intentar añadir el evento al calendario.
    //primero se asegura que no haya eventos a la misma hora
    private void añadirEvento(){
       
    }

    //TODO
    //Al hacer clic en guardar calendario debe actualizar el calendario del maestro y guardar al maestro en la base de datos
    private void guardarCalendario() {

    }
    
    //TODO
    //Cierra este frame y abre el frame PrincipalMaestro
    private void cerrar(){
        
    }
    
    //TODO
    //Abre el frame MapaCalendario y esconde este
    private void abrirMapa(){
        
    }

    //TODO
    //Cambia el mes del calendario mostrando los eventos de ese mes
    private void cambiarMes(){
        
    }

    //TODO
    //Abre un seleccionador de color y se le asigna al evento
    private void abrirSeleccionColor() {
        CuadroDialogoColor color = new CuadroDialogoColor(this);
        color.setVisible(true);
    }

    //TODO
    //Abre un seleccionador de fecha y se le asigna al evento
    private void abrirSeleccionFecha(){
        JCalendar calendar=new JCalendar();
        calendar.setVisible(true);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGuardar = new javax.swing.JButton();
        lblGuardar = new javax.swing.JLabel();
        btnAtras = new javax.swing.JButton();
        pnlCalendario = new javax.swing.JPanel();
        mchMes = new com.toedter.calendar.JMonthChooser();
        pnlEvento = new javax.swing.JPanel();
        lblInfoEventoEstatico = new javax.swing.JLabel();
        lblUbicacionEstatico = new javax.swing.JLabel();
        txtUbicacion = new javax.swing.JTextField();
        btnMapa = new javax.swing.JButton();
        lblHoraEstatico = new javax.swing.JLabel();
        cmbHora = new javax.swing.JComboBox<>();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnGuardar.setText("Imagen de guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        lblGuardar.setText("Guardar");

        btnAtras.setText("Imagen de volver");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        pnlCalendario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout pnlCalendarioLayout = new javax.swing.GroupLayout(pnlCalendario);
        pnlCalendario.setLayout(pnlCalendarioLayout);
        pnlCalendarioLayout.setHorizontalGroup(
            pnlCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 344, Short.MAX_VALUE)
        );
        pnlCalendarioLayout.setVerticalGroup(
            pnlCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 340, Short.MAX_VALUE)
        );

        lblInfoEventoEstatico.setText("Selecciona un evento para editar");

        lblUbicacionEstatico.setText("Ubicacion");

        btnMapa.setText("Imagen de mapa");
        btnMapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMapaActionPerformed(evt);
            }
        });

        lblHoraEstatico.setText("Hora");

        cmbHora.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "12:00 AM", "1:00 AM", "2:00 AM", "3:00 AM", "4:00 AM", "5:00 AM", "6:00 AM", "7:00 AM", "8:00 AM", "9:00 AM", "10:00 AM", "11:00 AM", "12:00 PM", "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM", "5:00 PM", "6:00 PM", "7:00 PM", "8:00 PM", "9:00 PM", "10:00 PM", "11:00 PM" }));

        lblNombreEstatico.setText("Nombre");

        lblDescripcionEstatico.setText("Descripcion del evento");

        btnAñadir.setText("Imagen de añadir");
        btnAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirActionPerformed(evt);
            }
        });

        lblAñadir.setText("Añadir evento");

        btnCalendario.setText("Fecha");
        btnCalendario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalendarioActionPerformed(evt);
            }
        });

        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Unico", "Semanal" }));

        jLabel1.setText("Tipo de evento");

        btnColor.setText("Color");
        btnColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlEventoLayout = new javax.swing.GroupLayout(pnlEvento);
        pnlEvento.setLayout(pnlEventoLayout);
        pnlEventoLayout.setHorizontalGroup(
            pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEventoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlEventoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblInfoEventoEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlEventoLayout.createSequentialGroup()
                        .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(pnlEventoLayout.createSequentialGroup()
                                .addComponent(lblHoraEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblDescripcionEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlEventoLayout.createSequentialGroup()
                                .addComponent(btnCalendario)
                                .addGap(37, 37, 37)
                                .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblNombreEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlEventoLayout.createSequentialGroup()
                                .addComponent(txtUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlEventoLayout.createSequentialGroup()
                        .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnColor))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
            .addGroup(pnlEventoLayout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAñadir)
                    .addGroup(pnlEventoLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(lblAñadir)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnlEventoLayout.createSequentialGroup()
                .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEventoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblUbicacionEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEventoLayout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlEventoLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlEventoLayout.setVerticalGroup(
            pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEventoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblInfoEventoEstatico)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(lblUbicacionEstatico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMapa, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
                .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEventoLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblNombreEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEventoLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(btnCalendario)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblDescripcionEstatico)
                    .addComponent(lblHoraEstatico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlEventoLayout.createSequentialGroup()
                        .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(lblAñadir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(pnlEventoLayout.createSequentialGroup()
                        .addComponent(cmbHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnColor)
                        .addGap(32, 32, 32)))
                .addComponent(btnAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAtras)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardar)
                        .addGap(170, 170, 170))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlEvento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(mchMes, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(148, 148, 148))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(pnlCalendario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(53, Short.MAX_VALUE))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(198, 198, 198))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(mchMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnlCalendario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlEvento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardarCalendario();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirActionPerformed
        añadirEvento();
    }//GEN-LAST:event_btnAñadirActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        cerrar();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnMapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMapaActionPerformed
        abrirMapa();
    }//GEN-LAST:event_btnMapaActionPerformed

    private void btnColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColorActionPerformed
        abrirSeleccionColor();
    }//GEN-LAST:event_btnColorActionPerformed

    private void btnCalendarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalendarioActionPerformed
        abrirSeleccionFecha();
    }//GEN-LAST:event_btnCalendarioActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PrincipalCalendario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalCalendario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalCalendario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalCalendario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //TODO
                //Este maestro es solo una fachada
                List<EventoConsultableDTO> calendario = new ArrayList<>();

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, 10);
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);

                String rutaRealtiva = "fotoMaestro.png";

                ImageIcon icon = new ImageIcon(rutaRealtiva);
                
                ImageIcon scaledIcon = new ImageIcon(icon.getImage().getScaledInstance(120, 100, Image.SCALE_SMOOTH));

                EventoConsultableDTO evento = new EventoConsultableDTO("semanal", "Bases de datos", "...", Color.BLUE, null, calendar);

                calendario.add(evento);

                MaestroEditableDTO maestro = new MaestroEditableDTO(1L, "Gibran Duran", "AV0900", "Doy asesorias de 9 a 11 de bases de datos los sabados y domingos", scaledIcon, calendario);

                new PrincipalCalendario(maestro).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnAñadir;
    private javax.swing.JButton btnCalendario;
    private javax.swing.JButton btnColor;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnMapa;
    private javax.swing.JComboBox<String> cmbHora;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblAñadir;
    private javax.swing.JLabel lblDescripcionEstatico;
    private javax.swing.JLabel lblGuardar;
    private javax.swing.JLabel lblHoraEstatico;
    private javax.swing.JLabel lblInfoEventoEstatico;
    private javax.swing.JLabel lblNombreEstatico;
    private javax.swing.JLabel lblUbicacionEstatico;
    private com.toedter.calendar.JMonthChooser mchMes;
    private javax.swing.JPanel pnlCalendario;
    private javax.swing.JPanel pnlEvento;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtUbicacion;
    // End of variables declaration//GEN-END:variables
}
