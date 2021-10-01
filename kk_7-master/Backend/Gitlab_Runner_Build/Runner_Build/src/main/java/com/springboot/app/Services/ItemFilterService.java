package com.springboot.app.Services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.Databases.ItemDatabase;
import com.springboot.app.Databases.TenantDatabase;
import com.springboot.app.Databases.UserDatabase;
import com.springboot.app.Entities.Item;
import com.springboot.app.Entities.Tenant;
import com.springboot.app.Entities.User;



@Service
public class ItemFilterService {

	
	
	@Autowired 
	TenantDatabase db;
	
	@Autowired 
	ItemDatabase itemsList;
	
	
	public List<Item> getItemsFromSeller(String userEmail)
	{
		System.out.println("Get from seller");
		
		List<Item> findAll = getAvailableItems();
		List<Item> toReturn = new ArrayList<Item>();
		
		
		
		Iterator<Tenant> iter = db.findAll().iterator();
		String sellerId = "-1";
		while (iter.hasNext())
		{
			System.out.println("loop");
			Tenant cur = iter.next();
			if (cur.getEmail().compareTo(userEmail) == 0)
			{
				sellerId = cur.IDString();
			}
		}
		
		System.out.println("Seller ID : " + sellerId);
		
		Iterator<Item> iterItem = findAll.iterator();
		while (iterItem.hasNext())
		{
			System.out.println("loop");
			Item cur = iterItem.next();
			if (cur.GetSellerID().compareTo(sellerId)==0)
			{
				toReturn.add(cur);
			}
		}
		
		
		
		
		return toReturn;
		
	}
	
	
	
	
	
	
	
	
	
	public List<Item> getItemsFromBuyer(String userEmail)
	{
		
		
		List<Item> findAll = itemsList.findAll();
		System.out.println("Find all length" + findAll.size());
		List<Item> toReturn = new ArrayList<Item>();
		
		
		
		Iterator<Tenant> iter = db.findAll().iterator();
		String buyerId = "-1";
		while (iter.hasNext())
		{
			System.out.println("loop");
			Tenant cur = iter.next();
			if (cur.getEmail().compareTo(userEmail) == 0)
			{
				buyerId = cur.IDString();
			}
		}
		
		
		System.out.println("Buyer ID : " + buyerId);
		
		
		
		
		Iterator<Item> iterItem = findAll.iterator();
		while (iterItem.hasNext())
		{
			System.out.println("loop");
			Item cur = iterItem.next();
			System.out.println("Buyer Id of Cur" + cur.GetBuyerID());
			if (cur.GetBuyerID().compareTo(buyerId)==0)
			{
				toReturn.add(cur);
			}
		}
		return toReturn;	
	}
	
	
	
	
	public List<Item> getAvailableItems()
	{
		List<Item> findAll = itemsList.findAll();
		List<Item> toReturn = new ArrayList<Item>();
		
		Iterator<Item> iterItem = findAll.iterator();
		while (iterItem.hasNext())
		{
			System.out.println("loop");
			Item cur = iterItem.next();
			if (cur.getAvailable() == true)
			{
				toReturn.add(cur);
			}
		}
		return toReturn;	
		
	}
	
	
	
	
}
