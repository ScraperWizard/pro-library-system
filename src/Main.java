//import Gui.*;
import Gui.Main.MainFrame;
import Gui.loginSelector.loginSelector;
import java.awt.EventQueue;

public class Main {
	public static void main(String[] args) throws Exception {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame mainFrame = new MainFrame();
					loginSelector loginSelectorFrame = new loginSelector(mainFrame.jFrame);
					mainFrame.jFrame.setContentPane(loginSelectorFrame.contentPane);
				} catch (Exception error) {
					error.printStackTrace();
				}
			}
		});
	}
}
