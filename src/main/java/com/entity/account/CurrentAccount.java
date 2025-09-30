package main.java.com.entity.account;

import main.java.com.entity.enums.AccountType;

final public class CurrentAccount extends Account {

    private double authorizedOverdraft;

    public CurrentAccount() {
        this.setAccountType();
    }

    public CurrentAccount(double balance, double authorizedOverdraft) {
        super(balance);
        this.authorizedOverdraft = authorizedOverdraft;
        this.setAccountType();
    }

    public double getAuthorizedOverdraft() {
        return authorizedOverdraft;
    }
    public void setAuthorizedOverdraft(double authorizedOverdraft) {
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
