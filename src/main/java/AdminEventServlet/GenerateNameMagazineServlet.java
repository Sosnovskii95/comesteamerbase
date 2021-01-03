package AdminEventServlet;

import DAOLayer.DAOMagazine;
import Date.WorkDate;
import ServiceLayer.MagazineService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GenerateNameMagazineServlet", urlPatterns = "/adminview/GenerateNameMagazineServlet")
public class GenerateNameMagazineServlet extends HttpServlet {
    //Генерируем страницу с названиями магазинов и датой начала месяца и текущей датой
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAOMagazine magazine = new MagazineService();
        request.setAttribute("dateStart", WorkDate.getStartMonth());
        request.setAttribute("dateNow", WorkDate.getNowDateToString());
        request.setAttribute("list", magazine.getAllNameMagazineUserTrue());
        request.getRequestDispatcher("report/report.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
