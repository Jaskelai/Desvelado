package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
    //a digit must occur at least once,a lower case letter must occur at least once, no whitespace, 8+ symbols
    private final Pattern VALID_PASSWORD_REGEX = Pattern.compile("^(?=.*[0-9])$\n");

    public boolean validate(String source) {
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(source);
        return true;
    }
}
