package Gui.Staff.Books;

import Database.Staff.Staff;
import Database.BooksDB.BooksDB;
import Gui.loginSelector.loginSelector;
import Database.Customers.Customers;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Books {
    public JPanel booksPane;
    private JTable table_1;
    private JTable table;

    public Books(JFrame mainFrame) {
        Staff globalStaffObject = new Staff();
        BooksDB globalBooksObject = new BooksDB();
        booksPane = new JPanel();
        booksPane.setBackground(new Color(76, 128, 144));
        booksPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        booksPane.setLayout(null);

        // Get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (screenSize.width) / 2;
        int screenHeight = (screenSize.height) / 2;
        booksPane.setBounds(270, 0, screenSize.width, screenSize.height);
        
        // Table
        String[] columnNames = {"Author", "Book", "Genre", "Borrowed By", "Borrow date", "Status", "Action"};

        // Data
        Object[][] data = getTable(globalBooksObject);
        int heightOfTable = (data.length * 21) > 240 ? 240 : data.length * 21;
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(320, 93, 710, heightOfTable);
        booksPane.add(scrollPane_1);
        
        table = new JTable(data, columnNames);
        table.setShowGrid(false);
        table.setEnabled(true);
        table.setBackground(new Color(88, 127, 143));
        scrollPane_1.setViewportView(table);
        
        // Call method to change color of all statuses
        setStatusColor();
        addActionButtons();
        
        JPanel optionsPanel = new JPanel();
        optionsPanel.setBackground(new Color(76, 128, 144));
        optionsPanel.setBounds(1100, 90, 229, 103);
        booksPane.add(optionsPanel);
        optionsPanel.setLayout(new GridLayout(0, 1, 0, 0));
        
        JButton addBooks = new JButton("Add book");
        addBooks.setBackground(new Color(32, 99, 143));
        addBooks.setForeground(new Color(32, 99, 143));
        optionsPanel.add(addBooks);
        addBooks.setFont(new Font("Dialog", Font.BOLD, 14));
        
        JButton removeBooksBtn = new JButton("Remove book");
        removeBooksBtn.setBackground(new Color(32, 99, 143));
        removeBooksBtn.setFont(new Font("Dialog", Font.BOLD, 14));
        removeBooksBtn.setForeground(new Color(32, 99, 143));
        optionsPanel.add(removeBooksBtn);
        
        JButton editBooksBtn = new JButton("Edit book");
        editBooksBtn.setForeground(new Color(32, 99, 143));
        editBooksBtn.setFont(new Font("Dialog", Font.BOLD, 14));
        editBooksBtn.setBackground(new Color(32, 99, 143));
        optionsPanel.add(editBooksBtn);
        
        JButton refreshButton = new JButton("Refresh");
        refreshButton.setForeground(new Color(32, 99, 143));
        refreshButton.setFont(new Font("Dialog", Font.BOLD, 14));
        refreshButton.setBackground(new Color(32, 99, 143));
        optionsPanel.add(refreshButton);
        
        // When you click edit user info button
        addBooks.addActionListener(clickEvent -> {
        	addBooks addBooksFrame = new addBooks();
        });
        
        removeBooksBtn.addActionListener(clickEvent -> {
        	removeBook removeBooksFrame = new removeBook();
        });
        
        editBooksBtn.addActionListener(clickEvent -> {
        	editBook editBooksFrame = new editBook();
        });
        
        refreshButton.addActionListener(clickEvent -> {
        	refreshTable(table, booksPane, globalBooksObject, scrollPane_1);
            System.out.print("Refreshed");
        });
    }
    
    public ImageIcon changeIcon(int width, int height, String path) {
        ImageIcon icon = new ImageIcon(Customers.class.getResource(path));
        Image image = icon.getImage();
        int scaledWidth = width; // The new width you want
        int scaledHeight = height; // The new height you want
        Image scaledImage = image.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        return scaledIcon;
    }
    
    public Object[][] getTable(BooksDB globalBooksObject) {
    	BooksDB[] allBooks = globalBooksObject.getAllBooks();

        Object[][] data = new Object[allBooks.length][7];

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
    
    public void refreshTable(JTable table, JPanel panel, BooksDB globalBooksObject, JScrollPane scrollPanel) {
        // get the updated data for the table
        Object[][] data = getTable(globalBooksObject);
        
        // Adjust length of table
        int heightOfTable = (data.length * 21) > 240 ? 240 : data.length * 21;
        scrollPanel.setBounds(320, 93, 710, heightOfTable);
        
        // create a new table model with the updated data
        DefaultTableModel newTableModel = new DefaultTableModel(data, new Object[] {"Author", "Book", "Genre", "Borrowed By", "Borrow date", "Status"});
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

    public void addActionButtons() {
        // Get the TableColumn object for the "Status" column
        TableColumn actionColumn = table.getColumnModel().getColumn(6);

        // Define a custom cell renderer for the "Action" column
        actionColumn.setCellRenderer(new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                // Create a new button to hold the cell value
                JButton cellButton = new JButton("Action");

                // Set the background and foreground color of the cell
                cellButton.setBackground(Color.WHITE);
                cellButton.setForeground(Color.BLACK);

                cellButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Display a message when the button is clicked
                        JOptionPane.showMessageDialog(null, "Button clicked for row " + row);
                    }
                });

                // Return the button as the cell renderer component
                return cellButton;
            }
        });
    }

}
