package ServiceLayer;

import Connection.ConnectionDB;
import DAOLayer.DAODiscountCardService;
import EntityLayer.DiscountCard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiscountCardService extends ConnectionDB implements DAODiscountCardService {


    @Override
    public int addNewCard(DiscountCard new_card) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int id_card = 0;
        String sql = "INSERT INTO discountcard (ID_USER,ID_CLIENT,NUMBER_CARD,VALID_CARD,DATE_CREATE, OLD_CARD, STATUS_CARD) " +
                "VALUES(?,?,?,?,?,?,?) ";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1 , new_card.getUser_id());
            preparedStatement.setInt(2, new_card.getClient_id());
            preparedStatement.setInt(3, new_card.getNumber_card());
            preparedStatement.setBoolean(4, new_card.getValid_card());
            preparedStatement.setDate(5, new_card.getDate_create());
            preparedStatement.setString(6, new_card.getOld_card());
            preparedStatement.setString(7, new_card.getStatus_card());

            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()){
                id_card = resultSet.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
            closeResultSet(resultSet);
        }
        return id_card;
    }

    @Override
    public List<DiscountCard> getAllCard() {
        List<DiscountCard> cards = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM discountcard";
        try{
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                DiscountCard discountCard = new DiscountCard();

                discountCard.setId_card(resultSet.getInt(1));
                discountCard.setUser_id(resultSet.getInt(2));
                discountCard.setClient_id(resultSet.getInt(3));
                discountCard.setNumber_card(resultSet.getInt(4));
                discountCard.setValid_card(resultSet.getBoolean(5));
                discountCard.setDate_create(resultSet.getDate(6));
                discountCard.setOld_card(resultSet.getString(7));
                discountCard.setStatus_card(resultSet.getString(8));

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




    @Override
    public DiscountCard getCardByNumberCard(int number_card) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT ID_CARD, ID_USER, ID_CLIENT, NUMBER_CARD, VALID_CARD, DATE_CREATE, OLD_CARD, STATUS_CARD " +
                "FROM discountcard WHERE NUMBER_CARD=?";
        DiscountCard discountCard = new DiscountCard();
        try{
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, number_card);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                discountCard.setId_card(resultSet.getInt(1));
                discountCard.setUser_id(resultSet.getInt(2));
                discountCard.setClient_id(resultSet.getInt(3));
                discountCard.setNumber_card(resultSet.getInt(4));
                discountCard.setValid_card(resultSet.getBoolean(5));
                discountCard.setDate_create(resultSet.getDate(6));
                discountCard.setOld_card(resultSet.getString(7));
                discountCard.setStatus_card(resultSet.getString(8));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
            closeConnection(connection);
        }
        return discountCard;
    }

    @Override
    public DiscountCard getCardByIdCard(int id_card) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT ID_USER, ID_CLIENT, NUMBER_CARD, VALID_CARD, DATE_CREATE, OLD_CARD, STATUS_CARD " +
                "FROM discountcard WHERE ID_CARD=?";
        DiscountCard discountCard = new DiscountCard();
        try{
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id_card);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                discountCard.setUser_id(resultSet.getInt(1));
                discountCard.setClient_id(resultSet.getInt(2));
                discountCard.setNumber_card(resultSet.getInt(3));
                discountCard.setValid_card(resultSet.getBoolean(4));
                discountCard.setDate_create(resultSet.getDate(5));
                discountCard.setOld_card(resultSet.getString(6));
                discountCard.setStatus_card(resultSet.getString(7));
            }
            discountCard.setId_card(id_card);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
            closeConnection(connection);
        }
        return discountCard;
    }

    @Override
    public void updateCard(DiscountCard update_card) {
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
            preparedStatement.setDate(5, update_card.getDate_create());
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

    @Override
    public void deleteCard(DiscountCard delete_card) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM discountcard WHERE ID_CARD=?";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, delete_card.getId_card());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }

    }

    @Override
    public int getNumberCardByIdClient(int id_client){
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT NUMBER_CARD FROM discountcard WHERE ID_CLIENT=?";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id_client);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                result = resultSet.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return result;
    }

    @Override
    public int getIdCardByIdClient(int id_client){
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT ID_CARD FROM discountcard WHERE ID_CLIENT=?";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id_client);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                result = resultSet.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return result;
    }

    @Override
    public boolean checkCard(int number_card){
        boolean result = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT NUMBER_CARD FROM discountcard where NUMBER_CARD=?";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, number_card);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                result = true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return result;
    }

    @Override
    public int getIdCardByNumberCard(int number_card){
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT ID_CARD FROM discountcard WHERE NUMBER_CARD=?";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, number_card);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                result = resultSet.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
            closeConnection(connection);
        }
        return result;
    }

    @Override
    public int getIdClientByNumberCard(int number_card){
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT ID_CLIENT FROM discountcard WHERE NUMBER_CARD=?";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, number_card);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                result = resultSet.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
            closeConnection(connection);
        }
        return result;
    }

    @Override
    public List<DiscountCard> getAllNewCard() {
        List<DiscountCard> cards = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM discountcard WHERE VALID_CARD=FALSE";
        try{
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery(sql);

            while (resultSet.next()){
                DiscountCard discountCard = new DiscountCard();

                discountCard.setId_card(resultSet.getInt(1));
                discountCard.setUser_id(resultSet.getInt(2));
                discountCard.setClient_id(resultSet.getInt(3));
                discountCard.setNumber_card(resultSet.getInt(4));
                discountCard.setValid_card(resultSet.getBoolean(5));
                discountCard.setDate_create(resultSet.getDate(6));

                cards.add(discountCard);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection(connection);
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }
        return cards;
    }
}
