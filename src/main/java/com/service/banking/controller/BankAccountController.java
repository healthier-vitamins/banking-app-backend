package com.service.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.banking.exception.UsernameIsTakenException;
import com.service.banking.model.BankAccount;
import com.service.banking.service.BankAccountService;

@RestController
@RequestMapping("/api/bank-acc")
@CrossOrigin(origins = "http://localhost:4200")
public class BankAccountController {
	
	@Autowired
	private BankAccountService bankAccService;
	
	@PostMapping("/save-user")
	public ResponseEntity<?> saveBankAccAndUser(@RequestBody BankAccount bankAcc) {
		BankAccount savedBankAcc;
		try {
			savedBankAcc = bankAccService.saveBankAccAndUser(bankAcc);
		} catch (UsernameIsTakenException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Username taken");
		}
		return ResponseEntity.ok(savedBankAcc);
	}
}
