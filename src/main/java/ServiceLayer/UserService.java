package ServiceLayer;

import Connection.ConnectionDB;
import DAOLayer.DAOUserService;
import EntityLayer.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService extends ConnectionDB implements DAOUserService {


    @Override
    public void addNewUser(Users new_user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO users " +
                "(LOGIN, PASSWORD, MAGAZINE, FIO_USER, ROLE, VALID_USER) VALUES (?,?,?,?,?,?)";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, new_user.getLogin());
            preparedStatement.setString(2, new_user.getPassword());
            preparedStatement.setInt(3, new_user.getMagazine());
            preparedStatement.setString(4, new_user.getFio_user());
            preparedStatement.setString(5, new_user.getRole());
            preparedStatement.setBoolean(6, new_user.getValid_user());

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection(connection);
            closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public List<Users> getAllUsers() {
        List<Users> usersList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM users";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Users users = new Users();

                users.setId_user(resultSet.getInt(1));
                users.setLogin(resultSet.getString(2));
                users.setPassword(resultSet.getString(3));
                users.setMagazine(resultSet.getInt(4));
                users.setFio_user(resultSet.getString(5));
                users.setRole(resultSet.getString(6));
                users.setValid_user(resultSet.getBoolean(7));

                usersList.add(users);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return usersList;
    }

    @Override
    public Users getUserByIdUser(int id_user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT LOGIN,PASSWORD,MAGAZINE,FIO_USER,ROLE,VALID_USER" +
                " FROM users WHERE ID_USER=?";
        Users users = new Users();
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id_user);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.setLogin(resultSet.getString(1));
                users.setPassword(resultSet.getString(2));
                users.setMagazine(resultSet.getInt(3));
                users.setFio_user(resultSet.getString(4));
                users.setRole(resultSet.getString(5));
                users.setValid_user(resultSet.getBoolean(6));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return users;
    }

    @Override
    public void updateUser(Users update_user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE users SET " +
                "LOGIN=?,PASSWORD=?,MAGAZINE=?,FIO_USER=?,ROLE=?,VALID_USER=? WHERE ID_USER=?";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, update_user.getLogin());
            preparedStatement.setString(2, update_user.getPassword());
            preparedStatement.setInt(3, update_user.getMagazine());
            preparedStatement.setString(4, update_user.getFio_user());
            preparedStatement.setString(5, update_user.getRole());
            preparedStatement.setBoolean(6, update_user.getValid_user());
            preparedStatement.setInt(7, update_user.getId_user());

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public void deleteUser(int id_user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM users WHERE ID_USER=?;";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id_user);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public Users getRoleUser(String login_user, String password_user) {
        Users users = new Users();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM users WHERE LOGIN=? AND PASSWORD=?;";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login_user);
            preparedStatement.setString(2, password_user);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                users.setId_user(resultSet.getInt(1));
                users.setLogin(resultSet.getString(2));
                users.setPassword(resultSet.getString(3));
                users.setMagazine(resultSet.getInt(4));
                users.setFio_user(resultSet.getString(5));
                users.setRole(resultSet.getString(6));
                users.setValid_user(resultSet.getBoolean(7));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeConnection(connection);
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }
        return users;
    }

}
