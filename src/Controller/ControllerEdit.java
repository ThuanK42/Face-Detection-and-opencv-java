package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import DAO.ProcessPerson;
import View.Edit_Screen;
import View.Info_Screen;
import View.Start_2;

public class ControllerEdit implements ActionListener {

	Edit_Screen edit_Screen;
	Info_Screen info_Screen;
	ProcessPerson pp = new ProcessPerson();

	public ControllerEdit(Edit_Screen edit_Screen) {

		this.edit_Screen = edit_Screen;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("BACK")) {
			edit_Screen.dispose();
			new Start_2();
		}
		if (e.getActionCommand().contentEquals("DONE")) {
			if (checkNull() == false) {
				
				info_Screen.id = Integer.parseInt(edit_Screen.tf_id.getText());
				info_Screen.name = edit_Screen.tf_Name.getText();
				info_Screen.cmnd = Integer.parseInt(edit_Screen.tf_CMND.getText());
				info_Screen.day = edit_Screen.tf_Day.getText();
				info_Screen.month = edit_Screen.tf_Month.getText();
				info_Screen.year = edit_Screen.tf_Year.getText();
				info_Screen.gender = edit_Screen.tf_Gender.getText();
				info_Screen.job = edit_Screen.tf_Job.getText();
				info_Screen.address = edit_Screen.tf_Address.getText();
				info_Screen.phone = Integer.parseInt(edit_Screen.tf_Phone.getText());
				info_Screen.linkImg = edit_Screen.linkImg;
				String day = info_Screen.year + "-" + info_Screen.month + "-" + info_Screen.day;

				pp.editInfomation(info_Screen.id, info_Screen.name, info_Screen.gender, day, info_Screen.cmnd,
						info_Screen.address, info_Screen.job, info_Screen.phone, info_Screen.linkImg);
				edit_Screen.dispose();
				new Info_Screen();
			}

		}
	}

	public boolean checkNull() { // Cac o phai duoc dien
		Boolean result = false;
		String id = edit_Screen.tf_id.getText();
		String name = edit_Screen.tf_Name.getText();
		String cmnd = edit_Screen.tf_CMND.getText();
		String day = edit_Screen.tf_Day.getText();
		String month = edit_Screen.tf_Month.getText();
		String year = edit_Screen.tf_Year.getText();
		String gender = edit_Screen.tf_Gender.getText();
		String job = edit_Screen.tf_Job.getText();
		String address = edit_Screen.tf_Address.getText();
		String phone = edit_Screen.tf_Phone.getText();
		if (id.equalsIgnoreCase("") || name.equalsIgnoreCase("") || cmnd.equalsIgnoreCase("")
				|| day.equalsIgnoreCase("") || month.equalsIgnoreCase("") || year.equalsIgnoreCase("")
				|| gender.equalsIgnoreCase("") || job.equalsIgnoreCase("") || address.equalsIgnoreCase("")
				|| phone.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "");
			result = true;
			clear();
		}
		return result;
	}

	private void clear() { // set toan bo cac o dien ve null het
		edit_Screen.tf_id.setText("");
		// tf_id.requestFocus(); // nhay ve o id
		edit_Screen.tf_Name.setText("");
		edit_Screen.tf_Name.requestFocus();
		edit_Screen.tf_CMND.setText("");
		edit_Screen.tf_Day.setText("");
		edit_Screen.tf_Month.setText("");
		edit_Screen.tf_Year.setText("");
		edit_Screen.tf_Gender.setText("");
		edit_Screen.tf_Job.setText("");
		edit_Screen.tf_Address.setText("");
		edit_Screen.tf_Phone.setText("");
	}

// Chuoi nhap vao co dang 09xxx do dai 10 so
	// $ ket thuc 1 chuoi
	// ^ bat dau 1 chuoi
	public boolean checkNumberPhone() {
		String number = edit_Screen.tf_Phone.getText();
		Pattern pattern = Pattern.compile("^[0-9]*$");// So dien thoa
		Matcher matcher = pattern.matcher(number);
		Boolean result;
		if (!matcher.matches()) {
			JOptionPane.showMessageDialog(null, "");
			result = false;
		} else {
			if (number.length() == 10) {
				result = true;
			} else {

				result = false;
			}
		}
		return result;
	}

	public boolean isNumeric() { // kiem tra chuoi nhap co phai la so hay khong
		String str = edit_Screen.tf_CMND.getText();
		String str2 = edit_Screen.tf_id.getText();
		try {
			Integer.parseInt(str);
			Integer.parseInt(str2);
			return true;
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Xin vui long nhap cmnd du 9 so hoac nhap dung dinh dang so id!");
			return false;
		}
	}

//Kiem tra du lieu nhap vao co phai chuoi ko
	public boolean isString(String s) {
		for (int i = 0; i < s.length(); i++)
			if (Character.isDigit(s.charAt(i)) == false)
				return true;

		return false;
	}

//Kiem tra ngay nhap vao
	public boolean checkDate() {
		String date = edit_Screen.tf_Day.getText() + "/" + edit_Screen.tf_Month.getText() + "/"
				+ edit_Screen.tf_Year.getText();

		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			sdf.setLenient(false);
			try {
				sdf.parse(date);
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null, "Dinh dang ngay sinh sai. Nhap lai!");
				return false;
			}
			return true;
		}

	}

//	public boolean checkFailed() {
//		Boolean result = true;
//		if (isString(edit_Screen.tf_Name.getText()) == false || isNumeric() == false || checkDate() == false
//				|| checkNumberPhone() == false || checkNull() == false
//				|| isString(edit_Screen.tf_Address.getText()) == false
//				|| isString(edit_Screen.tf_Job.getText()) == false
//				|| isString(edit_Screen.tf_Gender.getText()) == false) {
//			clear();
//			result = false;
//		}
//		return result;
//	}

}
