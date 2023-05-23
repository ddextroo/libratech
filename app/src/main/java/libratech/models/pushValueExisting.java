/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libratech.models;

import com.google.firebase.database.*;
import java.util.*;

public class pushValueExisting {

    private DatabaseReference databaseReference;
    private String path = "user";
    private String pushKey;

    public pushValueExisting(String pushKey) {
        this.pushKey = pushKey;
        this.databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public void pushData(String path, HashMap<String, Object> data) {
        DatabaseReference childReference = databaseReference.child(path).child(pushKey);
        childReference.updateChildren(data, (error, ref) -> {
            if (error != null) {
                System.err.println("Data could not be saved: " + error.getMessage());
            } else {
                System.out.println("Data saved successfully.");
            }
        });
    }
}
