package neededClasses;

public class User {

	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String phoneNumber;
	private String building;
	private String floor;
	private String unit;
	
	
	public User(String FirstName, String LastName, String Username, String Password, String PhoneNumber, String Building, String Floor, String Unit)
	{
		firstName = FirstName;
		lastName = LastName;
		username = Username;
		password = Password;
		phoneNumber = PhoneNumber;
		building = Building;
		floor = Floor;
		unit = Unit;
	}
	
	
	public void setFirstName(String setFirstName)
	{
		firstName = setFirstName;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	
	public void setLastName(String setLastName)
	{
		lastName = setLastName;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public void setUsername(String setUsername)
	{
		username = setUsername;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public void setPassword(String setPassword)
	{
		password = setPassword;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPhoneNumber(String setPhoneNumber)
	{
		phoneNumber = setPhoneNumber;
	}
	
	public String getPhoneNumber()
	{
		return phoneNumber;
	}
	
	public void setBuilding(String setBuilding)
	{
		building = setBuilding;
	}
	
	public String getBuilding()
	{
		return building;
	}
	
	public void setFloor(String setFloor)
	{
		floor = setFloor;
	}
	
	public String getFloor()
	{
		return floor;
	}
	
	public void setUnit(String setUnit)
	{
		unit = setUnit;
	}
	
	public String getUnit()
	{
		return unit;
	}
	
}
