package Database.CustomerTickets;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.ArrayList;

public class CustomerTickets {
    private final String filePath = "src/Database/CustomerTickets/CustomerTickets.json";
    public String ticketID;
    public String from;
    public String to;
    public String subject;
    public String customerMessage;
    public String staffMessage;
    private String timestamp;

    public CustomerTickets(String ticketID, String from, String to, String subject, String customerMessage, String staffMessage, String timestamp) {
        this.ticketID = ticketID;  // Generate a unique ticket ID
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.customerMessage = customerMessage;
        this.staffMessage = staffMessage;
        this.timestamp = timestamp;
    }
    public CustomerTickets() {
    }

    /**
     * Add a new ticket to the database.
     *
     * @param from           the sender of the ticket
     * @param to             the recipient of the ticket
     * @param subject        the subject of the ticket
     * @param customerMessage the message content of the ticket
     * @param staffMessage   the staff message associated with the ticket
     * @param timestamp      the timestamp when the ticket was created
     * @throws Exception if there was an error reading or writing to the database
     */
    public void addTicket(String from, String to, String subject, String customerMessage, String staffMessage, String timestamp) throws Exception {
        // Create a JSON object and append all information to it
        JSONObject ticketObject = new JSONObject();
        ticketObject.put("from", from);
        ticketObject.put("to", to);
        ticketObject.put("subject", subject);
        ticketObject.put("customerMessage", customerMessage);
        ticketObject.put("staffMessage", staffMessage);
        ticketObject.put("timestamp", timestamp);

        JSONArray ticketData;

        // Read database
        try (FileReader file = new FileReader(filePath)) {
            JSONParser parser = new JSONParser();
            ticketData = (JSONArray) parser.parse(file);
        } catch (IOException | ParseException error) {
            System.out.println("An error has occurred while reading the DB.");
            throw error;
        }

        // Merge the two objects together
        ticketData.add(ticketObject);

        // Write JSON object to file
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(ticketData.toJSONString());
            System.out.println("Ticket added to the DB.");
        } catch (IOException error) {
            System.out.println("An error has occurred while writing to the DB.");
            throw error;
        }
    }

    /**
     * Requests a book by creating a ticket and adding it to the database.
     *
     * @param bookName the name of the book to request
     * @throws Exception if an error occurs while adding the ticket to the database
     */
    public void requestBook(String bookName) throws Exception {
        // Check if the book exists in the database
            // Create a ticket to request the book
            String from = "Customer";
            String to = "Staff";
            String subject = "Book Request";
            String customerMessage = "I would like to request the book \"" + bookName + "\".";
            String staffMessage = "";
            String timestamp = LocalDateTime.now().toString();

            // Call the createTicket method to add the ticket to the database
            addTicket(from, to, subject, customerMessage, staffMessage, timestamp);

            // Print out
            System.out.println("Book request for \"" + bookName + "\" has been submitted.");
    }

    /**
     * Retrieves customer support tickets from the database that match the specified
     * filter criteria and returns them as an array of CustomerTickets objects.
     *
     * @param fromFilter a string representing the "from" field to filter by, or
     *                   null to include all tickets.
     * @param toFilter   a string representing the "to" field to filter by, or null
     *                   to include all tickets.
     * @return an array of CustomerTickets objects containing the tickets that match
     *         the specified filter criteria.
     */
    public CustomerTickets[] getTickets(String ticketIDFilter) {
        ArrayList<CustomerTickets> matchingTickets = new ArrayList<>();

        // Read database
        JSONArray ticketsData = null;
        try (FileReader file = new FileReader(filePath)) {
            JSONParser parser = new JSONParser();
            ticketsData = (JSONArray) parser.parse(file);
        } catch (IOException | ParseException error) {
            System.out.println("An error has occurred while reading the DB.");
        }

        // Loop over all elements of the JSON array and convert them to CustomerTickets
        // objects
        for (Object obj : ticketsData) {
            JSONObject tempObject = (JSONObject) obj;
            String tempTicketID = (String) tempObject.get("ticketID");
            String tempFrom = (String) tempObject.get("from");
            String tempTo = (String) tempObject.get("to");
            String tempSubject = (String) tempObject.get("subject");
            String tempCustomerMessage = (String) tempObject.get("customerMessage");
            String tempStaffMessage = (String) tempObject.get("staffMessage");
            String tempTimestamp = (String) tempObject.get("timestamp");

            // Check if the ticket matches the filter criteria
            if (ticketIDFilter == null || tempTicketID.equals(ticketIDFilter)) {
                // Create a new CustomerTickets object and add it to the array
                CustomerTickets matchingTicket = new CustomerTickets(tempTicketID, tempFrom, tempTo, tempSubject,
                        tempCustomerMessage, tempStaffMessage, tempTimestamp);
                matchingTickets.add(matchingTicket);
            }
        }

        // Convert the ArrayList to an array and return it
        return matchingTickets.toArray(new CustomerTickets[0]);
    }

    /**
     * Generates a random 3-digit ticket ID.
     *
     * @return a string representing the generated ticket ID.
     */
    private String generateTicketID() {
        Random random = new Random();
        int ticketID = random.nextInt(900) + 100; // Generate a random 3-digit number (between 100 and 999)
        return String.valueOf(ticketID); // Convert the number to a string and return it
    }
}
