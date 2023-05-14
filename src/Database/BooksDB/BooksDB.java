package Database.BooksDB;

import java.io.*;
import java.util.Iterator;

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

    public void addBook(String author, String book, String genre, String borrowedBy, String borrowDate, String status, String note) throws Exception {
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
    }

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