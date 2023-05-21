package Gui.Main;

import Gui.Login.Login;

import javax.swing.*;

public class MainFrame {
    public JFrame jFrame;
    public MainFrame() {
        jFrame = new JFrame();

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.setResizable(false);
    }
}
