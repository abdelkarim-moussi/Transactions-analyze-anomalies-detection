package main.java.com.util;
import java.sql.Connection;
import java.sql.SQLException;
import org.postgresql.ds.PGConnectionPoolDataSource;

public class DataBaseConnection {

    private static PGConnectionPoolDataSource source;

    static {

            String dbName = "transanalyze";
            String user = "postgres";
            String password = "moussi@25";

            source = new PGConnectionPoolDataSource();
            source.setServerNames(new String[] {"localhost"});
            source.setDatabaseName(dbName);
            source.setUser(user);
            source.setPassword(password);

    }

    public static Connection getConnection() {
        try{
            return source.getConnection();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
