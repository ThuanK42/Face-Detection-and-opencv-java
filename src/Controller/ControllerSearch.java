package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import javax.net.ssl.SSLException;
import javax.swing.JOptionPane;

import DAO.ProcessPerson;
import Model.People;
import View.Search;
import View.AddFace_Screen;
import View.Info_Screen;
import View.Start_2;

public class ControllerSearch implements ActionListener {
	private Search camera;
	public static Info_Screen info_Screen;
	public static AddFace_Screen afc;
	int number;
	public static ArrayList<People> list = new ArrayList<People>();
	public static ArrayList<People> data = new ArrayList<People>();
	String birthday;
	static ProcessPerson person = new ProcessPerson();

	public ControllerSearch(Search camera) {
		this.camera = camera;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Search")) {
			database();
			File file = new File(camera.linkImage);
			byte[] buff = getBytesFromFile(file);
			HashMap<String, String> map = new HashMap<>();
			HashMap<String, byte[]> byteMap = new HashMap<>();
			int count = 0;
			for (int i = 0; i < list.size(); i++) {
				File file2 = new File(list.get(i).getAvatar());
				byte[] buff2 = getBytesFromFile(file2);
				String url = "https://api-us.faceplusplus.com/facepp/v3/compare";
				map.put("api_key", "QgfDmiwJMPjifntrhdgLiBVlN7Y2qJy9");
				map.put("api_secret", "DjcFMs4TNDDRCkPTuy93SLfUjLg6S5zG");
				byteMap.put("image_file1", buff);
				byteMap.put("image_file2", buff2);
				try {
					byte[] bacd = post(url, map, byteMap);
					String str = new String(bacd);
					// System.out.println(str);
					String[] strTest = str.split(",");
					String rs = strTest[14].trim();
					String rs2 = rs.substring(14, rs.length());
					Double conf = Double.parseDouble(rs2);
					System.out.println(conf);
					if (conf > 80) {
						camera.dispose();
						info_Screen.id = list.get(i).getId();
						info_Screen.name = list.get(i).getName();
						info_Screen.cmnd = list.get(i).getCmnd();
						birthday = list.get(i).getDay();
						formatDay(birthday);
						info_Screen.gender = list.get(i).getGender();
						info_Screen.job = list.get(i).getJob();
						info_Screen.address = list.get(i).getAddress();
						info_Screen.phone = list.get(i).getPhone();
						info_Screen.linkImg = list.get(i).getAvatar();
						JOptionPane.showMessageDialog(null, "Tìm kiếm thành công");
						info_Screen = new Info_Screen();
						count++;
						break;
					} else {
						count++;
						if (count == list.size()) {
							int n = JOptionPane.showConfirmDialog(null,
									"Khong tim thay du lieu khuon mat trong database. Ban co muon them khong ?",
									"Alert", JOptionPane.YES_NO_OPTION);
							if (n == JOptionPane.YES_OPTION) {
								afc.id = list.size()+1;
								afc.linkImg = camera.linkImage;
								camera.dispose();
								new AddFace_Screen();
							}
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			deleteDatabase();
		}
		if (e.getActionCommand().equals("Back")) {
			camera.dispose();
			new Start_2();
		}
	}

	private final static int CONNECT_TIME_OUT = 30000;
	private final static int READ_OUT_TIME = 50000;
	private static String boundaryString = getBoundary();

	protected static byte[] post(String url, HashMap<String, String> map, HashMap<String, byte[]> fileMap)
			throws Exception {
		HttpURLConnection conne;
		URL url1 = new URL(url);
		conne = (HttpURLConnection) url1.openConnection();
		conne.setDoOutput(true);
		conne.setUseCaches(false);
		conne.setRequestMethod("POST");
		conne.setConnectTimeout(CONNECT_TIME_OUT);
		conne.setReadTimeout(READ_OUT_TIME);
		conne.setRequestProperty("accept", "*/*");
		conne.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundaryString);
		conne.setRequestProperty("connection", "Keep-Alive");
		conne.setRequestProperty("user-agent", "Mozilla/4.0 (compatible;MSIE 6.0;Windows NT 5.1;SV1)");
		DataOutputStream obos = new DataOutputStream(conne.getOutputStream());
		Iterator iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry) iter.next();
			String key = entry.getKey();
			String value = entry.getValue();
			obos.writeBytes("--" + boundaryString + "\r\n");
			obos.writeBytes("Content-Disposition: form-data; name=\"" + key + "\"\r\n");
			obos.writeBytes("\r\n");
			obos.writeBytes(value + "\r\n");
		}
		if (fileMap != null && fileMap.size() > 0) {
			Iterator fileIter = fileMap.entrySet().iterator();
			while (fileIter.hasNext()) {
				Map.Entry<String, byte[]> fileEntry = (Map.Entry<String, byte[]>) fileIter.next();
				obos.writeBytes("--" + boundaryString + "\r\n");
				obos.writeBytes("Content-Disposition: form-data; name=\"" + fileEntry.getKey() + "\"; filename=\""
						+ encode(" ") + "\"\r\n");
				obos.writeBytes("\r\n");
				obos.write(fileEntry.getValue());
				obos.writeBytes("\r\n");
			}
		}
		obos.writeBytes("--" + boundaryString + "--" + "\r\n");
		obos.writeBytes("\r\n");
		obos.flush();
		obos.close();
		InputStream ins = null;
		int code = conne.getResponseCode();
		try {
			if (code == 200) {
				ins = conne.getInputStream();
			} else {
				ins = conne.getErrorStream();
			}
		} catch (SSLException e) {
			e.printStackTrace();
			return new byte[0];
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buff = new byte[4096];
		int len;
		while ((len = ins.read(buff)) != -1) {
			baos.write(buff, 0, len);
		}
		byte[] bytes = baos.toByteArray();
		ins.close();
		return bytes;
	}

	private static String getBoundary() {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 32; ++i) {
			sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-".charAt(
					random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_".length())));
		}
		return sb.toString();
	}

	private static String encode(String value) throws Exception {
		return URLEncoder.encode(value, "UTF-8");
	}

	public static byte[] getBytesFromFile(File f) {
		if (f == null) {
			return null;
		}
		try {
			FileInputStream stream = new FileInputStream(f);
			ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = stream.read(b)) != -1)
				out.write(b, 0, n);
			stream.close();
			out.close();
			return out.toByteArray();
		} catch (IOException e) {
		}
		return null;
	}

	public static ArrayList<People> database() {
		return list = (ArrayList<People>) person.getInformation();

	}

	public static ArrayList<People> deleteDatabase() {
		list.removeAll(list);
		return list;
	}

	public static void formatDay(String str) {
		String[] str2 = str.split("-");
		for (int i = 0; i < str2.length; i++) {
			info_Screen.day = str2[2];
			info_Screen.month = str2[1];
			info_Screen.year = str2[0];
		}
	}

	
}
