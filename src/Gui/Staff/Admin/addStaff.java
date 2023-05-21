package Gui.Staff.Admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Database.BooksDB.BooksDB;
import Database.Staff.Staff;

public class addStaff {
	public JFrame jFrame;
	private JTextField uniqueIDInput;
	private JTextField emailInput;
	private JTextField usernameInput;
	public addStaff() {
	    jFrame = new JFrame();
		Staff globalStaffObject = new Staff();

	    jFrame.setVisible(true);
	    jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    jFrame.setBounds(500, 500, 500, 400);
	    jFrame.setTitle("Staff - Add staff");
	    
	    JPanel panel = new JPanel();
	    jFrame.setContentPane(panel);
	    panel.setBackground(new Color(57, 130, 146));
	    panel.setLayout(null);
	    
	    JLabel addStaffLabel = new JLabel("Add staff");
	    addStaffLabel.setBounds(63, 0, 367, 35);
	    panel.add(addStaffLabel);
	    addStaffLabel.setForeground(new Color(254, 255, 255));
	    addStaffLabel.setFont(new Font("Dialog", Font.BOLD, 18));
	    addStaffLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    
	    JPanel mainPanel = new JPanel();
	    mainPanel.setBounds(103, 24, 300, 319);
	    mainPanel.setBackground(new Color(57, 130, 146));
	    panel.add(mainPanel);
	    mainPanel.setLayout(new GridLayout(0, 1, 0, 0));
	    
	    JLabel roleLabel = new JLabel("Role:");
	    roleLabel.setForeground(new Color(254, 255, 255));
	    roleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
	    mainPanel.add(roleLabel);
	    
	    JComboBox roleComboInput = new JComboBox();
	    roleComboInput.setModel(new DefaultComboBoxModel(new String[] {"Librarian", "Admin", "Financial"}));
	    mainPanel.add(roleComboInput);
	    
	    JLabel uniqueIDLabel = new JLabel("UniqueID");
	    uniqueIDLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
	    uniqueIDLabel.setForeground(new Color(254, 255, 255));
	    mainPanel.add(uniqueIDLabel);
	    
	    uniqueIDInput = new JTextField();
	    mainPanel.add(uniqueIDInput);
	    uniqueIDInput.setColumns(10);
	    
	    JLabel emailLabel = new JLabel("Email:");
	    emailLabel.setForeground(new Color(254, 255, 255));
	    emailLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
	    mainPanel.add(emailLabel);
	    
	    emailInput = new JTextField();
	    mainPanel.add(emailInput);
	    emailInput.setColumns(10);
	    
	    JLabel usernameLabel = new JLabel("Username:");
	    usernameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
	    usernameLabel.setForeground(new Color(254, 255, 255));
	    mainPanel.add(usernameLabel);
	    
	    usernameInput = new JTextField();
	    mainPanel.add(usernameInput);
	    usernameInput.setColumns(10);
	    
	    JButton addButton = new JButton("Add!");
	    addButton.setForeground(new Color(0, 0, 0));
	    addButton.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
	    mainPanel.add(addButton);
	    
	    // Save data when clicking save
	    addButton.addActionListener(clickEvent -> {
	        String uniqueIDValue = uniqueIDInput.getText();
	        String emailValue = emailInput.getText();
	        String usernameValue = usernameInput.getText();
	        String roleValue = (String) roleComboInput.getSelectedItem();
	        
	        if(uniqueIDValue.equals("") || emailValue.equals("") || usernameValue.equals("")) {
	            JOptionPane.showMessageDialog(panel, "Please fill in fields!");
	        } else {
	            try {
					globalStaffObject.addStaff(usernameValue, "null", "null", emailValue, uniqueIDValue, roleValue);
	                JOptionPane.showMessageDialog(panel, usernameValue + " has been added.");
	            } catch (Exception error) {
	                // TODO Auto-generated catch block
	                JOptionPane.showMessageDialog(panel, "An error has occured while adding the staff member!");
	                error.printStackTrace();
	            }
	        }
	    });
	   
	}
}
