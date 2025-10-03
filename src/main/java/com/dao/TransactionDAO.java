package main.java.com.dao;

import main.java.com.entity.enums.TransactionType;
import main.java.com.entity.transaction.Transaction;
import main.java.com.util.DataBaseConnection;
import main.java.com.util.Helper;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TransactionDAO implements DAOInterface<Transaction,String>{
    private static Connection connection = DataBaseConnection.getConnection();
    @Override
    public List<Transaction> findAll() {

        List<Transaction> transactions =  new ArrayList<>();

        var findAllSql = "SELECT * FROM transactions";
        try(var findAllStatement = connection.createStatement()){
            var resultSet = findAllStatement.executeQuery(findAllSql);

            while(resultSet.next()){
                Transaction transaction = new Transaction(
                        resultSet.getString("id"),
                        resultSet.getTimestamp("date").toLocalDateTime(),
                        resultSet.getBigDecimal("amount"),
                        TransactionType.valueOf(resultSet.getObject("type").toString()),
                        resultSet.getString("place"),
                        resultSet.getString("accountid")
                );
                transactions.add(transaction);

            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return transactions;
    }

    @Override
    public Transaction findById(String id) {

        if(id.trim().isEmpty()) return null;
        else {
            var findOneSql = "SELECT * FROM transactions WHERE id = ?";

            try(var findOnePreparedStatement = connection.prepareStatement(findOneSql)){

                findOnePreparedStatement.setString(1,id);
                var resultSet = findOnePreparedStatement.executeQuery();

                if(resultSet.next()){
                    return new Transaction(resultSet.getString("id"),
                            resultSet.getTimestamp("date").toLocalDateTime(),
                            resultSet.getBigDecimal("amount"),
                            TransactionType.valueOf(resultSet.getObject("type").toString()),
                            resultSet.getString("place"),
                            resultSet.getString("accountid")
                    );
                }

            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public int update(Transaction transaction) {
        var updateSql = "UPDATE transactions SET date = ?, amount = ?, type = ?, place = ? WHERE id = ?";
        if(transaction != null){

            try(var updatePreparedStatement = connection.prepareStatement(updateSql)){

                updatePreparedStatement.setTimestamp(1, Helper.dateFormaterToDate(transaction.date()));
                updatePreparedStatement.setBigDecimal(2,transaction.amount());
                updatePreparedStatement.setObject(3, transaction.type(), Types.OTHER);
                updatePreparedStatement.setString(4,transaction.place());
                updatePreparedStatement.setString(5,transaction.transactionId());

                var rowResult = updatePreparedStatement.executeUpdate();
                return rowResult;

            }catch (SQLException e){
                e.printStackTrace();
            }

        }
        return 0;
    }

    @Override
    public int delete(String id) {
        if(id.trim().isEmpty()) return 0;
        else{
            var deleteSql = "DELETE FROM transactions WHERE id = ?";

            try(var deletePreparedStatement = connection.prepareStatement(deleteSql)){

                deletePreparedStatement.setString(1,id);
                var rowResult = deletePreparedStatement.executeUpdate();
                return rowResult;
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
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
