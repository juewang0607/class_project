package com.springboot.app.Samuel_Mockito;



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

import com.springboot.app.Databases.TenantDatabase;
import com.springboot.app.Databases.UserDatabase;
import com.springboot.app.Entities.User;
import com.springboot.app.Services.LoginService;

@RunWith(SpringRunner.class)
public class TestingChangePassword {

	@TestConfiguration
	  static class StringContextConfiguration { // can be named whatever

	      @Bean
	      public LoginService lService() {
	          return new LoginService();
	      }

	      @Bean
				UserDatabase getRepo() {
	        return mock(UserDatabase.class);
	     }
	      
	      @Bean
			public TenantDatabase getRepoTenant() {
	        return mock(TenantDatabase.class);
	     }
	      
	      
		}
	
	

	
	
	@Autowired
	private LoginService ls;
	
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

		
		User u = new User();
		u.setEmail("Mockito_Test@email.com");
		u.setPassword("Testing_Mockito_Password");
		db.save(u);
		Boolean shouldPass = ls.validateUsername("Mockito_Test@email.com", "Testing_Mockito_Password");
		System.out.println(shouldPass);
		Boolean shouldFail = ls.validateUsername("Mockito_Test@email.com", "Testing_Mockito_Password_Incorrect");
		assertEquals(shouldPass,true);
		assertEquals(shouldFail,false);
		
		
		
		
		ls.changePassword("Mockito_Test@email.com", "New_Mockito_Password");
		
		Boolean shouldPass2 = ls.validateUsername("Mockito_Test@email.com", "New_Mockito_Password");
		System.out.println(shouldPass);
		Boolean shouldFail2 = ls.validateUsername("Mockito_Test@email.com", "New_Mockito_Password_Incorrect");
		assertEquals(shouldPass2,true);
		assertEquals(shouldFail2,false);
		
		
		
	}
	
	
	
	
}