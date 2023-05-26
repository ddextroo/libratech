package libratech.books.inshelf;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JLabel;

public class TableStatus extends JLabel {

    public StatusType getType() {
        return type;
    }

    public TableStatus() {
        setForeground(Color.WHITE);
    }

    private StatusType type;

    public void setType(StatusType type) {
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
            if (type == StatusType.Available) {
                g = new GradientPaint(0, 0, new Color(0, 255, 0), 0, getHeight(), new Color(0, 128, 0));
            } else if (type == StatusType.Borrowed) {
                g = new GradientPaint(0, 0, new Color(255, 165, 0), 0, getHeight(), new Color(255, 69, 0));
            } else if (type == StatusType.Overdue) {
                g = new GradientPaint(0, 0, new Color(255, 165, 0), 0, getHeight(), new Color(255, 69, 0));
            } else if (type == StatusType.Lost) {
                g = new GradientPaint(0, 0, new Color(255, 69, 0), 0, getHeight(), new Color(255, 69, 0));
            } else if (type == StatusType.Damaged) {
                g = new GradientPaint(0, 0, new Color(255, 69, 0), 0, getHeight(), new Color(255, 69, 0));
            } else {
                g = new GradientPaint(0, 0, new Color(255, 69, 0), 0, getHeight(), new Color(255, 69, 0));
            }
            g2.setPaint(g);
            g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));
        }
        super.paintComponent(grphcs);
    }
}
