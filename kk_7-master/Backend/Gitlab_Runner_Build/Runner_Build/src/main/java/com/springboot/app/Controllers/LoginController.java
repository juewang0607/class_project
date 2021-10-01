package com.springboot.app.Controllers;



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

import com.springboot.app.Databases.UserDatabase;
import com.springboot.app.Services.LoginService;
import com.springboot.app.Services.RegisterService;




@RestController
public class LoginController {

	
	@Autowired 
	UserDatabase db;
	
	@Autowired
	 LoginService loginService;
	
	@Autowired 
	RegisterService registerService;
	
	
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
    public ModelAndView showLoginPage(Model model){
		ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("loginpage");
	    return modelAndView;
    }
	
	
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	 public ModelAndView showWelcomePage(Model model, @RequestParam String email, @RequestParam String password){
		 boolean isValidUser = loginService.validateUsername(email, password);;
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
	 
	 
	 
	 
	
	
	
	@RequestMapping(value="/login/forgot-password", method = RequestMethod.GET)
	 public ModelAndView showforgotPasswordPage(Model model)
	 {
		 ModelAndView modelAndView = new ModelAndView();
		 modelAndView.setViewName("forgot-password");
		 return modelAndView;
	 }
	 
	 
	 @RequestMapping(value="/login/forgot-password", method = RequestMethod.POST)
	 public ModelAndView changePassword(Model model, @RequestParam String email)
	 {
		
		 String redirect = "redirect:forgot-password/" + email;
		 
		 return new ModelAndView(redirect);
	 }
	 
	 @RequestMapping(value="/login/forgot-password/{email}", method = RequestMethod.GET)
	 public ModelAndView showNewPasswordInput(@PathVariable String email)
	 {
		 ModelAndView modelAndView = new ModelAndView();
		 modelAndView.setViewName("update-password");
		 return modelAndView;
		 
	 }

	 
	 
	 
	 
	 
	 
	 
	 @RequestMapping(value="/login/forgot-password/{email}", method = RequestMethod.POST)
	 public ModelAndView setPassword(@PathVariable String email, @RequestParam String password){
		
		 loginService.changePassword(email, password);
		 
		 String redirect = "redirect:/login";
		 return new ModelAndView(redirect);
	 }
	
	
	
	
	
	
	
}
