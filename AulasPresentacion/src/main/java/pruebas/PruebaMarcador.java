
package pruebas;
import DTOS.campus.UbicacionDTO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

public class PruebaMarcador {
    private JFrame frame;
    private JPanel panelMapa;
    private JLabel labelMarcador;
    private BufferedImage imagenMapa;
    private Point posicionMarcador;
    private final ImageIcon iconoMarcador;
    private java.util.List<UbicacionDTO> ubicaciones=new ArrayList<>();
    private UbicacionDTO ubicacionActual;

    public PruebaMarcador() {

        frame = new JFrame("Mapa con Marcador");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelMapa = new JPanel() {
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
        };
        panelMapa.setLayout(null); // Para poder posicionar el marcador manualmente

        frame.add(panelMapa, BorderLayout.CENTER);

        // Carga la imagen del mapa
        try {
            URL urlMapa = new URL("https://itson.mx/universidad/PublishingImages/mapas-campus/campus-centro.jpg");
            BufferedImage imagenOriginal = ImageIO.read(urlMapa);

            // Define el tamaño deseado para la imagen
            int anchoDeseado = 480; // Coloca el ancho deseado en píxeles
            int altoDeseado = 360; // Coloca el alto deseado en píxeles

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
        labelMarcador = new JLabel(iconoMarcador);
        labelMarcador.setOpaque(false);
        
        panelMapa.add(labelMarcador);

        // Establece el tamaño del frame
        frame.setSize(800, 600);

        // Centra el frame en la pantalla
        frame.setLocationRelativeTo(null);

        // Muestra el frame
        frame.setVisible(true);

        // Agrega un listener de clic del ratón al panel del mapa
        panelMapa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                posicionMarcador = e.getPoint();
                panelMapa.repaint(); // Vuelve a dibujar el panel para mostrar el marcador en la nueva posición

                // Aquí puedes guardar la posición del marcador (posicionMarcador)
                System.out.println("Posición del marcador: " + posicionMarcador);
                
                Double x = posicionMarcador.getX();
                Double y = posicionMarcador.getY();

                ubicacionActual.setPosicionX(x);
                ubicacionActual.setPosicionY(y);
                ubicaciones.add(ubicacionActual);

                colocarMarcadores();
            }
        });
        limpiarUbicaciones();
        colocarMarcadores();
    }

    private void limpiarUbicaciones() {
        
        ubicacionActual=new UbicacionDTO();
        ubicacionActual.setIdentificador("LV-000");
        
        UbicacionDTO ubicacion1 = new UbicacionDTO();
        ubicacion1.setPosicionX(200D);
        ubicacion1.setPosicionY(200D);
        ubicacion1.setIdentificador("LV-200");
        
        UbicacionDTO ubicacion2 = new UbicacionDTO();
        ubicacion2.setPosicionX(300D);
        ubicacion2.setPosicionY(300D);
        ubicacion2.setIdentificador("LV-300");

        ubicaciones.add(ubicacion1);
        ubicaciones.add(ubicacion2);
        
        java.util.List<UbicacionDTO> ubicacionesNuevas=new ArrayList<>();
        
        //Descarta las ubicaciones sin coordenadas
        for (UbicacionDTO ubicacion:ubicaciones) {
            if (ubicacion.getPosicionX()!=null && ubicacion.getPosicionY()!=null) {
                ubicacionesNuevas.add(ubicacion);
            }
        }
        ubicaciones=ubicacionesNuevas;
        ubicaciones.add(ubicacionActual);
    }
    
    private void colocarMarcadores() {

        panelMapa.removeAll();

        for (UbicacionDTO ubicacion : ubicaciones) {
            if (ubicacion.getPosicionX() != null && ubicacion.getPosicionY() != null) {

                Double x = ubicacion.getPosicionX();
                Double y = ubicacion.getPosicionY();
                String identificador = ubicacion.getIdentificador();
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
                panelMapa.add(marcador);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PruebaMarcador::new);
    }
}
