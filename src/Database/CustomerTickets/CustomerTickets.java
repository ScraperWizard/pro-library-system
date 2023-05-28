package Database.CustomerTickets;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.util.Date.*;

public class CustomerTickets {
    private final String filePath = "src/Database/CustomerTickets/CustomerTickets.json";
    public String ticketID;
    public String from;
    public String to;
    public String subject;
    private String timestamp;
    public ArrayList<Message> messages;

    public CustomerTickets(String ticketID, String from, String to, String subject, String timestamp, ArrayList<Message> messages) {
        this.ticketID = ticketID;
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.timestamp = timestamp;
        this.messages = messages;
    }
    public CustomerTickets() {
    }

    /**
     * Add a new ticket to the database.
     *
     * @param from           the sender of the ticket
     * @param to             the recipient of the ticket
     * @param subject        the subject of the ticket
     * @throws Exception if there was an error reading or writing to the database
     */
    public String addTicket(String from, String to, String subject) throws Exception {
        // Create a JSON object and append all information to it
        JSONObject ticketObject = new JSONObject();
        String randomID = generateTicketID();
        ticketObject.put("ticketID", randomID);
        ticketObject.put("from", from);
        ticketObject.put("to", to);
        ticketObject.put("subject", subject);
        ticketObject.put("messages", new JSONArray()); // Empty array for messages
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = currentTime.format(formatter);
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

        return randomID;
    }

    /**
     * Remove a ticket from the database based on its ticket number.
     *
     * @param ticketNumber the ticket number of the ticket to remove
     * @throws Exception if there was an error reading or writing to the database
     */
    public void removeTicket(String ticketNumber) throws Exception {
        // Check if collection exists
        System.out.println("Remove book Has been called");
        System.out.println(ticketNumber);

        // Declaration
        JSONArray ticketsData = null;

        // Read database
        try (FileReader file = new FileReader(filePath)) {
            JSONParser parser = new JSONParser();
            ticketsData = (JSONArray) parser.parse(file);
        } catch (IOException | ParseException error) {
            System.out.println("An error has occured while reading DB.");
        }

        // Remove book object from JSONArray using an Iterator
        Iterator<Object> iter = ticketsData.iterator();
        while (iter.hasNext()) {
            JSONObject tempObject = (JSONObject) iter.next();
            System.out.println(tempObject.get("ticketID"));
            if (tempObject.get("ticketID").equals(ticketNumber)) {
                iter.remove();
            }
        }

        // Write json object to file
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(ticketsData.toJSONString()); // write the updated JSON array to file
            System.out.println("Ticket " + ticketNumber + " has been deleted from DB.");
        } catch (IOException error) {
            System.out.println("An error has occured while writing to DB.");
        }
    }

    /**
     * Checks if a customer support ticket exists in the database.
     *
     * @param ticketNumber the ticket number of the ticket to check
     * @throws Exception if there was an error reading or writing to the database
     */
    public boolean ticketExists(String ticketNumber) throws Exception {
        // Declaration
        JSONArray ticketsData = null;

        // Read database
        try (FileReader file = new FileReader(filePath)) {
            JSONParser parser = new JSONParser();
            ticketsData = (JSONArray) parser.parse(file);
        } catch (IOException | ParseException error) {
            System.out.println("An error has occured while reading DB.");
        }

        // Remove book object from JSONArray using an Iterator
        Iterator<Object> iter = ticketsData.iterator();
        while (iter.hasNext()) {
            JSONObject tempObject = (JSONObject) iter.next();
            System.out.println(tempObject.get("ticketID"));
            String tempTicketID = (String) tempObject.get("ticketID");
            if (tempTicketID != null && tempTicketID.equals(ticketNumber)) {
                return true;
            }
        }

        return false;
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
//            addTicket(from, to, subject, customerMessage, staffMessage, timestamp);

            // Print out
            System.out.println("Book request for \"" + bookName + "\" has been submitted.");
    }

    /**
     * Retrieves customer support tickets from the database that match the specified
     * filter criteria and returns them as an array of CustomerTickets objects.
     *
     * @return an array of CustomerTickets objects containing the tickets that match
     *         the specified filter criteria.
     */
    public List<CustomerTickets> getTickets(String ticketIDFilter, String fromFilter) {
        List<CustomerTickets> matchingTickets = new ArrayList<>();

        // Read database
        JSONArray ticketsData = null;
        try (FileReader file = new FileReader(filePath)) {
            JSONParser parser = new JSONParser();
            ticketsData = (JSONArray) parser.parse(file);
        } catch (IOException | org.json.simple.parser.ParseException error) {
            System.out.println("An error has occurred while reading the DB.");
        }

        // Loop over all elements of the JSON array and convert them to CustomerTickets objects
        for (Object obj : ticketsData) {
            JSONObject tempObject = (JSONObject) obj;
            String tempTicketID = (String) tempObject.get("ticketID");
            String tempFrom = (String) tempObject.get("from");
            String tempTo = (String) tempObject.get("to");
            String tempSubject = (String) tempObject.get("subject");
            String tempTimestamp = (String) tempObject.get("timestamp");

            JSONArray messagesArray = (JSONArray) tempObject.get("messages");
            List<Message> messages = new ArrayList<>();

            if (messagesArray != null) {
                for (Object messageObj : messagesArray) {
                    JSONObject messageJson = (JSONObject) messageObj;
                    String sender = (String) messageJson.get("sender");
                    String message = (String) messageJson.get("message");
                    String timestamp = (String) messageJson.get("timestamp");

                    Message messageObject = new Message(sender, message, timestamp);
                    messages.add(messageObject);
                }
            }

            // Check if the ticket matches the filter criteria
            boolean matchesFilter = true;
            if (ticketIDFilter != null && !tempTicketID.equals(ticketIDFilter)) {
                matchesFilter = false;
            }
            if (fromFilter != null && !tempFrom.equals(fromFilter)) {
                matchesFilter = false;
            }

            if (matchesFilter) {
                // Create a new CustomerTickets object and add it to the list
                CustomerTickets matchingTicket = new CustomerTickets(tempTicketID, tempFrom, tempTo, tempSubject, tempTimestamp, (ArrayList<Message>) messages);
                matchingTickets.add(matchingTicket);
            }
        }

        // Return the list of matching tickets
        return matchingTickets;
    }

    public void addMessageToTicket(String ticketID, String sender, String message) throws Exception {
        // Read the existing ticket data
        JSONArray ticketData;
        try (FileReader file = new FileReader(filePath)) {
            JSONParser parser = new JSONParser();
            ticketData = (JSONArray) parser.parse(file);
        } catch (IOException | ParseException error) {
            System.out.println("An error has occurred while reading the DB.");
            throw error;
        }

        // Find the ticket with the specified ticketID
        for (Object obj : ticketData) {
            JSONObject ticket = (JSONObject) obj;
            String currentTicketID = (String) ticket.get("ticketID");
            if (currentTicketID.equals(ticketID)) {
                // Add the new message to the messages array
                JSONArray messages = (JSONArray) ticket.get("messages");
                JSONObject newMessage = new JSONObject();
                newMessage.put("sender", sender);
                newMessage.put("message", message);

                // Format timestamp and add
                LocalDateTime currentTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String timestamp = currentTime.format(formatter);
                newMessage.put("timestamp", timestamp);

                messages.add(newMessage);

                // Update the ticket data
                try (FileWriter file = new FileWriter(filePath)) {
                    file.write(ticketData.toJSONString());
                    System.out.println("Message added to the ticket.");
                    return; // Exit the method after adding the message
                } catch (IOException error) {
                    System.out.println("An error has occurred while writing to the DB.");
                    throw error;
                }
            }
        }

        // If the ticket with the specified ticketID is not found
        System.out.println("Ticket not found.");
    }

    /**
     * Generates a random 3-digit ticket ID.
     *
     * @return a string representing the generated ticket ID.
     */
    private String generateTicketID() {
        List<CustomerTickets> existingTicketIDs = getTickets(null, null);

        Random random = new Random();
        String ticketID;

        if (!existingTicketIDs.isEmpty()) {
            ticketID = String.valueOf(random.nextInt(900) + 100); // Generate a random 3-digit number (between 100 and 999)

            // Check if the generated ID already exists
            while (existingTicketIDs.get(0).ticketID.equals(ticketID)) {
                ticketID = String.valueOf(random.nextInt(900) + 100);
            }
        } else {
            ticketID = String.valueOf(random.nextInt(900) + 100); // Generate a random 3-digit number (between 100 and 999)
        }

        return ticketID;
    }


}

