import java.util.concurrent.atomic.AtomicReference;

public class CurrentLoggedUser {
    final static AtomicReference<CurrentLoggedUser> instance = new AtomicReference<>();
    private boolean isUserLogged;  // Represents whether a user is currently logged in.
    private int loggedUserId;  // Stores the ID of the currently logged-in user.

    private CurrentLoggedUser(){
    }

    public void setLoggedUserId(int loggedUserId) {
        this.loggedUserId = loggedUserId;
        // Sets the user ID when a user logs in.
    }

    public boolean isUserLogged() {
        return isUserLogged;
        // Checks whether a user is currently logged in.
    }

    public int getLoggedUserId() {
        return loggedUserId;
        // Retrieves the ID of the currently logged-in user.
    }

    public static CurrentLoggedUser getInstance(){
        if(instance.get() == null){
            instance.set(new CurrentLoggedUser());
        }
        return instance.get();
    }

    public void setIsUserLogged(boolean b) {
        this.isUserLogged = b;
        // Sets whether a user is logged in or not.
    }
}
