import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToDatabase {
    static Connection connection = null;

    final String url = "jdbc:mysql://localhost:3306/library";
    final String user = "root";
    final String password = "zaq1@WSX";

    public ConnectToDatabase(){
        try {
            // Attempt to connect to the database
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            // Handle any potential exceptions that may occur during the connection process
            System.out.println("Error: " + e);
        }
    }

    static void closeDatabase(){
        try{
            // Close the database connection
            connection.close();
        }catch (SQLException e ){
            // Handle any exceptions that may occur when closing the connection
            System.out.println("Error: " + e);
        }
    }
}
