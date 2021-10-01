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





@Api(value="View Item Controller", description="Written by Samuel Mason")
@RestController
public class ViewItemController {

	
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
	
	
	
	@RequestMapping(value="/home/{email}/buy", method = RequestMethod.GET)
	 public ModelAndView getAvailableItems(Model model, @PathVariable(value="email") String email)
	 {
		 ModelAndView modelAndView = new ModelAndView();
		 modelAndView.setViewName("testingList");
		 model.addAttribute("Email",email);
		 model.addAttribute("items",itemFilterService.getAvailableItems());
		 //while (iter.hasNext())
		 //{
			// Item cur = iter.next();
			 //model.addAttribute("Title", cur.name);
			// model.addAttribute("Price", cur.price);
			 //model.addAttribute("ID", cur.id); 
		 //} 
		 return modelAndView;
	 }
	 
	 
	 
	 
	 @RequestMapping(value="/home/{email}/view_item/{id}", method = RequestMethod.GET)
	 public ModelAndView viewItem(Model model, @PathVariable(value="email") String email, @PathVariable(value="id") int id)
	 {
		 
		 System.out.println(id);
		 Optional<Item> item = itemList.findById(id);
		 System.out.println(item.get().getName());
		 System.out.println(item.get().getDescription());
		 ModelAndView modelAndView = new ModelAndView();
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
		 model.addAttribute("Available",item.get().getAvailable()+"");
		 
		 modelAndView.setViewName("show_single_item");
		 return modelAndView;
	 }
		 

	 
	 
	 
	 @RequestMapping(value="/getbyID", method = RequestMethod.GET)
	 public ModelAndView homeTest(Model model)
	 {
		 ModelAndView modelAndView = new ModelAndView();
		 
		 
		 
		 
		 
		 
		 
		 
		 modelAndView.setViewName("home");
		 return modelAndView;
	 }
	 
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
