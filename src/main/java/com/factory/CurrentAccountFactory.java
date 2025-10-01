package main.java.com.factory;

import main.java.com.entity.account.Account;
import main.java.com.entity.account.CurrentAccount;

public class CurrentAccountFactory extends BankAccountFactory{
    @Override
    public Account createAccount(){
        return new CurrentAccount();
    }
}
