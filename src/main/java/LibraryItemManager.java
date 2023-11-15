import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.sql.Date;

public class LibraryItemManager extends LibraryItem implements LibraryItemInterface {

    // Check if a library item with the specified title is available.
    public boolean checkItemAvailability(String title) {
        String howManyCopiesAvailableQuery =
                "SELECT AvailableCopies " +
                        "FROM books " +
                        "WHERE Title = ?";
        try {
            PreparedStatement statement = ConnectToDatabase.connection.prepareStatement(howManyCopiesAvailableQuery);
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int availableCopies = resultSet.getInt("AvailableCopies");
                return availableCopies != 0;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return false;
    }

    // Get the ID of a book with the specified title.
    private int getBookId(String title) {
        String getBookIdQuery =
                "SELECT bookID " +
                        "FROM books " +
                        "WHERE title = ?";
        try {
            PreparedStatement statement = ConnectToDatabase.connection.prepareStatement(getBookIdQuery);
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("bookID");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return 0;
    }

    @Override
    public void borrowLibraryItem(Book book) {
        // Check if the item is available (available copies of the item).
        // If enough copies, decrease available copies by 1 and add to user_books as a borrowed item.
        if (checkItemAvailability(book.title)) {
            // Decrease available copies by 1 and add the book to user_books with user ID and current date.

            String subtractAvailableCopiesByOneQuery =
                    "UPDATE books SET " +
                            "AvailableCopies = AvailableCopies - 1 " +
                            "WHERE Title = ?";
            try {
                PreparedStatement statement = ConnectToDatabase.connection.prepareStatement(subtractAvailableCopiesByOneQuery);
                statement.setString(1, book.title);
                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    int bookID = getBookId(book.title);
                    String setBookUserTable =
                            "INSERT INTO user_books(userID, bookID, borrow_date) " +
                                    "VALUES(?,?,?)";
                    PreparedStatement statement1 = ConnectToDatabase.connection.prepareStatement(setBookUserTable);

                    CurrentLoggedUser currentLoggedUser = CurrentLoggedUser.getInstance();
                    statement1.setInt(1, currentLoggedUser.getLoggedUserId());
                    statement1.setInt(2, bookID);

                    java.util.Date currentDate = Calendar.getInstance().getTime();
                    Date sqlDate = new Date(currentDate.getTime());

                    statement1.setDate(3, sqlDate);
                    int rowsAffected1 = statement1.executeUpdate();
                    if (rowsAffected1 > 0) {
                        System.out.println("You just borrowed a book with title: " + book.getTitle());
                    } else {
                        System.out.println("Something went wrong!");
                    }
                } else {
                    System.out.println("Something went wrong!");
                }
            } catch (SQLException e) {
                System.out.println("Error: " + e);
            }
        } else {
            System.out.println("There are no available copies!");
        }
    }

    @Override
    public void displayBorrowedItems(int userID) {
        // Select all items from the library borrowed by the user and print their information.
        String getBorrowedItemsQuery = "SELECT b.Title, b.Author, b.BookID, b.Genre, b.TotalCopies, b.AvailableCopies, ub.borrow_date FROM books b " +
                "JOIN user_books ub ON b.bookID = ub.bookID " +
                "WHERE ub.userID = ?";
        try {
            PreparedStatement statement = ConnectToDatabase.connection.prepareStatement(getBorrowedItemsQuery);
            statement.setInt(1, userID);

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                super.setTitle(resultSet.getString("Title"));
                super.setAuthor(resultSet.getString("Author"));
                super.setLibraryItemID(resultSet.getInt("BookID"));
                super.setLibraryItemGenre(resultSet.getString("Genre"));
                super.setLibraryItemTotalCopies(resultSet.getInt("TotalCopies"));
                super.setLibraryItemAvailableCopies(resultSet.getInt("AvailableCopies"));
                printLibraryItemInfo();
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }
}
