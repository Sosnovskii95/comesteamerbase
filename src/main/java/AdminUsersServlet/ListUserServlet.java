package AdminUsersServlet;

import DAOLayer.DAOMagazine;
import DAOLayer.DAOUserService;
import EntityLayer.Magazine;
import EntityLayer.Users;
import ServiceLayer.MagazineService;
import ServiceLayer.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListUserServlet", urlPatterns = "/adminview/listuser")
public class ListUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Список всех пользователей в БД
        DAOUserService usersService = new UserService();
        List<Users> usersList = usersService.getAllUsers();
        DAOMagazine magazine = new MagazineService();
        List<Magazine> magazineList = magazine.getAllNameMagazineByAllUser();
        request.setAttribute("userlist",usersList);
        request.setAttribute("magazinelist", magazineList);
        request.getRequestDispatcher("users/alluser.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
