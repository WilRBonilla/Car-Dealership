package dev.bonilla.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.bonilla.logging.MyLogger;
import dev.bonilla.model.Car;
import dev.bonilla.util.JDBCConnection;

public class CarDAOImpl implements CarDAO {

	
	public static Connection conn = JDBCConnection.getConnection();
	
	public Car getCar(int cid) {
		
		try {
			String sql  = "SELECT * FROM cars WHERE c_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(cid));
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				// Return car ID, year, make model, msrp, mileage
				return new Car(rs.getInt("C_ID"),rs.getInt("C_YEAR"), rs.getString("C_MAKE"), 
						rs.getString("C_MODEL"),rs.getInt("C_MSRP"), rs.getInt("C_MILES"));
			}
			
		} catch (SQLException e) {
			MyLogger.logger.error("FAILED TO RETRIEVE CAR.");
			e.printStackTrace();
		}
		
		return null;
	}

	public List<Car> getAllCars() {
		try {
			String sql = "SELECT * FROM cars";
			PreparedStatement ps = conn.prepareStatement(sql);
			List<Car> carList = new ArrayList<Car>();
			ResultSet rs = ps.executeQuery();
			MyLogger.logger.info("GETTING ALL CARS");
			while(rs.next()) {
				carList.add(new Car(rs.getInt("C_ID"),rs.getInt("C_YEAR"), rs.getString("C_MAKE"), 
						rs.getString("C_MODEL"),rs.getInt("C_MSRP"), rs.getInt("C_MILES")));
				MyLogger.logger.info("CREATING CAR OBJECTS.");
				
			}
			
			return carList;
		} catch (SQLException e) {
			MyLogger.logger.error("DB ERR: FAILED TO RETRIEVE CARS.");
			e.printStackTrace();
		}
		return null;
	}
	
	// Obtaining cars that user currently has offers on.
	public List<Car> getMyCars(int uid) {
		List<Car> carList = new ArrayList<Car>();
		try {
			String sql = "SELECT users.u_id, cars.*, offers.o_value FROM users LEFT JOIN offers ON offers.u_id = users.u_id LEFT JOIN cars ON offers.c_id = cars.c_id  WHERE offers.u_id =?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(uid));
			ResultSet rs = ps.executeQuery();
			MyLogger.logger.info("GETTING ALL CARS FOR THIS USER");
			while(rs.next()) {
				carList.add(new Car(rs.getInt("C_ID"),rs.getInt("C_YEAR"), rs.getString("C_MAKE"), 
						rs.getString("C_MODEL"),rs.getInt("C_MSRP"), rs.getInt("C_MILES")));
				MyLogger.logger.info("Making car object.");
				
			}
			
			return carList;
		} catch (SQLException e) {
			MyLogger.logger.error("FAILED TO RETRIEVE CAR.");
			System.err.println("Car not found!");
			e.printStackTrace();
		}
		return null;
	}

	public List<Car> getMyCars(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean deleteCar(int cid) {
		try {
			// need to delete offers first. CallableStatement w/ procedure
			String sql = "DELETE FROM cars WHERE c_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(cid));
			MyLogger.logger.info("DELETING CAR WITH ID: " + cid);
			ps.executeQuery();
			
			return true;
			
		} catch (SQLException e) {
			MyLogger.logger.info("FAILED TO DELETE CAR");			
		}
		return false;
	}
	// For cleaning up DB after testing.
	public boolean deleteCar(String make, String model) {
		try {
			// need to delete offers first. CallableStatement w/ procedure
			String sql = "DELETE FROM cars WHERE c_make = ? AND c_model = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, make);
			ps.setString(2, model);
			MyLogger.logger.info("DELETING CAR:" + make + model);
			ps.executeQuery();
			
			return true;
			
		} catch (SQLException e) {
			MyLogger.logger.info("FAILED TO DELETE CAR");			
		}
		return false;
	}

	public boolean updateCar(Car car) {
		try {
			String sql = "UPDATE cars SET c_msrp = ?, c_miles= ? WHERE c_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(car.getMsrp()));
			ps.setString(2, Integer.toString(car.getMileage()));
			ps.setString(3, Integer.toString(car.getId()));
			MyLogger.logger.info("UPDATING CAR WITH ID: " + car.getId());
			ps.executeQuery();
			
			return true;
			
		} catch (SQLException e) {
			MyLogger.logger.info("FAILED TO UPDATE CAR");
			e.printStackTrace();
			
		}
		return false;
	}

	
	public boolean createCar(Car c) {
		try {
			String sql = "INSERT INTO cars VALUES (cid_maker.nextval, ?, ?, ? ,?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(c.getYear()));
			ps.setString(2,  c.getMake());
			ps.setString(3, c.getModel());
			ps.setString(4, Integer.toString(c.getMsrp()));
			ps.setString(5, Integer.toString(c.getMileage()));
			
			ps.execute();
			return true;
			
		} catch (SQLException e) {
			MyLogger.logger.info("FAILED TO CREATE A CAR");
			System.err.println("Creation NOT successful!");
			e.printStackTrace();
			
		}
		return false;
	}







	

	

}
