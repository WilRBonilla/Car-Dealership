package dev.bonilla.services;

import java.util.List;

import dev.bonilla.model.User;

public interface UserService {
	// MANDATORY
		// Customers
		public abstract User getUser(String name, String pass);
		public abstract User createUser(String name, String pass);
		
		
		// Employees
//		public abstract List<User> viewAllUsers(String name);
		
		// OPTIONAL
//		public abstract boolean changeUsername(); // Updating
//		public abstract boolean changeUserPass(); // Updating
//		public abstract boolean changeCustomer(); 
}
