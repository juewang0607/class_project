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
import com.springboot.app.Databases.UserDatabase;
import com.springboot.app.Entities.Item;
import com.springboot.app.Entities.Tenant;
import com.springboot.app.Entities.User;
import com.springboot.app.Services.UserService;



@RestController
public class ListItemController {
	
	
	@Autowired 
	UserDatabase db;
	
	@Autowired 
	ItemDatabase itemList;
	
	@Autowired
	UserService userService;
	
	
	
	
	@RequestMapping(value="/home/{email}/sell", method = RequestMethod.GET)
	 public ModelAndView ListItem(Model model, @PathVariable(value="email") String email)
	 {
		 ModelAndView modelAndView = new ModelAndView();
		 model.addAttribute("Email",email);
		 modelAndView.setViewName("list_an_item");
		 return modelAndView;
	 }
	 
	 
	 
	 
	 @RequestMapping(value="/home/{email}/sell", method = RequestMethod.POST)
	 public ModelAndView getItemInfo(Model model, @PathVariable(value="email") String email, @RequestParam String ItemName, @RequestParam String Description, @RequestParam String Price, @RequestParam String Length, @RequestParam(required = false) MultipartFile file) throws IOException, InterruptedException
	 {
		 Item toAdd = new Item();
		 toAdd.setName(ItemName);
		 toAdd.setDescription(Description);
		 toAdd.setPrice(Price);
		 toAdd.setLength(Length);
		 System.out.println(email);
		 Tenant seller = userService.searchbyEmailTenant(email);
		 String sellerID = "";
		 if (seller == null)
		 {
			 sellerID = "-1";
		 }
		 else
		 {
			 sellerID = seller.IDString(); 
		 }
		 toAdd.setSellerID(sellerID);
		 
		 
		 itemList.save(toAdd);
		 
		 //int itemIndex = toAdd.getId();
		 
		 
		 
		 String writeTo = "src/main/resources/static/images/Item_Images/" + toAdd.IDString() + ".jpg";
		 System.out.println("writeTo:");
		 System.out.println(writeTo);
		 toAdd.setFilePath("/images/Item_Images/" + toAdd.IDString() + ".jpg");
		 itemList.saveAndFlush(toAdd);
		 
		 
		 
		 if (file != null)
		 {
	        // save the file on the local file system
	        try {
	            Path path = Paths.get(writeTo);
	            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
	            System.out.println("Written");
	        } catch (IOException e) {
	            System.out.println("That file doesn't work");
	        }
		 }
		 String redirect = "redirect:/home/" + email;
		 return new ModelAndView(redirect);
	 }
	 
	
	






}
