package com.springboot.app.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.Databases.TenantDatabase;
import com.springboot.app.Databases.UserDatabase;
import com.springboot.app.Entities.*;

/** 
* 
* @author Zihao Huang
*/


@Service
public class ChangeUserInfoService {

	
	@Autowired 
	TenantDatabase db;
	
	@Autowired
	UserService userService;
	/** 
	* This is a method to change a user's email. 
	    * @param email - the old email address that the user is using
	    * @param newEmail - the new email address that the user want use
	    * @setEmail() set the user's email to newEmail
	    * @db.saveAndFlush() save it in the database
	*/
	public void changeEmail(String email, String newEmail)
	{
		Tenant u = userService.searchbyEmailTenant(email);
		u.setEmail(newEmail);
		db.saveAndFlush(u);
	}
	
	/** 
	* This is a method to change a user's phone number. 
	    * @param email - the email address that the user is current using
	    * @param newPhone - the new phone number that the user want use
	    * @setPhone() set the user's phone to newPhone number
	    * @db.saveAndFlush() save it in the database
	*/
	
	public void changePhone(String email, String newPhone)
	{
		Tenant u = userService.searchbyEmailTenant(email);
		u.setPhone(newPhone);
		db.saveAndFlush(u);
	}
	
	/** 
	* This is a method to change a user's firstname. 
	    * @param email - the email address that the user is current using
	    * @param newFName - the new firstname that the user want use
	    * @setFName() set the user's Fname to newFName
	    * @db.saveAndFlush() save it in the database
	*/
	public void ChangeFName(String email, String newFName)
	{
		Tenant u = userService.searchbyEmailTenant(email);
		u.setfName(newFName);
		db.saveAndFlush(u);
	}
	/** 
	* This is a method to change a user's Lastname
	    * @param email the  email address that the user is current using
	    * @param newLName - the new lastname that the user want use
	    * @setLName() set the user's lastname to newLName
	    * @db.saveAndFlush() save it in the database
	*/
	public void ChangeLName(String email, String newLName)
	{
		Tenant u = userService.searchbyEmailTenant(email);
		u.setlName(newLName);
		db.saveAndFlush(u);
	}
	/** 
	* This is a method to change a user's unit. 
	    * @param email- the email address that the user is current using
	    * @param newUnit - the new unit number that the user want use
	    * @setUnit() set the user's unit to newUnit
	    * @db.saveAndFlush() save it in the database
	*/
	
	public void ChangeUnit(String email, String newUnit)
	{
		Tenant u = userService.searchbyEmailTenant(email);
		u.setUnit(newUnit);
		db.saveAndFlush(u);
	}
	/** 
	* This is a method to change a user's apartment. 
	    * @param email - the email address that the user is current using
	    * @param newApartment - the new apartment number that the user want use
	    * @setApartment() set the user's apartment to newApartment
	    * @db.saveAndFlush() save it in the database
	*/
	public void ChangeApartment(String email, String newApartment)
	{
		Tenant u = userService.searchbyEmailTenant(email);
		u.setApartment(newApartment);
		db.saveAndFlush(u);
	}

	public void ChangePhone(String email, String newPhone)
	{
		Tenant u = userService.searchbyEmailTenant(email);
		u.setPhone(newPhone);
		db.saveAndFlush(u);
	}

	
	
	
	
	
	
	
	
	
	
	
}
