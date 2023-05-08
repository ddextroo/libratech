package libratech.models.Dashboard;

import com.google.firebase.database.*;

public class retBooks {

    private DatabaseReference databaseRef;

    public retBooks(String ck) {
        this.databaseRef = FirebaseDatabase.getInstance().getReference().child("books/inshelf/" + ck);
    }

    public void addChildListener(String childKey, ChildCallback callback) {
        Query query = databaseRef.orderByKey().equalTo(childKey);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                    String key = dataSnapshot.child("key").getValue(String.class);
                    String bookCoverUrl = dataSnapshot.child("cover").getValue(String.class);
                    String bookTitle = dataSnapshot.child("booktitle").getValue(String.class);
                    String schoolName = dataSnapshot.child("booktitle").getValue(String.class);
                    String publisher = dataSnapshot.child("publisher").getValue(String.class);
                    String genre = dataSnapshot.child("genre").getValue(String.class);
                    String author = dataSnapshot.child("bookauthor").getValue(String.class);
                    String dewey = dataSnapshot.child("dewey").getValue(String.class);
                    String quantity = dataSnapshot.child("quantity").getValue(String.class);
                    String deck = dataSnapshot.child("deck").getValue(String.class);
                    String shelf = dataSnapshot.child("shelf").getValue(String.class);
                    String date = dataSnapshot.child("date").getValue(String.class);
                    String status = dataSnapshot.child("status").getValue(String.class);
                    callback.onChildRetrieved(bookCoverUrl, bookTitle, publisher, genre, author, dewey, quantity, deck, shelf, date, status);
                
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
                    String key = dataSnapshot.child("key").getValue(String.class);
                    String bookCoverUrl = dataSnapshot.child("cover").getValue(String.class);
                    String bookTitle = dataSnapshot.child("booktitle").getValue(String.class);
                    String publisher = dataSnapshot.child("publisher").getValue(String.class);
                    String genre = dataSnapshot.child("genre").getValue(String.class);
                    String author = dataSnapshot.child("bookauthor").getValue(String.class);
                    String dewey = dataSnapshot.child("dewey").getValue(String.class);
                    String quantity = dataSnapshot.child("quantity").getValue(String.class);
                    String deck = dataSnapshot.child("deck").getValue(String.class);
                    String shelf = dataSnapshot.child("shelf").getValue(String.class);
                    String date = dataSnapshot.child("date").getValue(String.class);
                    String status = dataSnapshot.child("status").getValue(String.class);
                    callback.onChildRetrieved(bookCoverUrl, bookTitle, publisher, genre, author, dewey, quantity, deck, shelf, date, status);
                
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }

    public interface ChildCallback {

        public void onChildRetrieved(String bookCoverUrl, String bookTitle, String publisher, String genre, String author, String dewey, String quantity, String deck, String shelf, String date, String status);
    }
}
