package imagenesURL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JPanel;

public class ImageFromURL {      

    public static void imagenURL(JPanel panel, String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            ImageIcon imageIcon = new ImageIcon(url);
            Image image = imageIcon.getImage();
            Image scaledImage = image.getScaledInstance(400, 300, Image.SCALE_SMOOTH);
            ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
            
            JLabel label = new JLabel(scaledImageIcon);
            label.setVisible(true);
            panel.add(label);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        panel.setVisible(true);
    }

}
