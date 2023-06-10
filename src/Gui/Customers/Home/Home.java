package Gui.Customers.Home;

import Database.BooksDB.BooksDB;
import Database.Staff.Staff;
import Gui.Customers.HelpDesk.newRequestPanel;
import Gui.Customers.Library.Library;
import Gui.Staff.sideMenu.sideMenu;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Home {
    public JPanel homePane;

    public Home(JFrame mainFrame, String username, JPanel parentPanel) {
        Staff globalStaffObject = new Staff();
        BooksDB globalBooksObject = new BooksDB();
        homePane = new JPanel();
        homePane.setBorder(null);
        homePane.setBackground(new Color(76, 128, 144));
        homePane.setLayout(null);

        // Get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (screenSize.width) / 2;
        int screenHeight = (screenSize.height) / 2;
        homePane.setBounds(270, 0, screenSize.width - 250, screenSize.height);
        
                JLabel headerLabel = new JLabel("Customers - Home");
                headerLabel.setForeground(new Color(254, 255, 255));
                headerLabel.setFont(new Font("Dialog", Font.BOLD, 24));
                headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
                headerLabel.setVerticalAlignment(SwingConstants.CENTER);
                headerLabel.setBounds(0, 0, 1430, 39);
                homePane.add(headerLabel);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(76, 128, 144));
        mainPanel.setBounds(493, 20, 482, 515);
        homePane.add(mainPanel);
        mainPanel.setLayout(new GridLayout(0, 1, 0, 0));

        JLabel ReadAreLeadText = new JLabel("Readers are Leaders");
        mainPanel.add(ReadAreLeadText);
        ReadAreLeadText.setForeground(Color.WHITE);
        ReadAreLeadText.setHorizontalAlignment(SwingConstants.CENTER);
        ReadAreLeadText.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));

        JPanel ourBooksPanel = new JPanel();
        mainPanel.add(ourBooksPanel);
        int thickness = 3; // Adjust the thickness value as desired
        Color borderColor = new Color(76, 128, 144);
        Border border = BorderFactory.createLineBorder(borderColor, thickness);
        ourBooksPanel.setBorder(border);
        ourBooksPanel.setBackground(new Color(153, 204, 255));
        ourBooksPanel.setLayout(null);

        JTextPane ourBooksText = new JTextPane();
        ourBooksText.setBackground(new Color(153, 204, 255));
        ourBooksText.setEditable(false);
        ourBooksText.setFont(new Font("Dialog", Font.ITALIC, 16));
        ourBooksText.setText("Welcome to Pro Library, aka your dream library.  Press on explore to learn more!");
        ourBooksText.setBounds(10, 11, 212, 110);
        ourBooksPanel.add(ourBooksText);

        JPanel explorePanel = new JPanel();
        explorePanel.setBackground(new Color(255, 255, 255));
        explorePanel.setBounds(299, 55, 108, 33);
        ourBooksPanel.add(explorePanel);
        explorePanel.setLayout(null);

        JLabel exploreText = new JLabel("EXPLORE!");
        exploreText.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
        exploreText.setHorizontalAlignment(SwingConstants.CENTER);
        exploreText.setBounds(0, 0, 108, 33);
        explorePanel.add(exploreText);

        JPanel aboutUsPanel = new JPanel();
        mainPanel.add(aboutUsPanel);
        aboutUsPanel.setBorder(border);
        aboutUsPanel.setLayout(null);
        aboutUsPanel.setBackground(new Color(153, 204, 255));

        JPanel ourGroupPicPanel = new JPanel();
        ourGroupPicPanel.setBackground(new Color(153, 204, 255));
        ourGroupPicPanel.setBounds(25, 11, 206, 110);
        aboutUsPanel.add(ourGroupPicPanel);
        ourGroupPicPanel.setLayout(null);

        JLabel OurPicture = new JLabel("");
        OurPicture.setBounds(0, 0, 206, 110);
        ourGroupPicPanel.add(OurPicture);
        OurPicture.setIcon(changeIcon(150, 132, "/Gui/images/OurPic.jpeg"));

        JPanel aboutUsPanelButton = new JPanel();
        aboutUsPanelButton.setBounds(305, 60, 117, 33);
        aboutUsPanel.add(aboutUsPanelButton);
        aboutUsPanelButton.setLayout(null);

        JLabel aboutUsLabel = new JLabel("ABOUT US!");
        aboutUsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        aboutUsLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
        aboutUsLabel.setBounds(0, 0, 117, 33);
        aboutUsPanelButton.add(aboutUsLabel);

        JPanel sugestionPanel = new JPanel();
        mainPanel.add(sugestionPanel);
        sugestionPanel.setBorder(border);
        sugestionPanel.setLayout(null);
        sugestionPanel.setBackground(new Color(153, 204, 255));

        JPanel suggestButtonPanel = new JPanel();
        suggestButtonPanel.setLayout(null);
        suggestButtonPanel.setBounds(305, 60, 117, 33);
        sugestionPanel.add(suggestButtonPanel);

        JLabel suggestLabel = new JLabel("SUGGEST!");
        suggestLabel.setHorizontalAlignment(SwingConstants.CENTER);
        suggestLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
        suggestLabel.setBounds(0, 0, 117, 33);
        suggestButtonPanel.add(suggestLabel);

        JTextArea suggestTextArea = new JTextArea();
        suggestTextArea.setBackground(new Color(153, 204, 255));
        suggestTextArea.setBounds(39, 60, 154, 62);
        sugestionPanel.add(suggestTextArea);
        suggestTextArea.setFont(new Font("Dialog", Font.ITALIC, 15));
        suggestTextArea.setText("        Any idea? \r\n     Do not hesitate\r\n       to suggest it ");
        suggestTextArea.setEditable(false);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(153, 204, 255));
        panel.setBounds(70, 11, 57, 51);
        sugestionPanel.add(panel);

        JLabel bulbImage = new JLabel("");
        panel.add(bulbImage);
        bulbImage.setIcon(changeIcon(65, 56, "/Gui/images/Lamba.png"));

        JLabel mailIcon = new JLabel("");
        mailIcon.setIcon(new ImageIcon(sideMenu.class.getResource("/Gui/images/mail.png")));
        mailIcon.setBounds(1270, 63, 40, 40);
        homePane.add(mailIcon);

        mailIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                mailIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mailIcon.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                new CustomerAnnouncement();
            }
        });

        JPanel policiesPanel = new JPanel();
        policiesPanel.setLayout(null);
        policiesPanel.setBackground(new Color(153, 204, 255));
        policiesPanel.setBounds(119, 510, 590, 133);

        // Create a container panel to hold the panels inside the scroll pane

        JPanel suggestButtonPanel_1 = new JPanel();
        suggestButtonPanel_1.setLayout(null);
        suggestButtonPanel_1.setBounds(305, 60, 117, 33);
        policiesPanel.add(suggestButtonPanel_1);

        JLabel suggestLabel_1 = new JLabel("CHECK!");
        suggestLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        suggestLabel_1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
        suggestLabel_1.setBounds(0, 0, 117, 33);
        suggestButtonPanel_1.add(suggestLabel_1);

        JTextArea suggestTextArea_1 = new JTextArea();
        String text = "Library policies include:\n- Membership requirements\n- Borrowing limits\n- Due dates\n- Late fees\n- Guidelines for behavior and resource usage";
        suggestTextArea_1.setText(text);
        suggestTextArea_1.setFont(new Font("Dialog", Font.ITALIC, 13));
        suggestTextArea_1.setEditable(false);
        suggestTextArea_1.setBackground(new Color(153, 204, 255));
        suggestTextArea_1.setBounds(34, 11, 154, 154);
        policiesPanel.add(suggestTextArea_1);

        JLabel policiesHeader = new JLabel("");
        policiesHeader.setBounds(64, 35, 112, 14);
        policiesPanel.add(policiesHeader);

        MouseAdapter panelMouseEventHandler = null;

        panelMouseEventHandler = new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                Component optionClicked = event.getComponent();
                if (optionClicked == explorePanel) {
                    // Delete everything inside the panel
                    parentPanel.removeAll();
                    parentPanel.repaint();
                    parentPanel.revalidate();
                    Library libraryFrame = new Library(mainFrame, username);
                    parentPanel.add(libraryFrame.libraryPanel);
                    parentPanel.repaint();
                    parentPanel.revalidate();
                    mainFrame.getContentPane().repaint();
                } else if (optionClicked == aboutUsPanelButton) {
                    homePane.removeAll();
                    aboutUsPage aboutUs = new aboutUsPage();
                    homePane.add(aboutUs.AboutUsPane);
                    aboutUs.AboutUsPane.setBounds(-60, -100, screenSize.width - 250, screenSize.height);
                } else if (optionClicked == suggestButtonPanel) {
                    new newRequestPanel(username);
                }

                homePane.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent event) {
                // handle mouse enter event
                event.getComponent().setBackground(new Color(123, 147, 163));
                event.getComponent().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent event) {
                event.getComponent().setBackground(new Color(240, 240, 240));
                event.getComponent().setCursor(Cursor.getDefaultCursor());

            }
        };
        explorePanel.addMouseListener(panelMouseEventHandler);
        suggestButtonPanel.addMouseListener(panelMouseEventHandler);
        aboutUsPanelButton.addMouseListener(panelMouseEventHandler);

    }

    public ImageIcon changeIcon(int width, int height, String path) {
        ImageIcon icon = new ImageIcon(sideMenu.class.getResource(path));
        Image image = icon.getImage();
        int scaledWidth = width; // The new width you want
        int scaledHeight = height; // The new height you want
        Image scaledImage = image.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        return scaledIcon;
    }
}
