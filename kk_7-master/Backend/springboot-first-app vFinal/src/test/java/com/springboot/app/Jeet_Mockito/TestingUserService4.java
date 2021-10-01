package com.springboot.app.Jeet_Mockito;

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
import com.springboot.app.Entities.Tenant;
import com.springboot.app.Entities.User;
import com.springboot.app.Services.UserService;


@RunWith(SpringRunner.class)
public class TestingUserService4 {
	@TestConfiguration
	  static class StringContextConfiguration {

	      @Bean
	      public UserService uService() {
	          return new UserService();
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
	private UserService us;
	
	@Autowired
	private TenantDatabase db;
	
	@Test
	public void testApartment() {
		List<Tenant> l = new ArrayList<Tenant>();
		
		when(db.findAll()).thenReturn(l);

when(db.save((Tenant)any(Tenant.class)))
.thenAnswer(x -> {
	  Tenant r = x.getArgument(0);
	  l.add(r);
	  return null;
});

Tenant one = new Tenant();
Tenant two = new Tenant();
Tenant three = new Tenant();
Tenant four = new Tenant();

one.setApartment("Building");
two.setApartment("Apartment");
three.setApartment("Building Three");
four.setApartment("");
one.setfName("Bob");
two.setfName("Bobby");
three.setfName("");
four.setfName("Aaron");
one.setPhone("1234567890");
two.setPhone("");
three.setPhone("1234568907");
four.setPhone("0987654321");
one.setEmail("");
two.setEmail("email1@email.com");
three.setEmail("email11@email.com");
four.setEmail("amail1@email.com");

db.save(one);
db.save(two);
db.save(three);
db.save(four);

List<Tenant> checker1 = new ArrayList<Tenant>();
List<Tenant> checker2 = new ArrayList<Tenant>();
checker1.add(four);
checker1.add(two);
checker1.add(one);
checker1.add(three);
checker2.add(one);
checker2.add(two);
checker2.add(three);
checker2.add(four);

Boolean isRight;
Boolean isWrong;

if(us.getUsersSorted("building", db.findAll()).equals(checker1)) {
	isRight = true;
}
else {
	isRight = false;
}
if(us.getUsersSorted("building", db.findAll())!=checker2) {
	isWrong = true;
}
else {
	isWrong = false;
}

assertEquals(true,isRight);
assertEquals(true,isWrong);

	}
	
}//test