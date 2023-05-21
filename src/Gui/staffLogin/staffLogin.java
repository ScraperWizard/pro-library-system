package Gui.staffLogin;

import Database.Maintenance.Maintenance;
import Database.Staff.Staff;
import Gui.Staff.sideMenu.sideMenu;
import Gui.loginSelector.loginSelector;
import com.sun.tools.javac.Main;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class staffLogin {

    public JPanel contentPane;
    private JTextField uniqueIDInput;

    public staffLogin(JFrame mainFrame) {
        // DB user object
        Staff globalStaffObject = new Staff();
        Maintenance globalMaintenanceObject = new Maintenance();

        // Declare new JPanel
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(57, 130, 146));
        contentPane.setLayout(null);

        // Get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (screenSize.width) / 2;
        int screenHeight = (screenSize.height) / 2;

        // Staff login button
        JButton returnButton = new JButton("< Return");
        returnButton.setForeground(new Color(255, 0, 0));
        returnButton.setBounds(5, 5, 100, 20);
        contentPane.add(returnButton);

        JPanel staffLoginPanel = new JPanel();
        staffLoginPanel.setBounds(718, 262, 303, 214);
        staffLoginPanel.setBackground(new Color(57, 130, 146));
        staffLoginPanel.setForeground(new Color(57, 130, 146));
        contentPane.add(staffLoginPanel);
        staffLoginPanel.setLayout(new GridLayout(0, 1, 0, 0));

        // Staff login heading
        JLabel lblStaffHeading = new JLabel("Staff Login");
        lblStaffHeading.setHorizontalAlignment(SwingConstants.CENTER);
        lblStaffHeading.setForeground(new Color(254, 255, 255));
        staffLoginPanel.add(lblStaffHeading);
        lblStaffHeading.setFont(new Font("Dialog", Font.BOLD, 22));

        // Unique ID heading
        JLabel lblUniqueID = new JLabel("Enter your unique ID:");
        lblUniqueID.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        lblUniqueID.setForeground(new Color(254, 255, 255));
        staffLoginPanel.add(lblUniqueID);

        // Input field
        uniqueIDInput = new JPasswordField();
        uniqueIDInput.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
        staffLoginPanel.add(uniqueIDInput);
        uniqueIDInput.setColumns(10);

        // Staff login button
        JButton staffLoginButton = new JButton("Login");
        staffLoginPanel.add(staffLoginButton);
        staffLoginButton.setForeground(new Color(0, 52, 255));

        // Staff login handler
        staffLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent clickEvent) {
                String uniqueId = uniqueIDInput.getText();

                if (globalStaffObject.validateLogin(uniqueId)) {
                    String role = globalStaffObject.getStaff(uniqueId).role;
                    String isMaintenance = globalMaintenanceObject.getStatus();

                    if (role.equals("Admin")) { // If user is admin, validate the login attempt
                        contentPane.setVisible(false);
                        sideMenu sideMenuFrame = new sideMenu(mainFrame, uniqueId);
                        mainFrame.setContentPane(sideMenuFrame.contentPane);
                    } else if (isMaintenance.equals("inactive")) { // If user is not admin and maintenance is inactive, validate
                        contentPane.setVisible(false);
                        sideMenu sideMenuFrame = new sideMenu(mainFrame, uniqueId);
                        mainFrame.setContentPane(sideMenuFrame.contentPane);
                    } else {
                        lblUniqueID.setText("Login Unavailable");
                        lblUniqueID.setForeground(Color.RED);
                        JOptionPane.showMessageDialog(null, "Invalid login. Maintenance mode is active.",
                                "Login attempt", JOptionPane.INFORMATION_MESSAGE);
                    }

                } else {
                    lblUniqueID.setText("Incorrect ID");
                    lblUniqueID.setForeground(Color.RED);
                    JOptionPane.showMessageDialog(null, "Invalid login", "Login attempt",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

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
