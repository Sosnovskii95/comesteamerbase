package EntityLayer;


import java.sql.Date;

public class Client {
    private int id_client;
    private String fio_client;
    private Date date_born;
    private long telephone;
    private boolean sms;
    private String status_client;

    public Client(){

    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_user) {
        this.id_client = id_user;
    }

    public String getFio_client() {
        return fio_client;
    }

    public void setFio_client(String fio_client) {
        this.fio_client = fio_client;
    }

    public Date getDate_born() {
        return date_born;
    }

    public void setDate_born(Date date_born) {
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
