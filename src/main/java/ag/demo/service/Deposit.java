package ag.demo.service;

import ag.demo.controller.Transaction;
import ag.demo.model.Screen;
import ag.demo.model.UserInput;
import ag.demo.util.BankDatabase;

public class Deposit extends Transaction {
	
	private double amount; 
	private UserInput userInput; 
	private DepositSlot depositSlot;
	
	private static final int CANCELED = 0; 
	
	public Deposit(int currentAccountNum, Screen screen, BankDatabase bankDb, UserInput userInput, DepositSlot depositSlot) {
		super(currentAccountNum, screen, bankDb); 
		this.userInput = userInput; 
		this.depositSlot = depositSlot; 
	}
	
	@Override
	public void execute() {
		BankDatabase bankDb = getBankDb(); // get reference 
		Screen screen = getScreen(); 
		
		amount = promptForDepositAmountInPence(); // get amount from user 
		
		if(amount != CANCELED) {
			//request deposit ££
			screen.displayMessage("\nInsert ");
			screen.displayGbpAmount(amount);
			
			boolean depositRecieved = depositSlot.isMoneyDeposited(); 
			
			// check if ££ recieved 
			if(depositRecieved) {
				screen.displayMessageLine("\nYour money has been received.\nNOTE: The money won't be available in your account until we verify the amount.");
				
				// credit the account to reflect the deposit  
				bankDb.topupBalance(getAccountNum(), amount);
			}
			else { // deposit not received 
				screen.displayMessageLine("\nYou did not insert money, so the ATM has canceled your transaction");
			}
		}
		else { // user canceled transaction 
			screen.displayMessageLine("\nCanceling transaction");
		}
	}

	private double promptForDepositAmountInPence() {
		Screen screen = getScreen(); 
		
		screen.displayMessage("\nEnter deposit amount in PENCE, or 0 to cancel: ");
		int input = userInput.getInput(); 
		
		if(input == CANCELED) {
			return CANCELED; 
		}
		else {
			return (double) input / 100; // return ££ amount 
		}
	}
}
