package Gui.Staff.Customers;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import Database.Customers.*;

public class editUserInformation {
    public JFrame jFrame;
    private JTextField contactInput;
    private JTextField emailInput;
    private JTextField nameInput;
    private JTextField noteInput;
    public editUserInformation() {
        jFrame = new JFrame();
        Customers globalCustomersObject = new Customers();

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setBounds(500, 500, 500, 400);
        jFrame.setTitle("Staff - Edit");
        
        JPanel panel = new JPanel();
        jFrame.setContentPane(panel);
        panel.setBackground(new Color(57, 130, 146));
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Edit user information");
        lblNewLabel.setBounds(63, 0, 367, 35);
        panel.add(lblNewLabel);
        lblNewLabel.setForeground(new Color(254, 255, 255));
        lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(157, 24, 300, 319);
        mainPanel.setBackground(new Color(57, 130, 146));
        panel.add(mainPanel);
        mainPanel.setLayout(new GridLayout(0, 1, 0, 0));
        
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(new Color(254, 255, 255));
        emailLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        mainPanel.add(emailLabel);
        
        emailInput = new JTextField();
        mainPanel.add(emailInput);
        emailInput.setColumns(10);
        
        JLabel contactLabel = new JLabel("Contact number:");
        contactLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        contactLabel.setForeground(new Color(254, 255, 255));
        mainPanel.add(contactLabel);
        
        contactInput = new JTextField();
        mainPanel.add(contactInput);
        contactInput.setColumns(10);
        
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setForeground(new Color(254, 255, 255));
        nameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        mainPanel.add(nameLabel);
        
        nameInput = new JTextField();
        mainPanel.add(nameInput);
        nameInput.setColumns(10);
        
        JLabel noteLabel = new JLabel("Note:");
        noteLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        noteLabel.setForeground(new Color(254, 255, 255));
        mainPanel.add(noteLabel);
        
        noteInput = new JTextField();
        mainPanel.add(noteInput);
        noteInput.setColumns(10);
        
        JButton saveButton = new JButton("Save!");
        saveButton.setForeground(new Color(0, 0, 0));
        saveButton.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        mainPanel.add(saveButton);
        
        JList<String> customersList = new JList();
        customersList.setForeground(new Color(254, 255, 255));
        customersList.setBorder(new LineBorder(new Color(44, 100, 144)));
        customersList.setBounds(31, 35, 125, 302);
        panel.add(customersList);
        customersList.setVisibleRowCount(3);
        customersList.setBackground(new Color(57, 130, 146));
        customersList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Package all data
        setModel(globalCustomersObject, customersList);
        
        // Change inputs whenever selecting a username
        customersList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt) {
            	String selectedValue = (String) customersList.getSelectedValue();
                if (!evt.getValueIsAdjusting() && selectedValue != null) {
                    Customers selectedCustomer = globalCustomersObject.getUser(selectedValue);
                    
                    nameInput.setText(selectedCustomer.username);
                    contactInput.setText(selectedCustomer.contact);
                    emailInput.setText(selectedCustomer.email);
                }
            }
        });
        
        // Save data when clicking save
        saveButton.addActionListener(clickEvent -> {
        	String selectedValue = (String) customersList.getSelectedValue();
        	String usernameValue = nameInput.getText();
        	String emailValue = emailInput.getText();
        	String contactValue = contactInput.getText();
        	
        	if(usernameValue.equals("") || emailValue.equals("") || contactValue.equals("")) {
        		JOptionPane.showMessageDialog(panel, "Please fill in fields!");
        	} else {
        		globalCustomersObject.editUserInformation(selectedValue, "email", emailValue);
        		globalCustomersObject.editUserInformation(selectedValue, "contact", contactValue);
        		globalCustomersObject.editUserInformation(selectedValue, "username", usernameValue);
        		JOptionPane.showMessageDialog(panel, selectedValue + " has been updated.");
        		setModel(globalCustomersObject, customersList);
        	}
        });
       
    }
    
    public void setModel(Customers globalCustomersObject, JList<String> customersList) {
        // Package all data
		String[] values;
		Customers[] allCustomers = globalCustomersObject.getAllUsers();
		values = new String[allCustomers.length];
		
		for(int i = 0; i < allCustomers.length; i++)
			values[i] = allCustomers[i].username;
        
        customersList.setModel(new AbstractListModel<String>() {
            public int getSize() {
                return values.length;
            }
            public String getElementAt(int index) {
                return values[index];
            }
        });
    }
}
