package main.java.com.factory;

import main.java.com.entity.account.Account;

import java.math.BigDecimal;

public interface BankAccountFactory {
    public abstract Account createAccount(String clientId, BigDecimal balance);
}
