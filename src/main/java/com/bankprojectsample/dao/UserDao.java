package com.bankprojectsample.dao;

import com.bankprojectsample.model.User;

public interface UserDao {
  public void login(User user);
  public boolean createCustomerAccount(User newUser);
  public boolean userExists(String email);
  public boolean userExists(String email, String password);

}
