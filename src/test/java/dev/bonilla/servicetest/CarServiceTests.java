package dev.bonilla.servicetest;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import dev.bonilla.dao.CarDAO;
import dev.bonilla.dao.CarDAOImpl;
import dev.bonilla.model.Car;
import dev.bonilla.services.CarService;
import dev.bonilla.services.CarServiceImpl;

@TestInstance(Lifecycle.PER_CLASS)
class CarServiceTests {

	CarService cs = new CarServiceImpl();
	CarDAO cd = new CarDAOImpl();

	@AfterEach
	void tearDown() throws Exception {
		cs.deleteCar("Burick", "Encore");
	}

	@Test
	public void getCarFail() {
		Car actual = cs.getCar(7777);
		Assertions.assertEquals(null, actual);

	}

	@Test
	public void getCarSuccess() {
		Car actual = cs.getCar(101);
		Car expected = new Car(101, 2017, "Honda", "Civic", 15000, 30000);
		Assertions.assertEquals(expected.toString(), actual.toString());
	}

	@Test
	public void getAllCarsSuccess() {
		List<Car> actual = cs.getAllCars();

		Assertions.assertTrue(actual != null);
	}

	@Test
	public void getUserCarsSuccess() {
		List<Car> actual = cs.getMyCars(4);
		Assertions.assertTrue(actual != null);
	}
	
	

	@Test
	public void CreateNewCar() {
		Car car = new Car(7777, 2019, "Burick", "Encore", 4500, 200);
		boolean actual = cs.createCar(car);

		Assertions.assertTrue(actual);
	}

	@Test
	public void UpdateCarSuccess() {
		Car car = new Car(7777, 2019, "Burick", "Encore", 45000, 2000);
		boolean actual = cs.updateCar(car);
		Assertions.assertTrue(actual);

	}

	@Test
	public void DeleteCarIDSuccess() {

		boolean actual = cs.deleteCar(7777);
		Assertions.assertTrue(actual);
	}

	@Test
	public void DeleteCarMMSuccess() {
		boolean actual = cs.deleteCar("Burick", "Encore");
		Assertions.assertTrue(actual);
	}

}
