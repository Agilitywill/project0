package com.revature.exceptions;

public class UsernameTooShortException extends Exception{

	public int inputLength;
	
	public UsernameTooShortException(int length) {
		super("Sorry needs to be at least 6 characters");
		this.inputLength = length;
	}
}