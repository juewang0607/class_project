package com.springboot.app.Entities;



import javax.persistence.*;
import java.util.Date;




@Entity
public class LandLord{

	
	
	
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
	
	
	
	
	public LandLord()
	{
		
	}
	
	public LandLord(String e, String p)
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
}
