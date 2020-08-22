package dev.bonilla.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dev.bonilla.logging.MyLogger;
import dev.bonilla.model.User;
import dev.bonilla.util.JDBCConnection;

public class UserDAOImpl implements UserDAO {

	private static Connection conn = JDBCConnection.getConnection();

	// Method used to retrieve a user to verify it exists.
	public User getUser(String name, String pass) {
		try {
			MyLogger.logger.info("ATTEMPTING TO GET USER FROM DB.");
			String sql = "SELECT * FROM users WHERE u_name = ? AND u_password = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				MyLogger.logger.info("QUERY TO DB SUCCESS");
				boolean customerJava = true;
				int customerSQL = rs.getInt("CUSTOMER");
				if (customerSQL == 0) {
					customerJava = false;
				} 
				return new User(rs.getInt("U_ID"), rs.getString("U_NAME"), rs.getString("U_PASSWORD"), customerJava);
			}
			
		} catch (SQLException e) {
			MyLogger.logger.error("WRONG USER/PASSWORD COMBINATION.");
			e.printStackTrace();
		}
		
		return null;
	}
	

	public User createUser(String n, String p) {

		try {
			MyLogger.logger.info("ATTEMPTING TO CREATE USER");
			String sql = "INSERT INTO users VALUES (uid_maker.nextval, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, n);
			ps.setString(2, p);
			ps.setInt(3, 1);
			ps.execute();
			User user = this.getUser(n, p);
			return user;
		} catch (SQLException e) {
			MyLogger.logger.error("USER ALREADY IN DB. CREATION FAILED.");
			e.printStackTrace();
		}

		return null;

	}

	public List<User> viewAllUsers(String name) {
		return null;
	}

	public boolean deleteUser(String name) {
		try {
			// FOR FUTURE: Change to callable statement w/ procedure in SQL
			MyLogger.logger.info("ATTEMPTING TO DELETE USER");
			String sql = "DELETE FROM users WHERE u_name = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.execute();
			return true;
		} catch (SQLException e) {
			MyLogger.logger.error("USER NOT FOUND DELETION FAIL");
			e.printStackTrace();
		}

		return false;
	}
// EXTRA
//
//	public boolean changeUsername() {
//		return false;
//
//	}
//
//	public boolean changeUserPass() {
//		return false;
//
//	}
//
//	public boolean changeCustomer() {
//		return false;
//
//	}

	

}
