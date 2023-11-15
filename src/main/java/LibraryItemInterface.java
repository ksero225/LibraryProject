public interface LibraryItemInterface {
    // Define methods for library items.

    // Borrow a library item,
    void borrowLibraryItem(Book book);

    // Display a list of borrowed items associated with a specific 'userID'.
    void displayBorrowedItems(int userID);
}