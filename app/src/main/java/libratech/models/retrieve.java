package libratech.models;

import com.google.firebase.database.*;
import java.util.*;

public class retrieve {

    private DatabaseReference dbRef;
    private List<String> payck;
    private List<Map<String, Object>> polmap;
    private List<Map<String, Object>> lmap;

    public retrieve(String path) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        dbRef = database.getReference(path);
        payck = new ArrayList<>();
        polmap = new ArrayList<>();
        lmap = new ArrayList<>();
    }

    public void retrieveData() {
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                polmap.clear();
                lmap.clear();
                payck.clear();
                try {
                    GenericTypeIndicator<HashMap<String, Object>> ind = new GenericTypeIndicator<HashMap<String, Object>>() {
                    };
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        HashMap<String, Object> map = data.getValue(ind);
                        String userKey = data.getKey();
                        String password = data.child("pass").getValue(String.class);
                        Map<String, Object> userMap = new HashMap<>();
                        userMap.put("uid", userKey);
                        userMap.put("password", password);
                        payck.add(data.getKey());
                        lmap.add(map);
                        polmap.add(userMap);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Error retrieving data from Firebase: " + databaseError.getMessage());
            }
        });
    }

    public List<String> getPayck() {
        return payck;
    }

    public List<Map<String, Object>> getPolmap() {
        return polmap;
    }
}
