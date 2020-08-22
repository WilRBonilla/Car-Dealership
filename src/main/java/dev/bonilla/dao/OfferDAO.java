package dev.bonilla.dao;


import java.util.List;

import dev.bonilla.model.Offer;

public interface OfferDAO {
	// READ

	public Offer getOffer(int uid, int cid);
	// Customers
	public abstract boolean createOffer(int uid, int cid, int value);
	public abstract boolean updateOffer(int uid, int cid, int value);
	public abstract boolean removeOffer(int uid, int cid);
	
	// Employees
	public abstract List<Offer> viewCarOffers(int cid);
	public abstract boolean approveOffer(int uid, int cid);
	public abstract boolean declineOffer(int uid, int cid);
	public boolean finalizeSale(int uid, int cid, int down, int term);
}
