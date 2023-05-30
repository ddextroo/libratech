/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libratech.design;

/**
 *
 * @author Lenovo
 */
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JCheckBox;
        
public class JCheckBoxCustom extends JCheckBox {
    private final int border = 4;
    
    public JCheckBoxCustom () {
   setCursor(new Cursor(Cursor.HAND_CURSOR));
   setOpaque (false);
   setBackground (new Color (69, 124, 235));
   }
 
@Override
public void paint (Graphics grphcs) {
    super.paint (grphcs);
    Graphics2D g2 = (Graphics2D) grphcs;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    int ly = (getHeight()-16)/2;
    if (isSelected ()) {
        if (isEnabled ()) {
            g2.setColor(getBackground ());
            }
        else {
            g2.setColor(Color.GRAY);
        }
        g2.fillRoundRect(1, ly, 16, 16, border, border);
    } else {
    
    }
}
}