package UserServlet;

import DAOLayer.DAOMagazine;
import DAOLayer.DAOUserService;
import Date.WorkDate;
import EntityLayer.Users;
import SHA1Encryption.Encryption;
import ServiceLayer.MagazineService;
import ServiceLayer.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "InvateServlet", urlPatterns = "/invate")
public class InvateServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("login");
        String password = request.getParameter("password");

        //Объект генерирующий хеш пароля
        Encryption encryption = new Encryption();
        HttpSession session = request.getSession();

        DAOUserService usersService = new UserService();
        Users users = new Users();
        try {
            users = usersService.getRoleUser(userName, encryption.getPasswordEncryption(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (users.getId_user() != 0) {
            DAOMagazine magazine = new MagazineService();
            String name_m = magazine.getNameMagazineByIdMagazine(users.getMagazine());
            if (users.getRole().equals("user")) {
                session.setAttribute("userName", userName);
                session.setAttribute("userRole", users.getRole());
                session.setAttribute("name",users.getFio_user());
                session.setAttribute("magazine", name_m);
                session.setAttribute("id_user", users.getId_user());
                session.setAttribute("date_now", WorkDate.getNowDateToString());
                response.sendRedirect("userview/user.jsp");
            }
            if (users.getRole().equals("admin")) {
                session.setAttribute("id_user", users.getId_user());
                session.setAttribute("date_now", WorkDate.getNowDateToString());
                session.setAttribute("userName", userName);
                session.setAttribute("magazine", name_m);
                session.setAttribute("userRole", users.getRole());
                response.sendRedirect("adminview/admin.jsp");
            }
        }else
        {
            response.sendRedirect("index.jsp");
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }
}
