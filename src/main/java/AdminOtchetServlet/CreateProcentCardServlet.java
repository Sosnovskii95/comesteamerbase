package AdminOtchetServlet;

import DAOLayer.DAOClientUsersDiscountCardService;
import ExcelCreate.ExcelCreate;
import ServiceLayer.ClientUsersDiscountCardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CreateProcentCardServlet",urlPatterns = "/adminview/createprocentcard")
public class CreateProcentCardServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Параметры для запроса
        float procentFrom = Float.parseFloat(request.getParameter("procentFrom"));
        float procentBy = Float.parseFloat(request.getParameter("procentBy"));

        DAOClientUsersDiscountCardService service = new ClientUsersDiscountCardService();
        ExcelCreate create = new ExcelCreate();
        //Создаем отчет. Согласно параметрам. Получаем путь к файлу
        String pathName = create.createListProcent(service.getListProcent(procentFrom,procentBy),procentFrom,procentBy);
        //Передаем путь к файлу
        request.setAttribute("pathName", pathName);
        //Вызываем загручик
        request.getRequestDispatcher("download").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }
}
