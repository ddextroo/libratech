package libratech.models;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.ErrorCode;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.AuthErrorCode;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class auth {

    private FirebaseAuth firebaseAuth;
    private String email;
    private String password;
    private String uid;
    private DatabaseReference databaseReference;
    private HashMap<String, Object> m;
    private pushValue v;
    private retrieve r;

    public auth(String email, String password) throws FileNotFoundException {
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.databaseReference = FirebaseDatabase.getInstance().getReference();
        this.email = email;
        this.password = password;
        this.uid = uid;
    }

    public void signUp() throws FirebaseAuthException {
        try {
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(email)
                    .setPassword(password);
            UserRecord userRecord = firebaseAuth.createUser(request);
            uid = userRecord.getUid();
            v = new pushValue(userRecord.getUid());
            m = new HashMap<>();
            m.put("email", email);
            m.put("pass", password);
            m.put("uid", uid);
            v.pushData("users", m);
        } catch (FirebaseAuthException e) {
            if (e.getErrorCode().equals(AuthErrorCode.EMAIL_ALREADY_EXISTS)) {
                JOptionPane.showMessageDialog(null, "Error: Email already exists", "Error", ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error: Invalid format", "Error", ERROR_MESSAGE);
            }
        }

    }

    public String[] login() throws FirebaseAuthException {
        //todo retrieve all users then condition if uid is equals to the stored users on the database
        String[] ret = new String[3];
        String authentication = "false";
        UserRecord userRecord = firebaseAuth.getUserByEmail(email);
        String uid = userRecord.getUid();
        r = new retrieve("users");
        r.retrieveData();
        while (r.getPolmap().isEmpty()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(auth.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        List<Map<String, Object>> polmap = r.getPolmap();
        for (Map<String, Object> userMap : polmap) {
            String userKey = (String) userMap.get("uid");
            String password1 = (String) userMap.get("password");
            ret[0] = userKey;
            System.out.println("User key: " + userKey + " Password: " + password1);
            System.out.println("User key local: " + uid + " Password local: " + password);
            if (userKey.equals(uid)) {
                System.out.println("UID_DB = " + userKey + ": UID = " + uid);
                if (password1.equals(password)) {
                    System.out.println("PASS_DB = " + password1 + ": PASS = " + password);
                    authentication = "true";
                } else {
                    authentication = "false";
                }
            } else {
            authentication = "false";
            }
        }
        ret[1] = authentication;
        return ret;
    }
}