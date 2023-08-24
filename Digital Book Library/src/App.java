import com.library.app.DatabaseConnection;
import com.library.app.UsersDAO;
import com.library.models.Users;

public class App {

    public static void main(String[] args) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        UsersDAO usersDAO = new UsersDAO(databaseConnection.getConnection());

        // Adding a new user
        Users newUser = new Users(1, "Stwewart", "Kruskar", "stwekar@example.com", "123-456-7890");
        if (usersDAO.addUser(newUser))
            System.out.println("User added successfully!");

        databaseConnection.closeConnection();
    }
}
