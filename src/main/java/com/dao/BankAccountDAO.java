package main.java.com.dao;

import main.java.com.entity.account.Account;
import main.java.com.entity.client.Client;
import main.java.com.util.DataBaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BankAccountDAO implements DAOInterface<Account,String>{

    private static Connection connection = DataBaseConnection.getConnection();

    @Override
    public List<Account> findAll() {
        return List.of();
    }

    @Override
    public Account finById(String id) {
        return null;
    }

    @Override
    public int update(Account account) {
        return 0;
    }

    @Override
    public int create(Account account) {
        if(account != null){
            var insertSql = "INSERT INTO bankaccounts (id, clientid, balance, authorizedoverdraft, interestrate) " +
                    "VALUES(? , ?, ?, ?, ?)";
            try{
                var insertPreparedStatement = connection.prepareStatement(insertSql);
                insertPreparedStatement.setString(1,account.getAccountId());
                insertPreparedStatement.setString(2,account.getClientId());
                insertPreparedStatement.setBigDecimal(3,account.getBalance());
//                insertPreparedStatement.setBigDecimal();

            }catch (SQLException e){
                e.printStackTrace();
            }

        }
        return 0;
    }

    @Override
    public int delete(String id) {
        return 0;
    }


}
