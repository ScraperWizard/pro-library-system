package Gui.Customers.Home;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Database.Announcements.Announcements;

public class CustomerAnnouncement extends JFrame {
    /**
     * Create the frame.
     */
    public CustomerAnnouncement() {
        // Create a new JFrame
        JFrame jFrame = new JFrame();
        Announcements globalAnnouncementsObject = new Announcements();

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setBounds(500, 500, 500, 400);
        jFrame.setTitle("Staff - View Announcements");

        // Create a panel for the JFrame
        JPanel panel = new JPanel();
        jFrame.setContentPane(panel);
        panel.setBackground(new Color(57, 130, 146));
        panel.setLayout(null);

        // Create a header label
        JLabel headerLabel = new JLabel("View Announcement");
        headerLabel.setBounds(63, 0, 367, 35);
        panel.add(headerLabel);
        headerLabel.setForeground(new Color(254, 255, 255));
        headerLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Create a main panel for displaying announcements
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(103, 30, 300, 163);
        mainPanel.setBackground(new Color(57, 130, 146));
        panel.add(mainPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Get all announcements for customers
        Announcements[] announcements = globalAnnouncementsObject.getAllAnnouncements("Customers");

        // Create and add announcement text areas to the main panel
        for (Announcements announcement : announcements) {
            JTextArea announcementTextArea = createAnnouncementTextArea(announcement.subject, announcement.body, announcement.timestamp);
            mainPanel.add(announcementTextArea);
        }

        // Create a scroll pane for the main panel
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBounds(50, 30, 400, 320);
        panel.add(scrollPane);
    }

    // Create a text area for displaying individual announcements
    private JTextArea createAnnouncementTextArea(String subject, String body, String timestamp) {
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(new Color(57, 130, 146)); // Set background color
        textArea.setForeground(Color.WHITE); // Set text color
        textArea.setFont(new Font("Dialog", Font.PLAIN, 14));

        textArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE), // Set border color
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        textArea.setText("Subject: " + subject + "\nBody:\n" + body + "\n\nTimestamp: " + timestamp);

        return textArea;
    }
}
