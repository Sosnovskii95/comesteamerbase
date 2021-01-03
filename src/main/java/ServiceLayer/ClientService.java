package ServiceLayer;

import Connection.ConnectionDB;
import DAOLayer.DAOClientService;
import EntityLayer.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ClientService extends ConnectionDB implements DAOClientService {

    @Override
    public int addNewClient(Client new_client) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        int id_client = 0;
        String sql = "INSERT INTO client (FIO_CLIENT, DATE_BORN, TELEPHONE, SMS, STATUS_CLIENT) " +
                "VALUES (?,?,?,?,?)";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, new_client.getFio_client());
            preparedStatement.setDate(2, new_client.getDate_born());
            preparedStatement.setLong(3, new_client.getTelephone());
            preparedStatement.setBoolean(4, new_client.getSms());
            preparedStatement.setString(5, new_client.getStatus_client());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()){
                id_client = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection(connection);
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }
        return id_client;
    }

    @Override
    public List<Client> getAllClient() {
        List<Client> clientsList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = "SELECT * FROM client";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Client client = new Client();

                client.setId_client(resultSet.getInt(1));
                client.setFio_client(resultSet.getString(2));
                client.setDate_born(resultSet.getDate(3));
                client.setTelephone(resultSet.getLong(4));
                client.setSms(resultSet.getBoolean(5));
                client.setStatus_client(resultSet.getString(6));

                clientsList.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection(connection);
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }
        return clientsList;
    }

    @Override
    public Client getClientByIdClient(int id_client) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT ID_CLIENT, FIO_CLIENT,DATE_BORN,TELEPHONE,SMS, STATUS_CLIENT" +
                " FROM client WHERE ID_CLIENT=?";
        Client client = new Client();
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id_client);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                client.setId_client(resultSet.getInt(1));
                client.setFio_client(resultSet.getString(2));
                client.setDate_born(resultSet.getDate(3));
                client.setTelephone(resultSet.getLong(4));
                client.setSms(resultSet.getBoolean(5));
                client.setStatus_client(resultSet.getString(6));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection(connection);
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
        }
        return client;
    }

    @Override
    public void updateClient(Client update_client) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE client SET FIO_CLIENT=?,DATE_BORN=?,TELEPHONE=?," +
                "SMS=?, STATUS_CLIENT=? WHERE ID_CLIENT=?";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, update_client.getFio_client());
            preparedStatement.setDate(2, update_client.getDate_born());
            preparedStatement.setLong(3, update_client.getTelephone());
            preparedStatement.setBoolean(4, update_client.getSms());
            preparedStatement.setString(5, update_client.getStatus_client());
            preparedStatement.setInt(6, update_client.getId_client());


            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection(connection);
            closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public void deleteClient(Client delete_client) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM client WHERE ID_CLIENT=?";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, delete_client.getId_client());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection(connection);
            closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public int getClientIdByNumberTelephone(long telephone_client){
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT ID_CLIENT FROM client WHERE TELEPHONE=?";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, telephone_client);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                result = resultSet.getInt(1);
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
    public int getCientIdByTelNumCardDateBorn(long telephone, int number_card, String client_date_born){
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT client.ID_CLIENT, FIO_CLIENT FROM client,discountcard " +
                "WHERE NUMBER_CARD=? AND TELEPHONE=? AND DATE_BORN=?;";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, number_card);
            preparedStatement.setLong(2, telephone);
            preparedStatement.setString(3, client_date_born);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                result = resultSet.getInt("id_client");
            }
        }catch (SQLException sqlex){
            sqlex.printStackTrace();
        }finally {
            closeConnection(connection);
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }
        return result;
    }

    @Override
    public List<Client> getClientDateBorn(String date_from, String date_by) {
        List<Client> clientsList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = "SELECT * FROM client WHERE date_format(?, '%m %d')<=date_format(DATE_BORN,'%m %d') " +
                "AND date_format(DATE_BORN,'%m %d')<=date_format(?,'%m %d');";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, date_from);
            preparedStatement.setString(2, date_by);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Client client = new Client();

                client.setId_client(resultSet.getInt(1));
                client.setFio_client(resultSet.getString(2));
                client.setDate_born(resultSet.getDate(3));
                client.setTelephone(resultSet.getLong(4));
                client.setSms(resultSet.getBoolean(5));

                clientsList.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection(connection);
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }
        return clientsList;
    }
}
