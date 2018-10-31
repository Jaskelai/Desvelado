package servlets;

import org.json.JSONObject;
import utils.CountryGenerator;
import utils.FIeldValidator;

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
        Boolean sex = (req.getParameter("sex").equals("male"));
        FIeldValidator fIeldValidator = new FIeldValidator();
        JSONObject result = fIeldValidator.validate(email, password, bDay);
        System.out.println(result.toString());
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        System.out.println(result.toString());
        if (result.toString().equals("{}")) {
            result.put("uri","login.jsp");
        }
        System.out.println(result.toString());
        PrintWriter pw = new PrintWriter(resp.getWriter());
        System.out.println(result.toString());
        pw.write(result.toString());
        pw.flush();
        pw.close();
    }
}
