package neededClasses;

import java.util.ArrayList;

public class Building {

	private String name;
	private String address;
	private int residentCount;
	public ArrayList<Floor> floors = new ArrayList<Floor>();
	
	public Building(String Name, String Address, int ResidentCount)
	{
		name = Name;
		address = Address;
		residentCount = ResidentCount;
	}
	
	public void setName(String setName)
	{
		name = setName;
	}
	
	public String getName()
	{
		return name;
	}
	
	
	
	
	
	
	
	
}
