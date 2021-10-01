package com.springboot.app.Entities;



import javax.persistence.*;
import java.util.Date;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column 
	String email;

	@Column 
	String password;
	
	
	@Column 
	String type;
	
	public User()
	{
		
	}
	
	public User (String e, String p)
	{
		email = e;
		password = p;
		
	}
	
	public String IDString()
	{
		return ""+id;
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
	
	public String getType()
	{
		return type;
	}
	
	public void setType(String setType)
	{
		type = setType;
	}
}
