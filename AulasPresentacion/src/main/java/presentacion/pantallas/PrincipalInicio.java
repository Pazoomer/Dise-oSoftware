
package presentacion.pantallas;

import DTOS.maestro.MaestroEditableDTO;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author t1pas
 */
public final class PrincipalInicio extends javax.swing.JFrame {

    MaestroEditableDTO admin;
    /**
     * Creates new form PrincipalInicio
     *
     * @param admin
     */
    public PrincipalInicio(MaestroEditableDTO admin) {
        //Verifica al administrador
        if (admin == null) {
            cerrar();
            return;
        }
        if (admin.getAdmin() == false) {
            cerrar();
            return;
        }
        setUndecorated(true);
        this.setResizable(false);
        initComponents();
        this.setSize(800, 600);
        this.admin=admin;
        decorar();
    }
    
    private void decorar() {
        this.lblNombreAdmin.setText("Administrador: " + admin.getNombre());
        // Carga el icono de retorno en el botón btnAtras
        try {
            ImageIcon iconoReturn = new ImageIcon(getClass().getResource("/imagenes/icons8-return-50.png"));
            this.btnVolver.setIcon(iconoReturn);

            ImageIcon iconoMapa = new ImageIcon(getClass().getResource("/imagenes/location-marker-icon-vector.jpg"));
            Image imagenRedimensionadaMapa = iconoMapa.getImage().getScaledInstance(btnMapa.getWidth(), btnMapa.getHeight(), Image.SCALE_SMOOTH);
            this.btnMapa.setIcon(new ImageIcon(imagenRedimensionadaMapa));
        } catch (Exception e) {
            System.out.println("No cargaron los iconos");
        }

    }

    private void cerrar() {
        this.dispose();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnMapa = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        pnlTitulo = new javax.swing.JPanel();
        lblNombreAdmin = new javax.swing.JLabel();
        pnlMapa = new javax.swing.JPanel();
        lblEstaticoMapa = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnMapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMapaActionPerformed(evt);
            }
        });

        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        pnlTitulo.setBackground(new java.awt.Color(22, 81, 198));

        lblNombreAdmin.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        lblNombreAdmin.setForeground(new java.awt.Color(255, 255, 255));
        lblNombreAdmin.setText("Administrador: ");

        javax.swing.GroupLayout pnlTituloLayout = new javax.swing.GroupLayout(pnlTitulo);
        pnlTitulo.setLayout(pnlTituloLayout);
        pnlTituloLayout.setHorizontalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblNombreAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(196, Short.MAX_VALUE))
        );
        pnlTituloLayout.setVerticalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblNombreAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pnlMapa.setBackground(new java.awt.Color(22, 81, 198));

        lblEstaticoMapa.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        lblEstaticoMapa.setForeground(new java.awt.Color(255, 255, 255));
        lblEstaticoMapa.setText("Administrar Mapa");

        javax.swing.GroupLayout pnlMapaLayout = new javax.swing.GroupLayout(pnlMapa);
        pnlMapa.setLayout(pnlMapaLayout);
        pnlMapaLayout.setHorizontalGroup(
            pnlMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMapaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblEstaticoMapa)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlMapaLayout.setVerticalGroup(
            pnlMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMapaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblEstaticoMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btnVolver))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pnlMapa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
                .addComponent(pnlMapa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnMapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMapaActionPerformed
        new PrincipalCampus(this).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnMapaActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        cerrar();
    }//GEN-LAST:event_btnVolverActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMapa;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel lblEstaticoMapa;
    private javax.swing.JLabel lblNombreAdmin;
    private javax.swing.JPanel pnlMapa;
    private javax.swing.JPanel pnlTitulo;
    // End of variables declaration//GEN-END:variables
}
