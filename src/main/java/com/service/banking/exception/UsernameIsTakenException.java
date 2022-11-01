package com.service.banking.exception;

public class UsernameIsTakenException extends Exception{
	
	private String username;

	public UsernameIsTakenException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UsernameIsTakenException(String username) {
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
		return "UsernameIsTakenException " + username;
	}
	
}
