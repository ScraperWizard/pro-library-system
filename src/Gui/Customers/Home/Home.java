package Gui.Customers.Home;

import Database.BooksDB.BooksDB;
import Database.Staff.Staff;
import Gui.Customers.HelpDesk.newRequestPanel;
import Gui.Customers.Library.Library;
import Gui.Staff.Home.announcements;
import Gui.Staff.sideMenu.sideMenu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Home {
    public JPanel homePane;

    public Home(JFrame mainFrame, String username) {
        Staff globalStaffObject = new Staff();
        BooksDB globalBooksObject = new BooksDB();
        homePane = new JPanel();
        homePane.setBackground(new Color(76, 128, 144));
        homePane.setBorder(new EmptyBorder(5, 5, 5, 5));
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
        headerLabel.setBounds(-120, 0, screenSize.width - 250, 39);
        homePane.add(headerLabel);

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

        JLabel ReadAreLeadText = new JLabel("Readers are Leaders");
        ReadAreLeadText.setHorizontalAlignment(SwingConstants.CENTER);
        ReadAreLeadText.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
        ReadAreLeadText.setBounds(252, 87, 398, 51);
        homePane.add(ReadAreLeadText);

        JPanel aboutUsPanel = new JPanel();
        aboutUsPanel.setBounds(311, 402, 482, 133);
        homePane.add(aboutUsPanel);
        aboutUsPanel.setLayout(null);
        aboutUsPanel.setBackground(new Color(153, 204, 255));

        JPanel ourGroupPicPanel = new JPanel();
        ourGroupPicPanel.setBackground(new Color(153, 204, 255));
        ourGroupPicPanel.setBounds(25, 11, 206, 110);
        aboutUsPanel.add(ourGroupPicPanel);
        ourGroupPicPanel.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("our pic all together");
        lblNewLabel_1.setBounds(26, 44, 132, 14);
        ourGroupPicPanel.add(lblNewLabel_1);

        JPanel aboutUsPanelButton = new JPanel();
        aboutUsPanelButton.setBounds(305, 60, 117, 33);
        aboutUsPanel.add(aboutUsPanelButton);
        aboutUsPanelButton.setLayout(null);

        JLabel aboutUsLabel = new JLabel("ABOUT US!");
        aboutUsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        aboutUsLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
        aboutUsLabel.setBounds(0, 0, 117, 33);
        aboutUsPanelButton.add(aboutUsLabel);

        JPanel ourBooksPanel = new JPanel();
        ourBooksPanel.setBounds(311, 238, 482, 133);
        homePane.add(ourBooksPanel);
        ourBooksPanel.setBackground(new Color(153, 204, 255));
        ourBooksPanel.setLayout(null);

        JTextPane ourBooksText = new JTextPane();
        ourBooksText.setBackground(new Color(153, 204, 255));
        ourBooksText.setEditable(false);
        ourBooksText.setFont(new Font("Dialog", Font.ITALIC, 16));
        ourBooksText.setText("  Welcome to Pro Library,   aka your dream library. \r\n  Our journey does not stop   here, explore our books for   a phenomenal experience  ");
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

        JPanel sugestionPanel = new JPanel();
        sugestionPanel.setBounds(311, 564, 482, 133);
        homePane.add(sugestionPanel);
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
        suggestTextArea.setBounds(39, 60, 154, 73);
        sugestionPanel.add(suggestTextArea);
        suggestTextArea.setFont(new Font("Dialog", Font.ITALIC, 15));
        suggestTextArea.setText("        Any idea? \r\n     Do not hesitate\r\n       to suggest it ");
        suggestTextArea.setEditable(false);

        JLabel lblNewLabel = new JLabel("Small bulb image");
        lblNewLabel.setBounds(64, 35, 112, 14);
        sugestionPanel.add(lblNewLabel);


        MouseAdapter panelMouseEventHandler = null;

        panelMouseEventHandler = new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                Component optionClicked = event.getComponent();
                homePane.removeAll();
                homePane.repaint();
                homePane.revalidate();
                if (optionClicked == explorePanel) {
                    Library libraryFrame = new Library(mainFrame, username);
                    homePane.add(libraryFrame.libraryPanel);
                } else if(optionClicked == aboutUsPanelButton) {
                    aboutUsPage aboutUs = new aboutUsPage ();
                    homePane.add(aboutUs.AboutUsPane);
                } else if (optionClicked == suggestButtonPanel) {
                    newRequestPanel suggest = new newRequestPanel(" ");
                    homePane.add(suggest.jFrame);
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
                // handle mouse exit event
                event.getComponent().setBackground(new Color(83, 107, 123));
                event.getComponent().setCursor(Cursor.getDefaultCursor());
            }
        };

        suggestButtonPanel.addMouseListener(panelMouseEventHandler);
        explorePanel.addMouseListener(panelMouseEventHandler);
        explorePanel.addMouseListener(panelMouseEventHandler);
        aboutUsPanelButton.addMouseListener(panelMouseEventHandler);
    }
}

