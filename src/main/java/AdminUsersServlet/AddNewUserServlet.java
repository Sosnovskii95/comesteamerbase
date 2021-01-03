package AdminUsersServlet;

import DAOLayer.DAOUserService;
import EntityLayer.Users;
import SHA1Encryption.Encryption;
import ServiceLayer.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "AddNewUserServlet", urlPatterns = "/adminview/addnewuser")
public class AddNewUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Users new_user = new Users();
        Encryption encryption = new Encryption();
        //Добавление нового пользователя Администратором
        new_user.setLogin(request.getParameter("login"));
        try {
            new_user.setPassword(encryption.getPasswordEncryption(request.getParameter("password")));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        new_user.setMagazine(Integer.parseInt(request.getParameter("id_magazine")));
        new_user.setFio_user(request.getParameter("fio"));
        new_user.setRole(request.getParameter("role"));
        new_user.setValid_user(Boolean.parseBoolean(request.getParameter("valid")));

        DAOUserService usersService = new UserService();
        //Добавление пользователя в БД
        usersService.addNewUser(new_user);
        response.sendRedirect("admin.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    }
}
