import java.util.List;

import com.library.app.DatabaseConnection;
import com.library.app.UsersDAO;
import com.library.models.Users;

public class App {

    public static void main(String[] args) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        UsersDAO usersDAO = new UsersDAO(databaseConnection.getConnection());

        List<Users> allUsers = usersDAO.getAllUsers();
        if (!allUsers.isEmpty()) {
            System.out.println("All Users:");
            usersDAO.printAllUsers(allUsers);
        } else {
            System.out.println("No users found.");
        }

        databaseConnection.closeConnection();
    }
}
