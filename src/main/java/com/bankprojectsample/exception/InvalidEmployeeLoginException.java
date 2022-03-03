package com.bankprojectsample.exception;

public class InvalidEmployeeLoginException extends Exception{
  public InvalidEmployeeLoginException(String str) {
    super(str);
  }
}
