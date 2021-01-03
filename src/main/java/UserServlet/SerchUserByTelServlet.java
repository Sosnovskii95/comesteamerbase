package UserServlet;

import DAOLayer.DAOClientService;
import DAOLayer.DAODiscountCardService;
import ServiceLayer.ClientService;
import ServiceLayer.DiscountCardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "SerchUserByTelServlet", urlPatterns = "/userview/searchtelephone")
public class SerchUserByTelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String telephone = request.getParameter("tel");
        String userName = request.getSession().getAttribute("userName").toString();
        if (userName != null) {
            if (!telephone.equals("")) {
                DAOClientService clientService = new ClientService();
                DAODiscountCardService cardService = new DiscountCardService();

                long tel = Long.parseLong(telephone);
                int id_client = clientService.getClientIdByNumberTelephone(tel);
                int number_card = cardService.getNumberCardByIdClient(id_client);

                if (number_card != 0) {
                    HttpSession session = request.getSession();
                    session.setAttribute("number_card", number_card);
                    session.setAttribute("id_client", id_client);
                    session.setAttribute("id_card", cardService.getIdCardByNumberCard(number_card));
                    response.sendRedirect("addnewsum");
                } else {
                    response.sendRedirect("user.jsp");
                }
            } else {
                response.sendRedirect("user.jsp");
            }
        }else{
            response.sendRedirect("index.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
