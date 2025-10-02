package main.java.com.factory;

import main.java.com.entity.account.Account;
import main.java.com.entity.account.CurrentAccount;

import java.math.BigDecimal;

public class CurrentAccountFactory implements BankAccountFactory {
    final private BigDecimal authorizedOverdraft;

    public CurrentAccountFactory(BigDecimal authorizedOverdraft){
        this.authorizedOverdraft = authorizedOverdraft;
    }
    @Override
    public Account createAccount(String clientId, BigDecimal balance){
        return new CurrentAccount(clientId,balance,authorizedOverdraft);
    }
}
