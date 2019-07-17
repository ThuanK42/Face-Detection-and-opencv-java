package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import Controller.ControllerStart_2;

public class Start_2 extends JFrame {
	JPanel pn_Main, pn_Btn, pn_Avatar, pn_Btn_2;
	JButton btn_Camera, btn_Library;
	JLabel lb_Avatar, lb_Color, lb_Color2;
	ControllerStart_2 css = new ControllerStart_2(this);
	ControllerStart_2 css2 = new ControllerStart_2(this);
	public JFileChooser jfc;

	public Start_2() {

		add(pn_Main());
		this.getContentPane().setBackground(Color.WHITE);
		setTitle("Start");
		setSize(700, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public JPanel pn_Btn() {
		jfc = new JFileChooser(new File("src/Image/Face"));

		pn_Btn = new JPanel(new GridLayout(2, 1, 0, 10));
		pn_Btn.setBorder(new TitledBorder("Source Image"));

		Border panelBorder = BorderFactory.createEmptyBorder(310, 5, 0, 5);

		btn_Camera = new JButton("Camera", new ImageIcon("src/Image/Icon/Camera.png"));
		btn_Camera.setPreferredSize(new Dimension(120, 40));
		btn_Camera.addActionListener(css);

		btn_Library = new JButton("Library", new ImageIcon("src/Image/Icon/folder.png"));
		btn_Library.setPreferredSize(new Dimension(120, 40));
		btn_Library.addActionListener(css2);
		pn_Btn.add(btn_Camera);
		pn_Btn.add(btn_Library);
		pn_Btn.setBackground(Color.white);

		pn_Btn_2 = new JPanel();
		pn_Btn_2.add(pn_Btn);
		pn_Btn_2.setBorder(panelBorder);
		pn_Btn_2.setBackground(Color.white);
		return pn_Btn_2;
	}

	public JPanel pn_Avatar() {
		pn_Avatar = new JPanel(new FlowLayout());
		lb_Avatar = new JLabel(new ImageIcon("src/Image/Logo/faceRecognition.png"));
		pn_Avatar.add(lb_Avatar);
		pn_Avatar.setBorder(new TitledBorder("Logo"));
		pn_Avatar.setPreferredSize(new Dimension(500, 500));
		pn_Avatar.setBackground(Color.white);
		return pn_Avatar;
	}

	public JPanel pn_Main() {
		pn_Main = new JPanel();
		pn_Main.add(pn_Avatar(), BorderLayout.WEST);
		pn_Main.add(pn_Btn(), BorderLayout.EAST);
		pn_Main.setBackground(Color.white);
		return pn_Main;
	}
}
