/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libratech.design;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class NotificationLabel extends JLabel {

    private boolean showBadge;

    public NotificationLabel() {
        setOpaque(false);
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        setPreferredSize(new Dimension(100, 100)); // Adjust the size as needed
        showBadge = false;
    }

    public void showBadge() {
        showBadge = true;
        setEnabled(true);
        repaint();
    }

    public void hideBadge() {
        showBadge = false;
        setEnabled(false);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (showBadge) {
            int diameter = Math.min(getWidth(), getHeight()) / 4;
            int x = getWidth() - diameter;
            int y = 6;
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.RED);
            g2.fill(new RoundRectangle2D.Double(x, y, 5, 5, 1000, 1000));
        }
    }
}
