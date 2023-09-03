package com.resttest.demo.user;

import java.net.URI;
import java.util.List;
//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import javax.validation.Valid;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
//import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	public EntityModel<User> getUser(@PathVariable int id) {
		User user = userdao.findOne(id);
		
		
		//"all-users", SERVER_PATH+"USERS" -> in this case if any body changing the url's we need to change here also. it will effect the hateoas implementation
		//so instead of directly fetching from links - we can take links from respective methods. so it will not effect. let's implement that
		EntityModel<User> entityModel = EntityModel.of(user);
		Link link = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).getAllUsers()).withRel("user-list");
//		ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(methodOn(this.getClass(), getAllUsers()));
		entityModel.add(link.withRel("all-users")); //all-users is the name which we refer to
		if(user == null)
			throw new UserNotFoundException("id - "+ id);
		return entityModel;
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public User deleteUser(@PathVariable int id) {
		User user = userdao.deleteById(id);
		if(user == null)
			throw new UserNotFoundException("id - "+ id);
		
		return user;
	}
}
