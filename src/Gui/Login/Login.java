package GUI.Login;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import Database.Users.User;
import java.awt.Window.*;
import java.lang.reflect.Method;

public class Login extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	public Login() {
		User globalUserObject = new User();
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"C:\\Users\\user\\OneDrive\\Pictures\\132-1324648_sergio-ramos-is-an-aura-real-madrid-cannot (1).jpg"));
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Heading
		JLabel lblMainHeading = new JLabel("Welcome to ProLibrary");
		lblMainHeading.setBounds(156, 11, 143, 14);
		lblMainHeading.setHorizontalAlignment(SwingConstants.LEFT);
		lblMainHeading.setVerticalAlignment(SwingConstants.BOTTOM);
		contentPane.add(lblMainHeading);

		// Username heading
		JLabel lblUsernameHeading = new JLabel("Username:");
		lblUsernameHeading.setBounds(10, 32, 81, 14);
		contentPane.add(lblUsernameHeading);

		// Username input
		JTextField usernameInput = new JTextField();
		usernameInput.setBounds(10, 50, 187, 20);
		contentPane.add(usernameInput);
		usernameInput.setColumns(10);

		// Password heading
		JLabel lblPasswordHeading = new JLabel("Password:");
		lblPasswordHeading.setBounds(10, 84, 181, 14);
		contentPane.add(lblPasswordHeading);

		// Password input
		JTextField passwordInput = new JPasswordField();
		passwordInput.setBounds(10, 102, 187, 20);
		contentPane.add(passwordInput);

		// Password
		JButton btnLogin = new JButton("Log in");
		btnLogin.setBounds(57, 133, 89, 23);
		contentPane.add(btnLogin);

		// Register form
		JLabel lblRegisterHeading = new JLabel("Register");
		lblRegisterHeading.setBounds(80, 167, 81, 14);
		contentPane.add(lblRegisterHeading);

		lblUsernameHeading.setText("New Username:");

		btnLogin.addActionListener(clickEvent -> {
			// Get username and password
			String username = usernameInput.getText();
			String password = passwordInput.getText();
			boolean validLogin = false;

			// Validate the login
			if (globalUserObject.validateLogin(username, password)) {
				validLogin = true;
			} else {
				// Password error display
				lblUsernameHeading.setText("Incorrect Username");
				lblUsernameHeading.setForeground(Color.RED);
				// Username error display
				lblPasswordHeading.setText("Incorrect Password");
				lblPasswordHeading.setForeground(Color.RED);
			}

			// Display login message
			JOptionPane.showMessageDialog(null, validLogin ? "Valid login" : "Invalid login", "Login attempt",
					JOptionPane.INFORMATION_MESSAGE);
		});
	}
}
