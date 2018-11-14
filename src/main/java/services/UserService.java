package services;

import dao.CountriesDaoJdbcImpl;
import dao.UsersDao;
import dao.UsersDaoJdbcImpl;
import entities.User;
import org.json.JSONObject;
import utils.PasswordEncryptor;
import utils.FieldRegValidator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class UserService {
    private static UserService userService;
    private UsersDao usersDao = new UsersDaoJdbcImpl();

    private UserService() {

    }

    public static UserService getUserServiceInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public String getCountries() {
        CountriesDaoJdbcImpl countriesDaoJdbc = new CountriesDaoJdbcImpl();
        List<String> countries = countriesDaoJdbc.findAll();
        System.out.println(countries);
        return countries.toString().replaceAll("\\[", "").replaceAll("]", "").replaceAll(" ", "");
    }

    public JSONObject validateReg(User user) {
        FieldRegValidator fieldValidator = new FieldRegValidator();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        JSONObject resultValidation = fieldValidator.validate(user.getEmail(),
                user.getPassword(), sdf.format(user.getBirthday()));
        user.setPassword(PasswordEncryptor.hashPassword(user.getPassword()));
        if (resultValidation.toString().equals("{}")) {
            if (usersDao.findByEmail(user.getEmail()) != null) {
                resultValidation.put("fieldError", "Email already exist in database!");
            } else if (usersDao.findByUsername(user.getUsername()) != null) {
                resultValidation.put("fieldError", "User with this username already exist in database!");
            } else {
                usersDao.save(user);
                resultValidation.put("url", "login");
            }
        }
        return resultValidation;
    }

    public User findByEmail(String email) {
        return usersDao.findByEmail(email);
    }
    public User findByToken(String token) {
        return usersDao.findByToken(token);
    }

    public JSONObject validateLogin(String email, String password) {
        JSONObject result = new JSONObject();
        if (usersDao.findByEmail(email) == null) {
            result.put("fieldError", "Email does not exist in database!");
        } else if (!PasswordEncryptor.hashPassword(password).equals(usersDao.findByEmail(email).getPassword())) {
            result.put("fieldError", "Password is wrong!");
        }
        return result;
    }

    public void update(User user) {
        usersDao.update(user);
    }
}
