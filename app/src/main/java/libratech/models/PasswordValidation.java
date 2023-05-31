package libratech.models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidation {

    private final String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$"; 

    public boolean isValidPassword(String password)
    {
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
