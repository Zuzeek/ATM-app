package ag.demo.model.test;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

import ag.demo.model.Account;

class AccountTecst {
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in); 
		
		Account myAccount = new Account(); 
		System.out.printf("intial name is: %s%n%n", myAccount.getName());
		
		System.out.println("enter your name: ");
		String name = input.nextLine(); 
		myAccount.setName(name);
		System.out.printf("Name in my account is: %s%n", myAccount.getName());
	}

	@Test
	void testAccount() {
		fail("Not yet implemented");
	}

	@Test
	void testAccountStringStringStringDouble() {
		fail("Not yet implemented");
	}

	@Test
	void testGetName() {
		fail("Not yet implemented");
	}

	@Test
	void testSetName() {
		fail("Not yet implemented");
	}

	@Test
	void testGetAccountNumber() {
		fail("Not yet implemented");
	}

	@Test
	void testSetAccountNumber() {
		fail("Not yet implemented");
	}

	@Test
	void testGetCardNumber() {
		fail("Not yet implemented");
	}

	@Test
	void testSetCardNumber() {
		fail("Not yet implemented");
	}

	@Test
	void testGetBalance() {
		fail("Not yet implemented");
	}

	@Test
	void testSetBalance() {
		fail("Not yet implemented");
	}

}
