package servlets;

import entities.User;
import repositories.UsersRepository;
import repositories.UsersRepositoryDB;
import utils.PasswordEncryptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

public class LoginServlet extends HttpServlet {
    private UsersRepository usersRepository;
    private Connection connection;

    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        usersRepository = new UsersRepositoryDB();
        if (usersRepository.isExist(new User(email, PasswordEncryptor.hashPassword(password),null,false,null ))) {
            HttpSession session = req.getSession();
            session.setAttribute("user", email);
            System.out.println("AUTHENTIFICATION COMPLETED");
            resp.sendRedirect("index.jsp");
        }
    }
}
