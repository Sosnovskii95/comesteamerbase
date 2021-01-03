package ServiceLayer;

import Connection.ConnectionDB;
import DAOLayer.DAOClientUsersDiscountCardService;
import EntityLayer.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientUsersDiscountCardService extends ConnectionDB implements DAOClientUsersDiscountCardService {


    @Override
    public List<ClientUsersDiscountCard> getListNewCard() {
        List<ClientUsersDiscountCard> discountCards = new ArrayList<>();

        String sql = "select client.ID_CLIENT, NUMBER_CARD,PROCENT_DISCONT, VALID_CARD, DATE_CREATE, name_m, " +
                "FIO_CLIENT, TELEPHONE, ALL_SUM, cashbek_sum, SMS from sumincard, discountcard, client, users, " +
                "magazine where discountcard.ID_CLIENT=client.ID_CLIENT and discountcard.ID_USER=users.ID_USER " +
                "and discountcard.ID_CARD=sumincard.ID_CARD and users.MAGAZINE=MAGAZINE.id_magazine AND " +
                "VALID_CARD=FALSE  ORDER BY NUMBER_CARD asc;";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ClientUsersDiscountCard card = new ClientUsersDiscountCard();

                card.setId_client(resultSet.getInt(1));
                card.setNumber_card(resultSet.getInt(2));
                card.setProcent_discont(resultSet.getFloat(3));
                card.setValid_card(resultSet.getBoolean(4));
                card.setDate_create(resultSet.getString(5));
                card.setMagazine(resultSet.getString(6));
                card.setFio_client(resultSet.getString(7));
                card.setTelephone(resultSet.getLong(8));
                card.setAll_sum(resultSet.getFloat(9));
                card.setCashbek_sum(resultSet.getFloat(10));
                card.setSms(resultSet.getBoolean(11));

                discountCards.add(card);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection(connection);
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }
        return discountCards;
    }

    @Override
    public List<ClientUsersDiscountCard> getListAllCard(int start, int finish){
        List<ClientUsersDiscountCard> discountCards = new ArrayList<>();
        String sql = "select client.ID_CLIENT ,NUMBER_CARD,PROCENT_DISCONT, VALID_CARD, DATE_CREATE, name_m, FIO_CLIENT, TELEPHONE, " +
                "ALL_SUM,cashbek_sum, SMS from sumincard, discountcard, client, users, magazine where(?<=NUMBER_CARD and NUMBER_CARD<=?)" +
                " and discountcard.ID_CLIENT=client.ID_CLIENT AND users.MAGAZINE=MAGAZINE.id_magazine " +
                "and discountcard.ID_USER=users.ID_USER and discountcard.ID_CARD=sumincard.ID_CARD ORDER BY NUMBER_CARD asc;";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, finish);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ClientUsersDiscountCard card = new ClientUsersDiscountCard();

                card.setId_client(resultSet.getInt(1));
                card.setNumber_card(resultSet.getInt(2));
                card.setProcent_discont(resultSet.getFloat(3));
                card.setValid_card(resultSet.getBoolean(4));
                card.setDate_create(resultSet.getString(5));
                card.setMagazine(resultSet.getString(6));
                card.setFio_client(resultSet.getString(7));
                card.setTelephone(resultSet.getLong(8));
                card.setAll_sum(resultSet.getFloat(9));
                card.setCashbek_sum(resultSet.getFloat(10));
                card.setSms(resultSet.getBoolean(11));

                discountCards.add(card);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection(connection);
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }
        return discountCards;
    }

    @Override
    public List<ClientUsersDiscountCard> getListAllCard(){
        List<ClientUsersDiscountCard> discountCards = new ArrayList<>();
        String sql = "select client.ID_CLIENT ,NUMBER_CARD,PROCENT_DISCONT, DATE_CREATE, name_m, FIO_CLIENT, TELEPHONE, " +
                "ALL_SUM, cashbek_sum, SMS from sumincard, discountcard, client, users, magazine where " +
                "discountcard.ID_CLIENT=client.ID_CLIENT " +
                "and discountcard.ID_USER=users.ID_USER and discountcard.ID_CARD=sumincard.ID_CARD " +
                "AND users.MAGAZINE=MAGAZINE.id_magazine ORDER BY NUMBER_CARD asc;";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ClientUsersDiscountCard card = new ClientUsersDiscountCard();

                card.setId_client(resultSet.getInt(1));
                card.setNumber_card(resultSet.getInt(2));
                card.setProcent_discont(resultSet.getFloat(3));
                card.setDate_create(resultSet.getString(4));
                card.setMagazine(resultSet.getString(5));
                card.setFio_client(resultSet.getString(6));
                card.setTelephone(resultSet.getLong(7));
                card.setAll_sum(resultSet.getFloat(8));
                card.setCashbek_sum(resultSet.getFloat(9));
                card.setSms(resultSet.getBoolean(10));

                discountCards.add(card);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection(connection);
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }
        return discountCards;
    }

    @Override
    public List<ClientUsersDiscountCard> getListProcent(float procent_from, float procent_to){
        List<ClientUsersDiscountCard> cards = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT NUMBER_CARD, PROCENT_DISCONT, FIO_CLIENT from discountcard,sumincard, client" +
                " where (?<=PROCENT_DISCONT and PROCENT_DISCONT<=?) and discountcard.ID_CLIENT=client.ID_CLIENT" +
                " and discountcard.ID_CARD=sumincard.ID_CARD;";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setFloat(1, procent_from);
            preparedStatement.setFloat(2, procent_to);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ClientUsersDiscountCard card = new ClientUsersDiscountCard();

                card.setNumber_card(resultSet.getInt(1));
                card.setProcent_discont(resultSet.getFloat(2));
                card.setFio_client(resultSet.getString(3));

                cards.add(card);
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

    @Override
    public List<ClientUsersDiscountCard> getListCardDateBorn(String date_from, String date_to){
        List<ClientUsersDiscountCard> cards = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT NUMBER_CARD, procent_discont, FIO_CLIENT, DATE_BORN FROM client, discountcard, sumincard " +
                "WHERE date_format(?, '%m %d')<=date_format(DATE_BORN, '%m %d') AND date_format(DATE_BORN, '%m %d')" +
                "<=date_format(?, '%m %d') AND client.ID_CLIENT=discountcard.ID_CLIENT AND " +
                "discountcard.ID_CARD=sumincard.ID_CARD ORDER BY date_format(DATE_BORN, '%m %d') ASC;";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, date_from);
            preparedStatement.setString(2, date_to);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                ClientUsersDiscountCard card = new ClientUsersDiscountCard();

                card.setNumber_card(resultSet.getInt(1));
                card.setProcent_discont(resultSet.getFloat(2));
                card.setFio_client(resultSet.getString(3));
                card.setDate_born(resultSet.getString(4));

                cards.add(card);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return cards;
    }

    @Override
    public List<ClientUsersDiscountCard> getListCardValid(boolean valid_card){
        List<ClientUsersDiscountCard> cards = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT NUMBER_CARD,PROCENT_DISCONT, FIO_CLIENT, VALID_CARD from client,discountcard,sumincard " +
                "where VALID_CARD=? and discountcard.ID_CLIENT=client.ID_CLIENT and discountcard.ID_CARD=sumincard.ID_CARD;";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, valid_card);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                ClientUsersDiscountCard card = new ClientUsersDiscountCard();

                card.setNumber_card(resultSet.getInt(1));
                card.setProcent_discont(resultSet.getFloat(2));
                card.setFio_client(resultSet.getString(3));
                card.setValid_card(resultSet.getBoolean(4));

                cards.add(card);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return cards;
    }

    @Override
    public List<ClientUsersDiscountCard> getListCardStatusClient(String status_client){
        List<ClientUsersDiscountCard> cards = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT NUMBER_CARD,PROCENT_DISCONT, FIO_CLIENT,STATUS_CLIENT from client,discountcard,sumincard " +
                "where STATUS_CLIENT=? and client.ID_CLIENT=discountcard.ID_CLIENT and discountcard.ID_CARD=sumincard.ID_CARD;";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, status_client);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                ClientUsersDiscountCard card = new ClientUsersDiscountCard();

                card.setNumber_card(resultSet.getInt(1));
                card.setProcent_discont(resultSet.getFloat(2));
                card.setFio_client(resultSet.getString(3));
                card.setStstus_client(resultSet.getString(4));

                cards.add(card);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return cards;
    }

    @Override
    public List<ClientUsersDiscountCard> getListCardSms(boolean valid_sms){
        List<ClientUsersDiscountCard> cards = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT NUMBER_CARD, TELEPHONE, SMS FROM client, discountcard" +
                " where SMS=? and client.ID_CLIENT=discountcard.ID_CLIENT;";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, valid_sms);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                ClientUsersDiscountCard card = new ClientUsersDiscountCard();

                card.setNumber_card(resultSet.getInt(1));
                card.setTelephone(resultSet.getLong(2));
                card.setSms(resultSet.getBoolean(3));

                cards.add(card);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return cards;
    }

    @Override
    public List<ClientUsersDiscountCard> getListCardByuCard(String status_card){
        List<ClientUsersDiscountCard> cards = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT NUMBER_CARD, FIO_CLIENT, STATUS_CARD FROM discountcard,client " +
                "where STATUS_CARD=? and discountcard.ID_CLIENT=client.ID_CLIENT;";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, status_card);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                ClientUsersDiscountCard card = new ClientUsersDiscountCard();

                card.setNumber_card(resultSet.getInt(1));
                card.setFio_client(resultSet.getString(2));
                card.setStatus_card(resultSet.getString(3));

                cards.add(card);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return cards;
    }

    @Override
    public List<ClientUsersDiscountCard> getListCardByDateAndOneUser(String date_from, String date_to, String magazine){
        List<ClientUsersDiscountCard> cards = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT NUMBER_CARD, FIO_CLIENT, DATE_BUY, name_m,TIME_BUY, FIO_USER, COMMENT,BUY_SUM, CASHBEK_SUM," +
                " GIFT_SUM FROM sale_sum, discountcard, client, users, magazine where (?<=DATE_BUY and DATE_BUY<=?)" +
                "and COMMENT IS NOT NULL AND name_m=? and sale_sum.ID_USER=users.ID_USER AND sale_sum.ID_CARD=discountcard.ID_CARD AND " +
                "discountcard.ID_CLIENT=client.ID_CLIENT AND users.MAGAZINE=MAGAZINE.id_magazine ORDER BY DATE_BUY ASC;";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, date_from);
            preparedStatement.setString(2, date_to);
            preparedStatement.setString(3, magazine);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                ClientUsersDiscountCard card = new ClientUsersDiscountCard();

                card.setNumber_card(resultSet.getInt(1));
                card.setFio_client(resultSet.getString(2));
                card.setDate_buy(resultSet.getString(3));
                card.setMagazine(resultSet.getString(4));
                card.setTime_buy(resultSet.getString(5));
                card.setFio_user(resultSet.getString(6));
                card.setComment(resultSet.getString(7));
                card.setBuy_sum(resultSet.getFloat(8));
                card.setCashbek_sum(resultSet.getFloat(9));
                card.setGift_sum(resultSet.getFloat(10));

                cards.add(card);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return cards;
    }

    @Override
    public List<ClientUsersDiscountCard> getListCardByDateAllUser(String date_from, String date_to){
        List<ClientUsersDiscountCard> cards = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT NUMBER_CARD, FIO_CLIENT, DATE_BUY, name_m, TIME_BUY, FIO_USER, COMMENT,BUY_SUM, " +
                "CASHBEK_SUM, GIFT_SUM FROM sale_sum, discountcard, client, users, magazine where ?<=DATE_BUY and DATE_BUY<=? " +
                "and COMMENT IS NOT NULL and sale_sum.ID_USER=users.ID_USER AND sale_sum.ID_CARD=discountcard.ID_CARD " +
                "AND discountcard.ID_CLIENT=client.ID_CLIENT AND users.MAGAZINE=magazine.id_magazine ORDER BY DATE_BUY ASC;";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, date_from);
            preparedStatement.setString(2, date_to);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                ClientUsersDiscountCard card = new ClientUsersDiscountCard();

                card.setNumber_card(resultSet.getInt(1));
                card.setFio_client(resultSet.getString(2));
                card.setDate_buy(resultSet.getString(3));
                card.setMagazine(resultSet.getString(4));
                card.setTime_buy(resultSet.getString(5));
                card.setFio_user(resultSet.getString(6));
                card.setComment(resultSet.getString(7));
                card.setBuy_sum(resultSet.getFloat(8));
                card.setCashbek_sum(resultSet.getFloat(9));
                card.setGift_sum(resultSet.getFloat(10));

                cards.add(card);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return cards;
    }

    @Override
    public List<ClientUsersDiscountCard> getListOldCard(){
        List<ClientUsersDiscountCard> cards = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT NUMBER_CARD, FIO_CLIENT, OLD_CARD from discountcard,client where OLD_CARD<>'' " +
                "and discountcard.ID_CLIENT=client.ID_CLIENT;";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                ClientUsersDiscountCard card = new ClientUsersDiscountCard();

                card.setNumber_card(resultSet.getInt(1));
                card.setFio_client(resultSet.getString(2));
                card.setOld_card(resultSet.getString(3));

                cards.add(card);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return cards;
    }

    @Override
    public List<ClientUsersDiscountCard> getCashBackIdCard(int id_card){
        List<ClientUsersDiscountCard> saleSums = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT BUY_SUM,CASHBEK_SUM, GIFT_SUM,DATE_BUY,COMMENT, name_m FROM users, sale_sum, magazine" +
                "WHERE ID_CARD=? AND cashbek_sum!=0 AND sale_sum.id_user=users.id_user AND users.MAGAZINE=MAGAZINE.id_magazine;";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id_card);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                ClientUsersDiscountCard sum = new ClientUsersDiscountCard();

                sum.setBuy_sum(resultSet.getFloat(1));
                sum.setCashbek_sum(resultSet.getFloat(2));
                sum.setGift_sum(resultSet.getFloat(3));
                sum.setDate_buy(resultSet.getString(4));
                sum.setComment(resultSet.getString(5));
                sum.setMagazine(resultSet.getString(6));

                saleSums.add(sum);
            }
        }catch (SQLException sqlex){
            sqlex.printStackTrace();
        }finally {
            closeConnection(connection);
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }

        return saleSums;
    }

    @Override
    public List<ClientUsersDiscountCard> getMoneyIdCard(int id_card){
        List<ClientUsersDiscountCard> saleSums = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT BUY_SUM,CASHBEK_SUM, DATE_BUY, name_m  FROM users, sale_sum, magazine" +
                " WHERE ID_CARD=? AND cashbek_sum=0 AND sale_sum.id_user=users.id_user and users.MAGAZINE=MAGAZINE.id_magazine;";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id_card);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                ClientUsersDiscountCard sum = new ClientUsersDiscountCard();

                sum.setBuy_sum(resultSet.getFloat(1));
                sum.setCashbek_sum(resultSet.getFloat(2));
                sum.setDate_buy(resultSet.getString(3));
                sum.setMagazine(resultSet.getString(4));

                saleSums.add(sum);
            }
        }catch (SQLException sqlex){
            sqlex.printStackTrace();
        }finally {
            closeConnection(connection);
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }

        return saleSums;
    }

    @Override
    public List<ClientUsersDiscountCard> getHistoryMoney(int id_user, String date_from, String date_to){
        List<ClientUsersDiscountCard> saleSums = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT DATE_BUY, time_buy, NUMBER_CARD, FIO_CLIENT, buy_sum, cashbek_sum, SPOSOB_OPLAT " +
                "FROM sale_sum, discountcard, client WHERE sale_sum.ID_USER=? AND ?<=DATE_BUY AND DATE_BUY<=? " +
                "AND COMMENT IS NULL AND SPOSOB_OPLAT IS NOT NULL AND sale_sum.ID_CARD=discountcard.ID_CARD " +
                "AND discountcard.ID_CLIENT=client.ID_CLIENT ORDER BY DATE_BUY DESC, time_buy DESC;";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id_user);
            preparedStatement.setString(2, date_from);
            preparedStatement.setString(3, date_to);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ClientUsersDiscountCard saleSum = new ClientUsersDiscountCard();

                saleSum.setDate_buy(resultSet.getString(1));
                saleSum.setTime_buy(resultSet.getString(2));
                saleSum.setNumber_card(resultSet.getInt(3));
                saleSum.setFio_client(resultSet.getString(4));
                saleSum.setBuy_sum(resultSet.getFloat(5));
                saleSum.setCashbek_sum(resultSet.getFloat(6));
                saleSum.setSposob_oplat(resultSet.getString(7));

                saleSums.add(saleSum);
            }

        }catch (SQLException sqlex){
            sqlex.printStackTrace();
        }finally {
            closeConnection(connection);
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }

        return saleSums;
    }

    @Override
    public List<ClientUsersDiscountCard> getHistotyCashbek(int id_user, String date_from, String date_to){
        List<ClientUsersDiscountCard> saleSums = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT DATE_BUY, time_buy, NUMBER_CARD, FIO_CLIENT, buy_sum, cashbek_sum,COMMENT, SPOSOB_OPLAT " +
                "FROM sale_sum, discountcard, client WHERE sale_sum.ID_USER=? AND ?<=DATE_BUY AND DATE_BUY<=? " +
                "AND COMMENT IS NOT NULL AND SPOSOB_OPLAT IS NOT NULL AND sale_sum.ID_CARD=discountcard.ID_CARD AND " +
                "discountcard.ID_CLIENT=client.ID_CLIENT ORDER BY DATE_BUY DESC, time_buy DESC;";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id_user);
            preparedStatement.setString(2, date_from);
            preparedStatement.setString(3, date_to);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ClientUsersDiscountCard saleSum = new ClientUsersDiscountCard();

                saleSum.setDate_buy(resultSet.getString(1));
                saleSum.setTime_buy(resultSet.getString(2));
                saleSum.setNumber_card(resultSet.getInt(3));
                saleSum.setFio_client(resultSet.getString(4));
                saleSum.setBuy_sum(resultSet.getFloat(5));
                saleSum.setCashbek_sum(resultSet.getFloat(6));
                saleSum.setComment(resultSet.getString(7));
                saleSum.setSposob_oplat(resultSet.getString(8));

                saleSums.add(saleSum);
            }

        }catch (SQLException sqlex){
            sqlex.printStackTrace();
        }finally {
            closeConnection(connection);
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }

        return saleSums;
    }

    @Override
    public List<ClientUsersDiscountCard> getHistoryMoneyIdCard(int id_card, String date_from, String date_to){
        List<ClientUsersDiscountCard> saleSums = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT ID_SALE,DATE_BUY,time_buy, name_m, buy_sum, cashbek_sum, SPOSOB_OPLAT  FROM sale_sum," +
                " users, magazine WHERE (sale_sum.ID_CARD=? AND ?<=DATE_BUY and DATE_BUY<=?)AND COMMENT IS NULL AND " +
                "sale_sum.ID_USER=users.ID_USER AND users.MAGAZINE=MAGAZINE.id_magazine ORDER BY DATE_BUY DESC, time_buy DESC;";
        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id_card);
            preparedStatement.setString(2, date_from);
            preparedStatement.setString(3, date_to);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                ClientUsersDiscountCard saleSum = new ClientUsersDiscountCard();

                saleSum.setId_sale(resultSet.getInt(1));
                saleSum.setDate_buy(resultSet.getString(2));
                saleSum.setTime_buy(resultSet.getString(3));
                saleSum.setMagazine(resultSet.getString(4));
                saleSum.setBuy_sum(resultSet.getFloat(5));
                saleSum.setCashbek_sum(resultSet.getFloat(6));
                saleSum.setSposob_oplat(resultSet.getString(7));

                saleSums.add(saleSum);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection(connection);
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }

        return saleSums;
    }

    @Override
    public List<ClientUsersDiscountCard> getHistoryCashbekIdCard(int id_card, String date_from, String date_to){
        List<ClientUsersDiscountCard> saleSums = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT ID_SALE, DATE_BUY,time_buy, name_m, buy_sum, cashbek_sum, COMMENT, SPOSOB_OPLAT " +
                "FROM sale_sum, users, magazine WHERE sale_sum.ID_CARD=? AND ?<=DATE_BUY and  DATE_BUY<=?" +
                " AND COMMENT IS NOT NULL AND sale_sum.ID_USER=users.ID_USER AND users.MAGAZINE=MAGAZINE.id_magazine " +
                "ORDER BY DATE_BUY DESC, time_buy DESC;";
        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id_card);
            preparedStatement.setString(2, date_from);
            preparedStatement.setString(3, date_to);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                ClientUsersDiscountCard saleSum = new ClientUsersDiscountCard();

                saleSum.setId_sale(resultSet.getInt(1));
                saleSum.setDate_buy(resultSet.getString(2));
                saleSum.setTime_buy(resultSet.getString(3));
                saleSum.setMagazine(resultSet.getString(4));
                saleSum.setBuy_sum(resultSet.getFloat(5));
                saleSum.setCashbek_sum(resultSet.getFloat(6));
                saleSum.setComment(resultSet.getString(7));
                saleSum.setSposob_oplat(resultSet.getString(8));

                saleSums.add(saleSum);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection(connection);
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }

        return saleSums;
    }

    @Override
    public List<ClientUsersDiscountCard> getReportDateCreateCard(String dateFrom, String dateTo) {
        List<ClientUsersDiscountCard> discountCards = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT NUMBER_CARD, TELEPHONE, SMS FROM client, discountcard WHERE SMS=TRUE AND ?<=DATE_CREATE " +
                "AND DATE_CREATE<=? AND discountcard.ID_CLIENT=client.ID_CLIENT;";
        try{
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, dateFrom);
            preparedStatement.setString(2, dateTo);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ClientUsersDiscountCard clientUsersDiscountCard = new ClientUsersDiscountCard();

                clientUsersDiscountCard.setNumber_card(resultSet.getInt(1));
                clientUsersDiscountCard.setTelephone(resultSet.getLong(2));
                clientUsersDiscountCard.setSms(resultSet.getBoolean(3));

                discountCards.add(clientUsersDiscountCard);
            }

        }catch (SQLException sqlEx){

        }finally {
            closeConnection(connection);
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }
        return discountCards;
    }
}
