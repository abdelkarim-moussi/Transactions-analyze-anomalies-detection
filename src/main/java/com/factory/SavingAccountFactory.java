package main.java.com.factory;

import main.java.com.entity.account.Account;
import main.java.com.entity.account.SavingAccount;

import java.math.BigDecimal;

public class SavingAccountFactory implements BankAccountFactory {

    final private float interestRate;
    public SavingAccountFactory(float interestRate){
        this.interestRate = interestRate;
    }

    @Override
    public Account createAccount(String clientId, BigDecimal balance){
        return new SavingAccount(clientId,balance,interestRate);
    }
}
