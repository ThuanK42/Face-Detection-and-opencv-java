package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

import Controller.ControllerSearch;
import Controller.FaceDectector;

public class Search extends JFrame {
	private JPanel pn_Camera, pn_Bottom, pn_ButtonPanel;
	private JButton bt_Search, bt_backCamera;
	private Border panelBorder = BorderFactory.createEmptyBorder(310, 5, 0, 5);
	private ControllerSearch conCamera2 = new ControllerSearch(this);
	private ControllerSearch conCamera3 = new ControllerSearch(this);
	public static String linkImage;
	private JLabel lb_Camera;
	public static ImageIcon imageIcon;
	public static Image image;

	public Search() {
		add(panelMain());
		setSize(700, 600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Camera");
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
	}

	public JPanel panelMain() {
		JPanel pn_Main = new JPanel();
		pn_Main.add(panelCamera(), BorderLayout.CENTER);
		pn_Main.add(panelButton(), BorderLayout.EAST);
		return pn_Main;
	}

	public JPanel panelButton() {

		pn_ButtonPanel = new JPanel();

		pn_Bottom = new JPanel();// panel nam trong panel pn_ButtonPanel
		pn_Bottom.setBorder(new TitledBorder("Button"));

		bt_Search = new JButton("Search", new ImageIcon("src/Image/Icon/search.png"));
		bt_Search.addActionListener(conCamera2);
		bt_Search.setPreferredSize(new Dimension(120, 40));

		bt_backCamera = new JButton("Back", new ImageIcon("src/Image/Icon/TrangChu.png"));
		bt_backCamera.addActionListener(conCamera3);
		bt_backCamera.setPreferredSize(new Dimension(120, 40));

		pn_Bottom.add(bt_Search);
		pn_Bottom.add(bt_backCamera);
		pn_Bottom.setLayout(new GridLayout(2, 1, 0, 10));
		pn_ButtonPanel.add(pn_Bottom);
		pn_ButtonPanel.setBorder(panelBorder);
		return pn_ButtonPanel;
	}

	public JPanel panelCamera() {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		FaceDectector face = new FaceDectector();
		pn_Camera = new JPanel(new FlowLayout());
//		ImageIcon imageIcon = new ImageIcon(linkImage);
		Mat mat = Imgcodecs.imread(linkImage);
		Mat imgaeMat = face.Cut(mat);
		ImageIcon imageIcon = new ImageIcon(matToBufferedImage(imgaeMat));

		Image image = imageIcon.getImage().getScaledInstance(490, 510, Image.SCALE_DEFAULT);
		lb_Camera = new JLabel(new ImageIcon(image));
		pn_Camera.add(lb_Camera);
		pn_Camera.setBorder(new TitledBorder("Image"));
		pn_Camera.setPreferredSize(new Dimension(500, 550));

		return pn_Camera;

	}

	public static BufferedImage matToBufferedImage(Mat matrix) {
		try {
			MatOfByte mob = new MatOfByte();
			Imgcodecs.imencode(".jpg", matrix, mob);
			byte ba[] = mob.toArray();
			BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(ba));
			return bufferedImage;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		new Search();
	}
}
