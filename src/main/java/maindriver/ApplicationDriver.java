package maindriver;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import dev.bonilla.logging.MyLogger;
import dev.bonilla.model.Car;
import dev.bonilla.model.Loan;
import dev.bonilla.model.Offer;
import dev.bonilla.model.User;
import dev.bonilla.services.CarService;
import dev.bonilla.services.CarServiceImpl;
import dev.bonilla.services.LoanService;
import dev.bonilla.services.LoanServiceImpl;
import dev.bonilla.services.OfferService;
import dev.bonilla.services.OfferServiceImpl;
import dev.bonilla.services.UserService;
import dev.bonilla.services.UserServiceImpl;

/**
 * 
 * @author william.bonilla
 *
 * An online car dealership application that allows users to view cars, place offers,
 * and ultimatley finance the vehicles. 
 * 
 * This application was designed to read input from the console.
 *
 */


public class ApplicationDriver {
	
	
	static Scanner sc = new Scanner(System.in);
	static String username;
	static String password;
	static int selection;
	static User user;
	static List<Offer> offersList;
	
	// services
	static CarService cs = new CarServiceImpl();
	static UserService us = new UserServiceImpl();
	static OfferService os = new OfferServiceImpl();
	static LoanService ls = new LoanServiceImpl();
	static boolean application = true;
	static boolean application2 = true;
	public static void main(String[] args) {
		
		
		while(application) {
		/*
		 * MENU TIER 1.
		 * 
		 * To get past tier 1, user must login or create an account.
		 * 
		 */
		application2 = true; // This unlocks menu 2, in case user logged out.
		System.out.println("\n=======================================================================================\n");
		System.out.println("Welcome to Car Hammock! \nThe RIGHT way to buy your car while social distancing!");
		System.out.println("Please input an option below:");
		System.out.println("1 -- Login \n2 -- Register New User \n3 -- Search Available Cars \n4 -- Exit");
		MyLogger.logger.info("PROGRAM START");
		// ATTEMPT TO REFACTOR INPUT
		selection = ApplicationDriver.userInput();
//		try {
//			selection = Integer.parseInt(sc.nextLine());
//		} catch (Exception e) {
//			System.err.println("ERROR: please make an appropriate selection.");
//			MyLogger.logger.error("INVALID SELECTION");
//			continue;
//		}
		
		
		
		switch (selection) {
		case 1:
			// Returns true if login is a success
			if(ApplicationDriver.login()) {
				System.out.println("** SUCCESSFULLY LOGGED IN **\n Welcome back, " + user.getName());
				MyLogger.logger.info("LOGIN SUCCESS: " + user.getName());
			} else {
				System.out.println("** Account not found. Please try again, or register a new account!");
				MyLogger.logger.error("ACCOUNT NOT FOUND");
				continue;
			}	
			break;
		case 2:
			if (ApplicationDriver.register()) {
				System.out.println("** SUCCESSFULLY CREATED ACCOUNT AND LOGGED IN! ** \n Welcome, " + user.getName());
				MyLogger.logger.info("ACCOUNT CREATION SUCCESS");
				break;
			} else {
				System.err.println("Unable to create account, username is already taken." );
				MyLogger.logger.error("ACCOUNT CREATION FAILED");
			}
			continue;
		case 3:
			ApplicationDriver.searchCars();
			System.out.println("When you find a car you love and are ready to place an offer,\nplease login or create an account!");
			continue;
		case 4:
			MyLogger.logger.info("PROGRAM EXIT AT MENU 1");
			ApplicationDriver.exit();
			break;

		default:
			System.err.println("ERROR: please make an appropriate selection.");
			MyLogger.logger.error("INVALID SELECTION");
			continue;
//			break;
		}
		
		/* 
		 * 	MENU TIER 2
		 * 
		 *  - I placed it in another while loop in case the user attempts to break it again.
		 * This will allow the while loop to reset back to tier 2 as opposed to restarting the entire app.
		 */
		// we should also have a test for if user is a customer or employee
		boolean menu2 = true;
		while(application2 && user.isCustomer()==true) {
			
			if(menu2) {
				System.out.println("\n=======================================================================================\n");
				ApplicationDriver.searchCars();
				menu2 = false;
			}	
			System.out.println("\n=======================================================================================\n");
			System.out.println("What would you like to do next?");
			System.out.println("1 -- Make a new offer on a car");
			System.out.println("2 -- View your current offers");
			System.out.println("3 -- Search again"); // Add filters for future
			System.out.println("4 -- Logout");
			System.out.println("5 -- Update current offer");
			System.out.println("6 -- Remove current offer");
			System.out.println("7 -- View my financed cars");
			
			selection = ApplicationDriver.userInput();
//			try {
//				selection = Integer.parseInt(sc.nextLine());
//			} catch (Exception e) {
//				System.err.println("ERROR: please make an appropriate selection.");
//			}
			
			switch (selection) {
			case 1:
				
				ApplicationDriver.createOffer();
				break;
			case 2:
				ApplicationDriver.getAllMyOffers(user.getId());
				break;
			case 3:
				ApplicationDriver.searchCars();
				break;
			case 4:
				MyLogger.logger.info("LOGGING OUT USER: " + user.getId() + " AT MENU 3");
				application2 = false;
				user = null;
				break;
			case 5:
				ApplicationDriver.updateOffer();
				break;
			case 6:
				ApplicationDriver.deleteOffer();
				break;
			case 7: 
				ApplicationDriver.getMyLoans();
				break;
			case 8:
				application2 = false;
				user = null;
				break;
			default:
				System.err.println("ERROR: please make an appropriate selection.");
				MyLogger.logger.error("INVALID SELECTION");
				continue;

			}
			
		} // END MENU 2 while loop
		
		
		
		/*
		 * 	Menu tier 3
		 * 
		 */
		while(application2 &&  user.isCustomer() == false) {
			
			System.out.println("\n=======================================================================================\n");
			System.out.println("Welcome <GENERIC EMPLOYEE> \nPlease remember that you're only valuable to our company as long as you are profitable.");
			System.out.println("Please input an option below:");
			System.out.println("1 -- View Cars on the Lot \n2 -- Add a Car \n3 -- Remove a Car \n4 -- Logout \n5 -- View Offers for a Car"
					+ "\n6 -- Accept Offers for a car \n7 -- Reject offers for a car \n8 -- Finalize Car Sale \n9 -- View All Car Payments");
			MyLogger.logger.info("PROGRAM START");
			selection = ApplicationDriver.userInput();
//			try {
//				selection = Integer.parseInt(sc.nextLine());
//			} catch (Exception e) {
//				System.err.println("ERROR: please make an appropriate selection.");
//			}
			switch (selection) {
			case 1:
				ApplicationDriver.searchCars();
				break;
			case 2:
				ApplicationDriver.createCar();
				break;
			case 3:
				ApplicationDriver.deleteCar();
				break;
			case 4:
				MyLogger.logger.info("LOGGING OUT USER: " + user.getId() + " AT MENU 3");
				application2 = false;
				user = null;
				break;
			case 5:
				ApplicationDriver.getCarOffers();;
				break;
			case 6:
				ApplicationDriver.approveOffer();
				break;
			case 7:
				ApplicationDriver.declineOffer();
				break;
			case 8:
				ApplicationDriver.finalizeSale();
				break;
			case 9:
				ApplicationDriver.getAllLoanPayments();
				break;
				
			default:
				System.err.println("ERROR: please make an appropriate selection.");
				MyLogger.logger.error("INVALID SELECTION");
				continue;			}
			
		}
		
		
		} // end of APPLICATION while loop
		
	} // end of main method.
	//CUSTOMERS
	public static boolean login() {
		boolean success = true;
		int counter = 1;
		do {
			System.out.println("UserID: ");
			username = ApplicationDriver.userStringInput();
			System.out.println("Password: ");
			password = ApplicationDriver.userStringInput();
			// Static user used for the rest of the application is saved.
			user = us.getUser(username, password);
			success =  user != null;
			counter++;
		} while (success == false && counter <= 3);
		// Provides user with 3 failed login attempts.
		return success;
		
	}
	
	public static boolean register() {
		System.out.println("Create a new UserID: ");
		username = ApplicationDriver.userStringInput();
		System.out.println("Enter a new password: ");
		password = ApplicationDriver.userStringInput();
		user = us.createUser(username, password);	
		if (user != null) {
			return true;
		}
		return false;
	}
	
	public static void searchCars() {
		List<Car> carsList = cs.getAllCars();
		System.out.println("Here are the cars we currently have: ");
			for(Car c: carsList) {
				System.out.println("--------------------------------");
				System.out.println(c.toString());
				System.out.println("--------------------------------\n");
			}
		
	} 
	

	
	// OFFERS
	public static void createOffer() {
		int cid, value;
		System.out.println("CREATE OFFER: ");
		System.out.println("Please input the CAR ID: ");
		try {
			cid = Integer.parseInt(sc.nextLine());
			System.out.println("Please input the value of your offer (no dollar sign): ");
			System.out.print("$ ");
			value = ApplicationDriver.userInput();
			if(os.createOffer(user.getId(), cid, value)) {
				System.out.println("Success! Thank you for your offer! Please check back again soon!");
				
			} else
				System.err.println("An error has occurred. Offer not accepted. Please try again.");
		} catch (Exception e) {
			System.err.println("ERROR: please make an appropriate selection");
		}
		
	}
	public static void getAllMyOffers(int uid) {
		// Purposely getting a car list for the user first.
		List<Car> carList = cs.getMyCars(uid);
		for(Car c: carList) {
			System.out.println(c);
			// For each car that we have offers on, we create new offer objects and retrieve the information from the DB.
			Offer offer = os.getOffer(uid, c.getId());
			System.out.println(offer);
			System.out.println("--------------------------------");
		}
		
	}
	public static void updateOffer() {
		int cid, value;
		System.out.println("UPDATE OFFER: ");
		System.out.println("Please input the CAR ID: ");
		try {
			cid = ApplicationDriver.userInput();
			System.out.println("Please input the UPDATED value of your offer (no dollar sign): ");
			System.out.print("$ ");
			value = ApplicationDriver.userInput();
			if (os.updateOffer(user.getId(), cid, value)) {
				System.out.println("Update successful! Thank you!");
			} else
				System.out.println("Update failed! Please try again.");
		} catch (Exception e) {
			System.err.println("ERROR: please make an appropriate selection");
		}
		
		
	}
	public static void deleteOffer() {
		int cid, value;
		System.out.println("DELETE CAR OFFER: ");
		System.out.println("Please input the CAR ID for the offer you would like to delete: ");
		try {
			cid = ApplicationDriver.userInput();
			if (os.removeOffer(user.getId(), cid)) {
				System.out.println("Offer removed, we hope you'll find something else you like on our lot!");
			} else
				System.out.println("Deletion failed! Please try again.");
		} catch (Exception e) {
			System.err.println("ERROR: please make an appropriate selection");
		}
		
	}
	// LOANS
	public static void getMyLoans() {
		System.out.println("CAR LOANS: ");
		System.out.println("--------------------------");
		List<Loan> myLoans = ls.getMyLoans(user.getId());
		for(Loan l : myLoans) {
			double monthly = (double) l.getValue() / l.getTerm();
			System.out.println(l);
			System.out.println("MONTHLY PAYMENT: $" + String.format("%.2f", monthly));
			System.out.println("--------------------------");
		}
	}
	
	// EMPLOYEES
	public static void createCar() {
		System.out.println("ADD A CAR:\n Please enter the following details.");
		try {
			System.out.println("YEAR: "); 
			int year = ApplicationDriver.userInput();
			System.out.println("MAKE: "); 
			String make = ApplicationDriver.userStringInput();
			System.out.println("MODEL: "); 
			String model = ApplicationDriver.userStringInput();
			System.out.println("MSRP: ");
			System.out.print("$");
			int msrp = ApplicationDriver.userInput();
			System.out.println("ODOMETER: ");
			int mileage = ApplicationDriver.userInput();
			// 0 is a placeholder until DB generates ID from DAO layer.
			Car car = new Car(0, year, make, model, msrp, mileage);
			if (cs.createCar(car)) {
				System.out.println("Added the following car: \n" + car.toString());
			}
			
		} catch (Exception e) {
			MyLogger.logger.info("FAILED TO ADD CAR TO THE LOT. INSUFFICIENT OR INCORRECT INFORMATION.");
			e.printStackTrace();
		}
	}
	
	public static void updateCar() {
		
	}
	
	public static void deleteCar() {
		System.out.println("REMOVE A CAR:\n Which car would you like to remove? ");
		try {
			System.out.println("CAR ID: "); 
			int cid = ApplicationDriver.userInput();
			System.out.println("This will permanently remove the car from the database and any offers. Are you certain? (Y/N)");
			String certain = ApplicationDriver.userStringInput();
			if (certain.equalsIgnoreCase("y")) {
				cs.deleteCar(cid);
				System.out.println("SUCCESS. Car removed from lot.");
			} else
				throw new IllegalArgumentException();
		} catch (Exception e) {
			MyLogger.logger.error("Removing car failed. Please try again.");
			System.err.println("Car was NOT removed.");
			e.printStackTrace();
		}
	}
	
	public static void getCarOffers() {
		
		System.out.println("CAR ID: ");
		try {
			int cid = Integer.parseInt(sc.nextLine());
			System.out.println("--------------------------- ");
			Car car = cs.getCar(cid);
			System.out.println(car.toString());
			System.out.println("--------------------------- ");
			List<Offer> oList = os.viewCarOffers(cid);
			for(Offer o : oList) {
				System.out.println(o.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void approveOffer() {
		try {
			System.out.println("CAR ID: ");
			int cid = ApplicationDriver.userInput();
			System.out.println("USER ID: ");
			int uid = ApplicationDriver.userInput();
			
			if (os.approveOffer(uid, cid)) {
				System.out.println("Offer APPROVED for USER: " + uid + "and CAR: " + cid);
			}
		} catch (Exception e) {
			MyLogger.logger.error("INVALID INPUT");
			System.out.println("Invalid input, please try again.");
		}
	}
	
	public static void declineOffer() {
		try {
			System.out.println("CAR ID: ");
			int cid = ApplicationDriver.userInput();
			System.out.println("USER ID: ");
			int uid = ApplicationDriver.userInput();
			
			if (os.declineOffer(uid, cid)) {
				System.out.println("Offer successfully declined for USER: " + uid + "and CAR: " + cid);
			}
		} catch (Exception e) {
			MyLogger.logger.error("INVALID INPUT");
			System.out.println("Invalid input, please try again.");
		}
		
	}
	
	public static void finalizeSale() {
		try {
			System.out.println("CAR ID: ");
			int cid = ApplicationDriver.userInput();
			System.out.println("USER ID: ");
			int uid = ApplicationDriver.userInput();
			System.out.println("DOWN PAYMENT: ");
			int down = ApplicationDriver.userInput();
			System.out.println("LOAN TERM LENGTH: ");
			int term = ApplicationDriver.userInput();
			if (os.finalizeSale(uid, cid, down, term)) {
				System.out.println("Offer FINALIZED for USER:" + uid + "and CAR: " + cid);
				System.out.println("This user will now be able to see the loan, payments, and term length in his or her account.");
			}
		} catch (Exception e) {
			MyLogger.logger.error("INVALID INPUT");
			System.out.println("Invalid input, please try again.");
		}
		
	
	}
	public static void getAllLoanPayments() {
		System.out.println("Here are all the payments for financed vehicles");
		List<Loan> allLoans = ls.getAllUserLoans();
		for(Loan l: allLoans) {
			double monthly = (double) l.getValue() / l.getTerm();
			System.out.println(l.toString(1));
			System.out.println("MONTHLY PAYMENT: $" + String.format("%.2f", monthly));
			System.out.println("--------------------------");
		}
	}
	public static int userInput() {
		int input = 0;
		try {
			 input = Integer.parseInt(sc.nextLine());
			
		} catch (Exception e) {
			System.err.println("Incorrect input, please enter a valid number.\n");
		}
		
		return input;
	}
	public static String userStringInput() {
		String input = "";
		try {
			 input = sc.nextLine();
			
		} catch (Exception e) {
			System.err.println("Incorrect input, please enter a valid number.\n");
		}
		
		return input;
	}
	public static void exit() {
		application = false;
		System.out.println("\n\tThank you for choosing Car Hammock. Good-bye!");
		application2 = false;
		sc.close();
		System.exit(0);
	}
	

}
