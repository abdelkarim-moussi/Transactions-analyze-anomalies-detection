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
                var rowResult = insertStatement.executeUpdate();
                insertStatement.close();
                return rowResult;

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
    public int update(Client client){

        var updateSql = "UPDATE clients SET number = ? , email = ? WHERE id = ?";
        if(client != null){
            try{
                var updatePreparedStatement = connection.prepareStatement(updateSql);
                updatePreparedStatement.setString(1,client.number());
                updatePreparedStatement.setString(2,client.email());
                updatePreparedStatement.setString(3,client.id());

                var rowResult = updatePreparedStatement.executeUpdate();
                updatePreparedStatement.close();
                return rowResult;

            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public Client finById(String id){
        Client client = null;
        var findOneSql = "SELECT * FROM clients WHERE id= ?";
        if(!id.trim().isEmpty()){

            try{
            var findOnePreparedStatement = connection.prepareStatement(findOneSql);
            findOnePreparedStatement.setString(1,id);
            var resultSet = findOnePreparedStatement.executeQuery();

            while(resultSet.next()){
                client = new Client(resultSet.getString("id"),
                        resultSet.getString("number"),
                        resultSet.getString("email"));
            }
            }catch (SQLException e){
                e.printStackTrace();
            }
            return client;

        }
        return null;
    }

    @Override
    public List<Client> finAll(String id){
        return null;
    }
}
