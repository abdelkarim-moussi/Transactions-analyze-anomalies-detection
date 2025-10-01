package main.java.com.test;

import main.java.com.service.ClientService;

public class Test {

    private static ClientService clientService = new ClientService();

    public static void test (){
        try{
        if(clientService.createClientAccount("+212612345679","example@gmail.com") == 1){
            System.out.println("client created succefully");
        }else System.out.println("couldn't create account");

        }catch (NullPointerException e){
            e.printStackTrace();
        }

    }
}
