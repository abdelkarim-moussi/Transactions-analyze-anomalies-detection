package main.java.com.business.account;

import main.java.com.business.enums.AccountType;
import main.java.com.utility.Helper;

import java.util.UUID;

public sealed abstract class Account permits CurrentAccount , SavingAccount {

    protected String accountId;
    protected String accountNumber;
    protected double balance = 0;
    protected String ClientId;
    protected AccountType accountType;

    Account(){
        this.setAccountNumber();
    }

    Account(double balance){
        this.setAccountNumber();
        this.balance = balance;
    }
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber() {
        this.accountNumber = Helper.generateCode("ACT-");
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
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
        return ClientId;
    }

    public void setClientId(String clientId) {
        ClientId = clientId;
    }

}

