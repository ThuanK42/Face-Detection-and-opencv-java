package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

import DAO.ProcessPerson;
import View.AddFace_Screen;
import View.Info_Screen;
import View.Start_2;

public class ControllerAddFace implements ActionListener {
	AddFace_Screen afc;
	ProcessPerson pp = new ProcessPerson();
	Info_Screen info_Screen;

	public ControllerAddFace(AddFace_Screen afc) {

		this.afc = afc;
	}

	@Override

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("BACK")) {
			afc.dispose();
			new Start_2();
		}
		if (e.getActionCommand().contentEquals("DONE")) {
			if (checkNull() == false) {
				// afc.dispose();
				info_Screen.id = Integer.parseInt(afc.tf_id.getText());
				info_Screen.name  = afc.tf_Name.getText();
				info_Screen.cmnd = Integer.parseInt(afc.tf_CMND.getText());
				info_Screen.day = afc.tf_Day.getText();
				info_Screen.month = afc.tf_Month.getText();
				info_Screen.year = afc.tf_Year.getText();
				info_Screen.gender = afc.tf_Gender.getText();
				info_Screen.job  = afc.tf_Job.getText();
				info_Screen.address = afc.tf_Address.getText();
				info_Screen.phone = Integer.parseInt(afc.tf_Phone.getText().trim());
				info_Screen.linkImg = afc.linkImg;
				String birthday = info_Screen.year + "-" + info_Screen.month + "-" + info_Screen.day;
				pp.addPeople(info_Screen.id, info_Screen.name, info_Screen.gender, birthday, info_Screen.cmnd,
						info_Screen.address, info_Screen.job, info_Screen.phone, afc.linkImg);
				afc.dispose();
				new Info_Screen();
			}
		}
	}

	public boolean checkNull() { // Cac o phai duoc dien
		Boolean result = false;
		String id = afc.tf_id.getText();
		String name = afc.tf_Name.getText();
		String cmnd = afc.tf_CMND.getText();
		String day = afc.tf_Day.getText();
		String month = afc.tf_Month.getText();
		String year = afc.tf_Year.getText();
		String gender = afc.tf_Gender.getText();
		String job = afc.tf_Job.getText();
		String address = afc.tf_Address.getText();
		String phone = afc.tf_Phone.getText();
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
		afc.tf_id.setText("");
		// tf_id.requestFocus(); // nhay ve o id
		afc.tf_Name.setText("");
		afc.tf_Name.requestFocus();
		afc.tf_CMND.setText("");
		afc.tf_Day.setText("");
		afc.tf_Month.setText("");
		afc.tf_Year.setText("");
		afc.tf_Gender.setText("");
		afc.tf_Job.setText("");
		afc.tf_Address.setText("");
		afc.tf_Phone.setText("");
	}

	// Chuoi nhap vao co dang 09xxx do dai 10 so
	// $ ket thuc 1 chuoi
	// ^ bat dau 1 chuoi
	public boolean checkNumberPhone() {
		String number = afc.tf_Phone.getText();
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
		String str = afc.tf_CMND.getText();
		String str2 = afc.tf_id.getText();
		try {
			Integer.parseInt(str);
			Integer.parseInt(str2);
			return true;
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Xin vui long nhap cmnd du 9 so hoac nhap dung dinh dang so id!");
			return false;
		}
	}

	// Kiem tra du lieu nhap vao co phai chuoi ko
	public boolean isString(String s) {
		for (int i = 0; i < s.length(); i++)
			if (Character.isDigit(s.charAt(i)) == false)
				return true;

		return false;
	}

	// Kiem tra ngay nhap vao
	public boolean checkDate() {
		String date = afc.tf_Day.getText() + "/" + afc.tf_Month.getText() + "/" + afc.tf_Year.getText();

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

}
