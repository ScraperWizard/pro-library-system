package Database.Maintenance;

import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.*;

/**
 * Represents the maintenance status of a database.
 */
public class Maintenance {
    final String filePath = "src/Database/Maintenance/Maintenance.json";
    public String status;

    /**
     * Constructs a Maintenance object with the specified status.
     *
     * @param status the current maintenance status
     */
    public Maintenance(String status) {
        this.status = status;
    }

    /**
     * Constructs a Maintenance object with default status.
     */
    public Maintenance() {

    }

    /**
     * Retrieves the current maintenance status from the database.
     *
     * @return the current maintenance status
     */
    public String getStatus() {
        try (FileReader file = new FileReader(filePath)) {
            JSONParser parser = new JSONParser();
            JSONObject maintenanceData = (JSONObject) parser.parse(file);
            return (String) maintenanceData.get("status");
        } catch (IOException | ParseException error) {
            System.out.println("An error has occurred while reading the maintenance status.");
        }
        return null;
    }

    /**
     * Sets the new maintenance status and updates it in the database.
     *
     * @param newStatus the new maintenance status to set
     */
    public void setStatus(String newStatus) {
        try (FileReader file = new FileReader(filePath)) {
            JSONParser parser = new JSONParser();
            JSONObject maintenanceData = (JSONObject) parser.parse(file);
            maintenanceData.put("status", newStatus);

            try (FileWriter fileWriter = new FileWriter(filePath)) {
                fileWriter.write(maintenanceData.toJSONString());
                fileWriter.flush();
                System.out.println("Maintenance status updated successfully.");
            } catch (IOException error) {
                System.out.println("An error occurred while updating the maintenance status.");
            }
        } catch (IOException | ParseException error) {
            System.out.println("An error occurred while reading the maintenance status.");
        }
    }
}