package ReplaceDate;

public class DateSaleSum {

    private int id_sale;
    private int id_card;
    private int id_user;
    private float buy_sum;
    private float cashbek_sum;
    private float gift_sum;
    private String date_byu;
    private String time_buy;
    private String comment;
    private String sposob_oplat;

    public DateSaleSum() {
    }

    public int getId_sale() {
        return id_sale;
    }

    public void setId_sale(int id_sale) {
        this.id_sale = id_sale;
    }

    public int getId_card() {
        return id_card;
    }

    public void setId_card(int id_card) {
        this.id_card = id_card;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public float getBuy_sum() {
        return buy_sum;
    }

    public void setBuy_sum(float buy_sum) {
        this.buy_sum = buy_sum;
    }

    public float getCashbek_sum() {
        return cashbek_sum;
    }

    public void setCashbek_sum(float cashbek_sum) {
        this.cashbek_sum = cashbek_sum;
    }

    public float getGift_sum() {
        return gift_sum;
    }

    public void setGift_sum(float gift_sum) {
        this.gift_sum = gift_sum;
    }

    public String getDate_byu() {
        return date_byu;
    }

    public void setDate_byu(String date_byu) {
        this.date_byu = date_byu;
    }

    public String getTime_buy() {
        return time_buy;
    }

    public void setTime_buy(String time_buy) {
        this.time_buy = time_buy;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSposob_oplat() {
        return sposob_oplat;
    }

    public void setSposob_oplat(String sposob_oplat) {
        this.sposob_oplat = sposob_oplat;
    }
}
