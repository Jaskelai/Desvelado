package servlets;

import dao.UsersDaoJdbcImpl;
import entities.User;
import utils.Encryptor;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookieToken = Arrays.stream(req.getCookies()).filter(c -> c.getName().equals("token"))
                .findAny().orElse(null);
        Cookie cookieEmail = Arrays.stream(req.getCookies()).filter(c -> c.getName().equals("email"))
                .findAny().orElse(null);
        if (cookieToken.getValue().equals("")) {
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            User user = new UsersDaoJdbcImpl().findByToken(cookieToken.getValue());
            req.setAttribute("username", user.getUsername());
            if (Encryptor.hashPassword(user.getEmail()).equals(cookieEmail.getValue())) {
                req.getServletContext().getRequestDispatcher("/WEB-INF/views/hello.jsp").forward(req, resp);
            } else {
                resp.sendRedirect(req.getContextPath() + "/login");
            }
        }
    }
}
