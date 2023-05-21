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

import Database.Maintenance.Maintenance;
import Database.Staff.Staff;
import javax.swing.JToggleButton;
import javax.swing.JTextPane;

public class maintenanceMode {
    public JFrame jFrame;
    public maintenanceMode() {
        jFrame = new JFrame();
        // Get the current maintenance status
        Maintenance maintenance = new Maintenance();
        String status = maintenance.getStatus();

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setBounds(500, 500, 500, 170);
        jFrame.setTitle("Admin - Maintenance");
        
        JPanel panel = new JPanel();
        jFrame.setContentPane(panel);
        panel.setBackground(new Color(57, 130, 146));
        panel.setLayout(null);
        
        JLabel maintenanceMode = new JLabel("Change maintenance mode");
        maintenanceMode.setBounds(0, 0, 500, 35);
        panel.add(maintenanceMode);
        maintenanceMode.setForeground(new Color(254, 255, 255));
        maintenanceMode.setFont(new Font("Dialog", Font.BOLD, 18));
        maintenanceMode.setHorizontalAlignment(SwingConstants.CENTER);

        JToggleButton onOffToggle = new JToggleButton("ON/OFF");
        onOffToggle.setForeground(new Color(0, 249, 0));
        onOffToggle.setBackground(new Color(0, 249, 0));
        onOffToggle.setBounds(170, 47, 161, 29);
        panel.add(onOffToggle);

        // Set the initial foreground color and text of the toggle button based on the status
        if (status.equals("active")) {
            onOffToggle.setForeground(new Color(0, 255, 0)); // Green color for active status
            onOffToggle.setSelected(true); // Set the button as selected
            onOffToggle.setText("ON");
        } else {
            onOffToggle.setForeground(new Color(255, 0, 0)); // Red color for inactive status
            onOffToggle.setSelected(false); // Set the button as not selected
            onOffToggle.setText("OFF");
        }

        // Add an ActionListener to handle toggle button events
        onOffToggle.addActionListener(e -> {
            if (onOffToggle.isSelected()) {
                // Button is selected, change status to active and set green foreground color
                maintenance.setStatus("active");
                onOffToggle.setForeground(new Color(0, 255, 0));
                onOffToggle.setText("ON");
            } else {
                // Button is not selected, change status to inactive and set red foreground color
                maintenance.setStatus("inactive");
                onOffToggle.setForeground(new Color(255, 0, 0));
                onOffToggle.setText("OFF");
            }
        });

        JTextPane descriptionText = new JTextPane();
        descriptionText.setEditable(false);
        descriptionText.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
        descriptionText.setBackground(new Color(57, 130, 146));
        descriptionText.setForeground(new Color(254, 255, 255));
        descriptionText.setText("By enabling maintenance mode, non-admin staff and all customers cannot access Pro Library system.");
        descriptionText.setBounds(16, 88, 500, 35);
        panel.add(descriptionText);

    }
}
