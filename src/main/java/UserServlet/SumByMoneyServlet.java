package UserServlet;

import CashBack.CashBack;
import DAOLayer.DAOClientService;
import DAOLayer.DAOSaleSumService;
import DAOLayer.DAOSumInCardService;
import Date.WorkDate;
import EntityLayer.Client;
import EntityLayer.SaleSum;
import EntityLayer.SumInCard;
import ServiceLayer.ClientService;
import ServiceLayer.SaleSumService;
import ServiceLayer.SumInCardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "SumByMoneyServlet", urlPatterns = "/userview/summoney")
public class SumByMoneyServlet extends HttpServlet {
    private final CashBack cashBack = new CashBack();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();//Объект сессия

        if (session.getAttribute("id_card")!=null && session.getAttribute("id_client")!=null){
            int id_card = Integer.parseInt(session.getAttribute("id_card").toString());//Текущий id_card
            int id_user = Integer.parseInt(session.getAttribute("id_user").toString());//Текущий пользователь
            int id_client = Integer.parseInt(session.getAttribute("id_client").toString());//Текущий клиент
            String sposob_oplat = request.getParameter("sposobzadengi");
            String procent10 =(request.getParameter("procent_10") == null) ? "" : request.getParameter("procent_10");

            DAOClientService clientService = new ClientService();//Управление таблиецей Client
            Client client = clientService.getClientByIdClient(id_client);//Модель таблицы Client

            DAOSumInCardService sumInCardService = new SumInCardService();//Управление таблицей SumInCard
            SumInCard sumInCard = sumInCardService.getSumInCardByIdCard(id_card);//Модель таблицы SumInCard

            SaleSum saleSum = new SaleSum();//Модель таблицы SaleSum
            DAOSaleSumService saleSumService = new SaleSumService();//Управление таблицей SaleSum
            //Сумма текущей покупки
            float new_sum = Float.parseFloat(request.getParameter("new_sum"));
            //Установка параметров новой записи для таблицы SaleSum
            saleSum.setId_card(id_card);
            saleSum.setId_user(id_user);
            saleSum.setBuy_sum(new_sum);
            saleSum.setGift_sum(0);
            saleSum.setDate_byu(WorkDate.getNowDateToDate());
            saleSum.setTime_buy(WorkDate.getNowTime());
            //Общая сумма по карте. Значение из БД + новая сумма
            float all_sum = sumInCard.getAll_sum()+new_sum;
            float new_cashbek = 0;
            if (sposob_oplat.equals("nal")){
                if (procent10.equals("10") && 10<sumInCard.getProcent_discont()){
                    new_cashbek = cashBack.getCashBackSum(new_sum, 10);
                }else{
                    new_cashbek = cashBack.getCashBackSum(new_sum, sumInCard.getProcent_discont());
                }
                saleSum.setSposob_oplat("nal");
            }else {
                saleSum.setSposob_oplat("beznal");
            }
            saleSum.setCashbek_sum(new_cashbek);

            //Установка нового значения всей суммы
            sumInCard.setAll_sum(all_sum);
            sumInCard.setId_card(id_card);
            request.setAttribute("sum_cashbek", new_cashbek);
            request.setAttribute("sum_gift", sumInCard.getGift_sum());
            //Установка нового значения Кешбека. Рассчет кешбека по новой сумме
            sumInCard.setCashbek_sum(sumInCard.getCashbek_sum()+new_cashbek);
            //Некоторые карты могут иметь больше 10% скидки. Это условие для того чтобы не сбивался процент
//            if (sumInCard.getProcent_discont()<=10) {
//                //Расчет новоого процента от всей суммы. Новая+БД
//                sumInCard.setProcent_discont(cashBack.getProcent(all_sum));
//            }

//            SimpleDateFormat format = new SimpleDateFormat("dd.MM");
//            Date date = new Date();
//            try {
//                //Часть кода предназначенная для работы с днями рожденями.
//                date = format.parse(client.getDate_born());
//                if (0<=date.compareTo(format.parse(calendars.getYesterdayDate())) && date.compareTo(format.parse(calendars.getTommorowDate()))<=0){
//
//                }else {
//                    sumInCard.setProcent_discont(cashBack.getProcent(all_sum));
//                }
//            }catch (ParseException e){
//                e.printStackTrace();
//            }

            sumInCard.setProcent_discont(cashBack.getProcent(all_sum));
            //Условие на случай если еще нет записи по этой карте в SumInCard
            if (sumInCardService.existIdCard(sumInCard.getId_card())){
                //Обновление суммы по карте
                sumInCardService.updateSumInCard(sumInCard);
            }else {
                //Добавление новой суммы
                sumInCardService.addNewSumInCard(sumInCard);
            }
            //Добавление "транзакции" по данной карте
            int id_sale = saleSumService.addNewSaleSum(saleSum);
            //отправка статус проведенной операции
            request.setAttribute("client_fio", client.getFio_client());
            request.setAttribute("new_sum", new_sum);
            request.setAttribute("procent", sumInCard.getProcent_discont());
            request.setAttribute("allsum", all_sum);
            request.setAttribute("cashbek", sumInCard.getCashbek_sum());
            request.setAttribute("gift", sumInCard.getGift_sum());
            request.setAttribute("allcashbek", sumInCard.getGift_sum()+sumInCard.getCashbek_sum());
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
