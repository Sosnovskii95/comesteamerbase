package AdminCardsServlet;

import DAOLayer.DAOClientUsersDiscountCardService;
import DAOLayer.DAODiscountCardService;
import Date.WorkDate;
import EntityLayer.ClientUsersDiscountCard;
import ServiceLayer.ClientUsersDiscountCardService;
import ServiceLayer.DiscountCardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "TranzaktServlet", urlPatterns = "/adminview/tranzakt")
public class TranzaktServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Получаем из запроса номер карты, диапазон дат, тип списка
        int number_card = Integer.parseInt(request.getParameter("number_card"));
        String dateFrom = "" + request.getParameter("dateFrom");
        String dateTo = "" + request.getParameter("dateTo");
        String listcard = request.getParameter("listcard");

        HttpSession session = request.getSession();//Значения текущий сесии
        //Диапазон дат из сессии
        String dateFromSession = "null";
        String dateToSession = "null";
        if (session.getAttribute("dateFrom") != null && session.getAttribute("dateTo") != null){
            //Вносим диапазон дат в переменные
            dateFromSession = ""+session.getAttribute("dateFrom").toString();
            dateToSession = ""+session.getAttribute("dateTo").toString();
        }

        DAODiscountCardService cardService = new DiscountCardService();
        //Получаем id_card из физического номера карты
        int id_card = cardService.getIdCardByNumberCard(number_card);
        //Получаем сумму на карте
        //DAOSumInCardService sumInCardService = new SumInCardService();

        DAOClientUsersDiscountCardService clientUsersDiscountCardService = new ClientUsersDiscountCardService();

        List<ClientUsersDiscountCard> historyMoney;
        List<ClientUsersDiscountCard> historyCashbek;

        if (!dateFrom.equals("null") && !dateTo.equals("null")) {
            dateFrom = WorkDate.parseDate(dateFrom);
            dateTo = WorkDate.parseDate(dateTo);
            historyMoney = clientUsersDiscountCardService.getHistoryMoneyIdCard(id_card, dateFrom, dateTo);
            historyCashbek = clientUsersDiscountCardService.getHistoryCashbekIdCard(id_card, dateFrom, dateTo);
        }else
            if ((!dateFromSession.equals("null") && !dateToSession.equals("null"))){
                dateFromSession = WorkDate.parseDate(dateFromSession);
                dateToSession = WorkDate.parseDate(dateToSession);
                historyMoney = clientUsersDiscountCardService.getHistoryMoneyIdCard(id_card, dateFromSession, dateToSession);
                historyCashbek = clientUsersDiscountCardService.getHistoryCashbekIdCard(id_card, dateFromSession, dateToSession);

                session.removeAttribute("dateFrom");
                session.removeAttribute("dateTo");

                dateFrom = dateFromSession;
                dateTo = dateToSession;
        }else {

            dateFrom = WorkDate.parseDate(WorkDate.getNowDateToString());
            dateTo = WorkDate.parseDate(WorkDate.getNowDateToString());

            historyMoney = clientUsersDiscountCardService.getHistoryMoneyIdCard(id_card, dateFrom, dateTo);
            historyCashbek = clientUsersDiscountCardService.getHistoryCashbekIdCard(id_card, dateFrom, dateTo);
        }

        request.setAttribute("dateFrom", dateFrom);
        request.setAttribute("dateTo", dateTo);
        request.setAttribute("number_card", number_card);
        request.setAttribute("historyMoney", historyMoney);
        request.setAttribute("historyCashbek", historyCashbek);
        request.setAttribute("listcard", listcard);
        request.getRequestDispatcher("cards/trahzaktsum.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
