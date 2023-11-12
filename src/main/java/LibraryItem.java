public abstract class LibraryItem {
    protected  String title;  // Stores the title of the library item.
    protected  String author;  // Stores the author of the library item.
    protected  int libraryItemID;  // Represents the unique ID of the library item.
    protected  String libraryItemGenre;  // Represents the genre or category of the library item.
    protected  int libraryItemTotalCopies;  // Represents the total number of copies of the library item.
    protected  int libraryItemAvailableCopies;  // Represents the number of available copies of the library item.

    public void setTitle(String title) {
        this.title = title;
        // Sets the title of the library item.
    }

    public void setAuthor(String author) {
        this.author = author;
        // Sets the author of the library item.
    }

    public void setLibraryItemID(int libraryItemID) {
        this.libraryItemID = libraryItemID;
        // Sets the unique ID of the library item.
    }

    public void setLibraryItemGenre(String libraryItemGenre) {
        this.libraryItemGenre = libraryItemGenre;
        // Sets the genre or category of the library item.
    }

    public void setLibraryItemTotalCopies(int libraryItemTotalCopies) {
        this.libraryItemTotalCopies = libraryItemTotalCopies;
        // Sets the total number of copies of the library item.
    }

    public void setLibraryItemAvailableCopies(int libraryItemAvailableCopies) {
        this.libraryItemAvailableCopies = libraryItemAvailableCopies;
        // Sets the number of available copies of the library item.
    }

    public String getTitle() {
        return title;
        // Retrieves the title of the library item.
    }

    void printLibraryItemInfo() {
        System.out.println(
                "title='" + this.title + '\'' +
                        ", author='" + author + '\'' +
                        ", libraryItemID=" + libraryItemID +
                        ", libraryItemGenre='" + libraryItemGenre + '\'' +
                        ", libraryItemTotalCopies=" + libraryItemTotalCopies +
                        ", libraryItemAvailableCopies=" + libraryItemAvailableCopies
        );
        // Prints information about the library item, including its title, author, ID, genre, total copies, and available copies.
    }
}
