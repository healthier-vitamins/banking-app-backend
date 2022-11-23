package com.service.banking.exception;

public class UserUsernameNotFoundException extends Exception{

	String username;

	public UserUsernameNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserUsernameNotFoundException(String username) {
		super();
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "UserUsernameNotFoundException username: " + username ;
	}
	
}
