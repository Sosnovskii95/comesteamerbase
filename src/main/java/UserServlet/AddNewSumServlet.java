package UserServlet;

import DAOLayer.*;
import Date.WorkDate;
import EntityLayer.*;
import ServiceLayer.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AddNewSumServlet", urlPatterns = "/userview/addnewsum")
public class AddNewSumServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DAODiscountCardService cardService = new DiscountCardService();
        DAOClientService clientService = new ClientService();
        DAOUserService usersService = new UserService();
        DAOSumInCardService sumInCardService = new SumInCardService();
        DAOSaleSumService saleSumService = new SaleSumService();
        DAOMagazine magazine = new MagazineService();
        WorkDate workDate = new WorkDate();

        HttpSession session = request.getSession();
        int number_card =  Integer.parseInt(session.getAttribute("number_card").toString());

        DiscountCard card = cardService.getCardByNumberCard(number_card);
        Client client = clientService.getClientByIdClient(card.getClient_id());
        Users users = usersService.getUserByIdUser(card.getUser_id());
        String name_m = magazine.getNameMagazineByIdMagazine(users.getMagazine());
        SumInCard sumInCard = sumInCardService.getSumInCardByIdCard(card.getId_card());
        SaleSum saleSum = saleSumService.getDateLastBuy(card.getId_card());

        request.setAttribute("number_card", number_card);
        request.setAttribute("date_create", WorkDate.undoParseDate(card.getDate_create()));
        request.setAttribute("magazine", name_m);
        request.setAttribute("fio", client.getFio_client());
        request.setAttribute("date_born", WorkDate.undoParseDate(client.getDate_born()));
        request.setAttribute("telephone", client.getTelephone());

        users = usersService.getUserByIdUser(saleSum.getId_user());
        name_m = magazine.getNameMagazineByIdMagazine(users.getMagazine());

        if (card.getValid_card()){
            request.setAttribute("valid","Активна");
        }else request.setAttribute("valid","Не активна");

        request.setAttribute("procent", sumInCard.getProcent_discont());
        request.setAttribute("all_sum",sumInCard.getAll_sum());
        request.setAttribute("cashbek",sumInCard.getCashbek_sum());
        request.setAttribute("gift", sumInCard.getGift_sum());
        request.setAttribute("all_cashbek",sumInCard.getGift_sum()+sumInCard.getCashbek_sum());
        request.setAttribute("date_buy", WorkDate.undoParseDate(saleSum.getDate_byu()));
        request.setAttribute("magazine_last", name_m);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("addnewsumtocard.jsp");
        requestDispatcher.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
