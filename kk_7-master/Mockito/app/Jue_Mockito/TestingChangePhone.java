package com.springboot.app.Jue_Mockito;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.springboot.app.Databases.UserDatabase;
import com.springboot.app.Entities.User;
import com.springboot.app.Services.ChangeUserInfoService;
import com.springboot.app.Services.LoginService;
import com.springboot.app.Services.UserService;

@RunWith(SpringRunner.class)
public class TestingChangePhone {

	@TestConfiguration
	  static class StringContextConfiguration { // can be named whatever

	      @Bean
	      public ChangeUserInfoService lService() {
	          return new ChangeUserInfoService();
	      }

	      @Bean
				UserDatabase getRepo() {
	        return mock(UserDatabase.class);
	     }
	      
	      @Bean
	      public UserService uService() {
	          return new UserService();
	      }
	      
   }
	      
		

	
	
	@Autowired
	private ChangeUserInfoService ls;
	
	@Autowired
	private UserDatabase db;
	
	
	
	@Test
	public void testLoginCheck()
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
		User new_user = new User();
		new_user.setEmail("Jue_Test@email.com");
		new_user.setPhone("Testing_phone");
		db.save(new_user);
		ls.changePhone("Jue_Test@email.com", "New_Testing_phone");
		if(new_user.getPhone().equals("New_Testing_phone")){
			isRight = true;
		}
		else {
			isRight = false;
		}
		if(new_user.getPhone().equals("Testing_phone")){
			isWrong = true;
		}
		else {
			isWrong = false;
		}
		assertEquals(true,isRight);
		assertEquals(false,isWrong);	
	}
	
	
	
	
}