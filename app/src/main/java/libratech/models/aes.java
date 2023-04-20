package libratech.models;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class aes {
    
    private final String password = "64f5717f24618a4c88f981d4c4a2a4ee";
    
    public aes() {

    }
    
    public String getPassword() {
        return this.password;
    }
    
    public static String encryptString(String plainText, String password) throws Exception {
        byte[] plainTextBytes = plainText.getBytes("UTF-8");
        byte[] passwordBytes = password.getBytes("UTF-8");

        SecretKeySpec secretKeySpec = new SecretKeySpec(passwordBytes, "AES");

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

        byte[] encryptedBytes = cipher.doFinal(plainTextBytes);
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decryptString(String encryptedText, String password) throws Exception {
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] passwordBytes = password.getBytes("UTF-8");

        SecretKeySpec secretKeySpec = new SecretKeySpec(passwordBytes, "AES");

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes, "UTF-8");
    }
}
