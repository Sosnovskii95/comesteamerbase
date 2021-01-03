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

@WebServlet(name = "CreateValidCardServlet", urlPatterns = "/adminview/createvalidcard")
public class CreateValidCardServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Получаем параметры для запроса
        boolean valid = Boolean.parseBoolean(request.getParameter("valid"));

        DAOClientUsersDiscountCardService service = new ClientUsersDiscountCardService();
        ExcelCreate create = new ExcelCreate();
        //Создаем отчет. Получаем путь к файлу
        String pathName = create.createListCardValid(service.getListCardValid(valid), valid);
        //Передаем в загручик путь к файлу
        request.setAttribute("pathName", pathName);
        //Вызываем загручик
        request.getRequestDispatcher("download").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
