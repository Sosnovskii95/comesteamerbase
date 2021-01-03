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

@WebServlet(name = "CreateOldCardServlet",urlPatterns = "/adminview/createoldcard")
public class CreateOldCardServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAOClientUsersDiscountCardService service = new ClientUsersDiscountCardService();
        ExcelCreate create = new ExcelCreate();
        //Создаем отчет. Получаем путь к файлу
        String pathName = create.createListCardOldCard(service.getListOldCard());
        //Передаем путь к файлу
        request.setAttribute("pathName", pathName);
        //Вызываем загручик
        request.getRequestDispatcher("download").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
