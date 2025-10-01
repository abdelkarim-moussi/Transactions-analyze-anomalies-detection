import main.java.com.util.DataBaseConnection;

import java.sql.Connection;

public class Main {

    public static void main(String[] args){
       Connection con = DataBaseConnection.getConnection();
        System.out.println(con);
    }
}
