package com.springboot.app.Zihao_Mockito.NewForDemo4;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.springboot.app.Databases.AdminDatabase;
import com.springboot.app.Databases.ItemDatabase;
import com.springboot.app.Databases.LandLordDatabase;
import com.springboot.app.Databases.TenantDatabase;
import com.springboot.app.Databases.UserDatabase;
import com.springboot.app.Entities.Item;
import com.springboot.app.Entities.User;
import com.springboot.app.Services.ChangeItemInfoService;
import com.springboot.app.Services.ChangeUserInfoService;
import com.springboot.app.Services.LoginService;
import com.springboot.app.Services.UserService;

@RunWith(SpringRunner.class)
public class TestingChangeItemLength {

	@TestConfiguration
	  static class StringContextConfiguration { // can be named whatever

	      @Bean
	      public ChangeUserInfoService lService() {
	          return new ChangeUserInfoService();
	      }
	      
	      @Bean
	      public ChangeItemInfoService iService() {
	          return new ChangeItemInfoService();
	      }
	      

	      @Bean
				UserDatabase getRepo() {
	        return mock(UserDatabase.class);
	     }
	      
	      
	      @Bean
			ItemDatabase getItemRepo() {
      return mock(ItemDatabase.class);
   }
	      
	      @Bean
	      public UserService uService() {
	          return new UserService();
	      }
	      
	      
	    
		      
		      @Bean
				public TenantDatabase getRepoTenant() {
		        return mock(TenantDatabase.class);
		     }
		      

		      @Bean
				public LandLordDatabase getRepoLandlord() {
		        return mock(LandLordDatabase.class);
		     }
		      
		      @Bean
				public AdminDatabase getRepoAdmin() {
		        return mock(AdminDatabase.class);
		     }
		      
	      
   }
	      
		

	
	
	@Autowired
	private ChangeItemInfoService ls;
	
	@Autowired
	private UserDatabase db;
	
	
	
	@Test
	public void testChangePrice()
	{
		
		
		List<User> l = new ArrayList<User>();
		
		when(db.findAll()).thenReturn(l);
		
		when(db.save((User)any(User.class)))
		.thenAnswer(x -> {
			  User r = x.getArgument(0);
			  l.add(r);
			  return null;
  });	
		
		
		
		Boolean isRight;
		Boolean isWrong;
		Item new_item = new Item();
		new_item.setPrice("5");
		ls.changePrice(new_item, "4");
		if(new_item.getPrice().equals("4")){
			isRight = true;
		}
		else {
			isRight = false;
		}
		if(new_item.getPrice().equals("5")){
			isWrong = true;
		}
		else {
			isWrong = false;
		}
		assertEquals(true,isRight);
		assertEquals(false,isWrong);	
	}
	
	
	
	
}