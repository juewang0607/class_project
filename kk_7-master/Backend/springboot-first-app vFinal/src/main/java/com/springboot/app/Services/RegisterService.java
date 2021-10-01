package com.springboot.app.Services;


import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.springboot.app.Databases.UserDatabase;
import com.springboot.app.Entities.User;


/** 
* 
* @author Samuel Mason 
*/
@Service
public class RegisterService {

	@Autowired 
	UserDatabase db;
	
	
	/** 
	* This is a method to check if an email has already been user to register an account. 
	    * @param u - a user object created with the given email and other information
	    * @return if email is being used
	*/
	public boolean checkIfEmailInUse(User u)
	{
		System.out.println("Into the register service");
		Boolean post = true;
	 List<User> list = db.findAll();
	 for(int x = 0; x < list.size()-1; x++) {
		 if(list.get(x).getEmail().contentEquals(u.getEmail())) {
			 post = false;
			 break;
		 }
	 }
	 if(post) {
		 db.save(u);
		 return true;
	 }
	 
	 return false;
	 
	}
	
	
}
