package libratech.models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidation {

    private static final String EMAIL_PATTERN = "^\\w+@(\\w+\\.)?\\w+\\.edu\\.ph$";

    public boolean validate(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
