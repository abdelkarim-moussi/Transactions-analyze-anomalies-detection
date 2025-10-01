package main.java.com.service;

import main.java.com.dao.ClientDAO;
import main.java.com.dao.DAOInterface;
import main.java.com.entity.client.Client;
import main.java.com.util.Validator;

public class ClientService {
    private final DAOInterface daoInterface;
    public ClientService(){
         daoInterface = new ClientDAO();
    }

    public int createClientAccount(String number, String email){

        String validNumber = Validator.numberValidator(number);
        String validEmail = Validator.emailValidator(email);
        var result = 0;

        if(!validNumber.trim().isEmpty() && !validEmail.trim().isEmpty()){

        Client client = new Client(validNumber,validEmail);
        result = daoInterface.create(client);
        }

        return result;
    }

}
