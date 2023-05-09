package Gui.Staff.Books;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;

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

public class removeBook {
    public JFrame jFrame;
    public removeBook() {
        jFrame = new JFrame();
        BooksDB globalBooksDBObject = new BooksDB();

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setBounds(500, 500, 500, 400);
        jFrame.setTitle("Staff - Remove books");
        
        JPanel panel = new JPanel();
        jFrame.setContentPane(panel);
        panel.setBackground(new Color(57, 130, 146));
        panel.setLayout(null);
        
        JLabel removeBooksLabel = new JLabel("Remove books");
        removeBooksLabel.setBounds(63, 0, 367, 35);
        panel.add(removeBooksLabel);
        removeBooksLabel.setForeground(new Color(254, 255, 255));
        removeBooksLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        removeBooksLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JList<String> booksList = new JList();
        booksList.setForeground(new Color(254, 255, 255));
        booksList.setBorder(new LineBorder(new Color(44, 100, 144)));
        booksList.setBounds(103, 37, 300, 273);
        panel.add(booksList);
        booksList.setVisibleRowCount(3);
        booksList.setBackground(new Color(57, 130, 146));
        
        // Package all data
        setModel(globalBooksDBObject, booksList);
        
        JButton removeButton = new JButton("Remove!");
        removeButton.setBounds(103, 316, 300, 50);
        panel.add(removeButton);
        removeButton.setForeground(new Color(0, 0, 0));
        removeButton.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        
        // Save data when clicking save
        removeButton.addActionListener(clickEvent -> {
            List<String> selectedBooks = booksList.getSelectedValuesList();

            if(selectedBooks.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Please select books to remove!");
            } else {
                try {
                    for(String book : selectedBooks) {
                        System.out.println("Trying to remove -> " + book);
                        
                        globalBooksDBObject.removeBook(book);
                    }
                    JOptionPane.showMessageDialog(panel, "Book(s) have been removed!");
                } catch (Exception error) {
                    JOptionPane.showMessageDialog(panel, "An error has occured while removing the book!");
                    error.printStackTrace();
                }
            }
        });

       
    }
    
    public void setModel(BooksDB globalBooksDBObject, JList<String> booksList) {
        // Package all data
		String[] values;
		BooksDB[] allBooks = globalBooksDBObject.getAllBooks();
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
