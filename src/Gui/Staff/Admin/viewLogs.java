package Gui.Staff.Admin;

import Database.Announcements.Announcements;
import Database.Logs.Logs;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;

public class viewLogs {
    public JFrame jFrame;
    public viewLogs() {
        jFrame = new JFrame();
        Logs globalLogsObject = new Logs();

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setBounds(500, 500, 500, 400);
        jFrame.setTitle("Admin - View Logs");

        JPanel panel = new JPanel();
        jFrame.setContentPane(panel);
        panel.setBackground(new Color(57, 130, 146));
        panel.setLayout(null);

        JLabel headerLabel = new JLabel("View Logs");
        headerLabel.setBounds(63, 0, 367, 35);
        panel.add(headerLabel);
        headerLabel.setForeground(new Color(254, 255, 255));
        headerLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(103, 30, 300, 163);
        mainPanel.setBackground(new Color(57, 130, 146));
        panel.add(mainPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Retrieve announcements from globalAnnouncementsObject
        Logs[] logs = globalLogsObject.getLogs(null, null);

        for (Logs log : logs) {
            JTextArea announcementTextArea = createAnnouncementTextArea(log);
            mainPanel.add(announcementTextArea);
        }

        // Scrolling
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBounds(50, 30, 400, 320);
        panel.add(scrollPane);

    }

    private JTextArea createAnnouncementTextArea(Logs log) {
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
        // Loop over all propteries and add them to finalString if they are not null
        StringBuilder finalString = new StringBuilder();

        // Get the class of the Logs object
        Class<?> logsClass = log.getClass();

        // Get all declared fields of the Logs class
        Field[] fields = logsClass.getDeclaredFields();

        // Iterate over the fields and append their values to finalString if they are not null
        for (Field field : fields) {

            try {
                Object value = field.get(log);
                if (!value.equals("null")) {
                    finalString.append(field.getName()).append(": ").append(value).append("\n");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        textArea.setText(finalString.toString());

        return textArea;
    }
}
