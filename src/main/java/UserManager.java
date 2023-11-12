import java.sql.Date;
import java.sql.ResultSet;
import java.util.Calendar;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserManager implements UserInterface {

    // Check if a user's password meets security criteria
    static boolean checkUserPassword(String password) {
        if (password.length() < 8) {
            return true;
        } else {
            Pattern pattern = Pattern.compile("[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]");
            Matcher matcher = pattern.matcher(password);
            return !matcher.find();
        }
    }

    // Check if a username is already in use
    static boolean isUserAlreadyUsed(String username) {
        String isUsernameAlreadyUsedQuery = "SELECT username FROM users WHERE username = ?";
        try {
            PreparedStatement statement = ConnectToDatabase.connection.prepareStatement(isUsernameAlreadyUsedQuery);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
            return false;
        }
    }

    // Prompt user for their current password and check if it matches the stored password
    private boolean checkCurrentPassword(String realCurrentPassword) {
        System.out.print("Current Password: ");
        Scanner scan = new Scanner(System.in);

        String currentPassword = scan.nextLine();

        return realCurrentPassword.equals(currentPassword);
    }

    @Override
    public void changePassword(int id) {
        // Retrieve the user's current password from the database
        String getUserCurrentPasswordQuery = "SELECT password FROM users WHERE userID = ?";
        try {
            PreparedStatement statement = ConnectToDatabase.connection.prepareStatement(getUserCurrentPasswordQuery);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String password = resultSet.getString("password");

                if (checkCurrentPassword(password)) {
                    // Prompt the user for a new password and update it in the database
                    System.out.print("New password: ");
                    Scanner scan = new Scanner(System.in);
                    String newPassword = scan.nextLine();

                    String changeUserPasswordQuery = "UPDATE users set password = ? WHERE userID = ?";

                    PreparedStatement statement1 = ConnectToDatabase.connection.prepareStatement(changeUserPasswordQuery);
                    statement1.setString(1, newPassword);
                    statement1.setInt(2, id);
                    int rowsAffected = statement1.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Password updated");
                    } else {
                        System.out.println("Failed to update password");
                    }
                } else {
                    System.out.println("Wrong current password");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    @Override
    public void displayUserInfo(int id) {
        // Retrieve and display user information from the database
        String userInfoQuery = "SELECT * FROM USERS WHERE userID = ?";
        try {
            PreparedStatement statement = ConnectToDatabase.connection.prepareStatement(userInfoQuery);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String userFirstName = resultSet.getString("userFirstName");
                String userLastName = resultSet.getString("userLastName");
                String phoneNumber = resultSet.getString("phoneNumber");
                Date registrationDate = resultSet.getDate("registrationDate");

                System.out.println("User ID: " + id);
                System.out.println("Username: " + username);
                System.out.println("Password: " + password);
                System.out.println("First Name: " + userFirstName);
                System.out.println("Last Name: " + userLastName);
                System.out.println("Phone Number: " + phoneNumber);
                System.out.println("Registration Date: " + registrationDate);

                System.out.println("Your borrowed books: ");
                new LibraryItemManager().displayBorrowedItems(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void login(String username, String password) {
        // Validate user login by checking credentials against the database
        String sqlQuery = "SELECT userID, username, password FROM users WHERE username = ? AND password = ?";
        try {
            PreparedStatement statement = ConnectToDatabase.connection.prepareStatement(sqlQuery);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                CurrentLoggedUser currentLoggedUser = CurrentLoggedUser.getInstance();
                currentLoggedUser.setLoggedUserId(resultSet.getInt("userID"));
                currentLoggedUser.setIsUserLogged(true);
            } else {
                System.out.println("Username or password is invalid!");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public void logout() {
        // Log out the currently logged-in user
        CurrentLoggedUser currentLoggedUser = CurrentLoggedUser.getInstance();
        currentLoggedUser.setIsUserLogged(false);
        System.out.println("You've been logged out");
    }

    @Override
    public void register(User user) {
        // Register a new user and insert their information into the database
        String registerQuery = "INSERT INTO users(username, password, userFirstName, userLastName, phoneNumber, registrationDate) VALUES(?,?,?,?,?,?)";
        try {
            java.util.Date currentDate = Calendar.getInstance().getTime();
            Date sqlDate = new Date(currentDate.getTime());

            if (checkUserPassword(user.password())) {
                System.out.println("Password too weak!");
                return;
            }

            PreparedStatement statement = ConnectToDatabase.connection.prepareStatement(registerQuery);
            statement.setString(1, user.username());
            statement.setString(2, user.password());
            statement.setString(3, user.userFirstName());
            statement.setString(4, user.userLastName());
            statement.setInt(5, user.phoneNumber());
            statement.setDate(6, sqlDate);

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }
}