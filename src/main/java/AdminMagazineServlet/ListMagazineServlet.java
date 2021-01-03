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
import java.util.List;

@WebServlet(name = "ListMagazineServlet", urlPatterns = "/adminview/listmagazine")
public class ListMagazineServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAOMagazine magazineService = new MagazineService();
        List<Magazine> magazineList = magazineService.getAllNameMagazineByAllUser();
        request.setAttribute("mag_list", magazineList);
        request.getRequestDispatcher("magazine/allmagazine.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
