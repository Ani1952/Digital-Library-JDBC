package com.library.app;

import com.library.models.Books;
import com.library.util.BooksDOA;
import com.library.util.DatabaseConnection;

import java.sql.Connection;

public class TestBooksApp {

    public static void main(String[] args) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        BooksDOA booksDOA = new BooksDOA(connection);

        // Test BooksDOA
        Books newBook = new Books("B001", "Sample Book", "Sample Author", "Fiction",
                new java.sql.Date(System.currentTimeMillis()), true, 10);
        booksDOA.addBook(newBook);

        Books retrievedBook = booksDOA.getBook("B001");
        booksDOA.printBookDetails(retrievedBook);

        booksDOA.printAllBooks(booksDOA.getAllBooks());

        // Close connection
        databaseConnection.closeConnection();
    }
}
