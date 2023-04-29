import Database.*;
import Database.Users.User;
import Gui.Login.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.Window.*;

public class Main {
	public static void main(String[] args) {
		// Start up GUI, prompt to login
		Login loginFrame = new Login();
		loginFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		loginFrame.setVisible(true);
	}
}
// Push from abdulrahman