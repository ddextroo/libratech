
package libratech.books.inshelf;

import javax.swing.Icon;

public class ModelBookCover {

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public ModelBookCover(Icon icon) {
        this.icon = icon;
    }

    public ModelBookCover() {
    }

    private Icon icon;
}