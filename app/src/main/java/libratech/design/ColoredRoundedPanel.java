/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libratech.design;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

public class ColoredRoundedPanel extends JPanel {

    private static final Color BLUE_COLOR = new Color(4, 28, 52);
    private static final int RADIUS = 10;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();

        // Calculate the size of the blue area (1/4 of the panel height)
        int blueWidth = getWidth();
        int blueHeight = getHeight() / 4;

        // Draw the blue area with rounded corners
        g2.setColor(BLUE_COLOR);
        g2.fill(new RoundRectangle2D.Float(0, 0, blueWidth, blueHeight + RADIUS, RADIUS, RADIUS));

        // Draw the rounded corners for the remaining area
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Float(0, blueHeight, getWidth(), getHeight() - blueHeight, RADIUS, RADIUS));

        g2.dispose();
    }

}
