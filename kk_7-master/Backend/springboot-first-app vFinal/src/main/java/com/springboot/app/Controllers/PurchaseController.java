package com.springboot.app.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.app.Databases.ItemDatabase;
import com.springboot.app.Databases.TenantDatabase;
import com.springboot.app.Databases.UserDatabase;
import com.springboot.app.Entities.Item;
import com.springboot.app.Entities.Tenant;
import com.springboot.app.Services.ItemFilterService;
import com.springboot.app.Services.UserService;

import io.swagger.annotations.Api;

@Api(value="Purchase Controller", description="Written by Samuel Mason")
@RestController
public class PurchaseController {

	@Autowired 
	UserDatabase db;
	
	@Autowired 
	ItemDatabase itemList;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ItemFilterService itemFilterService;
	
	@Autowired 
	TenantDatabase tdb;
	
	@RequestMapping(value="/home/{email}/view_item/{id}/purchase", method = RequestMethod.GET)
	 public ModelAndView getPurchase(Model model, @PathVariable(value="email") String email, @PathVariable(value="id") int id)
	 {
		ModelAndView modelAndView = new ModelAndView();
		Optional<Item> item = itemList.findById(id);
		 System.out.println(item.get().getName());
		 System.out.println(item.get().getDescription());
		 System.out.println("SellerID = " + item.get().GetSellerID());
		 int  sellerID = Integer.parseInt(item.get().GetSellerID());
		 Optional<Tenant> seller = tdb.findById(sellerID);
		 model.addAttribute("Email",email);
		 model.addAttribute("Title",item.get().getName());
		 model.addAttribute("Price",item.get().getPrice());
		 model.addAttribute("Description",item.get().getDescription());
		 model.addAttribute("Length",item.get().getLength());
		 model.addAttribute("Path",item.get().getFilePath());
		 model.addAttribute("Seller",seller.get());
		 System.out.println(item.get().IDString());
		 model.addAttribute("ItemID",item.get().IDString());
		 modelAndView.setViewName("confirm_purchase");
		 return modelAndView;	
	 }
	
	
	
	
	@RequestMapping(value="/home/{email}/view_item/{id}/purchase", method = RequestMethod.POST)
	 public ModelAndView postPurchase(Model model, @PathVariable(value="email") String email, @PathVariable(value="id") int id, @RequestParam String goingTo)
	 {
		
		
		String choice = "";
		System.out.println(goingTo);
		String redirect = "";
		int buyerID = userService.searchbyEmailTenant(email).getId();
		System.out.println(email);
		System.out.println("Buyer Id to set" + buyerID);
		if (goingTo.compareTo("Confirm Purchase") == 0)
		{
			Item justBought = itemList.getOne(id);
			justBought.setAvailable(false);
			justBought.setBuyerID(""+buyerID);
			itemList.saveAndFlush(justBought);
			
			
			System.out.println("Confirm");
			redirect = "redirect:/home/" + email;
			
			
		}
		else if (goingTo.compareTo("Cancel") == 0)
		{
			System.out.println("Cancel");
			redirect = "redirect:/home/" + email + "/view_item/" + id;
			
		}
		
		System.out.println(redirect);
		return new ModelAndView(redirect);
	 }
	
	
	
	
	
	
	
	
	
}
