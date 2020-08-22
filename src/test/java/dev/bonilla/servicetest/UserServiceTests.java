package dev.bonilla.servicetest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import dev.bonilla.dao.UserDAO;
import dev.bonilla.dao.UserDAOImpl;
import dev.bonilla.model.User;
import dev.bonilla.services.UserService;
import dev.bonilla.services.UserServiceImpl;
@TestInstance(Lifecycle.PER_CLASS)
class UserServiceTests {
	
	@Mock
	UserDAOImpl userdao;
	@InjectMocks
	UserServiceImpl userservice;
	
	UserService us = new UserServiceImpl();
	UserDAO ud = new UserDAOImpl();
	@BeforeAll
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getUserNotFound() {
		User mockUser = null;
		Mockito.when(userdao.getUser("Name", "Passive")).thenReturn(mockUser);
		
		User actual = this.userservice.getUser("Name", "Pass");
		
		Assertions.assertEquals(mockUser, actual );
	}
	@Test
	public void getUserFound() {
		// User object takes the u_id, the username, password, and customer boolean
		User mockUser2 = new User(1, "William", "password", false);
		
		// Mocking the dao, not the service layer.
		Mockito.when(userdao.getUser("William", "Bonilla")).thenReturn(mockUser2);
		// This should be mocked by MockUser2
		User actual = this.userservice.getUser("William", "password");
		
		Assertions.assertEquals(mockUser2, actual );
	}
	
	@Test
	public void newUsernameTaken() {
		User expected = null;
		User actual = us.createUser("William", "asfsaf");
		
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	public void createNewUserSuccess() {
		
		User actual = us.createUser("Andy", "password");
		User expected = us.getUser("Andy", "password");
		
		Assertions.assertEquals(expected.toString(), actual.toString());
	}
	
	@AfterAll
	public void cleanup() {
		ud.deleteUser("Andy");
	}

}
