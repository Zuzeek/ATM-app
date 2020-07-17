package ag.demo.service;

import ag.demo.controller.Transaction;
import ag.demo.model.Screen;
import ag.demo.util.BankDatabase;

public class BalanceInquiry extends Transaction{
	
	public BalanceInquiry(int accountNum, Screen screen, BankDatabase bankDb){
		super(accountNum, screen, bankDb); 
	}
	
	// perform transaction 	
	@Override
	public void execute() {
		BankDatabase bankDatabase = getBankDb(); 
		Screen atmScreen = getScreen(); 
		
		double availableBalance = bankDatabase.getAvailableBalance(getAccountNum()); 
		double totalBalance = bankDatabase.geTotalBalance(getAccountNum()); 
		
		// display balance info on screen
		atmScreen.displayMessageLine("\nBalance Information:");
		atmScreen.displayMessage(" - Available balance: ");
		atmScreen.displayGbpAmount(availableBalance);
		atmScreen.displayMessage("\n - Total balance: ");
		atmScreen.displayGbpAmount(totalBalance);
		atmScreen.displayMessageLine(""); 
	}
}
