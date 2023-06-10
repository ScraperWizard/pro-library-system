package Database.BooksDB;

import Database.InvalidPermissionsException;

public interface Books {
    public void addBook(String author, String book, String genre, String borrowedBy, String borrowDate, String status, String note, String uniqueId) throws Exception;
    public void editBookInformation(String bookTitle, String objectKey, String objectValue) throws InvalidPermissionsException;
    public void borrowBook(String bookName, String borrower, String duration) throws Exception;
    public void buyBook(String buyer, String book) throws Exception;
    public void removeBook(String bookName) throws InvalidPermissionsException;
    public BooksDB[] getAllBooks();
    public BooksDB getBook(String bookTitle);
}
