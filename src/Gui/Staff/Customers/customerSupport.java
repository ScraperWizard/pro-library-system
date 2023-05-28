package Gui.Staff.Customers;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.List;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Database.CustomerTickets.CustomerTickets;
import Database.CustomerTickets.Message;
import Database.Customers.*;
import javax.swing.JTextArea;

public class customerSupport {
    public JFrame jFrame;
    private JTextField subjectInput;
    private JTextField fromInput;
    private JTextField replyInput;
    public customerSupport() {
        jFrame = new JFrame();
        Customers globalCustomersObject = new Customers();
        CustomerTickets globalCustomerTicketsObject = new CustomerTickets();

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setBounds(500, 500, 848, 516);
        jFrame.setTitle("Staff - Customer support");

        JPanel panel = new JPanel();
        jFrame.setContentPane(panel);
        panel.setBackground(new Color(57, 130, 146));
        panel.setLayout(null);

        JLabel customerSupportLbl = new JLabel("Customer support");
        customerSupportLbl.setBounds(0, 0, 848, 35);
        panel.add(customerSupportLbl);
        customerSupportLbl.setForeground(new Color(254, 255, 255));
        customerSupportLbl.setFont(new Font("Dialog", Font.BOLD, 18));
        customerSupportLbl.setHorizontalAlignment(SwingConstants.CENTER);
        
        JButton closeTicketBtn = new JButton("Close ticket");
        closeTicketBtn.setForeground(new Color(255, 38, 0));
        closeTicketBtn.setBounds(730, 25, 117, 29);
        panel.add(closeTicketBtn);
        
        JButton viewMessages = new JButton("View all messages");
        viewMessages.setForeground(new Color(255, 146, 0));
        viewMessages.setBounds(689, 235, 158, 29);
        panel.add(viewMessages);
        
        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.setBounds(20, 440, 134, 35);
        panel.add(refreshBtn);

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(160, 13, 685, 470);
        mainPanel.setBackground(new Color(57, 130, 146));
        panel.add(mainPanel);
        mainPanel.setLayout(new GridLayout(0, 1, 0, 0));

        JLabel emailLabel = new JLabel("From:");
        emailLabel.setForeground(new Color(254, 255, 255));
        emailLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        mainPanel.add(emailLabel);

        fromInput = new JTextField();
        fromInput.setEnabled(false);
        fromInput.setEditable(false);
        mainPanel.add(fromInput);
        fromInput.setColumns(10);

        JLabel subjectLabel = new JLabel("Subject:");
        subjectLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        subjectLabel.setForeground(new Color(254, 255, 255));
        mainPanel.add(subjectLabel);

        subjectInput = new JTextField();
        subjectInput.setEnabled(false);
        subjectInput.setEditable(false);
        mainPanel.add(subjectInput);
        subjectInput.setColumns(10);

        JLabel messageLabel = new JLabel("Last message from customer:");
        messageLabel.setForeground(new Color(254, 255, 255));
        messageLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        mainPanel.add(messageLabel);
        
        JTextArea messageInput = new JTextArea();
        messageInput.setLineWrap(true); // Enable line wrapping
        messageInput.setWrapStyleWord(true); // Wrap at word boundaries
        messageInput.setEditable(false);
        messageInput.setEnabled(false);
        mainPanel.add(messageInput);

        JLabel replyLabel = new JLabel("Reply to customer:");
        replyLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        replyLabel.setForeground(new Color(254, 255, 255));
        mainPanel.add(replyLabel);

        replyInput = new JTextField();
        mainPanel.add(replyInput);
        replyInput.setColumns(10);

        JButton replyButton = new JButton("Send!");
        replyButton.setForeground(new Color(0, 0, 0));
        replyButton.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        mainPanel.add(replyButton);

        JList<String> ticketsList = new JList();
        ticketsList.setForeground(new Color(254, 255, 255));
        ticketsList.setBorder(new LineBorder(new Color(44, 100, 144)));
        ticketsList.setBounds(10, 35, 146, 393);
        panel.add(ticketsList);
        ticketsList.setVisibleRowCount(3);
        ticketsList.setBackground(new Color(57, 130, 146));
        ticketsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Package all data
        setModel(globalCustomerTicketsObject, ticketsList);

        // Change inputs whenever selecting a username
        ticketsList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt) {
                // Get the selected value from the ticketsList
                String selectedValue = (String) ticketsList.getSelectedValue();

                // Check if the selection has changed and a value is selected
                if (!evt.getValueIsAdjusting() && selectedValue != null) {
                    // Extract the ticket ID from the selectedValue
                    String ticketID = selectedValue.split("#")[1].split(" - ")[0];

                    // Get the selected customer ticket based on the ticket ID
                    List<CustomerTickets> selectedCustomerTicket = globalCustomerTicketsObject.getTickets(ticketID, null);

                    // Get the current ticket
                    CustomerTickets currentTicket = selectedCustomerTicket.get(0);

                    // Get the messages associated with the current ticket
                    List<Message> messages = selectedCustomerTicket.get(0).messages;

                    // Update the subject and from fields
                    subjectInput.setText(currentTicket.subject);
                    fromInput.setText(currentTicket.from);

                    // Check if there are messages for the ticket
                    if (!messages.isEmpty()) {
                        // Iterate through the messages in descending order
                        for (int i = messages.size() - 1; i >= 0; i--) {
                            // Find the latest message sent by the ticket's owner
                            if (messages.get(i).getSender().equals(currentTicket.from)) {
                                // Set the messageInput with the latest message
                                messageInput.setText(messages.get(i).getMessage());
                                break;
                            }
                        }
                    } else {
                        messageInput.setText("Null");
                        System.out.println("No messages found.");
                    }
                }
            }
        });

        // Select first ticket by default
        ticketsList.setSelectedIndex(0);

        closeTicketBtn.addActionListener(clickEvent -> {
            String selectedTicketNumber = ticketsList.getSelectedValue().split("#")[1].split(" - ")[0];
            try {
                globalCustomerTicketsObject.removeTicket(selectedTicketNumber);
                JOptionPane.showMessageDialog(panel, "Ticket " + selectedTicketNumber + " has been closed.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(panel, "Error occured while trying to close ticket!");
                throw new RuntimeException(e);
            }
        });

        // Save data when clicking save
        replyButton.addActionListener(clickEvent -> {
            String replyValue = replyInput.getText();
            String ticketID = ticketsList.getSelectedValue().split("#")[1].split(" - ")[0];
            List<CustomerTickets> selectedCustomerTicket = globalCustomerTicketsObject.getTickets(ticketID, null);

            if(replyValue.equals("")) {
                JOptionPane.showMessageDialog(panel, "Please fill in fields!");
            } else {
                try {
                    replyInput.setText("");
                    globalCustomerTicketsObject.addMessageToTicket(ticketID, selectedCustomerTicket.get(0).to, replyValue);
                    JOptionPane.showMessageDialog(panel, "Message has been sent!");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        viewMessages.addActionListener(clickEvent -> {
            new customerSupportAllMessages(ticketsList.getSelectedValue().split("#")[1].split(" - ")[0]);
        });

        refreshBtn.addActionListener(clickEvent -> {
            setModel(globalCustomerTicketsObject, ticketsList);
        });

    }
    public void setModel(CustomerTickets globalCustomerTicketsObject, JList<String> ticketsList) {
        // Package all data
        String[] values;
        List<CustomerTickets> allTickets = globalCustomerTicketsObject.getTickets(null, null);
        values = new String[allTickets.size()];

        for (int i = 0; i < allTickets.size(); i++) {
            values[i] = "#" + allTickets.get(i).ticketID +  " - " + allTickets.get(i).subject;
        }

        ticketsList.setModel(new AbstractListModel<String>() {
            public int getSize() {
                return values.length;
            }
            public String getElementAt(int index) {
                return values[index];
            }
        });

        ticketsList.setSelectedIndex(0);
    }
}
