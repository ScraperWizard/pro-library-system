package Gui.Customers.MyBooks;

import Database.BooksDB.BooksDB;
import Database.CustomerTickets.CustomerTickets;
import Database.Customers.Customers;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ExtendDate extends JFrame {

    private JPanel contentPane;
    private JTextField extendInput;
    BooksDB[] allBooks;

    public ExtendDate(String username) {
        CustomerTickets sendTicket = new CustomerTickets();
        Customers customer = new Customers();
        Customers currentUser = customer.getUser(username);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (screenSize.width) / 2;
        int screenHeight = (screenSize.height) / 2;

        BooksDB books = new BooksDB();
        CustomerTickets ticket = new CustomerTickets();
        Customers globalCustomerObject = new Customers();

        BooksDB[] allBookss = books.getAllBooks();

        // Frame
        JFrame jFrame = new JFrame();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setBounds(100, 100, 450, 350);
        jFrame.setTitle("Customer - Extend the return date");

        // Parent panel
        JPanel panel = new JPanel();
        jFrame.setContentPane(panel);
        panel.setBackground(new Color(57, 130, 146));
        panel.setLayout(null);

        JList<String> booksList = new JList();
        booksList.setForeground(new Color(254, 255, 255));
        booksList.setBorder(new LineBorder(new Color(44, 100, 144)));
        booksList.setBounds(62, 11, 300, 122);
        panel.add(booksList);
        booksList.setVisibleRowCount(3);
        booksList.setBackground(new Color(57, 130, 146));

        setBooks(books, booksList);

        JButton extendbtn = new JButton("Send");
        extendbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> selectedBooks = new ArrayList<>();
                String extendInputValue = extendInput.getText();

                selectedBooks.addAll(booksList.getSelectedValuesList());

                if (!selectedBooks.isEmpty()) {
                    if (!extendInputValue.isEmpty()) {
                        if (extendInputValue.matches("\\d+")) {
                            int extraDays = Integer.parseInt(extendInputValue);
                            for (String bookName : selectedBooks) {
                                String ticketID;
                                try {
                                    ticketID = sendTicket.addTicket(currentUser.email, "Staff", "Extend due date");

                                    try {
                                        sendTicket.addMessageToTicket(ticketID, currentUser.email, "Extend date for " + extraDays + " days. " + "Book: " + bookName + " Note: " + "");
                                    } catch (Exception e1) {
                                        // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                    }
                                } catch (Exception e2) {
                                    // TODO Auto-generated catch block
                                    e2.printStackTrace();
                                }
                            }

                            JOptionPane.showMessageDialog(panel, "The request to extend the due date of selected books has been made. The return dates might be extended in the following days.");
                        } else {
                            JOptionPane.showMessageDialog(panel, "Invalid input for extending the return date. Please enter a valid number of days.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(panel, "Please choose how long the return date should be extended.");
                    }
                } else {
                    JOptionPane.showMessageDialog(panel, "Please select at least one book.");
                }
            }
        });



        extendbtn.setBounds(130, 228, 156, 23);
        panel.add(extendbtn);

        JLabel extend = new JLabel("Please enter how many extra days you need ");
        extend.setBounds(30, 145, 366, 14);
        panel.add(extend);
        extend.setForeground(new Color(254, 255, 255));
        extend.setFont(new Font("Dialog", Font.BOLD, 15));
        extend.setHorizontalAlignment(SwingConstants.CENTER);

        extendInput = new JTextField();
        extendInput.setBounds(90, 170, 213, 20);
        panel.add(extendInput);
        extendInput.setColumns(10);
    }

    public void setBooks(BooksDB globalBooksDBObject, JList<String> booksList) {

        // this.allBooks = allBooks;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (screenSize.width) / 2;
        int screenHeight = (screenSize.height) / 2;
        BooksDB books = new BooksDB();

        String[] values;
        // Get all books from the database
        BooksDB[] allBooks = globalBooksDBObject.getAllBooks();
        // this.allBooks = allBooks;

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
