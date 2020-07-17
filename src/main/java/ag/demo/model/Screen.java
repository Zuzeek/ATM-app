package ag.demo.model;

public class Screen {
	
	// withouth carriage return 
	public void displayMessage(String message) {
		System.out.print(message);
	}
	
	// with carriage return
	public void displayMessageLine(String message) {
		System.out.println(message);
	}
	
	public void displayGbpAmount(Double amount) {
		System.out.printf("£%,.2f", amount);
	}
}
