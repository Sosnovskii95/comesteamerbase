package ServiceLayer;

import Connection.ConnectionDB;
import DAOLayer.DAOSaleSumService;
import EntityLayer.SaleSum;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SaleSumService extends ConnectionDB implements DAOSaleSumService {

    @Override
    public int addNewSaleSum(SaleSum new_sale_sum) {
        int id_sale = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "INSERT INTO sale_sum " +
                "(ID_CARD, ID_USER, BUY_SUM, CASHBEK_SUM, GIFT_SUM, DATE_BUY, TIME_BUY,COMMENT, SPOSOB_OPLAT) " +
                "VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, new_sale_sum.getId_card());
            preparedStatement.setInt(2, new_sale_sum.getId_user());
            preparedStatement.setFloat(3, new_sale_sum.getBuy_sum());
            preparedStatement.setFloat(4, new_sale_sum.getCashbek_sum());
            preparedStatement.setFloat(5, new_sale_sum.getGift_sum());
            preparedStatement.setDate(6, new_sale_sum.getDate_byu());
            preparedStatement.setTime(7, new_sale_sum.getTime_buy());
            preparedStatement.setString(8, new_sale_sum.getComment());
            preparedStatement.setString(9, new_sale_sum.getSposob_oplat());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                id_sale = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }
        return id_sale;
    }

    @Override
    public List<SaleSum> getAllSum() {
        List<SaleSum> saleSumList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM sale_sum";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SaleSum saleSum = new SaleSum();
                saleSum.setId_sale(resultSet.getInt(1));
                saleSum.setId_card(resultSet.getInt(2));
                saleSum.setId_user(resultSet.getInt(3));
                saleSum.setBuy_sum(resultSet.getFloat(4));
                saleSum.setGift_sum(resultSet.getFloat(5));
                saleSum.setCashbek_sum(resultSet.getFloat(6));
                saleSum.setDate_byu(resultSet.getDate(7));
                saleSum.setComment(resultSet.getString(8));

                saleSumList.add(saleSum);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }
        return saleSumList;
    }

    @Override
    public List<SaleSum> getSumByIdCard(int id_card) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT ID_SALE,ID_USER,BUY_SUM,CASHBEK_SUM, GIFT_SUM,DATE_BUY,COMMENT" +
                " FROM sale_sum WHERE ID_CARD=?";
        List<SaleSum> saleSum = new ArrayList<>();
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id_card);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SaleSum sum = new SaleSum();

                sum.setId_sale(resultSet.getInt(1));
                sum.setId_user(resultSet.getInt(2));
                sum.setBuy_sum(resultSet.getFloat(3));
                sum.setCashbek_sum(resultSet.getFloat(4));
                sum.setGift_sum(resultSet.getFloat(5));
                sum.setDate_byu(resultSet.getDate(6));
                sum.setComment(resultSet.getString(7));

                saleSum.add(sum);

            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }
        return saleSum;
    }

    @Override
    public void updateSaleSum(SaleSum update_sale_sum) {
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
            preparedStatement.setDate(6, update_sale_sum.getDate_byu());
            preparedStatement.setString(7, update_sale_sum.getComment());
            preparedStatement.setString(8, update_sale_sum.getSposob_oplat());
            preparedStatement.setInt(9, update_sale_sum.getId_sale());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
            closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public void deleteListSaleSum(List<SaleSum> delete_all_sale_sum) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM sale_sum WHERE ID_SALE=?";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (SaleSum sum : delete_all_sale_sum
            ) {
                preparedStatement.setInt(1, sum.getId_sale());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
            closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public void deleteSaleSum(int id_sale) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM sale_sum WHERE ID_SALE=?";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id_sale);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
            closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public SaleSum getSumIdSale(int id_sale) {
        SaleSum sum = new SaleSum();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = "SELECT * FROM sale_sum WHERE ID_SALE=?";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id_sale);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                sum.setId_sale(resultSet.getInt(1));
                sum.setId_card(resultSet.getInt(2));
                sum.setId_user(resultSet.getInt(3));
                sum.setBuy_sum(resultSet.getFloat(4));
                sum.setCashbek_sum(resultSet.getFloat(5));
                sum.setGift_sum(resultSet.getFloat(6));
                sum.setDate_byu(resultSet.getDate(7));
                sum.setTime_buy(resultSet.getTime(8));
                sum.setComment(resultSet.getString(9));
                sum.setSposob_oplat(resultSet.getString(10));

            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }
        return sum;
    }

    @Override
    public SaleSum getDateLastBuy(int id_card) {
        SaleSum saleSum = new SaleSum();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT ID_USER, DATE_BUY FROM sale_sum WHERE time_buy=(SELECT max(time_buy) FROM sale_sum " +
                "WHERE DATE_BUY=(SELECT max(DATE_BUY) FROM sale_sum WHERE ID_CARD=?) AND ID_CARD=?) AND ID_CARD=?";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id_card);
            preparedStatement.setInt(2, id_card);
            preparedStatement.setInt(3, id_card);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                saleSum.setId_user(resultSet.getInt(1));
                saleSum.setDate_byu(resultSet.getDate(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }
        return saleSum;
    }


//    public List<SaleSum> getGiftBySum(String dateFrom, String dateBy, int id_card){
//        List<SaleSum> sumList = new ArrayList<>();
//        Connection connection = null;
//        PreparedStatement preparedStatement  = null;
//        ResultSet resultSet = null;
//        String sql = "SELECT ID_SALE, GIFT_SUM from sale_sum where STR_TO_DATE(?,'%d.%m')<=STR_TO_DATE(DATE_BUY,'%d.%m') " +
//                "and STR_TO_DATE(DATE_BUY,'%d.%m')<=STR_TO_DATE(?,'%d.%m') and sale_sum.ID_CARD=?;";
//        try {
//            connection = getConnection();
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, dateFrom);
//            preparedStatement.setString(2, dateBy);
//            preparedStatement.setInt(3, id_card);
//            resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()){
//                SaleSum saleSum = new SaleSum();
//
//                saleSum.setId_sale(resultSet.getInt(1));
//                saleSum.setGift_sum(resultSet.getFloat(2));
//
//                sumList.add(saleSum);
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }finally {
//            closePreparedStatement(preparedStatement);
//            closeConnection(connection);
//            closeResultSet(resultSet);
//        }
//        return sumList;
//    }


}
