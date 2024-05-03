
package presentacion;

import DTOS.campus.CampusConsultableDTO;
import DTOS.campus.UbicacionDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import pruebas.PruebaMarcador;

/**
 *
 * @author t1pas
 */
public class CDMapa extends javax.swing.JDialog {

    private final UbicacionDTO ubicacion;
    private java.util.List<UbicacionDTO> ubicaciones=new ArrayList<>();
    private final CampusConsultableDTO campus;
    private JLabel labelMarcador;
    private BufferedImage imagenMapa;
    private Point posicionMarcador;
    private ImageIcon iconoMarcador;
    /**
     * Creates new form CDMapa
     * @param parent
     * @param modal
     * @param campus
     * @param ubicacion
     */
    public CDMapa(java.awt.Frame parent, boolean modal,CampusConsultableDTO campus, UbicacionDTO ubicacion) {
        super(parent, modal);
        setUndecorated(true);
        this.setResizable(false);
        initComponents();
        this.setSize(500, 620);
        this.ubicacion = ubicacion;
        this.campus=campus;
        colocarImagenMapa();
        prueba();
    }
    
    private void prueba(){

        //Crea el panel del mapa
         //pnlMapa = new JPanel();
         /*{
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (imagenMapa != null) {
                    g.drawImage(imagenMapa, 0, 0, this);
                    if (posicionMarcador != null) {
                        int x = (int) posicionMarcador.getX() - (labelMarcador.getWidth() / 2);
                        int y = (int) posicionMarcador.getY() - (labelMarcador.getHeight() / 2);
                        labelMarcador.setLocation(x, y);
                    }
                }
            }
        };*/

        // Carga la imagen del mapa
        try {
            URL urlMapa = new URL("https://itson.mx/universidad/PublishingImages/mapas-campus/campus-centro.jpg");
            BufferedImage imagenOriginal = ImageIO.read(urlMapa);

            // Define el tamaño deseado para la imagen
            int anchoDeseado = 435; // Coloca el ancho deseado en píxeles
            int altoDeseado = 350; // Coloca el alto deseado en píxeles

            // Redimensiona la imagen al tamaño deseado
            Image imagenRedimensionada = imagenOriginal.getScaledInstance(anchoDeseado, altoDeseado, Image.SCALE_SMOOTH);

            // Crea una nueva imagen a partir de la imagen redimensionada
            imagenMapa = new BufferedImage(anchoDeseado, altoDeseado, BufferedImage.TYPE_INT_ARGB);

            Graphics2D g2d = imagenMapa.createGraphics();
            g2d.drawImage(imagenRedimensionada, 0, 0, null);
            g2d.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Carga la imagen del marcador
        URL urlMarcador = getClass().getResource("/imagenes/marcador.png");
        BufferedImage imagenMarcador=null;
        try {
            imagenMarcador = ImageIO.read(urlMarcador);
        } catch (IOException ex) {
            Logger.getLogger(PruebaMarcador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Image imagenRedimensionada = imagenMarcador.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        iconoMarcador = new ImageIcon(imagenRedimensionada);
        //labelMarcador = new JLabel(iconoMarcador);
        //labelMarcador.setOpaque(false);
        //pnlMapa.add(labelMarcador);
        
        // Agrega un listener de clic del ratón al panel del mapa
        pnlMapa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                posicionMarcador = e.getPoint();
                pnlMapa.repaint(); // Vuelve a dibujar el panel para mostrar el marcador en la nueva posición

                // Aquí puedes guardar la posición del marcador (posicionMarcador)
                System.out.println("Posición del marcador: " + posicionMarcador);
                
                Double x = posicionMarcador.getX();
                Double y = posicionMarcador.getY();

                ubicacion.setPosicionX(x);
                ubicacion.setPosicionY(y);
                ubicaciones.add(ubicacion);

                colocarMarcadores();
            }
        });
        //Detalles del mapa
        pnlMapa.setLayout(null); // Para poder posicionar el marcador manualmente
        this.add(pnlMapa, BorderLayout.CENTER);

        //Iconos de botones
        try {
            ImageIcon iconoReturn = new ImageIcon(getClass().getResource("/imagenes/icons8-return-50.png"));
            this.btnCancelar.setIcon(iconoReturn);

            ImageIcon iconoAceptar = new ImageIcon(getClass().getResource("/imagenes/icons8-save-50.png"));
            Image imagenRedimensionadaAceptar = iconoAceptar.getImage().getScaledInstance(btnAceptar.getWidth(), btnAceptar.getHeight(), Image.SCALE_SMOOTH);
            this.btnAceptar.setIcon(new ImageIcon(imagenRedimensionadaAceptar));

        } catch (Exception e) {
            error("No cargaron los iconos");
        }

        // Centra el frame en la pantalla
        this.setLocationRelativeTo(null);

    }
    
    
    private void decorar() {
        this.lblMapaTitulo.setText("Mapa de " + campus.getNombre());
        try {
            ImageIcon iconoReturn = new ImageIcon(getClass().getResource("/imagenes/icons8-return-50.png"));
            this.btnCancelar.setIcon(iconoReturn);
            
            ImageIcon iconoAceptar = new ImageIcon(getClass().getResource("/imagenes/icons8-save-50.png"));
            Image imagenRedimensionadaAceptar = iconoAceptar.getImage().getScaledInstance(btnAceptar.getWidth(), btnAceptar.getHeight(), Image.SCALE_SMOOTH);
            this.btnAceptar.setIcon(new ImageIcon(imagenRedimensionadaAceptar));
        } catch (Exception e) {
            error("No cargaron los iconos");
        }

    }

    private void colocarImagenMapa() {
        SwingUtilities.invokeLater(() -> {
            try {
                // Lee la imagen desde la URL
                URL url = new URL("https://itson.mx/universidad/PublishingImages/mapas-campus/campus-centro.jpg");
                Image imagenDesdeURL = ImageIO.read(url);

                // Redimensiona la imagen si es necesario
                int anchoDeseado = 150; // Coloca el ancho deseado
                int altoDeseado = 150; // Coloca el alto deseado
                Image imagenRedimensionada = imagenDesdeURL.getScaledInstance(anchoDeseado, altoDeseado, Image.SCALE_SMOOTH);

                // Crea un ImageIcon con la imagen redimensionada
                ImageIcon icono = new ImageIcon(imagenRedimensionada);

                // Crea un JLabel con el ImageIcon
                JLabel label = new JLabel(icono);
                label.setVisible(true);

                this.pnlMapa.add(label);
                pnlMapa.revalidate();
                pnlMapa.repaint();
                 
                 
            } catch (IOException e) {
                error("Error al cargar la imagen desde la URL: " + e.getMessage());
            }
        });

    }
    
    private void colocarMarcadores(){
        
    }

    private void cerrar() {
        this.dispose();
    }
    
    private void error(String error) {
        JOptionPane.showMessageDialog(this, error);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblMapaTitulo = new javax.swing.JLabel();
        lblInstruccionEstatico = new javax.swing.JLabel();
        lblCancelarEstatico = new javax.swing.JLabel();
        lblAceptarEstatico = new javax.swing.JLabel();
        pnlMapa = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(22, 81, 198));

        lblMapaTitulo.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        lblMapaTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblMapaTitulo.setText("Mapa");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblMapaTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblMapaTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        lblInstruccionEstatico.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblInstruccionEstatico.setText("Haga clic en el mapa para colocar la ubicacion");

        lblCancelarEstatico.setText("Cancelar");

        lblAceptarEstatico.setText("Aceptar");

        pnlMapa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout pnlMapaLayout = new javax.swing.GroupLayout(pnlMapa);
        pnlMapa.setLayout(pnlMapaLayout);
        pnlMapaLayout.setHorizontalGroup(
            pnlMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 431, Short.MAX_VALUE)
        );
        pnlMapaLayout.setVerticalGroup(
            pnlMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 348, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblInstruccionEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(lblCancelarEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblAceptarEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87))
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlMapa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlMapa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblInstruccionEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCancelarEstatico)
                    .addComponent(lblAceptarEstatico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        cerrar();
    }//GEN-LAST:event_btnCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblAceptarEstatico;
    private javax.swing.JLabel lblCancelarEstatico;
    private javax.swing.JLabel lblInstruccionEstatico;
    private javax.swing.JLabel lblMapaTitulo;
    private javax.swing.JPanel pnlMapa;
    // End of variables declaration//GEN-END:variables
}
