package ServiceLayer;

import Connection.ConnectionDB;
import DAOLayer.DAOMagazine;
import EntityLayer.Magazine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MagazineService extends ConnectionDB implements DAOMagazine  {

    @Override
    public void addNewMagazine(String name_m) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO magazine(name_m) VALUES (?);";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name_m);
            preparedStatement.executeUpdate();
        }catch (SQLException sqlex)
        {
            sqlex.printStackTrace();
        }finally {
            closeConnection(connection);
            closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public void updateMagazine(Magazine magazine) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE magazine SET name_m=? WHERE id_magazine=?;";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, magazine.getName_m());
            preparedStatement.setInt(2, magazine.getId_magazine());
            preparedStatement.executeUpdate();
        }catch (SQLException sqlex)
        {
            sqlex.printStackTrace();
        }finally {
            closeConnection(connection);
            closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public void deleteMagazine(int id_magazine) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM magazine WHERE id_magazine=?;";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id_magazine);
            preparedStatement.executeUpdate();
        }catch (SQLException sqlex){
            sqlex.printStackTrace();
        }finally {
            closeConnection(connection);
            closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public List<Magazine> getAllNameMagazineUserTrue() {
        List<Magazine> magazineList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT id_magazine, name_m FROM magazine, users WHERE ROLE='user' " +
                "AND VALID_USER=TRUE AND users.MAGAZINE=MAGAZINE.id_magazine;";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Magazine magazine = new Magazine();
                magazine.setId_magazine(resultSet.getInt(1));
                magazine.setName_m(resultSet.getString(2));

                magazineList.add(magazine);
            }
        }catch (SQLException sqlex){
            sqlex.printStackTrace();
        }finally {
            closeConnection(connection);
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }
        return magazineList;
    }

    @Override
    public String getNameMagazineByIdMagazine(int id_magazine) {
        String result = "";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT name_m FROM magazine WHERE id_magazine=?";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id_magazine);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                result = resultSet.getString(1);
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
    public Magazine getMagazine(int id_magazine) {
        Magazine magazine = new Magazine();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM magazine WHERE id_magazine=?";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id_magazine);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                magazine.setId_magazine(resultSet.getInt(1));
                magazine.setName_m(resultSet.getString(2));
            }
        }catch (SQLException sqlex)
        {
            sqlex.printStackTrace();
        }finally {
            closeConnection(connection);
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }

        return magazine;
    }

    @Override
    public List<Magazine> getAllNameMagazineByAllUser() {
        List<Magazine> magazineList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM magazine";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Magazine magazine = new Magazine();
                magazine.setId_magazine(resultSet.getInt(1));
                magazine.setName_m(resultSet.getString(2));

                magazineList.add(magazine);
            }
        }catch (SQLException sqlex){
            sqlex.printStackTrace();
        }finally {
            closeConnection(connection);
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }
        return magazineList;
    }
}
