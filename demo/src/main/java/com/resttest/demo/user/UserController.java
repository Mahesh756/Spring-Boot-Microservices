package com.resttest.demo.user;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {
	@Autowired
	private UserDaoService userdao;
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userdao.findAll();
	}
	
	@PostMapping("/saveUser")
	public ResponseEntity<Object> saveUser(@Valid  @RequestBody User user) {
		User savedUser = userdao.save(user);
//		append the id to the uri of the saveUser
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/singleUser/{id}")
	public User getUser(@PathVariable int id) {
		User user = userdao.findOne(id);
		if(user == null)
			throw new UserNotFoundException("id - "+ id);
		
		return user;
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public User deleteUser(@PathVariable int id) {
		User user = userdao.deleteById(id);
		if(user == null)
			throw new UserNotFoundException("id - "+ id);
		
		return user;
	}
	
	
	@PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable int userId, @RequestBody User updatedUser) {
        // Find the existing user record by ID
        User existingUser = userdao.findById(userId);
        // Update fields with new data
        existingUser.setName("Sravani");
        // Save the updated record
        userdao.save(existingUser);
        return ResponseEntity.ok(existingUser);
        
        }
}
	

