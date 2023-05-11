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
        int screenWidth = (screenSize.width) / 2;
        int screenHeight = (screenSize.height) / 2;
        
        LineBorder line = new LineBorder(new Color(87, 105, 120), 4, true);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{718, 303, 0, 0};
        gbl_contentPane.rowHeights = new int[]{262, 253, 0, 0};
        gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);
         
         // Middle panel
         JPanel loginSelectorPanel = new JPanel();
         loginSelectorPanel.setBackground(new Color(57, 130, 146));
         loginSelectorPanel.setForeground(new Color(57, 130, 146));
         loginSelectorPanel.setBorder(null);
         GridBagConstraints gbc_loginSelectorPanel = new GridBagConstraints();
         gbc_loginSelectorPanel.fill = GridBagConstraints.BOTH;
         gbc_loginSelectorPanel.gridx = 1;
         gbc_loginSelectorPanel.gridy = 1;
         contentPane.add(loginSelectorPanel, gbc_loginSelectorPanel);
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