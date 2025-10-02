package main.java.com.factory;

import main.java.com.entity.account.Account;

import java.math.BigDecimal;

public interface BankAccountFactory {
    public abstract Account createNewAccount(String clientId, BigDecimal balance);
    public abstract Account createAccountFromDb(String accountId, String accountNumber,String clientId, BigDecimal balance);
}
