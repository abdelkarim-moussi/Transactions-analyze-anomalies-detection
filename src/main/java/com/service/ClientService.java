package main.java.com.service;

import main.java.com.entity.client.Client;
import main.java.com.utility.Validator;

public class ClientService {


    public Client createClientAccount(String number, String email){

        String validNumber = Validator.numberValidator(number);
        String validEmail = Validator.emailValidator(email);

        Client client = new Client(validNumber,validEmail);
        Client createdClient = DAOInterface.create(client);

        return createdClient;
    }

}
