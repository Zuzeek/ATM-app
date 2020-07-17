package ag.demo.util;

import ag.demo.model.Account;

public class BankDatabase {

	private Account[] accounts; 
	
	public BankDatabase() {
		accounts = new Account[2]; // just 2 accounts for testing
		accounts[0] = new Account(12345, 1234, 200.00, 500.00); 
		accounts[1] = new Account(23456, 4321, 500.00, 500.00);
	}
	
	// fetch account obj
	private Account getAccount(int userAccountNum) {
		for (Account userAccount : accounts) {
			if(userAccount.getAccountNumber() == userAccountNum) {
				return userAccount; 
			}
		}
		return null; 
	}
	
	// validate if account and pin in the db 
	public boolean authenticateUser(int userAccountNum, int userPin) {
		Account userAccount = getAccount(userAccountNum); 
		
		if(userAccount != null) {
			return userAccount.validatePin(userPin); 
		}
		else 
			return false;
	}
	
	public double geTotalBalance(int userAccountNum) {
		return getAccount(userAccountNum).getTotalBalance(); 
	}
	
	public double getAvailableBalance(int userAccountNum) {
		return getAccount(userAccountNum).getAvailableBalance(); 
	}
	
	public void topupBalance(int userAccountNum, double amount) {
		getAccount(userAccountNum).topupBalance(amount);
	}
	
	public void debit(int userAccountNum, double amount) {
		getAccount(userAccountNum).debit(amount);
	}

}
