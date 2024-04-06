package presentacion.pantallas;

import DTOS.evento.EventoConsultableDTO;
import DTOS.maestro.MaestroEditableDTO;
import excepciones.NegocioException;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import subsistemas.accesoMaestro.IAccesoMaestro;
import subsistemas.accesoMaestro.EditarMaestro;
import subsistemas.recuperarMaestro.IRecuperarMaestro;
import subsistemas.recuperarMaestro.RecuperarMaestro;
import javax.swing.ImageIcon;

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
     *
     * @param maestro
     */
    public PrincipalMaestro(MaestroEditableDTO maestro) {
        initComponents();
        this.maestro = maestro;
        cargarMaestro();
        cargarIconos();
        this.setVisible(true);
        this.setSize(800, 600);

    }

    /**
     * Carga los iconos en los botones de la interfaz.
     */
    private void cargarIconos() {
        // Carga el icono de retorno en el botón btnAtras
        ImageIcon iconoReturn = new ImageIcon(getClass().getResource("/imagenes/icons8-return-50.png"));
        
        btnAtras.setIcon(iconoReturn);
        // Carga el icono de calendario en el botón btnCalendario
        ImageIcon iconoCalendario = new ImageIcon(getClass().getResource("/imagenes/icons8-calendar-50.png"));
        btnCalendario.setIcon(iconoCalendario);
        // Carga el icono de guardar en el botón btnActualizar
        ImageIcon iconoGuardar = new ImageIcon(getClass().getResource("/imagenes/icons8-save-50.png"));
        btnActualizar.setIcon(iconoGuardar);
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
     * Toma los campos de texto y foto de perfil y la guarda en la base de datos
     * actualizando el perfil de maestro
     */
    private void editarInformacion() {
        String descripcion = this.txaDescripcion.getText();
        String cubiculo = this.txtCubiculo.getText();
        Icon foto = this.lblFotoMaestro.getIcon();

        accesoM = new EditarMaestro();
        List<EventoConsultableDTO> calendarioAuxiliar = maestro.getCalendario();

        MaestroEditableDTO maestroAuxiliar = new MaestroEditableDTO(maestro.getId(), maestro.getNombre(), cubiculo, descripcion, foto);
        try {
            maestro = accesoM.editarMaestro(maestroAuxiliar);
            maestro.setCalendario(calendarioAuxiliar);
        } catch (NegocioException ex) {
            Logger.getLogger(PrincipalMaestro.class.getName()).log(Level.SEVERE, "No se pudo actualizar la informacion en la base de datos", ex);
        }
        JOptionPane.showMessageDialog(null, "Se actualizo su informacion", "Mensaje de confirmación", JOptionPane.INFORMATION_MESSAGE);

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

    /**
     * Al dar clic en el boton de calendario deberia desplegarse el frame
     * PrincipalCalendario y ocultarse este frame
     */
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

        jPanel2 = new javax.swing.JPanel();
        btnAtras = new javax.swing.JButton();
        btnCalendario = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaDescripcion = new javax.swing.JTextArea();
        lblDescripcionEstatico = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtCubiculo = new javax.swing.JTextField();
        lblCubiculoEstatico = new javax.swing.JLabel();
        lblNombreMaestro = new javax.swing.JLabel();
        lblBienvenido = new javax.swing.JLabel();
        lblFotoMaestro = new javax.swing.JLabel();
        lblInfoFotoEstatico = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(null);

        btnAtras.setBackground(new java.awt.Color(255, 255, 255));
        btnAtras.setForeground(new java.awt.Color(255, 255, 255));
        btnAtras.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 0));
        btnAtras.setBorderPainted(false);
        btnAtras.setFocusPainted(false);
        btnAtras.setPreferredSize(new java.awt.Dimension(83, 59));
        btnAtras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAtrasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAtrasMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnAtrasMouseReleased(evt);
            }
        });
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });
        jPanel2.add(btnAtras);
        btnAtras.setBounds(70, 370, 70, 50);

        btnCalendario.setBackground(new java.awt.Color(255, 255, 255));
        btnCalendario.setBorder(null);
        btnCalendario.setBorderPainted(false);
        btnCalendario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCalendarioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCalendarioMouseExited(evt);
            }
        });
        btnCalendario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalendarioActionPerformed(evt);
            }
        });
        jPanel2.add(btnCalendario);
        btnCalendario.setBounds(363, 348, 80, 70);

        btnActualizar.setBackground(new java.awt.Color(255, 255, 255));
        btnActualizar.setBorder(null);
        btnActualizar.setBorderPainted(false);
        btnActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnActualizarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnActualizarMouseExited(evt);
            }
        });
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel2.add(btnActualizar);
        btnActualizar.setBounds(650, 370, 80, 50);

        txaDescripcion.setColumns(20);
        txaDescripcion.setRows(5);
        jScrollPane1.setViewportView(txaDescripcion);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(100, 70, 570, 260);

        lblDescripcionEstatico.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblDescripcionEstatico.setText("Informacion del maestro");
        jPanel2.add(lblDescripcionEstatico);
        lblDescripcionEstatico.setBounds(100, 50, 296, 14);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 160, 800, 440);

        jPanel1.setBackground(new java.awt.Color(22, 81, 198));

        lblCubiculoEstatico.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblCubiculoEstatico.setForeground(new java.awt.Color(255, 255, 255));
        lblCubiculoEstatico.setText("Cubículo");

        lblNombreMaestro.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblNombreMaestro.setForeground(new java.awt.Color(255, 255, 255));
        lblNombreMaestro.setText("Nombre completo del maestro");

        lblBienvenido.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        lblBienvenido.setForeground(new java.awt.Color(255, 255, 255));
        lblBienvenido.setText("Bienvenido");

        lblFotoMaestro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        lblFotoMaestro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFotoMaestroMouseClicked(evt);
            }
        });

        lblInfoFotoEstatico.setFont(new java.awt.Font("SansSerif", 0, 10)); // NOI18N
        lblInfoFotoEstatico.setForeground(new java.awt.Color(204, 204, 204));
        lblInfoFotoEstatico.setText("Clic en la foto para cambiarla");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(lblInfoFotoEstatico))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(lblFotoMaestro, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBienvenido, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombreMaestro, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 194, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCubiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCubiculoEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblBienvenido, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblCubiculoEstatico))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtCubiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNombreMaestro))
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblFotoMaestro, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(lblInfoFotoEstatico)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 800, 160);

        pack();
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

    private void btnAtrasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAtrasMouseEntered
        // TODO add your handling code here:
        btnAtras.setBackground(Color.lightGray);
    }//GEN-LAST:event_btnAtrasMouseEntered

    private void btnCalendarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCalendarioMouseEntered

        btnCalendario.setBackground(Color.lightGray);
    }//GEN-LAST:event_btnCalendarioMouseEntered

    private void btnActualizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizarMouseEntered

        btnActualizar.setBackground(Color.lightGray);
    }//GEN-LAST:event_btnActualizarMouseEntered

    private void btnAtrasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAtrasMouseExited
        // TODO add your handling code here:
        btnAtras.setBackground(Color.WHITE);
    }//GEN-LAST:event_btnAtrasMouseExited

    private void btnAtrasMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAtrasMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAtrasMouseReleased

    private void btnCalendarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCalendarioMouseExited
        // TODO add your handling code here:
        btnCalendario.setBackground(Color.WHITE);
    }//GEN-LAST:event_btnCalendarioMouseExited

    private void btnActualizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizarMouseExited
        // TODO add your handling code here:
        btnActualizar.setBackground(Color.WHITE);
    }//GEN-LAST:event_btnActualizarMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnCalendario;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBienvenido;
    private javax.swing.JLabel lblCubiculoEstatico;
    private javax.swing.JLabel lblDescripcionEstatico;
    private javax.swing.JLabel lblFotoMaestro;
    private javax.swing.JLabel lblInfoFotoEstatico;
    private javax.swing.JLabel lblNombreMaestro;
    private javax.swing.JTextArea txaDescripcion;
    private javax.swing.JTextField txtCubiculo;
    // End of variables declaration//GEN-END:variables
}
