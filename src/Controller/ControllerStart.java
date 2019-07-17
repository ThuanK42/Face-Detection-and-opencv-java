package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import View.About_Screen;
import View.Start;
import View.Start_2;

public class ControllerStart extends JFrame implements ActionListener {
	private Start start;

	public ControllerStart(Start start) {
		super();
		this.start = start;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Exit")) {
			int exit = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn thoát.", null, JOptionPane.YES_NO_OPTION);
			if (exit == JOptionPane.YES_NO_OPTION) {
				System.exit(0);
			}

		}
		if (e.getActionCommand().equals("About")) {
			new About_Screen();
			start.dispose();

		} else {
			new Start_2();
			start.dispose();
		}
	}
}
