package servlets;

import dao.UsersDaoJdbcImpl;
import entities.User;
import org.json.JSONObject;
import utils.Encryptor;
import utils.TokenGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public class LoginServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        UsersDaoJdbcImpl usersDao = new UsersDaoJdbcImpl();
        User user = usersDao.findByEmail(email);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        JSONObject result = new JSONObject();
        if (usersDao.findByEmail(email) == null) {
            result.put("fieldError","Email does not exist in database!");
        } else if (!Encryptor.hashPassword(password).equals(usersDao.findByEmail(email).getPassword())) {
            result.put("fieldError","Passowrd is wrong!");
        }
        if (result.toString().equals("{}")) {
            result.put("url","hello");
            String token = new TokenGenerator(20).nextString();
            Cookie cookieToken = new Cookie("token", token);
            cookieToken.setMaxAge(1000 * 60 * 60);
            Cookie cookieEmail = new Cookie("email", Encryptor.hashPassword(email));
            resp.addCookie(cookieEmail);
            resp.addCookie(cookieToken);
            usersDao.saveToken(token,user);
        }
        Writer pw = resp.getWriter();
        pw.write(result.toString());
        pw.close();
    }
}
