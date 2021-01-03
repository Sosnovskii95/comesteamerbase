package AdminMagazineServlet;

import DAOLayer.DAOMagazine;
import EntityLayer.Magazine;
import ServiceLayer.MagazineService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditMagazineServlet", urlPatterns = "/adminview/editmagazine")
public class EditMagazineServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_magazine = Integer.parseInt(request.getParameter("id_magazine"));

        DAOMagazine magazineService = new MagazineService();
        Magazine magazine = magazineService.getMagazine(id_magazine);

        magazine.setName_m(request.getParameter("name_m"));
        magazineService.updateMagazine(magazine);

        request.getRequestDispatcher("listmagazine").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
