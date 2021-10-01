package com.springboot.app;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class HomepageController {

	

	@RequestMapping(value="/home/{email}", method = RequestMethod.GET)
	 public String getUserHomepage(Model model, @PathVariable(value="email") String email)
	 {
		
		model.addAttribute("email", email);
		
		
		
		
		return "homepage";
	 }
	
	
	@RequestMapping(value="/home/{email}", method = RequestMethod.POST)
	 public String getUserRequestFromHomepage(Model model, @PathVariable(value="email") String email, @RequestParam String goingTo)
	 {
		model.addAttribute("email", email);
		String choice = "";
		System.out.println(goingTo);
		if (goingTo.compareTo("My Info") == 0)
		{
			choice = "my_info";
		}
		else if (goingTo.compareTo("See available item") == 0)
		{
			choice = "buy";
		}
		else if (goingTo.compareTo("Sell an item") == 0)
		{
			choice = "sell";
		}
		
		
		
		
		String redirect = "redirect:/home/" + email + "/" + choice;
		return redirect;
	 }
	
	
	
	
	
	
	
	
	
	
	 
	 
	 
	 
}

