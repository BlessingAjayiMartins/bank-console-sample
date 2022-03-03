package com.bankprojectsample.model;

import java.util.Scanner;

import com.bankprojectsample.dao.CustomerDaoImpl;
import com.bankprojectsample.dao.UserDaoImpl;
import com.bankprojectsample.exception.InvalidCustomerLoginException;
import com.bankprojectsample.exception.InvalidEmployeeLoginException;
import com.bankprojectsample.exception.InvalidInitialDepositException;

public class User {
  
  private String firstName;
	private String lastName;
	private String email;
	private int id;
	private String password;
  
  public User () {

  }

  public User(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public User(String firstName, String lastName, String email, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
    result = prime * result + id;
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
    User other = (User) obj;
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
    return "User [email=" + email + ", firstName=" + firstName + ", id=" + id + ", lastName=" + lastName + ", password="
        + password + "]";
  }

  

  // takes username and password
	public void login() {
		Scanner sn = new Scanner(System.in);
    System.out.println("===============================");
    System.out.println("  C U S T O M E R   L O G I N  ");
    System.out.println("===============================");
		
		// user types email... check db if user exists 
	
    System.out.println("Please enter your email: ");
		String email = sn.nextLine();
    System.out.println("===============================");

	
    // then password... if verified give access to account (customer/Employee)
	
    System.out.println("Please enter your password: ");
		String password = sn.nextLine();
    System.out.println("===============================");
	  UserDaoImpl user = new UserDaoImpl();
    
    Customer currCustomer;
    currCustomer = user.login(email, password);
    

    
      
    
      try {
        if (currCustomer.getFirstName() == null) {
          sn.close();
          throw new InvalidCustomerLoginException("System Error: Invalid Credentials for Customer login");
        } else {
          System.out.println(currCustomer.getFirstName()+" Logged In");
          currCustomer.displayMenu(currCustomer);
        }
          
      } catch (Exception e) {
        e.printStackTrace();
      }
    

    sn.close();
	};


	public void createCustomerAccount() {
		Scanner sn = new Scanner(System.in);

    System.out.println("===============================================");
    System.out.println(" C R E A T E   C U S T O M E R   A C C O U N T ");
    System.out.println("===============================================");

		
		
    System.out.println("Please enter an email.  ex: analace@gmail.com ");
		
		String email = sn.nextLine();
    System.out.println("===============================");
    
   
		System.out.println("Please enter your first name: ");
		String firstName = sn.nextLine();
    System.out.println("===============================");

		System.out.println("Please enter your last name: ");
		String lastName = sn.nextLine();
    System.out.println("===============================");

		
    
		
		System.out.println("Please enter a password: ");
		String password = sn.nextLine();
    System.out.println("===============================");


    
		System.out.println("Please renter password: ");
		String passwordCheck = sn.nextLine();
    System.out.println("===============================");


    
    User newUser = new User(firstName, lastName, email, password);
    
    if (password.equals(passwordCheck)) {
      UserDaoImpl addUser = new UserDaoImpl();
      addUser.createCustomerAccount(newUser);
    }
   sn.close();
	};


  public void employeeLogin() {
    Scanner sn = new Scanner(System.in);

    System.out.println("===============================");
    System.out.println(" E M P L O Y E E    L O G I N ");
    System.out.println("===============================");

    // user types email... check db if user exists 

    System.out.println("Please enter your email: ");
    String email = sn.nextLine();
    System.out.println("===============================");

    System.out.println("Please enter your password: ");
    String password = sn.nextLine();
    UserDaoImpl user = new UserDaoImpl();
    System.out.println("===============================");


    Employee currEmployee;
    currEmployee = user.employeeLogin(email, password);

    try {
      if (currEmployee.getFirstName() == null) {
        sn.close();
        throw new InvalidEmployeeLoginException("System Error: Invalid Credentials for Employee login");
      } else {
        System.out.println(currEmployee+" successful");
        currEmployee.displayMenu(currEmployee);
      }
        
    } catch (Exception e) {
      e.printStackTrace();
    }

    sn.close();
  }

}