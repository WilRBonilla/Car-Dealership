package dev.bonilla.daotesting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import dev.bonilla.dao.CarDAO;
import dev.bonilla.dao.CarDAOImpl;
import dev.bonilla.model.Car;
@TestInstance(Lifecycle.PER_CLASS)
class CarDAOTests {
CarDAO cd = new CarDAOImpl();
	@Test
	public void carNotFound() {
		Car expected = null;
		Car actual = cd.getCar(9000);
	
		assertEquals(expected, actual);
	}
	@Test
	public void carFound() {
		Car expected = new Car(101, 2017, "Honda", "Civic", 15000, 30000);
		Car actual = cd.getCar(101);
		
		assertEquals(expected.toString(), actual.toString());
		
	}
	@Test
	public void carListFound() {

		List<Car> actual = cd.getAllCars();
		
		Assertions.assertTrue(actual != null);
	
	}
	@Test
	public void getMyCarsNotFound() {
		List<Car> expected = new ArrayList<Car>();
		List<Car> actual = cd.getMyCars(9000);
		
		Assertions.assertEquals(expected, actual);
	}
	@Test
	public void getMyCarsFound() {
		List<Car> actual = cd.getMyCars(4);
		Assertions.assertTrue(actual != null);
	}
	@Test
	public void deleteCar() {
		boolean actual = cd.deleteCar(999);
		Assertions.assertTrue(actual);
	}
	
	@Test
	public void updateCarSuccess() {
		Car car = new Car(101, 2017, "Honda", "Civic", 15000, 30000);
		boolean actual = cd.updateCar(car);
		Assertions.assertEquals(true, actual);
		
	}
	
	@Test
	public void createCarSuccess() {
		Car car = new Car(999, 1999, "Bat", "Mobile", 1000000, 500);
		boolean actual = cd.createCar(car);
		
		Assertions.assertEquals(true, actual);
	}
	
	
	@AfterAll
	public void cleanUp() {
		cd.deleteCar("Bat", "Mobile");
		
	}
}


