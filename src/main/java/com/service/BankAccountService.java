package main.java.com.service;

import main.java.com.dao.BankAccountDAO;
import main.java.com.dao.ClientDAO;
import main.java.com.dao.DAOInterface;
import main.java.com.entity.account.Account;
import main.java.com.entity.client.Client;
import main.java.com.entity.enums.AccountType;
import main.java.com.factory.BankAccountFactory;
import main.java.com.factory.BankAccountFactoryProvider;

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

            Optional<Client> dbClient = Optional.ofNullable((Client) clientDao.findById(clientId));
            if(dbClient == null) return 0;
            else {
                BankAccountFactory factory = BankAccountFactoryProvider.getFactory(accountType,authorizedOverdraft,interestRate);
                Account newAccount = factory.createNewAccount(clientId,balance);
                System.out.println(newAccount.getAccountId());
                return bankAccountDao.create(newAccount);

            }

        }

        return 0;
    }

    public int updateBankAccount(String accountId,String clientId, BigDecimal balance,
                                 BigDecimal authorizedOverdraft,float interestRate,
                                 AccountType accountType){

        if(accountId.trim().isEmpty()) return 0;
        else {

            Optional<Account> dbAccount = Optional.ofNullable((Account) bankAccountDao.findById(accountId));

            if(dbAccount.isPresent()){
                if(!clientId.trim().isEmpty()
                        && balance.compareTo(BigDecimal.valueOf(0)) == 1
                        && accountType != null){

                    Optional<Client> dbClient = Optional.ofNullable((Client) clientDao.findById(clientId));
                    if(!dbClient.isPresent()) return 0;
                    else {
                        BankAccountFactory factory = BankAccountFactoryProvider.getFactory(accountType,authorizedOverdraft,interestRate);
                        Account newAccount = factory.createNewAccount(clientId,balance);
                        newAccount.setAccountId(accountId);
//                        System.out.println(dbAccount.get().getAccountNumber());
                        newAccount.setAccountNumber(dbAccount.get().getAccountNumber());
                        System.out.println(newAccount);
                        return bankAccountDao.update(newAccount);

                    }

                }
            }
        }

        return 0;

    }

    public int deleteBankAccount(String id){
        if(id.trim().isEmpty()) return 0;
        else {
            Optional<Account> dbAccount = Optional.ofNullable((Account) bankAccountDao.findById(id));

            if(dbAccount.isPresent()){
                return bankAccountDao.delete(id);
            }
        }

        return 0;
    }
}
