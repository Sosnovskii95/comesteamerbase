package DAOLayer;


import EntityLayer.DiscountCard;

import java.util.List;

public interface DAODiscountCardService {

    //Добавление данных новой карты, возврат id_card
    int addNewCard(DiscountCard new_card);

    //Список всех карт
    List<DiscountCard> getAllCard();

    //Получение данных карты по ее физическому номеру
    DiscountCard getCardByNumberCard(int number_card);

    //Обновление данных карты
    void updateCard(DiscountCard update_card);

    //Удаление данных по карте
    void deleteCard(DiscountCard delete_card);

    //Список новых карт
    List<DiscountCard> getAllNewCard();

    //Информация по id_card
    DiscountCard getCardByIdCard(int id_card);

    //Возвращает физический номер карты по id_client
    int getNumberCardByIdClient(int id_client);

    //Возвращает id_card по id_client
    int getIdCardByIdClient(int id_client);

    //Проверка существования карты
    boolean checkCard(int number_card);

    //Получение id_card по физическому номеру карты
    int getIdCardByNumberCard(int number_card);

    //Получение id_client по физическому номеру карты
    int getIdClientByNumberCard(int number_card);
}
