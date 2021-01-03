package DAOLayer;

import EntityLayer.ClientUsersDiscountCard;

import java.util.List;

public interface DAOClientUsersDiscountCardService {
    //Возвращает список новых карт (пришедших на активацию)
    List<ClientUsersDiscountCard> getListNewCard();

    //Возвращает "выборку карт". Например первые 100, или вторые 100 штук
    List<ClientUsersDiscountCard> getListAllCard(int start, int finish);

    //Возвращает список всех карт
    List<ClientUsersDiscountCard> getListAllCard();

    //Возвращает список карт. Согласно отбору по процентам скидок
    List<ClientUsersDiscountCard> getListProcent(float procent_from, float procent_to);

    //Возвращает список карт. Согласно отбору по дате рождения клиентов
    List<ClientUsersDiscountCard> getListCardDateBorn(String date_from, String date_to);

    //Возвращает список карт. Согласно валидности карты (валидна/не валидна)
    List<ClientUsersDiscountCard> getListCardValid(boolean valid_card);

    //Возвращает список карт. Согласно статусу клиента (новый/старый)
    List<ClientUsersDiscountCard> getListCardStatusClient(String status_client);

    //Возвращает список карт. Согласно согласия на sms-рассылку (согласен/не согласен)
    List<ClientUsersDiscountCard> getListCardSms(boolean valid_sms);

    //Возвращает список. Согласно статусу карты (выдана/куплена)
    List<ClientUsersDiscountCard> getListCardByuCard(String status_card);

    //Возвращает список товаров, списанных за кешбек. По всем магазинам или по одному. На определенный период.
    List<ClientUsersDiscountCard> getListCardByDateAndOneUser(String date_from, String date_to, String magazine);

    //Возвращает список карт. Выданных за опеределенный период
    List<ClientUsersDiscountCard> getListCardByDateAllUser(String date_from, String date_to);

    //Возвращает список карт, у которых менялись номера
    List<ClientUsersDiscountCard> getListOldCard();

    //Возвращает список "транзакций" за кешбек по определенной карте
    List<ClientUsersDiscountCard> getCashBackIdCard(int id_card);

    //Возвращает список "транзакций" за денежные средства по определенно карте
    List<ClientUsersDiscountCard> getMoneyIdCard(int id_card);

    //Возвращает список "транзакций" за кешбек по определенному пользователю, за определнный период
    List<ClientUsersDiscountCard> getHistoryMoney(int id_user, String date_from, String date_to);

    //Возвращает список "транзакций" за денежные средства по определенному пользователю, за опеределенный период
    List<ClientUsersDiscountCard> getHistotyCashbek(int id_user, String date_from, String date_to);

    //Возвращает список "транзакций" за кушбек по определенно карте, за определенный период
    List<ClientUsersDiscountCard> getHistoryMoneyIdCard(int id_card, String date_from, String date_to);

    //Возвращает список "транзакций" за денежные средства по определенно карте, за определенный период
    List<ClientUsersDiscountCard> getHistoryCashbekIdCard(int id_card, String date_from, String date_to);

    //Возвращает список карт за определенный период созданных и согласных на смс рассылку
    List<ClientUsersDiscountCard> getReportDateCreateCard(String dateFrom, String dateTo);
}
