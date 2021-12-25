package com.revature.movietweeter.service;

import org.mindrot.jbcrypt.BCrypt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.revature.movietweeter.dao.UserDAO;
import com.revature.movietweeter.exception.InvalidLoginException;
import com.revature.movietweeter.model.User;

@Service
public class UserService {
	
	@Autowired
	private UserDAO ud;
	
	public User getUserByUsernameAndPassword(String username, String password) throws InvalidLoginException {
		
		try {
			String pw_hash = ud.getUserfromUsername(username).getPassword();
			
			if (!BCrypt.checkpw(password, pw_hash)) {
				throw new InvalidLoginException("Username and/or password is incorrect");
			}
			
			User user = ud.getUserByUserNameAndPassword(username, pw_hash);
			
			return user;
		} catch(DataAccessException e) {
			throw new InvalidLoginException("Username and/or password is incorrect");

		}
		
	}
	
	public User createUser(String username, String password) throws InvalidLoginException {
		String pw_hash = BCrypt.hashpw(password, BCrypt.gensalt(8));
		
		try {
			User createdUser = this.ud.addUser(username, pw_hash);
			return createdUser;
		} catch( DataAccessException e) {
			throw new InvalidLoginException("Username and/or password is incorrect");
		}
	}
	
}