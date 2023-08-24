package com.library.app;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.models.Users;

public class UsersDAO {

    private Connection connection;

    public UsersDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean addUser(Users user) {
        try {
            String query = "{CALL AddUser(?, ?, ?, ?)}";
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setString(1, user.getFirstName());
            callableStatement.setString(2, user.getLastName());
            callableStatement.setString(3, user.getEmail());
            callableStatement.setString(4, user.getPhoneNumber());
            callableStatement.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Error adding user to database");
            e.printStackTrace();
            return false;
        }
    }

    public Users getUser(int userId) {
        try {
            String query = "{CALL displayUsers(?)}";
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setInt(1, userId);
            ResultSet resultSet = callableStatement.executeQuery();

            if (resultSet.next()) {
                return new Users(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5));
            }
        } catch (SQLException sqe) {
            System.out.println("Error getting user from database");
            sqe.printStackTrace();
        }

        return null;
    }

    public List<Users> getAllUsers() {
        try {
            String query = "{CALL displayAllUsers()}";
            CallableStatement callableStatement = connection.prepareCall(query);
            ResultSet resultSet = callableStatement.executeQuery();

            List<Users> users = new ArrayList<>();

            while (resultSet.next()) {
                users.add(new Users(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)));
            }

            return users;
        } catch (SQLException sqe) {
            System.out.println("Error getting users from database");
            sqe.printStackTrace();
        }

        return null;
    }

    public boolean updateUsers(Users user) {
        try {
            String query = "{CALL updateUsers(?, ?, ?, ?, ?)}";
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setString(1, user.getFirstName());
            callableStatement.setString(2, user.getLastName());
            callableStatement.setString(3, user.getEmail());
            callableStatement.setString(4, user.getPhoneNumber());
            callableStatement.setInt(5, user.getUserID());
            callableStatement.executeUpdate();
            return true;
        } catch (SQLException sqe) {
            System.out.println("Error updating user using stored procedure");
            sqe.printStackTrace();
            return false;
        }
    }

    public boolean deleteUsers(int userID) {
        try {
            String query = "{CALL deleteUsers(?)}";
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setInt(1, userID);
            callableStatement.executeUpdate();
            return true;
        } catch (SQLException sqe) {
            System.out.println("Error deleting user using stored procedure");
            sqe.printStackTrace();
            return false;
        }
    }

}
