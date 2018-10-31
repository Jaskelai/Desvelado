package utils;


import org.json.JSONObject;

public class FIeldValidator {
    public JSONObject validate(String email, String password, String bDay) {
        EmailValidator emailValidator = new EmailValidator();
        PasswordValidator passwordValidator = new PasswordValidator();
        BirthdayValidator birthdayValidator = new BirthdayValidator();
        JSONObject map = new JSONObject();
        if (!emailValidator.validate(email)) {
            map.put("emailError", "Wrong email!");
        }
        if (!passwordValidator.validate(password)) {
            map.put("passwordError", "Wrong password!");
        }
        if (!birthdayValidator.validate(bDay)) {
            map.put("bDayError","Wrong birthday!");
        }
        return map;
    }
}
