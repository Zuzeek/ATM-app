package ag.demo.model;

public class Account {
	
	private int accountNumber;
	private int pin; 
	private double availableBalance;
	private double totalBalance; 
	
	public Account() {}

	public Account(int accountNumber, int pin, double availableBalance, double totalBalance) {
		this.accountNumber = accountNumber;
		this.pin = pin;
		this.availableBalance = availableBalance;
		this.totalBalance = totalBalance;
	}

	public boolean validatePin(int userPin) {
		if(userPin == pin) {
			return true;
		}
		else {
			return false; 
		}
	}
	
	public void topupBalance(double amount) {
		totalBalance += amount; 
	}
	
	public void debit(double amount) {
		availableBalance -= amount; 
		totalBalance -= amount; 
	}
	
	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Integer getPin() {
		return pin;
	}

	public void setPin(Integer pin) {
		this.pin = pin;
	}

	public Double getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(Double availableBalance) {
		this.availableBalance = availableBalance;
	}

	public Double getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(Double totalBalance) {
		this.totalBalance = totalBalance;
	} 
		
}
