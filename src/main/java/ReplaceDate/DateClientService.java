package ReplaceDate;

import Connection.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DateClientService extends ConnectionDB {



    public List<DateClient> getAllClient() {
        List<DateClient> clientsList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String sql = "SELECT * FROM client";
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                DateClient client = new DateClient();

                client.setId_client(resultSet.getInt(1));
                client.setFio_client(resultSet.getString(2));
                client.setDate_born(resultSet.getString(3));
                client.setTelephone(resultSet.getLong(4));
                client.setSms(resultSet.getBoolean(5));
                client.setStatus_client(resultSet.getString(6));

                clientsList.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection(connection);
            closeStatement(statement);
            closeResultSet(resultSet);
        }
        return clientsList;
    }


    public void updateClient(DateClient update_client) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE client SET FIO_CLIENT=?,DATE_BORN=?,TELEPHONE=?," +
                "SMS=?, STATUS_CLIENT=? WHERE ID_CLIENT=?";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, update_client.getFio_client());
            preparedStatement.setString(2, update_client.getDate_born());
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


}
