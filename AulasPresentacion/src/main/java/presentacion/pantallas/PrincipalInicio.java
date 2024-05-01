
package presentacion.pantallas;

import DTOS.maestro.MaestroEditableDTO;

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
        }
        if (admin.getAdmin() == false) {
            cerrar();
        }
        initComponents();
        this.admin=admin;
        decorar();
    }
    
    private void decorar(){
        this.lblNombreAdmin.setText(admin.getNombre());
    }

    private void cerrar() {
        this.dispose();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnMapa = new javax.swing.JButton();
        lblEstaticoMapa = new javax.swing.JLabel();
        lblNombreAdmin = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnMapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMapaActionPerformed(evt);
            }
        });

        lblEstaticoMapa.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        lblEstaticoMapa.setText("Administrar Mapa y Ubicaciones del Campus");

        lblNombreAdmin.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        lblNombreAdmin.setText("Administrador: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addComponent(lblEstaticoMapa))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(224, 224, 224)
                        .addComponent(btnMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblNombreAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(216, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNombreAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 212, Short.MAX_VALUE)
                .addComponent(btnMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblEstaticoMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(140, 140, 140))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMapaActionPerformed
        new PrincipalCampus(this).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnMapaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMapa;
    private javax.swing.JLabel lblEstaticoMapa;
    private javax.swing.JLabel lblNombreAdmin;
    // End of variables declaration//GEN-END:variables
}
