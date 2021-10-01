package com.springboot.app.Controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springboot.app.Databases.UserDatabase;
import com.springboot.app.Entities.User;

import io.swagger.annotations.Api;





@Api(value="User Controller", description="Written by Samuel Mason")

@RestController
public class UserController {
	
	
	@Autowired
	UserDatabase db;
	
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable Integer id)
	{
		return db.findById(id).get();
	}
	
	
	
	@RequestMapping("/users")
		List<User> hello()
	{	
		return db.findAll();	
	}
		
	@PostMapping("/user")
	public User createUser(@RequestBody User u)
		{
			db.save(u);
			return u;
		}
	
	@PutMapping("/user/{id}")
	public User updateUser(@RequestBody User u, @PathVariable Integer id)
	{
		User prevPassword = db.findById(id).get();
		db.save(prevPassword);
		return prevPassword;
	}
	
	
	@DeleteMapping("/user/{id}")
	public String deleteUser(@PathVariable Integer id)
	{
		db.deleteById(id);
		return "deleted" + id;
	}
	
	
	
	
	
}
