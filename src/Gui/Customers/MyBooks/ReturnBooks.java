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

    public ReturnBooks() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (screenSize.width) / 2;
        int screenHeight = (screenSize.height) / 2;

        BooksDB books = new BooksDB();

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

        JList<String> booksList = new JList();
        booksList.setForeground(new Color(254, 255, 255));
        booksList.setBorder(new LineBorder(new Color(44, 100, 144)));
        booksList.setBounds(62, 11, 300, 273);
        panel.add(booksList);
        booksList.setVisibleRowCount(3);
        booksList.setBackground(new Color(57, 130, 146));

        setBooks(books, booksList);

        JButton returnbtn = new JButton("Return the book");
        returnbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedBook = (String) booksList.getSelectedValue();
                if (selectedBook != null) {
                    books.editBookInformation(selectedBook, "status", "AVAILABLE");
                    books.editBookInformation(selectedBook, "borrowedBy", "");
                    books.editBookInformation(selectedBook, "borrowDate", "");

                    setBooks(books, booksList); // here we refresh the table after pressing the button
                }

            }
        });
        returnbtn.setBounds(147, 288, 156, 23);
        panel.add(returnbtn);
    }

    public void setBooks(BooksDB globalBooksDBObject, JList<String> booksList) {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (screenSize.width) / 2;
        int screenHeight = (screenSize.height) / 2;
        BooksDB books = new BooksDB();

        String[] values;
        // Get all books from the database
        BooksDB[] allBooks = globalBooksDBObject.getAllBooks();

        // Filter the books to show only the sold and borrowed books
        List<String> filteredBooks = new ArrayList<>();
        for (BooksDB book : allBooks) {
            if (book.status.equals("BORROWED")) {
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

        String[] columnNames = { "Author", "Book", "Genre", "Status" };

        // Data
        Object[][] data = getTableBooks(books);
        int heightOfTable = (data.length * 21) > 240 ? 240 : data.length * 21;

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(30, 92, 710, heightOfTable);
        // jFrame.add(scrollPane_1);

        JTable table = new JTable(data, columnNames);
        table.setShowGrid(false);
        table.setEnabled(true);
        table.setBackground(new Color(88, 127, 143));
        table.setBounds(screenWidth - 200, screenHeight - 100, screenWidth, heightOfTable);
        scrollPane_1.setViewportView(table);
    }

    public Object[][] getTableBooks(BooksDB globalBooksObject) {
        BooksDB[] allBooks = globalBooksObject.getAllBooks();

        // Count the number of "Borrowed" and "Sold" books
        int count = 0;
        for (BooksDB book : allBooks) {
            if (book.status.equals("BORROWED") || book.status.equals("SOLD")) {
                count++;
            }
        }

        // Create a new array to hold the filtered books
        Object[][] data = new Object[count][4];

        // Iterate over the books and add the "Borrowed" and "Sold" books to the data
        // array
        int index = 0;
        for (BooksDB book : allBooks) {
            if (book.status.equals("BORROWED") || book.status.equals("SOLD")) {
                data[index][0] = book.author;
                data[index][1] = book.book;
                data[index][2] = book.genre;
                data[index][3] = book.status;
                index++;
            }
        }

        return data;
    }
}
