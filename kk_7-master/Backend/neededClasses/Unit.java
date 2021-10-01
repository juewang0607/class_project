package neededClasses;

import java.util.ArrayList;

public class Unit {

	private String name;
	private String unitNumber;
	public ArrayList<String> occupants = new ArrayList<String>();
	
	
	public Unit(String Name, String UnitNumber)
	{
		name = Name;
		unitNumber = UnitNumber;
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