import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static void printFirstMenu() {

        // Instructions for displaying the menu options
        Scanner scan = new Scanner(System.in);
        System.out.println("1. Log in");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Option: ");
        final int option = scan.nextInt();
        scan.nextLine();

        String login;
        String password;
        UserManager userManager = new UserManager();

        switch (option) {
            case 1:
                // Prompt for login information
                System.out.print("Login: ");
                login = scan.nextLine();
                System.out.print("Password: ");
                password = scan.nextLine();
                // Call the login function
                userManager.login(login, password);
                break;
            case 2:
                try {


                    // Prompt for registration information
                    System.out.print("Login: ");
                    login = scan.nextLine();
                    // Check if the login is already in use
                    if (UserManager.isUserAlreadyUsed(login)) {
                        System.out.println("Login is already used!");
                        break;
                    }
                    System.out.print("Password: ");
                    password = scan.nextLine();
                    // Check password strength
                    if (UserManager.checkUserPassword(password)) {
                        System.out.println("Password is too weak!");
                        break;
                    }
                    System.out.print("Your first name: ");
                    String userFirstName = scan.nextLine();
                    System.out.print("Your last name: ");
                    String userLastName = scan.nextLine();
                    System.out.print("Your phone number: ");
                    int phoneNumber = scan.nextInt();

                    // Register the user
                    if (userFirstName != null || userLastName != null) {
                        User user = new User(login, password, userFirstName, userLastName, phoneNumber);
                        userManager.register(user);
                    } else {
                        System.out.println("Invalid information");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Error: " + e);
                }
                break;
            case 3:
                // Exit the program
                System.exit(0);
                break;
            case 4:
                // Additional option (no implementation)
                break;
        }
    }

    static void printMenuIfLogged() {
        System.out.println();
        CurrentLoggedUser currentLoggedUser = CurrentLoggedUser.getInstance();

        if (!currentLoggedUser.isUserLogged()) {
            // Message when the user is not logged in
            System.out.println("You are not logged in");
        } else {
            Scanner scan = new Scanner(System.in);

            System.out.println("1. Display information about yourself");
            System.out.println("2. Change password");
            System.out.println("3. Borrow a book");
            System.out.println("4. Log out");

            Integer option = null;
            try {
                System.out.print("Option: ");
                option = scan.nextInt();
                scan.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Error: " + e);
            }
            if(option == null ){
                System.out.println("Error, option is null");
                return;
            }
            UserManager userManager = new UserManager();
            switch (option) {
                case 1:
                    // Display user information
                    userManager.displayUserInfo(currentLoggedUser.getLoggedUserId());
                    break;
                case 2:
                    // Call the function to change the password
                    System.out.println(currentLoggedUser.getLoggedUserId());
                    userManager.changePassword(currentLoggedUser.getLoggedUserId());
                    break;
                case 3:
                    // Prompt for the title of the book to borrow
                    System.out.print("Book title you want to borrow: ");
                    String bookTitleUserWantToBorrow = scan.nextLine();
                    // Create a book object and call the borrowLibraryItem function
                    Book book = new Book();
                    book.setBookInformation(bookTitleUserWantToBorrow);
                    new LibraryItemManager().borrowLibraryItem(book);
                    break;
                case 4:
                    // Call the logout function
                    userManager.logout();
                    break;
                default:
                    System.out.println("Wrong choice");

            }
        }
    }

    public static void main(String[] args) {

        new ConnectToDatabase();

        CurrentLoggedUser currentLoggedUser = CurrentLoggedUser.getInstance();
        printFirstMenu();

        while (currentLoggedUser.isUserLogged()) {
            printMenuIfLogged();
        }

        ConnectToDatabase.closeDatabase();
    }
}