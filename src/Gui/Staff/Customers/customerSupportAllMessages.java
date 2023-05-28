package Gui.Staff.Customers;

import Database.CustomerTickets.CustomerTickets;
import Database.CustomerTickets.Message;
import Database.Logs.Logs;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.lang.reflect.Field;

public class customerSupportAllMessages {
    public JFrame jFrame;
    public customerSupportAllMessages(String ticketID) {
        jFrame = new JFrame();
        CustomerTickets globalCustomerTicketsObject = new CustomerTickets();

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setBounds(500, 500, 500, 400);
        jFrame.setTitle("Staff - View messages");

        JPanel panel = new JPanel();
        jFrame.setContentPane(panel);
        panel.setBackground(new Color(57, 130, 146));
        panel.setLayout(null);

        JLabel headerLabel = new JLabel("View All Messages - #" + ticketID);
        headerLabel.setBounds(63, 0, 367, 35);
        panel.add(headerLabel);
        headerLabel.setForeground(new Color(254, 255, 255));
        headerLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(103, 30, 300, 163);
        mainPanel.setBackground(new Color(57, 130, 146));
        panel.add(mainPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Retrieve announcements from globalAnnouncementsObject
        List<CustomerTickets> selectedCustomerTicket = globalCustomerTicketsObject.getTickets(ticketID, null);
        List<Message> messages = selectedCustomerTicket.get(0).messages;

        for (Message message: messages) {
            JTextArea announcementTextArea = createAnnouncementTextArea(message, selectedCustomerTicket.get(0).from);
            mainPanel.add(announcementTextArea);
        }

        // Scrolling
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBounds(50, 30, 400, 320);
        panel.add(scrollPane);

    }

    private JTextArea createAnnouncementTextArea(Message message, String from) {
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(new Color(57, 130, 146)); // Set background color
        textArea.setForeground(Color.WHITE); // Set text color
        textArea.setFont(new Font("Dialog", Font.PLAIN, 14));

        textArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE), // Set border color
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        String role = message.getSender().equals(from) ? "Customer": "Staff";
        // Loop over all messages and add them to finalString
        StringBuilder finalString = new StringBuilder();
            finalString.append("Sender (" + role + "): ").append(message.getSender()).append("\n");
            finalString.append("Message: ").append(message.getMessage()).append("\n");
            finalString.append("Timestamp: ").append(message.getTimestamp()).append("\n");
            finalString.append("\n");

        textArea.setText(finalString.toString());

        return textArea;
    }
}

