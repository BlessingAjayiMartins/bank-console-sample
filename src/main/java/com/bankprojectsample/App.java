package com.bankprojectsample;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        BankApp app = new BankApp();
        
		app.display();
		
		Scanner sn = new Scanner(System.in);
		System.out.println("Choose a number from the menu: ");
		int num = sn.nextInt();
		
		
		app.userInput(num); 
        
    }
}
