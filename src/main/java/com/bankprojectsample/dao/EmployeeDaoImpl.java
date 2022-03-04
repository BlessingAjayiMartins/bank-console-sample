package com.bankprojectsample.dao;

// import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bankprojectsample.model.Employee;
import com.bankprojectsample.utility.DBConnection;

public class EmployeeDaoImpl implements EmployeeDao {

  Connection connection = DBConnection.getConnection();

  @Override
  public Employee getEmployeeData(String email) {
    Employee currEmployee = new Employee();
    PreparedStatement statement = null;
    String firstName;
    String lastName;
    int employeeId;

    try {
      statement = connection.prepareStatement("select * from employee where email in (?)");
			statement.setString(1, email);

      ResultSet result = statement.executeQuery();
      result.next();
			employeeId = result.getInt("employee_id");
      firstName = result.getString("first_name");
      lastName = result.getString("last_name");

      currEmployee.setFirstName(firstName);
      currEmployee.setLastName(lastName);
      currEmployee.setEmployeeId(employeeId);
      currEmployee.setEmail(email);

      // result.close();
      // statement.close();
      // connection.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return currEmployee;
  }

  @Override
  public void printTransactionLog(Employee currEmployee) {
    Connection connection = DBConnection.getConnection();
		PreparedStatement statement = null;

    if (currEmployee == null) {
      System.out.println("Somethin is wrong... Employee state is null.");
    }else {
      try {
        // statement = connection.prepareStatement("select * from print_transaction_log()");  // stored procedure need fixing
        statement = connection.prepareStatement("select transaction_id, transaction_type, payload, from_, to_, date_approved from transaction");
        ResultSet result = statement.executeQuery();
        while (result.next()) {
          System.out.println("Transaction ID: "+result.getString(1)+" | Transaction Type: "+result.getString(2)+" | Payload: "+result.getString(3)+" | From: "+result.getString(4)+" | To: "+result.getString(5)+" | Date: "+result.getString(6));
          System.out.println("====================================");
        }
  
        // result.close();
        // statement.close();
        // connection.close();
  
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    
    
    
  }

  @Override
  public void viewCustomerBankAccounts(int routingNum, Employee currEmployee) {
    Connection connection = DBConnection.getConnection();

  
    if (currEmployee == null) {
      System.out.println("Somethin is wrong... Employee state is null.");
    } else {
        PreparedStatement statement = null;
    
        try {
          statement = connection.prepareStatement("select * from account where account_id in (?)");
          statement.setInt(1, routingNum);
          ResultSet result = statement.executeQuery();
          while (result.next()) { 
            int currBalance = result.getInt("balance");

            System.out.println("=====================");
            System.out.println("   B A L A N C E  ");
            System.out.println("=====================");
            System.out.println("    "+currBalance);
            System.out.println("=====================");
          }
        
        } catch (Exception e) {
          e.printStackTrace();
        }


    }


		
    
  }
  
}
