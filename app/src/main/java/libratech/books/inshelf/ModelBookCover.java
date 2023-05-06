
package libratech.books.inshelf;

import javax.swing.Icon;

public class ModelBookCover {

    public Icon getIcon() {
        return icon;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public ModelBookCover(Icon icon) {
        this.icon = icon;
    }
    public ModelBookCover(Icon icon, String key) {
        this.icon = icon;
        this.key = key;
    }

    public ModelBookCover() {
    }

    private Icon icon;
    private String key;
}