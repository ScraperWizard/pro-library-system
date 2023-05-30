package Gui.Customers.MyBooks;

import Database.BooksDB.BooksDB;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class MyBooks {
    public JPanel myBooksPane;
    public JTable table;

    public MyBooks(JFrame mainFrame) {
        BooksDB globalBooksObject = new BooksDB();
        myBooksPane = new JPanel();
        myBooksPane.setBackground(new Color(76, 128, 144));
        myBooksPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        myBooksPane.setLayout(null);

        // Get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (screenSize.width) / 2;
        int screenHeight = (screenSize.height) / 2;
        myBooksPane.setBounds(270, 0, screenSize.width - 250, screenSize.height);

        JLabel headerLabel = new JLabel("Customers - My Books");
        headerLabel.setForeground(new Color(254, 255, 255));
        headerLabel.setFont(new Font("Dialog", Font.BOLD, 24));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setVerticalAlignment(SwingConstants.CENTER);
        headerLabel.setBounds(-120, 0, screenSize.width - 250, 39);
        myBooksPane.add(headerLabel);

        // Options panel and all buttons inside of them
        JPanel optionsPanel = new JPanel();
        optionsPanel.setBackground(new Color(76, 128, 144));
        optionsPanel.setBounds(750, 90, 229, 103);
        optionsPanel.setVisible(true);
        myBooksPane.add(optionsPanel);
        optionsPanel.setLayout(new GridLayout(0, 1, 0, 0));

        //  myBooks button
        JButton returnBook = new JButton("Return a book");
        returnBook.setForeground(new Color(32, 99, 143));
        returnBook.setFont(new Font("Dialog", Font.BOLD, 14));
        returnBook.setBackground(Color.WHITE);
        returnBook.setBounds(191, 438, 229, 51);
        optionsPanel.add(returnBook);

        // borrowed books button to show the borrowed books for the user
        JButton extendDate = new JButton("Extend due date");
        extendDate.setForeground(new Color(32, 99, 143));
        extendDate.setFont(new Font("Dialog", Font.BOLD, 14));
        extendDate.setBackground(Color.WHITE);
        optionsPanel.add(extendDate);

        // Table columns
        String[] columnNames = {"Author", "Book", "Genre", "status","Return Date","Days left"};

        // Get table data
        Object[][] data = getTableBooks(globalBooksObject);
        int heightOfTable = (data.length * 21) > 240 ? 240 : data.length * 21;

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 92, 710, heightOfTable);
        myBooksPane.add(scrollPane);

        JButton refreshbtn = new JButton("Refresh");
        refreshbtn.setForeground(new Color(32, 99, 143));
        refreshbtn.setFont(new Font("Dialog", Font.BOLD, 14));
        refreshbtn.setBackground(Color.WHITE);
        optionsPanel.add(refreshbtn);



        table = new JTable(data, columnNames);
        table.setShowGrid(false);
        table.setEnabled(true);
        table.setBackground(new Color(88, 127, 143));
        table.setBounds(screenWidth - 200, screenHeight - 100, screenWidth, heightOfTable);
        scrollPane.setViewportView(table);

        // doesn't work
        refreshbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshTable(table, myBooksPane, globalBooksObject, scrollPane);
            }
        });

        // Button click handlers
        returnBook.addActionListener(clickEvent -> {
            new ReturnBooks();
        });

        extendDate.addActionListener(clickEvent -> {
            new ExtendDate();
        });

        setStatusColor();
        setDaysLeftColor();

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
        Object[][] data = new Object[count][6];

        // Iterate over the books and add the "Borrowed" and "Sold" books to the data array
        int index = 0;
        for (BooksDB book : allBooks) {
            if (book.status.equals("BORROWED") || book.status.equals("SOLD")) {
                data[index][0] = book.author;
                data[index][1] = book.book;
                data[index][2] = book.genre;
                data[index][3] = book.status;
                data[index][4] = book.borrowDate;

                // Calculate days left
                long daysLeft = 0;
                if(!book.borrowDate.equals("")) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate borrowDate = LocalDate.parse(book.borrowDate, formatter);
                    LocalDate currentDate = LocalDate.now();
                    daysLeft = ChronoUnit.DAYS.between(currentDate, borrowDate.plusDays(30)); // Assuming a 30-day borrowing period
                }
                data[index][5] = String.valueOf(daysLeft);
                index++;
            }
        }
        return data;
    }

    public void refreshTable(JTable table, JPanel panel, BooksDB globalBooksObject, JScrollPane scrollPanel) {
        // get the updated data for the table
        Object[][] data = getTableBooks(globalBooksObject);

        // Adjust length of table
        int heightOfTable = (data.length * 21) > 240 ? 240 : data.length * 21;
        scrollPanel.setBounds(320, 93, 710, heightOfTable);

        // create a new table model with the updated data
        DefaultTableModel newTableModel = new DefaultTableModel(data, new Object[]{"Author", "Book", "Genre", "status","Return Date","Days left"});
        table.setModel(newTableModel);

        // Set the cell renderer for the "Status" column
        setStatusColor();

        // revalidate and repaint the table and the panel to refresh the view
        table.revalidate();
        table.repaint();
        panel.revalidate();
        panel.repaint();
    }
    public void setStatusColor() {
        // Get the TableColumn object for the "Status" column
        TableColumn statusColumn = table.getColumnModel().getColumn(3);

        // Define a custom cell renderer for the "Status" column
        statusColumn.setCellRenderer(new DefaultTableCellRenderer() {
            public void setValue(Object value) {
                super.setValue(value);
                if (value instanceof String) {
                    String stringValue = (String) value;
                    int daysLeft = Integer.parseInt(stringValue);
                    if (daysLeft == 1) {
                        setBackground(Color.RED);
                        setForeground(Color.WHITE);
                    } else {
                        setBackground(Color.WHITE);
                        setForeground(Color.BLACK);
                    }
                }
            }
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                // Create a new label to hold the cell value
                JLabel cellLabel = new JLabel();

                // Get the value of the "Status" column for this cell
                String status = (String) value;

                // Set the background and foreground color of the cell based on the status value
                if (status.equals("AVAILABLE")) {
                    cellLabel.setForeground(Color.GREEN);
                } else if (status.equals("SOLD")) {
                    cellLabel.setForeground(Color.RED);
                } else if(status.equals("BORROWED")) {
                    cellLabel.setForeground(Color.YELLOW);
                }

                // Set the text of the label to the value of the "Status" column for this cell
                cellLabel.setText(status);

                // Return the label as the cell renderer component
                return cellLabel;
            }

        });

    }
    public void setDaysLeftColor() {
        // Get the TableColumn object for the "Days left" column
        TableColumn daysLeftColumn = table.getColumnModel().getColumn(5);

        // Define a custom cell renderer for the "Days left" column
        daysLeftColumn.setCellRenderer(new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                // Create a new label to hold the cell value
                JLabel cellLabel = new JLabel();

                // Get the value of the "Days left" column for this cell
                String stringValue = (String) value;
                int daysLeft = Integer.parseInt(stringValue);

                // Set the background and foreground color of the cell based on the daysLeft value
                if (daysLeft == 1) {
                    cellLabel.setForeground(Color.RED);
                } else {
                    cellLabel.setForeground(Color.BLACK);
                }

                // Set the text of the label to the value of the "Days left" column for this cell
                cellLabel.setText(stringValue);

                // Return the label as the cell renderer component
                return cellLabel;
            }
        });
    }

}

