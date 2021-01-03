package UserServlet;

import DAOLayer.DAOClientUsersDiscountCardService;
import Date.WorkDate;
import EntityLayer.ClientUsersDiscountCard;
import ServiceLayer.ClientUsersDiscountCardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HistoryServlet", urlPatterns = "/userview/history")
public class HistoryServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dateFrom = WorkDate.parseDate(request.getParameter("dateFrom"));
        String dateTo = WorkDate.parseDate(request.getParameter("dateTo"));
        HttpSession session = request.getSession();
        int id_user = (int) session.getAttribute("id_user");

        DAOClientUsersDiscountCardService service = new ClientUsersDiscountCardService();

        List<ClientUsersDiscountCard> historyMoney = service.getHistoryMoney(id_user, dateFrom, dateTo);
        List<ClientUsersDiscountCard> historyCashbek = service.getHistotyCashbek(id_user, dateFrom, dateTo);

        request.setAttribute("dateF", WorkDate.undoParseDate(dateFrom));
        request.setAttribute("dateT", WorkDate.undoParseDate(dateTo));

        request.setAttribute("hMoney", historyMoney);
        request.setAttribute("moneySize", historyMoney.size());
        request.setAttribute("hCashbek", historyCashbek);
        request.setAttribute("cashbekSize", historyCashbek.size());
        request.getRequestDispatcher("history.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
