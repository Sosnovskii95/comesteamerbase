package AdminCardsServlet;

import CashBack.CashBack;
import DAOLayer.DAOClientService;
import DAOLayer.DAODiscountCardService;
import DAOLayer.DAOSaleSumService;
import DAOLayer.DAOSumInCardService;
import Date.WorkDate;
import Email.SendEmail;
import EntityLayer.Client;
import EntityLayer.DiscountCard;
import EntityLayer.SaleSum;
import EntityLayer.SumInCard;
import ServiceLayer.ClientService;
import ServiceLayer.DiscountCardService;
import ServiceLayer.SaleSumService;
import ServiceLayer.SumInCardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "NewCardServlet", urlPatterns = "/adminview/addnewcard")
public class NewCardServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();//Объект сессия для получения id_user для добавление в БД
        CashBack cashBack = new CashBack();//Объект вычисления кешбека

        Client client = new Client();//Объект "Клиент"
        DAOClientService clientService = new ClientService();//Обхект для управления таблицей "Client"

        DiscountCard discountCard = new DiscountCard();//Объект "Дисконтная карта"
        DAODiscountCardService cardService = new DiscountCardService();//Обхект для управления таблицей "Dicount card"

        SumInCard sumInCard = new SumInCard();//Объект "Сумма"
        DAOSumInCardService sumInCardService = new SumInCardService();//Объект для управления таблицей "Sum in card"

        SaleSum saleSum = new SaleSum();
        DAOSaleSumService saleSumService = new SaleSumService();

        int id_user = Integer.parseInt(session.getAttribute("id_user").toString());//Значение текущего пользователя


        //Заполнение данных клиента
        client.setFio_client(request.getParameter("familia")+" "+request.getParameter("imia")+" "+request.getParameter("otchestvo"));
        client.setDate_born(Date.valueOf(WorkDate.parseDate(request.getParameter("date_born"))));
        client.setTelephone(Long.parseLong(request.getParameter("telephone")));
        client.setSms(Boolean.parseBoolean(request.getParameter("sms")));
        //client.setId_client(id_user);
        client.setStatus_client(request.getParameter("client"));
        //Добавление нового клиента в БД


        //Заполнение данных новой дисконтной карты
        discountCard.setUser_id(id_user);
        discountCard.setClient_id(clientService.addNewClient(client));
        discountCard.setNumber_card(Integer.parseInt(request.getParameter("number_card")));
        discountCard.setValid_card(false);
        discountCard.setStatus_card(request.getParameter("status_card"));
        if (request.getParameter("client").equals("new")){
            sumInCard.setAll_sum(Float.parseFloat(request.getParameter("first_sum")));
            sumInCard.setProcent_discont(cashBack.getProcent(sumInCard.getAll_sum()));
            //client.setStatus_client("new");
        }else {
            //client.setStatus_client("old");
            sumInCard.setAll_sum(Float.parseFloat(request.getParameter("sum")));
//            sumInCard.setProcent_discont(Float.parseFloat(request.getParameter("procent")));
            sumInCard.setProcent_discont(cashBack.getProcent(sumInCard.getAll_sum()));
        }
        discountCard.setOld_card("");
        discountCard.setDate_create(Date.valueOf(WorkDate.parseDate(session.getAttribute("date_now").toString())));
        //Добавление новой дисконтной карты в БД
        int id_card = cardService.addNewCard(discountCard);
        sumInCard.setId_card(id_card);
        sumInCardService.addNewSumInCard(sumInCard);
        saleSum.setId_card(id_card);
        saleSum.setId_user(id_user);
        saleSum.setBuy_sum(sumInCard.getAll_sum());
        saleSum.setCashbek_sum(0);
        saleSum.setTime_buy(WorkDate.getNowTime());
        saleSum.setDate_byu(Date.valueOf(WorkDate.parseDate(session.getAttribute("date_now").toString())));
        saleSumService.addNewSaleSum(saleSum);
        //Отправка email
        SendEmail email = new SendEmail();
        email.send(session.getAttribute("magazine").toString(), client, discountCard);

        response.sendRedirect("cards.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
