package main.java.com.factory;

import main.java.com.entity.account.Account;
import main.java.com.entity.account.SavingAccount;
import main.java.com.entity.enums.AccountType;

import java.math.BigDecimal;

public class SavingAccountFactory implements BankAccountFactory {

    final private float interestRate;
    public SavingAccountFactory(float interestRate){
        this.interestRate = interestRate;
    }

    @Override
    public Account createNewAccount(String clientId, BigDecimal balance){
        return new SavingAccount(clientId,balance,interestRate);
    }
    @Override
    public Account createAccountFromDb(String accountId, String accountNumber, AccountType accountType, String clientId, BigDecimal balance){
        return new SavingAccount(accountId,accountNumber,accountType,clientId,balance,interestRate);
    }
}
