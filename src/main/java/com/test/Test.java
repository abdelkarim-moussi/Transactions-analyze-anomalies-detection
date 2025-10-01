package main.java.com.test;

import main.java.com.service.ClientService;

public class Test {

    private static ClientService clientService = new ClientService();

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
}
