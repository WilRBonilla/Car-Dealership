package dev.bonilla.model;

public class Loan {
	private int userid;
	private Car car;
	private int paid;
	private int value; 
	private int term;
	

	public Loan(int userid, Car car, int paid, int loan, int term){
		super();
		this.userid=userid;
		this.car = car;
		this.setPaid(paid);
		this.value = loan;
		this.term = term;
		
	}

	public int getId() {
		return userid;
	}

	public void setId(int id) {
		this.userid = id;
	}
	
	public Car getCar() {
		return this.car;
	}
	public String setCar(Car c) {
		return this.car.toString();
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}


	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public int getPaid() {
		return paid;
	}

	public void setPaid(int paid) {
		this.paid = paid;
	}

	@Override
	public String toString() {
		return "LOAN DETAILS:\n " + car.toString(1) + "\nAMOUNT PAID: " + paid + "\t\tLOAN AMOUNT: " + value 
				+ " for " + term + " months ";
	}
	
	public String toString(int a) {
		return "LOAN DETAILS FOR USERID: " + userid + "\n"  + car.toString(1) + "\nAMOUNT PAID: " + paid + "\t\tLOAN AMOUNT: " + value 
				+ " for " + term + " months ";
	}
	
	
	
}
