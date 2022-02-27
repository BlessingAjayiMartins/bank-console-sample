package com.bankprojectsample.model;

import java.util.HashMap;

public class Customer{
  private String firstName;
	private String lastName;
	private String email;
	private int id;
	private String password;
  private HashMap <String, Integer> accountsBalance = null;
  
  Customer () {

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

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public HashMap<String, Integer> getAccountsBalance() {
    return accountsBalance;
  }

  public void setAccountsBalance(HashMap<String, Integer> accountsBalance) {
    this.accountsBalance = accountsBalance;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((accountsBalance == null) ? 0 : accountsBalance.hashCode());
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
    result = prime * result + id;
    result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
    result = prime * result + ((password == null) ? 0 : password.hashCode());
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
    Customer other = (Customer) obj;
    if (accountsBalance == null) {
      if (other.accountsBalance != null)
        return false;
    } else if (!accountsBalance.equals(other.accountsBalance))
      return false;
    if (email == null) {
      if (other.email != null)
        return false;
    } else if (!email.equals(other.email))
      return false;
    if (firstName == null) {
      if (other.firstName != null)
        return false;
    } else if (!firstName.equals(other.firstName))
      return false;
    if (id != other.id)
      return false;
    if (lastName == null) {
      if (other.lastName != null)
        return false;
    } else if (!lastName.equals(other.lastName))
      return false;
    if (password == null) {
      if (other.password != null)
        return false;
    } else if (!password.equals(other.password))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Customer [accountsBalance=" + accountsBalance + ", email=" + email + ", firstName=" + firstName + ", id="
        + id + ", lastName=" + lastName + ", password=" + password + "]";
  }

  

  

}
