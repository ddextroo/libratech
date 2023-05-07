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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import javax.swing.table.DefaultTableModel;
import libratech.books.inshelf.Book;
import libratech.books.inshelf.StatusType;
import libratech.books.inshelf.TableStatus;
import libratech.util.firebaseInit;

public class auth {

    private FirebaseAuth firebaseAuth;
    private String email;
    private String password;
    private String uid;
    private DatabaseReference dbRef;
    private HashMap<String, Object> m;
    private pushValue v;
    private retrieve r;
    private Calendar cal = Calendar.getInstance();

    public auth(String email, String password) throws FileNotFoundException {
        this.firebaseAuth = FirebaseAuth.getInstance();
        new firebaseInit().initFirebase();
        this.dbRef = FirebaseDatabase.getInstance().getReference("users");
        this.email = email;
        this.password = password;
        this.uid = uid;
    }

    public boolean signUp(String school_name, String school_id, String url) throws FirebaseAuthException {
        try {
            String getnow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());

            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(email)
                    .setPassword(password);
            UserRecord userRecord = firebaseAuth.createUser(request);
            uid = userRecord.getUid();
            v = new pushValue(userRecord.getUid());
            m = new HashMap<>();
            m.put("email", email);
            m.put("pass", password);
            m.put("school_name", school_name);
            m.put("school_id", school_id);
            m.put("url", url);
            m.put("timestamp", getnow);
            m.put("uid", uid);
            v.pushData("users", m);
            JOptionPane.showMessageDialog(null, "Success: Account Created Successfully", "Welcome to LibraTech", INFORMATION_MESSAGE);
            return true;

        } catch (FirebaseAuthException e) {
            if (e.getErrorCode().equals(AuthErrorCode.EMAIL_ALREADY_EXISTS)) {
                JOptionPane.showMessageDialog(null, "Error: Email already exists", "Error", ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error: Invalid format", "Error", ERROR_MESSAGE);
            }
            return false;
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
                if (password1.equals(password)) {
                    System.out.println("UID_DB = " + userKey + ": UID = " + uid);
                    System.out.println("PASS_DB = " + password1 + ": PASS = " + password);
                    ret[1] = "true";
                    return ret;
                } else {
                    ret[1] = "false";
                    return ret;
                }
            } else {
                ret[1] = "false";
                return ret;
            }
        }
        ret[1] = "false";
        return ret;
       
    }
}
