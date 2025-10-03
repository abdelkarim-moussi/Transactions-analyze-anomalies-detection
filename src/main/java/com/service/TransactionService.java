package main.java.com.service;

import main.java.com.dao.BankAccountDAO;
import main.java.com.dao.DAOInterface;
import main.java.com.dao.TransactionDAO;
import main.java.com.entity.account.Account;
import main.java.com.entity.enums.TransactionType;
import main.java.com.entity.transaction.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransactionService {

    private DAOInterface transactionDao;
    private DAOInterface bankAccountDao;

    public TransactionService(){
        transactionDao = new TransactionDAO();
        bankAccountDao = new BankAccountDAO();
    }

    public int makeTransaction(BigDecimal amount, TransactionType type,
                               String place, String accountId){
        if(amount.intValue() > 0 && type != null &&
                !place.trim().isEmpty() && !accountId.trim().isEmpty()){

            Transaction transaction = new Transaction(amount,type,place,accountId);

            var result = transactionDao.create(transaction);
            return result;

        }

        return 0;

    }

    public List<Transaction> getTransactionsByClient(String clientId){

        List<Transaction> dbTransactions = transactionDao.findAll();
        List<Account> dbAccounts = bankAccountDao.findAll();
        List<Transaction> filteredTransactions = new ArrayList<>();

        List<Account> clientAccounts = dbAccounts.stream().filter(a -> a.getClientId().equals(clientId))
                .toList();

        for (Account account : clientAccounts) {
            filteredTransactions.add(dbTransactions.stream().
                    filter(t -> t.accountId().equals(account.getAccountId()))
                    .findFirst().get());
        }

        return filteredTransactions;
    }

    public List<Transaction> getTransactionsByAccount(String accountId){

        List<Transaction> dbTransactions = transactionDao.findAll();
        List<Account> dbAccounts = bankAccountDao.findAll();
        List<Transaction> filteredTransactions = new ArrayList<>();


       if(!accountId.trim().isEmpty()) {
                filteredTransactions = dbTransactions.stream().
                        filter(t -> t.accountId().equals(accountId))
                        .toList();
        }

        return filteredTransactions;
    }

}
