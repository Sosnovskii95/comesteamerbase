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

@WebServlet(name = "EditUserServlet", urlPatterns = "/adminview/edituser")
public class EditUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Редактирование пользователя
        int id_user = Integer.parseInt(request.getParameter("id_user"));
        DAOUserService usersService = new UserService();
        Users users = usersService.getUserByIdUser(id_user);
        DAOMagazine magazineService = new MagazineService();
        List<Magazine> magazineList = magazineService.getAllNameMagazineByAllUser();
        request.setAttribute("id_user", id_user);
        request.setAttribute("userlist" ,users);
        request.setAttribute("magazinelist", magazineList);
        request.getRequestDispatcher("users/edituser.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
