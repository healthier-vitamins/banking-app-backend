package com.service.banking.service;

import java.util.List;

import com.service.banking.exception.BankAccIdNotFoundException;
import com.service.banking.exception.EmptyDatabaseException;
import com.service.banking.exception.UsernameIsTakenException;
import com.service.banking.model.BankAccount;

public interface BankAccountService {
	
	public BankAccount saveBankAccAndUser(BankAccount bankAcc) throws UsernameIsTakenException;
	public BankAccount updateBankAcc(BankAccount bankAcc);
	public void delBankAccAndUser(Long bankAccId) throws BankAccIdNotFoundException;
	public BankAccount getBankAccountById(Long bankAccId);
	public List<BankAccount> getAllBankAccounts() throws EmptyDatabaseException;
	public Long getBankAccCount();
}
