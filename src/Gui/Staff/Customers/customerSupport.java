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
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Database.CustomerTickets.CustomerTickets;
import Database.Customers.*;

public class customerSupport {
    public JFrame jFrame;
    private JTextField subjectInput;
    private JTextField fromInput;
    private JTextField messageInput;
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

        JLabel messageLabel = new JLabel("Message:");
        messageLabel.setForeground(new Color(254, 255, 255));
        messageLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        mainPanel.add(messageLabel);

        messageInput = new JTextField();
        messageInput.setEditable(false);
        messageInput.setEnabled(false);
        mainPanel.add(messageInput);
        messageInput.setColumns(10);

        JLabel replyLabel = new JLabel("Reply:");
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
        ticketsList.setBounds(10, 35, 146, 447);
        panel.add(ticketsList);
        ticketsList.setVisibleRowCount(3);
        ticketsList.setBackground(new Color(57, 130, 146));
        ticketsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Package all data
        setModel(globalCustomerTicketsObject, ticketsList);

        // Change inputs whenever selecting a username
        ticketsList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt) {
                String selectedValue = (String) ticketsList.getSelectedValue();
                if (!evt.getValueIsAdjusting() && selectedValue != null) {
                    System.out.println(selectedValue.split(" - ")[0]);
                    CustomerTickets[] selectedCustomerTicket = globalCustomerTicketsObject.getTickets(selectedValue.split(" - ")[0].split("#")[1]);

                    messageInput.setText(selectedCustomerTicket[0].customerMessage);
                    subjectInput.setText(selectedCustomerTicket[0].subject);
                    fromInput.setText(selectedCustomerTicket[0].from);
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
        String[] values;
        CustomerTickets[] allCustomerTickets = globalCustomerTickets.getTickets(null);
        values = new String[allCustomerTickets.length];

        for(int i = 0; i < allCustomerTickets.length; i++)
            values[i] = "#" + allCustomerTickets[i].ticketID +  " - " + allCustomerTickets[i].subject;

        ticketsList.setModel(new AbstractListModel<String>() {
            public int getSize() {
                return values.length;
            }
            public String getElementAt(int index) {
                return values[index];
            }
        });
    }
}
