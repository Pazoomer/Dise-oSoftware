
package pruebas;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class PruebaURL2 {
    
    public static void main(String[] args) {
        // URL de la imagen en la web
        String urlImagen = "https://itson.mx/universidad/PublishingImages/mapas-campus/campus-centro.jpg"; // Ejemplo de URL de imagen
        
        SwingUtilities.invokeLater(() -> {
            // Crea un JFrame
            JFrame frame = new JFrame("Imagen en Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            try {
                // Lee la imagen desde la URL
                URL url = new URL(urlImagen);
                Image imagenDesdeURL = ImageIO.read(url);
                
                // Redimensiona la imagen si es necesario
                int anchoDeseado = 150; // Coloca el ancho deseado
                int altoDeseado = 150; // Coloca el alto deseado
                Image imagenRedimensionada = imagenDesdeURL.getScaledInstance(anchoDeseado, altoDeseado, Image.SCALE_SMOOTH);
                
                // Crea un ImageIcon con la imagen redimensionada
                ImageIcon icono = new ImageIcon(imagenRedimensionada);
                
                // Crea un JLabel con el ImageIcon
                JLabel label = new JLabel(icono);
                
                // Crea un JPanel para contener la imagen
                JPanel panel = new JPanel();
                panel.add(label);
                
                // Agrega el panel al JFrame
                frame.getContentPane().add(panel);
                
                // Ajusta el tamaño del JFrame para que se adapte al panel
                frame.pack();
                
                // Centra el JFrame en la pantalla
                frame.setLocationRelativeTo(null);
                
                // Hace visible el JFrame
                frame.setVisible(true);
            } catch (IOException e) {
                System.out.println("Error al cargar la imagen desde la URL: " + e.getMessage());
            }
        });
    }
}

