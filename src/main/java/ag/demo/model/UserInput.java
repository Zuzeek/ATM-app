package ag.demo.model;

import java.util.Scanner;

public class UserInput {
	
	private Scanner input; 
	
	public UserInput() {
		input = new Scanner(System.in); 
	}
	
	public int getInput() {
		return input.nextInt(); 
	}
}
