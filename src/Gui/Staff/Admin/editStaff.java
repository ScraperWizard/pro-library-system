package Gui.Staff.Admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Database.BooksDB.BooksDB;
import Database.Staff.Staff;

public class editStaff {
    public JFrame jFrame;
    private JTextField contactInput;
    private JTextField ageInput;
    private JTextField emailInput;
    
    public editStaff() {
        jFrame = new JFrame();
        Staff globalStaffObject = new Staff();

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setBounds(500, 500, 500, 400);
        jFrame.setTitle("Admin - Edit staff");
        
        JPanel panel = new JPanel();
        jFrame.setContentPane(panel);
        panel.setBackground(new Color(57, 130, 146));
        panel.setLayout(null);
        
        JLabel headerLbl = new JLabel("Edit staff information");
        headerLbl.setBounds(63, 0, 367, 35);
        panel.add(headerLbl);
        headerLbl.setForeground(new Color(254, 255, 255));
        headerLbl.setFont(new Font("Dialog", Font.BOLD, 18));
        headerLbl.setHorizontalAlignment(SwingConstants.CENTER);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(157, 24, 300, 319);
        mainPanel.setBackground(new Color(57, 130, 146));
        panel.add(mainPanel);
        mainPanel.setLayout(new GridLayout(0, 1, 0, 0));
        
        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setForeground(new Color(254, 255, 255));
        ageLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        mainPanel.add(ageLabel);
        
        ageInput = new JTextField();
        mainPanel.add(ageInput);
        ageInput.setColumns(10);
        
        JLabel contactLabel = new JLabel("Contact:");
        contactLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        contactLabel.setForeground(new Color(254, 255, 255));
        mainPanel.add(contactLabel);
        
        contactInput = new JTextField();
        mainPanel.add(contactInput);
        contactInput.setColumns(10);
        
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(new Color(254, 255, 255));
        emailLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        mainPanel.add(emailLabel);
        
        emailInput = new JTextField();
        mainPanel.add(emailInput);
        emailInput.setColumns(10);
        
        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        roleLabel.setForeground(new Color(254, 255, 255));
        mainPanel.add(roleLabel);
        
        JComboBox roleComboBox = new JComboBox();
        roleComboBox.setMaximumRowCount(3);
        roleComboBox.setModel(new DefaultComboBoxModel(new String[] {"Librarian", "Financial", "Admin"}));
        mainPanel.add(roleComboBox);
        
        JButton saveButton = new JButton("Save!");
        saveButton.setForeground(new Color(0, 0, 0));
        saveButton.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        mainPanel.add(saveButton);
        
        JList<String> staffList = new JList();
        staffList.setForeground(new Color(254, 255, 255));
        staffList.setBorder(new LineBorder(new Color(44, 100, 144)));
        staffList.setBounds(31, 35, 125, 302);
        panel.add(staffList);
        staffList.setVisibleRowCount(3);
        staffList.setBackground(new Color(57, 130, 146));
        staffList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Package all data
        setModel(globalStaffObject, staffList);
        
        // Change inputs whenever selecting a username
        staffList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt) {
            	String selectedValue = (String) staffList.getSelectedValue();
                if (!evt.getValueIsAdjusting() && selectedValue != null) {
                    Staff selectedStaff = globalStaffObject.getStaffByUsername(selectedValue);
                    
                    ageInput.setText(selectedStaff.age);
                    emailInput.setText(selectedStaff.email);
                    contactInput.setText(selectedStaff.contact);
                    roleComboBox.setSelectedItem(selectedStaff.role);
                }
            }
        });
        
        // Save data when clicking save
        saveButton.addActionListener(clickEvent -> {
        	String selectedValue = (String) staffList.getSelectedValue();
        	String ageValue = ageInput.getText();
        	String emailValue = emailInput.getText();
        	String contactValue = contactInput.getText();
        	String roleValue = (String) roleComboBox.getSelectedItem();
        	
        	if(ageValue.equals("") || emailValue.equals("") || contactValue.equals("")) {
        		JOptionPane.showMessageDialog(panel, "Please fill in fields!");
        	} else {
        		
        		globalStaffObject.editStaffInformation(selectedValue, "role", roleValue);
        		globalStaffObject.editStaffInformation(selectedValue, "age", ageValue);
        		globalStaffObject.editStaffInformation(selectedValue, "email", emailValue);
        		globalStaffObject.editStaffInformation(selectedValue, "contact", contactValue);
        		
        		JOptionPane.showMessageDialog(panel, selectedValue + " has been updated.");
        		setModel(globalStaffObject, staffList);
        	}
        });
       
    }
    
    public void setModel(Staff globalStaffObject, JList<String> booksList) {
        // Package all data
		String[] values;
		Staff allStaff[] = globalStaffObject.getAllStaff();
		values = new String[allStaff.length];
		
		for(int i = 0; i <allStaff.length; i++)
			values[i] = allStaff[i].username;
        
		 booksList.setModel(new AbstractListModel<String>() {
            public int getSize() {
                return values.length;
            }
            public String getElementAt(int index) {
                return values[index];
            }
        });
    }
}
