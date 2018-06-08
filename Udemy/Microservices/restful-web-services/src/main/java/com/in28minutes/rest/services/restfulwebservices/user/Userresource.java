package com.in28minutes.rest.services.restfulwebservices.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Userresource {

	@Autowired
	private UserDaoService service;
	
	
	@GetMapping(path="/users")
	public List<User> retriveAllUsers(){
		return service.findAll();
	}
	@GetMapping(path="/users/{id}")
	public User retrieveuser(@PathVariable int id) {
		return service.findOne(id);}
	
	@PostMapping(path="/users")
	public void addUser(@RequestBody User user) {
		service.save(user);
	}
}
