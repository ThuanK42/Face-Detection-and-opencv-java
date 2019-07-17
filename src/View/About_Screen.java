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

public class About_Screen extends JFrame {
	private JPanel pn_Image, pn_Image2, pn_Center, pn_Btn, pn_label, pn_Main;
	private ControllerAbout ca = new ControllerAbout(1, this);
	private ControllerAbout ca2 = new ControllerAbout(2, this);
	private ControllerAbout ca3 = new ControllerAbout(1, this);
	private ControllerAbout ca4 = new ControllerAbout(2, this);
	private JButton btn_Back, btn_Next;
	private JLabel lb_Title;

	public About_Screen() {

		setTitle("About");
		setBackground(Color.YELLOW);
		setLayout(null);
		setSize(700, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		add(pn_Main());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);

	}

	public JPanel pn_Image() {

		Border titleBorderAvatar = BorderFactory.createTitledBorder("Member");
		pn_Image = new JPanel(new FlowLayout());
		JPanel pn_Info = new JPanel(new GridLayout(6, 1));
		JPanel pn = new JPanel(new GridLayout(1, 2));
		JLabel lb_Image = new JLabel(new ImageIcon("src/Image/About/Quang.jpg"));
		JLabel lb_Name = new JLabel("Name: Nguyen Van Quang");
		JLabel lb_Id = new JLabel("MSSV: 16130529");
		JLabel lb_Job = new JLabel("Job: ");
		JLabel lb_Fb = new JLabel("Facebook");
		JLabel lb_LinkFb = new JLabel("https://www.facebook.com/VNQuangCoi");
		lb_LinkFb.addMouseListener(ca);

		pn_Image.add(lb_Image);
		pn_Info.add(lb_Name);
		pn_Info.add(lb_Id);
		pn_Info.add(lb_Job);
		pn_Info.add(lb_Fb);
		pn_Info.add(lb_LinkFb);

		pn.add(pn_Image);
		pn.add(pn_Info);
		pn.setBorder(titleBorderAvatar);
		pn_Image.setBackground(Color.CYAN);
		pn_Info.setBackground(Color.CYAN);
		pn.setBackground(Color.CYAN);
		return pn;
	}

	public JPanel pn_Image2() {
		Border titleBorderAvatar = BorderFactory.createTitledBorder("Member");
		pn_Image2 = new JPanel(new FlowLayout());
		JPanel pn_Info = new JPanel(new GridLayout(6, 1));
		JPanel pn = new JPanel(new GridLayout(1, 2));

		JLabel lb_Image = new JLabel(new ImageIcon("src/Image/About/QuocThai.jpg"));
		JLabel lb_Name = new JLabel("Name: Quoc Thai");
		JLabel lb_Id = new JLabel("MSSV: 16130605");
		JLabel lb_Job = new JLabel("Job: ");
		JLabel lb_Fb = new JLabel("Facebook");
		JLabel lb_LinkFb = new JLabel("https://www.facebook.com/quocthai.it98");
		lb_LinkFb.addMouseListener(ca2);

		pn_Image2.add(lb_Image);
		pn_Info.add(lb_Name);
		pn_Info.add(lb_Id);
		pn_Info.add(lb_Job);
		pn_Info.add(lb_Fb);
		pn_Info.add(lb_LinkFb);
		pn.add(pn_Image2);
		pn.add(pn_Info);
		pn.setBorder(titleBorderAvatar);
		pn_Image2.setBackground(Color.CYAN);
		pn_Info.setBackground(Color.CYAN);
		pn.setBackground(Color.CYAN);

		return pn;
	}

	public JPanel pn_Main() {

		lb_Title = new JLabel("Member Information");
		lb_Title.setFont(new Font("KABEL", Font.BOLD, 18));
		lb_Title.setForeground(Color.red);

		pn_Center = new JPanel(new GridLayout(2, 1));
		pn_Center.add(pn_Image());
		pn_Center.add(pn_Image2());

		pn_Btn = new JPanel(new GridLayout(1, 2));
		btn_Back = new JButton(new ImageIcon("src/Image/Icon/TrangChu.png"));
		btn_Back.addActionListener(ca3);
		btn_Next = new JButton(new ImageIcon("src/Image/Icon/Next.png"));
		btn_Next.addActionListener(ca4);
		pn_Btn.add(btn_Back);
		pn_Btn.add(btn_Next);
		pn_Btn.setPreferredSize(new Dimension(200, 30));

		JPanel jPanel = new JPanel(new FlowLayout());
		jPanel.add(pn_Btn);
		jPanel.setBackground(Color.cyan);

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
