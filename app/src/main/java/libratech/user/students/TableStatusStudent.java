
package libratech.user.students;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JLabel;

public class TableStatusStudent extends JLabel {

    public StatusTypeStudent getType() {
        return type;
    }

    public TableStatusStudent() {
        setForeground(Color.WHITE);
    }

    private StatusTypeStudent type;

    public void setType(StatusTypeStudent type) {
        this.type = type;
        setText(type.toString());
        repaint();
    }


    @Override
    protected void paintComponent(Graphics grphcs) {
        if (type != null) {
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            GradientPaint g;
            if (type == StatusTypeStudent.Active) {
                g = new GradientPaint(0, 0, new Color(0,255,0), 0, getHeight(), new Color(0,128,0));
            } else {
                g = new GradientPaint(0, 0, new Color(0,255,0), 0, getHeight(), new Color(0,128,0));
            }
            g2.setPaint(g);
            g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));
        }
        super.paintComponent(grphcs);
    }
}
