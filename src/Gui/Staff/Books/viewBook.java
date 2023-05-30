package Gui.Staff.Books;

import Database.BooksDB.BooksDB;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

public class viewBook {
    public JFrame jFrame;
    public viewBook() {
        jFrame = new JFrame();
        BooksDB globalBooksDBObject = new BooksDB();

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setBounds(500, 500, 500, 400);
        jFrame.setTitle("Staff - View books");

        JPanel panel = new JPanel();
        jFrame.setContentPane(panel);
        panel.setBackground(new Color(57, 130, 146));
        panel.setLayout(null);

        JLabel viewBookLabel = new JLabel("View books");
        viewBookLabel.setBounds(63, 0, 367, 35);
        panel.add(viewBookLabel);
        viewBookLabel.setForeground(new Color(254, 255, 255));
        viewBookLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        viewBookLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JList<String> booksList = new JList();
        booksList.setForeground(new Color(254, 255, 255));
        booksList.setBorder(new LineBorder(new Color(44, 100, 144)));
        booksList.setBounds(103, 37, 300, 273);
        panel.add(booksList);
        booksList.setVisibleRowCount(3);
        booksList.setBackground(new Color(57, 130, 146));

        // Package all data
        setModel(globalBooksDBObject, booksList);

        JButton viewButton = new JButton("View!");
        viewButton.setBounds(103, 316, 300, 50);
        panel.add(viewButton);
        viewButton.setForeground(new Color(0, 0, 0));
        viewButton.setFont(new Font("Lucida Grande", Font.PLAIN, 14));

        // Save data when clicking save
        viewButton.addActionListener(clickEvent -> {
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
