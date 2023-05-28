package Gui.Staff.Admin;

import Database.Announcements.Announcements;
import Database.Staff.Staff;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class announcements {
    public JFrame jFrame;
    private JTextField subjectInput;
    private JTextField bodyInput;
    public announcements(String uniqueID) {
        jFrame = new JFrame();
        Announcements globalAnnouncementsObject = new Announcements();

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setBounds(500, 500, 500, 400);
        jFrame.setTitle("Admin - Announcements");

        JPanel panel = new JPanel();
        jFrame.setContentPane(panel);
        panel.setBackground(new Color(57, 130, 146));
        panel.setLayout(null);

        JLabel headerLabel = new JLabel("Make an announcement");
        headerLabel.setBounds(63, 0, 367, 35);
        panel.add(headerLabel);
        headerLabel.setForeground(new Color(254, 255, 255));
        headerLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(103, 24, 300, 319);
        mainPanel.setBackground(new Color(57, 130, 146));
        panel.add(mainPanel);
        mainPanel.setLayout(new GridLayout(0, 1, 0, 0));

        JLabel toLabel = new JLabel("To:");
        toLabel.setForeground(new Color(254, 255, 255));
        toLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        mainPanel.add(toLabel);

        JComboBox toComboInput = new JComboBox();
        toComboInput.setModel(new DefaultComboBoxModel(new String[] {"Customers", "Staff"}));
        mainPanel.add(toComboInput);

        JLabel subjectLabel = new JLabel("Subject:");
        subjectLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        subjectLabel.setForeground(new Color(254, 255, 255));
        mainPanel.add(subjectLabel);

        subjectInput = new JTextField();
        mainPanel.add(subjectInput);
        subjectInput.setColumns(10);

        JLabel bodyLabel = new JLabel("Body:");
        bodyLabel.setForeground(new Color(254, 255, 255));
        bodyLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        mainPanel.add(bodyLabel);

        bodyInput = new JTextField();
        mainPanel.add(bodyInput);
        bodyInput.setColumns(10);

        JButton announceButton = new JButton("Announce!");
        announceButton.setForeground(new Color(0, 0, 0));
        announceButton.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        mainPanel.add(announceButton);

        // Save data when clicking save
        announceButton.addActionListener(clickEvent -> {
            String toValue = (String) toComboInput.getSelectedItem();
            String subjectValue = subjectInput.getText();
            String bodyValue = bodyInput.getText();

            if(subjectValue.equals("") || bodyValue.equals("")) {
                JOptionPane.showMessageDialog(panel, "Please fill in fields!");
            } else {
                try {
                    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    globalAnnouncementsObject.addAnnouncement(toValue, subjectValue, bodyValue, timestamp, uniqueID);
                    JOptionPane.showMessageDialog(panel, subjectValue + " has been announced.");
                } catch (Exception error) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(panel, "An error has occured while adding the staff member!");
                    error.printStackTrace();
                }
            }
        });
    }
}
