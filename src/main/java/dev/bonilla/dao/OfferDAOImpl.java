package dev.bonilla.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dev.bonilla.logging.MyLogger;
import dev.bonilla.model.Car;
import dev.bonilla.model.Offer;
import dev.bonilla.util.JDBCConnection;

public class OfferDAOImpl implements OfferDAO {
	Connection conn = JDBCConnection.getConnection();


	public Offer getOffer(int uid, int cid) {
		try {
			MyLogger.logger.info("SEARCHING DATABASE FOR OFFER");
			String sql = "SELECT * FROM offers WHERE u_id = ? AND c_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(uid));
			ps.setString(2, Integer.toString(cid));
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Offer(rs.getInt("U_ID"), rs.getInt("C_ID"), rs.getInt("O_VALUE"), rs.getInt("O_STATUS"));
			}
		} catch (SQLException e) {
			MyLogger.logger.info("OFFER NOT FOUND");
			e.printStackTrace();
		}
		return null;
	}

	public boolean createOffer(int uid, int cid, int value) {
		try {
			// ATTEMPT TRIGGER to prevent multiple offers from one user on same car?
			String sql = "INSERT INTO offers VALUES (?, ?, ?, 1)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(uid));
			ps.setString(2, Integer.toString(cid));
			ps.setString(3, Integer.toString(value));
			ps.execute();
			
			return true;
					
		} catch (SQLException e) {
			MyLogger.logger.info("OFFER NOT CREATED");
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateOffer(int uid, int cid, int value) {
		try {
			String sql = "UPDATE offers SET o_value = ? WHERE u_id = ? AND c_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(value));
			ps.setString(2, Integer.toString(uid));
			ps.setString(3, Integer.toString(cid));
			ps.execute();
			
			return true;
					
		} catch (SQLException e) {
			MyLogger.logger.info("UPDATE FAIL");
			e.printStackTrace();
		}
		return false;
	}

	public boolean removeOffer(int uid, int cid) {
		try {
			MyLogger.logger.info("PREPARING TO DELETE OFFER");
			String sql = "DELETE FROM offers WHERE u_id = ? AND c_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(uid));
			ps.setString(2, Integer.toString(cid));
			ps.execute();
			
			return true;
					
		} catch (SQLException e) {
			MyLogger.logger.info("DELETION FAIL");
			e.printStackTrace();
		}
		return false;
	}

	public List<Offer> viewCarOffers(int cid) {
		List<Offer> offersByCar = new ArrayList<Offer>();
		String sql  = "SELECT * FROM offers WHERE c_id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(cid));
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				MyLogger.logger.info("OFFERS FOR CAR FOUND IN DB.");
				Offer offer = new Offer(rs.getInt("U_ID"), rs.getInt("C_ID"), rs.getInt("O_VALUE"), rs.getInt("O_STATUS"));
				offersByCar.add(offer);
			}
			return offersByCar;
		} catch (SQLException e) {
			MyLogger.logger.error("NO OFFERS FOUND");
			e.printStackTrace();
		}
		return null;
	}

	public boolean approveOffer(int uid, int cid) {
		String sql = "CALL approveCar (?, ?)";
		CallableStatement cs = null;
		try {
			cs = conn.prepareCall(sql);
			cs.setInt(1, uid);
			cs.setInt(2, cid);
			cs.execute();
			
			return true;
		} catch (SQLException e) {
			MyLogger.logger.info("UNABLE TO APPROVE OFFER.");
			e.printStackTrace();
		}
		return false;
	}

	public boolean declineOffer(int uid, int cid) {
		String sql = "UPDATE offers SET o_status = 0 WHERE u_id = ? AND c_id = ?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, uid);
			ps.setInt(2, cid);
			ps.executeQuery();
			
			MyLogger.logger.info("OFFER FROM USER DECLINED");
			return true;
		} catch (SQLException e) {
			MyLogger.logger.error("OFFER DECLINE FAILED");
			e.printStackTrace();
		}
		return false;
	}

	public boolean finalizeSale(int uid, int cid, int down, int term) {
		String sql = "CALL sellCar (?, ?, ?, ?)";
		CallableStatement cs = null;
		try {
			cs = conn.prepareCall(sql);
			cs.setInt(1, uid);
			cs.setInt(2, cid);
			cs.setInt(3, down);
			cs.setInt(4, term);
			
			cs.execute();
			
			return true;
		} catch (SQLException e) {
			MyLogger.logger.info("UNABLE TO FINALIZE SALE.");
			e.printStackTrace();
		}
		return false;
	}

	

}
