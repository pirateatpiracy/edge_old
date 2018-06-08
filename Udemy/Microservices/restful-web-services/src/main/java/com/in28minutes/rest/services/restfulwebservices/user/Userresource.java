package com.in28minutes.rest.services.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class Userresource {

	@Autowired
	private UserDaoService service;

	@GetMapping(path = "/users")
	public List<User> retriveAllUsers() {
		return service.findAll();
	}

	@GetMapping(path = "/users/{id}")
	public User retrieveuser(@PathVariable int id) {
		User user = service.findOne(id);
		if (user==null)throw new UserNotFoundException("id-"+id);
		return user;
	}

	@PostMapping(path = "/users")
	public ResponseEntity<Object> addUser(@RequestBody User user) {
		User saveduser = service.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveduser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
