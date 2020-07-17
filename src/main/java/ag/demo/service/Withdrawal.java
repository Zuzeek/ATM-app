package ag.demo.service;

import ag.demo.controller.Transaction;
import ag.demo.model.Screen;
import ag.demo.model.UserInput;
import ag.demo.util.BankDatabase;

public class Withdrawal extends Transaction {
	
	private int amount; 
	
	// reference to associated objects
	private UserInput userInput; 
	private CashDispenser cashDispenser; 
	
	private static final int CANCELED = 6; 
	
	public Withdrawal(int currentAccountNum, Screen screen, BankDatabase bankDb, UserInput userInput, CashDispenser cashDispenser) {
		super(currentAccountNum, screen, bankDb); // initialise super class variables 
		
		this.userInput = userInput; 
		this.cashDispenser = cashDispenser; 
	}
	
	@Override
	public void execute() {
		boolean cashRecieved = false; 
		double availableBalance; 
		
		BankDatabase bankDb = getBankDb(); 
		Screen screen = getScreen(); 
		
		// loop until cash is received or user cancel's
		do {
			// get amount from user 
			amount = displayMenuAmounts(); 
			
			// check if user chose to withdraw or canceled 
			if(amount != CANCELED) {
				// get available balance 
				availableBalance = bankDb.getAvailableBalance(getAccountNum()); 
				
				// check if enough ££ in account
				if(amount <= availableBalance) {
					
					//check if atm has enough ££
					if(cashDispenser.isSufficientCashAvailable(amount)) {
						bankDb.debit(getAccountNum(), amount);
						cashDispenser.dispenseCash(amount);
						cashRecieved = true; 
						
						screen.displayMessageLine("\nYour cash has been dispensed. Take your cash now!");
					}
					else { // not enough ££ in the atm 
						screen.displayMessageLine("\nInsufficient cash available in the ATM.\nChoose smaller amount");
					}
				}
				else { // not enough ££ in user's account 
					screen.displayMessageLine("\nInsufficient founds in your account.\nChoose smaller amount.");
				}
			}
			else { // user chose to cancel 
				screen.displayMessageLine("\nCanceling transaction...");
				return; // return to main menu
			}
		}
		while (!cashRecieved); 
	}

	private int displayMenuAmounts() {
		int userChoice = 0; 
		Screen screen = getScreen(); 
		
		int[] amounts = {0, 20, 40, 60, 100, 200}; // array of amounts corresponding to menu numbers 

		while(userChoice == 0) {
			screen.displayMessageLine("\nWithdrawal Menu:");
			screen.displayMessageLine("1 - £20");
			screen.displayMessageLine("2 - £40");
			screen.displayMessageLine("3 - £60");
			screen.displayMessageLine("4 - £100");
			screen.displayMessageLine("5 - £200");
			screen.displayMessageLine("6 - Cancel transaction");
			screen.displayMessage("\nChoose a withdrawal amount: ");
			
			int input = userInput.getInput(); 
			
			switch(input) {
				case 1: 
				case 2:
				case 3:
				case 4:
				case 5:
					userChoice = amounts[input]; // save user choice
					break; 
				case CANCELED:
					userChoice = CANCELED; // save user's choice 
					break; 
				default: // user did not enter a choice 
					screen.displayMessageLine("\nInvalid selection. Try again.");
			}
		}
		return userChoice; // return withdrawal amount or cancel
	}
}
