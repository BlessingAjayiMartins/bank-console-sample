package com.bankprojectsample.dao;

import com.bankprojectsample.model.Employee;

public interface EmployeeDao {
  public Employee getEmployeeData(String email);
  public void printTransactionLog(Employee employee);
  public void viewCustomerBankAccounts(int routingNum, Employee employee); // aka account_id
  // public void pendingCases();
}
