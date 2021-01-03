package EntityLayer;

public class ClientUsersDiscountCard {
    private int id_client;
    private String fio_client;
    private String date_born;
    private long telephone;
    private boolean sms;
    private String ststus_client;
    private int id_card;
    private int number_card;
    private boolean valid_card;
    private String date_create;
    private String old_card;
    private int id_user;
    private String password;
    private String magazine;
    private String fio_user;
    private float procent_discont;
    private float all_sum;
    private String status_card;
    private int id_sale;
    private String date_buy;
    private String time_buy;
    private String comment;
    private float buy_sum;
    private float cashbek_sum;
    private float gift_sum;
    private float cashbek_in_card;

    public float getCashbek_in_card() {
        return cashbek_in_card;
    }

    public void setCashbek_in_card(float cashbek_in_card) {
        this.cashbek_in_card = cashbek_in_card;
    }

    public String getSposob_oplat() {
        return sposob_oplat;
    }

    private String sposob_oplat;

   public ClientUsersDiscountCard(){

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

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getFio_client() {
        return fio_client;
    }

    public void setFio_client(String fio_client) {
        this.fio_client = fio_client;
    }

    public String getDate_born() {
        return date_born;
    }

    public void setDate_born(String date_born) {
        this.date_born = date_born;
    }

    public long getTelephone() {
        return telephone;
    }

    public void setTelephone(long telephone) {
        this.telephone = telephone;
    }

    public boolean getSms() {
        return sms;
    }

    public void setSms(boolean sms) {
        this.sms = sms;
    }

    public String getStstus_client() {
        return ststus_client;
    }

    public void setStstus_client(String ststus_client) {
        this.ststus_client = ststus_client;
    }

    public int getId_card() {
        return id_card;
    }

    public void setId_card(int id_card) {
        this.id_card = id_card;
    }

    public int getNumber_card() {
        return number_card;
    }

    public void setNumber_card(int number_card) {
        this.number_card = number_card;
    }

    public boolean getValid_card() {
        return valid_card;
    }

    public void setValid_card(boolean valid_card) {
        this.valid_card = valid_card;
    }

    public String getDate_create() {
        return date_create;
    }

    public void setDate_create(String date_create) {
        this.date_create = date_create;
    }

    public String getOld_card() {
        return old_card;
    }

    public void setOld_card(String old_card) {
        this.old_card = old_card;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMagazine() {
        return magazine;
    }

    public void setMagazine(String magazine) {
        this.magazine = magazine;
    }

    public String getFio_user() {
        return fio_user;
    }

    public void setFio_user(String fio_user) {
        this.fio_user = fio_user;
    }

    public float getProcent_discont() {
        return procent_discont;
    }

    public void setProcent_discont(float procent_discont) {
        this.procent_discont = procent_discont;
    }

    public float getAll_sum() {
        return all_sum;
    }

    public void setAll_sum(float all_sum) {
        this.all_sum = all_sum;
    }

    public String getStatus_card() {
        return status_card;
    }

    public void setStatus_card(String status_card) {
        this.status_card = status_card;
    }

    public String getDate_buy() {
        return date_buy;
    }

    public void setDate_buy(String date_buy) {
        this.date_buy = date_buy;
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


    public void setId_sale(int id_sale) {
        this.id_sale = id_sale;
    }

    public int getId_sale() {
        return id_sale;
    }



    public void setSposob_oplat(String sposob_oplat) {
        this.sposob_oplat = sposob_oplat;
    }

    @Override
    public String toString() {
        return "ClientUsersDiscountCard{" +
                "id_client=" + id_client +
                ", fio_client='" + fio_client + '\'' +
                ", date_born='" + date_born + '\'' +
                ", telephone=" + telephone +
                ", sms=" + sms +
                ", ststus_client='" + ststus_client + '\'' +
                ", id_card=" + id_card +
                ", number_card=" + number_card +
                ", valid_card=" + valid_card +
                ", date_create='" + date_create + '\'' +
                ", old_card='" + old_card + '\'' +
                ", id_user=" + id_user +
                ", password='" + password + '\'' +
                ", magazine='" + magazine + '\'' +
                ", fio_user='" + fio_user + '\'' +
                ", procent_discont=" + procent_discont +
                ", all_sum=" + all_sum +
                '}';
    }
}
