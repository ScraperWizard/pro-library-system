package Database.Logs;

import java.io.*;
import java.util.ArrayList;
import org.json.simple.*;
import org.json.simple.parser.*;

public class Logs implements Log {
    final String filePath = "src/Database/Logs/logs.json";
    public String action;
    public String by;
    public String byRole;
    public String on;
    public String timestamp;
    public String note;

    public Logs(String action, String by, String byRole, String on, String timestamp, String note) {
        this.action = action;
        this.by = by;
        this.byRole = byRole;
        this.on = on;
        this.timestamp = timestamp;
        this.note = note;
    }

    public Logs() {

    }

    /**
     * Add a new log to the database.
     *
     * @param action the action performed
     * @param by the user who performed the action
     * @param byRole the role of the user who performed the action
     * @param on the object the action was performed on
     * @param timestamp the timestamp when the action was performed
     * @param note additional notes or comments about the action
     * @throws Exception if there was an error reading or writing to the database
     */
    public static void addLogs(String action, String by, String byRole, String on, String timestamp, String note) throws Exception {
        final String filePath = "src/Database/Logs/logs.json";

        // Create a json object and append all information to it
        JSONObject logsObject = new JSONObject();
        JSONArray logsData = null;
        logsObject.put("action", action);
        logsObject.put("by", by);
        logsObject.put("byRole", byRole);
        logsObject.put("on", on);
        logsObject.put("timestamp", timestamp);
        logsObject.put("note", note);

        // Read database
        try (FileReader file = new FileReader(filePath)) {
            JSONParser parser = new JSONParser();
            logsData = (JSONArray) parser.parse(file);
        } catch (IOException | ParseException error) {
            System.out.println("An error has occured while reading DB.");
        }

        // Merge the two objects together
        logsData.add(logsObject);

        // Write json object to file
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(logsData.toJSONString());
            System.out.println("Action " + action + " has been logged to DB.");
        } catch (IOException error) {
            System.out.println("An error has occured while writing to DB.");
        }
    }

    /**
     * Retrieves logs from the database that match the specified filter criteria and returns them as an array of Logs objects.
     *
     * @param byFilter a string representing the "by" field to filter by, or null to include all logs.
     * @param onFilter a string representing the "on" field to filter by, or null to include all logs.
     * @return an array of Logs objects containing the logs that match the specified filter criteria.
     */
    public Logs[] getLogs(String byFilter, String onFilter) {
        Logs[] logsArray;

        // Declare and initialize variables
        JSONArray logsData = null;
        int counter = 0;

        // Read database
        try (FileReader file = new FileReader(filePath)) {
            JSONParser parser = new JSONParser();
            logsData = (JSONArray) parser.parse(file);
        } catch (IOException | ParseException error) {
            System.out.println("An error has occured while reading DB.");
        }

        // Create an array to hold the matching logs
        ArrayList<Logs> matchingLogs = new ArrayList<>();

        // Loop over all elements of the JSON array and convert them to Logs objects
        for (Object obj : logsData) {
            // Extract the fields from the JSON object and create a new Logs object
            JSONObject tempObject = (JSONObject) obj;
            Logs tempLogsDB = new Logs();
            tempLogsDB.action = (String) tempObject.get("action");
            tempLogsDB.by = (String) tempObject.get("by");
            tempLogsDB.byRole = (String) tempObject.get("byRole");
            tempLogsDB.on = (String) tempObject.get("on");
            tempLogsDB.timestamp = (String) tempObject.get("timestamp");
            tempLogsDB.note = (String) tempObject.get("note");

            // Check if the Logs object matches the filter criteria
            if ((byFilter == null || tempLogsDB.by.equals(byFilter)) &&
                    (onFilter == null || tempLogsDB.on.equals(onFilter))) {
                // Add the matching Logs object to the array
                matchingLogs.add(tempLogsDB);
            }
        }

        // Convert the ArrayList to an array and return it
        logsArray = matchingLogs.toArray(new Logs[0]);
        return logsArray;
    }

}