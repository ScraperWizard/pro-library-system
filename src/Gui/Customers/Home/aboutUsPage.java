package Gui.Customers.Home;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Gui.Customers.sideMenu.sideMenu;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import java.awt.Panel;

public class aboutUsPage {
    public JPanel AboutUsPane;
    JScrollPane scrollPaneAlALI;
    JScrollPane scrollPaneYaman;
    JScrollPane scrollPaneYousef;
    JScrollPane scrollPaneAhmad;

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

        JLabel AlAliPic= new JLabel("");
        AlAliPic.setPreferredSize(new Dimension(183, 275));
        AlAliPic.setBounds(1, -1, 164, 147);
        abdulrahmanPicPanel.add(AlAliPic);
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/Gui/images/cuteCow.png"));
        Image image = imageIcon.getImage().getScaledInstance(100, 275, Image.SCALE_DEFAULT);
        AlAliPic.setPreferredSize(new Dimension(imageIcon.getIconWidth(), imageIcon.getIconHeight()));
        AlAliPic.setIcon(imageIcon);

        JLabel AbdulNameLabel = new JLabel("Abdulrahman Al Ali");
        AbdulNameLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        AbdulNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        AbdulNameLabel.setBounds(0, 11, 206, 37);
        AbdulrahmanPanel.add(AbdulNameLabel);

        scrollPaneAlALI = new JScrollPane();
        scrollPaneAlALI.setBounds(21, 214, 164, 207);
        AbdulrahmanPanel.add(scrollPaneAlALI);

        JTextArea AlAlTextArea = new JTextArea();
        AlAlTextArea.setWrapStyleWord(true);
        AlAlTextArea.setText("Meet AbdelRahman Al Ali, a visionary leader with a passion for innovation and a drive for success. Known for his exceptional problem-solving skills and strategic mindset, AbdelRahman has made a significant impact in the fields of technology and business.\r\n"
                + "\r\n"
                + "With a strong educational background in computer science and a wealth of practical experience, AbdelRahman has consistently demonstrated his ability to navigate complex challenges and deliver innovative solutions. His expertise in software development and data analysis has earned him recognition as a trusted advisor and a go-to resource for organizations seeking to optimize their operations.\r\n"
                + "\r\n"
                + "AbdelRahman's exceptional leadership qualities have also made him a sought-after mentor and team player. He has a remarkable ability to inspire and motivate those around him, fostering a collaborative and inclusive work environment that fuels creativity and productivity.\r\n"
                + "\r\n"
                + "Beyond his professional accomplishments, AbdelRahman is known for his philanthropic endeavors and commitment to giving back to the community. He actively volunteers his time and expertise to various charitable organizations, making a positive impact on the lives of many.\r\n"
                + "\r\n"
                + "In his spare time, AbdelRahman enjoys exploring new technologies, reading thought-provoking books, and engaging in outdoor activities. His curiosity knows no bounds, and he constantly seeks opportunities to learn and grow.\r\n"
                + "\r\n"
                + "With his exceptional skills, unwavering determination, and a genuine passion for making a difference, AbdelRahman Al Ali is poised to continue leaving a lasting mark on the industry and inspiring others to reach their full potential.");
        AlAlTextArea.setLineWrap(true);
        AlAlTextArea.setEditable(false);
        AlAlTextArea.setBackground(SystemColor.controlHighlight);
        scrollPaneAlALI.setViewportView(AlAlTextArea);

        JPanel YamanPanel = new JPanel();
        YamanPanel.setBackground(new Color(153, 204, 255));
        YamanPanel.setLayout(null);
        YamanPanel.setBounds(342, 197, 206, 432);
        AboutUsPane.add(YamanPanel);

        JPanel YamanPicPanel = new JPanel();
        YamanPicPanel.setLayout(null);
        YamanPicPanel.setBounds(21, 56, 164, 147);
        YamanPanel.add(YamanPicPanel);

        JLabel YamanPic = new JLabel("");
        YamanPic.setBounds(0, 0, 164, 147);
        YamanPicPanel.add(YamanPic);
        YamanPic.setIcon(changeIcon(160,160,"/Gui/images/MohYamaPic.jpeg"));


        JLabel YamanNameLabel = new JLabel("Mohammad Yaman");
        YamanNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        YamanNameLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        YamanNameLabel.setBounds(0, 11, 206, 37);
        YamanPanel.add(YamanNameLabel);

        scrollPaneYaman = new JScrollPane();
        scrollPaneYaman.setBounds(21, 214, 164, 207);
        YamanPanel.add(scrollPaneYaman);

        JTextArea YamanTextArea = new JTextArea();
        YamanTextArea.setBounds(21, 214, 164, 207);
//        YamanPanel.add(YamanTextArea);
        YamanTextArea.setText("A High School Graduate at Dubai International Private School – AL QOUZ with an Outstanding (97+) total average and being ranked the 6th. Mohammad Yaman have participated in MUN, Model United Nations, at AUD as well as have placed the 1st for the best invention at INJAZ JA Innovation Camp. Now, he is available to provide you Readers with the best services.\n\n“Dont lit the fuse if you can’t take the heat”");
        YamanTextArea.setEditable(false);
        YamanTextArea.setBackground(SystemColor.controlHighlight);
        YamanTextArea.setLineWrap(true); // Set line wrap property
        YamanTextArea.setWrapStyleWord(true);
        scrollPaneYaman.setViewportView(YamanTextArea);

        JPanel YousefPanel = new JPanel();
        YousefPanel.setLayout(null);
        YousefPanel.setBackground(new Color(153, 204, 255));
        YousefPanel.setBounds(585, 197, 206, 432);
        AboutUsPane.add(YousefPanel);

        JPanel YousefPicPanel = new JPanel();
        YousefPicPanel.setLayout(null);
        YousefPicPanel.setBounds(21, 56, 164, 147);
        YousefPanel.add(YousefPicPanel);

        JLabel YousefPic = new JLabel(""); //
        YousefPic.setPreferredSize(new Dimension(183, 275));
        YousefPic.setBounds(0, 0, 154, 147);
        YousefPicPanel.add(YousefPic);
        YousefPic.setIcon(changeIcon(150,160,"/Gui/images/YousefPic.jpeg"));

        JLabel YousefNameLabel = new JLabel("Yousef Alsakkaf");
        YousefNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        YousefNameLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        YousefNameLabel.setBounds(0, 11, 206, 37);
        YousefPanel.add(YousefNameLabel);

        scrollPaneYousef = new JScrollPane();
        scrollPaneYousef.setBounds(21, 214, 164, 207);
        YousefPanel.add(scrollPaneYousef);

        JTextArea YousefTextArea = new JTextArea();
        YousefTextArea.setWrapStyleWord(true);
        YousefTextArea.setText("I'm Yousef Alsakkaf, a Dubai native who attended The National Charity School. I enjoy learning, and I know a lot about several topics, including computer science and programming. I put a lot of effort into achieving my goals because I'm persistently curious and ready to learn. I like discovering new things, and I'm driven to keep getting better. I am confident that, with hard work and ambition, I can do everything I set my mind to.");
        YousefTextArea.setLineWrap(true);
        YousefTextArea.setEditable(false);
        YousefTextArea.setBackground(SystemColor.controlHighlight);
        scrollPaneYousef.setViewportView(YousefTextArea);

        JPanel AhmadPanel = new JPanel();
        AhmadPanel.setLayout(null);
        AhmadPanel.setBackground(new Color(153, 204, 255));
        AhmadPanel.setBounds(826, 196, 206, 432);
        AboutUsPane.add(AhmadPanel);

        JPanel AhmadPicPanel = new JPanel();
        AhmadPicPanel.setLayout(null);
        AhmadPicPanel.setBounds(21, 56, 164, 147);
        AhmadPanel.add(AhmadPicPanel);

        JLabel AhmadPic = new JLabel("");
        AhmadPic.setPreferredSize(new Dimension(183, 275));
        AhmadPic.setBounds(0, 0, 164, 147);
        AhmadPicPanel.add(AhmadPic);
        AhmadPic.setIcon(changeIcon(170,160,"/Gui/images/AhmadPic.jpeg"));

        JLabel AhmadNameLabel = new JLabel("Ahmad montaser");
        AhmadNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        AhmadNameLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        AhmadNameLabel.setBounds(0, 11, 206, 37);
        AhmadPanel.add(AhmadNameLabel);

        scrollPaneAhmad = new JScrollPane();
        scrollPaneAhmad.setBounds(21, 214, 164, 207);
        AhmadPanel.add(scrollPaneAhmad);

        JTextArea AhmadTextArea = new JTextArea();
        AhmadTextArea.setWrapStyleWord(true);
        AhmadTextArea.setText("Meet Ahamd montaser, a visionary entrepreneur with a passion for technology and innovation. With a keen eye for detail and a knack for problem-solving, Yousef has established himself as a prominent figure in the world of tech startups.\r\n\r\nYousef's journey began at a young age when he started tinkering with computers and coding. His insatiable curiosity and drive to push boundaries led him to create his first software application, captivating the attention of industry experts. Since then, Yousef has been on a mission to revolutionize the digital landscape.\r\n\r\nKnown for his exceptional leadership skills and ability to inspire others, Yousef has assembled a dynamic team of talented individuals who share his vision. Together, they have successfully launched several groundbreaking products that have garnered widespread acclaim.\r\n\r\nBeyond his professional endeavors, Yousef is also deeply committed to giving back to the community. He actively volunteers his time and expertise to mentor aspiring entrepreneurs and encourages young minds to explore the exciting possibilities of technology.\r\n\r\nWith his innovative mindset and determination, Yousef continues to spearhead groundbreaking projects that shape the future of the tech industry. Keep an eye out for this rising star, as he is destined to leave an indelible mark on the world of technology.");
        AhmadTextArea.setLineWrap(true);
        AhmadTextArea.setEditable(false);
        AhmadTextArea.setBackground(SystemColor.controlHighlight);
        scrollPaneAhmad.setViewportView(AhmadTextArea);

    }
    public ImageIcon changeIcon(int width, int height, String path) {
        ImageIcon icon = new ImageIcon(sideMenu.class.getResource(path));
        Image image = icon.getImage();
        int scaledWidth = width; // The new width you want
        int scaledHeight = height; // The new height you want
        Image scaledImage = image.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        return scaledIcon;
    }

}
