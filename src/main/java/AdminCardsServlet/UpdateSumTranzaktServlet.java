package AdminCardsServlet;

import CashBack.CashBack;
import DAOLayer.DAODiscountCardService;
import DAOLayer.DAOSaleSumService;
import DAOLayer.DAOSumInCardService;
import EntityLayer.DiscountCard;
import EntityLayer.SaleSum;
import EntityLayer.SumInCard;
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

@WebServlet(name = "UpdateSumTranzaktServlet", urlPatterns = "/adminview/updatesumtranzakt")
public class UpdateSumTranzaktServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Получаем данные из запроса
        int id_sale = Integer.parseInt(request.getParameter("id_sale"));
        int number_card = Integer.parseInt(request.getParameter("number_card"));
        float new_sum = Float.parseFloat(request.getParameter("new_sum"));
        String sposob_oplat = request.getParameter("sposob_oplat");
        String dateFrom = request.getParameter("dateF");
        String dateTo = request.getParameter("dateT");

        HttpSession session = request.getSession();//Данные из сессии
        CashBack cashBack = new CashBack();//Объект для работы с кешбеком
        //Получаем данные карты
        DAODiscountCardService cardService = new DiscountCardService();
        DiscountCard card = cardService.getCardByNumberCard(number_card);
        //Получаем данные о сумме накоплений
        DAOSumInCardService sumInCardService = new SumInCardService();
        SumInCard sumInCard = sumInCardService.getSumInCardByIdCard(card.getId_card());
        //Получаем список "транзакций"
        DAOSaleSumService saleSumService = new SaleSumService();
        SaleSum saleSum = saleSumService.getSumIdSale(id_sale);
        //Обновляем данные суммы накоплений
        sumInCard.setAll_sum(sumInCard.getAll_sum()-saleSum.getBuy_sum());
        sumInCard.setCashbek_sum(sumInCard.getCashbek_sum()-saleSum.getCashbek_sum());
        sumInCard.setProcent_discont(cashBack.getProcent(sumInCard.getAll_sum()));
        sumInCard.setAll_sum(sumInCard.getAll_sum()+new_sum);
        //Производим изменений какой-то конкретной "транзакции" согласно условиям
        if (sposob_oplat.equals("nal")){
            sumInCard.setCashbek_sum(sumInCard.getCashbek_sum()+cashBack.getCashBackSum(new_sum, sumInCard.getProcent_discont()));
            saleSum.setBuy_sum(new_sum);
            saleSum.setCashbek_sum(cashBack.getCashBackSum(new_sum, sumInCard.getProcent_discont()));
            saleSum.setSposob_oplat("nal");
        }else if (sposob_oplat.equals("beznal")){
            sumInCard.setCashbek_sum(sumInCard.getCashbek_sum()+0);
            saleSum.setBuy_sum(new_sum);
            saleSum.setCashbek_sum(0);
            saleSum.setSposob_oplat("beznal");
        }
        //Обновляем сумму на карте и транзакцию
        saleSumService.updateSaleSum(saleSum);
        sumInCardService.updateSumInCard(sumInCard);

        session.setAttribute("dateFrom", dateFrom);
        session.setAttribute("dateTo", dateTo);
        request.getRequestDispatcher("tranzakt").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
