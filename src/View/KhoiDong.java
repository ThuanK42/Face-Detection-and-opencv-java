package View;

import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import Controller.ControllerKhoiDong;

public class KhoiDong extends JFrame {
	private JProgressBar pr_bar;
	private ControllerKhoiDong run = new ControllerKhoiDong(this);
	ImageIcon background;
	Image img, temp;
	JLabel back;

	public KhoiDong() {
		
		setSize(700, 600);
		setResizable(false);
		setLayout(null);
		setLocationRelativeTo(null);

		background = new ImageIcon("src/Image/Logo/run.png");
		img = background.getImage();
		temp = img.getScaledInstance(700, 600, Image.SCALE_SMOOTH);
		background = new ImageIcon(temp);
		back = new JLabel(background);
		back.setLayout(null);
		back.setBounds(0, 0, 700, 600);
		
		add(pn_progreeBar());
		add(back);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		run.iterate();
	}


	public JProgressBar pn_progreeBar() {

		pr_bar = new JProgressBar(0, 500);
		pr_bar.setSize(500, 20);
		pr_bar.setBackground(Color.black);
		pr_bar.setValue(0);
		pr_bar.setStringPainted(true);
		pr_bar.setLocation(100, 500);
		return pr_bar;
	}

	public JProgressBar getJb() {
		return this.pr_bar;
	}

}
