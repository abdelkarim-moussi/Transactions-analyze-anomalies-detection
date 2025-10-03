package main.java.com.service;

import main.java.com.dao.ClientDAO;
import main.java.com.dao.DAOInterface;
import main.java.com.entity.client.Client;
import main.java.com.util.Validator;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public int updateClientAccount(String id,String newNumber,String newEmail){
        String validNewNumber = Validator.numberValidator(newNumber);
        String validNewEmail = Validator.emailValidator(newEmail);
        var result = 0;

        if(!id.trim().isEmpty()){
            Optional<Client> dbClient = (Optional.ofNullable((Client) daoInterface.findById(id)));
            if(dbClient.isPresent()){
                if(!validNewNumber.trim().isEmpty() && !validNewEmail.trim().isEmpty()){
                    Client client = new Client(dbClient.get().id(),validNewNumber,validNewEmail);
                    result = daoInterface.update(client);
                }
            }
        }

        return result;
    }

    public int deleteClientAccount(String id){
        var result = 0;
        if(!id.trim().isEmpty()){
            Optional<Client> dbClient = Optional.ofNullable((Client)daoInterface.findById(id));
            if(dbClient.isPresent()){
                result = daoInterface.delete(id);
            }
        }

        return result;
    }

    public List<Client>  getClientByIdOrName(String id, String email){

        List<Client> clients = daoInterface.findAll();
        List<Client> filtredClients = new ArrayList<>();

        if(!id.trim().isEmpty()){
             filtredClients = clients.stream().
                    filter(c1->c1.id().equals(id))
                     .toList();
        }else if(!email.trim().isEmpty()){
             filtredClients = clients.stream().filter(c1->c1.email().equals(email))
             .toList();
        }

        return filtredClients;
    }

    public List<Client> getAllClients(){
        List<Client> clients = daoInterface.findAll();
        List<Client> filteredClients = clients.stream().
                sorted((c1,c2)->c1.email().compareTo(c2.email()))
                .toList();
        return filteredClients;
    }
}
