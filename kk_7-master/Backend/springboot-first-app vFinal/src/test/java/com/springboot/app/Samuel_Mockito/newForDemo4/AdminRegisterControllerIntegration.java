package com.springboot.app.Samuel_Mockito.newForDemo4;



import com.springboot.app.Controllers.RegisterController;
import com.springboot.app.Databases.AdminDatabase;
import com.springboot.app.Databases.LandLordDatabase;
import com.springboot.app.Databases.TenantDatabase;
import com.springboot.app.Databases.UserDatabase;
import com.springboot.app.Entities.Admin;
import com.springboot.app.Entities.Tenant;
import com.springboot.app.Entities.User;
import com.springboot.app.Services.LoginService;
import com.springboot.app.Services.RegisterService;

//Import Java libraries
import java.util.List;
import java.util.ArrayList;

//import junit/spring tests
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.http.MediaType;

//import mockito related
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.doNothing;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;



@RunWith(SpringRunner.class)
@WebMvcTest(RegisterController.class)
public class AdminRegisterControllerIntegration {

	@TestConfiguration
	  static class StringContextConfiguration {

	      @Bean
	      public RegisterService rService() {
	          return new RegisterService();
	      }

	      @Bean
	      public LoginService uService() {
	          return new LoginService();
	      }

	      @Bean UserDatabase getRepo() {
	         return mock(UserDatabase.class);
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
	private MockMvc controller;

	@Autowired // note that this repo is also needed in
	private AdminDatabase repo;
	
	
	@Test
	public void testAdminRegisterPost() throws Exception {

		
		// Set up MOCK methods for the REPO
	    List<Admin> l = new ArrayList<Admin>();

	    // mock the findAll method
		  when(repo.findAll()).thenReturn(l);
		  
		  doNothing().when(repo).deleteAll();
		    l.clear();


			// mock the save() method to save argument to the list
	  	when(repo.save((Admin)any(Admin.class)))
				.thenAnswer(x -> {
					  Admin r = x.getArgument(0);
					  l.add(r);
					  return null;		  
		  }
				
						
						
						);

	  	
	  	
	  	
	  	
	  	assertEquals(repo.count(),0);
	  	
	  	
	  	MultiValueMap mm = new LinkedMultiValueMap();
	  	
	  	mm.add("email", "admin");
	  	mm.add("password", "should");
	  	mm.add("FirstName", "be");
	  	mm.add("LastName", "different");
	  	mm.add("PhoneNumber", "than user");
	  
	  	controller.perform(post("/register/admin").contentType(MediaType.APPLICATION_JSON).params(mm))
		.andExpect(status().is(302));
	  	
	  	
	  	assertEquals(l.size(),1);
	  	assertEquals(l.get(0).getEmail(),"admin");
	  	assertEquals(l.get(0).getPassword(),"should");
	  	assertEquals(l.get(0).getfName(),"be");
	  	assertEquals(l.get(0).getlName(),"different");
	  	//assertEquals(l.get(0).getPhone(),"than user");
	  	
	  	
	  	
	  	MultiValueMap nn = new LinkedMultiValueMap();
	  	
	  	nn.add("email", "Testing");
	  	nn.add("password", "that");
	  	nn.add("FirstName", "admin");
	  	nn.add("LastName", "is");
	  	nn.add("PhoneNumber", "different");
	
	  	controller.perform(post("/register/admin").contentType(MediaType.APPLICATION_JSON).params(nn))
		.andExpect(status().is(302));
	  	
	  	
	  	assertEquals(l.size(),2);
	  	assertEquals(l.get(1).getEmail(),"Testing");
	  	assertEquals(l.get(1).getPassword(),"that");
	  	assertEquals(l.get(1).getfName(),"admin");
	  	assertEquals(l.get(1).getlName(),"is");
	  	assertEquals(l.get(1).getPhone(),"different");
	  	
	  	
	  	
	  	
	  	
	  	

	}
	
	
	
	
}


