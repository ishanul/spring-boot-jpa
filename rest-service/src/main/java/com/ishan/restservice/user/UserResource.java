package com.ishan.restservice.user;

import java.net.URI;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ishan.restservice.user.exception.UserNotFoundException;

@RestController
public class UserResource {

	@Autowired
	private UserDAOService userDAOService;
	
	
	@GetMapping(path = "user")
	public List<User> retrieveUsers() {
		return userDAOService.findUsers();
	}

	@GetMapping(path = "user/{id}")
	public User retrieveUser(@PathVariable int id) {
		User user = userDAOService.findOne(id);
		if(user == null){
			throw new UserNotFoundException("id : " + id);
		}
	
		return user;
	}

	@PostMapping(path = "user")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userDAOService.saveOne(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{path}").buildAndExpand(savedUser.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path = "user/{id}")
	public void deleteOne(@PathVariable int id){
		User user = userDAOService.deleteOne(id);
		if(user == null){
			throw new UserNotFoundException("id : " + id);
		}
	
	}
}
