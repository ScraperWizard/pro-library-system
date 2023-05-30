package Gui.Customers.Library;

import Database.BooksDB.BooksDB;
import Database.Staff.Staff;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;

public class Library {
    public JPanel libraryPanel;
    private JTable table;

    public Library(JFrame mainFrame, String username) {
        Staff globalStaffObject = new Staff();
        BooksDB globalBooksObject = new BooksDB();
        libraryPanel = new JPanel();
        libraryPanel.setBackground(new Color(76, 128, 144));
        libraryPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        libraryPanel.setLayout(null);

        // Get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (screenSize.width) / 2;
        int screenHeight = (screenSize.height) / 2;
        libraryPanel.setBounds(270, 0, screenSize.width - 250, screenSize.height);

        JLabel headerLabel = new JLabel("Customers - Library");
        headerLabel.setForeground(new Color(254, 255, 255));
        headerLabel.setFont(new Font("Dialog", Font.BOLD, 24));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setVerticalAlignment(SwingConstants.CENTER);
        headerLabel.setBounds(-120, 0, screenSize.width - 250, 39);
        libraryPanel.add(headerLabel);

        // Table columns
        String[] columnNames = {"Author", "Book", "Genre", "Borrowed By", "Borrow date", "Status"};

        // Get table data
        Object[][] data = getTableBooks(globalBooksObject);
        int heightOfTable = (data.length * 21) > 240 ? 240 : data.length * 21;

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(188, 93, 710, heightOfTable);
        libraryPanel.add(scrollPane);

        table = new JTable(data, columnNames);
        table.setShowGrid(false);
        table.setEnabled(true);
        table.setBackground(new Color(88, 127, 143));
        table.setBounds(screenWidth - 200, screenHeight - 100, screenWidth, heightOfTable);
        scrollPane.setViewportView(table);

        // <---->

        // Options panel and all buttons inside of them
        JPanel optionsPanel = new JPanel();
        optionsPanel.setBackground(new Color(76, 128, 144));
        optionsPanel.setBounds(908, 91, 229, 103);
        optionsPanel.setVisible(true);
        libraryPanel.add(optionsPanel);
        optionsPanel.setLayout(new GridLayout(0, 1, 0, 0));

        // Get a book button
        JButton getBook = new JButton("Get a book");
        getBook.setForeground(new Color(32, 99, 143));
        getBook.setFont(new Font("Dialog", Font.BOLD, 14));
        getBook.setBackground(Color.WHITE);
        getBook.setBounds(191, 438, 229, 51);
        optionsPanel.add(getBook);

        // Request a book button
        JButton requestButton = new JButton("Request a book");
        requestButton.setForeground(new Color(32, 99, 143));
        requestButton.setFont(new Font("Dialog", Font.BOLD, 14));
        requestButton.setBackground(Color.WHITE);
        optionsPanel.add(requestButton);

        // Refresh table button
        JButton refreshButton = new JButton("Refresh");
        refreshButton.setForeground(new Color(32, 99, 143));
        refreshButton.setFont(new Font("Dialog", Font.BOLD, 14));
        refreshButton.setBackground(Color.WHITE);
        optionsPanel.add(refreshButton);

        // Button click handlers
        getBook.addActionListener(clickEvent -> {
            // Get book button clicked
        });

        requestButton.addActionListener(clickEvent -> {
            new RequestBook(username);
        });

        refreshButton.addActionListener(clickEvent -> {
            refreshTable(table, libraryPanel, globalBooksObject, scrollPane);
        });

        getBook.addActionListener(clickEvent -> {
            new GetABook(username);
        });

        // Apply colors
        setStatusColor();
    }

    public Object[][] getTableBooks(BooksDB globalBooksObject) {
        BooksDB[] allBooks = globalBooksObject.getAllBooks();

        Object[][] data = new Object[allBooks.length][6];

        for (int i = 0; i < allBooks.length; i++) {
            data[i][0] = allBooks[i].author;
            data[i][1] = allBooks[i].book;
            data[i][2] = allBooks[i].genre;
            data[i][3] = allBooks[i].borrowedBy;
            data[i][4] = allBooks[i].borrowDate;
            data[i][5] = allBooks[i].status;
        }

        return data;
    }

    public void refreshTable(JTable table, JPanel panel, BooksDB globalBooksObject, JScrollPane scrollPanel) {
        // get the updated data for the table
        Object[][] data = getTableBooks(globalBooksObject);

        // Adjust length of table
        int heightOfTable = (data.length * 21) > 240 ? 240 : data.length * 21;
        scrollPanel.setBounds(188, 93, 710, heightOfTable);

        // create a new table model with the updated data
        DefaultTableModel newTableModel = new DefaultTableModel(data, new Object[]{"Author", "Book", "Genre", "Borrowed By", "Borrow date", "Status"});
        table.setModel(newTableModel);

        // revalidate and repaint the table and the panel to refresh the view
        table.revalidate();
        table.repaint();
        panel.revalidate();
        panel.repaint();

        // Call method to change color of all statuses
        setStatusColor();
    }

    public void setStatusColor() {
        // Get the TableColumn object for the "Status" column
        TableColumn statusColumn = table.getColumnModel().getColumn(5);

        // Define a custom cell renderer for the "Status" column
        statusColumn.setCellRenderer(new DefaultTableCellRenderer() {
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
}
