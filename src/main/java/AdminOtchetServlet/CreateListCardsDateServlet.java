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

@WebServlet(name = "CreateListCardsDateServlet", urlPatterns = "/adminview/createlistcardsdate")
public class CreateListCardsDateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WorkDate workDate = new WorkDate();
        String dateFrom = WorkDate.parseDate(request.getParameter("dateFrom"));
        String dateTo = WorkDate.parseDate(request.getParameter("dateTo"));

        DAOClientUsersDiscountCardService daoClientUsersDiscountCardService = new ClientUsersDiscountCardService();
        ExcelCreate create = new ExcelCreate();
        String pathName = create.createListCardsDate(daoClientUsersDiscountCardService.getReportDateCreateCard(dateFrom, dateTo));
        request.setAttribute("pathName",pathName);
        //Вызываем сервлет загрузки документа на пользовательской ПК
        request.getRequestDispatcher("download").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
