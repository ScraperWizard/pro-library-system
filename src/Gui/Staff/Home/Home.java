package Gui.Staff.Home;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Database.BooksDB.BooksDB;
import Database.Customers.Customers;
import Database.Staff.Staff;
import Gui.Staff.Books.addBooks;
import Gui.Staff.Books.editBook;
import Gui.Staff.Books.removeBook;
import Gui.Staff.sideMenu.sideMenu;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Home {
    public JPanel homePane;
    private JTable table_1;

    public Home(JFrame mainFrame) {
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
        
        JLabel headerLabel = new JLabel("Staff - Home");
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
            	new announcements();
            }
        });
    }
}
