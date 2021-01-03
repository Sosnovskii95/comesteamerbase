package AdminCardsServlet;

import DAOLayer.DAOClientUsersDiscountCardService;
import DAOLayer.DAOSumInCardService;
import EntityLayer.ClientUsersDiscountCard;
import ServiceLayer.ClientUsersDiscountCardService;
import ServiceLayer.SumInCardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ListCardsServlet", urlPatterns = "/adminview/listcard")
public class ListCardsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAOClientUsersDiscountCardService client = new ClientUsersDiscountCardService();
        //Получаем выбранное значение отбора поиска
        int size = Integer.parseInt(request.getParameter("size"));
        List<ClientUsersDiscountCard> discountCards;
        //Делаем преобразование для постраничного вывода
        if(size == 1) {
            discountCards = client.getListAllCard(size - 1, size * 100);
        }
        else {
            discountCards = client.getListAllCard((size - 1)*100, size * 100);
        }
        HttpSession session = request.getSession();//Информация о сессии

        DAOSumInCardService cardService = new SumInCardService();
        int count = cardService.getCount();//Количество сумм в БД

        //Новые значения для постраничного вывода
        List<Integer> list = new ArrayList<>();
        double result = count/100.0;
        int n = (int)Math.ceil(result);
        for (int i = 1; i < n+1; i++){
            list.add(i);
        }
        //Передача на страницу
        request.setAttribute("listsize", list);
        request.setAttribute("list", discountCards);
        session.setAttribute("listcard", "old");
        //Переадрессация браузера на страницу
        request.getRequestDispatcher("cards/allcards.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
