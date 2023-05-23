package Gui.Customers.Settings;

import Database.BooksDB.BooksDB;
import Database.Customers.Customers;
import Database.Staff.Staff;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Settings {
    public JPanel settingsPanel;

    public Settings(JFrame mainFrame, String username) {
        System.out.println(username);
        Customers globalCustomerObject = new Customers();
        Customers currentUser = globalCustomerObject.getUser(username);
        settingsPanel = new JPanel();
        settingsPanel.setBackground(new Color(76, 128, 144));
        settingsPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        settingsPanel.setLayout(null);

        // Get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (screenSize.width) / 2;
        int screenHeight = (screenSize.height) / 2;
        settingsPanel.setBounds(270, 0, screenSize.width - 250, screenSize.height);

        JLabel headerLabel = new JLabel("Customers - Settings");
        headerLabel.setForeground(new Color(254, 255, 255));
        headerLabel.setFont(new Font("Dialog", Font.BOLD, 24));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setVerticalAlignment(SwingConstants.CENTER);
        headerLabel.setBounds(-120, 0, screenSize.width - 250, 39);
        settingsPanel.add(headerLabel);

        JPanel showUserNamePanel = new JPanel();
        showUserNamePanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        showUserNamePanel.setBounds(784, 164, 207, 23);
        settingsPanel.add(showUserNamePanel);
        showUserNamePanel.setLayout(new GridLayout(0, 1, 0, 0));

        JLabel usernameLabel = new JLabel("New label");
        showUserNamePanel.add(usernameLabel);
        usernameLabel.setText(currentUser.username);

        JLabel userNameLabel = new JLabel("Username :");
        userNameLabel.setBounds(784, 141, 153, 23);
        settingsPanel.add(userNameLabel);

        JLabel EmailLabel = new JLabel("E-mail : ");
        EmailLabel.setBounds(784, 198, 153, 23);
        settingsPanel.add(EmailLabel);

        JPanel showEmailPanel = new JPanel();
        showEmailPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        showEmailPanel.setBounds(784, 220, 207, 23);
        settingsPanel.add(showEmailPanel);
        showEmailPanel.setLayout(new GridLayout(0, 1, 0, 0));

        JLabel emailLabel = new JLabel("New label");
        showEmailPanel.add(emailLabel);
        emailLabel.setText(currentUser.email);

        JLabel PhoneNumberLabel = new JLabel("Phone number : ");
        PhoneNumberLabel.setBounds(784, 254, 153, 23);
        settingsPanel.add(PhoneNumberLabel);

        JPanel showPhoneNumPanel = new JPanel();
        showPhoneNumPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        showPhoneNumPanel.setBounds(784, 277, 207, 23);
        settingsPanel.add(showPhoneNumPanel);
        showPhoneNumPanel.setLayout(new GridLayout(0, 1, 0, 0));

        JLabel phoneNumLabel = new JLabel("New label");
        showPhoneNumPanel.add(phoneNumLabel);
        phoneNumLabel.setText(currentUser.contact);

        JButton changePassWordButton = new JButton("Change Password");

        changePassWordButton.addActionListener(clickEvent -> {
            changePassword changePassFrame = new changePassword(username);
        });

        changePassWordButton.setForeground(new Color(0, 139, 139));
        changePassWordButton.setFont(new Font("Dialog", Font.PLAIN, 15));
        changePassWordButton.setBounds(784, 311, 207, 30);
        settingsPanel.add(changePassWordButton);

        // Declare the mouse event handler
        MouseAdapter panelMouseEventHandler = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
//                contentPane.setVisible(false);
//                loginSelector loginSelectorFrame = new loginSelector(mainFrame);
//                mainFrame.setContentPane(loginSelectorFrame.contentPane);

            }

            @Override
            public void mouseEntered(MouseEvent event) {
                // handle mouse enter event
                event.getComponent().setBackground(new Color(123, 147, 163));
                event.getComponent().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent event) {
                // handle mouse exit event
                event.getComponent().setBackground(new Color(83, 107, 123));
                event.getComponent().setCursor(Cursor.getDefaultCursor());
            }
        };
    }
}
