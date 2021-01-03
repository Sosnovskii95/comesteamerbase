package AdminEventServlet;

import DAOLayer.DAOSumInCardService;
import ServiceLayer.SumInCardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "GenerateCountDBServlet", urlPatterns = "/adminview/generatecount")
public class GenerateCountDBServlet extends HttpServlet {
    //Генерируем постраничный вывод
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAOSumInCardService sumInCardService = new SumInCardService();
        int count = sumInCardService.getCount();
        List<Integer> list = new ArrayList<>();
        double result = count/100.0;
        int n = (int)Math.ceil(result);
        for (int i = 1; i < n+1; i++){
            list.add(i);
        }

        request.setAttribute("listsize", list);
        request.getRequestDispatcher("cards/allcards.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
