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

import com.springboot.app.Databases.AdminDatabase;
import com.springboot.app.Databases.LandLordDatabase;
import com.springboot.app.Databases.TenantDatabase;
import com.springboot.app.Databases.UserDatabase;
import com.springboot.app.Entities.Admin;
import com.springboot.app.Entities.LandLord;
import com.springboot.app.Entities.Tenant;
import com.springboot.app.Entities.User;
import com.springboot.app.Services.LoginService;
import com.springboot.app.Services.RegisterService;

import io.swagger.annotations.Api;


@Api(value="Register Controller", description="Written by Samuel Mason")
@RestController
public class RegisterController {
	

	//ArrayList<Login> logins = new ArrayList<Login>();
	//static ArrayList<User> userList = new ArrayList<User>();
	 
	@Autowired 
	UserDatabase db;
	
	@Autowired
	TenantDatabase tenantDb;
	
	@Autowired
	LandLordDatabase landlordDb;
	
	@Autowired
	AdminDatabase adminDb;
	
	@Autowired
	 LoginService loginService;
	
	@Autowired 
	RegisterService registerService;
	


	User searchbyEmail(String email)
	{
		Iterator<User> iter = db.findAll().iterator();
		while (iter.hasNext())
		{
			User cur = iter.next();
			if (cur.getEmail().compareTo(email) == 0)
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
	
	 
	
	
	
	@RequestMapping("/register/tenant")
	public ModelAndView ShowRegisterTenantPage() {
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("register");
	    return modelAndView;
	}
	
	
	
	
	 @RequestMapping(value="/register/tenant", method = RequestMethod.POST)
	 public ModelAndView ShowHomeTenantPage(Model model, @RequestParam String email, @RequestParam String password, @RequestParam String FirstName, @RequestParam String LastName, @RequestParam String PhoneNumber, @RequestParam String Address, @RequestParam String Unit){
		 
		 System.out.println(email + " " + password);
		 User u = new User();
		 u.setEmail(email);
		 u.setPassword(password);
		 u.setType("Tenant");
		 
		 Tenant t = new Tenant();
		 t.setEmail(email);
		 t.setPassword(password);
		 t.setfName(FirstName);
		 t.setlName(LastName);
		 t.setApartment(Address);
		 t.setPhone(PhoneNumber);
		 t.setUnit(Unit);
		 registerService.checkIfEmailInUse(u);
		 tenantDb.save(t);
		 //Add new redirect for email in use
		 model.addAttribute("email",email);
		 String redirect = "redirect:/login";
		 return new ModelAndView(redirect);
		 
	 }
	 
	 @RequestMapping("/register/landlord")
		public ModelAndView ShowRegisterLandlordPage() {
		    ModelAndView modelAndView = new ModelAndView();
		    modelAndView.setViewName("registerL");
		    return modelAndView;
		}
		
		
		
		
		 @RequestMapping(value="/register/landlord", method = RequestMethod.POST)
		 public ModelAndView ShowHomePageLandlord(Model model, @RequestParam String email, @RequestParam String password, @RequestParam String FirstName, @RequestParam String LastName, @RequestParam String PhoneNumber, @RequestParam String Address){
			 
			 System.out.println(email + " " + password);
			 User u = new User();
			 u.setEmail(email);
			 u.setPassword(password);
			 u.setType("Landlord");
			 
			 LandLord t = new LandLord();
			 t.setEmail(email);
			 t.setPassword(password);
			 t.setfName(FirstName);
			 t.setlName(LastName);
			 t.setApartment(Address);
			 t.setPhone(PhoneNumber);
			 registerService.checkIfEmailInUse(u);
			 landlordDb.save(t);
			 //Add new redirect for email in use
			 model.addAttribute("email",email);
			 String redirect = "redirect:/login";
			 return new ModelAndView(redirect);
			 
		 }
		 
		 @RequestMapping("/register/admin")
			public ModelAndView ShowRegisterAdminPage() {
			    ModelAndView modelAndView = new ModelAndView();
			    modelAndView.setViewName("registerA");
			    return modelAndView;
			}
			
			
			
			
			 @RequestMapping(value="/register/admin", method = RequestMethod.POST)
			 public ModelAndView ShowHomePageAdmin(Model model, @RequestParam String email, @RequestParam String password, @RequestParam String FirstName, @RequestParam String LastName, @RequestParam String PhoneNumber){
				 
				 System.out.println(email + " " + password);
				 User u = new User();
				 u.setEmail(email);
				 u.setPassword(password);
				 u.setType("Admin");
				 
				 Admin t = new Admin();
				 t.setEmail(email);
				 t.setPassword(password);
				 t.setfName(FirstName);
				 t.setlName(LastName);
				 t.setPhone(PhoneNumber);
				 registerService.checkIfEmailInUse(u);
				 adminDb.save(t);
				 //Add new redirect for email in use
				 model.addAttribute("email",email);
				 String redirect = "redirect:/login";
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
		 String redirect = "redirect:/login";
		 return new ModelAndView(redirect);
	 }
	 
	 
	 
	 
		 @RequestMapping("/UsersList")
		    List<User> showUsers(){
		     System.out.println("Into the get");  
			 List<User> list = db.findAll();
			 User temp;
			 for(int x = 0; x < list.size(); x++) {
				 for(int y = 0; y < list.size(); y++) {
					 if(alphaSort(list.get(x).getEmail(),list.get(y).getEmail(),0) == 0) {
					 	temp = list.get(x);
					 	list.set(x, list.get(y));
					 	list.set(y, temp);
					 }
				 }
			 }
			 return list;
		    }
	
		 
		 
		 
		 
		 
		 
		 
		 public int alphaSort(String name1, String name2, int x) {
			 if(name1.charAt(x)>name2.charAt(x)) {
				 return 1;
			 }
			 else if(name1.charAt(x)<name2.charAt(x)) {
				 return 0;
			 }
			 else {
				 if(name1.length()-1 == x) {
					 return 0;
				 }
				 else if(name2.length()-1 == x) {
					 return 1;
				 }
				 else {
				 	return alphaSort(name1, name2, x+1);
				 }
			 }
		 }
		 
		 
		  
}
