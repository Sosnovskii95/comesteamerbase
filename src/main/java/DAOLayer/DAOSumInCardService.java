package DAOLayer;

import EntityLayer.SumInCard;

import java.util.List;

public interface DAOSumInCardService {

    //Добавление новой суммы для карты
    void addNewSumInCard(SumInCard new_sum_in_card);

    //Список всех сумм ка картах
    List<SumInCard> getAllSum();

    //Получение суммы на карте по ее id
    SumInCard getSumInCardByIdCard(int id_card);

    //Обновление суммы на карте
    void updateSumInCard(SumInCard update_sum_in_card);

    //Удаление суммы карты
    void deleteSumInCard(SumInCard delete_sum_in_card);

    //Существует ли карта с определенным id
    boolean existIdCard(int id_card);

    //Получение количества сумм в таблице
    int getCount();
}
