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

@WebServlet(name = "CreateBuyCardServlet", urlPatterns = "/adminview/createbuycard")
public class CreateBuyCardServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Пераметр отбора для отчета
        String status_card = request.getParameter("status_card");
        DAOClientUsersDiscountCardService service = new ClientUsersDiscountCardService();
        ExcelCreate create = new ExcelCreate();
        //Создаем очтет. Получаем путь к файлу
        String pathName = create.createListCardStatusCard(service.getListCardByuCard(status_card),status_card);
        //Передаем указанный путь в загрузчик
        request.setAttribute("pathName", pathName);
        //Вызываем загрузчик
        request.getRequestDispatcher("download").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
