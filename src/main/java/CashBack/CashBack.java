package CashBack;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Properties;

public class CashBack {

    private int[] procent_discont;
    private int[] sum_discount;

    private void setParams(){
        int[] procent = new int[]{0};
        int[] sums = new int[]{0};
        int count;
        InputStream inputStream = null;
        try {
            //Считываем настройки процентов из конфига
            Properties properties = new Properties();
            inputStream =
                    getClass().getResourceAsStream("/Config/cashbak_config.properties");
            properties.load(inputStream);
            //Заполняем количество процентов
            count = properties.size()/2;
            //Создаем массивы заданной размерности
            procent = new int[count];
            sums = new int[count];

            for (int i = 0; i < procent.length; i++){
                //Записываем в массив значения процентов
                procent[i] = Integer.parseInt(properties.getProperty("procent"+(i+1)));
                //Записываем значения сумм для этих процентов
                sums[i] = Integer.parseInt(properties.getProperty("sum"+(i+1)));
            }

        }catch (IOException ex){

        }finally {
            try {
                if (inputStream != null)
                {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //Вносим данные для доступа
        this.procent_discont = procent;
        this.sum_discount = sums;
    }

    private boolean getParams(){
        boolean result = false;
        if (procent_discont.length != 0)
            result = true;
        return result;
    }

    public CashBack(){
        setParams();
    }

    public int[] getProcent_discont() {
        return procent_discont;
    }

    public int[] getSum_discount() {
        return sum_discount;
    }

    public float getProcent(float sum){
        float result = 0;
        //Проверяем значения массивов
        if (getParams()) {
            for (int i = 0; i < procent_discont.length; i++) {
                //В зависимости от суммы выставляем процент
                if (sum_discount[i] <= sum){
                    result = procent_discont[i];
                }
            }
        }
        //Возвращаем значения нового процента
        return result;
    }

    public float getCashBackSum(float sum, float procent){
        //расчитываем сумму кешбека по заданному проценту
        float result = sum*(procent/100);
        return new BigDecimal(result).setScale(1, RoundingMode.HALF_UP).floatValue();
    }

    public float getSum(float sum){
        return new BigDecimal(sum).setScale(1, RoundingMode.HALF_UP).floatValue();
    }
}
