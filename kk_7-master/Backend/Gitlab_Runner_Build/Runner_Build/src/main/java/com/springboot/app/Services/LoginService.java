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
* @author Jeet Nair 
*/
@Service
public class LoginService {
	
	@Autowired 
	UserDatabase db;
	
	
	
	
	/**
	 * Takes two Strings, an email and a password and verifies if the values match the same object within the database.
	 * @param givenEmail - an email string
	 * @param GivenPassword - a password string
	 * @return
	 */
	 public boolean validateUsername(String givenEmail, String GivenPassword)
		{
			
			System.out.println("Into the LoginService");
			// search for username
			System.out.println(givenEmail + " " + GivenPassword);
			List<User> ll = db.findAll();
			Iterator<User> iter = ll.iterator();
			String neededPassword = "123";
			while (iter.hasNext())
			{
				User cur = iter.next();
				if (cur.getEmail().compareTo(givenEmail) == 0)
				{
					System.out.println(cur.getEmail());
					neededPassword = cur.getPassword();
				}
			}
			System.out.println(neededPassword);
			System.out.println(GivenPassword);
			if (neededPassword.compareTo(GivenPassword) == 0)
			{
				return true;
			}
			
			
			return false;
		
		}
	 
	 
	 
	 
	 /**
	  * Takes two strings, an email and a password.  It verifies the email is attributed to an object and then modifies the password param with the second string
	  * @param email - an email string
	  * @param newPassword - a password string
	  * @return
	  */
	 public boolean changePassword(String email, String newPassword)
	 {
		 Boolean toReturn = false;
		 
		 List<User> list = db.findAll();
			User temp;
			for(int x = 0; x < list.size(); x++) {
				if(list.get(x).getEmail().contentEquals(email)) {
					temp = list.get(x);
					temp.setPassword(newPassword);
					db.saveAndFlush(temp);
					toReturn = true;
					break;
				}
			}
			
			return toReturn;
			
	 }
	
	
}
