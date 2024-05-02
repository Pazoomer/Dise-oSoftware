package imagenesURL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImageFromURL {      

    public static void imagenURL(JPanel panel, String imageUrl) {
        try {
            // Lee la imagen desde la URL
                URL url = new URL(imageUrl);
                Image imagenDesdeURL = ImageIO.read(url);
                
                // Redimensiona la imagen si es necesario
                int anchoDeseado = 150; // Coloca el ancho deseado
                int altoDeseado = 150; // Coloca el alto deseado
                Image imagenRedimensionada = imagenDesdeURL.getScaledInstance(anchoDeseado, altoDeseado, Image.SCALE_SMOOTH);
                
                // Crea un ImageIcon con la imagen redimensionada
                ImageIcon icono = new ImageIcon(imagenRedimensionada);
                
                // Crea un JLabel con el ImageIcon
                JLabel label = new JLabel(icono);
                
                // Agrega el JLabel al JFrame
                panel.add(label);
                
                // Ajusta el tamaño del JFrame para que se adapte a la imagen
                //frame.pack();
                
                // Centra el JFrame en la pantalla
                //frame.setLocationRelativeTo(null);
                
                // Hace visible el JFrame
                //frame.setVisible(true);
            } catch (IOException e) {
                System.out.println("Error al cargar la imagen desde la URL: " + e.getMessage());
            }
    }

}
