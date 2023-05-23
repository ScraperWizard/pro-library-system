package Gui.Customers.HelpDesk;

import Database.BooksDB.BooksDB;
import Database.Staff.Staff;
import Gui.Staff.Home.announcements;
import Gui.Staff.sideMenu.sideMenu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HelpDesk {
    public JPanel helpDeskPane;

    public HelpDesk(JFrame mainFrame) {
        Staff globalStaffObject = new Staff();
        BooksDB globalBooksObject = new BooksDB();
        helpDeskPane = new JPanel();
        helpDeskPane.setBackground(new Color(76, 128, 144));
        helpDeskPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        helpDeskPane.setLayout(null);

        // Get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (screenSize.width) / 2;
        int screenHeight = (screenSize.height) / 2;
        helpDeskPane.setBounds(270, 0, screenSize.width - 250, screenSize.height);

        JLabel headerLabel = new JLabel("Customers - Help Desk");
        headerLabel.setForeground(new Color(254, 255, 255));
        headerLabel.setFont(new Font("Dialog", Font.BOLD, 24));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setVerticalAlignment(SwingConstants.CENTER);
        headerLabel.setBounds(-120, 0, screenSize.width - 250, 39);
        helpDeskPane.add(headerLabel);

        Staff globalStaffObject1 = new Staff();

        Staff [] allStaff = globalStaffObject1.getAllStaff();

        // Table
        String[] columnNames = {"Name", "Email", "Contact Number"};

        Object[][] data = new Object[allStaff.length][3];

        for(int i = 0; i < allStaff.length; i++) {
            data[i][0] = allStaff[i].username;
            data[i][1] = allStaff[i].email;
            data[i][2] = allStaff[i].contact;
        }

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(320, 93, 710, data[0].length * 30);
        helpDeskPane.add(scrollPane_1);

        JTable table_2 = new JTable(data, columnNames);
        table_2.setShowGrid(false);
        table_2.setEnabled(false);
        table_2.setBackground(new Color(88, 127, 143));
        scrollPane_1.setViewportView(table_2);
    }
}
