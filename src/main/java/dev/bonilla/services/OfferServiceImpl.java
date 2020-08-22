package dev.bonilla.services;

import java.util.List;
import java.util.Map;

import dev.bonilla.dao.OfferDAO;
import dev.bonilla.dao.OfferDAOImpl;
import dev.bonilla.model.Car;
import dev.bonilla.model.Offer;

public class OfferServiceImpl implements OfferService {

	OfferDAO od = new OfferDAOImpl();


	public Offer getOffer(int uid, int cid) {
		return od.getOffer(uid, cid);
	}

	public boolean createOffer(int uid, int cid, int value) {
		return od.createOffer(uid, cid, value);
		 
	}

	public boolean updateOffer(int uid, int cid, int value) {
		return od.updateOffer(uid, cid, value);
	}

	public boolean removeOffer(int uid, int cid) {
		return od.removeOffer(uid, cid);
	}


	public boolean approveOffer(int uid, int cid) {
		return od.approveOffer(uid, cid);
	}

	public boolean declineOffer(int uid, int cid) {
		return od.declineOffer(uid, cid);
	}

	// for employees
	public List<Offer> viewCarOffers(int cid) {		
		return od.viewCarOffers(cid);
	}

	public boolean finalizeSale(int uid, int cid, int down, int term) {
		return od.finalizeSale(uid, cid, down, term);
	}

}
