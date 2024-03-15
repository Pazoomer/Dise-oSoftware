
package presentacion.pantallas;

import DTOS.evento.EventoConsultableDTO;
import DTOS.maestro.MaestroEditableDTO;
import excepciones.PersistenciaException;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import subsistemas.accesoMaestro.IAccesoMaestro;
import subsistemas.accesoMaestro.FachadaEditarMaestro;

/**
 *
 * @author t1pas
 */
public class PrincipalMaestro extends javax.swing.JFrame {

    /**
     * Es el maestro con el que iniciaste sesion
     */
    private MaestroEditableDTO maestro;
    
    /**
     * Permite el acceso a editar la informacion del maestro
     */
    private IAccesoMaestro accesoM;
    /**
     * Creates new form PrincipalMaestro
     * @param maestro
     */
    public PrincipalMaestro(MaestroEditableDTO maestro) {
        
        initComponents();
        this.maestro=maestro;
        cargarMaestro();
        this.setVisible(true);
    }

    /**
     * Coloca los valores del maestro en los campos de texto y foto de perfil
     */
    private void cargarMaestro() {
        this.txtCubiculo.setText(maestro.getCubiculo());
        this.txaDescripcion.setText(maestro.getDescripcion());
        this.lblNombreMaestro.setText(maestro.getNombre());     
        this.lblFotoMaestro.setIcon(maestro.getFoto());
    }
    
    /**
     * Toma los campos de texto y foto de perfil y la guarda en la base de datos actualizando el perfil de maestro
     */
    private void editarInformacion(){
        //TODO
        //Mostrar un cuadro de dialogo donde se le pregunte confirmacion para editar su información
        String descripcion=this.txaDescripcion.getText();
        String cubiculo=this.txtCubiculo.getText();
        Icon foto=this.lblFotoMaestro.getIcon();
        
        accesoM=new FachadaEditarMaestro();
    
        MaestroEditableDTO maestroAuxiliar=new MaestroEditableDTO(maestro.getId(),maestro.getNombre(),cubiculo,descripcion,foto);
        try {
            maestro=accesoM.editarMaestro(maestroAuxiliar);
        } catch (PersistenciaException ex) {
            Logger.getLogger(PrincipalMaestro.class.getName()).log(Level.SEVERE, "No se pudo actualizar la informacion en la base de datos", ex);
        }
        //TODO
        //Mostrar un mensaje diciendo que la actualizacion fue exitosa
    }

    /**
     * Permite cambiar la foto de perfil al darle clic por una en la carpeta de
     * archivos
     */
    private void añadirImagen() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());

            ImageIcon scaledIcon = new ImageIcon(icon.getImage().getScaledInstance(124, 102, Image.SCALE_SMOOTH));
            this.lblFotoMaestro.setIcon(scaledIcon);
            this.lblFotoMaestro.setText(""); // Clear text
        }

    }

    //TODO
    //Al dar clic en el boton de calendario deberia desplegarse el frame PrincipalCalendario y ocultarse este frame
    private void abrirCalendario() {
        new PrincipalCalendario(this, maestro).setVisible(true);
        this.setVisible(false);
    }


    /**
     * Cierra este frame y por consecuencia se acaba la ejecucion del programa
     */
    private void cerrar() {
        this.dispose();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCalendario = new javax.swing.JButton();
        lblFotoMaestro = new javax.swing.JLabel();
        lblNombreMaestro = new javax.swing.JLabel();
        lblCalendario = new javax.swing.JLabel();
        btnAtras = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        lblActualizar = new javax.swing.JLabel();
        lblBienvenido = new javax.swing.JLabel();
        lblCubiculoEstatico = new javax.swing.JLabel();
        lblDescripcionEstatico = new javax.swing.JLabel();
        txtCubiculo = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaDescripcion = new javax.swing.JTextArea();
        lblInfoFotoEstatico = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnCalendario.setText("Imagen de calendario");
        btnCalendario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalendarioActionPerformed(evt);
            }
        });

        lblFotoMaestro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblFotoMaestro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFotoMaestroMouseClicked(evt);
            }
        });

        lblNombreMaestro.setText("Nombre completo del maestro");
        lblNombreMaestro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblCalendario.setText("Calendario");

        btnAtras.setText("Imagen de volver");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        btnActualizar.setText("Imagen de guardar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        lblActualizar.setText("Actualizar informacion");

        lblBienvenido.setText("Bienvenido");

        lblCubiculoEstatico.setText("Cubiculo");

        lblDescripcionEstatico.setText("Informacion del maestro");

        txaDescripcion.setColumns(20);
        txaDescripcion.setRows(5);
        jScrollPane1.setViewportView(txaDescripcion);

        lblInfoFotoEstatico.setText("Clic en la foto para cambiarla");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(lblFotoMaestro, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblNombreMaestro, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                            .addComponent(lblBienvenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCubiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCubiculoEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnAtras)
                                .addGap(171, 171, 171)
                                .addComponent(btnCalendario)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblCalendario, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(136, 136, 136)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(26, 26, 26))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblInfoFotoEstatico)
                .addGap(524, 524, 524))
            .addGroup(layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescripcionEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblCubiculoEstatico)
                            .addComponent(lblBienvenido))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCubiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNombreMaestro))
                        .addGap(32, 32, 32))
                    .addComponent(lblFotoMaestro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(lblInfoFotoEstatico)
                .addGap(15, 15, 15)
                .addComponent(lblDescripcionEstatico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCalendario, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblActualizar))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCalendario, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        editarInformacion();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void lblFotoMaestroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFotoMaestroMouseClicked
        añadirImagen();
    }//GEN-LAST:event_lblFotoMaestroMouseClicked

    private void btnCalendarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalendarioActionPerformed
        abrirCalendario();
    }//GEN-LAST:event_btnCalendarioActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
       cerrar();
    }//GEN-LAST:event_btnAtrasActionPerformed

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
            java.util.logging.Logger.getLogger(PrincipalMaestro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalMaestro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalMaestro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalMaestro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

                new PrincipalMaestro(maestro).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnCalendario;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblActualizar;
    private javax.swing.JLabel lblBienvenido;
    private javax.swing.JLabel lblCalendario;
    private javax.swing.JLabel lblCubiculoEstatico;
    private javax.swing.JLabel lblDescripcionEstatico;
    private javax.swing.JLabel lblFotoMaestro;
    private javax.swing.JLabel lblInfoFotoEstatico;
    private javax.swing.JLabel lblNombreMaestro;
    private javax.swing.JTextArea txaDescripcion;
    private javax.swing.JTextField txtCubiculo;
    // End of variables declaration//GEN-END:variables
}
