package libratech.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import libratech.auth.splash;

public class getUID {

    private File file;
    private String uid;
    aes aes = new aes();

    public String getUid() {
        this.uid = "";
        this.file = new File("uid.txt");
        if (file.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                reader.close();
                uid = sb.toString();
                try {
                    uid = aes.decryptString(uid, aes.getPassword());
                } catch (Exception ex) {
                    Logger.getLogger(splash.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return uid;
    }

    public getUID() {

    }

}
