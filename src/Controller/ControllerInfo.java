package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import View.Edit_Screen;
import View.Info_Screen;
import View.Start_2;

public class ControllerInfo extends JFrame implements ActionListener {
	Info_Screen info_Screen;
	Edit_Screen edit_Screen;

	public ControllerInfo(Info_Screen info_Screen) {
		this.info_Screen = info_Screen;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("BACK")) {
			info_Screen.dispose();
			new Start_2();
		}
		if (e.getActionCommand().equals("EDIT")) {
			edit_Screen.id = Integer.parseInt(info_Screen.tf_id.getText());
			edit_Screen.name = info_Screen.tf_Name.getText();
			edit_Screen.cmnd = Integer.parseInt(info_Screen.tf_CMND.getText());
			edit_Screen.day = info_Screen.tf_Day.getText();
			edit_Screen.month = info_Screen.tf_Month.getText();
			edit_Screen.year = info_Screen.tf_Year.getText();
			edit_Screen.gender = info_Screen.tf_Gender.getText();
			edit_Screen.job = info_Screen.tf_Job.getText();
			edit_Screen.address = info_Screen.tf_Address.getText();
			edit_Screen.phone = Integer.parseInt(info_Screen.tf_Phone.getText());
			edit_Screen.linkImg = info_Screen.linkImg;
			info_Screen.dispose();
			new Edit_Screen();
		}

	}

}
