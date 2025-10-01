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

    public Client createClientAccount(String number, String email){

        String validNumber = Validator.numberValidator(number);
        String validEmail = Validator.emailValidator(email);

        Client client = new Client(validNumber,validEmail);
        Client createdClient = (Client) daoInterface.create(client);

        return createdClient;
    }

}
