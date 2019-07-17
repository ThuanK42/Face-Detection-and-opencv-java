package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.ControllerStart;

public class Start extends JFrame {
	public JButton bt_Start, bt_About, bt_Exit;
	public JPanel pn_view, pn_Start;
	ImageIcon background;
	Image img, temp;
	JLabel back;

	public ControllerStart controllStart = new ControllerStart(this);

	public Start() {

		setSize(700, 600);
		setResizable(false);
		setLayout(null);
		setLocationRelativeTo(null);

		background = new ImageIcon("src/Image/Logo/AI.jpg");
		img = background.getImage();
		temp = img.getScaledInstance(700, 600, Image.SCALE_SMOOTH);
		background = new ImageIcon(temp);
		back = new JLabel(background);
		back.setLayout(null);
		back.setBounds(0, 0, 700, 600);

		add(panel_Main());
		add(back);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	public JPanel panel_Main() {
		pn_Start = new JPanel();

		bt_Start = new JButton("Start", new ImageIcon("src/Image/Icon/start.png"));
		bt_Start.setPreferredSize(new Dimension(120, 50));
		bt_Start.addActionListener(controllStart);
		//
		bt_About = new JButton("About", new ImageIcon("src/Image/Icon/about.png"));
		bt_About.setPreferredSize(new Dimension(120, 50));
		bt_About.addActionListener(controllStart);
		//
		bt_Exit = new JButton("Exit", new ImageIcon("src/Image/Icon/exit.png"));
		bt_Exit.setPreferredSize(new Dimension(120, 50));
		bt_Exit.addActionListener(controllStart);
		//
		pn_Start.add(bt_Start);
		pn_Start.add(bt_About);
		pn_Start.add(bt_Exit);

		pn_Start.setLayout(new GridLayout(3, 1, 50, 20));

		pn_Start.setBackground(Color.white);

		pn_Start.setLocation(280, 350);
		pn_Start.setSize(130, 180);
		pn_Start.setBackground(Color.BLACK);

		return pn_Start;

	}

}
