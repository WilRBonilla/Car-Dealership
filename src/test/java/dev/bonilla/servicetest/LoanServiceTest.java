package dev.bonilla.servicetest;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dev.bonilla.model.Loan;
import dev.bonilla.services.LoanService;
import dev.bonilla.services.LoanServiceImpl;

class LoanServiceTest {

	LoanService ls = new LoanServiceImpl();

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void gettingLoanSuccess() {
		Loan actual = ls.getLoan(4, 404);
		Assertions.assertTrue(actual != null);
	}

	@Test
	public void gettingLoanFail() {
		Loan actual = ls.getLoan(12463465, 654464);
		Assertions.assertTrue(actual == null);
	}
	@Test
	public void userGetLoans() {
		List<Loan> actual = ls.getMyLoans(4);
		Assertions.assertTrue(actual != null);
	}
	
	
	@Test
	public void employeeGetLoanSuccess() {
		List<Loan> actual = ls.getAllUserLoans();
		Assertions.assertTrue(actual != null);
	}

}
