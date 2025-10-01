package main.java.com.service;

import main.java.com.dao.BankAccountDAO;
import main.java.com.dao.DAOInterface;
import main.java.com.entity.account.Account;
import main.java.com.entity.client.Client;
import main.java.com.entity.enums.AccountType;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.Optional;

public class BankAccountService {

    private DAOInterface daoInterface;

    public BankAccountService(){
        daoInterface = new BankAccountDAO();
    }

    public int createBankAccount(String clientId, BigDecimal balance, AccountType accountType){
        if(!clientId.trim().isEmpty()
        &&balance.compareTo(BigDecimal.valueOf(0)) == 1
        && accountType != null){
            Optional<Client> dbClient = Optional.ofNullable((Client) daoInterface.finById(clientId));
            if(dbClient.isPresent()){
                Account newAccount =
            }
        }
    }
}
