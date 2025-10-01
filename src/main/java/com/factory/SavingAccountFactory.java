package main.java.com.factory;

import main.java.com.entity.account.Account;
import main.java.com.entity.account.SavingAccount;

public class SavingAccountFactory extends BankAccountFactory{

    @Override
    public Account createAccount(){
        return new SavingAccount();
    }
}
