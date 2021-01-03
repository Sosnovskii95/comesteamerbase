package AdminCardsServlet;

import CashBack.CashBack;
import DAOLayer.DAOClientService;
import DAOLayer.DAODiscountCardService;
import DAOLayer.DAOSumInCardService;
import Date.WorkDate;
import EntityLayer.Client;
import EntityLayer.DiscountCard;
import EntityLayer.SumInCard;
import ServiceLayer.ClientService;
import ServiceLayer.DiscountCardService;
import ServiceLayer.SumInCardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "UpdateCardServlet",urlPatterns = "/adminview/updatecard")
public class UpdateCardServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Из запроса получаем ID карты
        int id_card = Integer.parseInt(request.getParameter("id_card"));
        CashBack cashBack = new CashBack();
        //Получаем информацию по данной карте
        DAODiscountCardService cardService = new DiscountCardService();
        DiscountCard card = cardService.getCardByIdCard(id_card);
        //Проверяем на ввод новых номеров карты
        //Если введен новый номер карты, будет произведена его замена
        if (!request.getParameter("new_number").equals("")){
            card.setOld_card(card.getOld_card()+card.getNumber_card() +"/");
            card.setNumber_card(Integer.parseInt(request.getParameter("new_number")));
        }
        card.setValid_card(Boolean.parseBoolean(request.getParameter("valid_card")));
        //Обновляем значения на карте
        cardService.updateCard(card);


        int id_sum = Integer.parseInt(request.getParameter("id_sum"));
        //Получаем суммы по данной карте
        DAOSumInCardService sumInCardService = new SumInCardService();
        SumInCard sumInCard = sumInCardService.getSumInCardByIdCard(id_sum);
        //Заполняем новыми значениями
        sumInCard.setId_sum(id_sum);
        sumInCard.setId_card(id_card);
        sumInCard.setAll_sum(Float.parseFloat(request.getParameter("all_sum")));
        sumInCard.setProcent_discont(cashBack.getProcent(sumInCard.getAll_sum()));
        sumInCard.setCashbek_sum(Float.parseFloat(request.getParameter("cashbek")));
        sumInCard.setGift_sum(Float.parseFloat(request.getParameter("gift_sum")));
        sumInCardService.updateSumInCard(sumInCard);

        //Получаем данные о клиенте
        DAOClientService clientService = new ClientService();
        Client client = clientService.getClientByIdClient(card.getClient_id());
        //Вносим новые значения
        client.setFio_client(request.getParameter("fio_client"));
        client.setDate_born(Date.valueOf(WorkDate.parseDate(request.getParameter("date_born"))));
        client.setTelephone(Long.parseLong(request.getParameter("telephone")));
        client.setSms(Boolean.parseBoolean(request.getParameter("sms")));
        client.setStatus_client(request.getParameter("status"));
        clientService.updateClient(client);

        HttpSession session = request.getSession();
        //В зависимости от значения списка откуда пришли туда и переводим
        if (session.getAttribute("listcard").toString().equals("old")){
            response.sendRedirect("generatecount");
            session.removeAttribute("listcard");
        }else{
            response.sendRedirect("listnewcard");
            session.removeAttribute("listcard");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
