package EntityLayer;

public class Users {

    private int id_user;
    private String login;
    private String password;
    private int magazine;
    private String fio_user;
    private String role;
    private boolean valid_user;

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMagazine() {
        return magazine;
    }

    public void setMagazine(int magazine) {
        this.magazine = magazine;
    }

    public String getFio_user() {
        return fio_user;
    }

    public void setFio_user(String fio_user) {
        this.fio_user = fio_user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean getValid_user() {
        return valid_user;
    }

    public void setValid_user(boolean valid) {
        this.valid_user = valid;
    }

    public Users(){

    }

    @Override
    public String toString() {
        return "Users{" +
                "id_user=" + id_user +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", magazine=" + magazine +
                ", fio_user='" + fio_user + '\'' +
                ", role='" + role + '\'' +
                ", valid_user=" + valid_user +
                '}';
    }
}
