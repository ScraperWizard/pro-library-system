package Gui.Staff.Customers;

import Database.Staff.Staff;
import Gui.loginSelector.loginSelector;
import Database.Customers.Customers;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Customer {
    public JPanel customersPane;
    private JTable table_1;
    private JTable table_2;

    public Customer(JFrame mainFrame, String uniqueID) {
        Staff globalStaffObject = new Staff();
        Customers globalCustomerObject = new Customers();
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
        lblNewLabel.setForeground(new Color(254, 255, 255));
        lblNewLabel.setBounds(579, 6, 170, 25);
        lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 22));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBackground(new Color(76, 128, 144));
        panel.add(lblNewLabel);

        Customers[] allCustomers = globalCustomerObject.getAllUsers();

        // Table
        String[] columnNames = {"Name", "Email", "Contact Number"};

        Object[][] data = new Object[allCustomers.length][3];

        for(int i = 0; i < allCustomers.length; i++) {
            data[i][0] = allCustomers[i].username;
            data[i][1] = allCustomers[i].email;
            data[i][2] = allCustomers[i].contact;
        }
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(320, 93, 710, data[0].length * 30);
        customersPane.add(scrollPane_1);
        
        table_2 = new JTable(data, columnNames);
        table_2.setShowGrid(false);
        table_2.setEnabled(false);
        table_2.setBackground(new Color(88, 127, 143));
        scrollPane_1.setViewportView(table_2);
        
        JPanel optionsPanel = new JPanel();
        optionsPanel.setBackground(new Color(76, 128, 144));
        optionsPanel.setBounds(1100, 90, 229, 103);
        customersPane.add(optionsPanel);
        optionsPanel.setLayout(new GridLayout(0, 1, 0, 0));
        
        JButton editUserInformationButton = new JButton("Edit user information");
        editUserInformationButton.setBackground(new Color(32, 99, 143));
        editUserInformationButton.setForeground(new Color(32, 99, 143));
        optionsPanel.add(editUserInformationButton);
        editUserInformationButton.setFont(new Font("Dialog", Font.BOLD, 14));
        
        JButton customerSupportButton = new JButton("Customer support");
        customerSupportButton.setBackground(new Color(32, 99, 143));
        customerSupportButton.setFont(new Font("Dialog", Font.BOLD, 14));
        customerSupportButton.setForeground(new Color(32, 99, 143));
        optionsPanel.add(customerSupportButton);
        
        // When you click edit user info button
        editUserInformationButton.addActionListener(clickEvent -> {
        	editUserInformation editFrame = new editUserInformation();
        });
        
        // Declare the mouse event handler
        MouseAdapter editUserInformationHandler = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	customersPane.setVisible(false);
                loginSelector loginSelectorFrame = new loginSelector(mainFrame);
                mainFrame.setContentPane(loginSelectorFrame.contentPane);
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
