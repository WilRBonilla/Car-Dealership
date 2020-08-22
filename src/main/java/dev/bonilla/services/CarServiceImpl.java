package dev.bonilla.services;

import java.util.List;

import dev.bonilla.dao.CarDAO;
import dev.bonilla.dao.CarDAOImpl;
import dev.bonilla.model.Car;

public class CarServiceImpl implements CarService{

	CarDAO cd = new CarDAOImpl();
	public Car getCar(int cid) {
		return cd.getCar(cid);
	}

	public List<Car> getAllCars() {
		return cd.getAllCars() ;
	}

	public List<Car> getMyCars(int uid) {
		
		return cd.getMyCars(uid);
	}

	public List<Car> getMyCars(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean deleteCar(int cid) {
		return cd.deleteCar(cid);
	}

	public boolean deleteCar(String make, String model) {
		return cd.deleteCar(make, model);
	}
	public boolean updateCar(Car car) {
		return cd.updateCar(car);
	}

	public boolean createCar(Car car) {
		return cd.createCar(car);
	}

	
}

