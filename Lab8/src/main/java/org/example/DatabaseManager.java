package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//clasa Singleton
public class DatabaseManager {

    private static DatabaseManager instance = null;
    private Connection connection;

    private DatabaseManager() throws SQLException {
        String url = "jdbc:postgresql://localhost/JavaLab8";
        String user = "postgres";
        String password = "teo";
        connection = DriverManager.getConnection(url, user, password);
    }

    public static DatabaseManager getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
