package Gui.Staff.Admin;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import Database.BooksDB.BooksDB;
import Database.Customers.Customers;
import Database.Staff.Staff;
import Gui.Staff.Books.addBooks;
import Gui.Staff.Books.editBook;
import Gui.Staff.Books.removeBook;

public class Admin {
    public JPanel adminPane;
    private JTable table_1;
    private JTable table;

    public Admin(JFrame mainFrame, String uniqueID) {
        Staff globalStaffObject = new Staff();
        BooksDB globalBooksObject = new BooksDB();
        adminPane = new JPanel();
        adminPane.setBackground(new Color(76, 128, 144));
        adminPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        adminPane.setLayout(null);

        // Get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (screenSize.width) / 2;
        int screenHeight = (screenSize.height) / 2;
        adminPane.setBounds(270, 0, screenSize.width - 250, screenSize.height);
        
        // Table
        String[] columnNames = {"Role", "Contact", "UniqueID", "age", "Email", "Username"};

        // Data
        Object[][] data = getTable(globalStaffObject);
        int heightOfTable = (data.length * 21) > 240 ? 240 : data.length * 25;
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(200, 93, 710, heightOfTable);
        adminPane.add(scrollPane);
        
        table = new JTable(data, columnNames);
        table.setShowGrid(false);
        table.setEnabled(true);
        table.setBackground(new Color(88, 127, 143));
        scrollPane.setViewportView(table);
        
        JPanel optionsPanel = new JPanel();
        optionsPanel.setBackground(new Color(76, 128, 144));
        optionsPanel.setBounds(980, 90, 229, 200);
        adminPane.add(optionsPanel);
        optionsPanel.setLayout(new GridLayout(0, 1, 0, 0));
        
        JButton addStaffBtn = new JButton("Add staff");
        addStaffBtn.setBackground(new Color(32, 99, 143));
        addStaffBtn.setForeground(new Color(32, 99, 143));
        optionsPanel.add(addStaffBtn);
        addStaffBtn.setFont(new Font("Dialog", Font.BOLD, 14));
        
        JButton removeStaffBtn = new JButton("Remove Staff");
        removeStaffBtn.setBackground(new Color(32, 99, 143));
        removeStaffBtn.setFont(new Font("Dialog", Font.BOLD, 14));
        removeStaffBtn.setForeground(new Color(32, 99, 143));
        optionsPanel.add(removeStaffBtn);
        
        JButton editStaffBtn = new JButton("Edit staff");
        editStaffBtn.setForeground(new Color(32, 99, 143));
        editStaffBtn.setFont(new Font("Dialog", Font.BOLD, 14));
        editStaffBtn.setBackground(new Color(32, 99, 143));
        optionsPanel.add(editStaffBtn);
        
        JButton maintenanceModeBtn = new JButton("Maintenance Mode");
        maintenanceModeBtn.setForeground(new Color(32, 99, 143));
        maintenanceModeBtn.setFont(new Font("Dialog", Font.BOLD, 14));
        maintenanceModeBtn.setBackground(new Color(32, 99, 143));
        optionsPanel.add(maintenanceModeBtn);
        
        JButton financesBtn = new JButton("View Finances");
        financesBtn.setForeground(new Color(32, 99, 143));
        financesBtn.setFont(new Font("Dialog", Font.BOLD, 14));
        financesBtn.setBackground(new Color(32, 99, 143));
        optionsPanel.add(financesBtn);
        
        JButton logsBtn = new JButton("View logs");
        logsBtn.setForeground(new Color(32, 99, 143));
        logsBtn.setFont(new Font("Dialog", Font.BOLD, 14));
        logsBtn.setBackground(new Color(32, 99, 143));
        optionsPanel.add(logsBtn);
        
        JButton announcementBtn = new JButton("Announcement");
        announcementBtn.setForeground(new Color(32, 99, 143));
        announcementBtn.setFont(new Font("Dialog", Font.BOLD, 14));
        announcementBtn.setBackground(new Color(32, 99, 143));
        optionsPanel.add(announcementBtn);
        
        JButton refreshButton = new JButton("Refresh");
        refreshButton.setForeground(new Color(32, 99, 143));
        refreshButton.setFont(new Font("Dialog", Font.BOLD, 14));
        refreshButton.setBackground(new Color(32, 99, 143));
        optionsPanel.add(refreshButton);
        
        JLabel headerLabel = new JLabel("Staff - Admin");
        headerLabel.setForeground(new Color(254, 255, 255));
        headerLabel.setFont(new Font("Dialog", Font.BOLD, 24));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setVerticalAlignment(SwingConstants.CENTER);
        headerLabel.setBounds(-120, 0, screenSize.width - 250, 39);
        adminPane.add(headerLabel);
        
        // When you click edit user info button
        addStaffBtn.addActionListener(clickEvent -> {
        	new addStaff();
        });
        
        removeStaffBtn.addActionListener(clickEvent -> {
        	new removeStaff();
        });
        
        editStaffBtn.addActionListener(clickEvent -> {
        	new editStaff();
        });

        maintenanceModeBtn.addActionListener(clickEvent -> {
            new maintenanceMode();
        });

        announcementBtn.addActionListener(clickEvent -> {
            new announcements(uniqueID);
        });

        logsBtn.addActionListener(clickEvent -> {
            new viewLogs();
        });

        refreshButton.addActionListener(clickEvent -> {
        	refreshTable(table, adminPane, globalStaffObject, scrollPane);
            System.out.print("Refreshed");
        });
    }
    
    public ImageIcon changeIcon(int width, int height, String path) {
        ImageIcon icon = new ImageIcon(Customers.class.getResource(path));
        Image image = icon.getImage();
        int scaledWidth = width; // The new width you want
        int scaledHeight = height; // The new height you want
        Image scaledImage = image.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        return scaledIcon;
    }
    
    public Object[][] getTable(Staff globalStaffObject) {
        Staff[] allStaff = globalStaffObject.getAllStaff();

        Object[][] data = new Object[allStaff.length][6];

        for(int i = 0; i < allStaff.length; i++) {
            data[i][0] = allStaff[i].role;
            data[i][1] = allStaff[i].contact;
            data[i][2] = allStaff[i].uniqueID;
            data[i][3] = allStaff[i].age;
            data[i][4] = allStaff[i].email;
            data[i][5] = allStaff[i].username;
        }
        
        return data;
    }
    
    public void refreshTable(JTable table, JPanel panel, Staff globalStaffObject, JScrollPane scrollPanel) {
        // get the updated data for the table
        Object[][] data = getTable(globalStaffObject);
        
        // Adjust length of table
        int heightOfTable = (data.length * 21) > 240 ? 240 : data.length * 25;
        scrollPanel.setBounds(200, 93, 710, heightOfTable);
        
        // create a new table model with the updated data
        DefaultTableModel newTableModel = new DefaultTableModel(data, new Object[] {"Role", "Contact", "UniqueID", "Age", "Email", "Username"});
        table.setModel(newTableModel);

        // revalidate and repaint the table and the panel to refresh the view
        table.revalidate();
        table.repaint();
        panel.revalidate();
        panel.repaint();
    }
}
