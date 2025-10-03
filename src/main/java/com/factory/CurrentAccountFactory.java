package main.java.com.factory;

import main.java.com.entity.account.Account;
import main.java.com.entity.account.CurrentAccount;
import main.java.com.entity.enums.AccountType;

import java.math.BigDecimal;

public class CurrentAccountFactory implements BankAccountFactory {
    final private BigDecimal authorizedOverdraft;

    public CurrentAccountFactory(BigDecimal authorizedOverdraft){
        this.authorizedOverdraft = authorizedOverdraft;
    }
    @Override
    public Account createNewAccount(String clientId, BigDecimal balance){
        return new CurrentAccount(clientId,balance,authorizedOverdraft);
    }
    @Override
    public Account createAccountFromDb(String accountId, String accountNumber, AccountType accountType, String clientId, BigDecimal balance){
        return new CurrentAccount(accountId, accountNumber,accountType,clientId,balance,authorizedOverdraft);
    }
}
