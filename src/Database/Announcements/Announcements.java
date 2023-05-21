package Database.Announcements;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Database.Logs.Logs;
import Database.Staff.Staff;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Announcements {
    final String filePath = "src/Database/Announcements/Announcements.json";
    public String to;
    public String subject;
    public String body;
    public String timestamp;

    public Announcements(String to, String subject, String body, String timestamp) {
        this.to = to;
        this.subject = subject;
        this.body = body;
        this.timestamp = timestamp;
    }

    public Announcements() {

    }

    public void addAnnouncement(String to, String subject, String body, String timestamp, String uniqueID) throws Exception {
        Staff globalStaffObject = new Staff();
        // Create a json object and append all information to it
        JSONObject announcementObject = new JSONObject();
        JSONArray announcementData = null;
        announcementObject.put("to", to);
        announcementObject.put("subject", subject);
        announcementObject.put("body", body);
        announcementObject.put("timestamp", timestamp);

        // Read database
        try (FileReader file = new FileReader(filePath)) {
            JSONParser parser = new JSONParser();
            announcementData = (JSONArray) parser.parse(file);
        } catch (IOException | ParseException error) {
            System.out.println("An error has occured while reading DB.");
        }

        // Merge the two objects together
        announcementData.add(announcementObject);

        // Write json object to file
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(announcementData.toJSONString());
            System.out.println("Announcement " + subject + " has been added to DB.");
        } catch (IOException error) {
            System.out.println("An error has occured while writing to DB.");
        }

        // Log action
        Staff staff = globalStaffObject.getStaff(uniqueID);
        Logs.addLogs("Announcement made", staff.username, staff.role, "null", timestamp, subject);
    }

    public Announcements[] getAllAnnouncements(String filterTo) {
        List<Announcements> announcementsList = new ArrayList<>();

        try (FileReader file = new FileReader(filePath)) {
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(file);

            for (Object obj : jsonArray) {
                JSONObject jsonAnnouncement = (JSONObject) obj;
                String to = (String) jsonAnnouncement.get("to");
                String subject = (String) jsonAnnouncement.get("subject");
                String body = (String) jsonAnnouncement.get("body");
                String timestamp = (String) jsonAnnouncement.get("timestamp");

                // Apply filter based on the "to" field
                if (filterTo == null || filterTo.isEmpty() || to.equalsIgnoreCase(filterTo)) {
                    Announcements announcement = new Announcements(to, subject, body, timestamp);
                    announcementsList.add(announcement);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return announcementsList.toArray(new Announcements[0]);
    }
}
