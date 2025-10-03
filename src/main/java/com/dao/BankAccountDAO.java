package main.java.com.dao;

import main.java.com.entity.account.Account;
import main.java.com.entity.account.CurrentAccount;
import main.java.com.entity.account.SavingAccount;
import main.java.com.entity.enums.AccountType;
import main.java.com.factory.BankAccountFactory;
import main.java.com.factory.BankAccountFactoryProvider;
import main.java.com.util.DataBaseConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankAccountDAO implements DAOInterface<Account,String>{

    private static Connection connection = DataBaseConnection.getConnection();

    @Override
    public List<Account> findAll() {

        var findAllSql = "SELECT * FROM bankaccounts";
        List<Account> bankAccounts = new ArrayList<>();

        try(var findAllStatement = connection.createStatement()){

            var resultSet = findAllStatement.executeQuery(findAllSql);

            while(resultSet.next()){
                BankAccountFactory factory = BankAccountFactoryProvider.getFactory(
                        AccountType.valueOf(resultSet.getObject("type").toString()),
                        resultSet.getBigDecimal("authorizedwithdraft"),
                        resultSet.getFloat("interestRate")
                );
                Account account = factory.createAccountFromDb(
                        resultSet.getString("id"),
                        resultSet.getString("accountnumber"),
                        resultSet.getString("clientid"),
                        resultSet.getBigDecimal("balance")
                );

                bankAccounts.add(account);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return bankAccounts;
    }

    @Override
    public Account findById(String id) {
        if(!id.trim().isEmpty()){
            var findOneSql = "SELECT * FROM bankaccounts WHERE id = ?";
            try(var findOnePreparedStatement = connection.prepareStatement(findOneSql)){
                findOnePreparedStatement.setString(1,id);
                var resultSet = findOnePreparedStatement.executeQuery();
                if(resultSet.next()){
                    BankAccountFactory factory = BankAccountFactoryProvider.getFactory(
                            AccountType.valueOf(resultSet.getObject("type").toString()),
                            resultSet.getBigDecimal("authorizedoverdraft"),
                            resultSet.getFloat("interestrate")
                    );
                    Account account = factory.createAccountFromDb(resultSet.getString("id"),resultSet.getString("accountnumber"),resultSet.getString("clientid"),resultSet.getBigDecimal("balance"));
                    return account;
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public int create(Account account) {
        if(account != null){
            var insertSql = "INSERT INTO bankaccounts (id, clientid,accountnumber, balance,type, authorizedoverdraft, interestrate) " +
                    "VALUES(? , ?, ?, ?, ?, ?, ?)";
            try{

                var insertPreparedStatement = connection.prepareStatement(insertSql);
                insertPreparedStatement.setString(1,account.getAccountId());
                insertPreparedStatement.setString(2,account.getClientId());
                insertPreparedStatement.setString(3,account.getAccountNumber());
                insertPreparedStatement.setBigDecimal(4,account.getBalance());
                insertPreparedStatement.setObject(5,account.getAccountType(),Types.OTHER);
                if(account instanceof CurrentAccount currentAccount){
                    insertPreparedStatement.setBigDecimal(6,currentAccount.getAuthorizedOverdraft());
                }else insertPreparedStatement.setBigDecimal(6, BigDecimal.valueOf(0));

                if (account instanceof SavingAccount savingAccount){
                    insertPreparedStatement.setFloat(7,savingAccount.getInterestRate());
                }else insertPreparedStatement.setFloat(7,0);

                var rowResult = insertPreparedStatement.executeUpdate();
                insertPreparedStatement.close();
                return rowResult;

            }catch (SQLException e){
                e.printStackTrace();
            }

        }
        return 0;
    }

    @Override
    public int update(Account account) {
        if(account != null){
            var updateSql = "UPDATE bankaccounts SET clientid = ?, balance = ?,type = ?, authorizedoverdraft = ?, interestrate = ? WHERE id = ?";
            try{

                var updatePreparedStatement = connection.prepareStatement(updateSql);
                updatePreparedStatement.setString(1,account.getClientId());
                updatePreparedStatement.setBigDecimal(2,account.getBalance());
                updatePreparedStatement.setObject(3,account.getAccountType(),Types.OTHER);
                updatePreparedStatement.setString(6, account.getAccountId());
                if(account instanceof CurrentAccount currentAccount){
                    updatePreparedStatement.setBigDecimal(4,currentAccount.getAuthorizedOverdraft());
                }else updatePreparedStatement.setBigDecimal(4, BigDecimal.valueOf(0));

                if (account instanceof SavingAccount savingAccount){
                    updatePreparedStatement.setFloat(5,savingAccount.getInterestRate());
                }else updatePreparedStatement.setFloat(5,0);

                var rowResult = updatePreparedStatement.executeUpdate();
                updatePreparedStatement.close();
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
        else {
            var deleteSql = "DELETE FROM bankaccounts WHERE id = ?";
            try{
                var deletePreparedStatement = connection.prepareStatement(deleteSql);
                deletePreparedStatement.setString(1,id);
                var rowResult = deletePreparedStatement.executeUpdate();
                return rowResult;

            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return 0;
    }


}
