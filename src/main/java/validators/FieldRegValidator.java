package validators;


import org.json.JSONObject;

public class FieldRegValidator {
    public JSONObject validate(String email, String password, String bDay) {
        EmailRegValidator emailValidator = new EmailRegValidator();
        PasswordRegValidator passwordValidator = new PasswordRegValidator();
        BirthdayRegValidator birthdayValidator = new BirthdayRegValidator();
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
