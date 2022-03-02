package com.bankprojectsample;

import java.util.Scanner;

import com.bankprojectsample.model.User;
public class BankApp {
  



  public void display() {
		System.out.println("---- Welcome to Boogie Down Banking Console -----");
		System.out.println("---- M E N U ----");
		System.out.println("1. Create an Account");
		System.out.println("2. Log In");
//		System.out.println("3. ");
//		System.out.println("4. ");
		System.out.println("5. Employee Login");
		System.out.println("9. Exit Bank App");
		
		
	}
	
	public void userInput(int num) {
		User user = new User();
		switch (num) {
		case 1:
			System.out.println("Great! Lets create an account.");
			
			user.createCustomerAccount();
			break;
		case 2:
			
			user.login();
		case 5:
			user.employeeLogin();
		case 9:
			System.out.println("Exiting console...");
			System.exit(0);
		default:
		
			System.out.println("Exiting console...");
			System.exit(0);
			break;
		}
	}
}
