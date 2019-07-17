package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import Connection.ConnectionMySQL;
import Model.People;

public class ProcessPerson {
	static Connection connection;
//	static Statement statement;
	static PreparedStatement ps;
	static String sql;
	ResultSet rs;

	public static List getInformation() {
		List<People> list = new ArrayList<People>();
		try {
			connection = ConnectionMySQL.getMySQLConnection();
			sql = "SELECT * FROM people";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("user_id");
				String name = rs.getString("user_name");
				int cmnd = rs.getInt("cmnd");
				String date = rs.getString("birthday");
				String gender = rs.getString("gender");
				String job = rs.getString("job");
				String address = rs.getString("address");
				int phone = rs.getInt("phone");
				String avatar = rs.getString("image");
				People people = new People(id, name, cmnd, date, gender, job, address, phone, avatar);
				list.add(people);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public static void addPeople(int id, String name, String gender, String day, int cmnd, String address, String job,
			int phone, String img) {
		try {
			connection = ConnectionMySQL.getMySQLConnection();
			sql = "INSERT INTO people VALUES(?,?,?,?,?,?,?,?,?)";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, gender);
			ps.setString(4, day);
			ps.setInt(5, cmnd);
			ps.setString(6, address);
			ps.setString(7, job);
			ps.setInt(8, phone);
			ps.setString(9, img);
			int rs = ps.executeUpdate();
			System.out.println("Thuc hien thanh cong add People");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void editInfomation(int id, String name, String gender, String day, int cmnd, String address,
			String job, int phone, String img) {
		try {
			connection = ConnectionMySQL.getMySQLConnection();
			sql = " update people set user_name = ?,gender=?,birthday=?,cmnd=?,address=?,job=?,phone=?,image=? where user_id=?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, gender);
			ps.setString(3, day);
			ps.setInt(4, cmnd);
			ps.setString(5, address);
			ps.setString(6, job);
			ps.setInt(7, phone);
			ps.setString(8, img);
			ps.setInt(9, id);
			int rs = ps.executeUpdate();
			System.out.println("Thuc hien thanh cong chinh sua thong tin");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void deletePeople(int id) {
		try {
			connection = ConnectionMySQL.getMySQLConnection();
			sql = " delete from people where user_id=?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			int rs = ps.executeUpdate();
			System.out.println("Thuc hien thanh cong xoa thong tin");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
editInfomation(2, "Le Van Thuan", "Nam", "1998-03-23", 225913049, "Thu Duc", "Sinh Vien", 983172229, "src/Image/About/Thuan.jpg");

	}
}
