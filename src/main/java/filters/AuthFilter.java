package filters;

import dao.UsersDao;
import dao.UsersDaoJdbcImpl;
import utils.TokenGenerator;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        if (Arrays.stream(request.getCookies()).filter(c -> c.getName().equals("email"))
//                .findAny().orElse(null) {
//        }
//        Cookie cookie = Arrays.stream(request.getCookies()).filter(c -> c.getName().equals("token"))
//                .findAny().orElse(null);
//        if (cookie == null) {
//            String token = new TokenGenerator(20).nextString();
//            cookie = new Cookie("token",token);
//            UsersDao usersDao = new UsersDaoJdbcImpl();
//            usersDao.update();
//            cookie.setPath(request.getContextPath());
//            response.addCookie(cookie);
//        } else {
//            String
//        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
