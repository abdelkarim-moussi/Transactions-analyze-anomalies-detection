package main.java.com.entity.account;

import main.java.com.entity.enums.AccountType;

import java.math.BigDecimal;

final public class CurrentAccount extends Account {

    private BigDecimal authorizedOverdraft;

    public CurrentAccount() {
        this.setAccountType();
    }

    public CurrentAccount(String clientId,BigDecimal balance, BigDecimal authorizedOverdraft) {
        super(clientId,balance);
        this.authorizedOverdraft = authorizedOverdraft;
        this.setAccountType();
    }

    public BigDecimal getAuthorizedOverdraft() {
        return authorizedOverdraft;
    }
    public void setAuthorizedOverdraft(BigDecimal authorizedOverdraft) {
        this.authorizedOverdraft = authorizedOverdraft;
    }

    @Override
    public void setAccountType(){
        this.accountType = AccountType.current_account;
    }

    @Override
    public String toString() {
        return "Account Details :" +
                "\ncode = " + accountNumber +
                "\naccountType = " + accountType +
                "\nbalance = " + balance +
                "\noverdraft = " + authorizedOverdraft +
                "\n---------------------------------------\n";
    }

}
