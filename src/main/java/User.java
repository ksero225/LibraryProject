public record User(String username, String password, String userFirstName, String userLastName, int phoneNumber) {

    // Override the toString method to provide a custom string representation of the User object.
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userFirstName='" + userFirstName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}