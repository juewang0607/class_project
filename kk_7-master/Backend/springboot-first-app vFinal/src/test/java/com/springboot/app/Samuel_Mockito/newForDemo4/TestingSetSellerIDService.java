package com.springboot.app.Samuel_Mockito.newForDemo4;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.app.Databases.ItemDatabase;
import com.springboot.app.Databases.UserDatabase;
import com.springboot.app.Entities.Item;
import com.springboot.app.Entities.User;
import com.springboot.app.Services.LoginService;
import com.springboot.app.Services.SetSellerIDService;



@RunWith(SpringRunner.class)
public class TestingSetSellerIDService {

	@TestConfiguration
	  static class StringContextConfiguration { // can be named whatever

	      @Bean
	      public SetSellerIDService lService() {
	          return new SetSellerIDService();
	      }

	      @Bean
				ItemDatabase getRepo() {
	        return mock(ItemDatabase.class);
	     }
		}

	
	
	@Autowired
	private SetSellerIDService setSellerIDService;
	
	@Autowired
	private ItemDatabase db;
	
	
	
	@Test
	public void testLoginCheck()
	{
		
		
		List<Item> l = new ArrayList<Item>();
		
				
		
		when(db.findAll()).thenReturn(l);
		
		

		when(db.save((Item)any(Item.class)))
		.thenAnswer(x -> {
			  Item r = x.getArgument(0);
			  l.add(r);
			  return null;
  });

		
		
		

		
		Item item = new Item();
		item.setBuyerID("5");
		db.save(item);
		
		System.out.println(l.get(0).GetBuyerID());
		assertEquals(item.GetBuyerID(),"5");
		setSellerIDService.changeSellerIDofItem(item, 2);
		
		System.out.println(l.get(0).GetBuyerID());
		assertEquals(item.GetBuyerID(),"2");
		
	}
    
	
	
	
}
