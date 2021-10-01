package com.springboot.app.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.Databases.ItemDatabase;
import com.springboot.app.Entities.Item;



@Service
public class SetSellerIDService {

	
	@Autowired 
	ItemDatabase itemList;
	
	
	
	public void setSellerID(Item itemIn, int idToSet)
	{
		itemList.findById(itemIn.getId());
		itemList.getOne(itemIn.getId()).setSellerID(""+idToSet);
	}
	
	
	public Item changeSellerIDofItem(Item itemIn, int idToSet)
	{
		Item toReturn = itemIn;
		itemIn.setBuyerID(""+idToSet);
		return toReturn;
	}
}
