package Controller;

import static org.opencv.imgcodecs.Imgcodecs.imwrite;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

import View.Search;
import View.VideoCapture1;

public class ControllerChupHinh implements ActionListener {
	private VideoCapture1 video;
	public DeamonThread myThread = null;
	public VideoCapture webSource = null;
	public Mat frame = new Mat();
	public MatOfByte mem = new MatOfByte();
	public Graphics g, g2;
	public JFileChooser jFileChooser1 = new JFileChooser(new File("src/Image/Face"));
	public String path, filename;
	public Search camera;
	public FaceDectector dectector;

	public ControllerChupHinh(VideoCapture1 video) {
		this.video = video;
		webSource = new VideoCapture(0);
		myThread = new DeamonThread();
		Thread t = new Thread(myThread);
		myThread.runnable = true;
		t.start();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand() == "Chup ảnh") {
			myThread.runnable = false;
			webSource.release();
			Date date = new Date();
			String day = String.valueOf(date.getDate());
			String month = String.valueOf(date.getMonth());
			String year = String.valueOf(date.getYear());
			String second = String.valueOf(date.getSeconds());
			String minute = String.valueOf(date.getMinutes());
			String hourd = String.valueOf(date.getHours());
			filename = day + month + year + hourd + minute + second + ".png";
			System.out.println(filename);
//			jFileChooser1.setSelectedFile(new File(filename));
//			int returnval = jFileChooser1.showSaveDialog(null);
//
//			if (returnval == JFileChooser.APPROVE_OPTION) {
//				File file = jFileChooser1.getSelectedFile();
//				path = file.getPath();
//				imwrite(file.getPath(), frame);
//				System.out.println("Luu thanh cong");
//				camera.linkImage = path;
//				new Search();
//				video.dispose();
			File file = new File("src//Image//Face//");

			path = file.getPath() + "\\" + filename;
			System.out.println(path);
			FaceDectector face = new FaceDectector();
			Mat mat = face.Cut(frame);
			imwrite(path, mat);
			System.out.println("Luu thanh cong");
			camera.linkImage = path;
			new Search();
			video.dispose();

		} else {
			System.out.println("File access cancelled by user.");
		}
//		}
	}

	public String getPath() {
		return this.path;
	}

	class DeamonThread implements Runnable {

		protected volatile boolean runnable = false;

		@Override
		public void run() {
			synchronized (this) {
				while (runnable) {
					if (webSource.grab()) {
						try {
							FaceDectector face = new FaceDectector();
							webSource.retrieve(frame);

							frame = face.detectFace(frame);
							// định dạng ảnh khi vẽ lên jpanell
							Imgcodecs.imencode(".jpg", frame, mem);
							// ảnh của webcam là một array
							// dectector.detectFace(mem);
							Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));

							BufferedImage buff = (BufferedImage) im;
							g = video.jPanel1.getGraphics();

							// vẽ chuỗi ảnh của webcam vào jpanell
							if (g.drawImage(buff, 0, 0, buff.getWidth(), buff.getHeight(), null)) {
								if (runnable == false) {
									this.wait();
								}
							}
						} catch (Exception ex) {
							System.out.println("Error");
						}
					}
				}
			}
		}
	}
}
