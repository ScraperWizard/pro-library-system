package Gui.Customers.Home;

import Database.BooksDB.BooksDB;
import Database.Staff.Staff;
import Gui.Staff.Home.announcements;
import Gui.Staff.sideMenu.sideMenu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Home {
    public JPanel homePane;

    public Home(JFrame mainFrame) {
        Staff globalStaffObject = new Staff();
        BooksDB globalBooksObject = new BooksDB();
        homePane = new JPanel();
        homePane.setBackground(new Color(76, 128, 144));
        homePane.setBorder(new EmptyBorder(5, 5, 5, 5));
        homePane.setLayout(null);

        // Get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (screenSize.width) / 2;
        int screenHeight = (screenSize.height) / 2;
        homePane.setBounds(270, 0, screenSize.width - 250, screenSize.height);

        JLabel headerLabel = new JLabel("Customers - Home");
        headerLabel.setForeground(new Color(254, 255, 255));
        headerLabel.setFont(new Font("Dialog", Font.BOLD, 24));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setVerticalAlignment(SwingConstants.CENTER);
        headerLabel.setBounds(-120, 0, screenSize.width - 250, 39);
        homePane.add(headerLabel);
    }
}

