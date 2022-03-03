package com.bankprojectsample.exception;

public class InvalidAccountException extends Exception{
    public InvalidAccountException(String str) {
      super(str);
    }
}
