package com.bankprojectsample.dao;

import com.bankprojectsample.model.Customer;

public interface CustomerDao {
  // called after login in to initialize variables in currentCustomer object to be used 
  public Customer getCustomerData(String email);

  public void openNewBankAccount(String accountType, int initialDeposit, Customer customer);

  public void viewAccountBalance(String accountType, String email);

  public void withdraw(String accountType, int payload, String email);

  public void deposit(String accountType, int payload, String email);

  public void sendMoney(int payload, String from, int to, String email);
}
