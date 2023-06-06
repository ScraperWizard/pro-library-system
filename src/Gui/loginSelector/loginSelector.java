package Gui.loginSelector;

import Database.Customers.Customers;
import Gui.Login.Login;
import Gui.staffLogin.staffLogin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;

public class loginSelector {

    public JPanel contentPane;

    public loginSelector(JFrame mainFrame) {
        // DB user object
        Customers globalCustomersObject = new Customers();

        // Declare new JPanel
        contentPane = new JPanel();
        contentPane.setBackground(new Color(57, 130, 146));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        // Get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int frameWidth = 500;
        int frameHeight = 400;
        int frameX = (screenWidth - frameWidth) / 2;
        int frameY = (screenHeight - frameHeight) / 2;

        mainFrame.setBounds(frameWidth, frameY, frameWidth, frameHeight);
        mainFrame.setTitle("Pro Library - Welcome!");


        LineBorder line = new LineBorder(new Color(87, 105, 120), 4, true);
         contentPane.setLayout(null);

         // Middle panel
         JPanel loginSelectorPanel = new JPanel();
         loginSelectorPanel.setBounds((frameWidth - 303) / 2, 50, 303, 253);
         loginSelectorPanel.setBackground(new Color(57, 130, 146));
         loginSelectorPanel.setForeground(new Color(57, 130, 146));
         loginSelectorPanel.setBorder(null);
         contentPane.add(loginSelectorPanel);
         loginSelectorPanel.setLayout(new GridLayout(0, 1, 0, 0));
         
         // Staff login heading
         JLabel lblStaffHeading = new JLabel("Welcome to Pro Library!");
         lblStaffHeading.setHorizontalAlignment(SwingConstants.CENTER);
         lblStaffHeading.setForeground(new Color(254, 255, 255));
         loginSelectorPanel.add(lblStaffHeading);
         lblStaffHeading.setFont(new Font("Dialog", Font.BOLD, 22));
         
         // UserChoice heading        
         JLabel UserChoiceVar = new JLabel("You are a ");
         UserChoiceVar.setForeground(new Color(254, 255, 255));
         loginSelectorPanel.add(UserChoiceVar);
         UserChoiceVar.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
         UserChoiceVar.setHorizontalAlignment(SwingConstants.CENTER);
         
         // Customer login button
         JButton customerLoginButton = new JButton("Customer");
         loginSelectorPanel.add(customerLoginButton);
         customerLoginButton.setForeground(new Color(0, 15, 255));
         
         // Customer event listener
         customerLoginButton.addActionListener(new ActionListener() {
        	 public void actionPerformed(ActionEvent clickEvent) {
        		 contentPane.setVisible(false);
        		 Login cusomterLoginFrame = new Login(mainFrame);
                 mainFrame.setContentPane(cusomterLoginFrame.contentPane);
             }
         });
         
         // Staff login button
         JButton staffLoginButton = new JButton("Staff");
         loginSelectorPanel.add(staffLoginButton);
         staffLoginButton.setForeground(new Color(255, 0, 0));
                                
         // Staff event listener
         staffLoginButton.addActionListener(new ActionListener() {
        	 public void actionPerformed(ActionEvent clickEvent) {
                 contentPane.setVisible(false);
                 staffLogin staffLoginFrame = new staffLogin(mainFrame);
                  mainFrame.setContentPane(staffLoginFrame.contentPane);
             }
         });

        // Full screen, and set it to visible
        contentPane.setVisible(true);
    }
}