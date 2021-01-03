package UserServlet;

import CashBack.CashBack;
import DAOLayer.DAOClientService;
import DAOLayer.DAODiscountCardService;
import DAOLayer.DAOSaleSumService;
import DAOLayer.DAOSumInCardService;
import Date.WorkDate;
import EntityLayer.Client;
import EntityLayer.DiscountCard;
import EntityLayer.SaleSum;
import EntityLayer.SumInCard;
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

@WebServlet(name = "SumByCashBekServlet", urlPatterns = "/userview/sumcashbak")
public class SumByCashBekServlet extends HttpServlet {
    private final CashBack cashBack = new CashBack();//Кешбек

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();//Объект сессия
        if (session.getAttribute("id_card")!=null && session.getAttribute("id_client")!=null) {
            SaleSum saleSum = new SaleSum();//Модель таблицы SaleSum
            DAOSaleSumService saleSumService = new SaleSumService();//Управление таблицей SaleSum
            SumInCard sumInCard;//Модель таблицы SumInCard
            DAOSumInCardService sumInCardService = new SumInCardService();//Управление таблицей SumInCard
            //Текущая ДК
            int id_card = Integer.parseInt(session.getAttribute("id_card").toString());
            String sposob_oplat = request.getParameter("sposob");
            String procent10 =(request.getParameter("procent_10") == null) ? "" : request.getParameter("procent_10");
            //Получение всех сумм по данной карте
            sumInCard = sumInCardService.getSumInCardByIdCard(id_card);
            //Получение суммы оплаты за бонусы кешбек+доп бонусы
            float allcashbek = Float.parseFloat(request.getParameter("cashbek"));
            //Установка значений "транзакции" для таблицы SaleSum
            saleSum.setId_card(id_card);
            saleSum.setId_user(Integer.parseInt(session.getAttribute("id_user").toString()));
            saleSum.setBuy_sum(Float.parseFloat(request.getParameter("nal")));
            //Данная часть кода предназначена для разбияения оплаты за накопления
            //Сначало будет списан доп бонус, затем кешбек
            if (sumInCard.getGift_sum()!=0){
                if (allcashbek<=sumInCard.getGift_sum()){
                    sumInCard.setGift_sum(sumInCard.getGift_sum()-allcashbek);
                    saleSum.setGift_sum(allcashbek);
                    saleSum.setCashbek_sum(0);
                }else{
                    saleSum.setCashbek_sum(allcashbek-sumInCard.getGift_sum());
                    saleSum.setGift_sum(sumInCard.getGift_sum());
                    sumInCard.setGift_sum(0);
                }
            }else{
                saleSum.setCashbek_sum(allcashbek);
            }
            saleSum.setDate_byu(WorkDate.getNowDateToDate());
            saleSum.setTime_buy(WorkDate.getNowTime());
            saleSum.setComment(request.getParameter("comment"));
            //Получение значения доплаты наличными
            float sum = Float.parseFloat(request.getParameter("nal"));
            //Подсчет всей суммы
            float all_sum = sumInCard.getAll_sum()+sum;

            sumInCard.setAll_sum(all_sum);
            sumInCard.setId_card(id_card);

            if (sposob_oplat.equals("nal")){
                if (procent10.equals("10") && 10<sumInCard.getProcent_discont()){
                    sumInCard.setCashbek_sum(sumInCard.getCashbek_sum()-saleSum.getCashbek_sum()
                            +cashBack.getCashBackSum(saleSum.getBuy_sum(), 10));
                }else
                {
                    sumInCard.setCashbek_sum(sumInCard.getCashbek_sum()-saleSum.getCashbek_sum()
                            +cashBack.getCashBackSum(saleSum.getBuy_sum(),sumInCard.getProcent_discont()));
                }
                saleSum.setSposob_oplat("nal");
            }else if (sposob_oplat.equals("beznal")){
                sumInCard.setAll_sum(all_sum);
                sumInCard.setCashbek_sum(sumInCard.getCashbek_sum()-allcashbek);
                sumInCard.setProcent_discont(cashBack.getProcent(sumInCard.getAll_sum()));
                saleSum.setSposob_oplat("beznal");
            }
            int id_sale = saleSumService.addNewSaleSum(saleSum);//Добавление "транзакции" в бд

            if (sumInCard.getProcent_discont()<=10) {
                sumInCard.setProcent_discont(cashBack.getProcent(all_sum));
            }
            DAODiscountCardService discountCardService = new DiscountCardService();
            DiscountCard discountCard = discountCardService.getCardByIdCard(id_card);
            DAOClientService clientService = new ClientService();
            Client client = clientService.getClientByIdClient(discountCard.getClient_id());
            //Обновление карты в БД
            sumInCardService.updateSumInCard(sumInCard);

            request.setAttribute("client_fio", client.getFio_client());
            request.setAttribute("new_sum", saleSum.getBuy_sum());
            request.setAttribute("sum_cashbek", saleSum.getCashbek_sum());
            request.setAttribute("sum_gift", saleSum.getGift_sum());
            request.setAttribute("procent", sumInCard.getProcent_discont());
            request.setAttribute("allsum", sumInCard.getAll_sum());
            request.setAttribute("cashbek", sumInCard.getCashbek_sum());
            request.setAttribute("gift", sumInCard.getGift_sum());
            request.setAttribute("allcashbek", sumInCard.getCashbek_sum()+sumInCard.getGift_sum());
            session.removeAttribute("id_client");
            session.removeAttribute("id_card");
            request.setAttribute("id_sale", id_sale);
            request.getRequestDispatcher("donesum.jsp").forward(request,response);
        }else{
            response.sendRedirect("user.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
