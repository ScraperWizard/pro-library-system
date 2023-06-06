package Gui.Customers.MyBooks;

import Database.BooksDB.BooksDB;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ReturnBooks extends JFrame {
    private JPanel contentPane;

    public ReturnBooks(String username) {
        // Create a new instance of BooksDB
        BooksDB booksDB = new BooksDB();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        // Frame
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(100, 100, 450, 350);
        frame.setTitle("Customer - Return book(s)");

        // Parent panel
        JPanel panel = new JPanel();
        frame.setContentPane(panel);
        panel.setBackground(new Color(57, 130, 146));
        panel.setLayout(null);

        JList<String> booksList = new JList<>();
        booksList.setForeground(new Color(254, 255, 255));
        booksList.setBorder(new LineBorder(new Color(44, 100, 144)));
        booksList.setBounds(62, 11, 300, 273);
        panel.add(booksList);
        booksList.setVisibleRowCount(3);
        booksList.setBackground(new Color(57, 130, 146));

        // Set the books in the JList
        setBooks(booksDB, booksList, username);

        JButton returnBtn = new JButton("Return book(s)");
        returnBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<String> selectedBooks = booksList.getSelectedValuesList();
                if (!selectedBooks.isEmpty()) {
                    // Iterate through the selected books and update their information
                    for (String selectedBook : selectedBooks) {
                        booksDB.editBookInformation(selectedBook, "status", "AVAILABLE");
                        booksDB.editBookInformation(selectedBook, "borrowedBy", "");
                        booksDB.editBookInformation(selectedBook, "borrowDate", "");
                    }

                    // Refresh the book list after returning books
                    setBooks(booksDB, booksList, username);

                    // Show a success message
                    String successMessage = selectedBooks.size() > 1 ? "Books" : "Book";
                    JOptionPane.showMessageDialog(panel, successMessage + " returned successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(panel, "Please select at least one book to return", "Fail", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        returnBtn.setBounds(147, 288, 156, 23);
        panel.add(returnBtn);
    }

    public void setBooks(BooksDB globalBooksDB, JList<String> booksList, String username) {
        // Get all books from the database
        BooksDB[] allBooks = globalBooksDB.getAllBooks();

        // Filter the books to show only the "BORROWED" books borrowed by the given username
        List<String> filteredBooks = new ArrayList<>();
        for (BooksDB book : allBooks) {
            if (book.status.equals("BORROWED") && book.borrowedBy.equals(username)) {
                filteredBooks.add(book.book);
            }
        }

        // Set the filtered books as the data for the JList
        booksList.setModel(new AbstractListModel<String>() {
            public int getSize() {
                return filteredBooks.size();
            }

            public String getElementAt(int index) {
                return filteredBooks.get(index);
            }
        });

        // Refresh the JList
        booksList.repaint();
        booksList.revalidate();
    }
}
