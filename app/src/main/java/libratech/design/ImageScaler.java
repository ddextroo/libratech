package libratech.design;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Image;

public class ImageScaler {

    public void scaleImage(JLabel jlabel, String filename) {

        if (jlabel == null) {
            // handle the case where jlabel is null
            return;
        }

        Dimension size = jlabel.getPreferredSize();

        ImageIcon icon = new ImageIcon(filename);
        Image img = icon.getImage().getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(img);
        jlabel.setIcon(scaledIcon);

    }
}
