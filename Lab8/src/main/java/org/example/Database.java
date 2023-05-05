package org.example;



import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class Database {
    private static final String URL = "jdbc:postgresql://localhost:5432/JavaLab8";
    private static final String USER = "postgres";
    private static final String PASSWORD = "teo";
    private static Connection connection = null;

    private static ComboPooledDataSource cpds;

    static {
        try {
            cpds = new ComboPooledDataSource();
            cpds.setDriverClass("org.postgresql.Driver");
            cpds.setJdbcUrl(URL);
            cpds.setUser(USER);
            cpds.setPassword(PASSWORD);
        } catch (Exception e) {
            System.err.println("Error creating connection pool: " + e.getMessage());
        }
    }

    public static Connection getConectionFromPool() throws SQLException {
       return cpds.getConnection();
    }

}