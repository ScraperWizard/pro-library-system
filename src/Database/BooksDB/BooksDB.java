package Database.BooksDB;

import java.io.*;

import org.json.simple.*;
import org.json.simple.parser.*;

public class BooksDB {
    final String filePath = "src/Database/BooksDB/Books.json";
    public String author;
    public String book;
    public String genre;
    public String borrowedBy;
    public String borrowDate;

    public BooksDB(String author, String book, String genre, String borrowedBy, String borrowDate) {
        this.author = author;
        this.book = book;
        this.genre = genre;
        this.borrowedBy = borrowedBy;
        this.borrowDate = borrowDate;
    }

    public BooksDB() {

    }

    public void addBook(String author, String book, String genre, String borrowedBy, String borrowDate) throws Exception {
        // Check if collection exists
        // if (!isUsernameTaken(username))
        //     return;

        // Create a json object and append all information to it
        JSONObject userObject = new JSONObject();
        JSONArray userData = null;
        userObject.put("author", author);
        userObject.put("book", book);
        userObject.put("genre", genre);
        userObject.put("borrowedBy", borrowedBy);
        userObject.put("borrowDate", borrowDate);

        // Read database
        try (FileReader file = new FileReader(filePath)) {
            JSONParser parser = new JSONParser();
            userData = (JSONArray) parser.parse(file);
        } catch (IOException | ParseException error) {
            System.out.println("An error has occured while reading DB.");
        }

        // Merge the two objects together
        userData.add(userObject);

        // Write json object to file
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(userData.toJSONString());
            System.out.println("Book " + book + " has been added to DB.");
        } catch (IOException error) {
            System.out.println("An error has occured while writing to DB.");
        }
    }

    public void editUserInformation(String username, String objectKey, String objectValue) {

        // Check if collection exists
        if (!isUsernameTaken(username)) {
            System.out.println("editUserInformation: username not found");
            return;
        }

        // Read database
        JSONArray userData = null;
        try (FileReader file = new FileReader(filePath)) {
            JSONParser parser = new JSONParser();
            userData = (JSONArray) parser.parse(file);
        } catch (IOException | ParseException error) {
            System.out.println("editUserInformation: an error has occured while reading DB.");
            error.printStackTrace();
            return;
        }

        // Loop over all elements of the object,
        // and search for username object
        for (Object obj : userData) {
            JSONObject tempObject = (JSONObject) obj;
            if (tempObject.get("username").equals(username)) {
                // Update object value
                tempObject.put(objectKey, objectValue);

                // Write updated data to file
                try (FileWriter file = new FileWriter(filePath)) {
                    file.write(userData.toJSONString());
                    file.flush();
                } catch (IOException error) {
                    System.out.println("editUserInformation: an error has occurred while writing to DB.");
                    error.printStackTrace();
                    return;
                }

                // Return after successfully editing object
                System.out.println("editUserInformation: object updated successfully");
                return;
            }
        }

        // If username is not found, print error message
        System.out.println("editUserInformation: username not found");
    }



    public void removeBook(String bookName) {
        // Check if collection exists
//        if (!isUsernameTaken(username))
//            return;

        // Decleration
        JSONArray booksData = null;

        // Read database
        try (FileReader file = new FileReader(filePath)) {
            JSONParser parser = new JSONParser();
            booksData = (JSONArray) parser.parse(file);
        } catch (IOException | ParseException error) {
            System.out.println("An error has occured while reading DB.");
        }

        // Loop over all elements of the object,
        // and search for book object
        for (Object obj : booksData) {
            JSONObject tempObject = (JSONObject) obj;
            if (tempObject.get("book").equals(bookName)) {
                booksData.remove(tempObject);
            }
        }

        // Write json object to file
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(booksData.toJSONString());
            System.out.println("User " + bookName + " has been deleted from DB.");
        } catch (IOException error) {
            System.out.println("An error has occured while writing to DB.");
        }

    }

    public boolean isUsernameTaken(String username) {
        boolean result = false;
        JSONArray userData = null;

        // Read database
        try (FileReader file = new FileReader(filePath)) {
            JSONParser parser = new JSONParser();
            userData = (JSONArray) parser.parse(file);
        } catch (IOException | ParseException error) {
            System.out.println("An error has occured while reading DB.");
        }

        // Loop over all elements of the object,
        // and check if username exists
        for (Object obj : userData) {
            JSONObject tempObject = (JSONObject) obj;
            if (tempObject.get("username").equals(username))
                result = true;
        }

        return result;
    }

    public boolean validateLogin(String username, String password) {
        boolean result = false;
        JSONArray userData = null;

        // Read database
        try (FileReader file = new FileReader(filePath)) {
            JSONParser parser = new JSONParser();
            userData = (JSONArray) parser.parse(file);
        } catch (IOException | ParseException error) {
            System.out.println("An error has occured while reading DB.");
        }

        // Loop over all elements of the object,
        // and check if username exists
        for (Object obj : userData) {
            JSONObject tempObject = (JSONObject) obj;
            if (tempObject.get("username").equals(username) && tempObject.get("password").equals(password))
                result = true;
        }

        return result;
    }

    public BooksDB[] getAllBooks() {
        BooksDB[] BooksArray;

        // Decleration
        JSONArray userData = null;

        // Read database
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

            BooksArray[counter] = tempBooksDB;
            counter++;
        }

        return BooksArray;
    }

    public Customers getUser(String username) {
        if (!isUsernameTaken(username))
            return null;

        // Decleration
        JSONArray userData = null;

        // Read database
        try (FileReader file = new FileReader(filePath)) {
            JSONParser parser = new JSONParser();
            userData = (JSONArray) parser.parse(file);
        } catch (IOException | ParseException error) {
            System.out.println("An error has occured while reading DB.");
        }

        // Loop over all elements of the object,
        // and search for username object
        for (Object obj : userData) {
            JSONObject tempObject = (JSONObject) obj;
            if (tempObject.get("username").equals(username)) {
                return new Customers((String) tempObject.get("username"), (String) tempObject.get("password"),
                        (String) tempObject.get("email"), (String) tempObject.get("contact"));
            }
        }
        return null;
    }
}