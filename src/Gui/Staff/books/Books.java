package Gui.Staff.books;

import Database.Customers.Customers;
import Database.Staff.Staff;
import Gui.Login.Login;
import Gui.loginSelector.loginSelector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Books {
    public JPanel contentPane;

    public Books(JFrame mainFrame, String uniqueID) {
        Staff globalStaffObject = new Staff();
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);

        // Get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (screenSize.width) / 2;
        int screenHeight = (screenSize.height) / 2;
        System.out.println(globalStaffObject.getStaff(uniqueID).username);

        // Staff login heading
        JLabel lblNameHeading = new JLabel("Welcome, " + globalStaffObject.getStaff(uniqueID).username);
        lblNameHeading.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNameHeading.setBounds(1420, 5, 400, 40);
        contentPane.add(lblNameHeading);

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
    }
}
