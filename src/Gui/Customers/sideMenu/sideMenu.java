package Gui.Customers.sideMenu;

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
import Gui.Customers.HelpDesk.HelpDesk;
import Gui.Customers.Library.Library;
import Gui.Customers.MyBooks.MyBooks;
import Gui.Customers.Settings.Settings;
import Gui.Staff.Admin.Admin;
import Gui.Staff.Books.Books;
import Gui.Staff.Customers.Customer;
import Gui.Customers.Home.Home;
import Gui.loginSelector.loginSelector;
import Gui.staffLogin.staffLogin;

public class sideMenu {
    public JPanel contentPane;
    private final JPanel sideMenuPanel = new JPanel();

    public sideMenu(JFrame mainFrame, String username) {
        Staff globalStaffObject = new Staff();
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
        Home homeFrame = new Home(mainFrame);
        mainContentPanel.add(homeFrame.homePane);

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

        JLabel homeIcon = new JLabel("");
        homeIcon.setIcon(new ImageIcon(sideMenu.class.getResource("/Gui/images/home.png")));
        homeIcon.setBounds(20, 3, 40, 40);
        homePanel.add(homeIcon);

        JPanel myBooksPanel = new JPanel();
        myBooksPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        myBooksPanel.setBackground(new Color(83, 107, 123));
        myBooksPanel.setBounds(0, 260, 270, 50);
        sideMenuPanel.add(myBooksPanel);
        myBooksPanel.setLayout(null);

        JLabel lblMyBooks = new JLabel("MY BOOKS");
        lblMyBooks.setForeground(new Color(254, 255, 255));
        lblMyBooks.setFont(new Font("Dialog", Font.BOLD, 18));
        lblMyBooks.setBounds(90, 6, 180, 34);
        myBooksPanel.add(lblMyBooks);

        JLabel booksIcon = new JLabel("");
        booksIcon.setBounds(20, 6, 40, 40);
        myBooksPanel.add(booksIcon);
        booksIcon.setIcon(changeIcon(40, 40, "/Gui/images/customers.png"));

        JPanel libraryPanel = new JPanel();
        libraryPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        libraryPanel.setBackground(new Color(83, 107, 123));
        libraryPanel.setBounds(0, 310, 270, 50);
        sideMenuPanel.add(libraryPanel);
        libraryPanel.setLayout(null);

        JLabel lblLibrary = new JLabel("LIBRARY");
        lblLibrary.setForeground(new Color(254, 255, 255));
        lblLibrary.setFont(new Font("Dialog", Font.BOLD, 18));
        lblLibrary.setBounds(90, 6, 104, 34);
        libraryPanel.add(lblLibrary);

        JLabel libraryIcon = new JLabel("");
        libraryIcon.setIcon(new ImageIcon(sideMenu.class.getResource("/Gui/images/books.png")));
        libraryIcon.setBounds(20, 6, 40, 40);
        libraryPanel.add(libraryIcon);

        JPanel customerServicePanel = new JPanel();
        customerServicePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        customerServicePanel.setBackground(new Color(83, 107, 123));
        customerServicePanel.setBounds(0, 360, 270, 50);
        sideMenuPanel.add(customerServicePanel);
        customerServicePanel.setLayout(null);

        JLabel lblCustomerSupport = new JLabel("HELPDESK");
        lblCustomerSupport.setForeground(new Color(254, 255, 255));
        lblCustomerSupport.setFont(new Font("Dialog", Font.BOLD, 18));
        lblCustomerSupport.setBounds(90, 6, 210, 34);
        customerServicePanel.add(lblCustomerSupport);

        JLabel customerSupportIcon = new JLabel("");
        customerSupportIcon.setIcon(new ImageIcon(sideMenu.class.getResource("/Gui/images/admin.png")));
        customerSupportIcon.setBounds(20, 6, 40, 40);
        customerServicePanel.add(customerSupportIcon);

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

        JLabel settingsIcon = new JLabel("");
        settingsIcon.setIcon(new ImageIcon(sideMenu.class.getResource("/Gui/images/Settings.png")));
        settingsIcon.setBounds(20, 6, 40, 40);
        settingsPanel.add(settingsIcon);
        MouseAdapter panelMouseEventHandler = null;
        settingsPanel.addMouseListener(panelMouseEventHandler);

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
        infoPanel.setLayout(null);
        infoPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        infoPanel.setBackground(new Color(83, 107, 123));
        infoPanel.setBounds(0, 510, 270, 60);
        sideMenuPanel.add(infoPanel);

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
        panelMouseEventHandler = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                Component optionClicked = event.getComponent();

                // Delete everything inside the panel
                mainContentPanel.removeAll();
                mainContentPanel.repaint();
                mainContentPanel.revalidate();

                // Add the appropiate panel
                if (optionClicked == homePanel) {
                    Home homeFrame = new Home(mainFrame);
                    mainContentPanel.add(homeFrame.homePane);
                } else if(optionClicked == myBooksPanel) {
                    MyBooks myBooksFrame = new MyBooks(mainFrame);
                    mainContentPanel.add(myBooksFrame.myBooksPane);
                } else if(optionClicked == customerServicePanel) {
                    HelpDesk helpDeskFrame = new HelpDesk(mainFrame);
                    mainContentPanel.add(helpDeskFrame.helpDeskPane);
                } else if(optionClicked == libraryPanel) {
                    Library libraryFrame = new Library(mainFrame);
                    mainContentPanel.add(libraryFrame.libraryPanel);
                } else if(optionClicked == settingsPanel) {
                    Settings settingsFrame = new Settings(mainFrame, username);
                    mainContentPanel.add(settingsFrame.settingsPanel);
                } else if(optionClicked == signOutPanel) {
                    loginSelector loginSelectorFrame = new loginSelector(mainFrame);
                    mainFrame.setContentPane(loginSelectorFrame.contentPane);
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
        myBooksPanel.addMouseListener(panelMouseEventHandler);
        settingsPanel.addMouseListener(panelMouseEventHandler);
        libraryPanel.addMouseListener(panelMouseEventHandler);
        customerServicePanel.addMouseListener(panelMouseEventHandler);

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
