package ReplaceDate;

public class DateDiscountCard {

    private int id_card;
    private int user_id;
    private int client_id;
    private int number_card;
    private boolean valid_card;
    private String date_create;
    private String old_card;
    private String status_card;

    public DateDiscountCard() {
    }

    public int getId_card() {
        return id_card;
    }

    public void setId_card(int id_card) {
        this.id_card = id_card;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
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

    public String getStatus_card() {
        return status_card;
    }

    public void setStatus_card(String status_card) {
        this.status_card = status_card;
    }
}
