package DAOLayer;

import EntityLayer.SaleSum;

import java.util.List;

public interface DAOSaleSumService {

    //Добавление новой "транзакции" и возврат ее id_sale
    int addNewSaleSum(SaleSum new_sale_sum);

    //Получение всех "транзакций"
    List<SaleSum> getAllSum();

    //Получение всех "транзакций" по конкретной карте
    List<SaleSum> getSumByIdCard(int id_card);

    //Изменение конкретной "транзакции"
    void updateSaleSum(SaleSum update_sale_sum);

    //Удаление списка "транзакций"
    void deleteListSaleSum(List<SaleSum> delete_all_sale_sum);

    //Удание 1 "транзакции" по id_sale
    void deleteSaleSum(int id_sale);

    //Получение "транзакции" по id "транзакции"
    SaleSum getSumIdSale(int id_sale);

    //Получение последней проведенной "транзакции" по конкретной карте
    SaleSum getDateLastBuy(int id_card);
}
