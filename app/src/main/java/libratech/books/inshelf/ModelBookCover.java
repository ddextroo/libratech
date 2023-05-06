
package libratech.books.inshelf;

import java.awt.image.BufferedImage;

public class ModelBookCover {

    public BufferedImage getIcon() {
        return image;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setIcon(BufferedImage icon) {
        this.image = icon;
    }

    public ModelBookCover(BufferedImage image) {
        this.image = image;
    }
    public ModelBookCover(BufferedImage icon, String key) {
        this.image = icon;
        this.key = key;
    }

    public ModelBookCover() {
    }

    private BufferedImage image;
    private String key;
}