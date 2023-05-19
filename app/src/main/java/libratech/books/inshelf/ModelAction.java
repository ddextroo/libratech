
package libratech.books.inshelf;


public class ModelAction {

    public Book getBook() {
        return books;
    }

    public void setBook(Book book) {
        this.books = book;
    }

    public EventAction getEvent() {
        return event;
    }

    public void setEvent(EventAction event) {
        this.event = event;
    }

    public ModelAction(Book books, EventAction event) {
        this.books = books;
        this.event = event;
    }

    public ModelAction() {
    }

    private Book books;
    private EventAction event;
}
