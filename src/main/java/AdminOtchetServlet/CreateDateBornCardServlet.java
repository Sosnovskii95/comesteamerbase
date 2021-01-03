package AdminOtchetServlet;

import DAOLayer.DAOClientUsersDiscountCardService;
import Date.WorkDate;
import ExcelCreate.ExcelCreate;
import ServiceLayer.ClientUsersDiscountCardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CreateDateBornCardServlet",urlPatterns = "/adminview/createdateborncard")
public class CreateDateBornCardServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Полчаем диапазон дат
        WorkDate workDate = new WorkDate();
        String dateFrom = WorkDate.parseDate(request.getParameter("dateFrom"));
        String dateBy = WorkDate.parseDate(request.getParameter("dateBy"));

        DAOClientUsersDiscountCardService service = new ClientUsersDiscountCardService();
        ExcelCreate create = new ExcelCreate();
        //Создаем отчет. Возвращаем путь к файлу
        String pathName = create.createListCardDateBorn(service.getListCardDateBorn(dateFrom,dateBy));
        //Передаем путь к файлу
        request.setAttribute("pathName", pathName);
        //Вызываем загручик
        request.getRequestDispatcher("download").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
