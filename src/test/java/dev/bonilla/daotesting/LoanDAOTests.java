package dev.bonilla.daotesting;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import dev.bonilla.dao.LoanDAOImpl;
import dev.bonilla.model.Car;
import dev.bonilla.model.Loan;

@TestInstance(Lifecycle.PER_CLASS)
public class LoanDAOTests {

	LoanDAOImpl ld = new LoanDAOImpl();

	@Test
	public void getLoanSuccess() {
		Loan actual = ld.getLoan(4, 404);
		Car car = new Car(404, 1994, "Toyota", "Celica", 1, 100000);
		Loan expected = new Loan(4, car, 0, 10000, 36);
		Assertions.assertEquals(expected.toString(), actual.toString());
	}

	@Test
	public void getLoanFailure() {
		Loan actual = ld.getLoan(999, 999);
		Assertions.assertEquals(null, actual);
	}

	@Test
	public void getOneUserLoansSuccess() {

		List<Loan> actual = ld.getMyLoans(4);
		List<Loan> expected = new ArrayList<Loan>();
		int i = 0;
		Car c = new Car(404, 1994, "Toyota", "Celica", 10000, 200000);
		while (i < actual.size()) {
			Loan l = new Loan(4, c, 0, 10000, 36);
			expected.add(l);
			Assertions.assertEquals(expected.get(i).toString(), actual.get(i).toString());
			i++;
		}
//		
		
	}

	@Test
	public void getAllUserLoansSuccess() {
		List<Loan> actual = ld.getAllUserLoans();
		List<Loan> expected = new ArrayList<Loan>();
		int i = 0;
		Car c = new Car(404, 1994, "Toyota", "Celica", 10000, 200000);
		while (i < actual.size()) {
			Loan l = new Loan(4, c, 0, 10000, 36);
			expected.add(l);
			Assertions.assertEquals(expected.get(i).toString(), actual.get(i).toString());
			i++;
		}
	}

}
