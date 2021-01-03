package AdminCardsServlet;

import DAOLayer.*;
import EntityLayer.*;
import ServiceLayer.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditCardServlet", urlPatterns = "/adminview/editcard")
public class EditCardServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Из запроса получаем номер карты
        int number_card = Integer.parseInt(request.getParameter("number_card"));

        DAODiscountCardService cardService = new DiscountCardService();
        //Получаем информацию по данной карте
        DiscountCard card = cardService.getCardByNumberCard(number_card);

        DAOClientService clientService = new ClientService();
        //Получаем информацию о связанном клиенте
        Client client = clientService.getClientByIdClient(card.getClient_id());

        DAOSumInCardService sumInCardService = new SumInCardService();
        //Получаем информацию о накопленной сумме со связанной картой
        SumInCard inCard = sumInCardService.getSumInCardByIdCard(card.getId_card());

        DAOUserService usersService = new UserService();
        //Получаем информацию о связанном пользователе
        Users users = usersService.getUserByIdUser(card.getUser_id());

        DAOMagazine magazineService = new MagazineService();
        String magazine = magazineService.getNameMagazineByIdMagazine(users.getMagazine());

        //Передаем на страницу собранную информацию
        request.setAttribute("card", card);
        request.setAttribute("client", client);
        request.setAttribute("sum", inCard);
        request.setAttribute("user", users);
        request.setAttribute("magazine", magazine);

        request.setAttribute("number_card", number_card);
        request.setAttribute("id_sum", inCard.getId_sum());
        request.setAttribute("id_client", card.getClient_id());
        //Переводим браузер на готовую страницу
        request.getRequestDispatcher("cards/editcard.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
