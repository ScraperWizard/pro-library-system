package Gui.Customers.Books;

import Database.Staff.Staff;
import Database.BooksDB.BooksDB;
import Gui.Staff.Books.addBooks;
import Gui.Staff.Books.editBook;
import Gui.Staff.Books.removeBook;
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

public class CustomersList extends JFrame {

    public JPanel contentPane;
    private JTable table;
    /**
     * Launch the application.
     */


    /**
     * Create the frame.
     */
    public CustomersList(JFrame mainFrame) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);

        table = new JTable();
        String[] columnNames = {"Author", "Book", "Genre", "Borrowed By", "Borrow date", "Status", "Action","Borrow"};
        DefaultTableModel modelTable = new DefaultTableModel(columnNames, 0);
        table.setModel(modelTable);

    }
    public void addChosenBooks(String author , String book , String genre , String borrowBy , String borrowDate , String bookStatus , String Action , String borrowOption) {

        DefaultTableModel modelTable = (DefaultTableModel) table.getModel();
        modelTable.addRow(new String [] {author , book , genre , borrowBy , borrowDate , bookStatus , Action , borrowOption});
    }

}
