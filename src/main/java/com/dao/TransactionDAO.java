package main.java.com.dao;

import main.java.com.entity.enums.TransactionType;
import main.java.com.entity.transaction.Transaction;
import main.java.com.util.DataBaseConnection;
import main.java.com.util.Helper;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class TransactionDAO implements DAOInterface<Transaction,String>{
    private static Connection connection = DataBaseConnection.getConnection();
    @Override
    public List<Transaction> findAll() {
        return List.of();
    }

    @Override
    public Transaction findById(String s) {
        return null;
    }

    @Override
    public int update(Transaction transaction) {
        return 0;
    }

    @Override
    public int delete(String s) {
        return 0;
    }

    @Override
    public int create(Transaction transaction) {

        var insertSql = "INSERT INTO transactions (id,date,amount,type,place,accountid) VALUES (?,?,?,?,?,?)";
        if(transaction != null){

            try(var insertPreparedStatement = connection.prepareStatement(insertSql)){

                insertPreparedStatement.setString(1,transaction.transactionId());
                insertPreparedStatement.setTimestamp(2, Helper.dateFormaterToDate(transaction.date()));
                insertPreparedStatement.setBigDecimal(3,transaction.amount());
                insertPreparedStatement.setObject(4, transaction.type(), Types.OTHER);
                insertPreparedStatement.setString(5,transaction.place());
                insertPreparedStatement.setString(6,transaction.accountId());

                var rowResult = insertPreparedStatement.executeUpdate();
                return rowResult;

            }catch (SQLException e){
                e.printStackTrace();
            }

        }
        return 0;
    }
}
