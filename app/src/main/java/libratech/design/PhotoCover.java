package libratech.design;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class PhotoCover extends JComponent {

    private int cornerRadius = 15;

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        repaint();
    }

    private BufferedImage image;

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        if (image != null) {
            BufferedImage scaledImage = getScaledImage(image, getWidth(), getHeight());
            Rectangle2D bounds = getImageBounds(scaledImage, getWidth(), getHeight());
            g2.setClip(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius));
            g2.drawImage(scaledImage, (int) bounds.getX(), (int) bounds.getY(), (int) bounds.getWidth(), (int) bounds.getHeight(), null);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(70, 70);
    }

    private BufferedImage getScaledImage(BufferedImage image, int width, int height) {
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        double scaleFactor = Math.min((double) width / imageWidth, (double) height / imageHeight);
        int scaledWidth = (int) (imageWidth * scaleFactor);
        int scaledHeight = (int) (imageHeight * scaleFactor);
        Image scaledImage = image.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
        BufferedImage bufferedImage = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bufferedImage.createGraphics();
        g2.drawImage(scaledImage, 0, 0, null);
        g2.dispose();
        return bufferedImage;
    }

    private Rectangle2D getImageBounds(BufferedImage image, int width, int height) {
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        double scaleFactor = Math.max((double) width / imageWidth, (double) height / imageHeight);
        int scaledWidth = (int) (imageWidth * scaleFactor);
        int scaledHeight = (int) (imageHeight * scaleFactor);
        int x = (width - scaledWidth) / 2;
        int y = (height - scaledHeight) / 2;
        return new Rectangle2D.Double(x, y, scaledWidth, scaledHeight);
    }
}
