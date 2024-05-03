
package presentacion;

import DTOS.campus.CampusConsultableDTO;
import DTOS.campus.UbicacionDTO;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import pruebas.PruebaMarcador;

/**
 *
 * @author t1pas
 */
public class CDMapa extends javax.swing.JDialog {

    private final UbicacionDTO ubicacion;
    private java.util.List<UbicacionDTO> ubicaciones=new ArrayList<>();
    private final CampusConsultableDTO campus;
    private BufferedImage imagenMapa;
    private Point posicionMarcador;
    private ImageIcon iconoMarcador;
    private final CDUbicacion pantallaAnterior;
    private JPanel panelImagen;
    
    /**
     * Creates new form CDMapa
     * @param pantallaAnterior
     * @param parent
     * @param modal
     * @param campus
     * @param ubicacion
     */
    public CDMapa(CDUbicacion pantallaAnterior,java.awt.Frame parent, boolean modal,CampusConsultableDTO campus, UbicacionDTO ubicacion) {
        super(parent, modal);
        
        this.ubicacion = new UbicacionDTO();
        this.ubicacion.setCampus(ubicacion.getCampus());
        this.ubicacion.setDescripcion(ubicacion.getDescripcion());
        this.ubicacion.setEventos(ubicacion.getEventos());
        this.ubicacion.setId(ubicacion.getId());
        this.ubicacion.setIdentificador(ubicacion.getIdentificador());
        this.ubicacion.setPosicionX(ubicacion.getPosicionX());
        this.ubicacion.setPosicionY(ubicacion.getPosicionY());
        
        this.campus=campus;
        this.pantallaAnterior=pantallaAnterior;
        this.setUndecorated(true);
        this.setResizable(false);
        initComponents();

        colocarMapa();
        decorar();
        limpiarUbicaciones();
        colocarMarcadores();
        
    }
    
    private void colocarMapa(){
        
        // Carga la imagen del mapa
        try {
            URL urlMapa = new URL(campus.getUrl());
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
        
        // Crear un panel para mostrar la imagen
        panelImagen = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imagenMapa, 0, 0, this); // Dibujar la imagen en el panel
            }
        };
        panelImagen.setSize(435, 350); // Establecer el tamaño del panel de imagen
        panelImagen.setLocation(0, 0); // Establecer la ubicación del panel de imagen

        // Agregar el panel de imagen al pnlMapa
        pnlMapa.add(panelImagen);
        
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
        new JLabel(iconoMarcador);
        
        // Agrega un listener de clic del ratón al panel del mapa
        panelImagen.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                posicionMarcador = e.getPoint();
                panelImagen.repaint(); // Vuelve a dibujar el panel para mostrar el marcador en la nueva posición

                // Aquí puedes guardar la posición del marcador (posicionMarcador)
                //System.out.println("Posición del marcador: " + posicionMarcador);
                
                Double x = posicionMarcador.getX();
                Double y = posicionMarcador.getY();

                ubicacion.setPosicionX(x);
                ubicacion.setPosicionY(y);
                ubicaciones.remove(ubicacion);
                ubicaciones.add(ubicacion);

                colocarMarcadores();
            }
        });
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

    private void limpiarUbicaciones() {
        if (campus.getUbicaciones() != null) {
            ubicaciones = campus.getUbicaciones();
        }

        java.util.List<UbicacionDTO> ubicacionesNuevas = new ArrayList<>();

        //Descarta las ubicaciones sin coordenadas
        for (UbicacionDTO ubicacion : ubicaciones) {
            if (ubicacion.getPosicionX() !=null && ubicacion.getPosicionY()!=null) {
                ubicacionesNuevas.add(ubicacion);
            }
        }
        ubicaciones = ubicacionesNuevas;
        ubicaciones.remove(ubicacion);
    }
    
    private void colocarMarcadores() {

        panelImagen.setLayout(null);
        //Quita los marcadores
        panelImagen.removeAll();

        //System.out.println(ubicaciones.size());
        for (UbicacionDTO ubicacionAux : ubicaciones) {
            if (ubicacionAux.getPosicionX() != null && ubicacionAux.getPosicionY() != null) {

                Double x = ubicacionAux.getPosicionX();
                Double y = ubicacionAux.getPosicionY();
                String identificador = ubicacionAux.getIdentificador();
                // Crea un nuevo JLabel con la imagen del marcador
                JLabel marcador = new JLabel(iconoMarcador);
                marcador.setSize(iconoMarcador.getIconWidth(), iconoMarcador.getIconHeight());
                marcador.setLocation(x.intValue() - (iconoMarcador.getIconWidth() / 2), y.intValue() - (iconoMarcador.getIconHeight() / 2));

                // Agrega un listener de clic del ratón al marcador
                marcador.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        super.mouseClicked(e);
                        // Muestra un mensaje con el identificador de la ubicación
                        JOptionPane.showMessageDialog(null, identificador);
                    }
                });

                // Agrega el marcador al panel del mapa
                panelImagen.add(marcador);
            }
        }

    }

    private void confirmar() {
        if (ubicacion != null) {
            if (ubicacion.getPosicionX() != null && ubicacion.getPosicionY() != null) {
                pantallaAnterior.colocarCoordenadas(this.ubicacion.getPosicionX(), this.ubicacion.getPosicionY());
                error("Coordenadas colocadas");
                this.dispose();
                return;
            }
        }
        error("Haga click en el mapa para colocar el marcador");

    }

    private void cerrar() {
        this.pantallaAnterior.setVisible(true);  
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
        lblCancelarEstatico = new javax.swing.JLabel();
        lblAceptarEstatico = new javax.swing.JLabel();
        pnlMapa = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
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
        confirmar();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        cerrar();
    }//GEN-LAST:event_formWindowClosed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblAceptarEstatico;
    private javax.swing.JLabel lblCancelarEstatico;
    private javax.swing.JLabel lblMapaTitulo;
    private javax.swing.JPanel pnlMapa;
    // End of variables declaration//GEN-END:variables
}
