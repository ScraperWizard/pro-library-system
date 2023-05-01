package Gui.loginSelector;

import Database.Customers.Customers;
import Gui.Login.Login;
import Gui.staffLogin.staffLogin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginSelector {

    public JPanel contentPane;

    public loginSelector(JFrame mainFrame) {
        // DB user object
        Customers globalCustomersObject = new Customers();

        // Declare new JPanel
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);

        // Get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (screenSize.width) / 2;
        int screenHeight = (screenSize.height) / 2;

        // Staff login heading
        JLabel lblStaffHeading = new JLabel("Welcome to Pro Library!");
        lblStaffHeading.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        lblStaffHeading.setBounds(533, 89, 400, 40);
        contentPane.add(lblStaffHeading);

        // Staff login button
        JButton staffLoginButton = new JButton("Staff");
        staffLoginButton.setForeground(new Color(255, 0, 0));
        staffLoginButton.setBounds(567, 292, 225, 55);
        contentPane.add(staffLoginButton);

        // Customer login button
        JButton customerLoginButton = new JButton("Customer");
        customerLoginButton.setForeground(new Color(0, 15, 255));
        customerLoginButton.setBounds(567, 178, 225, 55);
        contentPane.add(customerLoginButton);
        
        JLabel UserChoiceVar = new JLabel("You are a ");
        UserChoiceVar.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        UserChoiceVar.setHorizontalAlignment(SwingConstants.LEFT);
        UserChoiceVar.setBounds(638, 140, 161, 27);
        contentPane.add(UserChoiceVar);

        // staff event listener
        staffLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent clickEvent) {
                contentPane.setVisible(false);
                staffLogin staffLoginFrame = new staffLogin(mainFrame);
                mainFrame.setContentPane(staffLoginFrame.contentPane);
            }
        });

        // Customer event listener
        customerLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent clickEvent) {
                contentPane.setVisible(false);
                Login cusomterLoginFrame = new Login(mainFrame);
                mainFrame.setContentPane(cusomterLoginFrame.contentPane);
            }
        });

        // Full screen, and set it to visible
        contentPane.setVisible(true);
    }
}