package main.java.com.entity.account;

import main.java.com.entity.enums.AccountType;

import java.math.BigDecimal;

final public class SavingAccount extends Account {

    private float interestRate;

    public SavingAccount() {
        this.setAccountType();
    }

    public SavingAccount(String clientId,BigDecimal balance, float interestRate){
        super(clientId,balance);
        this.interestRate = interestRate;
        this.setAccountType();
    }

    public float getInterestRate(){
        return this.interestRate;
    }

    public void setInterestRate(float interestRate){
        this.interestRate = interestRate;
    }

    @Override
    public void setAccountType(){
        this.accountType = AccountType.saving_account;
    }

    @Override
    public String toString() {
        return "Account Details :" +
                "\ncode = " + accountNumber +
                "\naccountType = " + accountType +
                "\nbalance = " + balance +
                "\ninterestRate = " + interestRate +
                "\n------------------------------------\n";
    }

}
