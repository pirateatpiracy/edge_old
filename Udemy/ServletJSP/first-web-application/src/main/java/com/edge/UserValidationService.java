package com.edge;

public class UserValidationService {
	
	public boolean isUserValid(String user, String pass) {
		if (user.equals("edge") && pass.equals("pass") )
		return true;
		return false;
	}

}
