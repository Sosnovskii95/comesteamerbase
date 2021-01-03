package AdminCardsServlet;

import DAOLayer.DAOClientUsersDiscountCardService;
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

@WebServlet(name = "ListNewCardServlet", urlPatterns = "/adminview/listnewcard")
public class ListNewCardServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAOClientUsersDiscountCardService service = new ClientUsersDiscountCardService();
        List<ClientUsersDiscountCard> discountCards = service.getListNewCard();
        HttpSession session = request.getSession();

        request.setAttribute("list", discountCards);
        session.setAttribute("listcard", "new");
        request.getRequestDispatcher("cards/allcards.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
