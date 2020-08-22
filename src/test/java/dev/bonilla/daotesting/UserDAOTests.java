package dev.bonilla.daotesting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;

import dev.bonilla.dao.CarDAO;
import dev.bonilla.dao.LoanDAO;
import dev.bonilla.dao.LoanDAOImpl;
import dev.bonilla.dao.OfferDAO;
import dev.bonilla.dao.OfferDAOImpl;
import dev.bonilla.dao.UserDAO;
import dev.bonilla.dao.UserDAOImpl;
import dev.bonilla.model.User;
@TestInstance(Lifecycle.PER_CLASS)
class UserDAOTests {
	//@Mock
	CarDAO cd;
	@InjectMocks
	
	UserDAO ud = new UserDAOImpl();	
	OfferDAO od = new OfferDAOImpl();
	LoanDAO ld = new LoanDAOImpl();
	
/**
 * 
 * USER DAO Testing
 * 
 */

	
	// getUser method
	
	@Test
	public void emptyStrings() {
		User expected = null;
		User actual = ud.getUser("", "");;
	
		assertEquals(expected, actual);
	}

	@Test
	public void badUserBadPass() {
		User expected = null;
		User actual = ud.getUser("Fried", "Jello");;
	
		assertEquals(expected, actual);
	}
	
	@Test
	public void goodUserBadPass() {
		User expected = null;
		User actual = ud.getUser("William", "asdf");;
	
		assertEquals(expected, actual);
	}
	
	@Test
	public void badUserGoodPass() {
		User expected = null;
		User actual = ud.getUser("asdfasdf", "password");;
	
		assertEquals(expected, actual);
	}
	
	@Test
	public void goodUserGoodPassEmployee() {
		User expected = new User(1, "William", "password", false);
		User actual = ud.getUser("William", "password");;
	
		assertEquals(expected, actual);
	}
	@Test
	public void goodUserGoodPassCustomer() {
		User expected = new User(4, "Ron", "password", true);
		User actual = ud.getUser("Ron", "password");;
	
		assertEquals(expected, actual);
	}
	
	// createUser()
	@Test
	public void usernameNotAvailable() {
		User expected = null;
		User actual = ud.createUser("Ron", "password1");;
	
		assertEquals(expected, actual);
	}
	
	@Test
	public void creationSuccess() {
		
		User user = ud.createUser("Anne", "password1");;
	
		Assertions.assertTrue(user != null);
	}
	
	@AfterAll
	public void cleanup() {
		
		Assertions.assertEquals(true, ud.deleteUser("Anne"));
	}

	;
	
}
