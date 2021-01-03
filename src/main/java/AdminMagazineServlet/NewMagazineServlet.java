package AdminMagazineServlet;

import DAOLayer.DAOMagazine;
import ServiceLayer.MagazineService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "NewMagazineServlet", urlPatterns = "/adminview/newmagazine")
public class NewMagazineServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAOMagazine magazineService = new MagazineService();
        magazineService.addNewMagazine(request.getParameter("name_m"));
        response.sendRedirect("magazine.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
