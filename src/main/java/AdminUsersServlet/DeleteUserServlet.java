package AdminUsersServlet;

import DAOLayer.DAOUserService;
import ServiceLayer.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteUserServlet",urlPatterns = "/adminview/deleteuser")
public class DeleteUserServlet extends HttpServlet {
    //Удаление пользователя по его ID
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAOUserService usersService = new UserService();
        int id_user = Integer.parseInt(request.getParameter("id_user"));
        usersService.deleteUser(id_user);
        response.sendRedirect("listuser");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
