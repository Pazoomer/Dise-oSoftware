
package presentacion.pantallas;

import DTOS.maestro.MaestroEditableDTO;
import DTOS.usuarios.UsuarioDTO;
import accesoMaestro.FachadaAccesoMaestro;
import accesoMaestro.IAccesoMaestro;
import accesoUsuarios.FachadaAccesoUsuarios;
import accesoUsuarios.IAccesoUsuarios;
import excepciones.NegocioException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author pauli
 */
public class iniciarSesion extends javax.swing.JFrame {
   IAccesoUsuarios user=new FachadaAccesoUsuarios();
    /**
     * Creates new form iniciarSesion
     */
    public iniciarSesion() {
        setUndecorated(true);
        this.setResizable(false);
        initComponents();
        this.setSize(800,600);
        this.setLocationRelativeTo(null);
        decorar();
    }

    private void decorar() {
        
        ImageIcon iconoReturn = new ImageIcon(getClass().getResource("/imagenes/icons8-return-50.png"));
        this.btnAtras.setIcon(iconoReturn);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnIniciarSesion = new javax.swing.JButton();
        txtUsername = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnAtras = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(22, 81, 198));
        setSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(null);

        jPanel2.setBackground(new java.awt.Color(22, 81, 198));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(22, 81, 198));
        jLabel2.setText("Id:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(60, 180, 15, 19);

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(22, 81, 198));
        jLabel3.setText("Contraseña:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(60, 240, 78, 19);

        btnIniciarSesion.setBackground(new java.awt.Color(22, 81, 198));
        btnIniciarSesion.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnIniciarSesion.setForeground(new java.awt.Color(255, 255, 255));
        btnIniciarSesion.setText("Iniciar sesión");
        btnIniciarSesion.setFocusPainted(false);
        btnIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionActionPerformed(evt);
            }
        });
        jPanel1.add(btnIniciarSesion);
        btnIniciarSesion.setBounds(60, 330, 180, 30);
        jPanel1.add(txtUsername);
        txtUsername.setBounds(60, 200, 180, 30);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(22, 81, 198));
        jLabel1.setText("Iniciar sesión");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(67, 113, 153, 32);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/loginblue.png"))); // NOI18N
        jPanel1.add(jLabel4);
        jLabel4.setBounds(120, 54, 48, 48);
        jPanel1.add(txtPassword);
        txtPassword.setBounds(60, 260, 180, 30);

        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btnAtras)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 168, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(248, 248, 248))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(71, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))))
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 0, 800, 600);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void iniciarSesion() {
        String idUsuario = txtUsername.getText();
        String password = new String(txtPassword.getPassword());
        try {
            UsuarioDTO usuario = this.user.iniciarSesion(idUsuario, password);
            if (usuario != null) {
                if (usuario.isAdministrador()) {
                    //this.setVisible(false);
                    this.dispose();
                    new FrmInicio(this).setVisible(true);   
                    //new PrincipalInicio(usuario).setVisible(true);
                } else {
                    IAccesoMaestro acceso = new FachadaAccesoMaestro();
                    MaestroEditableDTO maestro;
                    maestro = acceso.recuperarMaestro(new MaestroEditableDTO(usuario.getIdUsuario()));
                    if (maestro != null) {
                        System.out.println(maestro.toString());
                        //this.setVisible(false);
                        this.dispose();
                        new PrincipalMaestro(maestro,this).setVisible(true);

                    } else {
                        JOptionPane.showMessageDialog(null, "Error al obtener al maestro", "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NegocioException e) {
            System.out.println(e.getMessage());
        }
    }
    
//    private void cerrar() {
//        this.dispose();
//    }
    private void btnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionActionPerformed
        iniciarSesion();

    }//GEN-LAST:event_btnIniciarSesionActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        //cerrar();
        this.dispose();
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
            java.util.logging.Logger.getLogger(iniciarSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(iniciarSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(iniciarSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(iniciarSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new iniciarSesion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnIniciarSesion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
