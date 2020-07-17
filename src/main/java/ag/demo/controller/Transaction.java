package ag.demo.controller;

import ag.demo.model.Screen;
import ag.demo.util.BankDatabase;

public abstract class Transaction {

	private int accountNum; 
	private Screen screen;
	private BankDatabase bankDb; 
	
	public Transaction(int accountNum, Screen screen, BankDatabase bankDb) {
		this.accountNum = accountNum; 
		this.screen = screen; 
		this.bankDb = bankDb; 
	}
	
	public int getAccountNum() {
		return accountNum; 
	}
	
	public Screen getScreen() {
		return screen; 
	}
	
	public BankDatabase getBankDb() {
		return bankDb; 
	}
	
	abstract public void execute(); // perfomr the transaction (overriden by each subclass)

}
