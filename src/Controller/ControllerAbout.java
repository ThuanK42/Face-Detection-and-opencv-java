package Controller;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import View.About_Screen;
import View.About_Screen2;
import View.Start;

public class ControllerAbout implements MouseListener, ActionListener {
	public int number;
	About_Screen about;
	About_Screen2 about2;

	public ControllerAbout(int a, About_Screen about) {
		this.number = a;
		this.about = about;
	}

	public ControllerAbout(int a, About_Screen2 ab2) {
		this.number = a;
		this.about2 = ab2;
	}

	public void mouseClicked(MouseEvent e) {
		Desktop desktop = Desktop.getDesktop();
		switch (number) {
		case 1:
			try {
				desktop.browse(new URI("https://www.facebook.com/VNQuangCoi"));
			} catch (IOException ex) {
				ex.printStackTrace();
			} catch (URISyntaxException ex) {
				ex.printStackTrace();
			}
			break;
		case 2:
			try {
				desktop.browse(new URI("https://www.facebook.com/quocthai.it98"));
			} catch (IOException ex) {
				ex.printStackTrace();
			} catch (URISyntaxException ex) {
				ex.printStackTrace();
			}

			break;
		case 3:

			try {
				desktop.browse(new URI("https://www.facebook.com/hieu.dev.37"));
			} catch (IOException ex) {
				ex.printStackTrace();
			} catch (URISyntaxException ex) {
				ex.printStackTrace();
			}

			break;
		case 4:
			try {
				desktop.browse(new URI("https://www.facebook.com/LeVanThuanK42"));
			} catch (IOException ex) {
				ex.printStackTrace();
			} catch (URISyntaxException ex) {
				ex.printStackTrace();
			}

			break;

		default:
			break;
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (number) {
		case 1:
			about.dispose();
			new Start();
			break;
		case 2:
			about.dispose();
			new About_Screen2();
			break;
		case 3:
			about2.dispose();
			new About_Screen();
			break;

		default:
			break;
		}

	}
}
