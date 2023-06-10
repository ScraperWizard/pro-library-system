package Gui.register;

import Database.Customers.Customers;
import Gui.Login.Login;
import Gui.loginSelector.loginSelector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Register {
	public JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField AccountInput;
	private JTextField PasswordInput;
	private JTextField CheckPassword;
	private JTextField UserNameInPut;
	private JTextField emailInput;
	private JTextField PhoneNumInput;
	private JPasswordField PassWordInput;
	private JPasswordField ReEnterPassInput;

	private boolean validity() {
		if (UserNameInPut.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Please enter your username!");
			return false;
		} else if (emailInput.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Please enter your email!");
			return false;
		} else if (new String(PassWordInput.getPassword()).isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Please choose a password!");
			return false;
		} else if (new String(ReEnterPassInput.getPassword()).isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Please re-enter your password");
			return false;
		} else if (PhoneNumInput.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Please enter your phone number!");
			return false;
		} else if(new String(PassWordInput.getPassword()) == new String(ReEnterPassInput.getPassword())) {
			JOptionPane.showMessageDialog(contentPane, "Passwords do not match!");
			return false;
		}

		return true;
	}

	public Register(JFrame mainFrame) {
		Customers globalCustomersObject = new Customers();
		contentPane = new JPanel();
		contentPane.setBackground(new Color(57, 130, 146));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		// Get screen size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		int frameWidth = 500;
		int frameHeight = 520;
		int frameX = (screenWidth - frameWidth) / 2;
		int frameY = (screenHeight - frameHeight) / 2;
		mainFrame.setBounds(frameWidth, frameY, frameWidth, frameHeight);

		JButton returnButton = new JButton("< Return");
		returnButton.setForeground(new Color(255, 0, 0));
		returnButton.setBounds(5, 5, 100, 20);
		contentPane.add(returnButton);

		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent clickEvent) {
				contentPane.setVisible(false);
				Login loginFrame = new Login(mainFrame);
				mainFrame.setContentPane(loginFrame.contentPane);
			}
		});

		JPanel registerPanel = new JPanel();
		registerPanel.setBorder(null);
		registerPanel.setBackground(new Color(57, 130, 146));
		registerPanel.setBounds(91, 34, 303, 427);
		contentPane.add(registerPanel);
		registerPanel.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblNewLabel = new JLabel("Register as customer!");
		lblNewLabel.setForeground(new Color(254, 255, 255));
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		registerPanel.add(lblNewLabel);

		JLabel UserNameVar = new JLabel("Username:");
		UserNameVar.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		UserNameVar.setForeground(new Color(254, 255, 255));
		registerPanel.add(UserNameVar);

		UserNameInPut = new JTextField();
		registerPanel.add(UserNameInPut);
		UserNameInPut.setColumns(10);

		JLabel EMailVar = new JLabel("E-mail:");
		EMailVar.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		EMailVar.setForeground(new Color(254, 255, 255));
		registerPanel.add(EMailVar);

		emailInput = new JTextField();
		registerPanel.add(emailInput);
		emailInput.setColumns(10);

		JLabel PassWordVar = new JLabel("Password");
		PassWordVar.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		PassWordVar.setForeground(new Color(254, 255, 255));
		registerPanel.add(PassWordVar);

		PassWordInput = new JPasswordField();
		PassWordInput.setColumns(10);
		registerPanel.add(PassWordInput);

		JLabel RepeatPassVar = new JLabel("Re-enter Password");
		RepeatPassVar.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		RepeatPassVar.setForeground(new Color(254, 255, 255));
		registerPanel.add(RepeatPassVar);

		ReEnterPassInput = new JPasswordField();
		registerPanel.add(ReEnterPassInput);

		JLabel PhoneNumVar = new JLabel("Phone Number");
		PhoneNumVar.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		PhoneNumVar.setForeground(new Color(254, 255, 255));
		registerPanel.add(PhoneNumVar);

		PhoneNumInput = new JTextField();
		registerPanel.add(PhoneNumInput);
		PhoneNumInput.setColumns(10);

		JButton RegisterVar = new JButton("Register");
		RegisterVar.setForeground(new Color(0, 52, 255));
		RegisterVar.setBounds(0, 0, 50, 50);
		registerPanel.add(RegisterVar);

		RegisterVar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validity()) {
					try {
						globalCustomersObject.addUser(UserNameInPut.getText(), new String(PassWordInput.getPassword()),
								PhoneNumInput.getText(), emailInput.getText());
						JOptionPane.showMessageDialog(contentPane, "Account registered!");
						contentPane.setVisible(false);
						Login newCustomer = new Login(mainFrame);
						mainFrame.setContentPane(newCustomer.contentPane);
					} catch (Exception dbError) {
						// TODO Auto-generated catch block
						System.out.print("An error has occured while accessing DB.");
					}
				}
			}
		});

		RegisterVar.setBackground(new Color(254, 255, 255));

		// Declare the mouse event handler
		MouseAdapter panelMouseEventHandler = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPane.setVisible(false);
				loginSelector loginSelectorFrame = new loginSelector(mainFrame);
				mainFrame.setContentPane(loginSelectorFrame.contentPane);
			}

			@Override
			public void mouseEntered(MouseEvent event) {
				// handle mouse enter event
				event.getComponent().setBackground(new Color(123, 147, 163));
				event.getComponent().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent event) {
				// handle mouse exit event
				event.getComponent().setBackground(new Color(83, 107, 123));
				event.getComponent().setCursor(Cursor.getDefaultCursor());
			}
		};
	}
}