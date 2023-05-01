package Gui.staffLogin;

import Database.Staff.Staff;
import Gui.Staff.Customers.Customers;
import Gui.Staff.sideMenu.sideMenu;
import Gui.loginSelector.loginSelector;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class staffLogin {

    public JPanel contentPane;
    private JTextField uniqueIDInput;

    public staffLogin( JFrame mainFrame) {
        // DB user object
        Staff globalStaffObject = new Staff();

        // Declare new JPanel
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);

        // Get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (screenSize.width) / 2;
        int screenHeight = (screenSize.height) / 2;

        // Staff login heading
        JLabel lblStaffHeading = new JLabel("Staff Login");
        lblStaffHeading.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        lblStaffHeading.setBounds(screenWidth - 90, screenHeight - 250, 200, 40);
        contentPane.add(lblStaffHeading);

        // Unique ID heading
        JLabel lblUniqueID = new JLabel("Enter your unique ID");
        lblUniqueID.setBounds(571, 246, 206, 13);
        contentPane.add(lblUniqueID);

        // Input field
        uniqueIDInput = new JPasswordField();
        uniqueIDInput.setBounds(571, 264, 200, 20);
        contentPane.add(uniqueIDInput);
        uniqueIDInput.setColumns(10);

        // Staff login button
        JButton staffLoginButton = new JButton("Login");
        staffLoginButton.setForeground(new Color(0, 52, 255));
        staffLoginButton.setBounds(560, 329, 222, 31);
        contentPane.add(staffLoginButton);
        
        // Staff login handler
        staffLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent clickEvent) {
                String uniqueId = uniqueIDInput.getText();
                if(globalStaffObject.validateLogin(uniqueId)) {
                    contentPane.setVisible(false);
                    sideMenu sideMenuFrame = new sideMenu(mainFrame, uniqueId);
                    mainFrame.setContentPane(sideMenuFrame.contentPane);
                } else {
                    lblUniqueID.setText("Incorrect ID");
                    lblUniqueID.setForeground(Color.RED);
                    JOptionPane.showMessageDialog(null, "Invalid login", "Login attempt", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // Staff login button
        JButton returnButton = new JButton("< Return");
        returnButton.setForeground(new Color(255, 0, 0));
        returnButton.setBounds(5, 5, 100, 20);
        contentPane.add(returnButton);
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent clickEvent) {
                contentPane.setVisible(false);
                loginSelector loginSelectorFrame = new loginSelector(mainFrame);
                mainFrame.setContentPane(loginSelectorFrame.contentPane);
            }
        });

        // Full screen, and set it to visible
        contentPane.setVisible(true);
    }


}
