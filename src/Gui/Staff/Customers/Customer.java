package Gui.Staff.Customers;

import Database.Staff.Staff;
import Gui.loginSelector.loginSelector;
import Database.Customers.Customers;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Customer {
    public JPanel customersPane;
    private JTable table_2;
    private JTextField subjectInput;
    private JTextField fromInput;
    private JTextField messageInput;
    private JTextField replyInput;

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
        
        // Table
        String[] columnNames = {"Name", "Email", "Contact Number"};
        // Data
        Object[][] data = getTable(globalCustomerObject);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(200, 93, 710, data.length * 17);
        customersPane.add(scrollPane_1);
        
        table_2 = new JTable(data, columnNames);
        table_2.setShowGrid(false);
        table_2.setEnabled(false);
        table_2.setBackground(new Color(88, 127, 143));
        scrollPane_1.setViewportView(table_2);
        
        JPanel optionsPanel = new JPanel();
        optionsPanel.setBackground(new Color(76, 128, 144));
        optionsPanel.setBounds(980, 90, 229, 103);
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
        
        JButton refreshButton = new JButton("Refresh");
        refreshButton.setForeground(new Color(32, 99, 143));
        refreshButton.setFont(new Font("Dialog", Font.BOLD, 14));
        refreshButton.setBackground(new Color(32, 99, 143));
        optionsPanel.add(refreshButton);
        
        JLabel headerLabel = new JLabel("Staff - Customers");
        headerLabel.setForeground(new Color(254, 255, 255));
        headerLabel.setFont(new Font("Dialog", Font.BOLD, 24));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setBounds(-120, 0, screenSize.width - 250, 39);
        customersPane.add(headerLabel);

        
        // When you click edit user info button
        editUserInformationButton.addActionListener(clickEvent -> {
        	editUserInformation editFrame = new editUserInformation();
        });

        customerSupportButton.addActionListener(clickEvent -> {
            customerSupport customerSupportFrame = new customerSupport(uniqueID);
        });
        
        refreshButton.addActionListener(clickEvent -> {
        	refreshTable(table_2, customersPane, globalCustomerObject, scrollPane_1);
            System.out.print("Refreshed");
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
    
    public Object[][] getTable(Customers globalCustomerObject) {
    	Customers[] allCustomers = globalCustomerObject.getAllUsers();

        Object[][] data = new Object[allCustomers.length][3];

        for(int i = 0; i < allCustomers.length; i++) {
            data[i][0] = allCustomers[i].username;
            data[i][1] = allCustomers[i].email;
            data[i][2] = allCustomers[i].contact;
        }
        
        return data;
    }
    
    public void refreshTable(JTable table, JPanel panel, Customers globalCustomerObject, JScrollPane scrollPanel) {
        // get the updated data for the table
        Object[][] data = getTable(globalCustomerObject);
        
        // create a new table model with the updated data
        DefaultTableModel newTableModel = new DefaultTableModel(data, new Object[] {"Name", "Email", "Contact Number"});
        table.setModel(newTableModel);
        scrollPanel.setBounds(200, 93, 710, data.length * 17);

        // revalidate and repaint the table and the panel to refresh the view
        table.revalidate();
        table.repaint();
        panel.revalidate();
        panel.repaint();
    }

}
