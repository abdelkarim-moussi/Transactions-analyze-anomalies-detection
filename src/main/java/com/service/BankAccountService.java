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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public Map<String,Account> getAllBankAccounts(){

        List<Account> dbAccounts = bankAccountDao.findAll();
        Map<String,Account> accounts = new HashMap<>();

        for (Account account : dbAccounts) {
            accounts.put(account.getAccountId(),account);
        }

        return accounts;
    }

    public Map<String,Account> getAccountsByNumberOrClient(String accountNumber,String clientId){

        List<Account> dbAccounts = bankAccountDao.findAll();
        Map<String,Account> accountsMap = new HashMap<>();

        if(!accountNumber.trim().isEmpty()){
            accountsMap = dbAccounts.stream().
                    filter(a->a.getAccountNumber().equals(accountNumber))
                    .collect(Collectors.toMap(Account::getAccountId, Function.identity()));
        }else if(!clientId.trim().isEmpty()){
            accountsMap = dbAccounts.stream().
                    filter(a->a.getClientId().equals(clientId))
                    .collect(Collectors.toMap(Account::getAccountId, Function.identity()));

        }

        return accountsMap;

    }

    public Map<String,Account> getAccountWithMaxAndMinBalance(){
        List<Account> dbAccounts = bankAccountDao.findAll();
        Map<String,Account> maxMinAccounts = new HashMap<>();

            Optional<Account> accountWithMaxBalance = dbAccounts.stream().
                    max((ac1,ac2)-> ac1.getBalance().compareTo(ac2.getBalance()))
                    .stream().findFirst();

            Optional<Account> accountWithMinBalance = dbAccounts.stream().
                    min((ac1,ac2)-> ac1.getBalance().compareTo(ac2.getBalance()))
                    .stream().findFirst();

        accountWithMinBalance.ifPresent(account -> maxMinAccounts.put("MinBalanceAccount", account));
        accountWithMaxBalance.ifPresent(account -> maxMinAccounts.put("MaxBalanceAccount", account));

        return maxMinAccounts;
    }
}
