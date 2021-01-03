package DAOLayer;

import EntityLayer.Client;

import java.util.List;

public interface DAOClientService {

    //Добавление нового клиента, возврат id_client
    int addNewClient(Client new_client);

    //Получение списка всех клиентов
    List <Client> getAllClient();

    //Получение данных клиента по его ID
    Client getClientByIdClient(int id_client);

    //Обновление данных клиента
    void updateClient(Client update_client);

    //Удаление данных клиента
    void deleteClient(Client delete_client);

    //id_client по значение моб телефона
    int getClientIdByNumberTelephone(long telephone_client);

    //id_client по значениям моб телефона, номера карты, даты рождения
    int getCientIdByTelNumCardDateBorn(long telephone, int number_card, String client_date_born);

    //Список клиентов, попадающий в заданный диапазон дат
    List<Client> getClientDateBorn(String date_from, String date_by);
}
