package hotelguestify.models;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import hotelguestify.util.firebaseInit;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.common.collect.ImmutableList;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.*;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.JOptionPane;

public class auth {

    private String accessToken;
    private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
    private DatabaseReference user = _firebase.getReference("user");
    private ChildEventListener _user_child_listener;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseUser useruid;
    
    public auth(String email, String password) throws IOException {
        new firebaseInit().initFirebase();

        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n    \"email\": \"" + email + "\",\n    \"password\": \"" + password + "\",\n    \"returnSecureToken\": true\n}");
        Request request = new Request.Builder()
                .url("https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=AIzaSyAWrYbhD7Kb7YtbifRSTvVKzh7ZudWLMGE")
                .post(body)
                .addHeader("Authorization", "Bearer " + accessToken)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();

        if (response.isSuccessful()) {
            JsonObject jsonResponse = new Gson().fromJson(response.body().string(), JsonObject.class);
            String idToken = jsonResponse.get("idToken").getAsString();
            JOptionPane.showMessageDialog(null, "Login successfully " + idToken);
            if (user != null) {
                String uid = useruid.getUid();
                System.out.println("The user's UID is: " + uid);
            } else {
                // No user is signed in
            }
            // do something with the idToken
        } else {
            // handle the error
            int errorCode = response.code();
            String errorMessage = response.body().string();
            JsonObject errorJson = new Gson().fromJson(errorMessage, JsonObject.class);
            String messageKey = errorJson.get("error").getAsJsonObject().get("message").getAsString();
            JOptionPane.showMessageDialog(null, "Login failed with error code " + messageKey);
        }
    }
}
