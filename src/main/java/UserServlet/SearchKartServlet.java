package UserServlet;

import DAOLayer.DAODiscountCardService;
import ServiceLayer.DiscountCardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "SearchKertServlet", urlPatterns = "/userview/searchkart")
public class SearchKartServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String card = ""+request.getParameter("card");
        String card2 = ""+request.getAttribute("card");
            if (!card.equals("")|| !card2.equals("")) {
                if (!card2.equals("null")){
                    card = card2;
                }
                int number_card = Integer.parseInt(card);
                DAODiscountCardService cardService = new DiscountCardService();
                int id_client = cardService.getIdClientByNumberCard(number_card);
                int id_card = cardService.getIdCardByNumberCard(number_card);
                if (id_card != 0) {
                    HttpSession session = request.getSession();
                    session.setAttribute("number_card", number_card);
                    session.setAttribute("id_client", id_client);
                    session.setAttribute("id_card", id_card);
                    response.sendRedirect("addnewsum");
                } else {
                    response.sendRedirect("user.jsp");
                }
            } else {
                response.sendRedirect("user.jsp");
            }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
