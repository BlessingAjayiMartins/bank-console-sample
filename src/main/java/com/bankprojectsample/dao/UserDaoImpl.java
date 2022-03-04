package com.bankprojectsample.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bankprojectsample.model.Customer;
import com.bankprojectsample.model.Employee;
import com.bankprojectsample.model.User;
import com.bankprojectsample.utility.DBConnection;


public class UserDaoImpl implements UserDao {

  Connection connection = DBConnection.getConnection();

  public Customer login(String email, String password) {
    // System.out.println("Logging In...");
    // PreparedStatement statement = null;
    UserDaoImpl test = new UserDaoImpl();
    Customer currCustomer;
    CustomerDaoImpl potentialCustomer = new CustomerDaoImpl();

      
      currCustomer = (test.userExists(email, password)) ? potentialCustomer.getCustomerData(email) :  new Customer();
      // System.out.println("login succesful");
      return currCustomer;

   
  }

  public boolean employeeExists(String email, String password) {
    boolean employeeExist = false;
		PreparedStatement stat;
		try {
			stat = connection.prepareStatement("select * from employee where email = ? and password = ? ");
			stat.setString(1, email);
			stat.setString(2, password);

			ResultSet res = stat.executeQuery();
			employeeExist = res.next();
      // return employeeExist;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeExist;
  }

  public Employee employeeLogin(String email, String password) {
    
    // PreparedStatement statement = null;
    UserDaoImpl test = new UserDaoImpl();
    Employee currEmployee;
    EmployeeDaoImpl potentialEmployee = new EmployeeDaoImpl();

      
      currEmployee = (test.employeeExists(email, password)) ? potentialEmployee.getEmployeeData(email) :  new Employee();
      
      return currEmployee;

   
  }

  public boolean createCustomerAccount(User newUser) {
    // System.out.println("##Adding user :" + login);
		PreparedStatement statement = null;
		int rows = 0;
		try {
			statement = connection.prepareStatement("insert into customer(id, first_name, last_name, email, password) values(default,?,?,?,?)");
			statement.setString(1, newUser.getFirstName());
			statement.setString(2, newUser.getLastName());
      statement.setString(3, newUser.getEmail());
      statement.setString(4, newUser.getPassword());


			rows = statement.executeUpdate();
			System.out.println(rows + " new user registered successfully");

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		if (rows == 0)
			return false;
		else
			return true;
    
  }

  public boolean userExists(String email) {
    boolean userExist = false;
		PreparedStatement stat;
		try {
			stat = connection.prepareStatement("select * from customer where email = ? ");
			stat.setString(1, email);
			

			ResultSet res = stat.executeQuery();
			userExist = res.next();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userExist;
  }
  

  public boolean userExists(String email, String password) {
    boolean userExist = false;
		PreparedStatement stat;
		try {
			stat = connection.prepareStatement("select * from customer where email = ? and password = ? ");
			stat.setString(1, email);
			stat.setString(2, password);

			ResultSet res = stat.executeQuery();
			userExist = res.next();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userExist;
  }
  
}
