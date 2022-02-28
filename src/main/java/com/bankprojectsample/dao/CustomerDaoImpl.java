package com.bankprojectsample.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;



import com.bankprojectsample.model.Customer;
import com.bankprojectsample.utility.DBConnection;

public class CustomerDaoImpl implements CustomerDao {

  Connection connection = DBConnection.getConnection();
  Customer customerState;

  
  @Override
  public Customer getCustomerData(String email) {
    Customer currCustomer = new Customer();
		PreparedStatement statement = null;
    String firstName;
    String lastName;
    int customer_id;
    int accounts;
    String password;
    HashMap <String, Integer> accountsBalance;
    HashMap <Integer, String> accountType;

    // int rows = 0;
		try {
			statement = connection.prepareStatement("select * from customer where email in (?)");
			statement.setString(1, email);
			


			ResultSet result = statement.executeQuery();
      result.next();
			customer_id = result.getInt("id");
      firstName = result.getString("first_name");
      lastName = result.getString("last_name");
      password = result.getString("password");
      accounts = result.getInt("accounts");

      currCustomer.setFirstName(firstName);
      currCustomer.setLastName(lastName);
      currCustomer.setId(customer_id);
      currCustomer.setPassword(password);
      currCustomer.setEmail(email);
      currCustomer.setAccounts(accounts);

      if (currCustomer.getAccounts() > 0) {
      // retrieve account details from account table
      statement = connection.prepareStatement("select * from account where customer_id in (?)");
      statement.setInt(1, customer_id);
      result = statement.executeQuery();
      int accountId;
      String type;
      int balance;
      accountsBalance = new HashMap <String, Integer>();
      accountType = new HashMap <Integer, String>() ;

      while (result.next()) {
        accountId = result.getInt("account_id");
        type = result.getString("account_type");
        balance = result.getInt("balance");

        accountType.put(accountId,type);
        accountsBalance.put(type, balance);
      }
      currCustomer.setAccountType(accountType);
      currCustomer.setAccountsBalance(accountsBalance);
      result.close();
      statement.close();
      connection.close();
      } 
      
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    // currCustomer = new Customer(firstName, lastName, email, customer_id, password, accountsBalance, accountType);
    customerState = currCustomer;
    return currCustomer;
  }

  @Override
  public void openNewBankAccount(String accountType, int initialDeposit, Customer customer) {
    int customerId = customer.getId();
    int accounts = customer.getAccounts() + 1;
    Date date = new Date();
    PreparedStatement statement = null;
    int row = 0;
    try {
      System.out.println("working on it");
      statement = connection.prepareStatement("insert into account(account_id, account_type, balance, date_created, approved_by, customer_id) values(default,?,?,?,?,?)");

      statement.setString(1, accountType);
      statement.setInt(2, initialDeposit);
      statement.setString(3, date.toString());
      statement.setInt(4, 1);
      statement.setInt(5, customerId);
      // statement.setString(6, Integer.toString(accounts));
      System.out.println("finalizing..");
      row = statement.executeUpdate();
      System.out.println(row + "row in account has been updated");

      statement = connection.prepareStatement("update customer set accounts = ? where id in (?)");

      statement.setInt(1, accounts);
      statement.setInt(2, customerId);
      
      row = statement.executeUpdate();

      
      // update state
      getCustomerData(customer.getEmail());

    } catch (Exception e) {
      //TODO: handle exception
    }
    
  }

  @Override
  public void viewAccountBalance(String accountType, String email) {
    Customer currCustomer = getCustomerData(email);
    if (currCustomer == null) {
      System.out.println("Somethin is wrong... customer state is null.");
    } else if (!currCustomer.getAccountsBalance().containsKey(accountType)) {
      System.out.println("The account type " +accountType+ "is not associated with your account.");
    } else {
      int currBalance = currCustomer.getAccountsBalance().get(accountType);
      System.out.println(accountType+ "Account");
      System.out.println(currBalance);

    }

    
  }

  @Override
  public void withdraw(String accountType, int payload) {
    



      getCustomerData(customerState.getEmail());
    
  }

  @Override
  public void deposit(String accountType, int payload) {
    



    getCustomerData(customerState.getEmail());
    
  }

  @Override
  public void sendMoney() {
    
    
    getCustomerData(customerState.getEmail());
  }
  
}
