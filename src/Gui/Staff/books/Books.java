package Gui.Staff.books;

import Database.Customers.Customers;
import Database.Staff.Staff;
import Gui.Login.Login;
import Gui.Main.MainFrame;
import Gui.loginSelector.loginSelector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;

public class Books {
    public JPanel contentPane;
    private final JPanel sideMenuPanel = new JPanel();

    public Books(JFrame mainFrame, String uniqueID) {
        Staff globalStaffObject = new Staff();
        Staff currentUser = globalStaffObject.getStaff(uniqueID);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(57, 130, 146));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);

        // Get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (screenSize.width) / 2;
        int screenHeight = (screenSize.height) / 2;
        sideMenuPanel.setBorder(new LineBorder(new Color(0, 0, 0)));

        sideMenuPanel.setBackground(new Color(83, 107, 123));
        sideMenuPanel.setBounds(0, 0, 270, 560);
        contentPane.add(sideMenuPanel);
        sideMenuPanel.setLayout(null);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
        mainFrame.setBounds(0, 150, screenSize.width, 588);
        mainFrame.setTitle("Pro Library - Staff");
        
        JPanel homePanel = new JPanel();
        homePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        homePanel.setBackground(new Color(83, 107, 123));
        homePanel.setBounds(0, 210, 270, 50);
        sideMenuPanel.add(homePanel);
        homePanel.setLayout(null);
        
        JLabel lblHome = new JLabel("HOME");
        lblHome.setHorizontalAlignment(SwingConstants.LEFT);
        lblHome.setForeground(new Color(254, 255, 255));
        lblHome.setBounds(90, 6, 104, 34);
        lblHome.setFont(new Font("Dialog", Font.BOLD, 18));
        homePanel.add(lblHome);
        
        JLabel signoutIcon_1_1 = new JLabel("");
        signoutIcon_1_1.setIcon(new ImageIcon(Books.class.getResource("/Gui/images/home.png")));
        signoutIcon_1_1.setBounds(20, 3, 40, 40);
        homePanel.add(signoutIcon_1_1);
        
        JPanel customersPanel = new JPanel();
        customersPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        customersPanel.setBackground(new Color(83, 107, 123));
        customersPanel.setBounds(0, 260, 270, 50);
        sideMenuPanel.add(customersPanel);
        customersPanel.setLayout(null);
        
        JLabel lblCustomers = new JLabel("CUSTOMERS");
        lblCustomers.setForeground(new Color(254, 255, 255));
        lblCustomers.setFont(new Font("Dialog", Font.BOLD, 18));
        lblCustomers.setBounds(90, 6, 180, 34);
        customersPanel.add(lblCustomers);
        
        JPanel booksPanel = new JPanel();
        booksPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        booksPanel.setBackground(new Color(83, 107, 123));
        booksPanel.setBounds(0, 310, 270, 50);
        sideMenuPanel.add(booksPanel);
        booksPanel.setLayout(null);
        
        JLabel lblBooks = new JLabel("BOOKS");
        lblBooks.setForeground(new Color(254, 255, 255));
        lblBooks.setFont(new Font("Dialog", Font.BOLD, 18));
        lblBooks.setBounds(90, 6, 104, 34);
        booksPanel.add(lblBooks);
        
        JPanel nullPanel = new JPanel();
        nullPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        nullPanel.setBackground(new Color(83, 107, 123));
        nullPanel.setBounds(0, 360, 270, 50);
        sideMenuPanel.add(nullPanel);
        nullPanel.setLayout(null);
        
        JLabel lblNull = new JLabel("ADMIN");
        lblNull.setForeground(new Color(254, 255, 255));
        lblNull.setFont(new Font("Dialog", Font.BOLD, 18));
        lblNull.setBounds(90, 6, 104, 34);
        nullPanel.add(lblNull);
        
        JPanel settingsPanel = new JPanel();
        settingsPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        settingsPanel.setBackground(new Color(83, 107, 123));
        settingsPanel.setBounds(0, 410, 270, 50);
        sideMenuPanel.add(settingsPanel);
        settingsPanel.setLayout(null);
        
        JLabel lblSettings = new JLabel("SETTINGS");
        lblSettings.setForeground(new Color(254, 255, 255));
        lblSettings.setFont(new Font("Dialog", Font.BOLD, 18));
        lblSettings.setBounds(90, 6, 104, 34);
        settingsPanel.add(lblSettings);
        
        JLabel signoutIcon_1 = new JLabel("");
        signoutIcon_1.setIcon(new ImageIcon(Books.class.getResource("/Gui/images/Settings.png")));
        signoutIcon_1.setBounds(20, 6, 40, 40);
        settingsPanel.add(signoutIcon_1);
        
        JPanel signOutPanel = new JPanel();
        signOutPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        signOutPanel.setBackground(new Color(83, 107, 123));
        signOutPanel.setBounds(0, 460, 270, 50);
        sideMenuPanel.add(signOutPanel);
        signOutPanel.setLayout(null);
        
        JLabel signoutIcon = new JLabel("");
        signoutIcon.setBounds(20, 6, 40, 40);
        signoutIcon.setIcon(new ImageIcon(Books.class.getResource("/Gui/images/signOut.png")));
        signOutPanel.add(signoutIcon);
        
        JLabel lblSignOut = new JLabel("SIGNOUT");
        lblSignOut.setForeground(new Color(254, 255, 255));
        lblSignOut.setFont(new Font("Dialog", Font.BOLD, 18));
        lblSignOut.setBounds(90, 6, 104, 34);
        signOutPanel.add(lblSignOut);
        
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(null);
        infoPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        infoPanel.setBackground(new Color(83, 107, 123));
        infoPanel.setBounds(0, 510, 270, 50);
        sideMenuPanel.add(infoPanel);
        
        JLabel lblNewLabel_2 = new JLabel(currentUser.role + ": " + currentUser.username);
        lblNewLabel_2.setBounds(20, 16, 270, 16);
        infoPanel.add(lblNewLabel_2);
        lblNewLabel_2.setForeground(new Color(254, 255, 255));
        lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 14));
        
        JPanel logoPanel = new JPanel();
        logoPanel.setLayout(null);
        logoPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        logoPanel.setBackground(new Color(83, 107, 123));
        logoPanel.setBounds(0, 0, 270, 210);
        sideMenuPanel.add(logoPanel);
        
        JLabel lblLogo = new JLabel("");
        lblLogo.setBounds(0, 0, 258, 189);
        ImageIcon icon = new ImageIcon("/Users/abdulrahmananas/git/pro-library-system/src/Gui/images/logo.png");
        Image image = icon.getImage();
        int scaledWidth = 258; // The new width you want
        int scaledHeight = 189; // The new height you want
        Image scaledImage = image.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        lblLogo.setIcon(scaledIcon);
        logoPanel.add(lblLogo);

        
        
        // Declare the mouse event handler
        MouseAdapter panelMouseEventHandler = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                contentPane.setVisible(false);
                loginSelector loginSelectorFrame = new loginSelector(mainFrame);
                mainFrame.setContentPane(loginSelectorFrame.contentPane);
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

        // Register the mouse event handler with the panel
        signOutPanel.addMouseListener(panelMouseEventHandler);
        homePanel.addMouseListener(panelMouseEventHandler);
        customersPanel.addMouseListener(panelMouseEventHandler);
        settingsPanel.addMouseListener(panelMouseEventHandler);
        booksPanel.addMouseListener(panelMouseEventHandler);
        nullPanel.addMouseListener(panelMouseEventHandler);
        
    }
}
