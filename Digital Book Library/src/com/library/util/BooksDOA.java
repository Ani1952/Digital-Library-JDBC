package com.library.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.models.Books;

public class BooksDOA {

    private Connection connection;

    public BooksDOA(Connection connection) {
        this.connection = connection;
    }

    public boolean addBook(Books book) {
        try {
            String query = "{CALL addBook(?,?, ?, ?, ?, ?, ?)}";
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setString(1, book.getBookId());
            callableStatement.setString(2, book.getTitle());
            callableStatement.setString(3, book.getAuthor());
            callableStatement.setString(4, book.getGenre());
            callableStatement.setDate(5, book.getPublicationDate());
            callableStatement.setBoolean(6, book.isAvailable());
            callableStatement.setInt(7, book.getCount());
            callableStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error adding book to database");
            e.printStackTrace();
            return false;
        }
    }

    public Books getBook(String bookId) {
        try {
            String query = "{CALL displayBook(?)}";
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setString(1, bookId);
            ResultSet resultSet = callableStatement.executeQuery();

            if (resultSet.next()) {
                return new Books(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDate(5),
                        resultSet.getBoolean(6),
                        resultSet.getInt(7));
            }
        } catch (SQLException sqe) {
            System.out.println("Error getting book from database");
            sqe.printStackTrace();
        }

        return null;
    }

    public List<Books> getAllBooks() {
        try {
            String query = "{CALL displayAllBooks()}";
            CallableStatement callableStatement = connection.prepareCall(query);
            ResultSet resultSet = callableStatement.executeQuery();

            List<Books> books = new ArrayList<>();
            while (resultSet.next()) {
                books.add(new Books(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDate(5),
                        resultSet.getBoolean(6),
                        resultSet.getInt(7)));
            }
            return books;
        } catch (SQLException sqe) {
            System.out.println("Error getting books from database");
            sqe.printStackTrace();
        }

        return null;
    }

    public boolean updateBook(Books book) {
        try {
            String query = "{CALL updateBooks(?, ?, ?, ?, ?, ?, ?)}";
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setString(1, book.getBookId());
            callableStatement.setString(2, book.getTitle());
            callableStatement.setString(3, book.getAuthor());
            callableStatement.setString(4, book.getGenre());
            callableStatement.setDate(5, book.getPublicationDate());
            callableStatement.setBoolean(6, book.isAvailable());
            callableStatement.setInt(7, book.getCount());
            callableStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error updating book in database");
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteBook(int bookId) {
        try {
            String query = "{CALL deleteBook(?)}";
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setInt(1, bookId);
            callableStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error deleting book from database");
            e.printStackTrace();
            return false;
        }
    }

    public void printBookDetails(Books book) {
        if (book == null) {
            System.out.println("Book not found");
            return;
        }
        System.out.println("Book ID: " + book.getBookId());
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Genre: " + book.getGenre());
        System.out.println("Publication Date: " + book.getPublicationDate());
        System.out.println("Is Available: " + book.isAvailable());
        System.out.println("Count: " + book.getCount());
    }

    public void printAllBooks(List<Books> books) {
        if (books == null) {
            System.out.println("No books found");
            return;
        }
        for (Books book : books) {
            printBookDetails(book);
            System.out.println();
            System.out.println("___________________________________");
        }
    }

}
