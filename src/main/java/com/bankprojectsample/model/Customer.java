package com.bankprojectsample.model;

import java.util.HashMap;
import java.util.Scanner;

import com.bankprojectsample.dao.CustomerDaoImpl;
import com.bankprojectsample.exception.InvalidInitialDepositException;

public class Customer{
  private String firstName;
	private String lastName;
	private String email;
	private int id;
  private int accounts;
	private String password;
  private HashMap <String, Integer> accountsBalance = null;
  private HashMap <Integer, String> accountType = null;
  private Customer customer;
  
  
  public Customer () {

  }

  public Customer (Customer customer) {
    this.customer = customer;
  }

  public Customer(String firstName, String lastName, String email, int id, int accounts, String password,
      HashMap<String, Integer> accountsBalance, HashMap<Integer, String> accountType) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.id = id;
    this.accounts = accounts;
    this.password = password;
    this.accountsBalance = accountsBalance;
    this.accountType = accountType;
  }

  public int getAccounts() {
    return accounts;
  }

  public void setAccounts(int accounts) {
    this.accounts = accounts;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public HashMap<String, Integer> getAccountsBalance() {
    return accountsBalance;
  }

  public void setAccountsBalance(HashMap<String, Integer> accountsBalance) {
    this.accountsBalance = accountsBalance;
  }

  public HashMap<Integer, String> getAccountType() {
    return accountType;
  }

  public void setAccountType(HashMap<Integer, String> accountType) {
    this.accountType = accountType;
  }

  

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
    result = prime * result + ((accountsBalance == null) ? 0 : accountsBalance.hashCode());
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
    result = prime * result + id;
    result = prime * result + accounts;
    result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
    result = prime * result + ((password == null) ? 0 : password.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Customer other = (Customer) obj;
    if (accountType == null) {
      if (other.accountType != null)
        return false;
    } else if (!accountType.equals(other.accountType))
      return false;
    if (accountsBalance == null) {
      if (other.accountsBalance != null)
        return false;
    } else if (!accountsBalance.equals(other.accountsBalance))
      return false;
    if (email == null) {
      if (other.email != null)
        return false;
    } else if (!email.equals(other.email))
      return false;
    if (firstName == null) {
      if (other.firstName != null)
        return false;
    } else if (!firstName.equals(other.firstName))
      return false;
    if (id != other.id)
      return false;
    if (accounts != other.accounts)
      return false;
    if (lastName == null) {
      if (other.lastName != null)
        return false;
    } else if (!lastName.equals(other.lastName))
      return false;
    if (password == null) {
      if (other.password != null)
        return false;
    } else if (!password.equals(other.password))
      return false;
    return true;
  }

  

  @Override
  public String toString() {
    return "Customer [accountType=" + accountType + ", accountsBalance=" + accountsBalance + ", email=" + email
        + ", firstName=" + firstName + ", id=" + id + ", accounts=" + accounts + ", lastName=" + lastName + ", password=" + password + "]";
  }

  public void displayMenu(Customer currCustomer) {
    Scanner sn = new Scanner(System.in);
    // String email = currCustomer.getEmail();

    try {
      int userInput = 0;
      while (userInput != 9) {
        System.out.println("Welcome back "+ firstName + ".");
        System.out.println("===============================");
        System.out.println("1. Open new Bank Account.");
        System.out.println("2. View Account Balance");
        System.out.println("3. Make a Deposit");
        System.out.println("4. Make a Withdrawl");
        System.out.println("5. Make a Transfer");
        System.out.println("6. Send Money");
        System.out.println("9. Log out");
        System.out.println("===============================");
        System.out.println("Please enter a number from the menue above: ");

        userInput = sn.nextInt();
        // sn.close();
        // Customer currCustomer = new Customer()
        switch (userInput) {
          case 1:
            openNewBankAccount(currCustomer);
            break;
          case 2:
            viewAccountBalance(currCustomer.getEmail());
            break;
          case 3:
            deposit(currCustomer.getEmail());
            break;
          case 4:
            withdraw(currCustomer.getEmail());
            break;
          case 5:
            sendMoney(currCustomer.getEmail());
            break;
          case 6:
            sendMoney(currCustomer.getEmail());
            break;
          case 9:
            System.exit(0);
            break;
        
          default:
            break;
        }
        
      }
    } catch (Exception e) {
      //TODO: handle exception
    }

  }

  
  public void openNewBankAccount(Customer currCustomer) {
    // choose from the account type you want to open [Checking, Saving, Investing]
    System.out.println("- - - Account Types - - - ");
    System.out.println("==========================");
    System.out.println("1. Checking");
    System.out.println("2. Saving");
    System.out.println("3. Investing");
    System.out.println("==========================");


    System.out.println("Enter a number to Choose the account you would like to open");
    Scanner sn = new Scanner(System.in);
    String accountType = null;
    int userInput = sn.nextInt();
    
    try {  
        
        switch (userInput) {
          case 1:
            accountType = "Checking";
            break;
          case 2:
          accountType = "Saving";
            break;
          case 3:
            accountType = "Investing";
            break;
          default:
            System.out.println("Invalid input");
            System.out.println("==========================");

            break;
        }
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    // dont forget to have exceptions for invalid inputs
    // to open a new bank account you must make a min deposit of $35 
    System.out.println("==========================");
    
    System.out.println("How much would you like to deposit into your " + accountType + " account? Min deposit of $35*** ");
    int initialDeposit;
    try {
      initialDeposit = sn.nextInt();
      int count = 2;
      while(count > 0) {

        if (initialDeposit < 35) {
          System.out.println("Initial deposit must be $35 to create " + accountType + " account");
          System.out.println("Attempts left: " + count + ".");
          count--;
          throw new InvalidInitialDepositException("Please try aagain");
          
        } else {
          count--;
          continue;
        }
        
      }
      // customerdaoimpl.createnewbankaccount to insert into db
    CustomerDaoImpl customer = new CustomerDaoImpl();
    customer.openNewBankAccount(accountType, initialDeposit, customer.getCustomerData(currCustomer.getEmail()));
      
    } catch (Exception e) {
      e.printStackTrace();
    } 

    
    // must confirm intial deposit before inserting db


  }

  public void viewAccountBalance(String email) {
    // choose an account you would like 
    System.out.println("=====================");
    System.out.println("- - - Account Types - - - ");
    System.out.println("==========================");
    System.out.println("1. Checking");
    System.out.println("2. Saving");
    System.out.println("3. Investing");
    System.out.println("==========================");


    System.out.println("Enter a number to view the balance associated with the account");
    Scanner sn = new Scanner(System.in);
    String accountType = null;
    int userInput = sn.nextInt();
    CustomerDaoImpl customer;
    try {  
        
        switch (userInput) {
          case 1:
            accountType = "Checking";
            customer = new CustomerDaoImpl();

            customer.viewAccountBalance(accountType, email);
            break;
          case 2:
            accountType = "Saving";
            customer = new CustomerDaoImpl();

            customer.viewAccountBalance(accountType, email);

            break;
          case 3:
            accountType = "Investing";
            customer = new CustomerDaoImpl();

            customer.viewAccountBalance(accountType, email);

            break;
          default:
            System.out.println("Invalid input");
            System.out.println("==========================");

            break;
        }
    } catch (Exception e) {
      e.printStackTrace();
    }

    // CustomerDaoImpl customer = new CustomerDaoImpl();

    // customer.viewAccountBalance(accountType, customer.getCustomerData(currCustomer.getEmail()));
    

  }

  public void withdraw(String email) {

    System.out.println("=====================");
    System.out.println("- - - Account Types - - - ");
    System.out.println("==========================");
    System.out.println("1. Checking");
    System.out.println("2. Saving");
    System.out.println("3. Investing");
    System.out.println("==========================");

    System.out.println("Which account do you want to withdraw from?     **Please enter a number** ");

    Scanner sn = new Scanner(System.in);
    String accountType = null;
    int userInput = sn.nextInt();

    System.out.println("How much would you like to withdraw? ");
    int payload = sn.nextInt();
    CustomerDaoImpl customer;
    try {  
        
        switch (userInput) {
          case 1:
            accountType = "Checking";
            customer = new CustomerDaoImpl();

            customer.withdraw(accountType, payload, email);
            break;
          case 2:
            accountType = "Saving";
            customer = new CustomerDaoImpl();

            customer.withdraw(accountType, payload, email);

            break;
          case 3:
            accountType = "Investing";
            customer = new CustomerDaoImpl();

            customer.withdraw(accountType, payload, email);

            break;
          default:
            System.out.println("Invalid input");
            System.out.println("==========================");

            break;
        }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public void deposit(String email) {

    System.out.println("=====================");
    System.out.println("- - - Account Types - - - ");
    System.out.println("==========================");
    System.out.println("1. Checking");
    System.out.println("2. Saving");
    System.out.println("3. Investing");
    System.out.println("==========================");

    System.out.println("Choose an account to deposit your funds to:      **Please enter a number** ");

    Scanner sn = new Scanner(System.in);
    String accountType = null;
    int userInput = sn.nextInt();

    System.out.println("How much would you like to deposit? ");

    int payload = sn.nextInt();
    CustomerDaoImpl customer;
    try {  
        
        switch (userInput) {
          case 1:
            accountType = "Checking";
            customer = new CustomerDaoImpl();

            customer.deposit(accountType, payload, email);
            break;
          case 2:
            accountType = "Saving";
            customer = new CustomerDaoImpl();

            customer.deposit(accountType, payload, email);

            break;
          case 3:
            accountType = "Investing";
            customer = new CustomerDaoImpl();

            customer.deposit(accountType, payload, email);

            break;
          default:
            System.out.println("Invalid input");
            System.out.println("==========================");

            break;
        }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void sendMoney(String email) {
    Scanner sn = new Scanner(System.in);
    System.out.println("Welcome to Money Transfers");
    System.out.println("===========================");
    System.out.println("How much would you like to send?");
    System.out.println("=====================");
    int payload= sn.nextInt();
    
    
    System.out.println("=====================");
    System.out.println("- - - Account Types - - - ");
    System.out.println("==========================");
    System.out.println("1. Checking");
    System.out.println("2. Saving");
    System.out.println("3. Investing");
    System.out.println("==========================");

    System.out.println("From which account:      **Please enter a number** ");

    
    String from = null;
    int userInput = sn.nextInt();
    System.out.println("===========================");
    System.out.println("Please enter the accountId of the account recieving your transfer: ");
    int to = sn.nextInt();

    
    CustomerDaoImpl customer;
    try {  
        
        switch (userInput) {
          case 1:
            from = "Checking";
            customer = new CustomerDaoImpl();

            customer.sendMoney(payload, from, to, email);
            break;
          case 2:
            from = "Saving";
            customer = new CustomerDaoImpl();

            customer.sendMoney(payload, from, to, email);

            break;
          case 3:
            from = "Investing";
            customer = new CustomerDaoImpl();

            customer.sendMoney(payload, from, to, email);

            break;
          default:
            System.out.println("Invalid input");
            System.out.println("==========================");

            break;
        }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  

}
