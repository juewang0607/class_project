package com.springboot.app.Zihao_Mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import com.springboot.app.Entities.Tenant;

@RunWith(SpringRunner.class)
public class TestingTenant {
      @Test
      public void testChangeEmail()
      {
    	Tenant new_tenant = new Tenant("huangpp@iastate.edu","test");
        new_tenant.setEmail("test@test.com");
       assertEquals(new_tenant.getEmail(),"test@test.com");
      }
      @Test
      public void testChangePassword()
      {
    	Tenant new_tenant = new Tenant("huangpp@iastate.edu","test");
        new_tenant.setPassword("test1");
       assertEquals(new_tenant.getPassword(),"test1");
      }
     
      @Test
      public void testChangefName()
      {
    	Tenant new_tenant = new Tenant("huangpp@iastate.edu","test");
        new_tenant.setfName("test");
       assertEquals(new_tenant.getfName(),"test");
      }  
      @Test
      public void testChangelName()
      {
    	Tenant new_tenant = new Tenant("huangpp@iastate.edu","test");
        new_tenant.setlName("test");
       assertEquals(new_tenant.getlName(),"test");
      }  
      @Test
      public void testChangeApartment()
      {
    	Tenant new_tenant = new Tenant("huangpp@iastate.edu","test");
        new_tenant.setApartment("1");
       assertEquals(new_tenant.getApartment(),"1");
      } 
      @Test
      public void testChangeUnit()
      {
    	Tenant new_tenant = new Tenant("huangpp@iastate.edu","test");
        new_tenant.setUnit("1");
       assertEquals(new_tenant.getUnit(),"1");
      }
	}