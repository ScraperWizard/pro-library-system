package Gui.register;

import Database.Customers.Customers;
import Gui.Login.Login;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register {
	public JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField AccountInput;
	private JTextField PasswordInput;
	private JTextField CheckPassword;

	public Register(JFrame mainFrame) {
		Customers globalUserObject = new Customers();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		// Get screen size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (screenSize.width) / 2;
		int screenHeight = (screenSize.height) / 2;

		JButton returnButton = new JButton("<- Return");
		returnButton.setForeground(new Color(255, 0, 0));
		returnButton.setBounds(5, 5, 100, 40);
		contentPane.add(returnButton);

		JLabel accountStatement = new JLabel("Enter your account : ");
		accountStatement.setBounds(screenWidth-70, screenHeight-250, 120, 14);
		contentPane.add(accountStatement);


		AccountInput = new JTextField();
		AccountInput.setBounds(screenWidth-70, screenHeight-230, 120, 20);
		contentPane.add(AccountInput);
		AccountInput.setColumns(10);

		JLabel PasswordStatement = new JLabel("Enter your password : ");
		PasswordStatement.setBounds(screenWidth-70, screenHeight-210, 130, 14);
		contentPane.add(PasswordStatement);

		PasswordInput = new JTextField();
		PasswordInput.setBounds(screenWidth-70, screenHeight-190, 120, 20);
		contentPane.add(PasswordInput);
		PasswordInput.setColumns(10);

		JLabel CheckPasswordState = new JLabel("Rewrite your password : ");
		CheckPasswordState.setBounds(screenWidth-70, screenHeight-170, 130, 14);
		contentPane.add(CheckPasswordState);

		CheckPassword = new JTextField();
		CheckPassword.setBounds(screenWidth-70, screenHeight-150, 120, 20);
		contentPane.add(CheckPassword);
		CheckPassword.setColumns(10);

		JButton btnNewButton = new JButton("Register");
		btnNewButton.setBounds(screenWidth-70, screenHeight-130, 89, 23);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent clickEvent) {
				String account = AccountInput.getText();
				String password = PasswordInput.getText();
				String repeatedPassword = CheckPassword.getText();

				if(password.equals(repeatedPassword)) { // this needs DB
					System.exit(0);
				}

				contentPane.setVisible(false);
				Login loginFrame = new Login(mainFrame);
				mainFrame.setContentPane(loginFrame.contentPane);
			}
		});

		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent clickEvent) {
				contentPane.setVisible(false);
				Login loginFrame = new Login(mainFrame);
				mainFrame.setContentPane(loginFrame.contentPane);
			}
		});
	}
}
