package dev.bonilla.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import dev.bonilla.logging.MyLogger;

public class JDBCConnection {
	
	private static Connection conn = null;
	
	public static Connection getConnection() {
		
		try {
			if(conn == null) {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				// For login credentials
				Properties props = new Properties();
				
				/*
				 * 		1. STATUS: WORKING
				 */
				// UNSAFE method, should try safer method, but failing on my machine.
				// Read from the credentials from IDE direct path.
//				FileInputStream myInput = new FileInputStream("src/main/resources/connection.properties");
//				props.load(myInput);
				
				/*
				 * 		2.STATUS: NOT WORKING
				 */
				// Attempt to use class loader from TRAINING:

//				FileInputStream input = new FileInputStream(JDBCConnection.class.getClassLoader().getResource("connection.properties").getFile());
//				props.load(input);
				/*
				 * 		3. STATUS: NOT WORKING. ClassLoader class doesn't like me.
				 */
				// Found on a random website, using the classloader.
				
//				ClassLoader cl = JDBCConnection.class.getClassLoader();
//				File file = new File(cl.getResource("connection.properties").getFile());
//				FileInputStream input2 = new FileInputStream(file);
				
				/*
				 * 		4.  STATUS: WORKING
				 */
				// Attempt getResource as Stream using method found on SO. This loads it from the root of the lcass path.

				InputStream input2 = JDBCConnection.class.getResourceAsStream("/connection.properties");
				props.load(input2);
				
				String url = props.getProperty("url");
				String username = props.getProperty("username");
				String password = props.getProperty("password");
				
				conn = DriverManager.getConnection(url, username, password);
				return conn;
				
				
			}
		} catch (Exception e) {
			MyLogger.logger.error("CONNECTION TO DATABASE FAILED");
			System.err.println("Connection failed, please try again.");
			e.printStackTrace();
			
		}
		
		return conn;
		
	}

}
