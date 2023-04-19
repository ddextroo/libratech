package libratech.design;

import java.awt.AlphaComposite;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Image;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class iconColor {

    public void colorImage(JLabel jlabel, String filename) {

        try {
            BufferedImage img = ImageIO.read(
                    iconColor.class.getResource(
                            filename
                    )
            );
            BufferedImage coloredImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Color targetColor = Color.RED;

            Graphics2D g2d = coloredImg.createGraphics();
            g2d.setColor(targetColor);

            for (int y = 0; y < img.getHeight(); y++) {
                for (int x = 0; x < img.getWidth(); x++) {
                    int rgb = img.getRGB(x, y);
                    Color color = new Color(rgb, true);
                    if (color.getAlpha() > 0) {
                        float alpha = color.getAlpha() / 255.0f;
                        g2d.setComposite(AlphaComposite.SrcOver.derive(alpha));
                        g2d.fillRect(x, y, 1, 1);
                    }
                }
            }

            g2d.dispose();
            jlabel.add(new JLabel(new ImageIcon(coloredImg)));
        } catch (IOException ex) {
            Logger.getLogger(iconColor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
