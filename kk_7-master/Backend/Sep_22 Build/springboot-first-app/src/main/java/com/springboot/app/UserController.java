package com.springboot.app;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;






@RestController
public class UserController {
	
	
	@Autowired
	MyDatabase db;
	
	@GetMapping("/user/{id}")
	User getUser(@PathVariable Integer id)
	{
		return db.findById(id).get();
	}
	
	
	
	@RequestMapping("/users")
		List<User> hello()
	{	
		return db.findAll();	
	}
		
	@PostMapping("/user")
	User createUser(@RequestBody User u)
		{
			db.save(u);
			return u;
		}
	
	@PutMapping("/user/{id}")
	User updateUser(@RequestBody User u, @PathVariable Integer id)
	{
		User prevPassword = db.findById(id).get();
		db.save(prevPassword);
		return prevPassword;
	}
	
	
	@DeleteMapping("/user/{id}")
	String deleteUser(@PathVariable Integer id)
	{
		db.deleteById(id);
		return "deleted" + id;
	}
	
	
	
	
	
}
