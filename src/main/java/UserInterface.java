public interface UserInterface {
    // User-related methods

    // Change the user's password based on their user ID
    void changePassword(int id);

    // Display user information based on their user ID
    void displayUserInfo(int id);

    // Log in with a username and password
    void login(String username, String password);

    // Log out the currently logged-in user
    void logout();

    // Register a new user with the provided User object
    void register(User user);
}