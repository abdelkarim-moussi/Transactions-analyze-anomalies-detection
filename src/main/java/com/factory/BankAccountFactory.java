package main.java.com.factory;

import main.java.com.entity.account.Account;

public abstract class BankAccountFactory {

    public Account create(){
        Account account = createAccount();
        return account;
    }

    protected abstract Account createAccount();
}
