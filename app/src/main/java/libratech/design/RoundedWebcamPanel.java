/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libratech.design;

import javax.swing.JPanel;

import com.github.sarxos.webcam.WebcamPanel;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedWebcamPanel extends JPanel {

    private WebcamPanel webcampanel;

    public RoundedWebcamPanel(WebcamPanel webcampanel) {
        this.webcampanel = webcampanel;
        setOpaque(false);
        setLayout(new BorderLayout());
        add(webcampanel, BorderLayout.CENTER);
    }

    public WebcamPanel getWebcampanel() {
        return webcampanel;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));
        g2.dispose();
        super.paintComponent(graphics);
    }

}
