package main.java.com.service;

import main.java.com.dao.BankAccountDAO;
import main.java.com.dao.DAOInterface;
import main.java.com.dao.TransactionDAO;
import main.java.com.entity.account.Account;
import main.java.com.entity.transaction.Transaction;

import javax.swing.text.html.Option;
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



    public List<Transaction> getTransactionsList(String clientId,String accountId){

        List<Transaction> dbTransactions = transactionDao.findAll();
        List<Account> dbAccounts = bankAccountDao.findAll();
        List<Transaction> filteredTransactions = new ArrayList<>();

        Optional<Account> account = dbAccounts.stream().filter(a -> a.getClientId().equals(clientId))
                .findFirst();
        if(account.isPresent() || !accountId.trim().isEmpty()){
            if(!clientId.trim().isEmpty()){
                filteredTransactions = dbTransactions.stream().
                        filter(t -> t.accountId().equals(accountId))
                        .toList();
        }
    }

        return filteredTransactions;

}
