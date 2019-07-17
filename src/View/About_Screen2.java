package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Controller.ControllerAbout;

public class About_Screen2 extends JFrame {
	JPanel pn_Image, pn_Image2, pn_Image3, pn_Image4, pn_Center, pn_Btn, pn_label, pn_Main;
	ControllerAbout ca = new ControllerAbout(3, this);
	ControllerAbout ca2 = new ControllerAbout(4, this);
	ControllerAbout ca3 = new ControllerAbout(3, this);

	JButton btn_Back;
	JLabel lb_Title;
	ImageIcon background;
	Image img, temp;
	JLabel back;

	public About_Screen2() {

		setBackground(Color.YELLOW);
		setLayout(null);
		setSize(700, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		add(pn_Main());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);

	}

	public JPanel pn_Image3() {
		Border titleBorderAvatar = BorderFactory.createTitledBorder("Member");
		pn_Image3 = new JPanel(new FlowLayout());
		JPanel pn_Info = new JPanel(new GridLayout(6, 1));
		JPanel pn = new JPanel(new GridLayout(1, 2));

		JLabel lb_Image = new JLabel(new ImageIcon("src/Image/About/Hieu.jpg"));
		JLabel lb_Name = new JLabel("Name: Nguyen Hieu");
		JLabel lb_Id = new JLabel("MSSV: ");
		JLabel lb_Job = new JLabel("Job: ");
		JLabel lb_Fb = new JLabel("Facebook");
		JLabel lb_LinkFb = new JLabel("https://www.facebook.com/hieu.dev.37");
		lb_LinkFb.addMouseListener(ca);
		pn_Image3.add(lb_Image);
		pn_Info.add(lb_Name);
		pn_Info.add(lb_Id);
		pn_Info.add(lb_Job);
		pn_Info.add(lb_Fb);
		pn_Info.add(lb_LinkFb);

		pn.add(pn_Image3);
		pn.add(pn_Info);
		pn.setBorder(titleBorderAvatar);
		pn_Image3.setBackground(Color.CYAN);
		pn_Info.setBackground(Color.CYAN);
		pn.setBackground(Color.CYAN);

		return pn;
	}

	public JPanel pn_Image4() {
		Border titleBorderAvatar = BorderFactory.createTitledBorder("Member");
		pn_Image4 = new JPanel(new FlowLayout());
		JPanel pn_Info = new JPanel(new GridLayout(6, 1));
		JPanel pn = new JPanel(new GridLayout(1, 2));

		JLabel lb_Image = new JLabel(new ImageIcon("src/Image/About/Thuan.jpg"));
		JLabel lb_Name = new JLabel("Name: Le Van Thuan");
		JLabel lb_Id = new JLabel("MSSV: 16130606");
		JLabel lb_Job = new JLabel("Job: ");
		JLabel lb_Fb = new JLabel("Facebook");
		JLabel lb_LinkFb = new JLabel("https://www.facebook.com/LeVanThuanK42");
		lb_LinkFb.addMouseListener(ca2);

		pn_Image4.add(lb_Image);
		pn_Info.add(lb_Name);
		pn_Info.add(lb_Id);
		pn_Info.add(lb_Job);
		pn_Info.add(lb_Fb);
		pn_Info.add(lb_LinkFb);

		pn.add(pn_Image4);
		pn.add(pn_Info);
		pn.setBorder(titleBorderAvatar);
		pn_Image4.setBackground(Color.CYAN);
		pn_Info.setBackground(Color.CYAN);
		pn.setBackground(Color.CYAN);

		return pn;
	}

	public JPanel pn_Main() {

		lb_Title = new JLabel("Member Information");
		lb_Title.setFont(new Font("KABEL", Font.BOLD, 18));
		lb_Title.setForeground(Color.red);

		JPanel jPanel = new JPanel(new FlowLayout());
		pn_Btn = new JPanel(new GridLayout(1, 2));
		jPanel.add(pn_Btn);
		jPanel.setBackground(Color.cyan);

		pn_Center = new JPanel(new GridLayout(2, 1));
		pn_Center.add(pn_Image3());
		pn_Center.add(pn_Image4());

		btn_Back = new JButton(new ImageIcon("src/Image/Icon/Back.jpg"));
		btn_Back.addActionListener(ca3);
		pn_Btn.add(btn_Back);
		pn_Btn.setPreferredSize(new Dimension(200, 30));

		pn_label = new JPanel(new FlowLayout());
		pn_label.add(lb_Title);
		pn_label.setBackground(Color.cyan);

		pn_Main = new JPanel(new BorderLayout());
		pn_Main.setSize(600, 480);
		pn_Main.setLocation(40, 40);

		pn_Main.add(pn_label, BorderLayout.NORTH);
		pn_Main.add(pn_Center, BorderLayout.CENTER);
		pn_Main.add(jPanel, BorderLayout.SOUTH);

		return pn_Main;

	}

}
