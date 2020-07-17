 package ag.demo.service;

public class CashDispenser {
	
	private final static int INITIAL_COUNT = 500; // the default amount of bills in the atm
	private int count; // num of £20 remaining in the atm
	
	public CashDispenser() {
		count = INITIAL_COUNT; // set count att to default 
	}
	
	// simulate dispensing cash 
	public void dispenseCash(int amount) {
		int billsRequired = amount / 20; // num of £20 bills required
		count -= billsRequired; 
	}
	
	// validate if atm can give desired amount
	public boolean isSufficientCashAvailable(int amount) {
		int billsRequired = amount/ 20; 
		
		if(count >= billsRequired) {
			return true; // enough bills available
		}
		else {
			return false; 
		}
	}
}
