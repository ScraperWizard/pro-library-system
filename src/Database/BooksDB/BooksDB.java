package Database.BooksDB;

import java.io.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;

import Database.Logs.Logs;
import Database.Staff.Staff;
import org.json.simple.*;
import org.json.simple.parser.*;

public class BooksDB {
    final String filePath = "src/Database/BooksDB/Books.json";
    public String author;
    public String book;
    public String genre;
    public String borrowedBy;
    public String borrowDate;
    public String status;
    public String note;
    Staff globalStaffObject = new Staff();

    public BooksDB(String author, String book, String genre, String borrowedBy, String borrowDate, String status, String note) {
        this.author = author;
        this.book = book;
        this.genre = genre;
        this.borrowedBy = borrowedBy;
        this.borrowDate = borrowDate;
        this.status = status;
        this.note = note;
    }

    public BooksDB() {

    }

    /**
     * Adds a new book to the database.
     *
     * @param author      the author of the book
     * @param book        the title of the book
     * @param genre       the genre of the book
     * @param borrowedBy  the name of the person borrowing the book
     * @param borrowDate  the date when the book was borrowed
     * @param status      the status of the book (e.g., "Available", "Borrowed")
     * @param note        additional note about the book
     * @throws Exception if an error occurs while adding the book to the database
     */
    public void addBook(String author, String book, String genre, String borrowedBy, String borrowDate, String status, String note, String uniqueId) throws Exception {
        // Check if collection exists
        // if (!isUsernameTaken(username))
        //     return;

        // Create a json object and append all information to it
        JSONObject bookObject = new JSONObject();
        JSONArray booksData = null;
        bookObject.put("author", author);
        bookObject.put("book", book);
        bookObject.put("genre", genre);
        bookObject.put("borrowedBy", borrowedBy);
        bookObject.put("borrowDate", borrowDate);
        bookObject.put("status", status);
        bookObject.put("note", note);

        // Read database
        try (FileReader file = new FileReader(filePath)) {
            JSONParser parser = new JSONParser();
            booksData = (JSONArray) parser.parse(file);
        } catch (IOException | ParseException error) {
            System.out.println("An error has occured while reading DB.");
        }

        // Merge the two objects together
        booksData.add(bookObject);

        // Write json object to file
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(booksData.toJSONString());
            System.out.println("Book " + book + " has been added to DB.");
        } catch (IOException error) {
            System.out.println("An error has occured while writing to DB.");
        }

        // Log action
        Staff staff = globalStaffObject.getStaff(uniqueId);
        Logs.addLogs("Book added", staff.username, staff.role, "null", LocalDate.now().toString(), book);
    }

    /**
     * Edits the information of a book in the database.
     *
     * @param bookTitle   the title of the book to edit
     * @param objectKey   the key of the object to edit (e.g., "author", "genre", "borrowedBy")
     * @param objectValue the new value to assign to the object
     */
    public void editBookInformation(String bookTitle, String objectKey, String objectValue) {
        // Read database
        JSONArray booksData = null;
        try (FileReader file = new FileReader(filePath)) {
            JSONParser parser = new JSONParser();
            booksData = (JSONArray) parser.parse(file);
        } catch (IOException | ParseException error) {
            System.out.println("editBookInformation: an error has occurred while reading DB.");
            error.printStackTrace();
            return;
        }

        // Loop over all books in the database,
        // and search for book with matching title
        for (Object obj : booksData) {
            JSONObject tempObject = (JSONObject) obj;
            if (tempObject.get("book").equals(bookTitle)) {
                // Update object value
                tempObject.put(objectKey, objectValue);

                // Write updated data to file
                try (FileWriter file = new FileWriter(filePath)) {
                    file.write(booksData.toJSONString());
                    file.flush();
                } catch (IOException error) {
                    System.out.println("editBookInformation: an error has occurred while writing to DB.");
                    error.printStackTrace();
                    return;
                }

                // Return after successfully editing object
                System.out.println("editBookInformation: book updated successfully");
                return;
            }
        }

        // If book is not found, print error message
        System.out.println("editBookInformation: book not found");
    }

    /**
     * Borrows a book from the library by updating the book's information in the database.
     *
     * @param bookName  the name of the book to borrow
     * @param borrower  the name of the borrower
     * @param duration  the duration of the borrowing in days
     * @throws Exception if an error occurs while reading or writing to the database
     */
    public void borrowBook(String bookName, String borrower, String duration) throws Exception {
        // Read the existing book data from the database
        JSONArray booksData = null;
        try (FileReader file = new FileReader(filePath)) {
            JSONParser parser = new JSONParser();
            booksData = (JSONArray) parser.parse(file);
        } catch (IOException | ParseException error) {
            System.out.println("An error has occurred while reading the database.");
            return;
        }

        // Find the book with the given name
        for (Object bookObj : booksData) {
            JSONObject bookData = (JSONObject) bookObj;
            String book = (String) bookData.get("book");
            if (book.equalsIgnoreCase(bookName)) {
                // Update the book's borrowedBy, borrowDate, and status fields
                bookData.put("borrowedBy", borrower);
                bookData.put("borrowDate", LocalDate.now().toString());
                bookData.put("status", "Borrowed");
                bookData.put("note", duration + " days");

                // Write the updated book data back to the database
                try (FileWriter file = new FileWriter(filePath)) {
                    file.write(booksData.toJSONString());
                    System.out.println("Book " + bookName + " has been borrowed by " + borrower + " for " + duration + ".");
                    return;
                } catch (IOException error) {
                    System.out.println("An error has occurred while writing to the database.");
                    return;
                }
            }
        }

        // If the book was not found
        System.out.println("Book " + bookName + " does not exist in the database.");
    }

    /**
     * Buys a book and adds it to the library by adding its information to the database.
     *
     * @param buyer   the name of the person buying the book
     * @param book    the name of the book
     * @throws Exception if an error occurs while reading or writing to the database
     */
    public void buyBook(String buyer, String book) throws Exception {
        // Read the existing book data from the database
        JSONArray booksData = null;
        try (FileReader file = new FileReader(filePath)) {
            JSONParser parser = new JSONParser();
            booksData = (JSONArray) parser.parse(file);
        } catch (IOException | ParseException error) {
            System.out.println("An error has occurred while reading the database.");
            return;
        }

        // Find the book with the given name
        for (Object bookObj : booksData) {
            JSONObject bookData = (JSONObject) bookObj;
            String bookName = (String) bookData.get("book");
            if (bookName.equalsIgnoreCase(book)) {
                // Update the book's status and note fields
                bookData.put("status", "Bought");
                bookData.put("note", "Bought by " + buyer);

                // Write the updated book data back to the database
                try (FileWriter file = new FileWriter(filePath)) {
                    file.write(booksData.toJSONString());
                    System.out.println("Book " + book + " has been bought by " + buyer + ".");
                    return;
                } catch (IOException error) {
                    System.out.println("An error has occurred while writing to the database.");
                    return;
                }
            }
        }

        // If the book was not found
        System.out.println("Book " + book + " does not exist in the database.");
    }

    /**
     * Removes a book from the database based on the book name.
     *
     * @param bookName the name of the book to remove
     */
    public void removeBook(String bookName) {
        // Check if collection exists
        System.out.println("Remove book Has been called");

        // Declaration
        JSONArray booksData = null;

        // Read database
        try (FileReader file = new FileReader(filePath)) {
            JSONParser parser = new JSONParser();
            booksData = (JSONArray) parser.parse(file);
        } catch (IOException | ParseException error) {
            System.out.println("An error has occured while reading DB.");
        }

        // Remove book object from JSONArray using an Iterator
        Iterator<Object> iter = booksData.iterator();
        while (iter.hasNext()) {
            JSONObject tempObject = (JSONObject) iter.next();
            if (tempObject.get("book").equals(bookName)) {
                iter.remove(); // remove book object safely using the iterator's remove() method
                // If normal loop is used it will throw an exception as you cannot modify JSONObject
                // While iterating
            }
        }

        // Write json object to file
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(booksData.toJSONString()); // write the updated JSON array to file
            System.out.println("Book " + bookName + " has been deleted from DB.");
        } catch (IOException error) {
            System.out.println("An error has occured while writing to DB.");
        }
    }

    /**
     * Retrieves an array of all books from the database.
     *
     * @return an array of BooksDB objects representing all books in the database
     */
    public BooksDB[] getAllBooks() {
        BooksDB[] BooksArray;

        // Decleration
        JSONArray userData = null;

        // Read databasew
        try (FileReader file = new FileReader(filePath)) {
            JSONParser parser = new JSONParser();
            userData = (JSONArray) parser.parse(file);
        } catch (IOException | ParseException error) {
            System.out.println("An error has occured while reading DB.");
        }

        // Assign userArray with correct size
        BooksArray = new BooksDB[userData.size()];
        int counter = 0;

        // Loop over all elements of the object,
        // and search for username object
        for (Object obj : userData) {
            JSONObject tempObject = (JSONObject) obj;
            BooksDB tempBooksDB = new BooksDB();
            tempBooksDB.author = (String) tempObject.get("author");
            tempBooksDB.book = (String) tempObject.get("book");
            tempBooksDB.genre = (String) tempObject.get("genre");
            tempBooksDB.borrowedBy = (String) tempObject.get("borrowedBy");
            tempBooksDB.borrowDate = (String) tempObject.get("borrowDate");
            tempBooksDB.status = (String) tempObject.get("status");
            tempBooksDB.note = (String) tempObject.get("note");

            BooksArray[counter] = tempBooksDB;
            counter++;
        }

        return BooksArray;
    }

    /**
     * Retrieves a book from the database based on the book title.
     *
     * @param bookTitle the title of the book to retrieve
     * @return a BooksDB object representing the book, or null if the book is not found
     */
    public BooksDB getBook(String bookTitle) {
        // Declare a variable to hold the BooksDB object corresponding to the specified book title
        BooksDB book = null;

        // Read the database
        try (FileReader file = new FileReader(filePath)) {
            JSONParser parser = new JSONParser();
            JSONArray userData = (JSONArray) parser.parse(file);

            // Loop over all elements of the object and search for the book title
            for (Object obj : userData) {
                JSONObject tempObject = (JSONObject) obj;

                // If the current object corresponds to the specified book title, create a BooksDB object for it
                if (tempObject.get("book").equals(bookTitle)) {
                    book = new BooksDB();
                    book.author = (String) tempObject.get("author");
                    book.book = (String) tempObject.get("book");
                    book.genre = (String) tempObject.get("genre");
                    book.borrowedBy = (String) tempObject.get("borrowedBy");
                    book.borrowDate = (String) tempObject.get("borrowDate");
                    book.status = (String) tempObject.get("status");
                    book.note = (String) tempObject.get("note");
                    
                    break;
                }
            }
            
        } catch (IOException | ParseException error) {
            System.out.println("An error has occurred while reading the database.");
        }

        // Return the BooksDB object corresponding to the specified book title, or null if the book is not found
        return book;
    }
}