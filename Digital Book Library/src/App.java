import com.library.models.Books;
import com.library.util.BooksDOA;
import com.library.util.DatabaseConnection;

public class App {
    public static void main(String[] args) {
        DatabaseConnection dbcon = new DatabaseConnection();
        BooksDOA booksDOA = new BooksDOA(dbcon.getConnection());

        Books book = new Books("test", "test", "test", "test", java.sql.Date.valueOf("1971-09-09"), false, 1);

        booksDOA.addBook(book);
        booksDOA.printAllBooks(null);
        dbcon.closeConnection();
    }

}
