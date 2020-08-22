package dev.bonilla.model;

public class Offer {

	// FK, or CK? w/ CarID+UserID?
	private int carID;
	private int userID;
	private int value;
	private String offerStatus;
	
	public Offer(int userid, int carid, int value, int status){
		super();
		this.carID = carid;
		this.userID = userid;
		this.value = value;
		if (status == 0) {
			this.offerStatus = "DENIED";
		} else if (status == 2) {
			this.offerStatus = "ACCEPTED";
		} else
			this.offerStatus = "PENDING";
	}

	public int getCarID() {
		return carID;
	}

	public void setCarID(int carID) {
		this.carID = carID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getOfferStatus() {
		return offerStatus;
	}

	public void setOfferStatus(String offerStatus) {
		this.offerStatus = offerStatus;
		
	}
	public void setOfferStatus(int offerStatus) {
		if (offerStatus == 0) {
			this.offerStatus = "DENIED";
		} else if (offerStatus == 2) {
			this.offerStatus = "ACCEPTED";
		} else
			this.offerStatus = "PENDING";
	}

	@Override
	public String toString() {
		return "OFFER:[$" + value + " \tSTATUS:" + offerStatus + "\tUSERID: " + userID + "]";
		
	}

	
	
	
	
}
