package hotelguestify.models;

import com.google.firebase.database.*;
import java.util.*;

public class pushValue {

    private DatabaseReference databaseReference;
    private String path = "user";
    private String pushKey;

    public pushValue(String pushkey) {
        this.path = path;
        this.databaseReference = FirebaseDatabase.getInstance().getReference();
        this.pushKey = pushkey;
    }

    public void pushData(String path, HashMap<String, Object> data) {
        DatabaseReference childReference = databaseReference.child(path).child(pushKey);
        Map<String, Object> updates = new HashMap<>();
        updates.put(pushKey, data);
        childReference.getParent().updateChildren(updates, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    System.err.println("Data could not be saved: " + databaseError.getMessage());
                } else {
                    System.out.println("Data saved successfully.");
                }
            }
        });
    }

}
