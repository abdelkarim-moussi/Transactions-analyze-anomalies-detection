package main.java.com.entity.account;

import main.java.com.entity.enums.AccountType;
import main.java.com.util.Helper;

import java.math.BigDecimal;
import java.util.UUID;

public sealed abstract class Account permits CurrentAccount , SavingAccount {

    protected String accountId;
    protected String accountNumber;
    protected BigDecimal balance = BigDecimal.valueOf(0);
    protected String clientId;
    protected AccountType accountType;

    public Account(){
        this.setAccountNumber();
    }

    public Account(String clientId, BigDecimal balance){
        this.setAccountNumber();
        this.balance = balance;
        this.clientId = clientId;
    }



    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber() {
        this.accountNumber = Helper.generateCode("ACT-");
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public abstract void setAccountType();

    public String getAccountId(){
        return this.accountId;
    }

    public void setAccountId(){
        this.accountId = UUID.randomUUID().toString().substring(0,10);
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        clientId = clientId;
    }

}

