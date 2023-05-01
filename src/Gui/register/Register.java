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
	private JTextField EMaiLInput;
	private JTextField PhoneNumInput;
	private JPasswordField PassWordInput;
	private JPasswordField ReEnterPassInput;
	
	private  boolean validity () {
	//Username Validity
		if (UserNameInPut.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Username cannot be empty");
			return false; 
		}
	//Email validity
		if (EMaiLInput.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Email cannot be empty");
			return false; 
		}
   //Password validity
		if (PassWordInput.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Password cannot be empty");
			return false; 
		}
	// Re-enter password validity	
		if (ReEnterPassInput.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Re-enter cannot be empty");
			return false; 
		}
	// Phone number validity
		if (PhoneNumInput.getText().isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Phone number cannot be empty");
			return false; 
		}
		
		return true;	
	}

	public Register(JFrame mainFrame) {
		Customers globalUserObject = new Customers();
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		// Get screen size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (screenSize.width) / 2;
		int screenHeight = (screenSize.height) / 2;

	JButton returnButton = new JButton("< Return");
		returnButton.setForeground(new Color(255, 0, 0));
		returnButton.setBounds(5, 5, 100, 20);
		contentPane.add(returnButton);
		
		JLabel UserNameVar = new JLabel("Username:");
		UserNameVar.setBounds(643, 198, 94, 14);
		contentPane.add(UserNameVar);
		
		UserNameInPut = new JTextField();
		UserNameInPut.setBounds(643, 223, 135, 14);
		contentPane.add(UserNameInPut);
		UserNameInPut.setColumns(10);
		
		JLabel EMailVar = new JLabel("E-mail:");
		EMailVar.setBounds(643, 248, 94, 14);
		contentPane.add(EMailVar);
		
		EMaiLInput = new JTextField();
		EMaiLInput.setColumns(10);
		EMaiLInput.setBounds(643, 273, 135, 14);
		contentPane.add(EMaiLInput);
		
		JLabel PassWordVar = new JLabel("Password");
		PassWordVar.setBounds(643, 298, 94, 14);
		contentPane.add(PassWordVar);
		
		JLabel RepeatPassVar = new JLabel("Re-enter Password");
		RepeatPassVar.setBounds(643, 348, 135, 14);
		contentPane.add(RepeatPassVar);
		
		JLabel PhoneNumVar = new JLabel("Phone Number");
		PhoneNumVar.setBounds(643, 399, 94, 14);
		contentPane.add(PhoneNumVar);
		
		PhoneNumInput = new JTextField();
		PhoneNumInput.setColumns(10);
		PhoneNumInput.setBounds(643, 424, 135, 14);
		contentPane.add(PhoneNumInput);
		
		PassWordInput = new JPasswordField();
		PassWordInput.setBounds(643, 323, 135, 14);
		contentPane.add(PassWordInput);
		
		ReEnterPassInput = new JPasswordField();
		ReEnterPassInput.setBounds(643, 373, 135, 14);
		contentPane.add(ReEnterPassInput);
		
		JButton RegisterVar = new JButton("Register");
		RegisterVar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterVar.setEnabled(validity());
				if (validity() == (false)) {
					RegisterVar.setEnabled(true);
				} 
				
				Login newCustomer = new Login(mainFrame);
				mainFrame.setContentPane(newCustomer.contentPane);
			}
		});
		RegisterVar.setBackground(SystemColor.inactiveCaption);
		RegisterVar.setBounds(663, 449, 100, 20);
		contentPane.add(RegisterVar);
		
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


		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent clickEvent) {
				contentPane.setVisible(false);
				Login loginFrame = new Login(mainFrame);
				mainFrame.setContentPane(loginFrame.contentPane);
			}
		});
	}
}