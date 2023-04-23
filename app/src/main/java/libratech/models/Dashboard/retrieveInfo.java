package libratech.models.Dashboard;

import com.google.firebase.database.*;

public class retrieveInfo {

    private DatabaseReference databaseRef;

    public retrieveInfo() {
        this.databaseRef = FirebaseDatabase.getInstance().getReference().child("users");
    }

    public void addChildListener(String childKey, ChildCallback callback) {
        Query query = databaseRef.orderByKey().equalTo(childKey);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                if (dataSnapshot.hasChild("school_name") && dataSnapshot.hasChild("school_id") && dataSnapshot.hasChild("url")) {
                    String schoolName = dataSnapshot.child("school_name").getValue(String.class);
                    String idNum = dataSnapshot.child("school_id").getValue(String.class);
                    String url = dataSnapshot.child("url").getValue(String.class);
                    callback.onChildRetrieved(schoolName, idNum, url);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
                 if (dataSnapshot.hasChild("school_name") && dataSnapshot.hasChild("school_id") && dataSnapshot.hasChild("url")) {
                    String schoolName = dataSnapshot.child("school_name").getValue(String.class);
                    String idNum = dataSnapshot.child("school_id").getValue(String.class);
                    String url = dataSnapshot.child("url").getValue(String.class);
                    callback.onChildRetrieved(schoolName, idNum, url);
                }
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

        public void onChildRetrieved(String schoolName, String idNum, String url);
    }
}
