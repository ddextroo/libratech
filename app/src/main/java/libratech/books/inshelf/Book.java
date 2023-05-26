package libratech.books.inshelf;

import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.border.Border;

public class Book {

    private String childKey;
    private String bookCoverUrl;
    private String author;
    private String title;
    private String date;

    private String deck;
    private String classification;
    private String controlNumber;
    private String publisher;
    private String copies;
    private String shelf;
    private StatusType status;
    private Icon icon;

    private String barcode;
    private String status_string;
    private String due_date;
    private String borrowed_date;
    int status_copies;
    private int fines;

    public String getStatus_string() {
        return status_string;
    }

    public int getFines() {
        return fines;
    }
    

//    private ImageIcon createRoundedImageIcon(BufferedImage originalImage, int cornerRadius) {
//        int width = originalImage.getWidth();
//        int height = originalImage.getHeight();
//        BufferedImage clippedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
//        Graphics2D g = clippedImage.createGraphics();
//        RoundRectangle2D.Float roundRect = new RoundRectangle2D.Float(0, 0, width, height, cornerRadius, cornerRadius);
//        g.setClip(roundRect);
//        g.drawImage(originalImage, 0, 0, null);
//        g.dispose();
//
//        Image scaledImage = clippedImage.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
//        return new ImageIcon(scaledImage);
//    }
//
//    private BufferedImage loadImageFromUrl(String url) throws IOException {
//        BufferedImage originalImage = ImageIO.read(new URL(url));
//        int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
//        BufferedImage resizedImage = new BufferedImage(70, 70, type);
//        Graphics2D g = resizedImage.createGraphics();
//        g.drawImage(originalImage, 0, 0, 70, 70, null);
//        g.dispose();
//        return resizedImage;
//    }
    private Border createEmptyBorderWithInsets(int top, int left, int bottom, int right) {
        return BorderFactory.createEmptyBorder(top, left, bottom, right);
    }

    public String getChildKey() {
        return childKey;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setChildKey(String childKey) {
        this.childKey = childKey;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getDeck() {
        return deck;
    }

    public String getClassification() {
        return classification;
    }

    public String getControlNumber() {
        return controlNumber;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getCopies() {
        return copies;
    }

    public String getShelf() {
        return shelf;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setControlNumber(String controlNumber) {
        this.controlNumber = controlNumber;
    }

    public Object[] toRowTable(EventAction event) {

        return new Object[]{title, publisher, classification, author, barcode, status_copies, status, new ModelAction(this, event)};
    }

    public Object[] toRowTableBorrow(EventAction event) {

        return new Object[]{barcode, classification, status, new ModelAction(this, event)};
    }

    public Object[] toRowTableReceipt(EventAction event) {

        return new Object[]{title, barcode, due_date, new ModelAction(this, event)};
    }
       public Object[] toRowTableReturn(EventAction event) {

        return new Object[]{title, barcode, borrowed_date, due_date, fines, new ModelAction(this, event)};
    }

    public Book(String bookCoverUrl, String author, String title, String date, String deck, String classification, String controlNumber, String publisher, String copies, String shelf, StatusType status) {
        this.bookCoverUrl = bookCoverUrl;
        this.author = author;
        this.title = title;
        this.date = date;
        this.deck = deck;
        this.classification = classification;
        this.controlNumber = controlNumber;
        this.publisher = publisher;
        this.copies = copies;
        this.shelf = shelf;
        this.status = status;
    }

    public Book(String title, String publisher, String classification, String author, String barcode, int status_copies, StatusType status, String childKey) {
        this.author = author;
        this.title = title;
        this.classification = classification;
        this.barcode = barcode;
        this.publisher = publisher;
        this.status_copies = status_copies;
        this.status = status;
        this.childKey = childKey;

    }

    public Book(String barcode, String classification, StatusType status, String status_string) {
        this.barcode = barcode;
        this.classification = classification;
        this.status = status;
        this.status_string = status_string;
    }

    public Book(String title, String barcode, String due_date) {
        this.title = title;
        this.barcode = barcode;
        this.due_date = due_date;
    }

    public Book(String title, String barcode, String borrowed_date, String due_date, int fines) {
        this.title = title;
        this.barcode = barcode;
        this.borrowed_date = borrowed_date;
        this.due_date = due_date;
        this.fines = fines;
    }

    public Book() {

    }

    public Book(String controlNumber) {
        this.controlNumber = controlNumber;
    }
}
