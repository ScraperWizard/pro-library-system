package Gui.Customers.Settings;

import Database.Customers.Customers;

import javax.swing.*;
import java.awt.*;

public class changePassword {
    public JFrame jFrame;
    private JTextField passWordInput;
    private JTextField reEnterNewPassInput;

    public changePassword(String username) {
        jFrame = new JFrame();
        Customers globalCustomersObject = new Customers();

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setBounds(500, 500, 500, 400);
        jFrame.setTitle("Staff - Edit");

        JPanel changePassPanel = new JPanel();
        jFrame.setContentPane(changePassPanel);
        changePassPanel.setBackground(new Color(57, 130, 146));
        changePassPanel.setLayout(null);

        JLabel TitleLabel = new JLabel("Change Password");
        TitleLabel.setBounds(63, 0, 367, 35);
        changePassPanel.add(TitleLabel);
        TitleLabel.setForeground(new Color(254, 255, 255));
        TitleLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        TitleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(100, 90, 300, 177);
        mainPanel.setBackground(new Color(57, 130, 146));
        changePassPanel.add(mainPanel);
        mainPanel.setLayout(new GridLayout(0, 1, 0, 0));

        JLabel NewPassWordLabel = new JLabel("New Password : ");
        NewPassWordLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        NewPassWordLabel.setForeground(new Color(254, 255, 255));
        mainPanel.add(NewPassWordLabel);

        passWordInput = new JPasswordField();
        mainPanel.add(passWordInput);
        passWordInput.setColumns(10);

        JLabel reEnterNewPassLabel = new JLabel("Re-enter new Password :");
        reEnterNewPassLabel.setForeground(new Color(254, 255, 255));
        reEnterNewPassLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        mainPanel.add(reEnterNewPassLabel);

        reEnterNewPassInput = new JPasswordField();
        mainPanel.add(reEnterNewPassInput);
        reEnterNewPassInput.setColumns(10);

        JButton saveButton = new JButton("Save!");
        saveButton.setForeground(new Color(0, 0, 0));
        saveButton.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        mainPanel.add(saveButton);

        // Save data when clicking save
        saveButton.addActionListener(clickEvent -> {
            String passWordValue = passWordInput.getText();


            if(passWordValue.equals("")) {
                JOptionPane.showMessageDialog(mainPanel, "Please fill in fields!");
            }
            else if (!passWordValue.equals(reEnterNewPassInput.getText())) {
                JOptionPane.showMessageDialog(mainPanel, "Please enter matching passwords");
            }
            else if (passWordValue.length() < 5) {
                JOptionPane.showMessageDialog(mainPanel, "Please enter a stronger password");
            }

            else {
                globalCustomersObject.editUserInformation( username , "password", passWordValue);
                JOptionPane.showMessageDialog(mainPanel, "Password changed sucessfully");

            }
        });

    }

    public void setModel(Customers globalCustomersObject, JList<String> customersList) {
        // Package all data
        String[] values;
        Customers[] allCustomers = globalCustomersObject.getAllUsers();
        values = new String[allCustomers.length];

        for(int i = 0; i < allCustomers.length; i++)
            values[i] = allCustomers[i].username;
    }
}