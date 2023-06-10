package Database.BooksDB;

import Database.InvalidPermissionsException;

public class Basic implements Books {
    BooksDB globalBooksDBObject;

    public Basic() {
        globalBooksDBObject = new BooksDB();
    }

    @Override
    public void addBook(String author, String book, String genre, String borrowedBy, String borrowDate, String status, String note, String uniqueId) throws Exception {
        throw new InvalidPermissionsException();
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
        throw new InvalidPermissionsException();
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
