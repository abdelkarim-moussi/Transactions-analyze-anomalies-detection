package main.java.com.entity.account;

import main.java.com.entity.enums.AccountType;

import java.math.BigDecimal;

final public class SavingAccount extends Account {

    private float interestRate;

    public SavingAccount(String accountId,String accountNumber,AccountType accountType,String clientId, BigDecimal balance,float interestRate) {
        super(accountId,accountNumber,clientId,balance);
        this.interestRate = interestRate;
        this.accountType = accountType;
    }

    public SavingAccount(String clientId,BigDecimal balance, float interestRate){
        super(clientId,balance);
        this.interestRate = interestRate;
        this.accountType = AccountType.saving_account;
    }

    public float getInterestRate(){
        return this.interestRate;
    }

    public void setInterestRate(float interestRate){
        this.interestRate = interestRate;
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
                "\ninterestRate = " + interestRate +
                "\n------------------------------------\n";
    }

}
