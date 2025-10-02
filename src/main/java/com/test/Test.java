package main.java.com.test;

import main.java.com.entity.enums.AccountType;
import main.java.com.service.BankAccountService;
import main.java.com.service.ClientService;

import java.math.BigDecimal;
import java.security.DigestException;

public class Test {

    private static ClientService clientService = new ClientService();
    private static BankAccountService bankAccountService = new BankAccountService();

    public static void createClientTest (){
        try{
            if(clientService.createClientAccount("+212612345679","example@gmail.com") == 1){
                System.out.println("client created succefully");
            }else System.out.println("couldn't create account");

        }catch (NullPointerException e){
            e.printStackTrace();
        }

    }

    public static void updateClientTest(){
        try{
            if(clientService.updateClientAccount("7e9a4887-0","+212612345678","email@gmail.com") == 1){
                System.out.println("client updated succefully");
            }else System.out.println("couldn't update account");

        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public static void deleteClientTest(){
        try {
            var res = clientService.deleteClientAccount("7e9a4887-0");
            if(res > 0){
                System.out.println("client deleted succefully");
            }else System.out.println("couldn't delete client");
        }catch(NullPointerException e){
            e.printStackTrace();
        }
    }

    //bank account

    public static void createAccount(){

        try {
            int res = bankAccountService.createBankAccount("030d20ef-1", BigDecimal.valueOf(1000),
                    BigDecimal.valueOf(0),0, AccountType.saving_account);
            if(res > 0){
                System.out.println("row inserted successfully");
            }
            else System.out.println("couldn't insert row");

        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public static void updateAccount(){

        try {
            int res = bankAccountService.updateBankAccount("ad432a85-8","030d20ef-1", BigDecimal.valueOf(1000),
                    BigDecimal.valueOf(0),0, AccountType.current_account);
            if(res > 0){
                System.out.println("row inserted successfully");
            }
            else System.out.println("couldn't insert row");

        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }
}
