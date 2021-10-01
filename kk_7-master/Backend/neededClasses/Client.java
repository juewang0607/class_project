package neededClasses;

import java.util.ArrayList;



public class Client extends User{

	
	
	public ArrayList<Building> properties = new ArrayList<Building>();
	
	
	public Client(String FirstName, String LastName, String Username, String Password, String PhoneNumber, String Building, String Floor, String Unit)
	{
		super(FirstName, LastName, Username, Password, PhoneNumber, Building, Floor, Unit);
	}

}
