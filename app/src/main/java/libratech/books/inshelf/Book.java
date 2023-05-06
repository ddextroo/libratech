package libratech.books.inshelf;


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
    private String genre;
    private String dewey;
    private String publisher;
    private String quantity;
    private String shelf;
    private StatusType status;
    private Icon icon;

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

    public String getGenre() {
        return genre;
    }

    public String getDewey() {
        return dewey;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getShelf() {
        return shelf;
    }

    public StatusType getStatus() {
        return status;
    }

    public Object[] toRowTable(EventAction event) {
        
        
        return new Object[]{title, publisher, genre, author, dewey, quantity, deck, status, childKey, new ModelAction(this, event)};
    }

    public Book(String bookCoverUrl, String author, String title, String date, String deck, String genre, String dewey, String publisher, String quantity, String shelf, StatusType status) {
        this.bookCoverUrl = bookCoverUrl;
        this.author = author;
        this.title = title;
        this.date = date;
        this.deck = deck;
        this.genre = genre;
        this.dewey = dewey;
        this.publisher = publisher;
        this.quantity = quantity;
        this.shelf = shelf;
        this.status = status;
    }
    public Book(String title, String publisher, String genre, String author,  String dewey, String quantity, String deck, StatusType status, String key) {
        this.author = author;
        this.title = title;
        this.deck = deck;
        this.genre = genre;
        this.dewey = dewey;
        this.publisher = publisher;
        this.quantity = quantity;
        this.status = status;
        this.childKey = key;
        
    }
    public Book() {
        
    }
    public Book(String childkey) {
        this.childKey = childkey;
    }
}
