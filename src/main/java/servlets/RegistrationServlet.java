package servlets;

import entities.User;
import org.json.JSONObject;
import repositories.UsersRepository;
import repositories.UsersRepositoryDB;
import utils.CountryGenerator;
import utils.FIeldRegValidator;
import utils.PasswordEncryptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CountryGenerator countryGenerator = new CountryGenerator();
        List<String> countries = countryGenerator.getCountries();
        req.setAttribute("listCountries", countries);
        req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String bDay = req.getParameter("bDay");
        String country = req.getParameter("country");
        Boolean gender = (req.getParameter("gender").equals("male"));
        FIeldRegValidator fIeldValidator = new FIeldRegValidator();
        JSONObject resultValidation = fIeldValidator.validate(email, password, bDay);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        if (resultValidation.toString().equals("{}")) {
            User user = new User(email, PasswordEncryptor.hashPassword(password), country, gender, bDay);
            UsersRepository userRepository = new UsersRepositoryDB();
            if (!userRepository.isExist(user)) {
                userRepository.save(user);
                resultValidation.put("url", "login");
            } else {
                resultValidation.put("existDBError","Email already exist in database!");
            }
        }
        PrintWriter pw = new PrintWriter(resp.getWriter());
        pw.write(resultValidation.toString());
        pw.flush();
        pw.close();
    }
}
