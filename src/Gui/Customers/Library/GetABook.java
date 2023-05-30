package Gui.Customers.Library;

import Database.BooksDB.BooksDB;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class GetABook {
    public JFrame jFrame;
    private JTextField bookInput;
    private JTextField authorInput;

    public GetABook(String username) {
        jFrame = new JFrame();
        BooksDB globalBooksObject = new BooksDB();

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setBounds(500, 500, 500, 400);
        jFrame.setTitle("Customer - Get a book");

        JPanel panel = new JPanel();
        jFrame.setContentPane(panel);
        panel.setBackground(new Color(57, 130, 146));
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Get a book!");
        lblNewLabel.setBounds(63, 0, 367, 35);
        panel.add(lblNewLabel);
        lblNewLabel.setForeground(new Color(254, 255, 255));
        lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(157, 24, 300, 319);
        mainPanel.setBackground(new Color(57, 130, 146));
        panel.add(mainPanel);
        mainPanel.setLayout(new GridLayout(0, 1, 0, 0));

        JLabel action = new JLabel("Author:");
        action.setForeground(new Color(254, 255, 255));
        action.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        mainPanel.add(action);

        authorInput = new JTextField();
        authorInput.setEnabled(false);
        authorInput.setEditable(false);
        mainPanel.add(authorInput);
        authorInput.setColumns(10);

        JLabel bookLabel = new JLabel("Book:");
        bookLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        bookLabel.setForeground(new Color(254, 255, 255));
        mainPanel.add(bookLabel);

        bookInput = new JTextField();
        bookInput.setEditable(false);
        bookInput.setEnabled(false);
        mainPanel.add(bookInput);
        bookInput.setColumns(10);

        JLabel actionLabel = new JLabel("Status:");
        actionLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        actionLabel.setForeground(new Color(254, 255, 255));
        mainPanel.add(actionLabel);

        JComboBox actionComboBox = new JComboBox();
        actionComboBox.setMaximumRowCount(2);
        actionComboBox.setModel(new DefaultComboBoxModel(new String[] { "Borrow", "Buy" }));
        mainPanel.add(actionComboBox);

        JButton actionButton = new JButton("Get!");
        actionButton.setForeground(new Color(0, 0, 0));
        actionButton.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        mainPanel.add(actionButton);

        JList<String> booksList = new JList();
        booksList.setForeground(new Color(254, 255, 255));
        booksList.setBorder(new LineBorder(new Color(44, 100, 144)));
        booksList.setBounds(31, 35, 125, 302);
        panel.add(booksList);
        booksList.setVisibleRowCount(3);
        booksList.setBackground(new Color(57, 130, 146));
        booksList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Package all data
        setModel(globalBooksObject, booksList);

        // Change inputs whenever selecting a username
        booksList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt) {
                String selectedValue = (String) booksList.getSelectedValue();
                if (!evt.getValueIsAdjusting() && selectedValue != null) {
                    BooksDB selectedBook = globalBooksObject.getBook(selectedValue);

                    authorInput.setText(selectedBook.author);
                    bookInput.setText(selectedBook.book);
                    actionComboBox.setSelectedItem(selectedBook.status);
                }
            }
        });

        // Save data when clicking save
        actionButton.addActionListener(clickEvent -> {
            String selectedValue = (String) booksList.getSelectedValue();
            String actionValue = (String) actionComboBox.getSelectedItem();
            if(selectedValue == null) {
                JOptionPane.showMessageDialog(panel, "Please choose a book ");
                return;
            };
            if (actionValue.equals("Buy")) {
                int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to buy " + selectedValue + " for 3.99$", "Confirmation", JOptionPane.YES_NO_OPTION);

                if (response == JOptionPane.YES_OPTION) {
                    globalBooksObject.editBookInformation(selectedValue, "status", "SOLD");
                    globalBooksObject.editBookInformation(selectedValue, "borrowedBy", username);
                    JOptionPane.showMessageDialog(panel, "Success you have bought " + selectedValue);
                    setModel(globalBooksObject, booksList);
                }
            } else {
                int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to borrow " + selectedValue + " for 5 days", "Confirmation", JOptionPane.YES_NO_OPTION);
                if(response == JOptionPane.YES_OPTION) {
                    globalBooksObject.editBookInformation(selectedValue, "status", "BORROWED");
                    globalBooksObject.editBookInformation(selectedValue, "borrowedBy", username);
                    LocalDate currentDate = LocalDate.now();
                    LocalDate fiveDaysLater = currentDate.plusDays(5);

                    // Pass the fiveDaysLater value to your method
                    globalBooksObject.editBookInformation(selectedValue, "borrowDate", String.valueOf(fiveDaysLater));

                    JOptionPane.showMessageDialog(panel, "Success you have borrowed " + selectedValue);
                    setModel(globalBooksObject, booksList);
                }
            }
        });

    }

    public void setModel(BooksDB globalBooksObject, JList<String> booksList) {
        // Package all data
        BooksDB[] allBooks = globalBooksObject.getAllBooks();
        ArrayList<String> values = new ArrayList<>();

        for (BooksDB book : allBooks) {
            if (book.status.equals("AVAILABLE")) {
                values.add(book.book);
            }
        }

        booksList.setModel(new AbstractListModel<String>() {
            @Override
            public int getSize() {
                return values.size();
            }

            @Override
            public String getElementAt(int index) {
                return values.get(index);
            }
        });
    }

}
