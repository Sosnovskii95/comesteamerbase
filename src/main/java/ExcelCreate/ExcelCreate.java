package ExcelCreate;


import Date.WorkDate;
import EntityLayer.ClientUsersDiscountCard;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelCreate {

    private void createNewFile(String pathName, HSSFWorkbook workbook) {
        File file;
        FileOutputStream outFile = null;
        try {
            file = new File(pathName);
            file.getParentFile().mkdirs();

            outFile = new FileOutputStream(file);
            workbook.write(outFile);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

    private static CellStyle getStyleFloat(HSSFWorkbook workbook) {
        CellStyle decimalStyle = workbook.createCellStyle();
        decimalStyle.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat("0.00"));
        return decimalStyle;
    }

    private static CellStyle getStyleLong(HSSFWorkbook workbook) {
        CellStyle decimalStyle = workbook.createCellStyle();
        decimalStyle.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat("0"));
        return decimalStyle;
    }


    public String createListAllCard(List<ClientUsersDiscountCard> cards) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("лист 1");
        CellStyle aFloat = getStyleFloat(workbook);
        CellStyle aLong = getStyleLong(workbook);

        int rownum = 0;
        Cell cell;
        Row row;

        HSSFCellStyle style = createStyleForTitle(workbook);

        row = sheet.createRow(rownum);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("№ карты");

        //row = sheet.createRow(rownum);
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("% скидки");

        //row = sheet.createRow(rownum);
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Дата выдачи");

        //row = sheet.createRow(rownum);
        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Магазин выдачи");

        //row = sheet.createRow(rownum);
        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("ФИО Клиента");

        //row = sheet.createRow(rownum);
        cell = row.createCell(5, CellType.STRING);
        cell.setCellValue("Телефон");

        //row = sheet.createRow(rownum);
        cell = row.createCell(6, CellType.STRING);
        cell.setCellValue("Сумма покупок");

        cell = row.createCell(7, CellType.STRING);
        cell.setCellValue("Сумма кешбека");

        //row = sheet.createRow(rownum);
        cell = row.createCell(8, CellType.STRING);
        cell.setCellValue("SMS рассылка");

        for (ClientUsersDiscountCard user : cards
        ) {
            rownum++;
            row = sheet.createRow(rownum);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(user.getNumber_card());

            cell = row.createCell(1, CellType.NUMERIC);
            cell.setCellValue(user.getProcent_discont());

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(user.getDate_create());

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue(user.getMagazine());

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue(user.getFio_client());

            cell = row.createCell(5, CellType.NUMERIC);
            cell.setCellStyle(aLong);
            cell.setCellValue(user.getTelephone());

            cell = row.createCell(6, CellType.NUMERIC);
            cell.setCellStyle(aFloat);
            cell.setCellValue(user.getAll_sum());

            cell = row.createCell(7, CellType.NUMERIC);
            cell.setCellStyle(aFloat);
            cell.setCellValue(user.getCashbek_sum());

            cell = row.createCell(8, CellType.BOOLEAN);
            if (user.getSms()) {
                cell.setCellValue("Да");
            } else {
                cell.setCellValue("Нет");
            }

        }
        for (int i = 0; i < 9; i++) {
            sheet.autoSizeColumn(i);
        }
        String pathName = "D:/Отчеты/Все карты/All-Cards-" + WorkDate.getNowDateToString() + ".xls";
        createNewFile(pathName, workbook);
        return pathName;
    }

    public String createListProcent(List<ClientUsersDiscountCard> cards, float procentFrom, float procentBy) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("лист 1");


        int rownum = 0;
        Cell cell;
        Row row;

        HSSFCellStyle style = createStyleForTitle(workbook);

        row = sheet.createRow(rownum);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("№ карты");

        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("% скидки");

        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("ФИО Клиента");


        for (ClientUsersDiscountCard user : cards
        ) {
            rownum++;
            row = sheet.createRow(rownum);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(user.getNumber_card());

            cell = row.createCell(1, CellType.NUMERIC);
            cell.setCellValue(user.getProcent_discont());

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(user.getFio_client());

        }
        for (int i = 0; i < 3; i++) {
            sheet.autoSizeColumn(i);
        }
        String pathName = "D:/Отчеты/Выборка по картам/Sample-" + procentFrom + "-" + procentBy + "-" + WorkDate.getNowDateToString() + ".xls";
        createNewFile(pathName, workbook);
        return pathName;
    }

    public String createListCardDateBorn(List<ClientUsersDiscountCard> cards) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("лист 1");


        int rownum = 0;
        Cell cell;
        Row row;

        HSSFCellStyle style = createStyleForTitle(workbook);

        row = sheet.createRow(rownum);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("№ карты");

        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("% скидки");

        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("ФИО Клиента");

        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Дата рождения");


        for (ClientUsersDiscountCard card : cards
        ) {
            rownum++;
            row = sheet.createRow(rownum);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(card.getNumber_card());

            cell = row.createCell(1, CellType.NUMERIC);
            cell.setCellValue(card.getProcent_discont());

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(card.getFio_client());

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue(card.getDate_born());

        }
        for (int i = 0; i < 4; i++) {
            sheet.autoSizeColumn(i);
        }
        String pathName = "D:/Отчеты/Выборка по дням рождениям/Date-Born-Client-" + WorkDate.getNowDateToString() + ".xls";
        createNewFile(pathName, workbook);
        return pathName;
    }

    public String createListCardValid(List<ClientUsersDiscountCard> cards, boolean valid) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("лист 1");


        int rownum = 0;
        Cell cell;
        Row row;

        HSSFCellStyle style = createStyleForTitle(workbook);

        row = sheet.createRow(rownum);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("№ карты");

        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("% скидки");

        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("ФИО Клиента");

        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Статус карты");


        for (ClientUsersDiscountCard card : cards
        ) {
            rownum++;
            row = sheet.createRow(rownum);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(card.getNumber_card());

            cell = row.createCell(1, CellType.NUMERIC);
            cell.setCellValue(card.getProcent_discont());

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(card.getFio_client());

            cell = row.createCell(3, CellType.STRING);
            if (card.getValid_card()) {
                cell.setCellValue("Активна");
            } else {
                cell.setCellValue("Не активна");
            }

        }
        for (int i = 0; i < 4; i++) {
            sheet.autoSizeColumn(i);
        }
        String pathName;
        if (valid) {
            pathName = "D:/Отчеты/Статус карт/Valid-Card-" + WorkDate.getNowDateToString() + ".xls";
        } else {
            pathName = "D:/Отчеты/Статус карт/Invalid-Card-" + WorkDate.getNowDateToString() + ".xls";
        }

        createNewFile(pathName, workbook);
        return pathName;
    }

    public String createListCardStatusClient(List<ClientUsersDiscountCard> cards) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("лист 1");


        int rownum = 0;
        Cell cell;
        Row row;

        HSSFCellStyle style = createStyleForTitle(workbook);

        row = sheet.createRow(rownum);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("№ карты");

        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("% скидки");

        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("ФИО Клиента");

        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Статус клиента");


        for (ClientUsersDiscountCard card : cards
        ) {
            rownum++;
            row = sheet.createRow(rownum);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(card.getNumber_card());

            cell = row.createCell(1, CellType.NUMERIC);
            cell.setCellValue(card.getProcent_discont());

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(card.getFio_client());

            cell = row.createCell(3, CellType.STRING);
            if (card.getStstus_client().equals("old")) {
                cell.setCellValue("Старый клиент");
            } else {
                cell.setCellValue("Новый клиент");
            }

        }
        for (int i = 0; i < 4; i++) {
            sheet.autoSizeColumn(i);
        }
        String pathName = "D:/Отчеты/Статус клиентов/Status-Client-" + WorkDate.getNowDateToString() + ".xls";
        createNewFile(pathName, workbook);
        return pathName;
    }

    public String createListCardSms(List<ClientUsersDiscountCard> cards, boolean sms) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("лист 1");


        int rownum = 0;
        Cell cell;
        Row row;

        HSSFCellStyle style = createStyleForTitle(workbook);

        row = sheet.createRow(rownum);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("№ карты");

        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Телефон");

        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("SMS");


        for (ClientUsersDiscountCard card : cards
        ) {
            rownum++;
            row = sheet.createRow(rownum);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(card.getNumber_card());

            cell = row.createCell(1, CellType.NUMERIC);
            cell.setCellStyle(getStyleLong(workbook));
            cell.setCellValue(card.getTelephone());

            cell = row.createCell(2, CellType.STRING);
            if (card.getSms()) {
                cell.setCellValue("Да");
            } else {
                cell.setCellValue("Нет");
            }

        }
        for (int i = 0; i < 4; i++) {
            sheet.autoSizeColumn(i);
        }
        String pathName;
        if (sms) {
            pathName = "D:/Отчеты/Статус телефонов/Status-SMS-Valid-" + WorkDate.getNowDateToString() + ".xls";
        } else {
            pathName = "D:/Отчеты/Статус телефонов/Status-SMS-Invalid-" + WorkDate.getNowDateToString() + ".xls";
        }
        createNewFile(pathName, workbook);
        return pathName;
    }

    public String createListCardStatusCard(List<ClientUsersDiscountCard> cards, String status_card) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("лист 1");


        int rownum = 0;
        Cell cell;
        Row row;

        HSSFCellStyle style = createStyleForTitle(workbook);

        row = sheet.createRow(rownum);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("№ карты");

        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("ФИО Клиента");

        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Операция");


        for (ClientUsersDiscountCard card : cards
        ) {
            rownum++;
            row = sheet.createRow(rownum);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(card.getNumber_card());

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(card.getFio_client());

            cell = row.createCell(2, CellType.STRING);
            if (card.getStatus_card().equals("new")) {
                cell.setCellValue("Продана");
            } else {
                cell.setCellValue("Выдана");
            }

        }
        for (int i = 0; i < 4; i++) {
            sheet.autoSizeColumn(i);
        }
        String pathName;
        if (status_card.equals("old")) {
            pathName = "D:/Отчеты/Статус проднных карт/Status-Card-OLD-" + WorkDate.getNowDateToString() + ".xls";
        } else {
            pathName = "D:/Отчеты/Статус проднных карт/Status-Card-NEW" + WorkDate.getNowDateToString() + ".xls";
        }
        createNewFile(pathName, workbook);
        return pathName;
    }

    //ДАТА	Магазин	время продажи	ФИО продавца	что куплено	сумма покупки за нал	сумма использованного кэшбэка	Сумма использования бонусных баллов
    public String createListCardSaleSum(List<ClientUsersDiscountCard> cards, String magazine) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("лист 1");
        CellStyle aFloat = getStyleFloat(workbook);

        int rownum = 0;
        Cell cell;
        Row row;

        HSSFCellStyle style = createStyleForTitle(workbook);

        row = sheet.createRow(rownum);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Номер карты");

        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("ФИО Клиента");

        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Дата");

        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Время продажи");

        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("Магазин");

        cell = row.createCell(5, CellType.STRING);
        cell.setCellValue("ФИО продавца");

        cell = row.createCell(6, CellType.STRING);
        cell.setCellValue("Наименование покупки");

        cell = row.createCell(7, CellType.STRING);
        cell.setCellValue("Доплата наличными");

        cell = row.createCell(8, CellType.STRING);
        cell.setCellValue("Использованный кешбек");

        cell = row.createCell(9, CellType.STRING);
        cell.setCellValue("Использованные доп бонусы");


        for (ClientUsersDiscountCard card : cards
        ) {
            rownum++;
            row = sheet.createRow(rownum);

            cell = row.createCell(0, CellType.NUMERIC);
            cell.setCellValue(card.getNumber_card());

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(card.getFio_client());

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(card.getDate_buy());

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue(card.getTime_buy());

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue(card.getMagazine());

            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue(card.getFio_user());

            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue(card.getComment());

            cell = row.createCell(7, CellType.NUMERIC);
            cell.setCellStyle(aFloat);
            cell.setCellValue(card.getBuy_sum());

            cell = row.createCell(8, CellType.NUMERIC);
            cell.setCellStyle(aFloat);
            cell.setCellValue(card.getCashbek_sum());

            cell = row.createCell(9, CellType.NUMERIC);
            cell.setCellStyle(aFloat);
            cell.setCellValue(card.getGift_sum());

        }
        for (int i = 0; i < 10; i++) {
            sheet.autoSizeColumn(i);
        }
        String pathName = "D:/Отчеты/Отчет по кешбеку/Cahbek-Card-" + magazine + "-" + WorkDate.getNowDateToString() + ".xls";
        createNewFile(pathName, workbook);
        return pathName;
    }

    public String createListCardOldCard(List<ClientUsersDiscountCard> cards) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("лист 1");


        int rownum = 0;
        Cell cell;
        Row row;

        HSSFCellStyle style = createStyleForTitle(workbook);

        row = sheet.createRow(rownum);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("№ карты");

        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("ФИО Клиента");

        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Номера старых карт");


        for (ClientUsersDiscountCard card : cards
        ) {
            rownum++;
            row = sheet.createRow(rownum);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(card.getNumber_card());

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(card.getFio_client());

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(card.getOld_card());

        }
        for (int i = 0; i < 4; i++) {
            sheet.autoSizeColumn(i);
        }
        String pathName = "D:/Отчеты/Отчет по старым картам/Old-Card--" + WorkDate.getNowDateToString() + ".xls";
        createNewFile(pathName, workbook);
        return pathName;
    }

    public String createListCardsDate(List<ClientUsersDiscountCard> cards) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("лист 1");


        int rownum = 0;
        Cell cell;
        Row row;

        HSSFCellStyle style = createStyleForTitle(workbook);

        row = sheet.createRow(rownum);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("№ карты");

        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Номер телефона");

        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Смс рассылка");


        for (ClientUsersDiscountCard card : cards
        ) {
            rownum++;
            row = sheet.createRow(rownum);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(card.getNumber_card());

            cell = row.createCell(1, CellType.NUMERIC);
            cell.setCellStyle(getStyleLong(workbook));
            cell.setCellValue(card.getTelephone());

            cell = row.createCell(2, CellType.STRING);
            if (card.getSms()) {
                cell.setCellValue("Да");
            } else {
                cell.setCellValue("Нет");
            }

        }
        for (int i = 0; i < 4; i++) {
            sheet.autoSizeColumn(i);
        }
        String pathName = "D:/Отчеты/Отчет за период/Cards-Date--" + WorkDate.getNowDateToString() + ".xls";
        createNewFile(pathName, workbook);
        return pathName;
    }
}
