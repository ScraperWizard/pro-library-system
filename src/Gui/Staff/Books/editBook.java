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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class editBook {
    public JFrame jFrame;
    private JTextField bookInput;
    private JTextField authorInput;
    private JTextField borrowedByInput;
    
    public editBook() {
        jFrame = new JFrame();
        BooksDB globalBooksObject = new BooksDB();

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setBounds(500, 500, 500, 400);
        jFrame.setTitle("Staff - Edit book");
        
        JPanel panel = new JPanel();
        jFrame.setContentPane(panel);
        panel.setBackground(new Color(57, 130, 146));
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Edit book information");
        lblNewLabel.setBounds(63, 0, 367, 35);
        panel.add(lblNewLabel);
        lblNewLabel.setForeground(new Color(254, 255, 255));
        lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(157, 24, 300, 319);
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
        
        JLabel borrowedByLabel = new JLabel("Borrowed By:");
        borrowedByLabel.setForeground(new Color(254, 255, 255));
        borrowedByLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        mainPanel.add(borrowedByLabel);
        
        borrowedByInput = new JTextField();
        borrowedByInput.setEditable(false);
        borrowedByInput.setEnabled(false);
        mainPanel.add(borrowedByInput);
        borrowedByInput.setColumns(10);
        
        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        statusLabel.setForeground(new Color(254, 255, 255));
        mainPanel.add(statusLabel);
        
        JComboBox statusComboBox = new JComboBox();
        statusComboBox.setMaximumRowCount(3);
        statusComboBox.setModel(new DefaultComboBoxModel(new String[] {"BORROWED", "SOLD", "AVAILABLE"}));
        mainPanel.add(statusComboBox);
        
        JButton saveButton = new JButton("Save!");
        saveButton.setForeground(new Color(0, 0, 0));
        saveButton.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        mainPanel.add(saveButton);
        
        JList<String> booksList = new JList();
        booksList.setForeground(new Color(254, 255, 255));
        booksList.setBorder(new LineBorder(new Color(44, 100, 144)));
        booksList.setBounds(31, 35, 125, 302);
        panel.add(booksList);
        booksList.setVisibleRowCount(3);
        booksList.setBackground(new Color(57, 130, 146));
        booksList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Package all data
        setModel(globalBooksObject, booksList);
        
        // Change inputs whenever selecting a username
        booksList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt) {
            	String selectedValue = (String) booksList.getSelectedValue();
                if (!evt.getValueIsAdjusting() && selectedValue != null) {
                    BooksDB selectedBook = globalBooksObject.getBook(selectedValue);
                    
                    authorInput.setText(selectedBook.author);
                    borrowedByInput.setText(selectedBook.borrowedBy);
                    bookInput.setText(selectedBook.book);
                    statusComboBox.setSelectedItem(selectedBook.status);
                }
            }
        });
        
        // Save data when clicking save
        saveButton.addActionListener(clickEvent -> {
        	String selectedValue = (String) booksList.getSelectedValue();
        	String authorValue = authorInput.getText();
        	String bookValue = bookInput.getText();
        	String statusValue = (String) statusComboBox.getSelectedItem();
        	
        	if(authorValue.equals("") || authorValue.equals("") || statusValue.equals("")) {
        		JOptionPane.showMessageDialog(panel, "Please fill in fields!");
        	} else {
        		globalBooksObject.editBookInformation(selectedValue, "author", authorValue);
        		globalBooksObject.editBookInformation(selectedValue, "book", bookValue);
        		globalBooksObject.editBookInformation(selectedValue, "status", statusValue);
        		
        		JOptionPane.showMessageDialog(panel, selectedValue + " has been updated.");
        		setModel(globalBooksObject, booksList);
        	}
        });
       
    }
    
    public void setModel(BooksDB globalBooksObject, JList<String> booksList) {
        // Package all data
		String[] values;
		BooksDB allBooks[] = globalBooksObject.getAllBooks();
		values = new String[allBooks.length];
		
		for(int i = 0; i < allBooks.length; i++)
			values[i] = allBooks[i].book;
        
		 booksList.setModel(new AbstractListModel<String>() {
            public int getSize() {
                return values.length;
            }
            public String getElementAt(int index) {
                return values[index];
            }
        });
    }
}
