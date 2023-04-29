package Gui.register;

import Database.Users.User;
import Gui.Login.Login;
import Gui.loginSelector.loginSelector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register {
	public JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	public Register(JFrame mainFrame) {
		User globalUserObject = new User();
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
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent clickEvent) {
				contentPane.setVisible(false);
				Login loginFrame = new Login(mainFrame);
				mainFrame.setContentPane(loginFrame.contentPane);
			}
		});
	}
}
