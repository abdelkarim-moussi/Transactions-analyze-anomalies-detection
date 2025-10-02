package main.java.com.entity.account;

import main.java.com.entity.enums.AccountType;

import java.math.BigDecimal;

final public class CurrentAccount extends Account {

    private BigDecimal authorizedOverdraft;

    public CurrentAccount(String accountId,String accountNumber,String clientId, BigDecimal balance, BigDecimal authorizedOverdraft) {
        super(accountId,accountNumber,clientId,balance);
        this.authorizedOverdraft = authorizedOverdraft;
    }

    public CurrentAccount(String clientId,BigDecimal balance, BigDecimal authorizedOverdraft) {
        super(clientId,balance);
        this.authorizedOverdraft = authorizedOverdraft;
        this.accountType = AccountType.current_account;
    }

    public BigDecimal getAuthorizedOverdraft() {
        return authorizedOverdraft;
    }
    public void setAuthorizedOverdraft(BigDecimal authorizedOverdraft) {
        this.authorizedOverdraft = authorizedOverdraft;
    }

    @Override
    public void setAccountType(AccountType accountType){
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return "Account Details :" +
                "\nid = "+ this.getAccountId()+
                "\ncode = " + accountNumber +
                "\naccountType = " + accountType +
                "\nbalance = " + balance +
                "\noverdraft = " + authorizedOverdraft +
                "\n---------------------------------------\n";
    }

}
