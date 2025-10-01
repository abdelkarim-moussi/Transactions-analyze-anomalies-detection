package main.java.com.dao;

import main.java.com.entity.client.Client;
import main.java.com.util.DataBaseConnection;

import java.lang.ref.Cleaner;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ClientDAO implements DAOInterface<Client,String>{
private static Connection connection = DataBaseConnection.getConnection();
    @Override
    public int create(Client client){

        var insertSql = "INSERT INTO clients (id,number,email) VALUES (?, ?, ?)";
        if(client != null){
            try{
                var insertStatement = connection.prepareStatement(insertSql);
                insertStatement.setString(1,client.id());
                insertStatement.setString(2,client.number());
                insertStatement.setString(3,client.email());

                var resultSet = insertStatement.executeUpdate();
                return resultSet;

            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        return 0;
    }

    @Override
    public int delete(String id){
        return 0;
    }

    @Override
    public int update(String id,Client client){
        return 0;
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
