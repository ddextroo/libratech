
package hotelguestify.util;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class firebaseInit {
    public void initFirebase() {
        try {
            FileInputStream refreshToken = null;
            
            refreshToken = new FileInputStream("credentials.json");
            
            FirebaseOptions options = null;
            try {
                options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(refreshToken))
                        .setDatabaseUrl("https://guestify-985ec-default-rtdb.firebaseio.com/")
                        .build();
            } catch (IOException ex) {
                Logger.getLogger(firebaseInit.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            FirebaseApp.initializeApp(options);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(firebaseInit.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    }
