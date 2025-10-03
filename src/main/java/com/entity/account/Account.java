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

    public Account(String clientId, BigDecimal balance){
        this.accountNumber = Helper.generateCode("ACT-");
        this.accountId = UUID.randomUUID().toString().substring(0,10);;
        this.balance = balance;
        this.clientId = clientId;
    }

    public Account(String accountId,String accountNumber,String clientId, BigDecimal balance){
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.clientId = clientId;
        this.balance = balance;
    }


    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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

    public abstract void setAccountType(AccountType accountType);

    public String getAccountId(){
        return this.accountId;
    }

    public void setAccountId(String accountId){
        this.accountId = accountId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        clientId = clientId;
    }

}

