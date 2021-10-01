package com.springboot.app.Services;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.springboot.app.Databases.TenantDatabase;
import com.springboot.app.Databases.UserDatabase;
import com.springboot.app.Entities.Tenant;
import com.springboot.app.Entities.User;


@Service
public class UserService {

	@Autowired 
	UserDatabase db;
	
	@Autowired
	TenantDatabase tenantDb;
	
	public int alphaSort(String name1, String name2, int x) {
		 if(name1 == null || name1.length() == 0) {
			 return 0;
		 }
		 if(name2 == null || name2.length() == 0) {
			 return 1;
		 }
		 if(name1.charAt(x)>name2.charAt(x)) {
			 return 1;
		 }
		 else if(name1.charAt(x)<name2.charAt(x)) {
			 return 0;
		 }
		 else {
			 if(name1.length()-1 == x) {
				 return 0;
			 }
			 else if(name2.length()-1 == x) {
				 return 1;
			 }
			 else {
			 	return alphaSort(name1, name2, x+1);
			 }
		 }
	 }

	
	
	
	
	public List<Tenant> getUsersSorted(String sort_by, List<Tenant> inputList)
	{
		List<Tenant> list = inputList;
		 Tenant temp;
		 if(sort_by.equals("email")) {
			 for(int x = 0; x < list.size(); x++) {
			 	for(int y = 0; y < list.size(); y++) {
				 	if(alphaSort(list.get(x).getEmail(),list.get(y).getEmail(),0) == 0) {
				 		temp = list.get(x);
				 		list.set(x, list.get(y));
				 		list.set(y, temp);
				 	}
			 	}
		 	}
		 }
		 else if(sort_by.equals("name")) {
			 for(int x = 0; x < list.size(); x++) {
				 	for(int y = 0; y < list.size(); y++) {
					 	if(alphaSort(list.get(x).getfName(),list.get(y).getfName(),0) == 0) {
					 		temp = list.get(x);
					 		list.set(x, list.get(y));
					 		list.set(y, temp);
					 	}
				 	}
			 	}
		 }
		 else if(sort_by.equals("phone")) {
			 for(int x = 0; x < list.size(); x++) {
				 	for(int y = 0; y < list.size(); y++) {
					 	if(alphaSort(list.get(x).getPhone(),list.get(y).getPhone(),0) == 0) {
					 		temp = list.get(x);
					 		list.set(x, list.get(y));
					 		list.set(y, temp);
					 	}
				 	}
			 	}
		 }
		 else if(sort_by.equals("building")) {
			 for(int x = 0; x < list.size(); x++) {
				 	for(int y = 0; y < list.size(); y++) {
					 	if(alphaSort(list.get(x).getApartment(),list.get(y).getApartment(),0) == 0) {
					 		temp = list.get(x);
					 		list.set(x, list.get(y));
					 		list.set(y, temp);
					 	}
				 	}
			 	}
		 }
		 return list;	 
	}
	
	
	
	
	public User searchbyEmail(String email)
	{
		Iterator<User> iter = db.findAll().iterator();
		System.out.println(db.findAll().size());
		System.out.println("Into the search service");
		while (iter.hasNext())
		{
			System.out.println("loop");
			User cur = iter.next();
			if (cur.getEmail().compareTo(email) == 0)
			{
				return cur;
			}
		}
		return null;
	}
	
	

	public Tenant searchbyEmailTenant(String email)
	{
		Iterator<Tenant> iter = tenantDb.findAll().iterator();
		System.out.println(tenantDb.findAll().size());
		System.out.println("Into the search service");
		while (iter.hasNext())
		{
			System.out.println("loop");
			Tenant cur = iter.next();
			if (cur.getEmail().compareTo(email) == 0)
			{
				return cur;
			}
		}
		return null;
	}
	
	
	
	
}
