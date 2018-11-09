package servlets;

import dao.CountriesDaoJdbcImpl;
import dao.UsersDao;
import dao.UsersDaoJdbcImpl;
import entities.User;
import org.json.JSONObject;
import validators.FieldRegValidator;
import utils.Encryptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CountriesDaoJdbcImpl cdji = new CountriesDaoJdbcImpl();
        List<String> countries = cdji.findAll();
        req.setAttribute("listCountries", countries.toString().replaceAll("\\[", "").replaceAll("\\]", ""));
        req.getServletContext().getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String bDay = req.getParameter("bDay");
        String country = req.getParameter("country");
        Boolean gender = req.getParameter("gender").equals("Male");
        FieldRegValidator fieldValidator = new FieldRegValidator();
        JSONObject resultValidation = fieldValidator.validate(email, password, bDay);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        if (resultValidation.toString().equals("{}")) {
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            User user = null;
            try {
                user = new User(username, email, Encryptor.hashPassword(password), country, gender, sdf.parse(bDay));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            UsersDao usersDao = new UsersDaoJdbcImpl();
            if (usersDao.findByEmail(user.getEmail()) != null) {
                resultValidation.put("fieldError", "Email already exist in database!");
            } else if (usersDao.findByUsername(user.getUsername()) != null) {
                resultValidation.put("fieldError", "User with this username already exist in database!");
            } else {
                usersDao.save(user);
                resultValidation.put("url", "login");
            }
        }
        Writer pw = resp.getWriter();
        pw.write(resultValidation.toString());
        pw.close();
    }
}
