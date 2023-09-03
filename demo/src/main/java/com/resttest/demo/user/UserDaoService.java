package com.resttest.demo.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class UserDaoService {
	
	@Autowired
	private UserRepository userRepository;
	
	
//	private static List<User> users = new ArrayList<>();
//	private static int count = 3;
//	static {
//		users.add(new User(1,"Mahesh",new Date()));
//		users.add(new User(2,"Sankar",new Date()));
//		users.add(new User(3,"akhil",new Date()));
//	}
	
	public List<User> findAll(){
//		return users;
		return userRepository.findAll();
	}
	
	public User save(User user) {
//		if(user.getId()==null) {
//			user.setId(++count);
//		}
//		users.add(user);
//		return user;
		return userRepository.save(user);
	}
	
	public User findOne(int id) {
//		for(User user: users) {
//			if(user.getId()==id) {
//				return user;
//			}
//		}
//		return null;
		List<User> userList = findAll();
		for(User user : userList) {
			if(user.getId().equals(id)) {
				return user;
			}	
		}
		return null;
	}
	
	
	public User deleteById(int id) {
//		Iterator<User> iterator = users.iterator();
//		while(iterator.hasNext()) {
//			User user = iterator.next();
//			if(user.getId()==id) {
//				iterator.remove();
//				return user;
//			}
//		}
//		return null;
		List<User> userList = findAll();
		for(User user : userList) {
			if(user.getId().equals(id)) {
				userRepository.delete(user);
			}	
		}
		return null;
		
		
	}

	public User findById(int userId) {
		// TODO Auto-generated method stub
		return userRepository.findById(userId);
	}
	
}
