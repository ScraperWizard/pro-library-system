package Gui.Customers.MyBooks;

import Database.BooksDB.BooksDB;
import Database.Staff.Staff;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MyBooks {
    public JPanel myBooksPane;

    public MyBooks(JFrame mainFrame) {
        Staff globalStaffObject = new Staff();
        BooksDB globalBooksObject = new BooksDB();
        myBooksPane = new JPanel();
        myBooksPane.setBackground(new Color(76, 128, 144));
        myBooksPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        myBooksPane.setLayout(null);

        // Get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (screenSize.width) / 2;
        int screenHeight = (screenSize.height) / 2;
        myBooksPane.setBounds(270, 0, screenSize.width - 250, screenSize.height);

        JLabel headerLabel = new JLabel("Customers - My Books");
        headerLabel.setForeground(new Color(254, 255, 255));
        headerLabel.setFont(new Font("Dialog", Font.BOLD, 24));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setVerticalAlignment(SwingConstants.CENTER);
        headerLabel.setBounds(-120, 0, screenSize.width - 250, 39);
        myBooksPane.add(headerLabel);
    }
}

