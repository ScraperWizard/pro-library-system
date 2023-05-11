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
import javax.swing.GroupLayout.Alignment;

public class Login {
	public JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	public Login(JFrame mainFrame) {
		Customers globalCustomersObject = new Customers();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(57, 130, 146));

		// Get screen size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (screenSize.width) / 2;
		int screenHeight = (screenSize.height) / 2;
		
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
				event.getComponent().setForeground(Color.WHITE);
				event.getComponent().setCursor(Cursor.getDefaultCursor());
			}
		};
						GridBagLayout gbl_contentPane = new GridBagLayout();
						gbl_contentPane.columnWidths = new int[]{100, 613, 303, 0};
						gbl_contentPane.rowHeights = new int[]{20, 237, 300, 0};
						gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
						gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
						contentPane.setLayout(gbl_contentPane);
								
										JButton returnButton = new JButton("< Return");
										returnButton.setForeground(new Color(255, 0, 0));
										GridBagConstraints gbc_returnButton = new GridBagConstraints();
										gbc_returnButton.fill = GridBagConstraints.BOTH;
										gbc_returnButton.insets = new Insets(0, 0, 5, 5);
										gbc_returnButton.gridx = 0;
										gbc_returnButton.gridy = 0;
										contentPane.add(returnButton, gbc_returnButton);
										
												returnButton.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent clickEvent) {
														contentPane.setVisible(false);
														loginSelector loginSelectorFrame = new loginSelector(mainFrame);
														mainFrame.setContentPane(loginSelectorFrame.contentPane);
													}
												});
						
								JPanel loginPanel = new JPanel();
								loginPanel.setBackground(new Color(57, 130, 146));
								loginPanel.setLayout(new GridLayout(0, 1, 0, 0));
								
										// Staff login heading
										JLabel lblStaffHeading = new JLabel("Login as customer!");
										lblStaffHeading.setHorizontalAlignment(SwingConstants.CENTER);
										lblStaffHeading.setForeground(new Color(254, 255, 255));
										loginPanel.add(lblStaffHeading);
										lblStaffHeading.setFont(new Font("Dialog", Font.BOLD, 22));
										
												// Username heading
												JLabel lblUsernameHeading = new JLabel("Username:");
												lblUsernameHeading.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
												lblUsernameHeading.setForeground(new Color(254, 255, 255));
												loginPanel.add(lblUsernameHeading);
												
														// Username input
														JTextField usernameInput = new JTextField();
														usernameInput.setToolTipText("Username..");
														loginPanel.add(usernameInput);
														
																// Password heading
																JLabel lblPasswordHeading = new JLabel("Password:");
																lblPasswordHeading.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
																lblPasswordHeading.setForeground(new Color(254, 255, 255));
																loginPanel.add(lblPasswordHeading);
																
																		// Password input
																		JTextField passwordInput = new JPasswordField();
																		passwordInput.setToolTipText("Password...");
																		loginPanel.add(passwordInput);
																		
																				// Login button
																				JButton customerLoginButton = new JButton("Login");
																				loginPanel.add(customerLoginButton);
																				customerLoginButton.setForeground(new Color(0, 52, 255));
																				
																				JLabel forgotPasswordLbl = new JLabel("Forgot your password?");
																				forgotPasswordLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
																				forgotPasswordLbl.setForeground(new Color(254, 255, 255));
																				forgotPasswordLbl.setHorizontalAlignment(SwingConstants.CENTER);
																				loginPanel.add(forgotPasswordLbl);
																				
																				JLabel orLbl = new JLabel("Or");
																				orLbl.setForeground(new Color(254, 255, 255));
																				orLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
																				orLbl.setHorizontalAlignment(SwingConstants.CENTER);
																				loginPanel.add(orLbl);
																				
																						JLabel registerNowLbl = new JLabel("Register");
																						registerNowLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
																						loginPanel.add(registerNowLbl);
																						registerNowLbl.setHorizontalAlignment(SwingConstants.CENTER);
																						registerNowLbl.setForeground(Color.WHITE);
																						GridBagConstraints gbc_loginPanel = new GridBagConstraints();
																						gbc_loginPanel.fill = GridBagConstraints.BOTH;
																						gbc_loginPanel.gridx = 2;
																						gbc_loginPanel.gridy = 2;
																						contentPane.add(loginPanel, gbc_loginPanel);
																						registerNowLbl.addMouseListener(panelMouseEventHandler);
																						
																								customerLoginButton.addActionListener(clickEvent -> {
																									// Get username and password
																									String username = usernameInput.getText();
																									String password = passwordInput.getText();
																									boolean validLogin = false;
																						
																									// Validate the login
																									if (globalCustomersObject.validateLogin(username, password)) {
																										validLogin = true;
																										contentPane.setVisible(false);
																										// staffLogin staffLogin = new staffLogin();
																										// mainFrame.setContentPane(staffLogin.contentPane);
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

	}
}