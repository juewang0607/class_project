package neededClasses;

import java.util.ArrayList;

public class Floor {

	private String name;
	private int residentsCount;
	public ArrayList<Unit> units = new ArrayList<Unit>();
	
	public Floor(String Name, int ResidentsCount)
	{
		name = Name;
		residentsCount = ResidentsCount;
	}
	
	
	public void setName(String setName)
	{
		name = setName;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setResidentsCount(int setResidentsCount)
	{
		residentsCount = setResidentsCount;
	}
	
	public int getResidentsCount()
	{
		return residentsCount;
	}
	
	
	
	
	

}
