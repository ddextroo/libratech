
package libratech.design;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Image;

public class ImageScaler {

    public void scaleImage(JLabel jlabel, JPanel jpanel, String filename) {
        ImageIcon icon = new ImageIcon(filename);
        Image img = icon.getImage().getScaledInstance(jlabel.getWidth(), jlabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(img);
        int iconWidth = scaledIcon.getIconWidth();
        int iconHeight = scaledIcon.getIconHeight();

        // calculate the x and y position to center the image within the panel
        int x = (jpanel.getWidth() - iconWidth) / 2;
        int y = (jpanel.getHeight() - iconHeight) / 2;

        // create a new JLabel to hold the icon and set its position within the panel
        JLabel label = new JLabel();
        label.setIcon(scaledIcon);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setBounds(x, y, iconWidth, iconHeight);

        // add the label to the panel
        jpanel.add(label);
    }
}