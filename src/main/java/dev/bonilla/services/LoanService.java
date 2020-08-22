package dev.bonilla.services;

import java.util.List;

import dev.bonilla.model.Loan;

public interface LoanService {
	public Loan getLoan(int uid, int cid);
	public List<Loan> getMyLoans(int uid);
	public List<Loan> getAllUserLoans();
//	public boolean createLoan(int uid, int cid);
//	public boolean updateLoan(int uid, int cid);
//	public boolean deleteLoan(int uid, int cid);
}
