package com.library.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private final String DB_URL = "jdbc:mysql://localhost:3306/library";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "";

    private Connection connection;

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Connected to database successfully");
        } catch (SQLException sqe) {
            System.out.println("Error in connecting to database  ->" + sqe.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error in loading driver ->" + cnfe.getMessage());
        }
        return connection;
    }

    public boolean closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed successfully");
            return true;
        } catch (SQLException sqe) {
            System.out.println("Error in closing connection ->" + sqe.getMessage());
            return false;
        }
    }
}
