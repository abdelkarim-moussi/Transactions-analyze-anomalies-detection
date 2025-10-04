package main.java.com.service;

import main.java.com.dao.BankAccountDAO;
import main.java.com.dao.DAOInterface;
import main.java.com.dao.TransactionDAO;
import main.java.com.entity.account.Account;
import main.java.com.entity.enums.TransactionType;
import main.java.com.entity.transaction.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
                        .sorted((t1,t2) -> t1.date().compareTo(t2.date()))
                        .toList();
        }

        return filteredTransactions;
    }

    public List<Transaction> getFilteredTransactions(BigDecimal amount, TransactionType transactionType,
                                                     LocalDate date, String place){
        List<Transaction> transactions = transactionDao.findAll();
        List<Transaction> filteredTransactions = new ArrayList<>();

        if(amount.compareTo(BigDecimal.valueOf(0)) == 1){
            filteredTransactions = transactions.stream().
                filter(t -> t.amount().equals(amount)).toList();
        }
        if(transactionType != null){
            filteredTransactions = transactions.stream().
                    filter(t -> t.type().equals(transactionType)).toList();
        }
        if (date != null){
            filteredTransactions = transactions.stream().
                    filter(t -> t.date().toLocalDate().isEqual(date)).toList();
        }

        if(!place.isEmpty()){
            filteredTransactions = transactions.stream().
                    filter(t -> t.place().equals(place)).toList();
        }


        return filteredTransactions;

    }

}
