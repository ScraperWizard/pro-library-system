package Gui.Staff.Customers;

import Database.Staff.Staff;
import Gui.loginSelector.loginSelector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Customers {
    public JPanel customersPane;
    private JTable table_1;
    private JTable table_2;

    public Customers(JFrame mainFrame, String uniqueID) {
        Staff globalStaffObject = new Staff();
        Staff currentUser = globalStaffObject.getStaff(uniqueID);
        customersPane = new JPanel();
        customersPane.setBackground(new Color(76, 128, 144));
        customersPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        customersPane.setLayout(null);

        // Get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (screenSize.width) / 2;
        int screenHeight = (screenSize.height) / 2;
        customersPane.setBounds(270, 0, screenSize.width, screenSize.height);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(76, 128, 144));
        panel.setBounds(6, 6, screenSize.width, 30);
        customersPane.add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("CUSTOMERS");
        lblNewLabel.setBounds(579, 6, 125, 25);
        lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBackground(new Color(76, 128, 144));
        panel.add(lblNewLabel);

        Staff[] allStaff = globalStaffObject.getAllStaff();

// Table
        String[] columnNames = {"ID", "Phone", "Email", "Name", "Age", "Role"};

        Object[][] data = new Object[allStaff.length][6];

        for(int i = 0; i < allStaff.length; i++) {
            data[i][0] = allStaff[i].uniqueID;
            data[i][1] = allStaff[i].contact;
            data[i][2] = allStaff[i].email;
            data[i][3] = allStaff[i].username;
            data[i][4] = allStaff[i].age;
            data[i][5] = allStaff[i].role;
        }


//        JTable table = new JTable(data, columnNames);
//        JScrollPane scrollPane = new JScrollPane(table);
//        table.setBounds(103, 99, 734, 348);
//        customersPane.add(scrollPane);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(296, 93, 710, data[0].length * 30);
        customersPane.add(scrollPane_1);
        
        table_2 = new JTable(data, columnNames);
        scrollPane_1.setViewportView(table_2);
        
//        table_1 = new JTable();
//        table_1.setBounds(103, 99, 734, 348);
//        customersPane.add(table_1);
        
        // Declare the mouse event handler
        MouseAdapter panelMouseEventHandler = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	customersPane.setVisible(false);
                loginSelector loginSelectorFrame = new loginSelector(mainFrame);
                mainFrame.setContentPane(loginSelectorFrame.contentPane);
            }

            @Override
            public void mouseEntered(MouseEvent event) {
                // handle mouse enter event
                event.getComponent().setBackground(new Color(123, 147, 163));
                event.getComponent().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent event) {
                // handle mouse exit event
                event.getComponent().setBackground(new Color(83, 107, 123));
                event.getComponent().setCursor(Cursor.getDefaultCursor());
            }
        };
        
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
}
