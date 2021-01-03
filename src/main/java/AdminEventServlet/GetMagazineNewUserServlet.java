package AdminEventServlet;

import DAOLayer.DAOMagazine;
import EntityLayer.Magazine;
import ServiceLayer.MagazineService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetMagazineNewUserServlet", urlPatterns = "/adminview/getmagazine")
public class GetMagazineNewUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAOMagazine magazineService = new MagazineService();
        List<Magazine> magazineList = magazineService.getAllNameMagazineByAllUser();

        request.setAttribute("magazinelist", magazineList);
        request.getRequestDispatcher("users/newuser.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
