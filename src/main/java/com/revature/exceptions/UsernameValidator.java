package com.revature.exceptions; 

import com.revature.exceptions.InappropriateCharacterException;
import com.revature.exceptions.UsernameTooShortException;

public interface UsernameValidator {
	
	
	boolean validUsername(String username) throws InappropriateCharacterException, UsernameTooShortException;

}