package libratech.models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidation {

    private final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";

    public boolean validate(String email)
    {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
