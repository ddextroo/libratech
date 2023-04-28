package libratech.models;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
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
    private String status;

    private ImageIcon createRoundedImageIcon(BufferedImage originalImage, int cornerRadius) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        BufferedImage clippedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = clippedImage.createGraphics();
        RoundRectangle2D.Float roundRect = new RoundRectangle2D.Float(0, 0, width, height, cornerRadius, cornerRadius);
        g.setClip(roundRect);
        g.drawImage(originalImage, 0, 0, null);
        g.dispose();

        Image scaledImage = clippedImage.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    private BufferedImage loadImageFromUrl(String url) throws IOException {
        BufferedImage originalImage = ImageIO.read(new URL(url));
        int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
        BufferedImage resizedImage = new BufferedImage(70, 70, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, 70, 70, null);
        g.dispose();
        return resizedImage;
    }

    private Border createEmptyBorderWithInsets(int top, int left, int bottom, int right) {
        return BorderFactory.createEmptyBorder(top, left, bottom, right);
    }

    public String getChildKey() {
        return childKey;
    }


    public ImageIcon getBookCoverUrl() {
        try {
            BufferedImage image = loadImageFromUrl(bookCoverUrl);
            return createRoundedImageIcon(image, 15);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
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

 

    public String getStatus() {
        return status;
    }


    public Book(String bookCoverUrl, String author, String title, String date, String deck, String genre, String dewey, String publisher, String quantity, String shelf, String status) {
        this.childKey = childKey;
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
}
