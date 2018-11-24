package servlets;

import utils.Helper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VideoCatalogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Helper.checkSession(req);
        req.getServletContext().getRequestDispatcher("/WEB-INF/views/videoCatalog.jsp").forward(req, resp);
    }
}
