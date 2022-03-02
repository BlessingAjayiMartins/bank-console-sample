package com.bankprojectsample.dao;

import com.bankprojectsample.model.Customer;
import com.bankprojectsample.model.Employee;
import com.bankprojectsample.model.User;

public interface UserDao {
  public Customer login(String email, String password);
  public Employee employeeLogin (String email, String password);
  public boolean createCustomerAccount(User newUser);
  public boolean userExists(String email);
  public boolean userExists(String email, String password);
  public boolean employeeExists(String email, String password);

}
