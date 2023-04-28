package Database.Users;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.simple.*;
import org.json.simple.parser.*;
import jakarta.json.JsonArray;

public class User {
    final String filePath = "src/Database/Users/Users.json";
    public String username;
    public String password;
    public String email;
    public String age;

    public User(String username, String password, String email, String age) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
    }

    public User() {

    }

    public void addUser(String username, String password, String age, String email) throws Exception {
        // Check if collection exists
        // if (!isUsernameTaken(username))
        //     return;

        // Create a json object and append all information to it
        JSONObject userObject = new JSONObject();
        JSONArray userData = null;
        userObject.put("username", username);
        userObject.put("password", password);
        userObject.put("age", age);
        userObject.put("email", email);

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
            System.out.println("User " + username + " has been added to DB.");
        } catch (IOException error) {
            System.out.println("An error has occured while writing to DB.");
        }
    }

    public void editUserInformation(String username, String objectKey, String objectValue) {
        // Check if collection exists
        if (!isUsernameTaken(username))
            return;

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
                tempObject.put(objectKey, objectValue);
            }
        }

    }

    public void removeUser(String username) {
        // Check if collection exists
        if (!isUsernameTaken(username))
            return;

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
                userData.remove(tempObject);
            }
        }

        // Write json object to file
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(userData.toJSONString());
            System.out.println("User " + username + " has been deleted from DB.");
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

    public User[] getAllUsers() {
        User[] userArray;

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
        userArray = new User[userData.size()];
        int counter = 0;

        // Loop over all elements of the object,
        // and search for username object
        for (Object obj : userData) {
            JSONObject tempObject = (JSONObject) obj;
            User tempUser = new User();
            tempUser.username = (String) tempObject.get("username");
            tempUser.password = (String) tempObject.get("password");
            tempUser.email = (String) tempObject.get("email");
            tempUser.age = (String) tempObject.get("age");

            userArray[counter] = tempUser;
            counter++;
        }
        return userArray;
    }

    public User getUser(String username) {
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
                return new User((String) tempObject.get("username"), (String) tempObject.get("password"),
                        (String) tempObject.get("email"), (String) tempObject.get("age"));
            }
        }
        return null;
    }
}