package dev.bonilla.model;

public class Car {
	// Mandatories
	// Primary Key
	private int cid;
	private int msrp;	
	// Optional searchable fields
	private String make = "";
	private String model = "";
	private int year;
	private int mileage;
	
	
	// Constructor for offers
	public Car(int ident, int yr, String mak, String mode,  int pric, int miles){
		super();
		this.cid = ident;
		this.make = mak;
		this.model = mode;
		this.year = yr;
		this.msrp = pric;
		this.mileage = miles;
	}
	// Constructor for bank
	public Car(int ident, int yr, String mak, String mode){
		super();
		this.cid = ident;
		this.make = mak;
		this.model = mode;
		this.year = yr;

	}
	public int getId() {
		return cid;
	}


	public void setId(int id) {
		this.cid = id;
	}


	public int getMsrp() {
		return msrp;
	}


	public void setMsrp(int msrp) {
		this.msrp = msrp;
	}


	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getPrice() {
		return msrp;
	}

	public void setPrice(int price) {
		this.msrp = price;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	@Override
	public String toString() {
		return "CARID:\t"+ cid + "\n" + year + "\t" + make.toUpperCase() + "\t" 
				+ model.toUpperCase() + "\nPRICE: $" + msrp + "\t MILES: " + mileage;
	}
	/**
	 * Argument is any number to indicate that this version of the method is only used by the bank.
	 * @param a is any number
	 * @return
	 */
	public String toString(int a) {
		return "CARID:\t"+ cid + "\n" + year + "\t" + make.toUpperCase() + "\t" + model.toUpperCase() ;
	}
	
	
	
	
	
	
	
	
	
}
