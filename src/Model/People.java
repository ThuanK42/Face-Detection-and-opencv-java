package Model;

public class People {
	String name, gender, address, job, day, avatar;
	int id, cmnd, phone;

	public People(int id, String name, int cmnd, String birthday, String gender, String job, String address, int phone,
			String avatar) {
		this.id = id;
		this.name = name;
		this.cmnd = cmnd;
		this.day = birthday;
		this.gender = gender;
		this.job = job;
		this.address = address;
		this.phone = phone;
		this.avatar = avatar;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCmnd() {
		return cmnd;
	}

	public void setCmnd(int cmnd) {
		this.cmnd = cmnd;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Override
	public String toString() {
		return "People [id=" + id + ", name=" + name + ", cmnd=" + cmnd + ", gender=" + gender + ", day=" + day
				+ ", address=" + address + ", job=" + job + ", phone=" + phone + ", avatar=" + avatar + "]";
	}
}