package com.bankprojectsample.dao;

// import java.sql.CallableStatement;
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
  // Customer customerState;

  
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
      // result.close();
      // statement.close();
      // connection.close();
      } 
      
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    // currCustomer = new Customer(firstName, lastName, email, customer_id, password, accountsBalance, accountType);
    // customerState = currCustomer;
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
      System.out.println(row + "row in customer has been updated");


      // submit a query in the account table where customer_id and account_type are eaqual to your variable. get account_id from that row
      // ===================================
        statement = connection.prepareStatement("select * from account where customer_id = ? and account_type = ?");

        statement.setInt(1, customerId);
        statement.setString(2, accountType);

        ResultSet result = statement.executeQuery();
        int account_id;
        result.absolute(1);
        account_id = result.getInt("account_id");

      // ===================================
      statement = connection.prepareStatement("insert into transaction(transaction_id, transaction_type, payload, from_, to_, date_initiated, date_approved, date_denied, status) values(default,?,?,?,?,?,?,?,?)");
      
      statement.setString(1, "Deposit");
      statement.setInt(2, initialDeposit);
      statement.setInt(3, 0); // 0 in the system will be seen as the currUser.
      statement.setInt(4, account_id);
      statement.setString(5, date.toString());
      statement.setString(6, date.toString());
      statement.setString(7, "N/A");
      statement.setString(8, "Pending");

      row = statement.executeUpdate();

      System.out.println(row+" row/s has been updated in the transaction table");
      // ===========================================

 

      // result.close();
      // statement.close();
      // connection.close();
      

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
      System.out.println("=====================");
      System.out.println("   B A L A N C E  ");
      System.out.println("=====================");
      System.out.println("    "+currBalance);
      System.out.println("=====================");


    }

    
  }

  @Override
  public void withdraw(String accountType, int payload, String email) {
    // uses email as key to get the current customer data
    Customer currCustomer = getCustomerData(email);
    int customerId = currCustomer.getId();
    Date date = new Date();
    PreparedStatement statement = null;
    int row = 0;
    try {
      
      // submit a query in the account table where customer_id and account_type are eaqual to your variable. get account_id from that row
      // ===================================
      statement = connection.prepareStatement("select * from account where customer_id = ? and account_type = ?");

      statement.setInt(1, customerId);
      statement.setString(2, accountType);

      ResultSet result = statement.executeQuery();
      int account_id;
      result.next();
      account_id = result.getInt("account_id");
      // subtract the payload from the current account balance
      // ===================================
      int balance = currCustomer.getAccountsBalance().get(accountType);
      balance = balance - payload;


      // update the account table to reflect the change
      // ===================================
      statement = connection.prepareStatement("update account set balance = ? where account_id in (?)");

      statement.setInt(1, balance);
      statement.setInt(2, account_id);

      row = statement.executeUpdate();

      System.out.println(row+" row has been updated in the account table");
      // ===================================
      statement = connection.prepareStatement("insert into transaction(transaction_id, transaction_type, payload, from_, to_, date_initiated, date_approved, date_denied, status) values(default,?,?,?,?,?,?,?,?)");

      statement.setString(1, "Withdraw");
      statement.setInt(2, payload);
      statement.setInt(3, account_id); 
      statement.setInt(4, 0);  // 0 represents "self" 
      statement.setString(5, date.toString());
      statement.setString(6, date.toString());
      statement.setString(7, "N/A");
      statement.setString(8, "Pending");

      row = statement.executeUpdate();

      System.out.println(row+" row/s has been updated in the transaction table");
      // ===========================================

      // result.close();
      // statement.close();
      // connection.close();
    } catch (Exception e) {
      //TODO: handle exception
    }
    

    
     





      
    
  }

  @Override
  public void deposit(String accountType, int payload, String email) {
    
    // uses email as key to get the current customer data
    Customer currCustomer = getCustomerData(email);
    int customerId = currCustomer.getId();
    Date date = new Date();
    System.out.println("making a deposit..."+customerId+" "+".");
    PreparedStatement statement = null;
    int row = 0;
    try {
      
      // submit a query in the account table where customer_id and account_type are eaqual to your variable. get account_id from that row
      // ===================================
      statement = connection.prepareStatement("select * from account where customer_id = ? and account_type = ?");

      statement.setInt(1, customerId);
      statement.setString(2, accountType);
      System.out.println("account Type: "+ accountType);
      System.out.println("customer id: "+ customerId);

      ResultSet result = statement.executeQuery();
      int account_id;
      result.next();
      account_id = result.getInt("account_id");
      // add the payload from the current account balance
      // ===================================
      int balance = currCustomer.getAccountsBalance().get(accountType);
      balance = balance + payload;

        System.out.println("...");
      // update the account table to reflect the change
      // ===================================
      statement = connection.prepareStatement("update account set balance = ? where account_id in (?)");

      statement.setInt(1, balance);
      statement.setInt(2, account_id);

      row = statement.executeUpdate();

      System.out.println(row+" row has been updated in the account table");
      System.out.println("...");
      
      // update transaction table
      // ===================================
      statement = connection.prepareStatement("insert into transaction(transaction_id, transaction_type, payload, from_, to_, date_initiated, date_approved, date_denied, status) values(default,?,?,?,?,?,?,?,?)");

      statement.setString(1, "Deposit");
      statement.setInt(2, payload);
      statement.setInt(3, 0); // 0 represents "self"
      statement.setInt(4, account_id);   
      statement.setString(5, date.toString());
      statement.setString(6, date.toString());
      statement.setString(7, "N/A");
      statement.setString(8, "Pending");

      row = statement.executeUpdate();
      
      System.out.println(row+" row/s has been updated in the transaction table");
      // ===========================================

       
    } catch (Exception e) {
      e.printStackTrace();
    } 
    
    //  result.close();
    //   statement.close();
    //   connection.close();



   
    
  }

  @Override
  public void sendMoney(int payload, String accountType, int to, String email) {

    
  
  // uses email as key to get the current customer data
    Customer currCustomer = getCustomerData(email);
    int customerId = currCustomer.getId();
    Date date = new Date();
    PreparedStatement statement = null;
    int row = 0;
    try {
      
      // submit a query in the account table where customer_id and account_type are eaqual to your variable. get account_id from that row
      // ===================================
      statement = connection.prepareStatement("select * from account where customer_id = ? and account_type = ?");

      statement.setInt(1, customerId);
      statement.setString(2, accountType);

      ResultSet result = statement.executeQuery();
      int account_id;
      result.next();
      account_id = result.getInt("account_id");
      // subtract the payload from the current account balance
      // ===================================
      int balance = currCustomer.getAccountsBalance().get(accountType);
      balance = balance - payload;



      // update the account table to reflect the change of both users
      // user1
      // ===================================
      statement = connection.prepareStatement("update account set balance = ? where account_id in (?)");

      statement.setInt(1, balance);
      statement.setInt(2, account_id);

      row = statement.executeUpdate();

      System.out.println(row+" row has been updated in the account table");
      // user2 - get and update balance
      // ===================================
      // get
      // ===================================
      statement = connection.prepareStatement("select * from account where account_id = ?");

      statement.setInt(1, to);

      result = statement.executeQuery();
      result.next();
      int recieversBalance = result.getInt("balance");
      recieversBalance = recieversBalance + payload;
      // ====================================
      // update
      // ====================================
      statement = connection.prepareStatement("update account set balance = ? where account_id in (?)");

      statement.setInt(1, recieversBalance);
      statement.setInt(2, to);

      row = statement.executeUpdate();

      System.out.println(row+" row has been updated in the account table");
      // update transaction table
      // ===================================
      statement = connection.prepareStatement("insert into transaction(transaction_id, transaction_type, payload, from_, to_, date_initiated, date_approved, date_denied, status) values(default,?,?,?,?,?,?,?,?)");

      statement.setString(1, "Transfer");
      statement.setInt(2, payload);
      statement.setInt(3, account_id); 
      statement.setInt(4, to);   
      statement.setString(5, date.toString());
      statement.setString(6, date.toString());
      statement.setString(7, "N/A");
      statement.setString(8, "Pending");

      row = statement.executeUpdate();

      System.out.println(row+" row/s has been updated in the transaction table");
      // ===========================================

      // result.close();
      // statement.close();
      // connection.close();
    } catch (Exception e) {

    }
    
    
    
    
  }
  
}
