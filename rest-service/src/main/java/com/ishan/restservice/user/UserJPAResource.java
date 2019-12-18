package com.ishan.restservice.user;

import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

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
public class UserJPAResource {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping(path = "/jpa/user")
	public List<User> retrieveUsers() {
		return userRepository.findAll();
	}

	@GetMapping(path = "/jpa/user/{id}")
	public User retrieveUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()){
			throw new UserNotFoundException("id : " + id);
		}
	
		return user.get();
	}

	@PostMapping(path = "/jpa/user")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{path}").buildAndExpand(savedUser.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path = "jpa/user/{id}")
	public void deleteOne(@PathVariable int id){
		userRepository.deleteById(id);
		
	
	}
}
