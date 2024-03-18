
package presentacion.pantallas;

import javax.swing.ImageIcon;
import presentacion.CDEvento;

/**
 *
 * @author t1pas
 */
public class MapaCalendario extends javax.swing.JFrame {

    CDEvento cdEvento;
    private String ubicacion;
    /**
     * Creates new form MapaCalendario
     * @param cdEvento
     */
    public MapaCalendario(CDEvento cdEvento) {
        initComponents();
        this.cdEvento=cdEvento;
        setMapa("Obregon Nainari");
    }

    //TODO
    //Guarda la ubicacion seleccionada
    private void guardar(){  
        ubicacion=this.txtUbicacionDinamica.getText();
        cdEvento.guardarUbicacion(ubicacion);
        cerrar();
    }
    
    private void cerrar(){
        cdEvento.calendario.setVisible(true);
        cdEvento.setVisible(true);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbCampus = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        lblGuardar = new javax.swing.JLabel();
        cmbMapa = new javax.swing.JComboBox<>();
        pnlMapa = new javax.swing.JPanel();
        imgPanel = new javax.swing.JLabel();
        btnAtras = new javax.swing.JButton();
        lblCampusEstatico = new javax.swing.JLabel();
        lblUbicacionEstatico = new javax.swing.JLabel();
        txtUbicacionDinamica = new javax.swing.JTextField();
        lblUbicacionEstatica = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        cmbCampus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Obregon Nainari", "Obregon Centro" }));
        cmbCampus.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCampusItemStateChanged(evt);
            }
        });
        cmbCampus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCampusActionPerformed(evt);
            }
        });

        btnGuardar.setText("Imagen de guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        lblGuardar.setText("Guardar");

        cmbMapa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AV0900 Aula Audiovisual", "AV1000 CISCO", "AV1100", "AV1200", "AV1300", "AV1400", "AV1500", "AV1600", "AV1700", "AV1800" }));
        cmbMapa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmbMapaMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMapaMouseReleased(evt);
            }
        });
        cmbMapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMapaActionPerformed(evt);
            }
        });

        pnlMapa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout pnlMapaLayout = new javax.swing.GroupLayout(pnlMapa);
        pnlMapa.setLayout(pnlMapaLayout);
        pnlMapaLayout.setHorizontalGroup(
            pnlMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlMapaLayout.setVerticalGroup(
            pnlMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
        );

        btnAtras.setText("Imagen de volver");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        lblCampusEstatico.setText("Campus");

        lblUbicacionEstatico.setText("Buscar ubicacion en mapa");

        lblUbicacionEstatica.setText("Ubicacion");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbCampus, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCampusEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnGuardar))
                            .addComponent(txtUbicacionDinamica, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblUbicacionEstatica, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbMapa, 0, 334, Short.MAX_VALUE)
                    .addComponent(pnlMapa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(lblUbicacionEstatico)))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCampusEstatico)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbCampus, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblUbicacionEstatico)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(lblUbicacionEstatica)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUbicacionDinamica, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblGuardar)
                        .addGap(104, 104, 104)
                        .addComponent(btnAtras, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(pnlMapa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmbCampusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCampusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCampusActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
       dispose();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void cmbCampusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCampusItemStateChanged
        String campus=cmbCampus.getSelectedItem().toString();
        setMapa(campus);
    }//GEN-LAST:event_cmbCampusItemStateChanged
    
    private void setMapa(String campus){
        ImageIcon icon=null;
        if(campus.equals("Obregon Nainari")){
            icon=new ImageIcon("campus-nainari.jpg");
        }else if(campus.equals("Obregon Centro")){
            icon=new ImageIcon("campus-centro.jpg");
        }
       imgPanel.setIcon(icon);
    }
    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        cerrar();
    }//GEN-LAST:event_formWindowClosed

    private void cmbMapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMapaActionPerformed
       this.txtUbicacionDinamica.setText((String) this.cmbMapa.getSelectedItem()); 
    }//GEN-LAST:event_cmbMapaActionPerformed

    private void cmbMapaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMapaMouseReleased
        
    }//GEN-LAST:event_cmbMapaMouseReleased

    private void cmbMapaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMapaMouseExited
        
    }//GEN-LAST:event_cmbMapaMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cmbCampus;
    private javax.swing.JComboBox<String> cmbMapa;
    private javax.swing.JLabel imgPanel;
    private javax.swing.JLabel lblCampusEstatico;
    private javax.swing.JLabel lblGuardar;
    private javax.swing.JLabel lblUbicacionEstatica;
    private javax.swing.JLabel lblUbicacionEstatico;
    private javax.swing.JPanel pnlMapa;
    private javax.swing.JTextField txtUbicacionDinamica;
    // End of variables declaration//GEN-END:variables
}
