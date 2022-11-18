package com.service.banking.exception;

public class BankAccIdNotFoundException extends Exception{
	private Long accId;

	public BankAccIdNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BankAccIdNotFoundException(Long accId) {
		super();
		this.accId = accId;
	}

	@Override
	public String toString() {
		return "BankAccIdNotFoundException accId: " + accId;
	}
	
}
