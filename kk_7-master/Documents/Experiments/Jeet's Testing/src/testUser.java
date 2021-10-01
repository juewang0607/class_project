
public class testUser {
	private String fName;
	private String lName;
	private String email;
	private String password;
	private String apartment;
	private int floor;
	private int room;
	private userDatabase userList;
	
	public testUser(String first, String last, String e, String pass, String apart, int f, int r) {
		fName = first;
		lName = last;
		email = e;
		password = pass;
		apartment = apart;
		floor = f;
		room = r;
	}
	
	//empty user constructor
	public testUser() {
		fName = null;
		lName = null;
		email = null;
		password = null;
		apartment = null;
		floor = 0;
		room = 0;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getApartment() {
		return apartment;
	}
	
	public String getPassword() {
		return password;
	}

	public void setApartment(String apartment) {
		this.apartment = apartment;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getRoom() {
		return room;
	}

	public void setRoom(int room) {
		this.room = room;
	}
	
	public void setPassword(String pass) {
		this.password = pass;
	}
	
}
