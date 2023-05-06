package Database.Customers;

import java.io.*;

import org.json.simple.*;
import org.json.simple.parser.*;

public class Customers {
    final String filePath = "src/Database/Customers/Customers.json";
    public String username;
    public String password;
    public String email;
    public String contact;

    public Customers(String username, String password, String email, String contact) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.contact = contact;
    }

    public Customers() {

    }

    public void addUser(String username, String password, String contact, String email) throws Exception {
        // Check if collection exists
        // if (!isUsernameTaken(username))
        //     return;

        // Create a json object and append all information to it
        JSONObject userObject = new JSONObject();
        JSONArray userData = null;
        userObject.put("username", username);
        userObject.put("password", password);
        userObject.put("contact", contact);
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

    public Customers[] getAllUsers() {
        Customers[] customersArray;

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
        customersArray = new Customers[userData.size()];
        int counter = 0;

        // Loop over all elements of the object,
        // and search for username object
        for (Object obj : userData) {
            JSONObject tempObject = (JSONObject) obj;
            Customers tempCustomers = new Customers();
            tempCustomers.username = (String) tempObject.get("username");
            tempCustomers.password = (String) tempObject.get("password");
            tempCustomers.email = (String) tempObject.get("email");
            tempCustomers.contact = (String) tempObject.get("contact");

            customersArray[counter] = tempCustomers;
            counter++;
        }
        return customersArray;
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