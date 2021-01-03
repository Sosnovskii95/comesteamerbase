package Tasks;

import DAOLayer.DAOClientService;
import DAOLayer.DAODiscountCardService;
import DAOLayer.DAOSumInCardService;
import Date.WorkDate;
import EntityLayer.Client;
import EntityLayer.DiscountCard;
import EntityLayer.SumInCard;
import ServiceLayer.ClientService;
import ServiceLayer.DiscountCardService;
import ServiceLayer.SumInCardService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateBornJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        float gift_sum;


        String tommorow = WorkDate.getTommorowDate();
        String yesterday = WorkDate.getYesterdayDate();
        DAOClientService clientService = new ClientService();
        List<Client> clients = clientService.getClientDateBorn(WorkDate.parseDate(yesterday), WorkDate.parseDate(tommorow));

        SimpleDateFormat format = new SimpleDateFormat("dd.MM");

        List<String> createGift = new ArrayList<>();
        createGift.add("Номер карты   ФИО клиента   Сумма бонуса  Процент на карте");
        List<String> deleteGift = new ArrayList<>();
        deleteGift.add("Номер карты   ФИО клиента   Сумма бонуса  Процент на карте");

        for (Client client: clients
                ) {
            Date date = new Date();
            try {
                date = format.parse(WorkDate.undoParseDate(client.getDate_born()));
                if (date.compareTo(format.parse(tommorow))==0){
                    gift_sum = 10;
                    updateSum(client, gift_sum, createGift, deleteGift);
                }
                if (date.compareTo(format.parse(yesterday))==0){
                    gift_sum = 0;
                    updateSum(client, gift_sum, createGift, deleteGift);
                }
            }catch (ParseException e){
                e.printStackTrace();
            }
        }
        createOtchet(createGift, deleteGift);
    }

    private void updateSum(Client client, float gift_sum, List<String> createGift, List<String>deleteGift){
        DAODiscountCardService cardService = new DiscountCardService();
        int id_card = cardService.getIdCardByIdClient(client.getId_client());
        DiscountCard discountCard = cardService.getCardByIdCard(id_card);

        DAOSumInCardService inCardService = new SumInCardService();
        SumInCard inCard = inCardService.getSumInCardByIdCard(id_card);

        inCard.setGift_sum(gift_sum);
        inCardService.updateSumInCard(inCard);

        if (gift_sum == 10)
        {
            createGift.add(discountCard.getNumber_card()+" "+ client.getFio_client()+" "+
                    gift_sum+" "+inCard.getProcent_discont());
        }
        if (gift_sum == 0)
        {
            deleteGift.add(discountCard.getNumber_card()+" "+client.getFio_client()+" "+
                    gift_sum+" "+inCard.getProcent_discont());
        }
    }

    private void createOtchet(List<String> create, List<String> delete) {
        String pathName = "D:/Отчеты/Дни рождения/"+ WorkDate.getNowDateToString()+".txt";
        final String LINE_SEPARATOR = System.getProperty("line.separator");
        FileOutputStream outputStream = null;
        try {
            if (create != null || delete != null){
                File file = new File(pathName);
                file.getParentFile().mkdirs();
                outputStream = new FileOutputStream(pathName);
                if (create != null){
                    outputStream.write("Добавленные бонусы!".getBytes());
                    outputStream.write(LINE_SEPARATOR.getBytes());
                    for (String line : create
                            ) {
                        outputStream.write(line.getBytes());
                        outputStream.write(LINE_SEPARATOR.getBytes());
                    }
                }
                if (delete != null){
                    outputStream.write(LINE_SEPARATOR.getBytes());
                    outputStream.write("Удаленные бонусы!".getBytes());
                    outputStream.write(LINE_SEPARATOR.getBytes());
                    for (String line : delete) {
                        outputStream.write(line.getBytes());
                        outputStream.write(LINE_SEPARATOR.getBytes());
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException ignore) {}
            }
        }
    }
}
