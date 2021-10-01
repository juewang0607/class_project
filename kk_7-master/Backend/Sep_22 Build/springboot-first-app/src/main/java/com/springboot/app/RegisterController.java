package com.springboot.app;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;



@RestController
public class RegisterController {
	

	//ArrayList<Login> logins = new ArrayList<Login>();
	//static ArrayList<User> userList = new ArrayList<User>();
	 
	@Autowired 
	MyDatabase db;
	
	@Autowired
	 LoginService service;
	


	User searchbyEmail(String email)
	{
		Iterator<User> iter = db.findAll().iterator();
		while (iter.hasNext())
		{
			User cur = iter.next();
			if (cur.email.compareTo(email) == 0)
			{
				return cur;
			}
		}
		return null;
	}
	 
	
	@RequestMapping("/start")
	public ModelAndView Start() {
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("start");
	    return modelAndView;
	}
	
	 
	
	
	
	@RequestMapping("/register")
	public ModelAndView ShowRegisterPage() {
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("register");
	    return modelAndView;
	}
	
	
	
	 @RequestMapping(value="/register", method = RequestMethod.POST)
	 public ModelAndView ShowHomePage(Model model, @RequestParam String email, @RequestParam String password){
		 
		 System.out.println(email + " " + password);
		 Login l = new Login(email,password);
		 User u = new User();
		 u.setEmail(email);
		 u.setPassword(password);
		 //logins.add(l);
		 db.save(u);
		 model.addAttribute("email",email);
		 String redirect = "redirect:register/" + email + "/add_info";
		 return new ModelAndView(redirect);
		 
	 }
	  
	 
	 @RequestMapping(value="/register/{email}/add_info", method = RequestMethod.GET)
			 public ModelAndView ShowAddInfoPage() {
		    ModelAndView modelAndView = new ModelAndView();
		    modelAndView.setViewName("add_info");
		    return modelAndView;
		}
	 
	 
	 
	 @RequestMapping(value="/register/{email}/add_info", method = RequestMethod.POST)
	 public ModelAndView showHomePage(@PathVariable String email, Model model, @RequestParam String FirstName, @RequestParam String LastName, @RequestParam String PhoneNumber, @RequestParam String Address){
		 System.out.println(FirstName + "   " + LastName + "   " + PhoneNumber + "   " + Address);
		 model.addAttribute("Name", FirstName+ " " + LastName);
		 
		 User toAdd = new User(email, FirstName, LastName, PhoneNumber, Address);
		 // db.save(toAdd);
		// userList.add(toAdd);
		 String redirect = "redirect:/login";
		 return new ModelAndView(redirect);
	 }
	 
	 
	 
	 
	 
	
		
		
		@RequestMapping(value="/login", method = RequestMethod.GET)
	    public ModelAndView showLoginPage(Model model){
			ModelAndView modelAndView = new ModelAndView();
		    modelAndView.setViewName("loginpage");
		    return modelAndView;
	    }

		
		 @RequestMapping(value="/login", method = RequestMethod.POST)
		 public ModelAndView showWelcomePage(Model model, @RequestParam String email, @RequestParam String password){
			 
			System.out.println("into the post");
			 boolean isValidUser = validateUsername(email, password);
		        System.out.println("back to the post");
		        System.out.println(email + password);
		        System.out.println(isValidUser);
		        if (!isValidUser) {
		            model.addAttribute("errorMessage", "Invalid Credentials");
		            ModelAndView modelAndView = new ModelAndView();
				    modelAndView.setViewName("loginpage");
				    return modelAndView;
		        }
		        model.addAttribute("name", email);
		        model.addAttribute("password", password);
		        
		        String redirect = "redirect:home/" + email;
		
		        return new ModelAndView(redirect);
		    }
		 
		 
		 
		 
		 
		 
		 

		 
		 
		 @RequestMapping("/UsersList")
		    List<User> showUsers(){
		     System.out.println("Into the get");   
			 return db.findAll();
		    }
	
		 
		 
		 public boolean validateUsername(String givenEmail, String GivenPassword)
			{
				
				System.out.println("Into the LoginService");
				// search for username
				List<User> ll = db.findAll();
				Iterator<User> iter = ll.iterator();
				String neededPassword = "123";
				while (iter.hasNext())
				{
					User cur = iter.next();
					if (cur.email.compareTo(givenEmail) == 0)
					{
						neededPassword = cur.password;
					}
				}
				System.out.println(neededPassword);
				System.out.println(GivenPassword);
				if (neededPassword.compareTo(GivenPassword) == 0)
				{
					return true;
				}
				
				
				
				
				
				return false;
			
			}
		 
		 
		 
		 
		 
		 @RequestMapping(value="/home/{email}/my_info", method = RequestMethod.GET)
		 public String getUserInfo(Model model, @PathVariable(value="email") String email)
		 {
			 User u = searchbyEmail(email);
			 model.addAttribute("email", email);
			 model.addAttribute("Name", u.firstName + " " + u.lastName);
			 model.addAttribute("PhoneNumber", u.phoneNumber);
			 model.addAttribute("Address", u.address);
			 return "my_info";	
		 }
		 
		 
		 
		 
	
	
	
}
