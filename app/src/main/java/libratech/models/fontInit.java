package libratech.models;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class fontInit {

    public static void initialize() {
        try {
            Font regFont = Font.createFont(Font.TRUETYPE_FONT, new File("poppinsr.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(regFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }
}
