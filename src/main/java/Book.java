import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Book extends LibraryItem {
    public void setBookInformation(String title){
        // Create an SQL query to retrieve information about a book based on its title
        String getBookInfoQuery =
                "SELECT * " +
                        "FROM books " +
                        "WHERE title = ?";
        try {
            // Create a prepared SQL statement
            PreparedStatement statement = ConnectToDatabase.connection.prepareStatement(getBookInfoQuery);
            statement.setString(1, title);

            // Execute the query and retrieve the results
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // Update the properties of the Book object based on the database results
                super.setTitle(resultSet.getString("Title"));
                super.setAuthor(resultSet.getString("Author"));
                super.setLibraryItemID(resultSet.getInt("BookID"));
                super.setLibraryItemGenre(resultSet.getString("Genre"));
                super.setLibraryItemTotalCopies(resultSet.getInt("TotalCopies"));
                super.setLibraryItemAvailableCopies(resultSet.getInt("AvailableCopies"));

                // Print the book information
                String information = super.toString();
                System.out.println(information);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }
}
