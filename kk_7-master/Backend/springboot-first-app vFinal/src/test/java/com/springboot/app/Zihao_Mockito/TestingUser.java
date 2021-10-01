package com.springboot.app.Zihao_Mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import com.springboot.app.Entities.Tenant;
import com.springboot.app.Entities.User;

@RunWith(SpringRunner.class)
public class TestingUser {
      @Test
      public void testChangeEmail()
      {
    	User new_user = new User("huangpp@iastate.edu","test");
        new_user.setEmail("test@test.com");
       assertEquals(new_user.getEmail(),"test@test.com");
      }
      @Test
      public void testChangePassword()
      {
    	User new_user = new User("huangpp@iastate.edu","test");
        new_user.setPassword("test1");
       assertEquals(new_user.getPassword(),"test1");
      }
     
      
	}