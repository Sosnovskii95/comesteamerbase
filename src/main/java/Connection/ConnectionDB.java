package Connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class ConnectionDB {

    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

    public Connection getConnection(){
        Connection connection = null;

        String db_host = "";//URL к БД
        String db_user = "";//логин
        String db_password = "";//пароль

        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            try {
                //Считывание данных из конфига

                inputStream =
                        getClass().getResourceAsStream("/Config/db_config.properties");
                properties.load(inputStream);

                //Задание параметров из конфига
                db_host = properties.getProperty("db.host");
                db_user = properties.getProperty("db.username");
                db_password = properties.getProperty("db.password");

            }catch (IOException e){
                e.printStackTrace();
            }finally {
                try {
                    if (inputStream != null)
                    {
                        inputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //загружаем драйвер БД
            Class.forName(DB_DRIVER);
            //Создаем подключение
            connection = DriverManager.getConnection(db_host, db_user, db_password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    public void closeConnection(Connection connection){
        try {
            if (connection != null)
            {
                connection.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void closeResultSet(ResultSet resultSet){
        try {
            if (resultSet != null)
            {
                resultSet.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void closePreparedStatement(PreparedStatement preparedStatement){
        try {
            if (preparedStatement != null)
            {
                preparedStatement.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void closeStatement(Statement statement){
        try {
            if (statement != null)
            {
                statement.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
