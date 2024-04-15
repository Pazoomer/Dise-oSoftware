package presentacion.pantallas;

//import BO.recuperarUbicacionesBO.IRecuperarUbicacionesBO;
//import BO.recuperarUbicacionesBO.RecuperarUbicacionesBO;
//import conexion.IConexionDAO;
import DTOS.campus.CampusConsultableDTO;
import DTOS.campus.UbicacionDTO;
import accesoUbicaciones.FachadaAccesoUbicaciones;
import accesoUbicaciones.IAccesoUbicaciones;
import excepciones.NegocioException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import presentacion.CDEvento;

/**
 *
 * @author t1pas
 */
public class MapaCalendario extends javax.swing.JFrame {

    CDEvento cdEvento;
    private int w = 0;
    private int h = 0;
    private Image img = null;
    private String ubicacion;
    private UbicacionDTO ubicacionDTO;
    private List<UbicacionDTO> ubicacionesCampus;
    private DefaultComboBoxModel cmbBoxModelEdificios;
    private IAccesoUbicaciones accesoUbicaciones;
//    private final IConexionDAO conexion;

    /**
     * Creates new form MapaCalendario
     *
     * @param cdEvento
     * @param conexion
     */
    public MapaCalendario(CDEvento cdEvento) {
        setUndecorated(true);
        setAlwaysOnTop(true);
        this.setResizable(false);
        initComponents();
        this.ubicacionesCampus=new ArrayList<>();
        this.accesoUbicaciones=new FachadaAccesoUbicaciones();
        this.cdEvento = cdEvento;
        setMapa("Obregon Nainari");
        this.setSize(800, 600);
        cargarIconos();
        actualizarImagenMapa();
        setUbicaciones("Obregon Nainari");
    }

    /**
     * Carga los iconos en los botones de la interfaz.
     */
    private void cargarIconos() {
        // Carga el icono de retorno en el botón btnAtras
        ImageIcon iconoReturn = new ImageIcon(getClass().getResource("/imagenes/icons8-return-50.png"));
        btnAtras.setIcon(iconoReturn);
        // Carga el icono de guardar en el botón btnGuardar
        ImageIcon iconoGuardar = new ImageIcon(getClass().getResource("/imagenes/icons8-save-50.png"));
        btnGuardar.setIcon(iconoGuardar);
    }

    /**
     * Actualiza la imagen en el JLabel lblImageMap con una imagen de un campus
     * y establece iconos en los botones btnZoomIn y btnZoomOut.
     */
    private void actualizarImagenMapa() {
        // Obtiene el alto y ancho del JScrollPane jScrollPane1
        w = jScrollPane1.getHeight();
        h = jScrollPane1.getWidth();
        // Carga la imagen del campus desde el recurso
        img = new ImageIcon(getClass().getResource("/imagenes/campus-nainari.jpg")).getImage();
        // Escala la imagen al tamaño del JScrollPane
        ImageIcon icon = new ImageIcon(zoom(h, w, img));
        // Establece la imagen en el JLabel lblImageMap
        lblImageMap.setIcon(icon);
        // Carga los iconos de zoom en los botones btnZoomIn y btnZoomOut
        ImageIcon iconoZoomIn = new ImageIcon(getClass().getResource("/imagenes/icons8-zoom-in-50.png"));
        btnZoomIn.setIcon(iconoZoomIn);
        ImageIcon iconoZoomOut = new ImageIcon(getClass().getResource("/imagenes/icons8-zoom-out-50.png"));
        btnZoomOut.setIcon(iconoZoomOut);
    }
    
    /**
     * Accede al subsistema de recupera ubicaciones por los campus
     */
    private void setUbicaciones(String campus) {
        cmbBoxModelEdificios=new DefaultComboBoxModel();
        
        try{
            ubicacionesCampus = accesoUbicaciones.recuperarEdificiosPorCampus(new CampusConsultableDTO(campus));
            if(!ubicacionesCampus.isEmpty()){
                for(UbicacionDTO u: ubicacionesCampus){
                    cmbBoxModelEdificios.addElement(u.getIdentificador());
                }
                cmbMapa.setModel(cmbBoxModelEdificios);
            }
        }catch(NegocioException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
//
//        
//        List<String> campusUbicaciones=accesoUbicaciones.recuperarEdificiosPorCampus(campus);
//
//        for (String campusItem : campusUbicaciones) {
//            this.cmbCampus.addItem(campusItem);
//        }
//        cambioCampus(campusUbicaciones.get(0));
    }

    /**
     * Cambia el comboBox de ubicaciones segun el campus
     * @param campus 
     */
//    private void cambioCampus(String campus) {
//        cmbBoxModelCampus=new DefaultComboBoxModel();
//        //IRecuperarUbicacionesBO recuperarUbicacionesBO=new RecuperarUbicacionesBO(conexion);
//        try{
//            List<UbicacionDTO> ubicacionesCampus = accesoUbicaciones.recuperarEdificiosPorCampus(new CampusConsultableDTO(campus));
//            if(!ubicacionesCampus.isEmpty()){
//                for(UbicacionDTO u: ubicacionesCampus){
//                    cmbBoxModelCampus.addElement(u.getCampus().getNombre());
//                }
//                cmbCampus.setModel(cmbBoxModelCampus);
//            }
//        }catch(NegocioException e){
//            JOptionPane.showMessageDialog(this, e.getMessage());
//        }
//
//        this.cmbMapa.removeAllItems();
//        for (String ubicacionCampus : ubicacionesCampus) {
//            this.cmbMapa.addItem(ubicacionCampus);
//        }
//    }

    private UbicacionDTO setUbicacionDTOSeleccionado(){
        String ubicacionTxt=txtUbicacionDinamica.getText();
        for(UbicacionDTO u:ubicacionesCampus){
            if(u.getIdentificador().equals(ubicacionTxt)){
                return u;
            }
        }
        return null;
    }
    //TODO
    //Guarda la ubicacion seleccionada
    private void guardar() {
        ubicacion = this.txtUbicacionDinamica.getText();
        cdEvento.guardarUbicacion(ubicacion,setUbicacionDTOSeleccionado());
        cerrar();
    }

    private void cerrar() {
        this.dispose();
        //cdEvento.calendario.setVisible(true);
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
        txtUbicacionDinamica = new javax.swing.JTextField();
        lblUbicacionEstatica = new javax.swing.JLabel();
        lblCampusEstatico = new javax.swing.JLabel();
        btnZoomIn = new javax.swing.JButton();
        btnZoomOut = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lblImageMap = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblTituloUbicacion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(null);
        getContentPane().add(imgPanel);
        imgPanel.setBounds(810, 129, 0, 460);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

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

        btnGuardar.setBorder(null);
        btnGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardarMouseExited(evt);
            }
        });
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

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
        lblUbicacionEstatico.setText("Buscar ubicacion en mapa");
        lblUbicacionEstatico.setOpaque(true);

        cmbCampus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Obregon Nainari", "Centro" }));
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

        btnZoomIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnZoomInMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnZoomInMouseExited(evt);
            }
        });
        btnZoomIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZoomInActionPerformed(evt);
            }
        });

        btnZoomOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnZoomOutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnZoomOutMouseExited(evt);
            }
        });
        btnZoomOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZoomOutActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(lblImageMap);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtUbicacionDinamica)
                            .addComponent(lblUbicacionEstatica, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                            .addComponent(lblCampusEstatico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbCampus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnZoomOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnZoomIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(lblUbicacionEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCampusEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUbicacionEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbCampus, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(btnZoomIn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnZoomOut, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lblUbicacionEstatica, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtUbicacionDinamica, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAtras, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 42, Short.MAX_VALUE))))
        );

        getContentPane().add(jPanel3);
        jPanel3.setBounds(0, 80, 800, 520);

        jPanel1.setBackground(new java.awt.Color(22, 81, 198));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 60));

        lblTituloUbicacion.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblTituloUbicacion.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloUbicacion.setText("Ubicación");
        ImageIcon iconoUbicacion = new ImageIcon(getClass().getResource("/imagenes/icons8-map-marker-50.png"));
        lblTituloUbicacion.setIcon(iconoUbicacion);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(lblTituloUbicacion)
                .addContainerGap(621, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTituloUbicacion, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
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
        cerrar();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void cmbCampusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCampusItemStateChanged
        String campusUbi = cmbCampus.getSelectedItem().toString();
        setMapa(campusUbi);
        setUbicaciones(campusUbi);
    }//GEN-LAST:event_cmbCampusItemStateChanged

    private void setMapa(String campus) {

        ImageIcon icon = null;
        w = jScrollPane1.getHeight();
        h = jScrollPane1.getWidth();
        if (campus.equals("Obregon Nainari")) {
            img = new ImageIcon(getClass().getResource("/imagenes/campus-nainari.jpg")).getImage();
            icon = new ImageIcon(zoom(h, w, img));
        } else {
            img = new ImageIcon(getClass().getResource("/imagenes/campus-centro.jpg")).getImage();
            icon = new ImageIcon(zoom(h, w, img));
        }
        lblImageMap.setIcon(icon);

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

    private void btnGuardarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseExited
        // TODO add your handling code here:
        btnGuardar.setBackground(Color.WHITE);
    }//GEN-LAST:event_btnGuardarMouseExited

    private void btnAtrasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAtrasMouseExited
        // TODO add your handling code here:
        btnAtras.setBackground(Color.WHITE);
    }//GEN-LAST:event_btnAtrasMouseExited

    private void btnZoomInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZoomInActionPerformed
        // TODO add your handling code here:
        h = h + 20;
        w = w + 20;
        ImageIcon icon = new ImageIcon(zoom(h, w, img));
        lblImageMap.setIcon(icon);
    }//GEN-LAST:event_btnZoomInActionPerformed

    private void btnZoomOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZoomOutActionPerformed
        // TODO add your handling code here:
        h = h - 20;
        w = w - 20;
        ImageIcon icon = new ImageIcon(zoom(h, w, img));
        lblImageMap.setIcon(icon);
    }//GEN-LAST:event_btnZoomOutActionPerformed

    private void btnZoomInMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnZoomInMouseEntered
        // TODO add your handling code here:
        btnZoomIn.setBackground(Color.lightGray);
    }//GEN-LAST:event_btnZoomInMouseEntered

    private void btnZoomInMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnZoomInMouseExited
        // TODO add your handling code here:
        btnZoomIn.setBackground(Color.WHITE);
    }//GEN-LAST:event_btnZoomInMouseExited

    private void btnZoomOutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnZoomOutMouseEntered
        // TODO add your handling code here:
        btnZoomOut.setBackground(Color.lightGray);
    }//GEN-LAST:event_btnZoomOutMouseEntered

    private void btnZoomOutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnZoomOutMouseExited
        // TODO add your handling code here:
        btnZoomOut.setBackground(Color.WHITE);
    }//GEN-LAST:event_btnZoomOutMouseExited
    private Image zoom(int H, int W, Image img) {
        BufferedImage buf = new BufferedImage(H, W, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = buf.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(img, 0, 0, W, H, null);
        g2d.dispose();
        return buf;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnZoomIn;
    private javax.swing.JButton btnZoomOut;
    private javax.swing.JComboBox<String> cmbCampus;
    private javax.swing.JComboBox<String> cmbMapa;
    private javax.swing.JLabel imgPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCampusEstatico;
    private javax.swing.JLabel lblImageMap;
    private javax.swing.JLabel lblTituloUbicacion;
    private javax.swing.JLabel lblUbicacionEstatica;
    private javax.swing.JLabel lblUbicacionEstatico;
    private javax.swing.JTextField txtUbicacionDinamica;
    // End of variables declaration//GEN-END:variables
}
