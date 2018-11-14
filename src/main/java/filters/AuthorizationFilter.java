package filters;

import services.UserService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

public class AuthorizationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            Cookie tokenCookie = Arrays.stream(request.getCookies()).filter(c -> c.getName().equals("token")).findAny().orElse(null);
            if (tokenCookie != null) {
                session = request.getSession();
                UserService userService = UserService.getUserServiceInstance();
                String username = userService.findByToken(tokenCookie.getValue()).getUsername();
                session.setAttribute("username",username);
            }
        }
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
