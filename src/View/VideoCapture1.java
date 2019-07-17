package View;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import org.opencv.core.Core;
import Controller.ControllerChupHinh;
import View.Bt_Chup;

public class VideoCapture1 extends JFrame {
	public JButton jButton1;
	public JButton jButton2;
	public JButton jButton3;
	public JPanel jPanel1;
	public Bt_Chup bt_Chup;
	public ControllerChupHinh controll = new ControllerChupHinh(this);

	public VideoCapture1() {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		setTitle("Chup anh webcam");
		initComponents();

		setSize(700, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	}

	@SuppressWarnings("unchecked")
	private void initComponents() {
		bt_show();
		pn_camera();

	}

	public void pn_camera() {
		jPanel1 = new JPanel();
		GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(
				jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 550, Short.MAX_VALUE));
		jPanel1Layout.setVerticalGroup(
				jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 520, Short.MAX_VALUE));

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup().addContainerGap()
								.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(
										bt_Chup, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addContainerGap(22, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(jPanel1,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(layout.createSequentialGroup()
										// dinh vi tri cua nut chup
										.addGap(150, 150, 150).addComponent(bt_Chup, GroupLayout.PREFERRED_SIZE, 85,
												GroupLayout.PREFERRED_SIZE)))
						.addGap(13, 13, 13)));
		pack();

	}

	public void bt_show() {
		bt_Chup = new Bt_Chup("Chup ảnh");
		bt_Chup.addActionListener(controll);
	}

}
