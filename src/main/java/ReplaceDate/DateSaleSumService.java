package ReplaceDate;

import Connection.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DateSaleSumService extends ConnectionDB{

    public List<DateSaleSum> getAllSum() {
        List<DateSaleSum> saleSumList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM sale_sum";
        try{
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                DateSaleSum saleSum = new DateSaleSum();
                saleSum.setId_sale(resultSet.getInt(1));
                saleSum.setId_card(resultSet.getInt(2));
                saleSum.setId_user(resultSet.getInt(3));
                saleSum.setBuy_sum(resultSet.getFloat(4));
                saleSum.setCashbek_sum(resultSet.getFloat(5));
                saleSum.setGift_sum(resultSet.getFloat(6));
                saleSum.setDate_byu(resultSet.getString(7));
                saleSum.setTime_buy(resultSet.getString(8));
                saleSum.setComment(resultSet.getString(9));
                saleSum.setSposob_oplat(resultSet.getString(10));

                saleSumList.add(saleSum);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection(connection);
            closeStatement(statement);
            closeResultSet(resultSet);
        }
        return saleSumList;
    }

    public void updateSaleSum(DateSaleSum update_sale_sum) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE sale_sum SET " +
                "ID_CARD=?,ID_USER=?,BUY_SUM=?,CASHBEK_SUM=?,GIFT_SUM=?,DATE_BUY=?,COMMENT=?, SPOSOB_OPLAT=? WHERE ID_SALE=?";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, update_sale_sum.getId_card());
            preparedStatement.setInt(2, update_sale_sum.getId_user());
            preparedStatement.setFloat(3, update_sale_sum.getBuy_sum());
            preparedStatement.setFloat(4, update_sale_sum.getCashbek_sum());
            preparedStatement.setFloat(5, update_sale_sum.getGift_sum());
            preparedStatement.setString(6, update_sale_sum.getDate_byu());
            preparedStatement.setString(7, update_sale_sum.getComment());
            preparedStatement.setString(8, update_sale_sum.getSposob_oplat());
            preparedStatement.setInt(9, update_sale_sum.getId_sale());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection(connection);
            closePreparedStatement(preparedStatement);
        }
    }
}
