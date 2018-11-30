package services;

import dao.CountriesJdbcImpl;
import dao.UsersDao;
import dao.UsersDaoJdbcImpl;
import entities.User;
import exceptions.DBException;
import org.json.JSONObject;
import utils.PasswordEncryptor;
import utils.FieldsRegValidator;

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

    public String getCountries() throws DBException {
        CountriesJdbcImpl countriesDaoJdbc = new CountriesJdbcImpl();
        List<String> countries = countriesDaoJdbc.findAll();
        return countries.toString().replaceAll("\\[", "").replaceAll("]", "").replaceFirst(" ", "");
    }

    public JSONObject save(User user) throws DBException {
        FieldsRegValidator fieldValidator = new FieldsRegValidator();
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

    public User findByEmail(String email) throws DBException {
        return usersDao.findByEmail(email);
    }

    public User findByUsername(String username) throws DBException {
        return usersDao.findByUsername(username);
    }

    public int findIdByUsername(String username) throws DBException {
        return usersDao.findIdByUsername(username);
    }

    public User findByToken(String token) throws DBException {
        return usersDao.findByToken(token);
    }

    public JSONObject validateLogin(String email, String password) throws DBException {
        JSONObject result = new JSONObject();
        if (usersDao.findByEmail(email) == null) {
            result.put("fieldError", "Email does not exist in database!");
        } else if (!PasswordEncryptor.hashPassword(password).equals(usersDao.findByEmail(email).getPassword())) {
            result.put("fieldError", "Password is wrong!");
        }
        return result;
    }

    public void update(User user) throws DBException {
        usersDao.update(user);
    }
}
