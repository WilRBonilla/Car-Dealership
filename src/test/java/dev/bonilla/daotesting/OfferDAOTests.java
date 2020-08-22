package dev.bonilla.daotesting;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import dev.bonilla.dao.LoanDAO;
import dev.bonilla.dao.LoanDAOImpl;
import dev.bonilla.dao.OfferDAO;
import dev.bonilla.dao.OfferDAOImpl;
import dev.bonilla.model.Offer;
@TestInstance(Lifecycle.PER_CLASS)
class OfferDAOTests {
	OfferDAO od = new OfferDAOImpl();
	LoanDAO ld = new LoanDAOImpl();
	@Test
	void getOfferFound() {
		String actual = od.getOffer(4, 101).toString();
		String expected = new Offer(4, 101, 13000, 1).toString();
		
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	public void creationFailure() {
		boolean actual = od.createOffer(999, 999, 9999);
		Assertions.assertTrue(actual == false);
	}
	
	@Test
	public void creationSuccess() {
		boolean actual = od.createOffer(2, 102, 1);
		Assertions.assertTrue(actual);
	}
	
	@Test
	public void updateOfferSuccess() {
		boolean actual = od.updateOffer(2, 102, 2);
		Assertions.assertTrue(actual);
	}
	
	
	@Test
	public void viewCarOffersSuccess() {
		List<Offer> actual = od.viewCarOffers(101);
		Assertions.assertTrue(actual != null);
	}
	
	@Test
	public void viewCarOffersNull() {
		List<Offer> actual = od.viewCarOffers(1);
		List<Offer> expected = new ArrayList<Offer>();
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	public void declineOfferSuccess() {
		boolean actual = od.declineOffer(2, 102);
		Assertions.assertTrue(actual);
	}
	@Test
	public void approveOfferSuccess() {
		boolean actual = od.approveOffer(2, 102);
		Assertions.assertTrue(actual);
		
	}
	
	@Test
	public void finalizeSaleSuccess() {
		// Need to clean up the finalzied sale from the bank.
		boolean actual = od.finalizeSale(2, 102, 0, 38);
		Assertions.assertTrue(actual);
	}
	
	@Test
	public void deletionSuccess() {
		 boolean actual = od.removeOffer(2, 102);
		 Assertions.assertTrue(actual);
	}
	@AfterAll
	public void cleanup() {
		// Cleans up offer table
		od.removeOffer(2, 102);
		// Cleans up bank table
		ld.deleteLoan(2, 102);
	}
	
	
	
	
}
