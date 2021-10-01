package com.springboot.app.Controllers;

import org.springframework.beans.factory.annotation.Autowired;



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
import com.springboot.app.Databases.MessageDatabase;
import com.springboot.app.Databases.TenantDatabase;
import com.springboot.app.Databases.UserDatabase;

import io.swagger.annotations.Api;


@Api(value="Manage Database Controller", description="Written by Samuel Mason")
@RestController
public class ManageDatabaseController {


	@Autowired 
	UserDatabase db;
	
	@Autowired 
	ItemDatabase itemList;
	
	
	@Autowired
	TenantDatabase tdb;
	
	@Autowired
	MessageDatabase mdb;
	
	
	
	@RequestMapping(value="/home/{email}/manageDatabase", method = RequestMethod.GET)
	 public ModelAndView getUserHomepage(Model model, @PathVariable(value="email") String email)
	 {
		ModelAndView modelAndView = new ModelAndView();
		model.addAttribute("Email",email);
	    modelAndView.setViewName("manage_database");  
	    return modelAndView;
	 }
	
	
	@RequestMapping(value="/home/{email}/manageDatabase", method = RequestMethod.POST)
	 public ModelAndView postUserHomepage(Model model, @PathVariable(value="email") String email, @RequestParam String goingTo)
	 {
		ModelAndView modelAndView = new ModelAndView();
				
		if (goingTo.compareTo("User Database") == 0)
		{
			db.deleteAll();
			tdb.deleteAll();
			
		}
		
		if (goingTo.compareTo("Item Database") == 0)
		{
			itemList.deleteAll();
		}
		
		
		
		if (goingTo.compareTo("Message Database") == 0)
		{
			mdb.deleteAll();
		}
		
		
		
		String redirect = "redirect:/home/" + email;
		modelAndView.setViewName(redirect);
	    return modelAndView;
	 }
	
	
	
	
	
	
}
