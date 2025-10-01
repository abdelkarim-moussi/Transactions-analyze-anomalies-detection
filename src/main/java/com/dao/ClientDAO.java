package main.java.com.dao;

import main.java.com.entity.client.Client;

import java.lang.ref.Cleaner;
import java.util.List;

public class ClientDAO implements DAOInterface<Client,String>{

    @Override
    public Client create(Client client){
        if(client != null){

        }
        return client;
    }

    @Override
    public int delete(String id){
        return 0;
    }

    @Override
    public Client update(String id,Client client){
        return client;
    }

    @Override
    public Client finById(String id){
        return null;
    }

    @Override
    public List<Client> finAll(String id){
        return null;
    }
}
