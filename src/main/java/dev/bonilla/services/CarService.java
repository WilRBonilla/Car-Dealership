package dev.bonilla.services;

import java.util.List;

import dev.bonilla.model.Car;

public interface CarService {
	public abstract Car getCar(int cid);
	public abstract List<Car> getAllCars();
	public abstract List<Car> getMyCars(int uid);
	public abstract List<Car> getMyCars(String username);
	// Employees only
	public abstract boolean deleteCar(int cid);
	public abstract boolean deleteCar(String make, String model);
	public abstract boolean updateCar(Car car);
	public abstract boolean createCar(Car car);
	
}
