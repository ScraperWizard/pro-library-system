package Gui.Customers.HelpDesk;

import Database.BooksDB.BooksDB;
import Database.CustomerTickets.CustomerTickets;
import Database.CustomerTickets.Message;
import Database.Customers.Customers;
import Database.Staff.Staff;
import Gui.Customers.Settings.changePassword;
import Gui.Staff.Customers.Customer;
import Gui.Staff.Customers.customerSupportAllMessages;
import Gui.Staff.Home.announcements;
import Gui.Staff.sideMenu.sideMenu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalTime;
import java.util.List;

public class HelpDesk {
    public JPanel helpDeskPane;
    private JTextField subjectInput;
    private JTextField fromInput;
    private JTextArea messageInput;
    private JTextField replyInput;

    public HelpDesk(JFrame mainFrame, String username) {
        Staff globalStaffObject = new Staff();
        Customers globalCustomerObject = new Customers();
        CustomerTickets globalCustomerTicketsObject = new CustomerTickets();
        Customers currentUser = globalCustomerObject.getUser(username);

        helpDeskPane = new JPanel();
        helpDeskPane.setBackground(new Color(76, 128, 144));
        helpDeskPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        helpDeskPane.setLayout(null);

        // Get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (screenSize.width) / 2;
        int screenHeight = (screenSize.height) / 2;
        helpDeskPane.setBounds(270, 0, screenSize.width - 250, screenSize.height);

        JLabel headerLabel = new JLabel("Customers - Help Desk");
        headerLabel.setForeground(new Color(254, 255, 255));
        headerLabel.setFont(new Font("Dialog", Font.BOLD, 24));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setVerticalAlignment(SwingConstants.CENTER);
        headerLabel.setBounds(-120, 0, screenSize.width - 250, 39);
        helpDeskPane.add(headerLabel);

        Staff globalStaffObject1 = new Staff();

        Staff [] allStaff = globalStaffObject1.getAllStaff();

        // Table
        String[] columnNames = {"Name", "Email", "Contact Number"};

        Object[][] data = new Object[allStaff.length][3];

        LocalTime nowTiming = LocalTime.now(); //Getting the recent time
        LocalTime before8pm = LocalTime.of(20, 0); // Setting 8 pm as an off time
        LocalTime atfter8am = LocalTime.of(8, 0); //Setting 8 am as a start time

        for(int i = 0; i < allStaff.length; i++) {
            data[i][0] = allStaff[i].username;
            data[i][1] = allStaff[i].email;
            if (nowTiming.isBefore(before8pm) && nowTiming.isAfter(atfter8am))
                data[i][2] = allStaff[i].contact;
            else
                data[i][2] = "See you between 8am and 8pm <3";
        }

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(320, 50, 710, data[0].length * 30);
        helpDeskPane.add(scrollPane_1);

        JTable table_2 = new JTable(data, columnNames);
        table_2.setShowGrid(false);
        table_2.setEnabled(false);
        table_2.setBackground(new Color(88, 127, 143));
        scrollPane_1.setViewportView(table_2);

        JPanel panel = new JPanel();
        panel.setLocation(250, 135);
        panel.setSize(850, 430);
        panel.setBackground(new Color(76, 128, 144));
        panel.setLayout(null);
        helpDeskPane.add(panel);

        JButton newRequestBtn = new JButton("New Request");
        newRequestBtn.setForeground(new Color(50, 205, 50));
        newRequestBtn.setBounds(730, 25, 117, 29);
        panel.add(newRequestBtn);

        newRequestBtn.addActionListener(clickEvent -> {
            new newRequestPanel(username);
        });

        JButton viewMessages = new JButton("View all messages");
        viewMessages.setForeground(new Color(255, 146, 0));
        viewMessages.setBounds(689, 200, 158, 29);
        panel.add(viewMessages);
        
        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.setForeground(new Color(0, 0, 0));
        btnRefresh.setBounds(10, 373, 146, 29);
        panel.add(btnRefresh);

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(160, 13, 685, 400);
        mainPanel.setBackground(new Color(76, 128, 144));
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

        JLabel messageLabel = new JLabel("Last message from staff:");
        messageLabel.setForeground(new Color(254, 255, 255));
        messageLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        mainPanel.add(messageLabel);

        messageInput = new JTextArea();
        messageInput.setLineWrap(true); // Enable line wrapping
        messageInput.setWrapStyleWord(true); // Wrap at word boundaries
        messageInput.setEditable(false);
        messageInput.setEnabled(false);
        mainPanel.add(messageInput);

        JLabel replyLabel = new JLabel("Reply to staff:");
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
        ticketsList.setBounds(10, 35, 146, 332);
        panel.add(ticketsList);
        ticketsList.setVisibleRowCount(3);
        ticketsList.setBackground(new Color(76, 128, 144));
        ticketsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Select first ticket by default
        ticketsList.setSelectedIndex(0);

        // Package all data
        setModel(globalCustomerTicketsObject, ticketsList, currentUser.email);

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
                    List<CustomerTickets> selectedCustomerTicket = globalCustomerTicketsObject.getTickets(ticketID, currentUser.email);

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
                            if (messages.get(i).getSender().equals(currentTicket.to)) {
                                // Set the messageInput with the latest message
                                messageInput.setText(messages.get(i).getMessage());
                                break;
                            } else {
                                messageInput.setText("<- Pending reply ->");
                            }
                        }
                    } else {
                        messageInput.setText("<- Pending reply ->");
                        System.out.println("No messages found.");
                    }
                }
            }
        });

        // Save data when clicking save
        replyButton.addActionListener(clickEvent -> {
            String replyValue = replyInput.getText();
            String ticketID = ticketsList.getSelectedValue().split("#")[1].split(" - ")[0];
            List<CustomerTickets> selectedCustomerTicket = globalCustomerTicketsObject.getTickets(ticketID, currentUser.email);

            if(replyValue.equals("")) {
                JOptionPane.showMessageDialog(panel, "Please fill in fields!");
            } else {
                try {
                    replyInput.setText("");
                    globalCustomerTicketsObject.addMessageToTicket(ticketID, selectedCustomerTicket.get(0).from, replyValue);
                    JOptionPane.showMessageDialog(panel, "Message has been sent!");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        viewMessages.addActionListener(clickEvent -> {
            new customerSupportAllMessages(ticketsList.getSelectedValue().split("#")[1].split(" - ")[0]);
        });

        btnRefresh.addActionListener(clickEvent -> {
            setModel(globalCustomerTicketsObject, ticketsList, currentUser.email);
        });
    }
    public void setModel(CustomerTickets globalCustomerTicketsObject, JList<String> ticketsList, String email) {
        // Package all data
        String[] values;
        List<CustomerTickets> allTickets = globalCustomerTicketsObject.getTickets(null, email);
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
