package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorHandler extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Throwable throwable = (Throwable)
                request.getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer)
                request.getAttribute("javax.servlet.error.status_code");
        String requestUri = (String)
                request.getAttribute("javax.servlet.error.request_uri");
        if (throwable == null && statusCode == null) {
            request.setAttribute("errorHead", "500 INTERNAL SERVER ERROR");
            request.setAttribute("errorBody", "Something wrong with the server");
        } else if (statusCode == 404) {
            request.setAttribute("errorHead", "404 NOT FOUND");
            request.setAttribute("errorBody", "Error information is missing. Are you sure you want " + requestUri + " ?");
        } else {
            request.setAttribute("errorHead", statusCode + " SERVER ERROR");
            request.setAttribute("errorBody", "Something wrong with the server");
        }
        request.getServletContext().getRequestDispatcher("/WEB-INF/views/errorpage.jsp").forward(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}
