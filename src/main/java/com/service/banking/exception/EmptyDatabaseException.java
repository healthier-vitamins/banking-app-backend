package com.service.banking.exception;

public class EmptyDatabaseException extends Exception{

	public EmptyDatabaseException() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "EmptyDatabaseException";
	}
	
}
