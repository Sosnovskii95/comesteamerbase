package EntityLayer;

public class SumInCard {

    private int id_sum;
    private int id_card;
    private float procent_discont;
    private float all_sum;
    private float cashbek_sum;
    private float gift_sum;

    public int getId_sum() {
        return id_sum;
    }

    public void setId_sum(int id_sum) {
        this.id_sum = id_sum;
    }

    public int getId_card() {
        return id_card;
    }

    public void setId_card(int number_card) {
        this.id_card = number_card;
    }

    public float getProcent_discont() {
        return procent_discont;
    }

    public void setProcent_discont(float procent_discount) {

        this.procent_discont = procent_discount;
    }

    public float getAll_sum() {
        return all_sum;
    }

    public void setAll_sum(float all_sum) {
        this.all_sum = all_sum;
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

    public SumInCard(){

    }

    @Override
    public String toString() {
        return "SumInCard{" +
                "id_sum=" + id_sum +
                ", id_card=" + id_card +
                ", procent_discount=" + procent_discont +
                ", all_sum=" + all_sum +
                ", cashbek_sum=" + cashbek_sum +
                ", gift_sum=" + gift_sum +
                '}';
    }
}
