package Gui.Login;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import Database.Customers.Customers;
import Gui.loginSelector.loginSelector;
import Gui.register.Register;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login {
	public JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	public Login(JFrame mainFrame) {
		Customers globalCustomersObject = new Customers();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		// Get screen size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (screenSize.width) / 2;
		int screenHeight = (screenSize.height) / 2;

		// Staff login heading
		JLabel lblStaffHeading = new JLabel("Customer Login");
		lblStaffHeading.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblStaffHeading.setBounds(screenWidth - 110, screenHeight - 350, 200, 40);
		contentPane.add(lblStaffHeading);

		// Username heading
		JLabel lblUsernameHeading = new JLabel("Username:");
		lblUsernameHeading.setBounds(screenWidth - 96, screenHeight - 245, 206, 13);
		contentPane.add(lblUsernameHeading);

		// Username input
		JTextField usernameInput = new JTextField();
		usernameInput.setBounds(screenWidth - 100, screenHeight - 230, 187, 20);
		contentPane.add(usernameInput);
		usernameInput.setColumns(10);

		// Password heading
		JLabel lblPasswordHeading = new JLabel("Password:");
		lblPasswordHeading.setBounds(screenWidth - 96, screenHeight - 155, 187, 13);
		contentPane.add(lblPasswordHeading);

		// Password input
		JTextField passwordInput = new JPasswordField();
		passwordInput.setBounds(screenWidth - 100, screenHeight - 140, 187, 20);
		contentPane.add(passwordInput);

		// Login button
		JButton customerLoginButton = new JButton("Login");
		customerLoginButton.setForeground(new Color(0, 52, 255));
		customerLoginButton.setBounds(627, 284, 100, 31);
		contentPane.add(customerLoginButton);
		customerLoginButton.addActionListener(clickEvent -> {
			// Get username and password
			String username = usernameInput.getText();
			String password = passwordInput.getText();
			boolean validLogin = false;

			// Validate the login
			if (globalCustomersObject.validateLogin(username, password)) {
				validLogin = true;
				contentPane.setVisible(false);
//				staffLogin staffLogin = new staffLogin();
//				mainFrame.setContentPane(staffLogin.contentPane);
			} else {
				// Password error display
				lblUsernameHeading.setText("Incorrect Username");
				lblUsernameHeading.setForeground(Color.RED);
				// Username error display
				lblPasswordHeading.setText("Incorrect Password");
				lblPasswordHeading.setForeground(Color.RED);
				// Display login message
				JOptionPane.showMessageDialog(null, validLogin ? "Valid login" : "Invalid login", "Login attempt",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		JButton returnButton = new JButton("< Return");
		returnButton.setForeground(new Color(255, 0, 0));
		returnButton.setBounds(5, 5, 100, 20);
		contentPane.add(returnButton);
		 MouseAdapter panelMouseEventHandler = new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                contentPane.setVisible(false);
	                Register RegisterFrame = new Register(mainFrame);
	                mainFrame.setContentPane(RegisterFrame.contentPane);
	            }

	            @Override
	            public void mouseEntered(MouseEvent event) {
	                // handle mouse enter event
	                event.getComponent().setForeground(Color.BLUE);
	                event.getComponent().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	            }

	            @Override
	            public void mouseExited(MouseEvent event) {
	                // handle mouse exit event
	                event.getComponent().setForeground(Color.BLACK);
	                event.getComponent().setCursor(Cursor.getDefaultCursor());
	            }
	        };
			
			JLabel lblNewLabel = new JLabel("Do not have an account? Register");
			contentPane.add(lblNewLabel);
			
			lblNewLabel.setBounds(598, 346, 200, 14);
	       lblNewLabel.addMouseListener(panelMouseEventHandler);
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent clickEvent) {
				contentPane.setVisible(false);
				loginSelector loginSelectorFrame = new loginSelector(mainFrame);
				mainFrame.setContentPane(loginSelectorFrame.contentPane);
			}
		});

		
	}
}