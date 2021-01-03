package FilterServlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //получение данных сессии
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        //URL Запроса/переадресации на Servlet входа
        String loginURI = request.getContextPath() + "/invate";
        String loggenUrl2 =request.getContextPath()+"/index.jsp";
        //Если сессия ранее создана
        boolean loggedIn = session != null && session.getAttribute("userName") != null && session.getAttribute("userRole") != null;
        boolean loginRequest = request.getRequestURI().equals(loginURI) || request.getRequestURI().equals(loggenUrl2);
        String path = request.getRequestURI();
//        if (){
//            System.out.println(path);
//            filterChain.doFilter(servletRequest,servletResponse);
//        }
        //Если запрос пришел со страницы с входом или сессия не пуста даем добро следовать дальше
        //Если нет ридерект на страницу входа
        if (loggedIn || loginRequest || path.matches(".*(css|png)")){
            filterChain.doFilter(request, response);
        }else{
            response.sendRedirect(loggenUrl2);
        }
    }

    @Override
    public void destroy() {
    }
}