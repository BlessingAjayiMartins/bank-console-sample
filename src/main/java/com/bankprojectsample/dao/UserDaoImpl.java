package com.bankprojectsample.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bankprojectsample.model.User;
import com.bankprojectsample.utility.DBConnection;


public class UserDaoImpl implements UserDao {

  Connection connection = DBConnection.getConnection();

  public void login(User user) {
    System.out.println("Logging In...");
    PreparedStatement statement = null;

    
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
			// TODO Auto-generated catch block
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
