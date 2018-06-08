/**
 * 
 */
package com.in28minutes.rest.services.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * @author Leadics-18-5
 *
 */
@Component
public class UserDaoService {
 private static List<User> users=new ArrayList<>();
 
 private static int usersCount=3;
 static {
	 users.add(new User(1,"Adam",new Date()));
	 users.add(new User(2,"Eve",new Date()));
	 users.add(new User(3,"Jack",new Date()));
 }
 
 
 @SuppressWarnings("unlikely-arg-type")
public User findOne(int id){
	 
	for (User user:users)
		if(user.getId()==id)
			return user;
	return null;}
 
 public User save(User user) {
	 if(user.getId()==null)
		 user.setId(++usersCount);
	 users.add(user);
	return user;}
 
 public List<User> findAll(){
		return users;}


 
}
