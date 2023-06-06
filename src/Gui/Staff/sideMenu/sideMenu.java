package Gui.Staff.sideMenu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Database.Customers.Customers;
import Database.Staff.Staff;
import Gui.Staff.Admin.Admin;
import Gui.Staff.Books.Books;
import Gui.Staff.Customers.Customer;
import Gui.Staff.Home.Home;
import Gui.loginSelector.loginSelector;
import Gui.staffLogin.staffLogin;
import java.awt.GridLayout;

public class sideMenu {
    public JPanel contentPane;
    private final JPanel sideMenuPanel = new JPanel();

    public sideMenu(JFrame mainFrame, String uniqueID) {
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
        
        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setBackground(new Color(57, 130, 146));
        mainContentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        mainContentPanel.setBounds(0, 0, 1920, 570);
        mainContentPanel.setLayout(null);

        sideMenuPanel.setBackground(new Color(83, 107, 123));
        sideMenuPanel.setBounds(0, 0, 270, 570);
        sideMenuPanel.setLayout(null);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
        mainFrame.setBounds(0, 150, screenSize.width, 588);
        mainFrame.setTitle("Pro Library - Staff");
        contentPane.add(sideMenuPanel);
        
        // This will open Customer page
        Customer customersFrame = new Customer(mainFrame, uniqueID);
        mainContentPanel.add(customersFrame.customersPane);
        contentPane.add(mainContentPanel);
        
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
        signoutIcon_1_1.setIcon(new ImageIcon(sideMenu.class.getResource("/Gui/images/home.png")));
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

        JLabel signoutIcon_1_3 = new JLabel("");
        signoutIcon_1_3.setBounds(20, 6, 40, 40);
        customersPanel.add(signoutIcon_1_3);
        signoutIcon_1_3.setIcon(changeIcon(40, 40, "/Gui/images/customers.png"));

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

        JLabel signoutIcon_1_2 = new JLabel("");
        signoutIcon_1_2.setIcon(new ImageIcon(sideMenu.class.getResource("/Gui/images/books.png")));
        signoutIcon_1_2.setBounds(20, 6, 40, 40);
        booksPanel.add(signoutIcon_1_2);

        JPanel adminPanel = new JPanel();
        adminPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        adminPanel.setBackground(new Color(83, 107, 123));
        adminPanel.setBounds(0, 360, 270, 50);
        sideMenuPanel.add(adminPanel);
        adminPanel.setLayout(null);

        JLabel lblAdmin = new JLabel("ADMIN");
        lblAdmin.setForeground(new Color(254, 255, 255));
        lblAdmin.setFont(new Font("Dialog", Font.BOLD, 18));
        lblAdmin.setBounds(90, 6, 104, 34);
        adminPanel.add(lblAdmin);

        JLabel signoutIcon_1_4 = new JLabel("");
        signoutIcon_1_4.setIcon(new ImageIcon(sideMenu.class.getResource("/Gui/images/admin.png")));
        signoutIcon_1_4.setBounds(20, 6, 40, 40);
        adminPanel.add(signoutIcon_1_4);

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
        signoutIcon_1.setIcon(new ImageIcon(sideMenu.class.getResource("/Gui/images/Settings.png")));
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
        signoutIcon.setIcon(new ImageIcon(sideMenu.class.getResource("/Gui/images/signOut.png")));
        signOutPanel.add(signoutIcon);

        JLabel lblSignOut = new JLabel("SIGNOUT");
        lblSignOut.setForeground(new Color(254, 255, 255));
        lblSignOut.setFont(new Font("Dialog", Font.BOLD, 18));
        lblSignOut.setBounds(90, 6, 104, 34);
        signOutPanel.add(lblSignOut);

        JPanel infoPanel = new JPanel();
        infoPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        infoPanel.setBackground(new Color(83, 107, 123));
        infoPanel.setBounds(0, 510, 270, 60);
        sideMenuPanel.add(infoPanel);
        infoPanel.setLayout(null);

        JLabel lblNewLabel_2 = new JLabel(currentUser.role + ": " + currentUser.username);
        lblNewLabel_2.setBounds(5, 1, 268, 50);
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
        lblLogo.setIcon(changeIcon(258, 189, "/Gui/images/logo.png"));
        logoPanel.add(lblLogo);


        // Declare the mouse event handler
        MouseAdapter panelMouseEventHandler = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
            	Component optionClicked = event.getComponent();

                // Delete everything inside the panel
                mainContentPanel.removeAll();
                mainContentPanel.repaint();
                mainContentPanel.revalidate();

                // Add the appropiate panel
                if(optionClicked == homePanel) {
                    Home homeFrame = new Home(mainFrame);
                    mainContentPanel.add(homeFrame.homePane);
                } else if(optionClicked == booksPanel) {
                    Books booksFrame = new Books(mainFrame, uniqueID);
                    mainContentPanel.add(booksFrame.booksPane, uniqueID);
            	} else if(optionClicked == customersPanel) {
                    Customer customersFrame = new Customer(mainFrame, uniqueID);
                    mainContentPanel.add(customersFrame.customersPane);
            	} else if(optionClicked == adminPanel) {
                    Admin adminFrame = new Admin(mainFrame, uniqueID);
                    mainContentPanel.add(adminFrame.adminPane);
                } else if(optionClicked == signOutPanel) {
                    staffLogin staffLoginFrame = new staffLogin(mainFrame);
                    mainFrame.setContentPane(staffLoginFrame.contentPane);
                }

                mainContentPanel.repaint();
                contentPane.repaint();
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
        adminPanel.addMouseListener(panelMouseEventHandler);

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
