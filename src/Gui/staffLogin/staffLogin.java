package Gui.staffLogin;

import Database.Users.User;
import Gui.Login.Login;
import Gui.loginSelector.loginSelector;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class staffLogin {

    public JPanel contentPane;
    private JTextField uniqueIDInput;

    public staffLogin( JFrame mainFrame) {
        // DB user object
        User globalUserObject = new User();

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
        lblUniqueID.setBounds(screenWidth - 87, screenHeight - 135, 206, 13);
        contentPane.add(lblUniqueID);

        // Input field
        uniqueIDInput = new JPasswordField();
        uniqueIDInput.setBounds(screenWidth - 90, screenHeight - 120, 150, 30);
        contentPane.add(uniqueIDInput);
        uniqueIDInput.setColumns(10);

        // Staff login button
        JButton staffLoginButton = new JButton("Login");
        staffLoginButton.setForeground(new Color(0, 52, 255));
        staffLoginButton.setBounds(screenHeight + 150, screenWidth - 350, 300, 100);
        contentPane.add(staffLoginButton);
        staffLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent clickEvent) {
                String uniqueId = uniqueIDInput.getText();
                contentPane.setVisible(false);
                Login newLogin = new Login(mainFrame);
                mainFrame.setContentPane(newLogin.contentPane);
            }
        });

        // Staff login button
        JButton returnButton = new JButton("<- Return");
        returnButton.setForeground(new Color(255, 0, 0));
        returnButton.setBounds(5, 5, 100, 40);
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
