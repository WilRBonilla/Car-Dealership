package dev.bonilla.dao;

import java.util.List;

import dev.bonilla.model.Car;


/**
 * @author willi
 *
 * CRUD - Create, Read, Update, Delete
 *
 *
 *
 */
public interface CarDAO {
	// Customers & Users
	public abstract Car getCar(int cid);
	public abstract List<Car> getAllCars();
	public abstract List<Car> getMyCars(int uid);
	public abstract List<Car> getMyCars(String username);
	// Employees only
	public abstract boolean deleteCar(int cid);
	public boolean deleteCar(String make, String model);
	public abstract boolean updateCar(Car car);
	public abstract boolean createCar(Car car);

	
	
	
	
	
	
	
//	// optional for customers
//	public abstract List<Car> getCarsByYear();
//	public abstract List<Car> getCarsByPrice();
//	public abstract List<Car> getCarsByMake();
	
	// optional for staff
	
//	public abstract List<Car> updateCarOffers();
	
	
	
	
}
