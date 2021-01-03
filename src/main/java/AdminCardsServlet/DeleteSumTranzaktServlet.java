package AdminCardsServlet;

import CashBack.CashBack;
import DAOLayer.DAOSaleSumService;
import DAOLayer.DAOSumInCardService;
import EntityLayer.SaleSum;
import EntityLayer.SumInCard;
import ServiceLayer.SaleSumService;
import ServiceLayer.SumInCardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "DeleteSumTranzaktServlet", urlPatterns = "/adminview/deletesumtranzakt")
public class DeleteSumTranzaktServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dateFrom = request.getParameter("dateF");
        String dateTo = request.getParameter("dateT");
        int id_sale = Integer.parseInt(request.getParameter("id_sale"));

        HttpSession session = request.getSession();//Данные из сессии

        CashBack cashBack = new CashBack();

        DAOSaleSumService saleSumService = new SaleSumService();
        SaleSum saleSum = saleSumService.getSumIdSale(id_sale);

        DAOSumInCardService sumInCardService = new SumInCardService();
        SumInCard sumInCard = sumInCardService.getSumInCardByIdCard(saleSum.getId_card());

        if (saleSum.getSposob_oplat().equals("nal")){
            sumInCard.setAll_sum(sumInCard.getAll_sum()-saleSum.getBuy_sum());
            sumInCard.setCashbek_sum(sumInCard.getCashbek_sum()-saleSum.getCashbek_sum());
            sumInCard.setProcent_discont(cashBack.getProcent(sumInCard.getAll_sum()));
        }else
            if (saleSum.getSposob_oplat().equals("beznal")){
            sumInCard.setAll_sum(sumInCard.getAll_sum()-saleSum.getBuy_sum());
            sumInCard.setProcent_discont(cashBack.getProcent(sumInCard.getAll_sum()));
        }
        sumInCardService.updateSumInCard(sumInCard);
        saleSumService.deleteSaleSum(saleSum.getId_sale());

        session.setAttribute("dateFrom", dateFrom);
        session.setAttribute("dateTo", dateTo);
        request.getRequestDispatcher("tranzakt").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
