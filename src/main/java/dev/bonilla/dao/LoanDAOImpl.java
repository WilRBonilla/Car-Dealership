package dev.bonilla.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.bonilla.logging.MyLogger;
import dev.bonilla.model.Car;
import dev.bonilla.model.Loan;
import dev.bonilla.util.JDBCConnection;

public class LoanDAOImpl implements LoanDAO {
	Connection conn = JDBCConnection.getConnection();
	public Loan getLoan(int uid, int cid) {
		try {
			MyLogger.logger.info("FINDING ACTIVE LOAN");
			String sql = "SELECT * FROM bank WHERE u_id = ? AND c_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(uid));
			ps.setString(2, Integer.toString(cid));
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				MyLogger.logger.info("LOAN FOUND");
				Car car = new Car(rs.getInt("C_ID"), rs.getInt("B_YEAR"), rs.getString("B_MAKE"), rs.getString("B_MODEL"));
				return new Loan(rs.getInt("U_ID"), car,rs.getInt("B_PAID"),
								rs.getInt("B_LOAN"), rs.getInt("B_TERM"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			MyLogger.logger.error("ERROR: LOAN NOT FOUND");
		}
		return null;
	}
	public List<Loan> getMyLoans(int uid) {
		List<Loan> myLoans = new ArrayList<Loan>();
		try {
			MyLogger.logger.info("FINDING ACTIVE LOAN");
			String sql = "SELECT * FROM bank WHERE u_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(uid));
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				MyLogger.logger.info("LOAN FOUND");
				Car car = new Car(rs.getInt("C_ID"), rs.getInt("B_YEAR"), rs.getString("B_MAKE"), rs.getString("B_MODEL"));
				Loan loan = new Loan(rs.getInt("U_ID"), car,rs.getInt("B_PAID"),
						rs.getInt("B_LOAN"), rs.getInt("B_TERM"));
				myLoans.add(loan);
			}
			return myLoans;
			
		} catch (SQLException e) {
			e.printStackTrace();
			MyLogger.logger.error("ERROR: LOAN NOT FOUND");
		}
		return null;
	}
	
	public List<Loan> getAllUserLoans(){
		MyLogger.logger.info("RETRIEVING ALL USER LOANS");
		String sql = "SELECT * FROM bank";
		PreparedStatement ps = null;
		List<Loan> allLoans = new ArrayList<Loan>();
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				MyLogger.logger.info("LOAN FOUND");
				Car car = new Car(rs.getInt("C_ID"), rs.getInt("B_YEAR"), rs.getString("B_MAKE"), rs.getString("B_MODEL"));
				Loan loan = new Loan(rs.getInt("U_ID"), car,rs.getInt("B_PAID"),
						rs.getInt("B_LOAN"), rs.getInt("B_TERM"));
				allLoans.add(loan);
			}
			return allLoans;
		} catch (SQLException e) {
			MyLogger.logger.error("FAILED TO RETRIEVED ALL LOANS.");
			e.printStackTrace();
		}
		return null;
	}
	public boolean createLoan(int uid, int cid) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateLoan(int uid, int cid) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteLoan(int uid, int cid) {
		try {
			MyLogger.logger.info("ATTEMPTING DELETING FROM BANK");
			String sql = "DELETE FROM bank WHERE u_id = ? AND c_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(uid));
			ps.setString(2, Integer.toString(cid));
			ps.executeQuery();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			MyLogger.logger.error("DELETION INCOMPLETE");
		}
		return false;
	}



	



}
