package com.springboot.app;



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
	String firstName;
	
	@Column 
	String lastName;
	
	@Column 
	String phoneNumber;
	
	@Column 
	String address;
	
	
	
	
	public User()
	{
		
	}
	
	public User (String e, String fn, String ln, String pn, String a)
	{
		email = e;
		firstName = fn;
		lastName = ln;
		phoneNumber = pn;
		address = a;
		
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
	
	
	
	public void setFirstName(String fn)
	{
		firstName = fn;
	}
	
	
	public String getFirstName()
	{
		return firstName;
	}

	
	
	
	
	
	
}
