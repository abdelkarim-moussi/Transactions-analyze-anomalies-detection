package main.java.com.service;

import main.java.com.dao.BankAccountDAO;
import main.java.com.dao.ClientDAO;
import main.java.com.dao.DAOInterface;
import main.java.com.entity.account.Account;
import main.java.com.entity.client.Client;
import main.java.com.entity.enums.AccountType;
import main.java.com.factory.BankAccountFactory;
import main.java.com.factory.BankAccountFactoryProvider;
import main.java.com.factory.CurrentAccountFactory;
import main.java.com.factory.SavingAccountFactory;

import java.math.BigDecimal;
import java.util.Optional;

public class BankAccountService {

    private DAOInterface bankAccountDao;
    private DAOInterface clientDao;

    public BankAccountService(){
        bankAccountDao = new BankAccountDAO();
        clientDao = new ClientDAO();
    }

    public int createBankAccount(String clientId, BigDecimal balance,
                                 BigDecimal authorizedOverdraft,float interestRate,
                                 AccountType accountType){

        if(!clientId.trim().isEmpty()
        && balance.compareTo(BigDecimal.valueOf(0)) == 1
        && accountType != null){

            Optional<Client> dbClient = Optional.ofNullable((Client) clientDao.finById(clientId));
            if(dbClient == null) return 0;
            else {
                BankAccountFactory factory = BankAccountFactoryProvider.getFactory(accountType,authorizedOverdraft,interestRate);
                Account newCurrentAccount = factory.createAccount(clientId,balance);
                System.out.println(newCurrentAccount);

            }

        }

        return 0;
    }
}
