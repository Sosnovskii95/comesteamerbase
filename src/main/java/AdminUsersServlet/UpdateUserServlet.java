package AdminUsersServlet;

import DAOLayer.DAOUserService;
import EntityLayer.Users;
import SHA1Encryption.Encryption;
import ServiceLayer.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "UpdateUserServlet", urlPatterns = "/adminview/updateuser")
public class UpdateUserServlet extends HttpServlet {
    //Обновление данных пользователя
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAOUserService usersService = new UserService();
        Encryption encryption = new Encryption();
        Users updateUser = usersService.getUserByIdUser(Integer.parseInt(request.getParameter("id_user")));

        updateUser.setId_user(Integer.parseInt(request.getParameter("id_user")));
        updateUser.setLogin(request.getParameter("login"));
        try {
            if (!request.getParameter("password").equals("")){
                //Шифрование пароля
                updateUser.setPassword(encryption.getPasswordEncryption(request.getParameter("password")));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        updateUser.setMagazine(Integer.parseInt(request.getParameter("id_magazine")));
        updateUser.setFio_user(request.getParameter("fio"));
        updateUser.setRole(request.getParameter("role"));
        updateUser.setValid_user(Boolean.parseBoolean(request.getParameter("valid")));
        //Обновление данных в БД
        usersService.updateUser(updateUser);
        response.sendRedirect("listuser");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
