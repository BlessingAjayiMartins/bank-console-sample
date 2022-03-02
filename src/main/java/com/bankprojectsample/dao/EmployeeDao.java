package com.bankprojectsample.dao;

import com.bankprojectsample.model.Employee;

public interface EmployeeDao {
  public Employee getEmployeeData(String email);
  public void printTransactionLog();
  public void viewCustomerBankAccounts(int customerId);
  // public void pendingCases();
}
