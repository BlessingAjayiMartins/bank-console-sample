package com.bankprojectsample.model;

import java.util.Scanner;

import com.bankprojectsample.dao.EmployeeDaoImpl;

public class Employee {
  private int employeeId;
  private String firstName;
  private String lastName;
  private String email;
  private Employee employee;

  
  public Employee() {
  }
  
  public Employee(Employee employee) {
    this.employee = employee;
  }
  
  public Employee(int employeeId, String firstName, String lastName, String email) {
    this.employeeId = employeeId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public int getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(int employeeId) {
    this.employeeId = employeeId;
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


  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + employeeId;
    result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
    result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
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
    Employee other = (Employee) obj;
    if (email == null) {
      if (other.email != null)
        return false;
    } else if (!email.equals(other.email))
      return false;
    if (employeeId != other.employeeId)
      return false;
    if (firstName == null) {
      if (other.firstName != null)
        return false;
    } else if (!firstName.equals(other.firstName))
      return false;
    if (lastName == null) {
      if (other.lastName != null)
        return false;
    } else if (!lastName.equals(other.lastName))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Employee [email=" + email + ", employeeId=" + employeeId + ", firstName=" + firstName + ", lastName="
        + lastName + "]";
  }

  public void displayMenu(Employee currEmployee) {
    Scanner sn = new Scanner(System.in);
    

    try {
      int userInput = 0;
      while (userInput != 9) {
        System.out.println("Welcome back "+ firstName + " .");
        System.out.println("===============================");
        System.out.println(" E M P L O Y E E   P O R T A L ");
        System.out.println("===============================");
        System.out.println("1. View pending cases/accounts.");
        System.out.println("2. View a customer's bank accounts");
        System.out.println("3. View a log of all transactions.");
        System.out.println("9. Log out");
        System.out.println("===============================");
        System.out.println("Please enter a number from the menu above: ");

        userInput = sn.nextInt();
       
        switch (userInput) {
          case 1:
            // openNewBankAccount(currEmployee);
            break;
          case 2:
            viewCustomerBankAccounts(currEmployee);
            break;
          case 3:
            viewTransactionLog(currEmployee);
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
    sn.close();
  }
  public void viewCustomerBankAccounts(Employee employee) {
    Scanner sn = new Scanner(System.in);

    System.out.println("==============================");
    System.out.println("V I E W   C U S T O M E R   A C C O U N T");
    System.out.println("==============================");
    System.out.println("Please Enter the customers routing number to view balance: ");   //routing number is account_id
    int routingNum = sn.nextInt();


    EmployeeDaoImpl currEmployee = new EmployeeDaoImpl();
    // currEmployee.getEmployeeData(email);
    currEmployee.viewCustomerBankAccounts(routingNum, employee);
  }


  public void viewTransactionLog(Employee employee) {
    EmployeeDaoImpl currEmployee = new EmployeeDaoImpl();
    currEmployee.printTransactionLog(employee);

  }
}
