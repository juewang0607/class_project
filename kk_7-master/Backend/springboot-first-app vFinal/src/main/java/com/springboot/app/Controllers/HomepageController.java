package com.springboot.app.Controllers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.app.Databases.ItemDatabase;
import com.springboot.app.Databases.TenantDatabase;
import com.springboot.app.Databases.UserDatabase;
import com.springboot.app.Entities.*;
import com.springboot.app.Services.*;

import io.swagger.annotations.Api;


@Api(value="Homepage Controller", description="Written by Samuel Mason")
@RestController
public class HomepageController {
	
	
	
	
	@Autowired 
	UserDatabase db;
	
	
	@Autowired
	TenantDatabase tenantDb;
	
	@Autowired 
	ItemDatabase itemList;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ItemFilterService itemFilterService;
	
	
	
	
	
	
	
	
	
	
	

	@RequestMapping(value="/home/{email}", method = RequestMethod.GET)
	 public ModelAndView getUserHomepage(Model model, @PathVariable(value="email") String email)
	 {
		
		ModelAndView modelAndView = new ModelAndView();
		model.addAttribute("Email",email);
	    modelAndView.setViewName("homepage");
	    return modelAndView;
	 }
	
	
	@RequestMapping(value="/home/{email}", method = RequestMethod.POST)
	 public ModelAndView getUserRequestFromHomepage(Model model, @PathVariable(value="email") String email, @RequestParam String goingTo)
	 {
		model.addAttribute("email", email);
		String choice = "";
		System.out.println(goingTo);
		if (goingTo.compareTo("My Info") == 0)
		{
			choice = "my_info";
		}
		else if (goingTo.compareTo("See available items") == 0)
		{
			choice = "buy";
		}
		else if (goingTo.compareTo("Sell an item") == 0)
		{
			choice = "sell";
		}
		else if(goingTo.compareTo("View all users") == 0)
		{
			choice = "view_all_users/sort_by/email";
		}
		else if(goingTo.compareTo("Manage Database") == 0)
		{
			choice = "manageDatabase";
		}
		else if(goingTo.compareTo("Message Home") == 0)
		{
			choice = "messages/" + email;
		}
		
		
		String redirect = "redirect:/home/" + email + "/" + choice;
		System.out.println(redirect);
		return new ModelAndView(redirect);
	 }
	
	
	
	
	@RequestMapping(value="/home/{email}/my_purchases", method = RequestMethod.GET)
	public ModelAndView getMyPurchases(Model model, @PathVariable(value="email") String email)
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("my_purchases");
		model.addAttribute("Email",email);
		List<Item> filteredItemList = itemFilterService.getItemsFromBuyer(email);
		model.addAttribute("items",filteredItemList);
		return modelAndView;
	}
	
	
	
	
	@RequestMapping(value="/home/{email}/my_listings", method = RequestMethod.GET)
	public ModelAndView getMyListings(Model model, @PathVariable(value="email") String email)
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("my_listings");
		model.addAttribute("Email",email);
		List<Item> filteredItemList = itemFilterService.getItemsFromSeller(email);
		System.out.println(filteredItemList.size());
		model.addAttribute("items",filteredItemList);
		return modelAndView;
	}
	
	
	
	
	
	
	
	
	@RequestMapping(value="/home/{email}/view_all_users/sort_by/{sort_by}", method = RequestMethod.POST)
	 public ModelAndView getUserRequestFromViewAllUsers(Model model, @PathVariable(value="email") String email, @RequestParam String goingTo)
	 {
		model.addAttribute("email", email);
		String choice = "";
		System.out.println(goingTo);
		if (goingTo.compareTo("Email") == 0)
		{
			choice = "email";
		}
		else if (goingTo.compareTo("Name") == 0)
		{
			choice = "name";
		}
		else if (goingTo.compareTo("Phone") == 0)
		{
			choice = "phone";
		}
		else if(goingTo.compareTo("Building") == 0)
		{
			choice = "building";
		}
		
		
		
		
		String redirect = "redirect:/home/" + email + "/view_all_users/sort_by/" + choice;
		System.out.println(redirect);
		return new ModelAndView(redirect);
	 }
	
	
	
	
	
	
	
	@RequestMapping(value="/home/{email}/view_all_users/sort_by/{sortBy}", method = RequestMethod.GET)
	 public ModelAndView getAllUsersSort(Model model, @PathVariable(value="email") String email, @PathVariable(value="sortBy") String sort_by)
	 {
		 
		String howToSort = sort_by;
		
		
		
		 ModelAndView modelAndView = new ModelAndView();
		 model.addAttribute("Email",email);
		 
		 // Jeet, just replace db.findAll() below with your list sorted appropriately 
		 // the howToSort param will be email,phone,name, or apartment and that is how they should be sorted
		 
		 
		 List<Tenant> l = userService.getUsersSorted(sort_by, tenantDb.findAll());
		 
		 
		 model.addAttribute("Users_List", l);
		 modelAndView.setViewName("view_all_users_sorted");
		    
		    
		    
		    return modelAndView;
	 }
	
	
	
	
	
	 @RequestMapping(value="/home/{email}/my_info", method = RequestMethod.GET)
	 public ModelAndView getUserInfo(Model model, @PathVariable(value="email") String email)
	 {
		 Tenant u = userService.searchbyEmailTenant(email);
		 ModelAndView modelAndView = new ModelAndView();
		 model.addAttribute("Email", u.getEmail());
		 model.addAttribute("Name", u.getfName()+" "+ u.getlName());
		 model.addAttribute("PhoneNumber", u.getPhone());
		 model.addAttribute("Address", u.getApartment());
		 modelAndView.setViewName("my_info");
		 return modelAndView;
	 }
	
	
	
	 
	 
	 
	 
	 @RequestMapping("/ItemsList")
	    List<Item> showUsers(){
	     System.out.println("Into the get");  
		 return itemList.findAll();
	    }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
