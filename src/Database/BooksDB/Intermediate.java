package Database.BooksDB;

import Database.InvalidPermissionsException;

public class Intermediate implements Books{
    BooksDB globalBooksDBObject;

    public Intermediate() {
        globalBooksDBObject = new BooksDB();
    }

    @Override
    public void addBook(String author, String book, String genre, String borrowedBy, String borrowDate, String status, String note, String uniqueId) throws Exception {
        globalBooksDBObject.addBook(author, book, genre, borrowedBy, borrowDate, status, note, uniqueId);
    }

    @Override
    public void editBookInformation(String bookTitle, String objectKey, String objectValue) throws InvalidPermissionsException {
        throw new InvalidPermissionsException();
    }

    @Override
    public void borrowBook(String bookName, String borrower, String duration) throws Exception {
        globalBooksDBObject.borrowBook(bookName, borrower, duration);
    }

    @Override
    public void buyBook(String buyer, String book) throws Exception {
        globalBooksDBObject.buyBook(buyer, book);
    }

    @Override
    public void removeBook(String bookName) throws InvalidPermissionsException {
        globalBooksDBObject.removeBook(bookName);
    }

    @Override
    public BooksDB[] getAllBooks() {
        return globalBooksDBObject.getAllBooks();
    }

    @Override
    public BooksDB getBook(String bookTitle) {
        return globalBooksDBObject.getBook(bookTitle);
    }
}
