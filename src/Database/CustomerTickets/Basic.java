package Database.CustomerTickets;

import Database.InvalidPermissionsException;

import java.util.List;

public class Basic implements CustomerSupport {
    CustomerTickets globalCustomerTicketsObject;

    public Basic() {
        globalCustomerTicketsObject = new CustomerTickets();
    }
    @Override
    public String addTicket(String from, String to, String subject) throws Exception {
        return globalCustomerTicketsObject.addTicket(from, to, subject);
    }

    @Override
    public void removeTicket(String ticketNumber) throws Exception {
        throw new InvalidPermissionsException();
    }

    @Override
    public boolean ticketExists(String ticketNumber) throws Exception {
        throw new InvalidPermissionsException();
    }

    @Override
    public void requestBook(String bookName) throws Exception {
        globalCustomerTicketsObject.requestBook(bookName);
    }

    @Override
    public List<CustomerTickets> getTickets(String ticketIDFilter, String fromFilter) {
        return globalCustomerTicketsObject.getTickets(ticketIDFilter, fromFilter);
    }

    @Override
    public void addMessageToTicket(String ticketID, String sender, String message) throws Exception {
        globalCustomerTicketsObject.addMessageToTicket(ticketID, sender, message);
    }
}
