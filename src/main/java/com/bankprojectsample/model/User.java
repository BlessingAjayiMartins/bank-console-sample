package com.bankprojectsample.model;

import java.util.Scanner;

public class User {
  
  private String firstName;
	private String lastName;
	private String email;
	private int id;
	private String password;
  
  public User() {
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
		
		// user types email... check db if user exists 
	
    System.out.println("Please enter your email: ");
		String email = sn.nextLine();
	
    // then password... if verified give access to account (customer/Employee)
	
    System.out.println("Please enter your password: ");
		String password = sn.nextLine();
	
  
    // aka redirect to customer menu
		
	};
	public void createCustomerAccount() {
		Scanner sn = new Scanner(System.in);

		// to create an account we need to have the user email 
		
    System.out.println("Please enter an email.  ex: analace@gmail.com ");
		
		String email = sn.nextLine();
	
    // check with existing db to see if email exists.
      // consider a callableStatement | procedue | sequence | something that returns a boolean
      // from the customer table...
        // if email exists, return first name saying this name is associated with this email. 
        // is this you? do you want to open a new bank account? okay, lets get you logged in first>>>
  
    // if emailExists = false. ask for first name and last name.
		System.out.println("Please enter your first name: ");
		String firstName = sn.nextLine();
		System.out.println("Please enter your last name: ");
		String lastName = sn.nextLine();
		
    
		// then create password and save new account in the db with a generated id
    // ask user to type in a password
		System.out.println("Please enter a password: ");
		String password = sn.nextLine();

    // retype passowrd
		System.out.println("Please renter password: ");
		String passwordCheck = sn.nextLine();

    // confirm password is the same ith equals method.

    // place inputs in a user/customer constructor object and send it to the database to be added to the customer record.
		// redirect to customer class to open bank account
		// choose an account type to open. min deposit of $35
		// logout
	};
}
