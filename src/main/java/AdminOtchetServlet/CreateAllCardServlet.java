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

@WebServlet(name = "CreateAllCardServlet", urlPatterns = "/adminview/createallcard")
public class CreateAllCardServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAOClientUsersDiscountCardService service = new ClientUsersDiscountCardService();
        ExcelCreate create = new ExcelCreate();
        //Создаем выбранный отчет. Передаем в переменную путь к созданному файлу
        String pathName = create.createListAllCard(service.getListAllCard());
        request.setAttribute("pathName",pathName);
        //Вызываем сервлет загрузки документа на пользовательской ПК
        request.getRequestDispatcher("download").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
