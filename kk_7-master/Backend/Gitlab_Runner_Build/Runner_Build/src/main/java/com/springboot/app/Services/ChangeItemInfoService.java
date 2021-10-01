package com.springboot.app.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.Databases.ItemDatabase;
import com.springboot.app.Entities.Item;

/** 
* 
* @author Jue Wang
*/



@Service
public class ChangeItemInfoService {

	
	@Autowired 
	ItemDatabase itemList;
	/** 
	* This is a method to change a item's name. 
	    * @param i - the item you would like to make changes
	    * @param newName - the new name address that is used for item
	    * @setName() set the item's newName to database
	    * @db.saveAndFlush() save changes in the database
	*/

	public void changeName(Item i, String newName)
	{
		i.setName(newName);
		itemList.saveAndFlush(i);
	}
	
	/** 
	* This is a method to change a item's description. 
	    * @param i - the item you would like to make changes
	    * @param newDescription - the new description for item
	    * @setDescription() set the item's description to database
	    * @db.saveAndFlush() save changes in the database
	*/
	
	public void changeDescription(Item i, String newDescription)
	{
		i.setDescription(newDescription);
		itemList.saveAndFlush(i);
	}
	/** 
	* This is a method to change a item's price. 
	    * @param i - the item you would like to make changes
	    * @param newPrice - the new price for item
	    * @setPrice() set the item's price to database
	    * @db.saveAndFlush() save changes in the database
	*/
	
	public void changePrice(Item i, String newPrice)
	{
		i.setPrice(newPrice);
		itemList.saveAndFlush(i);
	}
	/** 
	* This is a method to change a item's length. 
	    * @param i - the item you would like to make changes
	    * @param newLength - the new length for item
	    * @setLength() set the item's length to database
	    * @db.saveAndFlush() save changes in the database
	*/
	
	public void changeLength(Item i, String newLength)
	{
		i.setLength(newLength);
		itemList.saveAndFlush(i);
	}

	
}
