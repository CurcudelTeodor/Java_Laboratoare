package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:postgresql://localhost:5432/JavaLab11";
    private static final String USER = "postgres";
    private static final String PASSWORD = "teo";
    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        createConnection();
        return connection;
    }
    private static void createConnection() {
        try {
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            //connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
    public static void closeConnection() throws SQLException {
        if (connection!=null) {
            connection.close();
        }
    }
}