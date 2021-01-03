package FilterServlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/adminview/*")
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        //Существует ли сессиия
        boolean loggedIn = session != null && session.getAttribute("userName") != null && session.getAttribute("userRole") != null;
        String loggenUrl = request.getContextPath()+"/invate";

            if (loggedIn) {
                //Если существует то получаем роль
                String userRole = session.getAttribute("userRole").toString();
                if (userRole.equals("admin")) {
                    filterChain.doFilter(request,response);
                }else if (userRole.equals("user")){
                    response.sendRedirect(request.getContextPath()+"/userview/user.jsp");
                }else{
                    response.sendRedirect(loggenUrl);
                }
                //Если нет то на страницу входа.
            } else {
                response.sendRedirect(loggenUrl);
            }
    }

    @Override
    public void destroy() {

    }
}