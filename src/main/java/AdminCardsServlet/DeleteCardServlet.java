package AdminCardsServlet;

import DAOLayer.DAOClientService;
import DAOLayer.DAODiscountCardService;
import DAOLayer.DAOSaleSumService;
import DAOLayer.DAOSumInCardService;
import EntityLayer.DiscountCard;
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

@WebServlet(name = "DeleteCardServlet", urlPatterns = "/adminview/deletecard")
public class DeleteCardServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Получаем из запроса физический номер карты
        int number_card = Integer.parseInt(request.getParameter("number_card"));

        DAODiscountCardService cardService = new DiscountCardService();
        //Получаем информацию о карте по ее номеру
        DiscountCard card = cardService.getCardByNumberCard(number_card);

        DAOSaleSumService saleSumService = new SaleSumService();
        //Удаляем все "транзакции" связанные с данной картой
        saleSumService.deleteListSaleSum(saleSumService.getSumByIdCard(card.getId_card()));

        DAOSumInCardService sumInCardService = new SumInCardService();
        //Удаляем сумму накоплений связанную с данной картой
        sumInCardService.deleteSumInCard(sumInCardService.getSumInCardByIdCard(card.getId_card()));

        //Удаляем саму карту из БД
        cardService.deleteCard(card);

        DAOClientService clientService = new ClientService();
        //Удаляем клиента связанного с данной картой
        clientService.deleteClient(clientService.getClientByIdClient(card.getClient_id()));

        //Переводим на список новых карт браузер
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
