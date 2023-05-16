
package libratech.design;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;

public class ScrollBarCustom extends JScrollBar {

    public ScrollBarCustom() {
        setUI(new ModernScrollBarUI());
        setPreferredSize(new Dimension(12, 70));
        setForeground(new Color(180, 180, 180));
        setUnitIncrement(20);
        setOpaque(false);
    }
    
}
