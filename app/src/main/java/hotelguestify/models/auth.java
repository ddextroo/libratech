package hotelguestify.models;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import hotelguestify.util.firebaseInit;
import java.io.IOException;
import javax.swing.JOptionPane;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class auth{

    private FirebaseAuth firebaseAuth;
    private String email;
    private String password;
    private DatabaseReference databaseReference;
    private HashMap<String, Object> m;
    private pushValue v;
    

    public auth(String email, String password) throws FileNotFoundException {
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.databaseReference = FirebaseDatabase.getInstance().getReference();
        this.email = email;
        this.password = password;
    }

    public void signUp() throws FirebaseAuthException {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(email)
                .setPassword(password);
        UserRecord userRecord = firebaseAuth.createUser(request);
        System.out.println("Successfully created user: " + userRecord.getUid());
        v = new pushValue(userRecord.getUid());
        m = new HashMap<>();
        m.put("email", email);
        m.put("pass", password);
        v.pushData("users", m);
    }

    public String login() throws FirebaseAuthException {
        UserRecord userRecord = firebaseAuth.getUserByEmail(email);
        String uid = firebaseAuth.createCustomToken(userRecord.getUid());
        return uid;
    }

//    public void forgotPassword(String email) throws FirebaseAuthException {
//        firebaseAuth.sendPasswordResetEmail(email);
//        System.out.println("Password reset email sent to: " + email);
//    }
}
