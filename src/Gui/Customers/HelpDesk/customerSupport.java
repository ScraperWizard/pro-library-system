package Gui.Customers.HelpDesk;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Database.CustomerTickets.CustomerTickets;
import Database.Customers.*;

public class customerSupport {
    public JFrame jFrame;
    private JTextField subjectInput;
    private JTextField fromInput;
    private JTextField replyInput;

    public customerSupport(String username) {
        jFrame = new JFrame();
        Customers globalCustomersObject = new Customers();
        CustomerTickets globalCustomerTicketsObject = new CustomerTickets();
        Customers currentUser = globalCustomersObject.getUser(username);

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setBounds(500, 500, 848, 516);
        jFrame.setTitle("Staff - Customer support");

        JPanel panel = new JPanel();
        jFrame.setContentPane(panel);
        panel.setBackground(new Color(76, 128, 144));
        panel.setLayout(null);

        JLabel customerSupportLbl = new JLabel("Customer support");
        customerSupportLbl.setBounds(0, 0, 848, 35);
        panel.add(customerSupportLbl);
        customerSupportLbl.setForeground(new Color(254, 255, 255));
        customerSupportLbl.setFont(new Font("Dialog", Font.BOLD, 18));
        customerSupportLbl.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(181, 70, 641, 396);
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
        mainPanel.add(subjectInput);
        subjectInput.setColumns(10);
        subjectInput.setEnabled(false);

        JLabel messageLabel = new JLabel("Message:");
        messageLabel.setForeground(new Color(254, 255, 255));
        messageLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        mainPanel.add(messageLabel);

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
        ticketsList.setBounds(10, 35, 146, 447);
        panel.add(ticketsList);
        ticketsList.setVisibleRowCount(3);
        ticketsList.setBackground(new Color(57, 130, 146));
        ticketsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Package all data
        setModel(globalCustomerTicketsObject, ticketsList);

        JButton newTicketButton = new JButton("New Request");
        newTicketButton.setForeground(new Color(50, 205, 50));
        newTicketButton.setBounds(640, 30, 182, 29);
        panel.add(newTicketButton);

        newTicketButton.addActionListener(clickEvent -> {
            jFrame.dispose();
            newRequestPanel chosenNewRequest = new newRequestPanel(username);
        });

        // Change inputs whenever selecting a username
        ticketsList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt) {
                String selectedValue = (String) ticketsList.getSelectedValue();
                if (!evt.getValueIsAdjusting() && selectedValue != null) {
//                    System.out.println(selectedValue.split(" - ")[0]);
//                    CustomerTickets[] selectedCustomerTicket = globalCustomerTicketsObject.getTickets(selectedValue.split(" - ")[0].split("#")[1]);
//                    subjectInput.setText(selectedCustomerTicket[0].subject);
//                    fromInput.setText(selectedCustomerTicket[0].to);
                }
            }
        });

        // Save data when clicking save
        replyButton.addActionListener(clickEvent -> {
            String selectedValue = (String) ticketsList.getSelectedValue();
            String replyValue = replyInput.getText();

            if(replyValue.equals("")) {
                JOptionPane.showMessageDialog(panel, "Please fill in fields!");
            } else {
                JOptionPane.showMessageDialog(panel, selectedValue + " has been updated.");
                setModel(globalCustomerTicketsObject, ticketsList);
            }
        });

    }

    public void setModel(CustomerTickets globalCustomerTickets, JList<String> ticketsList) {
        // Package all data
//        String[] values;
//        CustomerTickets[] allCustomerTickets = globalCustomerTickets.getTickets(null);
//        values = new String[allCustomerTickets.length];
//
//        for(int i = 0; i < allCustomerTickets.length; i++)
//            values[i] = "#" + allCustomerTickets[i].ticketID +  " - " + allCustomerTickets[i].subject;
//
//        ticketsList.setModel(new AbstractListModel<String>() {
//            public int getSize() {
//                return values.length;
//            }
//            public String getElementAt(int index) {
//                return values[index];
//            }
//        });
    }
}