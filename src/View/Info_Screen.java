package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import Controller.ControllerInfo;

public class Info_Screen extends JFrame {
	JLabel lb_id, lb_CMND, lb_Birthday, lb_Phone, lb_Name, lb_Gender, lb_Address, lb_Job, lb_Avatar, lb_Author;
	public static JTextField tf_id, tf_CMND, tf_Phone, tf_Name, tf_Address, tf_Job, tf_Day, tf_Month, tf_Year, tf_Gender;
	JButton btn_Back, btn_Done, btn_Edit;
	JPanel pn_Avatar, pn_Btn, pn_South, pn_Info, pn_Author, pn_Birthday, pn_Main;
	ControllerInfo controllerInfo = new ControllerInfo(this);
	ControllerInfo controllerInfo2 = new ControllerInfo(this);
	public static int id, cmnd, phone;
	public static String name, day, month, year, gender, job, address, linkImg,str_id, str_cmnd, str_phone;


	public Info_Screen() {
		setTitle("Info");
		setSize(700, 600);
		setResizable(true);
		setLocationRelativeTo(null);
		add(pn_Main());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public JPanel pn_Avatar() {
		// Khai bao tieu de cua vien
		Border titleBorderAvatar = BorderFactory.createTitledBorder("Avatar");
		// Khai bao panel
		pn_Avatar = new JPanel(new FlowLayout());
		// Khai bao jlabel
		ImageIcon imageIcon = new ImageIcon(linkImg);
		Image image = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
		lb_Avatar = new JLabel(new ImageIcon(image));
		// Them tung thanh phan vao panel
		pn_Avatar.setBorder(titleBorderAvatar);
		pn_Avatar.add(lb_Avatar);
		lb_Avatar.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 3)); // tao do day duong vien
		pn_Avatar.setBackground(Color.cyan);// set mau cho panel

		return pn_Avatar;
	}

	public JPanel pn_Info() {

		// Khai bao Jlabel
		lb_id = new JLabel("ID: ");
		lb_CMND = new JLabel("CMND: ");
		lb_Birthday = new JLabel("Birthday: ");
		lb_Phone = new JLabel("Phone: ");
		lb_Name = new JLabel("Name: ");
		lb_Gender = new JLabel("Gender: ");
		lb_Address = new JLabel("Address: ");
		lb_Job = new JLabel("Job: ");

		// Khai bao textfield
		tf_id = new JTextField();
		tf_id.setEditable(false);
		str_id = String.valueOf(id);
		tf_id.setText(str_id);

		tf_CMND = new JTextField();
		tf_CMND.setEditable(false);
		str_cmnd = String.valueOf(cmnd);
		tf_CMND.setText(str_cmnd);

		tf_Day = new JTextField();
		tf_Day.setEditable(false);
		tf_Day.setText(day);

		tf_Month = new JTextField();
		tf_Month.setEditable(false);
		tf_Month.setText(month);

		tf_Year = new JTextField();
		tf_Year.setEditable(false);
		tf_Year.setText(year);

		tf_Phone = new JTextField();
		tf_Phone.setEditable(false);
		str_phone = String.valueOf(phone);
		tf_Phone.setText(str_phone);

		tf_Name = new JTextField();
		tf_Name.setEditable(false);
		tf_Name.setText(name);

		tf_Address = new JTextField();
		tf_Address.setEditable(false);
		tf_Address.setText(address);

		tf_Job = new JTextField();
		tf_Job.setEditable(false);
		tf_Job.setText(job);

		tf_Gender = new JTextField();
		tf_Gender.setEditable(false);
		tf_Gender.setText(gender);

		// Khai báo panel
		pn_Info = new JPanel(new GridLayout(8, 1));
		JPanel pn_Info2 = new JPanel(new GridLayout(8, 1));
		JPanel jPanel = new JPanel();

		pn_Birthday = new JPanel(new GridLayout(1, 3));
		// Them tung thanh phan vao panel

		pn_Birthday.add(tf_Day);
		pn_Birthday.add(tf_Month);
		pn_Birthday.add(tf_Year);

		pn_Info.add(lb_id);
		pn_Info2.add(lb_Gender);
		pn_Info.add(tf_id);
		pn_Info2.add(tf_Gender);
		pn_Info.add(lb_Name);
		pn_Info2.add(lb_Job);
		pn_Info.add(tf_Name);
		pn_Info2.add(tf_Job);
		pn_Info.add(lb_CMND);
		pn_Info2.add(lb_Address);
		pn_Info.add(tf_CMND);
		pn_Info2.add(tf_Address);
		pn_Info.add(lb_Birthday);
		pn_Info2.add(lb_Phone);
		pn_Info.add(pn_Birthday);
		pn_Info2.add(tf_Phone);

		pn_Info2.setBackground(Color.cyan);
		pn_Info.setBackground(Color.cyan);

		pn_Info.setPreferredSize(new Dimension(300, 200));
		pn_Info2.setPreferredSize(new Dimension(300, 200));
		jPanel.add(pn_Info, BorderLayout.WEST);
		jPanel.add(pn_Info2, BorderLayout.EAST);
		jPanel.setBorder(new TitledBorder("Info"));
		jPanel.setBackground(Color.cyan);

		return jPanel;
	}

	public JPanel pn_Btn() {
		// Khai bao pảnel
		pn_Btn = new JPanel(new GridLayout(1, 2));
		pn_South = new JPanel(new FlowLayout());
		// Khai bao button
		btn_Back = new JButton("BACK", new ImageIcon("src/Image/Icon/TrangChu.png"));
		btn_Back.addActionListener(controllerInfo);
		btn_Edit = new JButton("EDIT", new ImageIcon("src/Image/Icon/ChinhSua.png"));
		btn_Edit.addActionListener(controllerInfo2);
		// Them tung thanh phan vao panel
		pn_Btn.add(btn_Back);
		pn_Btn.add(btn_Edit);
		pn_South.add(pn_Btn);
		pn_South.setBackground(Color.cyan);

		return pn_South;
	}

	public JPanel pn_Main() {
		// Khai bao panel
		pn_Main = new JPanel();
		// Them tung thanh phan vao panel
		pn_Main.add(pn_Avatar());
		pn_Main.add(pn_Info());
		pn_Main.add(pn_Btn());
		pn_Main.setLayout(new BoxLayout(pn_Main, BoxLayout.Y_AXIS));
		return pn_Main;
	}

}
