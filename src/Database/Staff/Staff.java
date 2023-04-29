package Database.Staff;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Staff {
    final String filePath = "src/Database/Staff/Staff.json";
    public String username;
    public String password;
    public String email;
    public String age;
    public String uniqueID;
    public String role;

    public Staff(String username, String password, String email, String age, String uniqueID, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
        this.role = role;
        this.uniqueID = uniqueID;
    }

    public Staff() {

    }

    public void addStaff(String username, String password, String age, String email, String uniqueID, String role) throws Exception {
        // Check if collection exists
         if (isUsernameTaken(username))
             return;

        // Create a json object and append all information to it
        JSONObject userObject = new JSONObject();
        JSONArray userData = null;
        userObject.put("username", username);
        userObject.put("password", password);
        userObject.put("age", age);
        userObject.put("email", email);
        userObject.put("uniqueID", uniqueID);
        userObject.put("role", role);

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

    public void editStaffInformation(String username, String objectKey, String objectValue) {
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

    public void removeStaff(String username) {
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
        for (int i = 0; i < userData.size(); i++) {
            Object obj = userData.get(i);
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

    public boolean validateLogin(String uniqueID) {
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
            if (tempObject.get("uniqueID").equals(uniqueID))
                result = true;
        }

        return result;
    }

    public Staff[] getAllStaff() {
        Staff[] staffArray;

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
        staffArray = new Staff[userData.size()];
        int counter = 0;

        // Loop over all elements of the object,
        // and search for username object
        for (Object obj : userData) {
            JSONObject tempObject = (JSONObject) obj;
            Staff tempStaff = new Staff();
            tempStaff.username = (String) tempObject.get("username");
            tempStaff.password = (String) tempObject.get("password");
            tempStaff.email = (String) tempObject.get("email");
            tempStaff.age = (String) tempObject.get("age");
            tempStaff.role = (String) tempObject.get("role");
            tempStaff.uniqueID = (String) tempObject.get("uniqueID");

            staffArray[counter] = tempStaff;
            counter++;
        }
        return staffArray;
    }

    public Staff getStaff(String uniqueID) {
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
            if (tempObject.get("uniqueID").equals(uniqueID)) {
                return new Staff((String) tempObject.get("username"), (String) tempObject.get("password"),
                        (String) tempObject.get("email"), (String) tempObject.get("age"),
                        (String) tempObject.get("role"), (String) tempObject.get("uniqueID"));
            }
        }
        return null;
    }
}