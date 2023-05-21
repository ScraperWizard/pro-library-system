package Gui.Customers.Library;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Database.BooksDB.BooksDB;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class BuyABook extends JFrame {

    private JPanel contentPane;
    private JTextField bookInput;
    private JTextField authorInput;
    private JTextField noteInput;
    private JTable table;



    public BuyABook() {
        JFrame jFrame = new JFrame();
        BooksDB books = new BooksDB();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (screenSize.width) / 2;
        int screenHeight = (screenSize.height) / 2;

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setBounds(100, 100, 835, 568);
        jFrame.setTitle("Customer - Borrow a book");

        JPanel panel = new JPanel();
        jFrame.setContentPane(panel);
        panel.setBackground(new Color(57, 130, 146));
        panel.setLayout(null);

        JLabel addBooksLabel = new JLabel("Borrow books");
        addBooksLabel.setBounds(63, 0, 367, 35);
        panel.add(addBooksLabel);
        addBooksLabel.setForeground(new Color(254, 255, 255));
        addBooksLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        addBooksLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(63, 110, 290, 187);
        mainPanel.setBackground(new Color(57, 130, 146));
        panel.add(mainPanel);
        mainPanel.setLayout(new GridLayout(0, 1, 0, 0));

        JLabel bookLabel = new JLabel("Choose a book : ");
        bookLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        bookLabel.setForeground(new Color(254, 255, 255));
        mainPanel.add(bookLabel);

        bookInput = new JTextField();
        mainPanel.add(bookInput);
        bookInput.setColumns(10);

        JLabel noteLabel = new JLabel("Note:");
        noteLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        noteLabel.setForeground(new Color(254, 255, 255));
        mainPanel.add(noteLabel);

        noteInput = new JTextField();
        mainPanel.add(noteInput);
        noteInput.setColumns(10);

        JButton borrowButton = new JButton("Borrow!");
        borrowButton.setForeground(new Color(0, 0, 0));
        borrowButton.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        mainPanel.add(borrowButton);



        JList<String> booksList = new JList();
        booksList.setForeground(new Color(254, 255, 255));
        booksList.setBorder(new LineBorder(new Color(44, 100, 144)));
        booksList.setBounds(439, 82, 300, 273);
        panel.add(booksList);
        booksList.setVisibleRowCount(3);
        booksList.setBackground(new Color(57, 130, 146));

        buyBooksList(books , booksList);

        borrowButton.addActionListener(clickEvent -> {
            List<String> selectedBooks = booksList.getSelectedValuesList();

            if(selectedBooks.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Dear customer, please select a book to buy");
            } else {
                try {
                    for(String book : selectedBooks) {
                        System.out.println("Trying to borrow -> " + book);
                        addBoughtBook(book);
                    }
                    JOptionPane.showMessageDialog(panel, "Book(s) have been taken!");
                } catch (Exception error) {
                    JOptionPane.showMessageDialog(panel, "An error has occured while buying the book!");
                    error.printStackTrace();
                }
            }
        });



    }
    public void buyBooksList(BooksDB globalBooksDBObject, JList<String> booksList) {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (screenSize.width) / 2;
        int screenHeight = (screenSize.height) / 2;
        BooksDB books = new BooksDB();

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
        String[] columnNames = {"Author", "Book", "Genre", "Borrowed By", "Borrow date", "Status"};


        Object[][] data = getTableBooks(books);
        int heightOfTable = (data.length * 21) > 240 ? 240 : data.length * 21;

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(30, 92, 710, heightOfTable);


        JTable table = new JTable(data, columnNames);
        table.setShowGrid(false);
        table.setEnabled(true);
        table.setBackground(new Color(88, 127, 143));
        table.setBounds(screenWidth-200, screenHeight-100, screenWidth, heightOfTable);
        scrollPane_1.setViewportView(table);
    }
    public void addBoughtBook(String book) {



        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[]{book});
    }
    public Object[][] getTableBooks(BooksDB globalBooksObject) {
        BooksDB[] allBooks = globalBooksObject.getAllBooks();

        Object[][] data = new Object[allBooks.length][6];

        for(int i = 0; i < allBooks.length; i++) {
            data[i][0] = allBooks[i].author;
            data[i][1] = allBooks[i].book;
            data[i][2] = allBooks[i].genre;
            data[i][3] = allBooks[i].borrowedBy;
            data[i][4] = allBooks[i].borrowDate;
            data[i][5] = allBooks[i].status;
        }

        return data;
    }

}
