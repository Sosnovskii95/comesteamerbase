package ReplaceDate;

import Connection.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DateDiscountCardService extends ConnectionDB {

    public List<DateDiscountCard> getAllCard() {
        List<DateDiscountCard> cards = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM discountcard";
        try{
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                DateDiscountCard discountCard = new DateDiscountCard();

                discountCard.setId_card(resultSet.getInt("id_card"));
                discountCard.setUser_id(resultSet.getInt("id_user"));
                discountCard.setClient_id(resultSet.getInt("id_client"));
                discountCard.setNumber_card(resultSet.getInt("number_card"));
                discountCard.setValid_card(resultSet.getBoolean("valid_card"));
                discountCard.setDate_create(resultSet.getString("date_create"));
                discountCard.setOld_card(resultSet.getString("old_card"));
                discountCard.setStatus_card(resultSet.getString("status_card"));

                cards.add(discountCard);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeStatement(statement);
            closeResultSet(resultSet);
            closeConnection(connection);
        }
        return cards;
    }

    public void updateCard(DateDiscountCard update_card) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE discountcard SET ID_USER=?, ID_CLIENT=?, " +
                "NUMBER_CARD=?, VALID_CARD=?, DATE_CREATE=?, OLD_CARD=?, STATUS_CARD=? WHERE ID_CARD=?";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, update_card.getUser_id());
            preparedStatement.setInt(2, update_card.getClient_id());
            preparedStatement.setInt(3, update_card.getNumber_card());
            preparedStatement.setBoolean(4, update_card.getValid_card());
            preparedStatement.setString(5, update_card.getDate_create());
            preparedStatement.setString(6, update_card.getOld_card());
            preparedStatement.setString(7, update_card.getStatus_card());
            preparedStatement.setInt(8, update_card.getId_card());


            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }
}
