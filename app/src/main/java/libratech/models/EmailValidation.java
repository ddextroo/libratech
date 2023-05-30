package libratech.models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidation {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Z0-9._%+\\-#']+@[A-Z0-9.-]+\\.(?:[A-Z]{2}|com|org|net|edu|gov|mil|biz|info|mobi|name|aero|asia|jobs|museum)$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String email) {
        String[] invalidStrings = {
                "..", ".@"
        };
        for (String illegal : invalidStrings) {
            if (email.contains(illegal)) {
                return false;
            }
        }

        Matcher m = EMAIL_PATTERN.matcher(email.trim());
        return m.matches();
    }
}
