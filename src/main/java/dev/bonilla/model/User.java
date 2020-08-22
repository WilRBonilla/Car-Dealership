package dev.bonilla.model;


public class User {
	
	// PK
	private int id;
	private String name;
	private String password;
	private boolean customer=true;

	
	public User( String nam, String pass, boolean c){

		this.name = nam;
		this.password = pass;
		this.customer = c;
		
	}
	
	public User(int ident, String nam, String pass, boolean c){
		this.id = ident;
		this.name = nam;
		this.password = pass;
		this.customer = c;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isCustomer() {
		return customer;
	}

	public void setCustomer(boolean customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (customer != other.customer)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	

}
