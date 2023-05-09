package Gui.Staff.Books;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Database.BooksDB.BooksDB;
import Database.Customers.*;

public class addBooks {
    public JFrame jFrame;
    private JTextField bookInput;
    private JTextField authorInput;
    private JTextField genreInput;
    private JTextField noteInput;
    public addBooks() {
        jFrame = new JFrame();
        BooksDB globalBooksDBObject = new BooksDB();

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setBounds(500, 500, 500, 400);
        jFrame.setTitle("Staff - Add book");
        
        JPanel panel = new JPanel();
        jFrame.setContentPane(panel);
        panel.setBackground(new Color(57, 130, 146));
        panel.setLayout(null);
        
        JLabel addBooksLabel = new JLabel("Add books");
        addBooksLabel.setBounds(63, 0, 367, 35);
        panel.add(addBooksLabel);
        addBooksLabel.setForeground(new Color(254, 255, 255));
        addBooksLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        addBooksLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(103, 24, 300, 319);
        mainPanel.setBackground(new Color(57, 130, 146));
        panel.add(mainPanel);
        mainPanel.setLayout(new GridLayout(0, 1, 0, 0));
        
        JLabel authorLabel = new JLabel("Author:");
        authorLabel.setForeground(new Color(254, 255, 255));
        authorLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        mainPanel.add(authorLabel);
        
        authorInput = new JTextField();
        mainPanel.add(authorInput);
        authorInput.setColumns(10);
        
        JLabel bookLabel = new JLabel("Book:");
        bookLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        bookLabel.setForeground(new Color(254, 255, 255));
        mainPanel.add(bookLabel);
        
        bookInput = new JTextField();
        mainPanel.add(bookInput);
        bookInput.setColumns(10);
        
        JLabel genreLabel = new JLabel("Genre");
        genreLabel.setForeground(new Color(254, 255, 255));
        genreLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        mainPanel.add(genreLabel);
        
        genreInput = new JTextField();
        mainPanel.add(genreInput);
        genreInput.setColumns(10);
        
        JLabel noteLabel = new JLabel("Note:");
        noteLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        noteLabel.setForeground(new Color(254, 255, 255));
        mainPanel.add(noteLabel);
        
        noteInput = new JTextField();
        mainPanel.add(noteInput);
        noteInput.setColumns(10);
        
        JButton addButton = new JButton("Add!");
        addButton.setForeground(new Color(0, 0, 0));
        addButton.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        mainPanel.add(addButton);
        
        // Save data when clicking save
        addButton.addActionListener(clickEvent -> {
        	String authorValue = authorInput.getText();
        	String bookValue = bookInput.getText();
        	String genreValue = genreInput.getText();
        	
        	if(authorValue.equals("") || bookValue.equals("") || genreValue.equals("")) {
        		JOptionPane.showMessageDialog(panel, "Please fill in fields!");
        	} else {
        		try {
					globalBooksDBObject.addBook(authorValue, bookValue, genreValue, bookValue, genreValue);
					JOptionPane.showMessageDialog(panel, bookValue + " has been added.");
				} catch (Exception error) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(panel, "An error has occured while adding the book!");
					error.printStackTrace();
				}
        	}
        });
       
    }
   
}
