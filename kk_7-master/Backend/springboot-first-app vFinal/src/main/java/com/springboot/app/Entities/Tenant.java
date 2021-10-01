package com.springboot.app.Entities;



import javax.persistence.*;
import java.util.Date;




@Entity
public class Tenant{

	
	@Column
	int buySize = 0;
	
	@Column
	int sellSize = 0;
	
	@Column
	int[] sold = new int[sellSize];
	
	@Column
	int[] buy = new int[buySize];
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column 
	String email;

	@Column 
	String password;
	
	@Column
	String fName;
	
	@Column
	String lName;
	
	@Column
	String phone;
	
	@Column
	String apartment;
	
	@Column
	String unit;
	
	
	
	
	public Tenant()
	{
		
	}
	
	public Tenant(String e, String p)
	{
		email = e;
		password = p;
		
	}
	
	public String IDString()
	{
		return ""+id;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getApartment() {
		return apartment;
	}

	public void setApartment(String apartment) {
		this.apartment = apartment;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getId()
	{
		return id;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String em)
	{
		email = em;
	}
	
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String pw)
	{
		password = pw;
	}	
	
	public int[] getSold(){
		return sold;
	}
	
	public void addSold(int ID) {
		sellSize++;
		int[] temp = new int[sellSize];
		for(int x = 0; x<sold.length; x++) {
			temp[x]=sold[x];
		}
		temp[sellSize-1] = ID;
		sold = temp;
	}
	
	public int[] getBuy(){
		return buy;
	}
	
	public void addBuy(int ID) {
		buySize++;
		int[] temp = new int[buySize];
		for(int x = 0; x<buy.length; x++) {
			temp[x]=buy[x];
		}
		temp[buySize-1] = ID;
		buy = temp;
	}
}
