package Controller;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.opencv.core.Core;
import org.opencv.videoio.VideoCapture;

import View.Search;
import View.Start_2;
import View.VideoCapture1;

public class ControllerStart_2 implements ActionListener {
	public Start_2 start_Screen;
	public Search camera;
	public ControllerChupHinh chupHinh;

	public ControllerStart_2(Start_2 start_Screen) {
		this.start_Screen = start_Screen;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Camera") {
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			try {
				for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
					if ("Nimbus".equals(info.getName())) {
						UIManager.setLookAndFeel(info.getClassName());
						break;
					}
				}
			} catch (ClassNotFoundException ex) {
				Logger.getLogger(VideoCapture.class.getName()).log(Level.SEVERE, null, ex);
			} catch (InstantiationException ex) {
				Logger.getLogger(VideoCapture.class.getName()).log(Level.SEVERE, null, ex);
			} catch (IllegalAccessException ex) {
				Logger.getLogger(VideoCapture.class.getName()).log(Level.SEVERE, null, ex);
			} catch (UnsupportedLookAndFeelException ex) {
				Logger.getLogger(VideoCapture.class.getName()).log(Level.SEVERE, null, ex);
			}
			// </editor-fold>

			/* Create and display the form */
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					new VideoCapture1().setVisible(true);
				}
			});
			// muc dich la lay dc anh qua ben kia
			start_Screen.dispose();

		}
		if (e.getActionCommand() == "Library") {
			int returnValue = start_Screen.jfc.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = start_Screen.jfc.getSelectedFile();
				camera.linkImage = "src/Image/Face/" + selectedFile.getName();
				start_Screen.dispose();
				camera = new Search();
			}
		}

	}

	public String getLink(String linkImage) {
		return linkImage;
	}
}
