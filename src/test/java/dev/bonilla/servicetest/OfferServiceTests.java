package dev.bonilla.servicetest;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dev.bonilla.dao.LoanDAO;
import dev.bonilla.dao.LoanDAOImpl;
import dev.bonilla.model.Offer;
import dev.bonilla.services.OfferService;
import dev.bonilla.services.OfferServiceImpl;

class OfferServiceTests {
	OfferService os = new OfferServiceImpl();
	LoanDAO ld = new LoanDAOImpl();
	@AfterEach
	void tearDown() throws Exception {
		ld.deleteLoan(62, 103);
	}

	@Test
	public void gettOfferSuccess() {
		Offer actual = os.getOffer(4, 101);
		Offer expected = new Offer(4, 101, 13000, 1);
		
		Assertions.assertEquals(expected.toString(), actual.toString());
	}
	@Test
	public void viewCarOffersSuccess() {
		List<Offer> actual = os.viewCarOffers(4);
		Assertions.assertTrue(actual != null);
	}

	@Test
	public void createOfferfail() {
		boolean actual = os.createOffer(62, 3, 1);
		Assertions.assertTrue(actual==false);
	}
	
	@Test
	public void createOffer() {
		boolean actual = os.createOffer(62, 103, 1);
		Assertions.assertTrue(actual);
	}
	
	@Test
	public void updateOffer() {
		boolean actual = os.updateOffer(62, 103, 10000);
		Assertions.assertTrue(actual);
	}
	@Test
	public void declineAnOffer() {
		boolean actual = os.declineOffer(62, 103);
		Assertions.assertTrue(actual);
	}
	
	
	@Test
	public void approveAnOffer() {
		boolean actual = os.approveOffer(62, 103);
		Assertions.assertTrue(actual);
	}
	@Test
	public void finalizeSaleSuccess() {
		boolean actual = os.finalizeSale(62, 103, 50, 99);
		Assertions.assertTrue(actual);
	}
	
	@Test
	public void rescindOffer() {
		boolean actual = os.removeOffer(62, 103);
		Assertions.assertTrue(actual);
	}
	
}
