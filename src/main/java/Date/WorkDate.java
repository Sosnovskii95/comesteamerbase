package Date;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

public class WorkDate {

    public static String parseDate(String date) {
        String new_date;
        boolean result = date.indexOf('.') != 0;
        if (result) {
            date.replace('.', '-');
        }
        new_date = date.substring(6, 10) + "-" + date.substring(3, 5) + "-" + date.substring(0, 2);
        return new_date;
    }

    public static String undoParseDate(String date) {
        String new_date;
        new_date = date.substring(8, 10) + "." + date.substring(5, 7) + "." + date.substring(0, 4);
        return new_date;
    }

    public static String undoParseDate(Date date) {
        String new_date;
        new_date = undoParseDate(date.toString());
        return new_date;
    }


    //Генерирует текущую дату в строку
    public static String getNowDateToString() {
        String date;
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(Calendar.DAY_OF_MONTH) < 10) {
            date = "0" + calendar.get(Calendar.DAY_OF_MONTH);
        } else {
            date = "" + calendar.get(Calendar.DAY_OF_MONTH);
        }
        if ((calendar.get(Calendar.MONTH) + 1) < 10) {
            date += ".0" + (calendar.get(Calendar.MONTH) + 1);
        } else {
            date += "." + (calendar.get(Calendar.MONTH) + 1);
        }
        date += "." + calendar.get(Calendar.YEAR);
        return date;
    }

    //Генерирует текущую дату в строку
    public static Date getNowDateToDate() {
        String date = "";
        Calendar calendar = Calendar.getInstance();

        date += calendar.get(Calendar.YEAR) + "-";
        if ((calendar.get(Calendar.MONTH) + 1) < 10) {
            date += "0" + (calendar.get(Calendar.MONTH) + 1);
        } else {
            date += (calendar.get(Calendar.MONTH) + 1);
        }
        if (calendar.get(Calendar.DAY_OF_MONTH) < 10) {
            date += "-0" + calendar.get(Calendar.DAY_OF_MONTH);
        } else {
            date += "-" + calendar.get(Calendar.DAY_OF_MONTH);
        }
        return Date.valueOf(date);
    }

    //Генерирует текущие время в строку
    public static Time getNowTime() {
        String time;
        Calendar calendar = Calendar.getInstance();

        if (calendar.get(Calendar.HOUR_OF_DAY) < 10) {
            time = "0" + calendar.get(Calendar.HOUR_OF_DAY);
        } else {
            time = "" + calendar.get(Calendar.HOUR_OF_DAY);
        }

        if (calendar.get(Calendar.MINUTE) < 10) {
            time += ":0" + calendar.get(Calendar.MINUTE);
        } else {
            time += ":" + calendar.get(Calendar.MINUTE);
        }

        if (calendar.get(Calendar.SECOND) < 10) {
            time += ":0" + calendar.get(Calendar.SECOND);
        } else {
            time += ":" + calendar.get(Calendar.SECOND);
        }

        return Time.valueOf(time);
    }

    //Генерирует завтрашнюю дату в строку
    public static String getTommorowDate() {
        String date;
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(Calendar.DAY_OF_MONTH) < 10) {
            date = "0" + (calendar.get(Calendar.DAY_OF_MONTH) + 1) + ".0" + (calendar.get(Calendar.MONTH) + 1) + "." + calendar.get(Calendar.YEAR);
        } else {
            if (calendar.get(Calendar.MONTH) < 10) {
                date = "" + (calendar.get(Calendar.DAY_OF_MONTH) + 1) + ".0" + (calendar.get(Calendar.MONTH) + 1) + "." + calendar.get(Calendar.YEAR);
            } else {
                date = "" + (calendar.get(Calendar.DAY_OF_MONTH) + 1) + "." + (calendar.get(Calendar.MONTH) + 1) + "." + calendar.get(Calendar.YEAR);
            }
        }
        return date;
    }

    //Генерирует вчерашнюю дату в строку
    public static String getYesterdayDate() {
        String date;
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(Calendar.DAY_OF_MONTH) < 10) {
            date = "0" + (calendar.get(Calendar.DAY_OF_MONTH) - 2) + ".0" + (calendar.get(Calendar.MONTH) + 1) + "." + calendar.get(Calendar.YEAR);
        } else {
            if (calendar.get(Calendar.MONTH) < 10) {
                date = "" + (calendar.get(Calendar.DAY_OF_MONTH) - 2) + ".0" + (calendar.get(Calendar.MONTH) + 1) + "." + calendar.get(Calendar.YEAR);
            } else {
                date = "" + (calendar.get(Calendar.DAY_OF_MONTH) - 2) + "." + (calendar.get(Calendar.MONTH) + 1) + "." + calendar.get(Calendar.YEAR);
            }
        }
        return date;
    }

    //Генерирует дату начала текущего месяца в стркоу
    public static String getStartMonth() {
        String date;
        Calendar calendar = Calendar.getInstance();
        date = "0" + (calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        if (calendar.get(Calendar.MONTH) + 1 < 10) {
            date += ".0" + (calendar.get(Calendar.MONTH) + 1);
        } else {
            date += "." + (calendar.get(Calendar.MONTH) + 1);
        }
        date += "." + calendar.get(Calendar.YEAR);
        return date;
    }

    //Генерирует дату конца текущего месяца в строку
    public static String getEndMonth() {
        String date;
        Calendar calendar = Calendar.getInstance();
        if (calendar.getActualMaximum(Calendar.DAY_OF_MONTH) < 10) {
            date = "0" + (calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) + ".0" + (calendar.get(Calendar.MONTH) + 1) + "." + calendar.get(Calendar.YEAR);
        } else {
            if (calendar.get(Calendar.MONTH) < 10) {
                date = "" + calendar.getActualMaximum(Calendar.DAY_OF_MONTH) + ".0" + (calendar.get(Calendar.MONTH) + 1) + "." + calendar.get(Calendar.YEAR);
            } else {
                date = "" + calendar.getActualMaximum(Calendar.DAY_OF_MONTH) + "." + (calendar.get(Calendar.MONTH) + 1) + "." + calendar.get(Calendar.YEAR);
            }
        }
        return date;
    }
}
