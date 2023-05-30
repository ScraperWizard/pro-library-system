package Gui.Customers.Home;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import java.awt.SystemColor;

public class aboutUsPage {
    public JPanel AboutUsPane;

    public aboutUsPage () {
        AboutUsPane = new JPanel();
        AboutUsPane.setBackground(new Color(76, 128, 144));
        AboutUsPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        AboutUsPane.setLayout(null);

        // Get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (screenSize.width) / 2;
        int screenHeight = (screenSize.height) / 2;
        AboutUsPane.setBounds(270, 0, screenSize.width - 250, screenSize.height);

        JLabel ourTeamText = new JLabel("OUR TEAM");
        ourTeamText.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 25));
        ourTeamText.setHorizontalAlignment(SwingConstants.CENTER);
        ourTeamText.setBounds(333, 128, 475, 58);
        AboutUsPane.add(ourTeamText);

        JPanel AbdulrahmanPanel = new JPanel();
        AbdulrahmanPanel.setBackground(new Color(153, 204, 255));
        AbdulrahmanPanel.setBounds(92, 197, 206, 432);
        AboutUsPane.add(AbdulrahmanPanel);
        AbdulrahmanPanel.setLayout(null);

        JPanel abdulrahmanPicPanel = new JPanel();
        abdulrahmanPicPanel.setBounds(21, 56, 164, 147);
        AbdulrahmanPanel.add(abdulrahmanPicPanel);
        abdulrahmanPicPanel.setLayout(null);

        JLabel AbdulNameLabel = new JLabel("Abdulrahman Al Ali");
        AbdulNameLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        AbdulNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        AbdulNameLabel.setBounds(0, 11, 206, 37);
        AbdulrahmanPanel.add(AbdulNameLabel);

        JTextArea abdulTextArea = new JTextArea();
        abdulTextArea.setBackground(UIManager.getColor("Button.light"));
        abdulTextArea.setEditable(false);
        abdulTextArea.setText("Type your info");
        abdulTextArea.setBounds(21, 214, 164, 207);
        AbdulrahmanPanel.add(abdulTextArea);

        JPanel YamanPanel = new JPanel();
        YamanPanel.setBackground(new Color(153, 204, 255));
        YamanPanel.setLayout(null);
        YamanPanel.setBounds(342, 197, 206, 432);
        AboutUsPane.add(YamanPanel);

        JPanel YamanPicPanel = new JPanel();
        YamanPicPanel.setLayout(null);
        YamanPicPanel.setBounds(21, 56, 164, 147);
        YamanPanel.add(YamanPicPanel);

        JLabel YamanNameLabel = new JLabel("Mohammad Yaman");
        YamanNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        YamanNameLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        YamanNameLabel.setBounds(0, 11, 206, 37);
        YamanPanel.add(YamanNameLabel);

        JTextArea YamanTextArea = new JTextArea();
        YamanTextArea.setText("Type your info");
        YamanTextArea.setEditable(false);
        YamanTextArea.setBackground(SystemColor.controlHighlight);
        YamanTextArea.setBounds(21, 214, 164, 207);
        YamanPanel.add(YamanTextArea);

        JPanel YousefPanel = new JPanel();
        YousefPanel.setBackground(new Color(153, 204, 255));
        YousefPanel.setLayout(null);
        YousefPanel.setBounds(588, 197, 206, 432);
        AboutUsPane.add(YousefPanel);

        JPanel YousefPicPanel = new JPanel();
        YousefPicPanel.setLayout(null);
        YousefPicPanel.setBounds(21, 56, 164, 147);
        YousefPanel.add(YousefPicPanel);

        JLabel YousefNameLabel = new JLabel("Yousef  Al Sakkaf");
        YousefNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        YousefNameLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        YousefNameLabel.setBounds(0, 11, 206, 37);
        YousefPanel.add(YousefNameLabel);

        JTextArea YousefTextArea = new JTextArea();
        YousefTextArea.setText("Type your info");
        YousefTextArea.setEditable(false);
        YousefTextArea.setBackground(SystemColor.controlHighlight);
        YousefTextArea.setBounds(21, 214, 164, 207);
        YousefPanel.add(YousefTextArea);

        JPanel MontaserPanel = new JPanel();
        MontaserPanel.setBackground(new Color(153, 204, 255));
        MontaserPanel.setLayout(null);
        MontaserPanel.setBounds(835, 197, 206, 432);
        AboutUsPane.add(MontaserPanel);

        JPanel MontaserPicPanel = new JPanel();
        MontaserPicPanel.setLayout(null);
        MontaserPicPanel.setBounds(21, 56, 164, 147);
        MontaserPanel.add(MontaserPicPanel);

        JLabel MontaserNameLabel = new JLabel("Ahmad Montaser");
        MontaserNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        MontaserNameLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        MontaserNameLabel.setBounds(0, 11, 206, 37);
        MontaserPanel.add(MontaserNameLabel);

        JTextArea MontaserTextArea = new JTextArea();
        MontaserTextArea.setText("Type your info");
        MontaserTextArea.setEditable(false);
        MontaserTextArea.setBackground(SystemColor.controlHighlight);
        MontaserTextArea.setBounds(21, 214, 164, 207);
        MontaserPanel.add(MontaserTextArea);

    }
}
