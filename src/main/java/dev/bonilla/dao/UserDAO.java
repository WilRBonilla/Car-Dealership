package dev.bonilla.dao;

import java.util.List;

import dev.bonilla.model.User;

public interface UserDAO {
	// MANDATORY
	// Customers
	public abstract User getUser(String name, String pass);
//	public User getUser(String name);
	public abstract User createUser(String name, String pass);
	
	public boolean deleteUser(String name); 
	// Employees
	public abstract List<User> viewAllUsers(String name);
	
//	// OPTIONAL
//	public abstract boolean changeUsername(); // Updating
//	public abstract boolean changeUserPass(); // Updating
//	public abstract boolean changeCustomer(); 
}
