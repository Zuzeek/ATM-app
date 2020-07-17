package ag.demo.controller;

import ag.demo.model.UserInput;
import ag.demo.service.BalanceInquiry;
import ag.demo.service.CashDispenser;
import ag.demo.service.Deposit;
import ag.demo.service.DepositSlot;
import ag.demo.service.Withdrawal;
import ag.demo.model.Screen;
import ag.demo.util.BankDatabase;

public class ATM {
	
	private boolean userAuthenticated;
	private int currentAccountNum; 
	private Screen screen; 
	private UserInput userInput; 
	private CashDispenser cashDispenser; 
	private DepositSlot depositSlot; 
	private BankDatabase bankDb; 
	
	// constants corresponding to main menu
	private static final int BALANCE_INQUIRY = 1; 
	private static final int WITHDRAWAL = 2; 
	private static final int DEPOSIT = 3; 
	private static final int EXIT = 4; 
	
	public ATM() {
		userAuthenticated = false; // user is not authenticated to start
		currentAccountNum = 0; // no accNum to star
		screen = new Screen();
		userInput = new UserInput(); 
		cashDispenser = new CashDispenser();
		depositSlot = new DepositSlot(); 
		bankDb = new BankDatabase(); // create acc info db 
	}
	
	public void run() {
		
		while(true) {
			// while user not authenticated 
			while(!userAuthenticated) {
				screen.displayMessageLine("\nWelcome");
				authenticateUser(); 
			}
			
			perfomTransaction(); // user has been authenticated 
			userAuthenticated = false; // reset before next ATM session
			currentAccountNum = 0; // reset before next ATM session
			screen.displayMessageLine("\nGoodbye!");
		}
	}

	private void authenticateUser() {
		screen.displayMessage("\nEnter account number: ");
		int accountNum = userInput.getInput();
		
		screen.displayMessage("\nEnter pin: ");
		int pin = userInput.getInput(); 
		
		userAuthenticated = bankDb.authenticateUser(accountNum, pin);
		
		// check if inputed account num same as db account num 
		if(userAuthenticated) {
			currentAccountNum = accountNum; // save user's account num 
		}
		else {
			screen.displayMessageLine("Invalid account number and pin. Try again");
		}
	}
	
	private void perfomTransaction() {
		Transaction currenTransaction = null; // local variable to store transaction
		
		boolean userExited = false; // user has not chosen to exit 
		
		while(!userExited) {
			int mainMenuSelection = displayMainMenu(); 
			
			switch(mainMenuSelection) {
				case BALANCE_INQUIRY:
				case WITHDRAWAL:
				case DEPOSIT: 
					currenTransaction = createTransaction(mainMenuSelection); // initialise as a new object of chosen type 
					currenTransaction.execute();
					break; 
				
				case EXIT: 
					screen.displayMessageLine("\nExiting the system...");
					userExited = true; 
					break; 
				
				default: // user did not make menu selection
					screen.displayMessageLine("\n No valid selection was entered, try again");
					break; 
			}
		}
	}

	private int displayMainMenu() {
		screen.displayMessageLine("\nMain menu:");
		screen.displayMessageLine("1 - View balance");
		screen.displayMessageLine("2 - Withdraw cash");
		screen.displayMessageLine("3 - Deposit cash");
		screen.displayMessageLine("4 - Exit\n");
		screen.displayMessageLine("Enter a choice: ");
		return userInput.getInput();
	}
	
	// return object of specified transaction subclass
	private Transaction createTransaction(int mainMenuSelection) {
		Transaction temp = null; 
		
		switch(mainMenuSelection) {
			case BALANCE_INQUIRY: // create new transaction
				temp = new BalanceInquiry(currentAccountNum, screen, bankDb);
				break; 
			case WITHDRAWAL: // create new transaction 
				temp = new Withdrawal(currentAccountNum, screen, bankDb, userInput, cashDispenser); 
				break; 
			case DEPOSIT: // create new transaction 
				temp = new Deposit(currentAccountNum, screen, bankDb, userInput, depositSlot); 
				break; 
		}
		
		return temp;
	}
}
