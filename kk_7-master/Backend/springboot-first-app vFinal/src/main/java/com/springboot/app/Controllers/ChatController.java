package com.springboot.app.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.app.Databases.ItemDatabase;
import com.springboot.app.Databases.TenantDatabase;
import com.springboot.app.Databases.UserDatabase;
import com.springboot.app.Entities.Tenant;
import com.springboot.app.Services.UserService;

import io.swagger.annotations.Api;


@Api(value="Chat Controller", description="Written by Samuel Mason")
@RestController
public class ChatController {
	
	
	
	
	@Autowired 
	UserDatabase db;
	
	@Autowired 
	ItemDatabase itemList;
	
	@Autowired
	UserService userService;
	
	@Autowired
	TenantDatabase tdb;
	
	
	
	
	
	
	@RequestMapping(value="/home/{email}/messages/{recipient}", method = RequestMethod.GET)
	public ModelAndView getMyPurchases(Model model, @PathVariable(value="email") String email, @PathVariable(value="recipient") String recipient)
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("message_home");
		model.addAttribute("Email",email);
		model.addAttribute("Recipient",recipient);
		model.addAttribute("items",itemList.findAll());
 List<Tenant> l = userService.getUsersSorted(email, tdb.findAll());
		 
		 
		 model.addAttribute("Users_List", l);
		return modelAndView;
	}
	
	
	
	
	
	
}
