package com.revature.exceptions;

import com.revature.exceptions.*;

public class UsernameValidatorImpl implements UsernameValidator {

	public boolean validUsername(String username) throws InappropriateCharacterException, UsernameTooShortException {
		
		if(username.contains(" ")) {
			InappropriateCharacterException ice = new InappropriateCharacterException();
			throw ice;
		}
		
		if(username.length() < 6) {
			UsernameTooShortException utse = new UsernameTooShortException(username.length());
			throw utse;
		}

		return true;
	}

}
