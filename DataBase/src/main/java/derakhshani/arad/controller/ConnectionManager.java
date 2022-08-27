package derakhshani.arad.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final String server = "localhost";
    private static final String port = "5432";
    private static final String userName = "postgres";
    private static final String password = "1234";
    private static final String dbName = "schooldb";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://" + server + ":" + port + "/" + dbName;
        return DriverManager.getConnection(url,userName,password);
    }
}
