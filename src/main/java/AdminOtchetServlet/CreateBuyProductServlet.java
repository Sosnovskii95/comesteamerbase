package AdminOtchetServlet;

import DAOLayer.DAOClientUsersDiscountCardService;
import Date.WorkDate;
import ExcelCreate.ExcelCreate;
import ServiceLayer.ClientUsersDiscountCardService;
import Trans.Trans;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CreateBuyProductServlet",urlPatterns = "/adminview/createbuyproduct")
public class CreateBuyProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Параметры для отчета
        WorkDate workDate = new WorkDate();
        String magazine = request.getParameter("magazine");
        String dateFrom = WorkDate.parseDate(request.getParameter("dateFrom"));
        String dateBy = WorkDate.parseDate(request.getParameter("dateBy"));
        //Объект преобразования русского в латиницу
        Trans trans = new Trans();
        DAOClientUsersDiscountCardService service = new ClientUsersDiscountCardService();
        ExcelCreate create = new ExcelCreate();
        String pathName;
        //В зависимости от выбора вызыаются разные запросы выборки данных
        if (magazine.equals("Все")){
            pathName = create.createListCardSaleSum(service.getListCardByDateAllUser(dateFrom, dateBy), trans.translit(magazine));
        }else{
            pathName = create.createListCardSaleSum(service.getListCardByDateAndOneUser(dateFrom, dateBy, magazine),trans.translit(magazine));
        }
        //Передаем путь к файлу
        request.setAttribute("pathName", pathName);
        //Вызываем загрузчик
        request.getRequestDispatcher("download").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
