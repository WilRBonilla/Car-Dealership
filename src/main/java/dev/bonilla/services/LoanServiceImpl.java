package dev.bonilla.services;

import java.util.List;

import dev.bonilla.dao.LoanDAO;
import dev.bonilla.dao.LoanDAOImpl;
import dev.bonilla.model.Loan;

public class LoanServiceImpl implements LoanService {

	LoanDAO ld = new LoanDAOImpl();
	public Loan getLoan(int uid, int cid) {
		return ld.getLoan(uid, cid);
	}

	public List<Loan> getMyLoans(int uid){
		return ld.getMyLoans(uid);
	}
	
	public List<Loan> getAllUserLoans(){
		return ld.getAllUserLoans();
	}
//	public boolean createLoan(int uid, int cid) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	public boolean updateLoan(int uid, int cid) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	public boolean deleteLoan(int uid, int cid) {
//		// TODO Auto-generated method stub
//		return false;
//	}

}
