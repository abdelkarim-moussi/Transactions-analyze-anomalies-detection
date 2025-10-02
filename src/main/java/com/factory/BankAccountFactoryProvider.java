package main.java.com.factory;

import main.java.com.entity.enums.AccountType;

import java.math.BigDecimal;

public class BankAccountFactoryProvider {

    public static BankAccountFactory getFactory(AccountType accountType,
                                                BigDecimal authorizedOverdraft, float interestRate){
        return switch (accountType){
            case current_account -> new CurrentAccountFactory(authorizedOverdraft);
            case saving_account -> new SavingAccountFactory(interestRate);
        };

    }
}
