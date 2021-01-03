package ServiceLayer;

import Connection.ConnectionDB;
import DAOLayer.DAOSumInCardService;
import EntityLayer.SumInCard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SumInCardService extends ConnectionDB implements DAOSumInCardService {


    @Override
    public void addNewSumInCard(SumInCard new_sum_in_card) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO sumincard " +
                "(ID_CARD, PROCENT_DISCONT, ALL_SUM, CASHBEK_SUM, GIFT_SUM) VALUES (?,?,?,?,?)";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, new_sum_in_card.getId_card());
            preparedStatement.setFloat(2, new_sum_in_card.getProcent_discont());
            preparedStatement.setFloat(3, new_sum_in_card.getAll_sum());
            preparedStatement.setFloat(4, new_sum_in_card.getCashbek_sum());
            preparedStatement.setFloat(5, new_sum_in_card.getGift_sum());

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection(connection);
            closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public List<SumInCard> getAllSum() {
        List<SumInCard> sumInCards = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM sumincard";
        try{
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                SumInCard sumInCard = new SumInCard();

                sumInCard.setId_sum(resultSet.getInt(1));
                sumInCard.setId_card(resultSet.getInt(2));
                sumInCard.setProcent_discont(resultSet.getFloat(3));
                sumInCard.setCashbek_sum(resultSet.getFloat(4));
                sumInCard.setGift_sum(resultSet.getFloat(5));

                sumInCards.add(sumInCard);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection(connection);
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
        }
        return sumInCards;
    }

    @Override
    public SumInCard getSumInCardByIdCard(int id_card) {
        SumInCard sumInCard = new SumInCard();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM sumincard WHERE ID_CARD=?";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id_card);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                sumInCard.setId_sum(resultSet.getInt(1));
                sumInCard.setId_card(resultSet.getInt(2));
                sumInCard.setProcent_discont(resultSet.getFloat(3));
                sumInCard.setAll_sum(resultSet.getFloat(4));
                sumInCard.setCashbek_sum(resultSet.getFloat(5));
                sumInCard.setGift_sum(resultSet.getFloat(6));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection(connection);
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }
        return sumInCard;
    }

    @Override
    public void updateSumInCard(SumInCard update_sum_in_card) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String sql = "UPDATE sumincard SET " +
                "ID_CARD=?,PROCENT_DISCONT=?,ALL_SUM=?,CASHBEK_SUM=?,GIFT_SUM=? WHERE ID_SUM=?";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, update_sum_in_card.getId_card());
            preparedStatement.setFloat(2, update_sum_in_card.getProcent_discont());
            preparedStatement.setFloat(3, update_sum_in_card.getAll_sum());
            preparedStatement.setFloat(4, update_sum_in_card.getCashbek_sum());
            preparedStatement.setFloat(5, update_sum_in_card.getGift_sum());
            preparedStatement.setInt(6, update_sum_in_card.getId_sum());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection(connection);
            closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public void deleteSumInCard(SumInCard delete_sum_in_card) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM sumincard WHERE ID_SUM=?";
        try{
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, delete_sum_in_card.getId_sum());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection(connection);
            closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public boolean existIdCard(int id_card){
        Connection connection = null;
        boolean result = false;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT ID_CARD FROM sumincard WHERE ID_CARD=?";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id_card);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                result = true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection(connection);
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }

        return result;
    }

    @Override
    public int getCount(){
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT MAX(NUMBER_CARD) FROM discountcard;";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                result = resultSet.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection(connection);
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
        }
        return result;
    }
}
