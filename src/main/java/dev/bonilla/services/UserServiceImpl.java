package dev.bonilla.services;

import java.util.List;

import dev.bonilla.dao.UserDAO;
import dev.bonilla.dao.UserDAOImpl;
import dev.bonilla.model.User;

public class UserServiceImpl implements UserService{

	static UserDAO ud = new UserDAOImpl();
	public User getUser(String name, String pass) {
		return ud.getUser(name, pass);
	} 

	public User createUser(String name, String pass) {
		return ud.createUser(name, pass);
	}

//	public List<User> viewAllUsers(String name) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public boolean changeUsername() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	public boolean changeUserPass() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	public boolean changeCustomer() {
//		// TODO Auto-generated method stub
//		return false;
//	}

}
