package Gui.Customers.Library;

import Database.BooksDB.BooksDB;
import Database.CustomerTickets.CustomerTickets;
import Database.Customers.Customers;
import Database.Staff.Staff;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RequestBook extends JFrame {

    private JPanel contentPane;
    private JTextField bookInput;
    private JTextField authorInput;
    private JTextField noteInput;
    private JTable table;


    public RequestBook() {
        CustomerTickets globalTicketsObject = new CustomerTickets();

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String actualTimestamp = now.format(formatter);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (screenSize.width) / 2;
        int screenHeight = (screenSize.height) / 2;
        
        // Frame
        JFrame jFrame = new JFrame();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setBounds(100, 100, 450, 350);
        jFrame.setTitle("Customer - Request a book");

        // Parent panel
        JPanel panel = new JPanel();
        jFrame.setContentPane(panel);
        panel.setBackground(new Color(57, 130, 146));
        panel.setLayout(null);

        // Request book label
        JLabel requestBookLabel = new JLabel("Request a book");
        requestBookLabel.setBounds(0, 0, 450, 35);
        panel.add(requestBookLabel);
        requestBookLabel.setForeground(new Color(254, 255, 255));
        requestBookLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        requestBookLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Child panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(6, 47, 438, 240);
        mainPanel.setBackground(new Color(57, 130, 146));
        panel.add(mainPanel);
        mainPanel.setLayout(new GridLayout(0, 1, 0, 0));

        // Book name label
        JLabel bookLabel = new JLabel("Book:");
        bookLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        bookLabel.setForeground(new Color(254, 255, 255));
        mainPanel.add(bookLabel);
        
        // Book input        
        bookInput = new JTextField();
        mainPanel.add(bookInput);
        bookInput.setColumns(10);

        // Note label
        JLabel noteLabel = new JLabel("Note:");
        noteLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        noteLabel.setForeground(new Color(254, 255, 255));
        mainPanel.add(noteLabel);
        
        // Note input
        noteInput = new JTextField();
        mainPanel.add(noteInput);
        noteInput.setColumns(10);

        // Request button
        JButton requestButton = new JButton("Request!");
        requestButton.setForeground(new Color(0, 0, 0));
        requestButton.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        mainPanel.add(requestButton);
        
        requestButton.addActionListener(clickEvent -> {
        	String bookName = bookInput.getText();
        	String note = noteInput.getText();
        	
        	if(bookName.equals("")) {
        		JOptionPane.showMessageDialog(panel, "Please enter a book name!");
        	} else {
                try {
//                    String ticketID = globalTicketsObject.addTicket(, "Staff", "Book request", "I would like to request this book, please add it " + bookName);
//                    JOptionPane.showMessageDialog(panel, bookName + " has been requested, the book might be added to our library in the next 5 working days.");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
        	}
        });
    }
}

