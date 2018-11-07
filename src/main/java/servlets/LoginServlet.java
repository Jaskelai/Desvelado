package servlets;

import dao.UsersDao;
import dao.UsersDaoJdbcImpl;
import entities.User;
import org.json.JSONObject;
import utils.PasswordEncryptor;

import javax.servlet.ServletException;
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
        UsersDao usersDao = new UsersDaoJdbcImpl();
        User user = usersDao.findByEmail(email);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        JSONObject result = new JSONObject();
        if (user == null) {
            result.put("emailError","Email does not exist in database!");
        } else if (!PasswordEncryptor.hashPassword(password).equals(user.getPassword())) {
            result.put("passwordError","Wrong password!");
        }
        if (result.toString().equals("{}")) {
            result.put("url","index");
        }

        Writer pw = resp.getWriter();
        pw.write(result.toString());
        pw.close();
    }
}
