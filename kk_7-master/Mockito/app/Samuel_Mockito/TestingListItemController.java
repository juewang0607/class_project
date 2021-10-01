package com.springboot.app.Samuel_Mockito;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.springboot.app.Controllers.ListItemController;
import com.springboot.app.Controllers.RegisterController;
import com.springboot.app.Databases.ItemDatabase;
import com.springboot.app.Databases.UserDatabase;
import com.springboot.app.Entities.Item;
import com.springboot.app.Entities.User;
import com.springboot.app.Services.LoginService;
import com.springboot.app.Services.RegisterService;
import com.springboot.app.Services.UserService;

	
	
	
	@RunWith(SpringRunner.class)
	@WebMvcTest(ListItemController.class)
	public class TestingListItemController {

		@TestConfiguration
		  static class StringContextConfiguration { // can be named whatever

		      @Bean
		      public RegisterService rService() {
		          return new RegisterService();
		      }
		      
		      
		      @Bean
		      public LoginService lService() {
		          return new LoginService();
		      }

		      
		      @Bean
		      public UserService uService() {
		          return new UserService();
		      }
		      
		      
		      @Bean
					ItemDatabase getRepoItem() {
		        return mock(ItemDatabase.class);
		     }
		      
		      @Bean
				UserDatabase getRepoUser() {
	        return mock(UserDatabase.class);
	     }
		      
		      
			}

	
		@Autowired
		private MockMvc controller;

		@Autowired // note that this repo is also needed in
		private ItemDatabase repo;
	
		@Autowired // note that this repo is also needed in
		private UserDatabase Urepo;
		
		
		
		
		
		@Test
		public void testRegisterPost() throws Exception {

			
			// Set up MOCK methods for the REPO
		    List<Item> l = new ArrayList<Item>();

		    
		    List<User> uu = new ArrayList<User>();


		    // mock the findAll method
			  when(repo.findAll()).thenReturn(l);
			  
			  
			  


				// mock the save() method to save argument to the list
		  	when(repo.save((Item)any(Item.class)))
					.thenAnswer(x -> {
						  Item r = x.getArgument(0);
						  l.add(r);
						  return null;		  
			  });
				
					
					// mock the save() method to save argument to the list
				  	when(Urepo.save((User)any(User.class)))
							.thenAnswer(x -> {
								  User r = x.getArgument(0);
								  uu.add(r);
								  return null;		  
					  });
					
							
				  	assertEquals(repo.count(),0);
				  	
				  	
				  	MultiValueMap mm = new LinkedMultiValueMap();
				  	
				  	mm.add("ItemName", "a");
				  	mm.add("Description", "b");
				  	mm.add("Price", "c");
				  	mm.add("Length", "d");
				  	
				  	controller.perform(post("/home/sammyleemason@gmail.com/sell").contentType(MediaType.APPLICATION_JSON).params(mm))
					.andExpect(status().is(302));
		    
				  	
				  	
				  	
				  	assertEquals(l.size(),1);
				  	assertEquals(l.get(0).getName(),"a");
				  	assertEquals(l.get(0).getDescription(),"b");
				  	assertEquals(l.get(0).getPrice(),"c");
				  	assertEquals(l.get(0).getLength(),"d");
					
				  
		  	

		}
		
		
		
		
		
		
	
	
}
