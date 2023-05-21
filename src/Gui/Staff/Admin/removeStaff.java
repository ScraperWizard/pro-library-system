package Gui.Staff.Admin;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import Database.BooksDB.BooksDB;
import Database.Staff.Staff;

public class removeStaff {
    public JFrame jFrame;
    public removeStaff() {
        jFrame = new JFrame();
        Staff globalStaffObject = new Staff();

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setBounds(500, 500, 500, 400);
        jFrame.setTitle("Admin - Remove staff");
        
        JPanel panel = new JPanel();
        jFrame.setContentPane(panel);
        panel.setBackground(new Color(57, 130, 146));
        panel.setLayout(null);
        
        JLabel removeStaffLabel = new JLabel("Remove staff");
        removeStaffLabel.setBounds(63, 0, 367, 35);
        panel.add(removeStaffLabel);
        removeStaffLabel.setForeground(new Color(254, 255, 255));
        removeStaffLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        removeStaffLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JList<String> staffList = new JList();
        staffList.setForeground(new Color(254, 255, 255));
        staffList.setBorder(new LineBorder(new Color(44, 100, 144)));
        staffList.setBounds(103, 37, 300, 273);
        panel.add(staffList);
        staffList.setVisibleRowCount(3);
        staffList.setBackground(new Color(57, 130, 146));
        
        // Package all data
        setModel(globalStaffObject, staffList);
        
        JButton removeButton = new JButton("Remove!");
        removeButton.setBounds(103, 316, 300, 50);
        panel.add(removeButton);
        removeButton.setForeground(new Color(0, 0, 0));
        removeButton.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        
        // Save data when clicking save
        removeButton.addActionListener(clickEvent -> {
            List<String> selectedStaff = staffList.getSelectedValuesList();

            if(selectedStaff.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Please select books to remove!");
            } else {
                try {
                    for(String staff : selectedStaff) {
                        globalStaffObject.removeStaff(staff);
                    }
                    JOptionPane.showMessageDialog(panel, "Staff(s) have been removed!");
                } catch (Exception error) {
                    JOptionPane.showMessageDialog(panel, "An error has occured while removing the book!");
                    error.printStackTrace();
                }
            }
        });

       
    }
    
    public void setModel(Staff globalStaffObject, JList<String> booksList) {
        // Package all data
		String[] values;
		Staff[] allStaff = globalStaffObject.getAllStaff();
		values = new String[allStaff.length];
		
		for(int i = 0; i < allStaff.length; i++)
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
