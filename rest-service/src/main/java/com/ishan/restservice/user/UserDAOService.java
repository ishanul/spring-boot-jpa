package com.ishan.restservice.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDAOService {
	private static int userCount = 3;
	private static List<User> users = new ArrayList<>();
	
	static{
		users.add(new User(1, "Ishan", new Date()));
		users.add(new User(2, "Nirma", new Date()));
		users.add(new User(3, "Kian", new Date()));
	}
	
	public List<User> findUsers(){
		return users;
	}
	
	public User saveOne(User user){
		if (user.getId() == null){
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}
	
	public User findOne(int id ){
		
		for(User user : users){
			if(user.getId() == id){
				return user;
			}
		}
		return null;
	}
	
	public User deleteOne(int id ){
		Iterator<User> iterator = users.iterator();
		while(iterator.hasNext()){
			User user = iterator.next();
			if(user.getId() == id){
				iterator.remove();
				return user;
			}
		}
		return null;
	}
}
