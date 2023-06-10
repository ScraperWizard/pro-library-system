package Database.CustomerTickets;

import java.util.List;

public interface CustomerSupport {
    public String addTicket(String from, String to, String subject) throws Exception;
    public void removeTicket(String ticketNumber) throws Exception;
    public boolean ticketExists(String ticketNumber) throws Exception;
    public void requestBook(String bookName) throws Exception;
    public List<CustomerTickets> getTickets(String ticketIDFilter, String fromFilter);
    public void addMessageToTicket(String ticketID, String sender, String message) throws Exception;
}
