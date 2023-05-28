package Gui.Customers.HelpDesk;

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
import java.time.LocalTime;

import Database.CustomerTickets.CustomerTickets;
import Database.Customers.*;

public class newRequestPanel {
    public JFrame jFrame;
    private JTextField subjectInput;
    private JTextField messageInput;

    public newRequestPanel(String username) {
        jFrame = new JFrame();
        Customers globalCustomersObject = new Customers();
        CustomerTickets globalCustomerTicketsObject = new CustomerTickets();
        Customers currentUser = globalCustomersObject.getUser(username);

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setBounds(500, 500, 550, 350);
        jFrame.setTitle("Customers - Customer support");

        JPanel panel = new JPanel();
        jFrame.setContentPane(panel);
        panel.setBackground(new Color(76, 128, 144));
        panel.setLayout(null);

        JLabel customerSupportLbl = new JLabel("New request");
        customerSupportLbl.setBounds(0, 0, 550, 35);
        panel.add(customerSupportLbl);
        customerSupportLbl.setForeground(new Color(254, 255, 255));
        customerSupportLbl.setFont(new Font("Dialog", Font.BOLD, 18));
        customerSupportLbl.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(25, 35, 500, 238);
        mainPanel.setBackground(new Color(76, 128, 144));
        panel.add(mainPanel);
        mainPanel.setLayout(new GridLayout(0, 1, 0, 0));

        JLabel subjectLabel = new JLabel("Subject:");
        subjectLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        subjectLabel.setForeground(new Color(254, 255, 255));
        mainPanel.add(subjectLabel);

        subjectInput = new JTextField();
        mainPanel.add(subjectInput);
        subjectInput.setColumns(10);

        JLabel messageLabel = new JLabel("Message:");
        messageLabel.setForeground(new Color(254, 255, 255));
        messageLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        mainPanel.add(messageLabel);

        messageInput = new JTextField();
        mainPanel.add(messageInput);
        messageInput.setColumns(10);

        JButton sendButton = new JButton("Send!");
        sendButton.setForeground(new Color(0, 0, 0));
        sendButton.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        mainPanel.add(sendButton);

        // Save data when clicking save
        sendButton.addActionListener(clickEvent -> {
            if(!subjectInput.getText().equals("") && !messageInput.getText().equals("")) {
                LocalTime nowTimeStamp = LocalTime.now();
                try {
                    String ticketID = globalCustomerTicketsObject.addTicket(currentUser.email, "staff", subjectInput.getText());
                    globalCustomerTicketsObject.addMessageToTicket(ticketID, currentUser.email, messageInput.getText());
                    JOptionPane.showMessageDialog(panel, "Thank you! Your issue will be looked after");
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            else {
                JOptionPane.showMessageDialog(panel, "Please fill in fields!");
            }
        });

    }
}