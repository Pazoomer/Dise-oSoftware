
package presentacion.pantallas;

import java.awt.Color;
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
        this.setSize(800, 600);
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

        imgPanel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnAtras = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        cmbMapa = new javax.swing.JComboBox<>();
        lblUbicacionEstatico = new javax.swing.JLabel();
        cmbCampus = new javax.swing.JComboBox<>();
        pnlMapa = new javax.swing.JPanel();
        txtUbicacionDinamica = new javax.swing.JTextField();
        lblUbicacionEstatica = new javax.swing.JLabel();
        lblCampusEstatico = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(null);
        getContentPane().add(imgPanel);
        imgPanel.setBounds(810, 129, 0, 460);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        btnAtras.setIcon(new javax.swing.ImageIcon("C:\\GitHub\\Disenio de software\\Dise-oSoftware\\Aulas\\icons8-return-50.png")); // NOI18N
        btnAtras.setBorder(null);
        btnAtras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAtrasMouseEntered(evt);
            }
        });
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        btnGuardar.setIcon(new javax.swing.ImageIcon("C:\\GitHub\\Disenio de software\\Dise-oSoftware\\Aulas\\icons8-save-50.png")); // NOI18N
        btnGuardar.setBorder(null);
        btnGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarMouseEntered(evt);
            }
        });
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

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

        lblUbicacionEstatico.setBackground(new java.awt.Color(22, 81, 198));
        lblUbicacionEstatico.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblUbicacionEstatico.setForeground(new java.awt.Color(255, 255, 255));
        lblUbicacionEstatico.setIcon(new javax.swing.ImageIcon("C:\\GitHub\\Disenio de software\\Dise-oSoftware\\Aulas\\icons8-search-50white.png")); // NOI18N
        lblUbicacionEstatico.setText("Buscar ubicacion en mapa");
        lblUbicacionEstatico.setOpaque(true);

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

        pnlMapa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout pnlMapaLayout = new javax.swing.GroupLayout(pnlMapa);
        pnlMapa.setLayout(pnlMapaLayout);
        pnlMapaLayout.setHorizontalGroup(
            pnlMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 332, Short.MAX_VALUE)
        );
        pnlMapaLayout.setVerticalGroup(
            pnlMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 315, Short.MAX_VALUE)
        );

        lblUbicacionEstatica.setBackground(new java.awt.Color(22, 81, 198));
        lblUbicacionEstatica.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblUbicacionEstatica.setForeground(new java.awt.Color(255, 255, 255));
        lblUbicacionEstatica.setText("                    Ubicacion");
        lblUbicacionEstatica.setOpaque(true);

        lblCampusEstatico.setBackground(new java.awt.Color(22, 81, 198));
        lblCampusEstatico.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblCampusEstatico.setForeground(new java.awt.Color(255, 255, 255));
        lblCampusEstatico.setText("                    Campus");
        lblCampusEstatico.setOpaque(true);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtUbicacionDinamica)
                            .addComponent(lblUbicacionEstatica, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                            .addComponent(lblCampusEstatico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbCampus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUbicacionEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlMapa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(136, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCampusEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUbicacionEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbCampus, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblUbicacionEstatica, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUbicacionDinamica, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(pnlMapa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 66, Short.MAX_VALUE))))
        );

        getContentPane().add(jPanel3);
        jPanel3.setBounds(0, 80, 800, 520);

        jPanel1.setBackground(new java.awt.Color(22, 81, 198));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 60));

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\GitHub\\Disenio de software\\Dise-oSoftware\\Aulas\\icons8-map-marker-50.png")); // NOI18N
        jLabel1.setText("Ubicación");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel1)
                .addContainerGap(621, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 800, 80);

        pack();
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

    private void btnGuardarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseEntered
        // TODO add your handling code here:
        btnGuardar.setBackground(Color.lightGray);
    }//GEN-LAST:event_btnGuardarMouseEntered

    private void btnAtrasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAtrasMouseEntered
        // TODO add your handling code here:
        btnAtras.setBackground(Color.lightGray);
    }//GEN-LAST:event_btnAtrasMouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cmbCampus;
    private javax.swing.JComboBox<String> cmbMapa;
    private javax.swing.JLabel imgPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblCampusEstatico;
    private javax.swing.JLabel lblUbicacionEstatica;
    private javax.swing.JLabel lblUbicacionEstatico;
    private javax.swing.JPanel pnlMapa;
    private javax.swing.JTextField txtUbicacionDinamica;
    // End of variables declaration//GEN-END:variables
}
