package ReplaceDate;

public class DateClient {
    private int id_client;
    private String fio_client;
    private String date_born;
    private long telephone;
    private boolean sms;
    private String status_client;

    public DateClient() {
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

    public String getStatus_client() {
        return status_client;
    }

    public void setStatus_client(String status_client) {
        this.status_client = status_client;
    }
}
